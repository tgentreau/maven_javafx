package org.article.bo;

import java.util.List;

public class CartePostale extends Produit{
    private TypeCartePostale type;
    private String typeCP;
    private List<Auteur> lesAuteurs;

    public CartePostale() {
        super();
    }

    public CartePostale(String marque, String libelle, long qteStock, float prixUnitaire, List<Auteur> lesAuteurs, TypeCartePostale type) {
        super(libelle, marque, qteStock, prixUnitaire);
        this.setType(type);
        this.lesAuteurs = lesAuteurs;
    }

    public CartePostale(long refProd, String marque, String libelle, long qteStock, float prixUnitaire, String typeCP) {
        super(refProd, libelle, marque, qteStock, prixUnitaire);
        this.setTypeCP(typeCP);
    }

    public TypeCartePostale getType() {
        return type;
    }

    public void setType(TypeCartePostale type) {
        this.type = type;
    }

    public String getTypeCP() {
        return typeCP;
    }

    public void setTypeCP(String typeCP) {
        this.typeCP = typeCP;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("CartePostale{");
        sb.append(super.toString()).append(", ");
        sb.append("auteurs=");
        for(Auteur auteur : lesAuteurs) {
            sb.append("auteur").append((lesAuteurs.indexOf(auteur))+1).append("=");
            sb.append(auteur.getNom()).append(" ").append(auteur.getPrenom()).append(", ");
        }
        sb.append("type=").append(type);
        sb.append(']');
        return sb.toString();
    }
}
