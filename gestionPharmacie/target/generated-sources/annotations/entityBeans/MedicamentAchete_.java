package entityBeans;

import entityBeans.Commande;
import entityBeans.Medicament;
import entityBeans.MedicamentAchetePK;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(MedicamentAchete.class)
public class MedicamentAchete_ { 

    public static volatile SingularAttribute<MedicamentAchete, MedicamentAchetePK> medicamentAchetePK;
    public static volatile SingularAttribute<MedicamentAchete, Medicament> medicament;
    public static volatile SingularAttribute<MedicamentAchete, Integer> quantite;
    public static volatile SingularAttribute<MedicamentAchete, Commande> commande;

}