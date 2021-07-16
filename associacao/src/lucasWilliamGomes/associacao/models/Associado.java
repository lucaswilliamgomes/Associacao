package lucasWilliamGomes.associacao.models;

import lucasWilliamGomes.associacao.DAOS.DAOPagamentos;
import lucasWilliamGomes.associacao.exceptions.ValorInvalido;

import java.sql.SQLException;
import java.util.ArrayList;

public class Associado {
    private int numero;
    private String nome;
    private String telefone;
    private long dataAssociacao;
    private long nascimento;

    public Associado (int numero, String nome, String telefone, long dataAssociacao, long nascimento) {
        this.numero = numero;
        this.nome = nome;
        this.telefone = telefone;
        this.dataAssociacao = dataAssociacao;
        this.nascimento = nascimento;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public long getDataAssociacao() {
        return dataAssociacao;
    }

    public void setDataAssociacao(long dataAssociacao) {
        this.dataAssociacao = dataAssociacao;
    }

    public long getNascimento() {
        return nascimento;
    }

    public void setNascimento(long nascimento) {
        this.nascimento = nascimento;
    }

    public void registroDePagamento(Taxa taxa, long data, double valor, int vigencia, int associacao, int associado) throws ValorInvalido {
        DAOPagamentos daoPagamentos = new DAOPagamentos();
        try {
            ArrayList<Pagamento> pagamentosRealizados = daoPagamentos.recuperarPagamentosDeUmaTaxa(taxa.getNome(), vigencia, associacao, associado);
            double totalPago = 0;
            for(Pagamento pagamento: pagamentosRealizados) {
                totalPago += pagamento.getValor();
            }
            double faltaPagar = taxa.getValorAno() - totalPago;
            if (totalPago >= taxa.getValorAno()) {
                throw new ValorInvalido();
            } else if (valor >= taxa.getValorMes() || valor >= faltaPagar) {
                daoPagamentos.inserirPagamento(data, valor, associado, vigencia, taxa.getNome(), associacao);
                return;
            } else if (valor <= taxa.getValorMes()){
                throw new ValorInvalido();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
