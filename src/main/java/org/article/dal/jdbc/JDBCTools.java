package org.article.dal.jdbc;

import org.article.dal.DALException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCTools {

    public static Connection getConnection() throws DALException {
        StringBuilder sb = new StringBuilder();
        sb.append("jdbc:mariadb://");
        sb.append(Settings.getProperty("server")).append(":").append(Settings.getProperty("port"));
        sb.append("/").append(Settings.getProperty("db")).append("?");
        sb.append("user=").append(Settings.getProperty("username")).append("&");
        sb.append("password=").append(Settings.getProperty("password"));
        System.out.println(sb);
        try {
            return DriverManager.getConnection(sb.toString());
        } catch (SQLException e) {
            throw new DALException("erreur de connexion à la bdd ",e);
        }
    }
}
