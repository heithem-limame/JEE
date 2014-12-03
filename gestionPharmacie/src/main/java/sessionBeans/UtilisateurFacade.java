/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author tatou
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {

    @PersistenceContext(unitName = "tn.haithem_gestionPharmacie_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }

    public Utilisateur findByCin(Integer cin) {
        Query q = getEntityManager().createNamedQuery("Utilisateur.findByCin", Utilisateur.class);
        q.setParameter("cin", cin);
        if (q.getResultList().isEmpty()) {
            return null;
        } else {
            return (Utilisateur) q.getResultList().get(0);
        }
    }
}
