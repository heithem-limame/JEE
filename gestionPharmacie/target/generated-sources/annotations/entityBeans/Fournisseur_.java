package entityBeans;

import entityBeans.Commande;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-28T17:30:45")
@StaticMetamodel(Fournisseur.class)
public class Fournisseur_ { 

    public static volatile SingularAttribute<Fournisseur, String> adresse;
    public static volatile SingularAttribute<Fournisseur, String> email;
    public static volatile SingularAttribute<Fournisseur, Integer> tel;
    public static volatile SingularAttribute<Fournisseur, Integer> idFournisseur;
    public static volatile CollectionAttribute<Fournisseur, Commande> commandeCollection;
    public static volatile SingularAttribute<Fournisseur, Integer> nom;

}