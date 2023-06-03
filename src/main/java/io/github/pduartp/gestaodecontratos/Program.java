package io.github.pduartp.gestaodecontratos;

import io.github.pduartp.contrato.Contrato;
import io.github.pduartp.cliente.Cliente;
import io.github.pduartp.cliente.ClienteDao;
import io.github.pduartp.contrato.ContratoDao;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author Patrick Duarte Pimenta
 */
public class Program {

    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc="Inserindo o cliente 1 e seus contratos">
        // Inserindo Cliente Ana Zaíra
        Cliente c1 = new Cliente(null, 11929826303l, "Ana Zaíra");

        /*c1.setCpf(11929826303l);

        try
        {
            c1.setNome("Ana Zaíra");
        } catch (Exception ex)
        {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
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
        Cliente c2 = new Cliente(null, 26752965030l, "Beatriz Yana");

        /*c2.setCpf(26752965030l);

        try
        {
            c2.setNome("Beatriz Yana");
        } catch (Exception ex)
        {
            Logger.getLogger(Program.class.getName()).log(Level.SEVERE, null, ex);
        }
         */
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

        co7.setRedacao("Contrato intermitente");
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

        System.out.println(co1);
        System.out.println(co2);
        System.out.println(co3);

        System.out.println(c2);

        System.out.println(co4);
        System.out.println(co5);
        System.out.println(co6);
        System.out.println(co7);

        //</editor-fold>
        // C-R-U-D = CREATE, READ, UPDATE, DELETE
        //<editor-fold defaultstate="collapsed" desc="C (Create): Cliente">
        Long c1ID = new ClienteDao().saveOrUpdate(c1);
        c1.setId(c1ID);

        Long c2ID = new ClienteDao().saveOrUpdate(c2);
        c2.setId(c2ID);
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="R (Read): Cliente">
        // Listando todos os clientes
        List<Cliente> clientes = new ClienteDao().findAll();
        System.out.println(">> " + clientes);

        // Listar um cliente em específico através do id
        Cliente clienteAux = new ClienteDao().findById(c1ID);
        System.out.println(">>" + clienteAux);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="U (Update): Cliente">
        clienteAux.setCpf(777l);
        new ClienteDao().saveOrUpdate(clienteAux);
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="D (Delete: Cliente">
        new ClienteDao().delete(c1ID);
        new ClienteDao().delete(c2ID);

        // id volta a receber null;
        c1.setId(null);
        c2.setId(null);
//</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="C (Create): Contrato">  
        // Adicionando novamente os clientes
        c1ID = new ClienteDao().saveOrUpdate(c1);
        c1.setId(c1ID);

        c2ID = new ClienteDao().saveOrUpdate(c2);
        c2.setId(c2ID);

        // Adicionando como id dos contratos o id dos clientes
        co1.setId(c1.getId());
        co2.setId(c1.getId());
        co3.setId(c1.getId());

        co4.setId(c2.getId());
        co5.setId(c2.getId());
        co6.setId(c2.getId());
        co7.setId(c2.getId());

        // Contratos do Cliente 1
        String co1r = new ContratoDao().save(co2);
        String co2r = new ContratoDao().save(co2);
        String co3r = new ContratoDao().save(co3);

        // Contratos do Cliente 2   
        String co4r = new ContratoDao().save(co4);
        String co5r = new ContratoDao().save(co5);
        String co6r = new ContratoDao().save(co6);
        String co7r = new ContratoDao().save(co7);
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="R (Read): Contrato">
        // Listando todos os contratos
        List<Contrato> contratos = new ContratoDao().findAll();
        System.out.println(">>" + contratos);

        // Listar um contrato em específico através da descrição;
        List<Contrato> contratoComRedacao
                = new ContratoDao().findByRedacao("Contrato de experiência");
        System.out.println(">>" + contratoComRedacao);
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="U (Update): Contrato">
        co5.setRedacao(co5.getRedacao() + "!!!!");
        new ContratoDao().saveOrUpdate(co5);
//        for (Contrato c : contratoComRedacao)
//        {
//            c.setRedacao("CONTRATO DE EXPERIÊNCIA CONCLUIDO");
//            new ContratoDao().saveOrUpdate(c);
//        }
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="D (Delete): Contrato">
        new ContratoDao().delete(co7r);
        //</editor-fold>
    }
}
