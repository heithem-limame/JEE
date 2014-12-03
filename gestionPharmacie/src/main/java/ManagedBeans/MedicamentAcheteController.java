package ManagedBeans;

import entityBeans.MedicamentAchete;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import sessionBeans.MedicamentAcheteFacade;

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

@Named("medicamentAcheteController")
@SessionScoped
public class MedicamentAcheteController implements Serializable {

    @EJB
    private sessionBeans.MedicamentAcheteFacade ejbFacade;
    private List<MedicamentAchete> items = null;
    private MedicamentAchete selected;

    public MedicamentAcheteController() {
    }

    public MedicamentAchete getSelected() {
        return selected;
    }

    public void setSelected(MedicamentAchete selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMedicamentAchetePK().setCodeFamille(selected.getMedicament().getMedicamentPK().getCodeFamille());
        selected.getMedicamentAchetePK().setIdMedicament(selected.getMedicament().getMedicamentPK().getIdMedicament());
        selected.getMedicamentAchetePK().setDateCommande(selected.getCommande().getDateCommande());
    }

    protected void initializeEmbeddableKey() {
        selected.setMedicamentAchetePK(new entityBeans.MedicamentAchetePK());
    }

    private MedicamentAcheteFacade getFacade() {
        return ejbFacade;
    }

    public MedicamentAchete prepareCreate() {
        selected = new MedicamentAchete();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentAcheteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentAcheteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MedicamentAcheteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<MedicamentAchete> getItems() {
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

    public MedicamentAchete getMedicamentAchete(entityBeans.MedicamentAchetePK id) {
        return getFacade().find(id);
    }

    public List<MedicamentAchete> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<MedicamentAchete> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = MedicamentAchete.class)
    public static class MedicamentAcheteControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicamentAcheteController controller = (MedicamentAcheteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicamentAcheteController");
            return controller.getMedicamentAchete(getKey(value));
        }

        entityBeans.MedicamentAchetePK getKey(String value) {
            entityBeans.MedicamentAchetePK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entityBeans.MedicamentAchetePK();
            key.setCodeFamille(Integer.parseInt(values[0]));
            key.setIdMedicament(Integer.parseInt(values[1]));
            key.setDateCommande(java.sql.Date.valueOf(values[2]));
            return key;
        }

        String getStringKey(entityBeans.MedicamentAchetePK value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value.getCodeFamille());
            sb.append(SEPARATOR);
            sb.append(value.getIdMedicament());
            sb.append(SEPARATOR);
            sb.append(value.getDateCommande());
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof MedicamentAchete) {
                MedicamentAchete o = (MedicamentAchete) object;
                return getStringKey(o.getMedicamentAchetePK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), MedicamentAchete.class.getName()});
                return null;
            }
        }

    }

}
