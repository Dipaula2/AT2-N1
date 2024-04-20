public class Loja {
    private Conta conta;
    public static final double SALARIO_FUNCIONARIO = 1400.00;  // Salário fixo para cada funcionário

    public Loja(String nomeLoja) {
        this.conta = new Conta(2800, "ContaLoja" + nomeLoja);  
    }

    public void pagarFuncionario(Funcionario funcionario, Banco banco) {
        if (this.conta.getSaldo() >= SALARIO_FUNCIONARIO) {
            banco.realizarTransferencia(this.conta, funcionario.getContaSalario(), SALARIO_FUNCIONARIO);
            System.out.println("Pagamento realizado para funcionário: " + funcionario);
        } else {
            System.out.println("Pagamento não realizado: saldo insuficiente na conta da loja.");
        }
    }

    public Conta getConta() {
        return this.conta;
    }
}
