package entityBeans;

import entityBeans.MedicamentVendu;
import entityBeans.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Note.class)
public class Note_ { 

    public static volatile CollectionAttribute<Note, MedicamentVendu> medicamentVenduCollection;
    public static volatile SingularAttribute<Note, Date> dateNote;
    public static volatile SingularAttribute<Note, Float> montantTTC;
    public static volatile SingularAttribute<Note, Utilisateur> idUtilisateur;

}