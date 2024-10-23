public abstract class ContaBancaria {
    protected double saldo;

    public ContaBancaria(double saldoInicial) {
        this.saldo = saldoInicial;
    }

    
    public abstract void saque(double valor);
    public abstract void deposito(double valor);
    public abstract void consultaSaldo();
}


public class ContaCorrente extends ContaBancaria {
    private double limite;
    private final double taxaSaque = 5.0;
    private final double taxaDeposito = 2.0;
    private final double taxaConsulta = 1.0;

    public ContaCorrente(double saldoInicial, double limite) {
        super(saldoInicial);
        this.limite = limite;
    }

    @Override
    public void saque(double valor) {
        double valorComTaxa = valor + taxaSaque;
        if (saldo + limite >= valorComTaxa) {
            saldo -= valorComTaxa;
            System.out.println("Saque de R$" + valor + " realizado com sucesso. Taxa de R$" + taxaSaque + " aplicada.");
        } else {
            System.out.println("Saldo insuficiente para saque. Limite disponível de R$" + (saldo + limite));
        }
    }

    @Override
    public void deposito(double valor) {
        saldo += valor - taxaDeposito;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso. Taxa de R$" + taxaDeposito + " aplicada.");
    }

    @Override
    public void consultaSaldo() {
        saldo -= taxaConsulta;
        System.out.println("Consulta de saldo: R$" + saldo + ". Taxa de R$" + taxaConsulta + " aplicada.");
    }
}


public class ContaPoupanca extends ContaBancaria {
    private final double taxaSaque = 3.0;
    private final double taxaDeposito = 1.5;
    private final double taxaConsulta = 0.5;

    public ContaPoupanca(double saldoInicial) {
        super(saldoInicial);
    }

    @Override
    public void saque(double valor) {
        double valorComTaxa = valor + taxaSaque;
        if (saldo >= valorComTaxa) {
            saldo -= valorComTaxa;
            System.out.println("Saque de R$" + valor + " realizado com sucesso. Taxa de R$" + taxaSaque + " aplicada.");
        } else {
            System.out.println("Saldo insuficiente. Não é permitido sacar mais do que o saldo disponível.");
        }
    }

    @Override
    public void deposito(double valor) {
        saldo += valor - taxaDeposito;
        System.out.println("Depósito de R$" + valor + " realizado com sucesso. Taxa de R$" + taxaDeposito + " aplicada.");
    }

    @Override
    public void consultaSaldo() {
        saldo -= taxaConsulta;
        System.out.println("Consulta de saldo: R$" + saldo + ". Taxa de R$" + taxaConsulta + " aplicada.");
    }
}


public class Main {
    public static void main(String[] args) {
        
        ContaCorrente cc = new ContaCorrente(1000.0, 500.0);
        ContaPoupanca cp = new ContaPoupanca(1500.0);

        
        System.out.println("---- Conta Corrente ----");
        cc.consultaSaldo();
        cc.deposito(300.0);
        cc.saque(1200.0);
        cc.consultaSaldo();

        
        System.out.println("\n---- Conta Poupança ----");
        cp.consultaSaldo();
        cp.deposito(200.0);
        cp.saque(1600.0);
        cp.consultaSaldo();
    }
}
