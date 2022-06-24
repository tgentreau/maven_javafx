package org.article.dal.jdbc;

import org.article.bo.*;
import org.article.dal.DALException;
import org.article.dal.DAO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProduitJDBCImpl implements DAO<Produit> {
    private static final String SQL_INSERT = "insert into produit (libelle, marque, prixUnitaire, qteStock, typeProduit, dateLimiteConso, poids, couleur, typeMine, parfum, temperatureConservation, typeCartePostale) values (?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE = "update produit set libelle=?, marque=?, prixUnitaire=?, qteStock=?, typeProduit=?, dateLimiteConso=?, poids=?, couleur=?, typeMine=?, parfum=?, temperatureConservation=?, typeCartePostale=? where idProduit=?";
    private static final String SQL_DELETE = "delete from produit where idProduit=?";
    private static final String SQL_SELECT_ALL = "select * from produit";
    private static final String SQL_SELECT_BY_ID = "select * from produit where idProduit=?";

    @Override
    public Produit selectById(long id) throws DALException {
        ResultSet resultSet = null;
        Produit produit = null;
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_SELECT_BY_ID)){
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if(resultSet.getString(6).equals("pain")) {
                    produit = new Pain(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getInt(8), resultSet.getFloat(4), resultSet.getLong(5));
                } else if(resultSet.getString(6).equals("glace")) {
                    produit = new Glace(resultSet.getLong(1), resultSet.getDate(7).toLocalDate(), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(8), resultSet.getFloat(4), resultSet.getString(11), resultSet.getInt(12));
                } else if(resultSet.getString(6).equals("stylo")) {
                    produit = new Stylo(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(5), resultSet.getFloat(4), resultSet.getString(8), resultSet.getString(9));
                } else if(resultSet.getString(6).equals("carte postale")) {
                    produit = new CartePostale(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(8), resultSet.getFloat(4), resultSet.getString(12));
                }
            }
        } catch (SQLException e) {
            throw new DALException("erreur du select by id - id = " + id + e);
        }
        return produit;
    }

    @Override
    public List<Produit> selectAll() throws DALException {
        ResultSet resultSet = null;
        List<Produit> produits = new ArrayList<>();
        try (Connection connection = JDBCTools.getConnection();
             Statement statement = connection.createStatement()){
            resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                if(resultSet.getString(6).equals("pain")) {
                    Pain pain = new Pain(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getInt(8), resultSet.getFloat(4), resultSet.getLong(5));
                    produits.add(pain);
                } else if(resultSet.getString(6).equals("glace")) {
                    Glace glace = new Glace(resultSet.getLong(1), resultSet.getDate(7).toLocalDate(), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(8), resultSet.getFloat(4), resultSet.getString(11), resultSet.getInt(12));
                    produits.add(glace);
                } else if(resultSet.getString(6).equals("stylo")) {
                    Stylo stylo = new Stylo(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(5), resultSet.getFloat(4), resultSet.getString(8), resultSet.getString(9));
                    produits.add(stylo);
                }
//                else if(resultSet.getString(6).equals("carte postale")) {
//                    CartePostale cartePostale = new CartePostale(resultSet.getLong(1), resultSet.getString(3), resultSet.getString(2), resultSet.getLong(8), resultSet.getFloat(4), resultSet.getString(12));
//                    produits.add(cartePostale);
//                }
            }
        }  catch (SQLException e) {
            throw new DALException("erreur du selectAll - data="+ produits + " " + e);
        }
        return produits;
    }

    @Override
    public void update(Produit data) throws DALException {
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE)){
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setFloat(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            if(data instanceof Pain) {
                preparedStatement.setString(5, "pain");
                preparedStatement.setDate(6, Date.valueOf(((Pain) data).getDateLimiteConso()));
                preparedStatement.setInt(7, ((Pain) data).getPoids());
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setNull(12, Types.NULL);
            } else if(data instanceof Glace) {
                preparedStatement.setString(5, "glace");
                preparedStatement.setDate(6, Date.valueOf(((Glace) data).getDateLimiteConso()));
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setString(10, ((Glace) data).getParfum());
                preparedStatement.setInt(11, ((Glace) data).getTemperatureConservation());
                preparedStatement.setNull(12, Types.NULL);
                preparedStatement.setLong(13, data.getRefProd());
            } else if(data instanceof Stylo) {
                preparedStatement.setString(5, "stylo");
                preparedStatement.setNull(6, Types.NULL);
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setString(8, ((Stylo) data).getCouleur());
                preparedStatement.setString(9, ((Stylo) data).getTypeMine());
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setNull(12, Types.NULL);
                preparedStatement.setLong(13, data.getRefProd());
            } else if(data instanceof CartePostale) {
                preparedStatement.setString(5, "carte postale");
                preparedStatement.setNull(6, Types.NULL);
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setString(12, ((CartePostale) data).getTypeCP());
            }
            preparedStatement.setLong(13, data.getRefProd());
            int nbrRows = preparedStatement.executeUpdate();
            System.out.println(data);
            if(nbrRows != 0) {
                System.out.println("update effectué: " + nbrRows);
            }
        }  catch (SQLException e) {
           throw new DALException("erreur du update - data="+data+ " " + e);
       }
    }

    @Override
    public void insert(Produit data) throws DALException {
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)){
            preparedStatement.setString(1, data.getLibelle());
            preparedStatement.setString(2, data.getMarque());
            preparedStatement.setFloat(3, data.getPrixUnitaire());
            preparedStatement.setLong(4, data.getQteStock());
            if(data instanceof Pain) {
                preparedStatement.setString(5, "pain");
                preparedStatement.setDate(6, Date.valueOf(((Pain) data).getDateLimiteConso()));
                preparedStatement.setInt(7, ((Pain) data).getPoids());
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setNull(12, Types.NULL);
            } else if(data instanceof Glace) {
                preparedStatement.setString(5, "glace");
                preparedStatement.setDate(6, Date.valueOf(((Glace) data).getDateLimiteConso()));
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setString(10, ((Glace) data).getParfum());
                preparedStatement.setInt(11, ((Glace) data).getTemperatureConservation());
                preparedStatement.setNull(12, Types.NULL);
            } else if(data instanceof Stylo) {
                preparedStatement.setString(5, "stylo");
                preparedStatement.setNull(6, Types.NULL);
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setString(8, ((Stylo) data).getCouleur());
                preparedStatement.setString(9, ((Stylo) data).getTypeMine());
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setNull(12, Types.NULL);
            } else if(data instanceof CartePostale) {
                preparedStatement.setString(5, "carte postale");
                preparedStatement.setNull(6, Types.NULL);
                preparedStatement.setNull(7, Types.NULL);
                preparedStatement.setNull(8, Types.NULL);
                preparedStatement.setNull(9, Types.NULL);
                preparedStatement.setNull(10, Types.NULL);
                preparedStatement.setNull(11, Types.NULL);
                preparedStatement.setString(12, ((CartePostale) data).getTypeCP());
            }
            int nbrRows = preparedStatement.executeUpdate();
            if(nbrRows == 1) {
                ResultSet rs = preparedStatement.getGeneratedKeys();
                if(rs.next()) {
                    data.setRefProd(rs.getLong(1));
                    System.out.println("insert effectué: " + nbrRows);
                }
            }
        }catch (SQLException e) {
            throw new DALException("Erreur lors de l'insertion d'un produit", e);
        }
    }

    @Override
    public void delete(Produit data) throws DALException {
        try (Connection connection = JDBCTools.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE)){
            if(data instanceof CartePostale) {
                preparedStatement.setLong(1, data.getRefProd());
            } else if(data instanceof Pain) {
                preparedStatement.setLong(1, data.getRefProd());
            } else if(data instanceof Stylo) {
                preparedStatement.setLong(1, data.getRefProd());
            } else if(data instanceof Glace) {
                preparedStatement.setLong(1, data.getRefProd());
            }
            int nbrRows = preparedStatement.executeUpdate();
            if(nbrRows != 0) {
                System.out.println("delete effectué: " + nbrRows);
            }
        } catch (SQLException e) {
            throw new DALException("erreur du delete - data="+data+ " " + e);
        }
    }
}
