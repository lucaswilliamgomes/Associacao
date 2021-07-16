package lucasWilliamGomes.associacao.models;

public class Associacao {
    private int num;
    private String nome;

    public Associacao(int num, String nome) {
        this.num = num;
        this.nome = nome;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
