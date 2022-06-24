package org.article.bo;

import java.util.ArrayList;
import java.util.List;

public class Ligne {
    private int quantite;
    private Produit produit;


    public Ligne(Produit produit, int quantite) {
        this.setProduit(produit);
        this.setQuantite(quantite);
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public Produit getProduit() {
        return produit;
    }

    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public double getPrix() {
        return getQuantite() * produit.getPrixUnitaire();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Ligne{");
        sb.append("quantite=").append(quantite);
        sb.append(", produit=").append(produit);
        sb.append('}');
        return sb.toString();
    }
}
