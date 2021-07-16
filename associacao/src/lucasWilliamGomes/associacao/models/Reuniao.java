package lucasWilliamGomes.associacao.models;

public class Reuniao {
    private long data;
    private String ata;

    public Reuniao(long data, String ata) {
        this.data = data;
        this.ata = ata;
    }

    public long getData() {
        return data;
    }

    public void setData(long data) {
        this.data = data;
    }

    public String getAta() {
        return ata;
    }

    public void setAta(String ata) {
        this.ata = ata;
    }
}
