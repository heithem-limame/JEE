package entityBeans;

import entityBeans.Medicament;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Rayon.class)
public class Rayon_ { 

    public static volatile SingularAttribute<Rayon, Integer> codeRayon;
    public static volatile CollectionAttribute<Rayon, Medicament> medicamentCollection;
    public static volatile SingularAttribute<Rayon, Integer> nom;

}