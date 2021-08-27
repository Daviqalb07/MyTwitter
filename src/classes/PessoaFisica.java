package classes;

public class PessoaFisica extends Perfil{
    private long cpf;

    public PessoaFisica(String user, long cpf){
        super(user);
        this.cpf = cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public long getCpf() {
        return this.cpf;
    }
}
