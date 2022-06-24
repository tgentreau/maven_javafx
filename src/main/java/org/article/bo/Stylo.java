package org.article.bo;

public class Stylo extends Produit{
    private String couleur;
    private String typeMine;

    public Stylo(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, String couleur, String typeMine) {
        super(refProd, libelle, marque, qteStock, prixUnitaire);
        this.setCouleur(couleur);
        this.setTypeMine(typeMine);
    }

    public Stylo(String marque, String libelle, long qteStock, float prixUnitaire, String couleur, String typeMine) {
        super(marque, libelle, qteStock, prixUnitaire);
        this.setCouleur(couleur);
        this.setTypeMine(typeMine);
    }

    public String getCouleur() {
        return couleur;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public String getTypeMine() {
        return typeMine;
    }

    public void setTypeMine(String typeMine) {
        this.typeMine = typeMine;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("Stylo {").append('\'');
        sb.append("couleur='").append(couleur).append('\'');
        sb.append(", typeMine='").append(typeMine).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
