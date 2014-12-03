/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.Medicament;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tatou
 */
@Stateless
public class MedicamentFacade extends AbstractFacade<Medicament> {
    @PersistenceContext(unitName = "tn.haithem_gestionPharmacie_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentFacade() {
        super(Medicament.class);
    }
    
}
