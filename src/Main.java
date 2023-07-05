public class Main {
public static void main(String[] args) {
	Cliente Eduardo = new Cliente();
	Eduardo.setNome("Eduardo");
		
	Conta cc = new ContaCorrente(Eduardo);
	Conta poupanca = new ContaPoupanca(Eduardo);
	// Valor de um salário mínimo
	cc.depositar(1320);
	cc.transferir(132, poupanca);
	// 10% do salário	
	cc.imprimirExtrato();
	poupanca.imprimirExtrato();

	CartaoDebito cartaoDebito = cc.getCartaoDebito();
	cartaoDebito.pagar(50);
	cartaoDebito.imprimirExtrato();
}
}