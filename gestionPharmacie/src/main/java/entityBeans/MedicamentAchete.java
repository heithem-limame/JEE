/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tatou
 */
@Entity
@Table(name = "MedicamentAchete")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentAchete.findAll", query = "SELECT m FROM MedicamentAchete m"),
    @NamedQuery(name = "MedicamentAchete.findByCodeFamille", query = "SELECT m FROM MedicamentAchete m WHERE m.medicamentAchetePK.codeFamille = :codeFamille"),
    @NamedQuery(name = "MedicamentAchete.findByIdMedicament", query = "SELECT m FROM MedicamentAchete m WHERE m.medicamentAchetePK.idMedicament = :idMedicament"),
    @NamedQuery(name = "MedicamentAchete.findByDateCommande", query = "SELECT m FROM MedicamentAchete m WHERE m.medicamentAchetePK.dateCommande = :dateCommande"),
    @NamedQuery(name = "MedicamentAchete.findByQuantite", query = "SELECT m FROM MedicamentAchete m WHERE m.quantite = :quantite")})
public class MedicamentAchete implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentAchetePK medicamentAchetePK;
    @Column(name = "quantite")
    private Integer quantite;
    @JoinColumn(name = "dateCommande", referencedColumnName = "dateCommande", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Commande commande;
    @JoinColumns({
        @JoinColumn(name = "codeFamille", referencedColumnName = "codeFamille", insertable = false, updatable = false),
        @JoinColumn(name = "idMedicament", referencedColumnName = "idMedicament", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medicament medicament;

    public MedicamentAchete() {
    }

    public MedicamentAchete(MedicamentAchetePK medicamentAchetePK) {
        this.medicamentAchetePK = medicamentAchetePK;
    }

    public MedicamentAchete(int codeFamille, int idMedicament, Date dateCommande) {
        this.medicamentAchetePK = new MedicamentAchetePK(codeFamille, idMedicament, dateCommande);
    }

    public MedicamentAchetePK getMedicamentAchetePK() {
        return medicamentAchetePK;
    }

    public void setMedicamentAchetePK(MedicamentAchetePK medicamentAchetePK) {
        this.medicamentAchetePK = medicamentAchetePK;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Commande getCommande() {
        return commande;
    }

    public void setCommande(Commande commande) {
        this.commande = commande;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentAchetePK != null ? medicamentAchetePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentAchete)) {
            return false;
        }
        MedicamentAchete other = (MedicamentAchete) object;
        if ((this.medicamentAchetePK == null && other.medicamentAchetePK != null) || (this.medicamentAchetePK != null && !this.medicamentAchetePK.equals(other.medicamentAchetePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.MedicamentAchete[ medicamentAchetePK=" + medicamentAchetePK + " ]";
    }
    
}
