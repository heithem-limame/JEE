/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ManagedBeans;

import entityBeans.FamilleMedicaments;
import entityBeans.Medicament;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.event.SelectEvent;



/**
 *
 * @author tatou
 */
@Named(value = "venteController")
@SessionScoped
public class VenteController implements Serializable {

    private FamilleMedicaments famille;

    private String nomMedicament;

    private String codeMedicament;

    private List<FamilleMedicaments> listeFamilles;

    private List<Medicament> listeMedicaments;

    private List<Medicament> listeVente;

    @EJB
    private sessionBeans.FamilleMedicamentsFacade familleMedicFacade;
    @EJB
    private sessionBeans.MedicamentFacade MedicamentFacade;

    private Medicament medicament;
    
    private Medicament medicamentInfo;

    public VenteController() {
    }

    @PostConstruct
    public void init() {
        listeFamilles = familleMedicFacade.findAll();
        listeVente = new ArrayList<>();
        listeMedicaments = MedicamentFacade.findAll();
    }

    public Medicament getMedicamentInfo() {
        return medicamentInfo;
    }
   

    public void setMedicamentInfo(Medicament medicamentInfo) {
        this.medicamentInfo = medicamentInfo;
    }

    public List<Medicament> getListeVente() {
        return listeVente;
    }

    public void setListeVente(List<Medicament> listeVente) {
        this.listeVente = listeVente;
    }

    /**
     * Get the value of medicament
     *
     * @return the value of medicament
     */
    public Medicament getMedicament() {
        return medicament;
    }

    /**
     * Set the value of medicament
     *
     * @param medicament new value of medicament
     */
    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    /**
     * Get the value of codeMedicament
     *
     * @return the value of codeMedicament
     */
    public String getCodeMedicament() {
        return codeMedicament;
    }

    /**
     * Set the value of codeMedicament
     *
     * @param codeMedicament new value of codeMedicament
     */
    public void setCodeMedicament(String codeMedicament) {
        this.codeMedicament = codeMedicament;
    }

    /**
     * Get the value of nomMedicament
     *
     * @return the value of nomMedicament
     */
    public String getNomMedicament() {
        return nomMedicament;
    }

    /**
     * Set the value of nomMedicament
     *
     * @param nomMedicament new value of nomMedicament
     */
    public void setNomMedicament(String nomMedicament) {
        this.nomMedicament = nomMedicament;
    }
   

    /**
     * Get the value of famille
     *
     * @return the value of famille
     */
    public FamilleMedicaments getFamille() {
        return famille;
    }

    /**
     * Set the value of famille
     *
     * @param famille new value of famille
     */
    public void setFamille(FamilleMedicaments famille) {
        this.famille = famille;
    }

    public List<Medicament> getListeMedicaments() {
        return listeMedicaments;
    }

    public void setListeMedicaments(List<Medicament> listeMedicaments) {
        this.listeMedicaments = listeMedicaments;
    }

//    public List<Medicament> getListMedicaments() {
//        if (famille != null) {
//            List<Medicament> temp = new CopyOnWriteArrayList<>(listeMedicaments);
//            for (Medicament m : temp) {
//                if (!m.getNomMedcament().toLowerCase().contains(texte.toLowerCase())) {
//                    temp.remove(m);
//                }
//            }
//            return temp;
//        } else {
//            listeMedicaments = MedicamentFacade.findAll();
//            List<Medicament> temp = new CopyOnWriteArrayList<>(listeMedicaments);
//            for (Medicament m : temp) {
//                if (!m.getFamilleMedicaments().equals(famille)
//                        && !m.getNomMedcament().toLowerCase().contains(texte.toLowerCase())) {
//                    temp.remove(m);
//                }
//            }
//            return temp;
//        }
//    }
    public List<Medicament> completeMedicamentCode(String texte) {
        if (famille != null) {
            List<Medicament> temp = new CopyOnWriteArrayList<>(listeMedicaments);
            for (Medicament m : temp) {
                if (!String.valueOf(m.getCodeMedicament()).contains(texte)) {
                    temp.remove(m);
                }
            }
            return temp;
        } else {
            listeMedicaments = MedicamentFacade.findAll();
            List<Medicament> temp = new CopyOnWriteArrayList<>(listeMedicaments);
            for (Medicament m : temp) {
                if (!m.getFamilleMedicaments().equals(famille)
                        && !String.valueOf(m.getCodeMedicament()).contains(texte)) {
                    temp.remove(m);
                }
            }
            return temp;
        }
    }
    
    public List<FamilleMedicaments> completeFamille(String texte) {
        List<FamilleMedicaments> temp = new CopyOnWriteArrayList<>(listeFamilles);
        for (FamilleMedicaments fm : temp) {
            if (!fm.getNomFamille().toLowerCase().startsWith(texte.toLowerCase())) {
                temp.remove(fm);
            }
        }
        return temp;
    }

    public void selectFamille(SelectEvent event) {
        listeMedicaments = new ArrayList<>(famille.getMedicamentCollection());
    }

    public void ajouterMedicament() {
        if (!listeVente.contains(medicament)) {
            listeVente.add(medicament);
        } else {
            int c = listeVente.indexOf(medicament);
            Medicament a = listeVente.get(c);
            a.setQuantite(a.getQuantite() + 1);
        }

    }
    
    public void affichInfo(Medicament m){
        medicamentInfo = m;
    }

    public void annulerVente(){
        listeVente.clear();
    }
    
        
    }
    
   

