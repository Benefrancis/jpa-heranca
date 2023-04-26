package br.com.fiap;

import br.com.fiap.conta.model.Agencia;
import br.com.fiap.conta.model.Conta;
import br.com.fiap.conta.model.ContaCorrente;
import br.com.fiap.conta.model.ContaPoupanca;
import br.com.fiap.pessoa.model.PF;
import br.com.fiap.pessoa.model.PJ;
import br.com.fiap.pessoa.model.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("maria-db");
        EntityManager manager = factory.createEntityManager();

        PF bene = new PF();
        bene.setRG("13213213");
        bene.setCPF("21321321");
        bene.setNome("Benefrancis do Nascimento");
        bene.setNascimento(LocalDate.of(1977, 3, 8));

        var supermercado = new PJ();
        supermercado.setCNPJ("2131254654");
        supermercado.setInscricaoEstadual("2132465464");
        supermercado.setNascimento(LocalDate.now().minusYears(5));
        supermercado.setNome("Super Mercados Benezinho");

        Agencia agencia = new Agencia();
        agencia.setNumero(1200);

        ContaCorrente ccBene = new ContaCorrente();
        ccBene.setLimite(1_000_000);
        ccBene.setSaldo(2_000);
        ccBene.setNumero(88888);
        ccBene.setAgencia(agencia);
        ccBene.addTitular(bene);

        ContaPoupanca cpBene = new ContaPoupanca();
        cpBene.setAgencia(agencia);
        cpBene.setNumero(10088888);
        cpBene.setAniversario(MonthDay.now().getDayOfMonth());
        cpBene.setSaldo(20_000);
        cpBene.addTitular(bene);

        ContaCorrente ccSuper = new ContaCorrente();
        ccSuper.setNumero(999999);
        ccSuper.setAgencia(agencia);
        ccSuper.setSaldo(250_000_000);
        ccSuper.setLimite(1_000_000);
        ccSuper.addTitular(supermercado);


        manager.getTransaction().begin();

        List<Pessoa> pessoas = Arrays.asList(bene, supermercado);
        pessoas.forEach(manager::persist);

        List<Conta> contas = Arrays.asList(ccBene, cpBene, ccSuper);
        contas.forEach(manager::persist);

        manager.getTransaction().commit();

        contas.forEach(System.out::println);
    }
}