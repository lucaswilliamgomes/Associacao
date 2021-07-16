package lucasWilliamGomes.associacao.models;

public class Frequencia {
    private int associado;
    private int associacao;
    private long data;

    public int getAssociado() {
        return associado;
    }

    public void setAssociado(int associado) {
        this.associado = associado;
    }

    public int getAssociacao() {
        return associacao;
    }

    public void setAssociacao(int associacao) {
        this.associacao = associacao;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public Frequencia(int associado, int associacao, long data) {
        this.associado = associado;
        this.associacao = associacao;
        this.data = data;
    }

}
