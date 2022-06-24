package org.article.bll;

import org.article.bo.Produit;
import org.article.dal.DALException;
import org.article.dal.DAO;
import org.article.dal.DAOFactory;

import java.util.List;

public class ProduitManager {
    private static volatile ProduitManager instance = null;
    private static DAO<Produit> impl;
    private ProduitManager() {
        impl = DAOFactory.getProduitDAO();
    }

    public static final ProduitManager getInstance() {
        if(ProduitManager.instance == null) {
            synchronized (ProduitManager.class) {
                if(ProduitManager.instance == null) {
                    ProduitManager.instance = new ProduitManager();
                }
            }
        }
        return ProduitManager.instance;
    }

    public List<Produit> getProduits() throws BLLExeption {
        List<Produit> produits = null;
        try {
            produits = impl.selectAll();
        } catch (DALException e) {
            throw new BLLExeption("Erreur lors de la récupération des produits",e);
        }
        return produits;
    }

    public void addProduit(Produit produit) throws BLLExeption {
        if(produit.getRefProd() != 0) {
            throw new BLLExeption("Produit déjà existant");
        }
        valider(produit);
        try {
            impl.insert(produit);
        } catch (DALException e) {
            throw new BLLExeption("Erreur lors de l'ajout d'un produit' "+produit,e);
        }
    }

    public void deleteProduit(Produit produit) throws BLLExeption {
        try {
            valider(produit);
            impl.delete(produit);
        } catch (DALException e) {
            throw new BLLExeption("Erreur lors de la suppression d'un produit' "+produit,e);
        }
    }

    public void editProduit(Produit produit) throws BLLExeption {
        valider(produit);
        try {
            impl.update(produit);
        } catch (DALException e) {
            throw new BLLExeption("Erreur lors de la modification d'un produit' "+produit,e);
        }
    }

    private void valider(Produit produit) throws BLLExeption {
        boolean valide = true;
        StringBuilder sb = new StringBuilder();
        if(produit == null) {
            throw new BLLExeption("Produit ne peut pas être null");
        }
        if(produit.getLibelle() == null) {
            throw new BLLExeption("Le libellé d'un produit ne peut pas être null");
        }
        if(produit.getMarque() == null) {
            throw new BLLExeption("La marque d'un produit ne peut pas être null");
        }
    }
}
