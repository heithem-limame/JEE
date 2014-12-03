package entityBeans;

import entityBeans.Medicament;
import entityBeans.MedicamentVenduPK;
import entityBeans.Note;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(MedicamentVendu.class)
public class MedicamentVendu_ { 

    public static volatile SingularAttribute<MedicamentVendu, Medicament> medicament;
    public static volatile SingularAttribute<MedicamentVendu, Integer> quantite;
    public static volatile SingularAttribute<MedicamentVendu, MedicamentVenduPK> medicamentVenduPK;
    public static volatile SingularAttribute<MedicamentVendu, Note> note;

}