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
@Table(name = "Note")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Note.findAll", query = "SELECT n FROM Note n"),
    @NamedQuery(name = "Note.findByDateNote", query = "SELECT n FROM Note n WHERE n.dateNote = :dateNote"),
    @NamedQuery(name = "Note.findByMontantTTC", query = "SELECT n FROM Note n WHERE n.montantTTC = :montantTTC")})
public class Note implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "dateNote")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateNote;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montantTTC")
    private Float montantTTC;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "note")
    private Collection<MedicamentVendu> medicamentVenduCollection;
    @JoinColumn(name = "idUtilisateur", referencedColumnName = "idUtilisateur")
    @ManyToOne(optional = false)
    private Utilisateur idUtilisateur;

    public Note() {
    }

    public Note(Date dateNote) {
        this.dateNote = dateNote;
    }

    public Date getDateNote() {
        return dateNote;
    }

    public void setDateNote(Date dateNote) {
        this.dateNote = dateNote;
    }

    public Float getMontantTTC() {
        return montantTTC;
    }

    public void setMontantTTC(Float montantTTC) {
        this.montantTTC = montantTTC;
    }

    @XmlTransient
    public Collection<MedicamentVendu> getMedicamentVenduCollection() {
        return medicamentVenduCollection;
    }

    public void setMedicamentVenduCollection(Collection<MedicamentVendu> medicamentVenduCollection) {
        this.medicamentVenduCollection = medicamentVenduCollection;
    }

    public Utilisateur getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(Utilisateur idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dateNote != null ? dateNote.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Note)) {
            return false;
        }
        Note other = (Note) object;
        if ((this.dateNote == null && other.dateNote != null) || (this.dateNote != null && !this.dateNote.equals(other.dateNote))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Note[ dateNote=" + dateNote + " ]";
    }
    
}
