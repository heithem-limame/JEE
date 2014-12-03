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
@Table(name = "MedicamentVendu")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MedicamentVendu.findAll", query = "SELECT m FROM MedicamentVendu m"),
    @NamedQuery(name = "MedicamentVendu.findByDateNote", query = "SELECT m FROM MedicamentVendu m WHERE m.medicamentVenduPK.dateNote = :dateNote"),
    @NamedQuery(name = "MedicamentVendu.findByCodeFamille", query = "SELECT m FROM MedicamentVendu m WHERE m.medicamentVenduPK.codeFamille = :codeFamille"),
    @NamedQuery(name = "MedicamentVendu.findByIdMedicament", query = "SELECT m FROM MedicamentVendu m WHERE m.medicamentVenduPK.idMedicament = :idMedicament"),
    @NamedQuery(name = "MedicamentVendu.findByQuantite", query = "SELECT m FROM MedicamentVendu m WHERE m.quantite = :quantite")})
public class MedicamentVendu implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MedicamentVenduPK medicamentVenduPK;
    @Column(name = "quantite")
    private Integer quantite;
    @JoinColumns({
        @JoinColumn(name = "codeFamille", referencedColumnName = "codeFamille", insertable = false, updatable = false),
        @JoinColumn(name = "idMedicament", referencedColumnName = "idMedicament", insertable = false, updatable = false)})
    @ManyToOne(optional = false)
    private Medicament medicament;
    @JoinColumn(name = "dateNote", referencedColumnName = "dateNote", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Note note;

    public MedicamentVendu() {
    }

    public MedicamentVendu(MedicamentVenduPK medicamentVenduPK) {
        this.medicamentVenduPK = medicamentVenduPK;
    }

    public MedicamentVendu(Date dateNote, int codeFamille, int idMedicament) {
        this.medicamentVenduPK = new MedicamentVenduPK(dateNote, codeFamille, idMedicament);
    }

    public MedicamentVenduPK getMedicamentVenduPK() {
        return medicamentVenduPK;
    }

    public void setMedicamentVenduPK(MedicamentVenduPK medicamentVenduPK) {
        this.medicamentVenduPK = medicamentVenduPK;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Medicament getMedicament() {
        return medicament;
    }

    public void setMedicament(Medicament medicament) {
        this.medicament = medicament;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (medicamentVenduPK != null ? medicamentVenduPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentVendu)) {
            return false;
        }
        MedicamentVendu other = (MedicamentVendu) object;
        if ((this.medicamentVenduPK == null && other.medicamentVenduPK != null) || (this.medicamentVenduPK != null && !this.medicamentVenduPK.equals(other.medicamentVenduPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.MedicamentVendu[ medicamentVenduPK=" + medicamentVenduPK + " ]";
    }
    
}
