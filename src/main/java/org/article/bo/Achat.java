package org.article.bo;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Achat {
    private double montant;
    private List<Ligne> lignes = new ArrayList<>();

    public Achat(Ligne ligne) {
        this.lignes.add(ligne);
    }

    public Double getMontant() {
        return montant;
    }

    public void setMontant(Double montant) {
        this.montant = montant;
    }

    public List<Ligne> getLignes() {
        return lignes;
    }

    public void setLignes(List<Ligne> lignes) {
        this.lignes = lignes;
    }

    public Ligne getLigne(int index) {
        return lignes.get(index);
    }

    public void ajouteLigne(Produit produit, int qte) {
        Ligne ligne = new Ligne(produit, qte);
        lignes.add(ligne);
    }

    public void modifieLigne(int index, int nouvelleQte) {
        Ligne ligne = lignes.get(index);
        ligne.setQuantite(nouvelleQte);
    }

    public void supprimeLigne(int index) {
        lignes.remove(index);
    }

    public double calculMontant() {
        montant = 0;
        for (Ligne ligne : lignes) {
            montant += ligne.getQuantite() * ligne.getProduit().getPrixUnitaire();
        }
        return montant;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Achat{");
        DecimalFormat df = new DecimalFormat("#.00");
        sb.append("\n");
        for(Ligne ligne : lignes) {
            sb.append("\n");
            sb.append("ligne ").append((lignes.indexOf(ligne)) + 1).append(" : ");
            sb.append(ligne.toString());
        }
        sb.append("\n");
        sb.append("\n");
        sb.append("Total de l'achat : ").append(df.format(calculMontant())).append(" euro").append((montant > 1) ? "s" : "");
        return sb.toString();
    }
}
