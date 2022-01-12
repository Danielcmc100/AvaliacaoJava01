package classes;

public class Piloto extends Pessoa {
    private String breve;

    public Piloto(String nome,String cpf, String breve){
        this.nome = nome;
        this.cpf = cpf;
        this.breve = breve;
    }

    public String getBreve() {
        return breve;
    }

    public void setBreve(String breve) {
        this.breve = breve;
    }
    
}
