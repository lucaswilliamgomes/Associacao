package lucasWilliamGomes.associacao.models;

import lucasWilliamGomes.associacao.models.Associado;

public class AssociadoRemido extends Associado {
    private long dataRemissao;

    public long getDataRemissao() {
        return dataRemissao;
    }

    public void setDataRemissao(long dataRemissao) {
        this.dataRemissao = dataRemissao;
    }

    public AssociadoRemido(int numero, String nome, String telefone, long dataAssociacao, long nascimento, long dataRemissao) {
        super(numero, nome, telefone, dataAssociacao, nascimento);
        this.dataRemissao = dataRemissao;
    }
}
