/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entityBeans.Utilisateur;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author tatou
 */
@Named(value = "loginController")
@SessionScoped
public class LoginController implements Serializable {

    @EJB
    private sessionBeans.UtilisateurFacade ejbFacade;

    private Utilisateur currentUser;
    
    
    /**
     * Creates a new instance of LoginController
     */
    public LoginController() {
    }

    private Integer login;
    private String password;

    public Integer getLogin() {
        return login;
    }

    public void setLogin(Integer login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Utilisateur getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Utilisateur currentUser) {
        this.currentUser = currentUser;
    }

    
    public String loginAction() {
        // test if the user login exists
        Utilisateur u = ejbFacade.findByCin(login);
        if (u != null) {
            // test if the password is correct 
            if (u.getPassword().equals(password)) {
                // redirect user to his space
                if (u.getRole() == 0) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Login avec succes !"));
                    setCurrentUser(u);
                    return "/WEB-INF/user";
                } else {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Login avec succes !"));
                    setCurrentUser(u);
                    return "/WEB-INF/admin";
                }
            }

        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erreur", "Vérifier vos données personnelles."));

        return "";
    }
    
    
    
    public String deconnect(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        currentUser = null;
        return "/index?faces-redirect=true";
    }

}
