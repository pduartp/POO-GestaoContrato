package io.github.pduartp.gestaodecontratos;

import io.github.pduartp.contrato.Contrato;
import io.github.pduartp.cliente.Cliente;
import io.github.pduartp.cliente.ClienteDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Patrick Duarte Pimenta
 */
public class Program {

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc="Inserindo o cliente 1 e seus contratos">
        // Inserindo Cliente Ana Zaíra
        Cliente c1 = new Cliente();

        c1.setCpf(11929826303l);

        try
        {
            c1.setNome("Ana Zaíra");
        } catch (Exception ex)
        {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Inserindo contratos de Ana Zaíra
        // Contrato 1
        Contrato co1 = new Contrato();

        co1.setRedacao("Contrato por tempo determinado");
        co1.setUltimaAtualizacao(LocalDate.parse("21/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Contrato 2
        Contrato co2 = new Contrato();

        co2.setRedacao("Contrato por tempo determinado");
        co2.setUltimaAtualizacao(LocalDate.parse("01/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        // Contrato 3
        Contrato co3 = new Contrato();

        co3.setRedacao("Contrato por tempo determinado");
        co3.setUltimaAtualizacao(LocalDate.parse("26/05/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));

        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Inserindo o cliente 2 e seus contratos">
        // Inserindo Cliente Beatriz Yana
        Cliente c2 = new Cliente();

        c2.setCpf(26752965030l);

        try
        {
            c2.setNome("Beatriz Yana");
        } catch (Exception ex)
        {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }

        // Inserindo Contratos de Beatriz Yana
        // Inserindo Contrato 4
        Contrato co4 = new Contrato();

        co4.setRedacao("Contrato de estágio");
        co4.setUltimaAtualizacao(LocalDate.parse("15/10/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        // Inserindo Contrato 5
        Contrato co5 = new Contrato();

        co5.setRedacao("Contrato de experiência");
        co5.setUltimaAtualizacao(LocalDate.parse("16/09/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        // Inserindo Contrato 6
        Contrato co6 = new Contrato();

        co6.setRedacao("Contrato de teletrabalho");
        co6.setUltimaAtualizacao(LocalDate.parse("17/08/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        
        // Inserindo Contrato 7
        Contrato co7 = new Contrato();

        co7.setRedacao("Contrato intermitente,");
        co7.setUltimaAtualizacao(LocalDate.parse("15/07/2023", DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Implementando Bidirecionalidade para Cliente e Contrato">
        c1.adicionarContratos(co1);
        c1.adicionarContratos(co2);
        c1.adicionarContratos(co3);

        c2.adicionarContratos(co4);
        c2.adicionarContratos(co5);
        c2.adicionarContratos(co6);
        c2.adicionarContratos(co7);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="Imprimindo Resultados">
        System.out.println(c1);
        System.out.println("\n");
        System.out.println(c2);

        //</editor-fold>
        
        // CRUD = CREATE,RENAME,UPDATE, DELETE
        
        // Salvando Entidades Clientes no Banco de Dados
        //Long c1ID = new ClienteDao().saveOrUpdate(c1); 
        //c1.setId(c1ID);
    }
}
