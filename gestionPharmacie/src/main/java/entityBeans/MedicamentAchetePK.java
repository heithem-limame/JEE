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
public class MedicamentAchetePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeFamille")
    private int codeFamille;
    @Basic(optional = false)
    @NotNull
    @Column(name = "idMedicament")
    private int idMedicament;
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCommande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;

    public MedicamentAchetePK() {
    }

    public MedicamentAchetePK(int codeFamille, int idMedicament, Date dateCommande) {
        this.codeFamille = codeFamille;
        this.idMedicament = idMedicament;
        this.dateCommande = dateCommande;
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

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codeFamille;
        hash += (int) idMedicament;
        hash += (dateCommande != null ? dateCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MedicamentAchetePK)) {
            return false;
        }
        MedicamentAchetePK other = (MedicamentAchetePK) object;
        if (this.codeFamille != other.codeFamille) {
            return false;
        }
        if (this.idMedicament != other.idMedicament) {
            return false;
        }
        if ((this.dateCommande == null && other.dateCommande != null) || (this.dateCommande != null && !this.dateCommande.equals(other.dateCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.MedicamentAchetePK[ codeFamille=" + codeFamille + ", idMedicament=" + idMedicament + ", dateCommande=" + dateCommande + " ]";
    }
    
}
