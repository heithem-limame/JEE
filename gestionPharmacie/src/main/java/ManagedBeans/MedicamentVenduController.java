package ManagedBeans;

import entityBeans.MedicamentVendu;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import sessionBeans.MedicamentVenduFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("medicamentVenduController")
@SessionScoped
public class MedicamentVenduController implements Serializable {

    @EJB
    private sessionBeans.MedicamentVenduFacade ejbFacade;
    private List<MedicamentVendu> items = null;
    private MedicamentVendu selected;

    public MedicamentVenduController() {
    }

    public MedicamentVendu getSelected() {
        return selected;
    }

    public void setSelected(MedicamentVendu selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMedicamentVenduPK().setCodeFamille(selected.getMedicament().getMedicamentPK().getCodeFamille());
        selected.getMedicamentVenduPK().setDateNote(selected.getNote().getDateNote());
        selected.getMedicamentVenduPK().setIdMedicament(selected.getMedicament().getMedicamentPK().getIdMedicament());
    }

    protected void initializeEmbeddableKey() {
        selected.setMedicamentVenduPK(new entityBeans.MedicamentVenduPK());
    }

    private MedicamentVenduFacade getFacade() {
        return ejbFacade;
    }

    public MedicamentVendu prepareCreate() {
        selected = new MedicamentVendu();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentVenduCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentVenduUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MedicamentVenduDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MedicamentVendu> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    private void persist(PersistAction persistAction, String successMessage) {
        if (selected != null) {
            setEmbeddableKeys();
            try {
                if (persistAction != PersistAction.DELETE) {
                    getFacade().edit(selected);
                } else {
                    getFacade().remove(selected);
                }
                JsfUtil.addSuccessMessage(successMessage);
            } catch (EJBException ex) {
                String msg = "";
                Throwable cause = ex.getCause();
                if (cause != null) {
                    msg = cause.getLocalizedMessage();
                }
                if (msg.length() > 0) {
                    JsfUtil.addErrorMessage(msg);
                } else {
                    JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
                }
            } catch (Exception ex) {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
                JsfUtil.addErrorMessage(ex, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            }
        }
    }

    public MedicamentVendu getMedicamentVendu(entityBeans.MedicamentVenduPK id) {
        return getFacade().find(id);
    }

    public List<MedicamentVendu> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MedicamentVendu> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MedicamentVendu.class)
    public static class MedicamentVenduControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicamentVenduController controller = (MedicamentVenduController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicamentVenduController");
            return controller.getMedicamentVendu(getKey(value));
        }

        entityBeans.MedicamentVenduPK getKey(String value) {
            entityBeans.MedicamentVenduPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entityBeans.MedicamentVenduPK();
            key.setDateNote(java.sql.Date.valueOf(values[0]));
            key.setCodeFamille(Integer.parseInt(values[1]));
            key.setIdMedicament(Integer.parseInt(values[2]));
            return key;
        }

        String getStringKey(entityBeans.MedicamentVenduPK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getDateNote());
            sb.append(SEPARATOR);
            sb.append(value.getCodeFamille());
            sb.append(SEPARATOR);
            sb.append(value.getIdMedicament());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MedicamentVendu) {
                MedicamentVendu o = (MedicamentVendu) object;
                return getStringKey(o.getMedicamentVenduPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MedicamentVendu.class.getName()});
                return null;
            }
        }

    }

}
