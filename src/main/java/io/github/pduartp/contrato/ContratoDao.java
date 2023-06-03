package io.github.pduartp.contrato;

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

/*
CREATE TABLE Contrato (
    redacao VARCHAR(100000) NOT NULL,
    ultimaAtualizacao DATE,
    id BIGINT,
    FOREIGN KEY (id) REFERENCES Cliente(id)
);
 */
public class ContratoDao
        extends Dao<Contrato> {

    public static final String TABLE = "Contrato";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(redacao, ultimaAtualizacao, id)  values (?, ?, ?)";
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

            if (e.getCliente() != null)
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
                + " from Contrato";
    }

    @Override
    public String getDeleteStatement() {
        return "delete"
                + " from Contrato where redacao = ?";
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
            contrato.setId(resultSet.getLong("id"));
        } catch (SQLException ex)
        {
            Logger.getLogger(ContratoDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return contrato;
    }

    // Salva uma entidade com chave estrangeira definida
    public String save(Contrato contrato) {
        String redacao = null;

        if (contrato.getId() != null || contrato.getId() != 0)
        {
            // Insert a new record
            try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getSaveStatment()))
            {
                // Assemble the SQL statement with the data
                composeSaveOrUpdateStatement(preparedStatement, contrato);

                // Show the full sentence
                System.out.println(">> SQL: " + preparedStatement);

                // Performs insertion into the database
                preparedStatement.executeUpdate();

                redacao = contrato.getRedacao();

            } catch (Exception ex)
            {
                System.out.println(">> " + ex);
            }
        }

        return redacao;
    }

    public List<Contrato> findByRedacao(String redacao) {

        final String SQL = "select *"
                + " from " + TABLE
                + " where redacao"
                + " like ?";

        try (PreparedStatement preparedStatement
                = DbConnection.getConnection().prepareStatement(SQL))
        {

            preparedStatement.setString(1, "%" + redacao + "%");

            // Show the full sentence
            System.out.println(">> SQL: " + preparedStatement);

            // Performs the query on the database
            ResultSet resultSet = preparedStatement.executeQuery();

            // Returns the respective object
            return extractObjects(resultSet);

        } catch (Exception ex)
        {
            System.out.println("Exception: " + ex);
        }

        return null;
    }

    public void delete(String redacao) {
        try (PreparedStatement preparedStatement = DbConnection.getConnection().prepareStatement(getDeleteStatement()))
        {
            preparedStatement.setString(1, redacao);
            System.out.println(">> SQL: " + preparedStatement);
            preparedStatement.executeUpdate();

        } catch (Exception ex)
        {
            System.out.println("Exception: " + ex);
        }
    }
}
