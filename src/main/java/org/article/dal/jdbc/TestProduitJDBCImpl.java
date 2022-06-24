package org.article.dal.jdbc;
import org.article.bo.Produit;
import org.article.dal.DALException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestProduitJDBCImpl {
    public static void main(String[] args) {
        ProduitJDBCImpl produitJDBC = new ProduitJDBCImpl();
        List<Produit> produitsToAdd = new ArrayList<>();
        List<Produit> produitsToUpdate = new ArrayList<>();

        //Insert
//        try{
//        CartePostale cartePostale = new CartePostale("la belle marque", "le beau libelle", 10, 1,"paysage");
//        Pain pain = new Pain("le pain", "boulangerie", 100, 50, 1);
//        Stylo stylo = new Stylo("le stylo", "qui marche", 500, 1, "Bleu", "papier");
//        Glace glace = new Glace(LocalDate.of(2020, 2, 18), "hagendaz", "la bonne glace", 10, "Vanille", 4, 2);
//           produitsToAdd.add(cartePostale);
//           produitsToAdd.add(pain);
//          produitsToAdd.add(stylo);
//            produitsToAdd.add(glace);
//            for (Produit produit : produitsToAdd) {
//               produitJDBC.insert(produit);
//            }
//        } catch (DALException e) {
//            e.printStackTrace();
//        }
        //Delete
//        try{
//            CartePostale cartePostaleToDelete = new CartePostale(6,"la belle marque", "le beau libelle", 10, 1,"paysage");
//            Pain painToDelete = new Pain(7,"le pain", "boulangerie", 100, 50, 1);
//            Stylo styloToDelete = new Stylo(8, "le stylo", "qui marche", 500, 1, "Bleu", "papier");
//            Glace glaceToDelete = new Glace(9, LocalDate.of(2020, 2, 18),"hagendaz", "la bonne glace", 10, 4, "Vanille", 2);
//            produitJDBC.delete(glaceToDelete);
//        } catch (DALException e) {
//            e.printStackTrace();
//        }
        //Update
//        try{
//            Produit produit1 = produitJDBC.selectById(11);
//            Produit produitToUpdate = null;
//            if(produit1 instanceof Pain) {
//                produit1.setMarque("le beau pain");
//                produit1.setLibelle("la belle boulangerie");
//                produitToUpdate = produit1;
//            } else if(produit1 instanceof Glace) {
//                produit1.setQteStock(50);
//                produitToUpdate = produit1;
//            } else if(produit1 instanceof Stylo) {
//                produit1.setMarque("bic");
//                produitToUpdate = produit1;
//            } else if(produit1 instanceof CartePostale) {
//                produit1 = new CartePostale();
//                produitToUpdate = produit1;
//            }
//            System.out.println(produitToUpdate);
//            if(produitToUpdate != null) {
//                produitJDBC.update(produitToUpdate);
//            } else {
//                System.err.println("Le produit n'existe pas");
//            }
//        } catch (DALException e) {
//            e.printStackTrace();
//        }
        //Select all
        try{
            List<Produit> produits = produitJDBC.selectAll();
            for (Produit produit : produits) {
                System.out.println(produit);
            }
        } catch (DALException e) {
            e.printStackTrace();
        }

//        //Select by id
//        try{
//            Produit produits = produitJDBC.selectById(11);
//            System.out.println(produits);
//        } catch (DALException e) {
//            e.printStackTrace();
//        }

    }
}
