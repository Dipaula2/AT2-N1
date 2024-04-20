public class Main {
    public static void main(String[] args) throws InterruptedException {
        Banco banco = new Banco();
        Loja loja1 = new Loja("1");
        Loja loja2 = new Loja("2");
        Loja[] lojas = {loja1, loja2};

        Funcionario[] funcionarios = new Funcionario[4];
        for (int i = 0; i < funcionarios.length; i++) {
            funcionarios[i] = new Funcionario(Integer.toString(i + 1));
            new Thread(funcionarios[i], "Funcionario" + (i + 1)).start();
        }

        Cliente[] clientes = new Cliente[5];
        for (int i = 0; i < clientes.length; i++) {
            clientes[i] = new Cliente(lojas, banco, Integer.toString(i + 1));
            new Thread(clientes[i], "Cliente" + (i + 1)).start();
        }

        // Esperar um tempo para que os clientes possam começar a fazer compras
        Thread.sleep(5000);  

        // Tenta pagar cada funcionário em cada loja após o delay
        for (Funcionario funcionario : funcionarios) {
            loja1.pagarFuncionario(funcionario, banco);
            loja2.pagarFuncionario(funcionario, banco);
        }

        // Continua tentando pagar os funcionários a cada 5 segundos até que todos tenham sido pagos
        boolean todosPagos = false;
        while (!todosPagos) {
            todosPagos = true;
            for (Funcionario funcionario : funcionarios) {
            	if (funcionario.getContaSalario().getSaldo() < Loja.SALARIO_FUNCIONARIO) {
            	    loja1.pagarFuncionario(funcionario, banco);
            	    loja2.pagarFuncionario(funcionario, banco);
            	    todosPagos = false;
            	}
            }
            Thread.sleep(10000);  // Espera mais 10 segundos antes da próxima tentativa
        }

        System.out.println("Todos os funcionários foram pagos.");
    }
}
