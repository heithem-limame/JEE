/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tatou
 */
@Entity
@Table(name = "Medicament")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Medicament.findAll", query = "SELECT m FROM Medicament m"),
    @NamedQuery(name = "Medicament.findByCodeFamille", query = "SELECT m FROM Medicament m WHERE m.medicamentPK.codeFamille = :codeFamille"),
    @NamedQuery(name = "Medicament.findByIdMedicament", query = "SELECT m FROM Medicament m WHERE m.medicamentPK.idMedicament = :idMedicament"),
    @NamedQuery(name = "Medicament.findByCodeMedicament", query = "SELECT m FROM Medicament m WHERE m.codeMedicament = :codeMedicament"),
    @NamedQuery(name = "Medicament.findByNomMedcament", query = "SELECT m FROM Medicament m WHERE m.nomMedcament = :nomMedcament"),
    @NamedQuery(name = "Medicament.findByQtStock", query = "SELECT m FROM Medicament m WHERE m.qtStock = :qtStock"),
    @NamedQuery(name = "Medicament.findByQtMin", query = "SELECT m FROM Medicament m WHERE m.qtMin = :qtMin"),
    @NamedQuery(name = "Medicament.findByPrixHT", query = "SELECT m FROM Medicament m WHERE m.prixHT = :prixHT"),
    @NamedQuery(name = "Medicament.findByTauxTaxe", query = "SELECT m FROM Medicament m WHERE m.tauxTaxe = :tauxTaxe"),
    @NamedQuery(name = "Medicament.findByDescription", query = "SELECT m FROM Medicament m WHERE m.description = :description"),
    @NamedQuery(name = "Medicament.findByAutorisation", query = "SELECT m FROM Medicament m WHERE m.autorisation = :autorisation")})
public class Medicament implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentPK medicamentPK;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeMedicament")
    private int codeMedicament;
    @Size(max = 254)
    @Column(name = "nomMedcament")
    private String nomMedcament;
    @Column(name = "qtStock")
    private Integer qtStock;
    @Column(name = "qtMin")
    private Integer qtMin;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "prixHT")
    private Float prixHT;
    @Column(name = "tauxTaxe")
    private Float tauxTaxe;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @Column(name = "autorisation")
    private Boolean autorisation;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicament")
    private Collection<MedicamentVendu> medicamentVenduCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "medicament")
    private Collection<MedicamentAchete> medicamentAcheteCollection;
    @JoinColumn(name = "codeRayon", referencedColumnName = "codeRayon")
    @ManyToOne(optional = false)
    private Rayon codeRayon;
    @JoinColumn(name = "codeFamille", referencedColumnName = "codeFamille", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private FamilleMedicaments familleMedicaments;

    @Transient
    private Integer quantite = 1;

    public Medicament() {
    }

    public Medicament(MedicamentPK medicamentPK) {
        this.medicamentPK = medicamentPK;
    }

    public Medicament(MedicamentPK medicamentPK, int codeMedicament) {
        this.medicamentPK = medicamentPK;
        this.codeMedicament = codeMedicament;
    }

    public Medicament(int codeFamille, int idMedicament) {
        this.medicamentPK = new MedicamentPK(codeFamille, idMedicament);
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public MedicamentPK getMedicamentPK() {
        return medicamentPK;
    }

    public void setMedicamentPK(MedicamentPK medicamentPK) {
        this.medicamentPK = medicamentPK;
    }

    public int getCodeMedicament() {
        return codeMedicament;
    }

    public void setCodeMedicament(int codeMedicament) {
        this.codeMedicament = codeMedicament;
    }

    public String getNomMedcament() {
        return nomMedcament;
    }

    public void setNomMedcament(String nomMedcament) {
        this.nomMedcament = nomMedcament;
    }

    public Integer getQtStock() {
        return qtStock;
    }

    public void setQtStock(Integer qtStock) {
        this.qtStock = qtStock;
    }

    public Integer getQtMin() {
        return qtMin;
    }

    public void setQtMin(Integer qtMin) {
        this.qtMin = qtMin;
    }

    public Float getPrixHT() {
        return prixHT;
    }

    public void setPrixHT(Float prixHT) {
        this.prixHT = prixHT;
    }

    public Float getTauxTaxe() {
        return tauxTaxe;
    }

    public void setTauxTaxe(Float tauxTaxe) {
        this.tauxTaxe = tauxTaxe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getAutorisation() {
        return autorisation;
    }

    public void setAutorisation(Boolean autorisation) {
        this.autorisation = autorisation;
    }

    @XmlTransient
    public Collection<MedicamentVendu> getMedicamentVenduCollection() {
        return medicamentVenduCollection;
    }

    public void setMedicamentVenduCollection(Collection<MedicamentVendu> medicamentVenduCollection) {
        this.medicamentVenduCollection = medicamentVenduCollection;
    }

    @XmlTransient
    public Collection<MedicamentAchete> getMedicamentAcheteCollection() {
        return medicamentAcheteCollection;
    }

    public void setMedicamentAcheteCollection(Collection<MedicamentAchete> medicamentAcheteCollection) {
        this.medicamentAcheteCollection = medicamentAcheteCollection;
    }

    public Rayon getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(Rayon codeRayon) {
        this.codeRayon = codeRayon;
    }

    public FamilleMedicaments getFamilleMedicaments() {
        return familleMedicaments;
    }

    public void setFamilleMedicaments(FamilleMedicaments familleMedicaments) {
        this.familleMedicaments = familleMedicaments;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentPK != null ? medicamentPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Medicament)) {
            return false;
        }
        Medicament other = (Medicament) object;
        if ((this.medicamentPK == null && other.medicamentPK != null) || (this.medicamentPK != null && !this.medicamentPK.equals(other.medicamentPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNomMedcament();
    }

}
