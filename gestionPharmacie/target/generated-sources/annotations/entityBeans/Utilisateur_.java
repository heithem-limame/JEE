package entityBeans;

import entityBeans.Commande;
import entityBeans.Note;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Utilisateur.class)
public class Utilisateur_ { 

    public static volatile SingularAttribute<Utilisateur, String> prenom;
    public static volatile SingularAttribute<Utilisateur, Integer> role;
    public static volatile SingularAttribute<Utilisateur, Integer> idUtilisateur;
    public static volatile SingularAttribute<Utilisateur, Integer> cin;
    public static volatile SingularAttribute<Utilisateur, String> password;
    public static volatile CollectionAttribute<Utilisateur, Commande> commandeCollection;
    public static volatile SingularAttribute<Utilisateur, String> nom;
    public static volatile CollectionAttribute<Utilisateur, Note> noteCollection;

}