import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

public class CartaoDebitoTest {

    private Conta criarConta() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente");
        return new ContaCorrente(cliente);
    }

    @Test
    public void testPagar() {
        Conta conta = criarConta();
        conta.depositar(100.0);
        CartaoDebito cartao = conta.getCartaoDebito();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        cartao.pagar(30.0);
        System.setOut(original);

        assertEquals(70.0, conta.getSaldo());
        assertTrue(out.toString().contains("Pagamento de 30.00 realizado com sucesso."));
    }

    @Test
    public void testImprimirExtrato() {
        Conta conta = criarConta();
        conta.depositar(50.0);
        CartaoDebito cartao = conta.getCartaoDebito();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream original = System.out;
        System.setOut(new PrintStream(out));
        cartao.imprimirExtrato();
        System.setOut(original);

        String output = out.toString();
        assertTrue(output.contains("=== Extrato do Cartao de Debito ==="));
        assertTrue(output.contains("Titular: Cliente"));
        assertTrue(output.contains(String.format("Agencia: %d", conta.getAgencia())));
        assertTrue(output.contains(String.format("Numero: %d", conta.getNumero())));
        assertTrue(output.contains(String.format("Saldo: %.2f", conta.getSaldo())));
    }
}
