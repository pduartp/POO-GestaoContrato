/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.pduartp.contrato;

import io.github.pduartp.cliente.Cliente;
import io.github.pduartp.entity.Entity;
import java.time.LocalDate;

/**
 *
 * @author Patrick Duarte Pimenta
 */
public class Contrato
        extends Entity {

    private String redacao;
    private LocalDate ultimaAtualizacao;
    Cliente cliente;

    //<editor-fold defaultstate="collapsed" desc="Constructos">
    public Contrato() {
    }

    public Contrato(Long id, String redacao, LocalDate ultimaAtualizacao) {
        setId(id);
        this.redacao = redacao;
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Getters/Setters">
    public String getRedacao() {
        return redacao;
    }

    public void setRedacao(String redacao) {
        this.redacao = redacao;
    }

    public LocalDate getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(LocalDate ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

//</editor-fold>
    @Override
    public String toString() {
        return "Contrato{"
                + "redacao=" + redacao
                + ", ultimaAtualizacao=" + ultimaAtualizacao
                + '}' + "\n";
    }

}
