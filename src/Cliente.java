import java.util.Random;

public class Cliente implements Runnable {
    private Conta conta;
    private Loja[] lojas;  // Array de lojas para alternar as compras
    private Banco banco;

    public Cliente(Loja[] lojas, Banco banco, String nomeConta) {
        this.conta = new Conta(1000, "ContaCliente" + nomeConta);  // Saldo inicial de R$ 1000 com identificador
        this.lojas = lojas;
        this.banco = banco;
    }

    @Override
    public void run() {
        Random rand = new Random();
        int lojaIndex = 0;  // Inicia comprando na primeira loja

        while (conta.getSaldo() > 0) {
            double valorCompra = rand.nextBoolean() ? 100.0 : 200.0;
            
            if (conta.getSaldo() >= valorCompra) {
                // Realiza a compra se houver saldo suficiente
                synchronized (banco) {
                    banco.realizarTransferencia(conta, lojas[lojaIndex].getConta(), valorCompra);
                }
                
                // Alterna entre as lojas para a próxima compra
                lojaIndex = 1 - lojaIndex;
            }

            // Simula tempo de espera entre compras
            try {
                Thread.sleep(rand.nextInt(1000) + 500);  
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrompida.");
            }
        }

        System.out.println(Thread.currentThread().getName() + " completou suas compras. Saldo final: " + conta.getSaldo());
    }
}
