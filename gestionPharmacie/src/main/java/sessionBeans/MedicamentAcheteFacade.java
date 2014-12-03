/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessionBeans;

import entityBeans.MedicamentAchete;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author tatou
 */
@Stateless
public class MedicamentAcheteFacade extends AbstractFacade<MedicamentAchete> {
    @PersistenceContext(unitName = "tn.haithem_gestionPharmacie_war_1.0PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MedicamentAcheteFacade() {
        super(MedicamentAchete.class);
    }
    
}
