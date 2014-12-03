/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author tatou
 */
@Embeddable
public class MedicamentPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeFamille")
    private int codeFamille;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedicament")
    private int idMedicament;

    public MedicamentPK() {
    }

    public MedicamentPK(int codeFamille, int idMedicament) {
        this.codeFamille = codeFamille;
        this.idMedicament = idMedicament;
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
        hash += (int) codeFamille;
        hash += (int) idMedicament;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentPK)) {
            return false;
        }
        MedicamentPK other = (MedicamentPK) object;
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
        return "entityBeans.MedicamentPK[ codeFamille=" + codeFamille + ", idMedicament=" + idMedicament + " ]";
    }
    
}
