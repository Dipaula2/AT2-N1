public class Conta {
    private double saldo;
    private String nome;  

    public Conta(double saldoInicial, String nome) {
        this.saldo = saldoInicial;
        this.nome = nome;
    }

    public synchronized void depositar(double valor) {
        this.saldo += valor;
        System.out.println("Depósito de " + valor + " realizado na " + nome);
    }

    public synchronized boolean sacar(double valor) {
        if (this.saldo >= valor) {
            this.saldo -= valor;
            System.out.println("Saque de " + valor + " realizado na " + nome);
            return true;
        }
        return false;
    }

    public double getSaldo() {
        return this.saldo;
    }

    @Override
    public String toString() {
        return nome + " (Saldo: " + saldo + ")";
    }
}
