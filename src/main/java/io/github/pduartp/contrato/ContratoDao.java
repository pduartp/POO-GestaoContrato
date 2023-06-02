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
public class ContratoDao
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

    @Override
    public void moveToTrash(Contrato e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void restoreFromTrash(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Contrato> findAllOnTrash() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
