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
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tatou
 */
@Entity
@Table(name = "Rayon")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Rayon.findAll", query = "SELECT r FROM Rayon r"),
    @NamedQuery(name = "Rayon.findByCodeRayon", query = "SELECT r FROM Rayon r WHERE r.codeRayon = :codeRayon"),
    @NamedQuery(name = "Rayon.findByNom", query = "SELECT r FROM Rayon r WHERE r.nom = :nom")})
public class Rayon implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeRayon")
    private Integer codeRayon;
    @Column(name = "nom")
    private Integer nom;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codeRayon")
    private Collection<Medicament> medicamentCollection;

    public Rayon() {
    }

    public Rayon(Integer codeRayon) {
        this.codeRayon = codeRayon;
    }

    public Integer getCodeRayon() {
        return codeRayon;
    }

    public void setCodeRayon(Integer codeRayon) {
        this.codeRayon = codeRayon;
    }

    public Integer getNom() {
        return nom;
    }

    public void setNom(Integer nom) {
        this.nom = nom;
    }

    @XmlTransient
    public Collection<Medicament> getMedicamentCollection() {
        return medicamentCollection;
    }

    public void setMedicamentCollection(Collection<Medicament> medicamentCollection) {
        this.medicamentCollection = medicamentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeRayon != null ? codeRayon.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Rayon)) {
            return false;
        }
        Rayon other = (Rayon) object;
        if ((this.codeRayon == null && other.codeRayon != null) || (this.codeRayon != null && !this.codeRayon.equals(other.codeRayon))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entityBeans.Rayon[ codeRayon=" + codeRayon + " ]";
    }
    
}
