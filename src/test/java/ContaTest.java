import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class ContaTest {

    private Conta criarContaComSaldo(double saldoInicial) {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente");
        Conta conta = new ContaCorrente(cliente);
        if (saldoInicial > 0) {
            conta.depositar(saldoInicial);
        }
        return conta;
    }

    @Test
    public void testDepositar() {
        Conta conta = criarContaComSaldo(0);
        conta.depositar(100.0);
        assertEquals(100.0, conta.getSaldo());
    }

    @Test
    public void testSacar() {
        Conta conta = criarContaComSaldo(200.0);
        conta.sacar(50.0);
        assertEquals(150.0, conta.getSaldo());
    }

    @Test
    public void testTransferir() {
        Cliente clienteDestino = new Cliente();
        clienteDestino.setNome("Destino");
        Conta contaOrigem = criarContaComSaldo(100.0);
        Conta contaDestino = new ContaPoupanca(clienteDestino);

        contaOrigem.transferir(40.0, contaDestino);

        assertEquals(60.0, contaOrigem.getSaldo());
        assertEquals(40.0, contaDestino.getSaldo());
    }
}
