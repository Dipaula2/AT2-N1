public class Banco {
    
    // Método sincronizado para realizar transferências entre contas
    public synchronized void realizarTransferencia(Conta origem, Conta destino, double valor) {
        if (origem == null || destino == null) {
            throw new IllegalArgumentException("Contas de origem e destino devem ser válidas.");
        }
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor da transferência deve ser positivo.");
        }
        
        // Verifica se a conta de origem tem saldo suficiente para a transferência
        if (origem.getSaldo() >= valor) {
            origem.sacar(valor);          
            destino.depositar(valor);     
            
            // Exibir as informações da transferência 
            System.out.println("Transferência realizada: " + valor + " de " + origem + " para " + destino);
            System.out.println("Saldo após transferência: Origem: " + origem.getSaldo() + ", Destino: " + destino.getSaldo());
        } else {
            // Informa se não há saldo suficiente
            System.out.println("Transferência não realizada: saldo insuficiente.");
        }
    }
}
