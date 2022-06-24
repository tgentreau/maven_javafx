package org.article.bo;

import java.text.DecimalFormat;

public class Produit {
    protected long refProd;
    protected String libelle;
    protected String marque;
    protected float prixUnitaire;
    protected long qteStock;

    public Produit() {
        super();
    }

    public Produit(long refProd, String marque, String libelle, long qteStock, float prixUnitaire) {
        this.setRefProd(refProd);
        this.setLibelle(libelle);
        this.setMarque(marque);
        this.setPrixUnitaire(prixUnitaire);
        this.setQteStock(qteStock);
    }

    public Produit(String marque, String libelle, long qteStock, float prixUnitaire) {
        this.setLibelle(libelle);
        this.setMarque(marque);
        this.setPrixUnitaire(prixUnitaire);
        this.setQteStock(qteStock);
    }

    public long getRefProd() {
        return refProd;
    }

    public void setRefProd(long refProd) {
        this.refProd = refProd;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public long getQteStock() {
        return qteStock;
    }

    public void setQteStock(long qteStock) {
        this.qteStock = qteStock;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat("#0.00");
        sb.append(this.getClass().getSimpleName()).append(" [");
        sb.append("libelle=").append(libelle);
        if (refProd != 0) {
            sb.append(", refProd=").append(refProd).append(", ");
        }
        sb.append(", marque=").append(marque);
        sb.append(", prixUnitaire=").append(df.format(prixUnitaire)).append(" euro").append((prixUnitaire > 1) ? "s" : "");
        sb.append(", qteStock=").append(qteStock).append(" ");
        return sb.toString();
    }
}
