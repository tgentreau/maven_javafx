package org.article.bo;

import java.util.ArrayList;
import java.util.List;

public class Auteur {
    private long id;
    private String nom;
    private String prenom;
    private List<CartePostale> lesCartes = new ArrayList<>();

    public Auteur(String nom, String prenom) {
        this.setNom(nom);
        this.setPrenom(prenom);

    }
    public Auteur(long id,String nom, String prenom) {
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public List<CartePostale> getLesCartes() {
        return lesCartes;
    }

    public void setLesCartes(List<CartePostale> lesCartes) {
        this.lesCartes = lesCartes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Auteur{");
        sb.append("id=").append(id);
        sb.append(", nom='").append(nom).append('\'');
        sb.append(", prenom='").append(prenom).append('\'');
        sb.append(", lesCartes=").append(lesCartes);
        sb.append('}');
        return sb.toString();
    }
}
