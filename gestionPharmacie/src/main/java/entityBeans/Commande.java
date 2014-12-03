/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entityBeans;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tatou
 */
@Entity
@Table(name = "Commande")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Commande.findAll", query = "SELECT c FROM Commande c"),
    @NamedQuery(name = "Commande.findByDateCommande", query = "SELECT c FROM Commande c WHERE c.dateCommande = :dateCommande"),
    @NamedQuery(name = "Commande.findByMontantTTC", query = "SELECT c FROM Commande c WHERE c.montantTTC = :montantTTC")})
public class Commande implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateCommande")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCommande;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantTTC")
    private Float montantTTC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "commande")
    private Collection<MedicamentAchete> medicamentAcheteCollection;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;
    @JoinColumn(name = "idFournisseur", referencedColumnName = "idFournisseur")
    @ManyToOne(optional = false)
    private Fournisseur idFournisseur;

    public Commande() {
    }

    public Commande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Date getDateCommande() {
        return dateCommande;
    }

    public void setDateCommande(Date dateCommande) {
        this.dateCommande = dateCommande;
    }

    public Float getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(Float montantTTC) {
        this.montantTTC = montantTTC;
    }

    @XmlTransient
    public Collection<MedicamentAchete> getMedicamentAcheteCollection() {
        return medicamentAcheteCollection;
    }

    public void setMedicamentAcheteCollection(Collection<MedicamentAchete> medicamentAcheteCollection) {
        this.medicamentAcheteCollection = medicamentAcheteCollection;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateCommande != null ? dateCommande.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Commande)) {
            return false;
        }
        Commande other = (Commande) object;
        if ((this.dateCommande == null && other.dateCommande != null) || (this.dateCommande != null && !this.dateCommande.equals(other.dateCommande))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Commande[ dateCommande=" + dateCommande + " ]";
    }
    
}
