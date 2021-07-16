package lucasWilliamGomes.associacao.models;

public class Pagamento {
    private long data;
    private double valor;
    private int associado;
    private int vigencia;
    private String taxa;
    private int associacao;

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getAssociado() {
        return associado;
    }

    public void setAssociado(int associado) {
        this.associado = associado;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public int getAssociacao() {
        return associacao;
    }

    public void setAssociacao(int associacao) {
        this.associacao = associacao;
    }

    public Pagamento(long data, double valor, int associado, int vigencia, String taxa, int associacao) {
        this.data = data;
        this.valor = valor;
        this.associado = associado;
        this.vigencia = vigencia;
        this.taxa = taxa;
        this.associacao = associacao;
    }
}
