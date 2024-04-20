public class Funcionario implements Runnable {
    private Conta contaSalario;
    private Conta contaInvestimento;
    private static final double PERCENTUAL_INVESTIMENTO = 0.20;  
    private final Object lock = new Object();

    public Funcionario(String nomeFuncionario) {
        this.contaSalario = new Conta(0, "ContaSalario" + nomeFuncionario);
        this.contaInvestimento = new Conta(0, "ContaInvestimento" + nomeFuncionario);
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                
                while (contaSalario.getSaldo() == 0) {
                    System.out.println(Thread.currentThread().getName() + " aguardando depósito de salário.");
                    Thread.sleep(100);  
                }
                double valorInvestimento = contaSalario.getSaldo() * PERCENTUAL_INVESTIMENTO;
                System.out.println(Thread.currentThread().getName() + " sacando para investimento: " + valorInvestimento);
                contaSalario.sacar(valorInvestimento);
                System.out.println(Thread.currentThread().getName() + " depositando em conta de investimento: " + valorInvestimento);
                contaInvestimento.depositar(valorInvestimento);
                System.out.println("Investimento realizado: " + valorInvestimento + " por " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread de " + Thread.currentThread().getName() + " foi interrompida.");
            }
        }
    }

    public void receberSalario(double salario) {
        synchronized (lock) {
            contaSalario.depositar(salario);
            lock.notifyAll();  // Notifica a thread que o salário foi depositado
        }
    }


    public Conta getContaSalario() {
        return contaSalario;
    }

    public Conta getContaInvestimento() {
        return contaInvestimento;
    }
}
