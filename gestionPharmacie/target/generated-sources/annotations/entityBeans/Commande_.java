package entityBeans;

import entityBeans.Fournisseur;
import entityBeans.MedicamentAchete;
import entityBeans.Utilisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Commande.class)
public class Commande_ { 

    public static volatile SingularAttribute<Commande, Float> montantTTC;
    public static volatile CollectionAttribute<Commande, MedicamentAchete> medicamentAcheteCollection;
    public static volatile SingularAttribute<Commande, Utilisateur> idUtilisateur;
    public static volatile SingularAttribute<Commande, Fournisseur> idFournisseur;
    public static volatile SingularAttribute<Commande, Date> dateCommande;

}