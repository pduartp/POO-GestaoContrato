package io.github.pduartp.cliente;

import io.github.pduartp.contrato.Contrato;
import io.github.pduartp.entity.Entity;
import java.util.ArrayList;

/**
 *
 * @author Patrick Duarte Pimenta
 */
public class Cliente
        extends Entity {

    private Long cpf;
    private String nome;
    private ArrayList<Contrato> contratos;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    public Cliente() {
        contratos = new ArrayList<>();
    }

    public Cliente(Long id, Long cpf, String nome) {
        this();
        
        setId(id);
        this.cpf = cpf;
        this.nome = nome;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public Long getCpf() {
        return cpf;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) throws Exception {
        if (nome.length() > 45)
        {
            throw new Exception("Valor excedido para nome");
        }

        this.nome = nome;
    }

    public ArrayList<Contrato> getContratos() {
        return contratos;
    }

    public void setContratos(ArrayList<Contrato> contratos) {
        this.contratos = contratos;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Adicionar contratos">
    public void adicionarContratos(Contrato contrato) {
        contratos.add(contrato);
        contrato.setCliente(this);
    }
//</editor-fold>

    @Override
    public String toString() {
        return "Cliente{"
                + "id=" + getId()
                + ", cpf=" + cpf
                + ", nome=" + nome
//                + ", contratos=\n" + contratos.toString()
                + '}' + "\n";
    }

}
