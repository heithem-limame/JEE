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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author tatou
 */
@Entity
@Table(name = "FamilleMedicaments")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FamilleMedicaments.findAll", query = "SELECT f FROM FamilleMedicaments f"),
    @NamedQuery(name = "FamilleMedicaments.findByCodeFamille", query = "SELECT f FROM FamilleMedicaments f WHERE f.codeFamille = :codeFamille"),
    @NamedQuery(name = "FamilleMedicaments.findByNomFamille", query = "SELECT f FROM FamilleMedicaments f WHERE f.nomFamille = :nomFamille"),
    @NamedQuery(name = "FamilleMedicaments.findByDescription", query = "SELECT f FROM FamilleMedicaments f WHERE f.description = :description")})
public class FamilleMedicaments implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "codeFamille")
    private Integer codeFamille;
    @Size(max = 254)
    @Column(name = "nomFamille")
    private String nomFamille;
    @Size(max = 254)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "familleMedicaments")
    private Collection<Medicament> medicamentCollection;

    public FamilleMedicaments() {
    }

    public FamilleMedicaments(Integer codeFamille) {
        this.codeFamille = codeFamille;
    }

    public Integer getCodeFamille() {
        return codeFamille;
    }

    public void setCodeFamille(Integer codeFamille) {
        this.codeFamille = codeFamille;
    }

    public String getNomFamille() {
        return nomFamille;
    }

    public void setNomFamille(String nomFamille) {
        this.nomFamille = nomFamille;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        hash += (codeFamille != null ? codeFamille.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FamilleMedicaments)) {
            return false;
        }
        FamilleMedicaments other = (FamilleMedicaments) object;
        if ((this.codeFamille == null && other.codeFamille != null) || (this.codeFamille != null && !this.codeFamille.equals(other.codeFamille))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nomFamille;
    }
    
}
