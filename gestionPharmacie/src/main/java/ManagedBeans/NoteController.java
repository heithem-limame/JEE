package ManagedBeans;

import entityBeans.Note;
import ManagedBeans.util.JsfUtil;
import ManagedBeans.util.JsfUtil.PersistAction;
import entityBeans.Medicament;
import sessionBeans.NoteFacade;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

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

@Named("noteController")
@SessionScoped
public class NoteController implements Serializable {
  private List<Medicament> listeNote;
    @EJB
    private sessionBeans.NoteFacade ejbFacade;
    private List<Note> items = null;
    private Note selected;

    public NoteController() {
    }

    public List<Medicament> getListeNote() {
        return listeNote;
    }

    public void setListeNote(List<Medicament> listeNote) {
        this.listeNote = listeNote;
    }
    
   

   

    

    public NoteFacade getEjbFacade() {
        return ejbFacade;
    }

    public void setEjbFacade(NoteFacade ejbFacade) {
        this.ejbFacade = ejbFacade;
    }


    public Note getSelected() {
        return selected;
    }

    public void setSelected(Note selected) {
        this.selected = selected;
    }

    protected void setEmbeddableKeys() {
    }

    protected void initializeEmbeddableKey() {
    }

    private NoteFacade getFacade() {
        return ejbFacade;
    }

    public Note prepareCreate() {
        selected = new Note();
        initializeEmbeddableKey();
        return selected;
    }

    public void create() {
        persist(PersistAction.CREATE, ResourceBundle.getBundle("/Bundle").getString("NoteCreated"));
        if (!JsfUtil.isValidationFailed()) {
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public void update() {
        persist(PersistAction.UPDATE, ResourceBundle.getBundle("/Bundle").getString("NoteUpdated"));
    }

    public void destroy() {
        persist(PersistAction.DELETE, ResourceBundle.getBundle("/Bundle").getString("NoteDeleted"));
        if (!JsfUtil.isValidationFailed()) {
            selected = null; // Remove selection
            items = null;    // Invalidate list of items to trigger re-query.
        }
    }

    public List<Note> getItems() {
        if (items == null) {
            items = getFacade().findAll();
        }
        return items;
    }

    
     public boolean FaireVente ()
     {
     Note lanote = new Note();
     // 
     // lanote.set 
             ejbFacade.create(lanote);
                     return true ; 
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

    public Note getNote(java.util.Date id) {
        return getFacade().find(id);
    }

    public List<Note> getItemsAvailableSelectMany() {
        return getFacade().findAll();
    }

    public List<Note> getItemsAvailableSelectOne() {
        return getFacade().findAll();
    }
      public DateFormat getDate ()
   {
       DateFormat date;
        date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        
        return date  ;//(dateFormat.format(date)); //2014/08/06 15:59:48
   }

    @FacesConverter(forClass = Note.class)
    public static class NoteControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NoteController controller = (NoteController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "noteController");
            return controller.getNote(getKey(value));
        }

        java.util.Date getKey(String value) {
            java.util.Date key;
            key = java.sql.Date.valueOf(value);
            return key;
        }

        String getStringKey(java.util.Date value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Note) {
                Note o = (Note) object;
                return getStringKey(o.getDateNote());
            } else {
                Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, "object {0} is of type {1}; expected type: {2}", new Object[]{object, object.getClass().getName(), Note.class.getName()});
                return null;
            }
        }
        
        
       

    }

}
