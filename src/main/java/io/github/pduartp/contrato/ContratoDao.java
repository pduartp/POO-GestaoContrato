package io.github.pduartp.contrato;

import io.github.pduartp.cliente.Cliente;
import io.github.pduartp.cliente.ClienteDao;
import io.github.pduartp.repository.Dao;
import io.github.pduartp.repository.DbConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick Duarte Pimenta
 */
public abstract class ContratoDao
        extends Dao<Contrato> {

    public static final String TABLE = "Contrato";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(redacao, ultimaAtualizacao)  values (?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE + " set redacao = ?, ultimaAtualizacao = ? where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Contrato e) {
        try
        {
            pstmt.setObject(1, e.getRedacao(), java.sql.Types.VARCHAR);
            pstmt.setObject(2, e.getUltimaAtualizacao(), java.sql.Types.DATE);

            // Just for the update
            if (e.getId() != null)
            {
                pstmt.setLong(3, e.getId());
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(e.getRedacao()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select id, redacao, ultimaAtualizacao"
                + " from Contrato where id = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select id, redacao, ultimaAtualizacao"
                + " from Contrato"
                + " where exlcuido = false";
    }

    @Override
    public String getMoveToTrashStatement() {
        return "update " + TABLE + " set excluido = true"
                + " where id = ?";
    }

    @Override
    public String getRestoreFromTrashStatement() {
        return "update " + TABLE + " set excluido = false"
                + " where id = ?";
    }

    @Override
    public String getFindAllOnTrashStatement() {
        return "select * from " + TABLE + " where excluido = true";
    }

    @Override
    public Contrato extractObject(ResultSet resultSet) {
        Contrato contrato = null;

        try
        {
            contrato = new Contrato();
            contrato.setId(resultSet.getLong("id"));
            contrato.setRedacao(resultSet.getString("redacao"));
            contrato.setUltimaAtualizacao(resultSet.getDate("ultimaAtualizacao").toLocalDate());
        } catch (SQLException ex)
        {
            Logger.getLogger(ContratoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contrato;
    }

//    public List<Contrato> findByRedacaoBiggerThan100000() {
//
//        final String SQL = "select *"
//                + " from " + TABLE
//                + " where redacao > 100000";
//
//        try (PreparedStatement preparedStatement
//                = DbConnection.getConnection().prepareStatement(SQL))
//        {
//
//            // Show the full sentence
//            System.out.println(">> SQL: " + preparedStatement);
//
//            // Performs the query on the database
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            // Returns the respective object
//            return extractObjects(resultSet);
//
//        } catch (Exception ex)
//        {
//            System.out.println("Exception: " + ex);
//        }
//
//        return null;
//    }
}
