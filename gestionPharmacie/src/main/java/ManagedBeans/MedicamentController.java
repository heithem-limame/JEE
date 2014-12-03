package ManagedBeans;

import entityBeans.Medicament;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import sessionBeans.MedicamentFacade;

import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@Named("medicamentController")
@SessionScoped
public class MedicamentController implements Serializable {

    @EJB
    private sessionBeans.MedicamentFacade ejbFacade;
    private List<Medicament> items = null;
    private Medicament selected;
   

    public Medicament getSelected() {
        return selected;
    }

    public void setSelected(Medicament selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
        selected.getMedicamentPK().setCodeFamille(selected.getFamilleMedicaments().getCodeFamille());
    }

    protected void initializeEmbeddableKey() {
        selected.setMedicamentPK(new entityBeans.MedicamentPK());
    }

    private MedicamentFacade getFacade() {
        return ejbFacade;
    }

    public Medicament prepareCreate() {
        selected = new Medicament();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("MedicamentUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("MedicamentDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Medicament> getItems() {
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

    public Medicament getMedicament(entityBeans.MedicamentPK id) {
        return getFacade().find(id);
    }

    public List<Medicament> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Medicament> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }

    @FacesConverter(forClass = Medicament.class)
    public static class MedicamentControllerConverter implements Converter {

        private static final String SEPARATOR = "#";
        private static final String SEPARATOR_ESCAPED = "\\#";

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            MedicamentController controller = (MedicamentController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "medicamentController");
            return controller.getMedicament(getKey(value));
        }

        entityBeans.MedicamentPK getKey(String value) {
            entityBeans.MedicamentPK key;
            String values[] = value.split(SEPARATOR_ESCAPED);
            key = new entityBeans.MedicamentPK();
            key.setCodeFamille(Integer.parseInt(values[0]));
            key.setIdMedicament(Integer.parseInt(values[1]));
            return key;
        }

        String getStringKey(entityBeans.MedicamentPK value) {
            StringBuilder sb = new StringBuilder();
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
            if (object instanceof Medicament) {
                Medicament o = (Medicament) object;
                return getStringKey(o.getMedicamentPK());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Medicament.class.getName()});
                return null;
            }
        }

    }

}
