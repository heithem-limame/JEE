package entityBeans;

import entityBeans.FamilleMedicaments;
import entityBeans.MedicamentAchete;
import entityBeans.MedicamentPK;
import entityBeans.MedicamentVendu;
import entityBeans.Rayon;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Medicament.class)
public class Medicament_ { 

    public static volatile SingularAttribute<Medicament, Float> prixHT;
    public static volatile CollectionAttribute<Medicament, MedicamentVendu> medicamentVenduCollection;
    public static volatile SingularAttribute<Medicament, Integer> qtStock;
    public static volatile SingularAttribute<Medicament, String> nomMedcament;
    public static volatile SingularAttribute<Medicament, String> description;
    public static volatile CollectionAttribute<Medicament, MedicamentAchete> medicamentAcheteCollection;
    public static volatile SingularAttribute<Medicament, Boolean> autorisation;
    public static volatile SingularAttribute<Medicament, Integer> qtMin;
    public static volatile SingularAttribute<Medicament, Integer> codeMedicament;
    public static volatile SingularAttribute<Medicament, MedicamentPK> medicamentPK;
    public static volatile SingularAttribute<Medicament, Float> tauxTaxe;
    public static volatile SingularAttribute<Medicament, Rayon> codeRayon;
    public static volatile SingularAttribute<Medicament, FamilleMedicaments> familleMedicaments;

}