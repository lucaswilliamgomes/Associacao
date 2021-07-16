package lucasWilliamGomes.associacao.models;

public class Taxa {
    private String nome;
    private int vigencia;
    private double valorAno;
    private int parcelas;
    private double valorMes;
    private boolean administrativa;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVigencia() {
        return vigencia;
    }

    public void setVigencia(int vigencia) {
        this.vigencia = vigencia;
    }

    public double getValorAno() {
        return valorAno;
    }

    public void setValorAno(double valorAno) {
        this.valorAno = valorAno;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public boolean isAdministrativa() {
        return administrativa;
    }

    public void setAdministrativa(boolean administrativa) {
        this.administrativa = administrativa;
    }

    public double getValorMes() {
        return valorMes;
    }

    public void setValorMes(double valorMes) {
        this.valorMes = valorMes;
    }

    public Taxa(String nome, int vigencia, double valorAno, int parcelas, boolean administrativa){
        this.nome = nome;
        this.vigencia = vigencia;
        this.valorAno = valorAno;
        this.parcelas = parcelas;
        this.administrativa = administrativa;
        this.valorMes = valorAno / parcelas;
    }
}
