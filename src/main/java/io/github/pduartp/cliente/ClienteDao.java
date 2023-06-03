package io.github.pduartp.cliente;

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
CREATE TABLE cliente (
    id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    cpf BIGINT,
    nome VARCHAR(45) NOT NULL
);
 */
public class ClienteDao
        extends Dao<Cliente> {

    public static final String TABLE = "Cliente";

    @Override
    public String getSaveStatment() {
        return "insert into " + TABLE + "(cpf, nome)  values (?, ?)";
    }

    @Override
    public String getUpdateStatment() {
        return "update " + TABLE + " set cpf = ?, nome = ? where id = ?";
    }

    @Override
    public void composeSaveOrUpdateStatement(PreparedStatement pstmt, Cliente e) {
        try
        {
            pstmt.setObject(1, e.getCpf(), java.sql.Types.BIGINT);
            pstmt.setObject(2, e.getNome(), java.sql.Types.VARCHAR);

            // Just for the update
            if (e.getId() != null)
            {
                pstmt.setLong(3, e.getId());
            }

        } catch (SQLException ex)
        {
            Logger.getLogger(e.getNome()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getFindByIdStatment() {
        return "select id, cpf, nome"
                + " from Cliente where id = ?";
    }

    @Override
    public String getFindAllStatment() {
        return "select id, cpf, nome"
                + " from Cliente";
    }

    @Override
    public String getDeleteStatement() {
        return "delete"
                + " from Cliente where id = ?";
    }

    @Override
    public Cliente extractObject(ResultSet resultSet) {
        Cliente cliente = null;

        try
        {
            cliente = new Cliente();
            cliente.setId(resultSet.getLong("id"));
            cliente.setCpf(resultSet.getLong("cpf"));
            cliente.setNome(resultSet.getString("nome"));
        } catch (Exception ex)
        {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return cliente;
    }
}
