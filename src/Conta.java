
public abstract class Conta implements IConta {

	private static final int AGENCIA_PADRAO = 10000;
	private static int SEQUENCIAL = 100000;

	protected int agencia;
	protected int numero;
	protected double saldo;
	protected Cliente cliente;
	protected CartaoDebito cartaoDebito;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
		this.cartaoDebito = new CartaoDebito(this);
	}

	@Override
	public void sacar(double valor) {
		double saldoAnterior = saldo;
		saldo -= valor;
		System.out.printf("Operacao: Saque | Valor: %.2f | Saldo anterior: %.2f | Saldo resultante: %.2f%n", valor, saldoAnterior, saldo);
	}

	@Override
	public void depositar(double valor) {
		double saldoAnterior = saldo;
		saldo += valor;
		System.out.printf("Operacao: Deposito | Valor: %.2f | Saldo anterior: %.2f | Saldo resultante: %.2f%n", valor, saldoAnterior, saldo);
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		double saldoAnterior = saldo;
		saldo -= valor;
		contaDestino.depositar(valor);
		String destinoInfo;
		if (contaDestino instanceof Conta) {
			Conta destino = (Conta) contaDestino;
			destinoInfo = String.format("%s - %05d-%d", destino.cliente.getNome(), destino.numero / 10, destino.numero % 10);
		} else {
			destinoInfo = "desconhecido";
		}
		System.out.printf("Operacao: Transferencia | Valor: %.2f | Saldo anterior: %.2f | Saldo resultante: %.2f | Destino: %s%n", valor, saldoAnterior, saldo, destinoInfo);
	}

	public int getAgencia() {
		return agencia;
	}

	public int getNumero() {
		return numero;
	}

	public double getSaldo() {
		return saldo;
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
	 System.out.println(String.format("Agencia: %04d-%d", this.agencia / 10, this.agencia % 10));
		System.out.println(String.format("Numero: %05d-%d", this.numero / 10, this.numero % 10));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	public CartaoDebito getCartaoDebito() {
		return cartaoDebito;
	}
}
