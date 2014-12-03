package entityBeans;

import entityBeans.Medicament;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(FamilleMedicaments.class)
public class FamilleMedicaments_ { 

    public static volatile SingularAttribute<FamilleMedicaments, String> description;
    public static volatile SingularAttribute<FamilleMedicaments, Integer> codeFamille;
    public static volatile SingularAttribute<FamilleMedicaments, String> nomFamille;
    public static volatile CollectionAttribute<FamilleMedicaments, Medicament> medicamentCollection;

}