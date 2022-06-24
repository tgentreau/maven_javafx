package org.article.dal;

import org.article.bo.Produit;
import org.article.dal.jdbc.ProduitJDBCImpl;

public class DAOFactory {
    public static DAO<Produit> getProduitDAO() {
        return new ProduitJDBCImpl();
    }
}
