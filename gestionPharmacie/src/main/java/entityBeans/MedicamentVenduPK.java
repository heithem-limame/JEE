/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tatou
 */
@Embeddable
public class MedicamentVenduPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateNote")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNote;
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeFamille")
    private int codeFamille;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedicament")
    private int idMedicament;

    public MedicamentVenduPK() {
    }

    public MedicamentVenduPK(Date dateNote, int codeFamille, int idMedicament) {
        this.dateNote = dateNote;
        this.codeFamille = codeFamille;
        this.idMedicament = idMedicament;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    public int getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(int codeFamille) {
        this.codeFamille = codeFamille;
    }

    public int getIdMedicament() {
        return idMedicament;
    }

    public void setIdMedicament(int idMedicament) {
        this.idMedicament = idMedicament;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateNote != null ? dateNote.hashCode() : 0);
        hash += (int) codeFamille;
        hash += (int) idMedicament;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentVenduPK)) {
            return false;
        }
        MedicamentVenduPK other = (MedicamentVenduPK) object;
        if ((this.dateNote == null && other.dateNote != null) || (this.dateNote != null && !this.dateNote.equals(other.dateNote))) {
            return false;
        }
        if (this.codeFamille != other.codeFamille) {
            return false;
        }
        if (this.idMedicament != other.idMedicament) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.MedicamentVenduPK[ dateNote=" + dateNote + ", codeFamille=" + codeFamille + ", idMedicament=" + idMedicament + " ]";
    }
    
}
