package org.article.bo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ProduitPerissable extends Produit{
    private LocalDate dateLimiteConso;

    public ProduitPerissable() {
    }

    public ProduitPerissable(LocalDate dateLimiteConso) {
        this.dateLimiteConso = dateLimiteConso;
    }

    public ProduitPerissable(long refProd, LocalDate dateLimiteConso, String marque, String libelle,  long qteStock, float prixUnitaire) {
        super(refProd, marque, libelle, qteStock, prixUnitaire);
        this.setDateLimiteConso(dateLimiteConso);
    }

    public ProduitPerissable(LocalDate dateLimiteConso, String marque, String libelle, long qteStock, float prixUnitaire) {
        super(marque, libelle, qteStock, prixUnitaire);
        this.setDateLimiteConso(dateLimiteConso);
    }

    public LocalDate getDateLimiteConso() {
        return dateLimiteConso;
    }

    public void setDateLimiteConso(LocalDate dateLimiteConso) {
        this.dateLimiteConso = dateLimiteConso;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(super.toString());
        sb.append("ProduitPerissable{");
        sb.append(", dateLimiteConso=").append(dateLimiteConso.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        sb.append('}');
        return sb.toString();
    }
}
