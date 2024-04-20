# Sistema Bancário com Threads em Java

## Descrição do Projeto
Este projeto simula um sistema bancário em Java utilizando threads para representar as interações entre clientes, funcionários e lojas. O sistema foi projetado para entender e aplicar conceitos de concorrência e sincronização em um ambiente simulado de transações bancárias.

## Entidades do Sistema
- **Banco**: Intermedia todas as transações de forma síncrona, garantindo a consistência dos saldos das contas.
- **Loja**: Existem duas lojas no sistema, cada uma com sua conta bancária. Recebem pagamentos dos clientes e pagam os salários dos funcionários.
- **Funcionário**: Cada loja tem dois funcionários. Cada funcionário é uma thread que possui uma conta de salário e uma conta de investimento, onde depositam 20% do seu salário.
- **Cliente**: Cada um dos cinco clientes é representado por uma thread e possui uma conta bancária inicial de R$ 1.000,00, realizando compras até esgotar o saldo.

## Funcionalidades
- **Transações Clientes-Lojas**: Clientes realizam compras alternando entre as duas lojas.
- **Pagamento de Salários**: As lojas pagam seus funcionários sempre que têm saldo suficiente.
- **Investimentos**: Após receber o pagamento, funcionários transferem 20% do valor para suas contas de investimento.

## Tecnologias Utilizadas
- **Java 17**
- **Multithreading**: Uso de threads para simular operações concorrentes.
