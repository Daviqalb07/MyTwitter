package classes;

public class PessoaJuridica extends Perfil{
    private long cnpj;


    public PessoaJuridica(String user, long cnpj) {
        super(user);
        this.cnpj = cnpj;
    }

    public void setCnpj(long cnpj) {
        this.cnpj = cnpj;
    }

    public long getCnpj() {
        return this.cnpj;
    }
}
