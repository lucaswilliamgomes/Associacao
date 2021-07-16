package lucasWilliamGomes.associacao;

import lucasWilliamGomes.associacao.DAOS.*;
import lucasWilliamGomes.associacao.exceptions.*;
import lucasWilliamGomes.associacao.models.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MinhaAssociacao implements InterfaceAssociacao {

    public void limparBanco() {
        Connection con = Conexao.getConexao();
        String cmd1 = "delete from associacao";
        String cmd2 = "delete from associado";
        String cmd3 = "delete from frequencia";
        String cmd4 = "delete from pagamento";
        String cmd5 = "delete from reuniao";
        String cmd6 = "delete from taxa";
        Statement st = null;
        try {
            st = con.createStatement();
            st.executeUpdate(cmd1);
            st.executeUpdate(cmd2);
            st.executeUpdate(cmd3);
            st.executeUpdate(cmd4);
            st.executeUpdate(cmd5);
            st.executeUpdate(cmd6);
            st.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Associacao pesquisarAssociacao(int numAssociacao) {
        DAOAssociacao daoAssociacao = new DAOAssociacao();
        try {
            Associacao associacao = daoAssociacao.recuperar(numAssociacao);
            if (associacao != null) {
                return associacao;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    @Override
    public void adicionar(Associacao a) throws AssociacaoJaExistente, ValorInvalido {
        if (a == null || a.getNum() < 0 || a.getNome() == null || a.getNome().isBlank()) throw new ValorInvalido();
        if (pesquisarAssociacao(a.getNum()) == null) {
            DAOAssociacao daoAssociacao = new DAOAssociacao();
            try {
                daoAssociacao.inserir(a);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } else {
            throw new AssociacaoJaExistente();
        }
    }

    @Override
    public void adicionar(int associacao, Associado a) throws AssociacaoNaoExistente, AssociadoJaExistente, ValorInvalido {
        if (associacao < 0 || a.getNome() == null || a.getTelefone() == null || a.getNome().isBlank() || a.getTelefone().isBlank()
            || a.getNumero() < 0 || a.getNascimento() < 0 || a.getDataAssociacao() < 0 )
            throw new ValorInvalido();

        Associacao devidaAssociacao = pesquisarAssociacao(associacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOAssociado daoAssociado = new DAOAssociado();
            try {
                Associado associado = daoAssociado.recuperar(a.getNumero(), associacao);
                if (associado == null) {
                    daoAssociado.inserir(a, associacao);
                } else {
                    throw new AssociadoJaExistente();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void adicionar(int associacao, Reuniao r) throws AssociacaoNaoExistente, ReuniaoJaExistente, ValorInvalido {
        if (associacao < 0 || r.getData() < 0 || r.getAta() == null || r.getAta().isBlank()) throw new ValorInvalido();
        Associacao devidaAssociacao = pesquisarAssociacao(associacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOReuniao daoReuniao = new DAOReuniao();
            try {
                if (daoReuniao.recuperar(r.getData(), associacao) == null) {
                    daoReuniao.adicionarReuniao(r, associacao);
                } else {
                    throw new ReuniaoJaExistente();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public void adicionar(int associacao, Taxa t) throws AssociacaoNaoExistente, TaxaJaExistente, ValorInvalido {
        if (associacao < 0 || t.getParcelas() <= 0 || t.getValorAno() <= 0 || t.getValorAno() < 0 || t.getNome() == null || t.getNome().isBlank() || t.getVigencia() < 0) throw new ValorInvalido();

        Associacao devidaAssociacao = pesquisarAssociacao(associacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOTaxa daoTaxa = new DAOTaxa();
            try {
                if (daoTaxa.recuperar(t.getNome(), t.getVigencia(), associacao) == null) {
                    daoTaxa.adicionarTaxa(t, associacao);
                } else {
                    throw new TaxaJaExistente();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public void registrarFrequencia(int codigoAssociado, int numAssociacao, long dataReuniao)
            throws AssociadoNaoExistente, ReuniaoNaoExistente, AssociacaoNaoExistente,
            FrequenciaJaRegistrada, FrequenciaIncompativel {
        Associacao devidaAssociacao = pesquisarAssociacao(numAssociacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOFrequencia daoFrequencia = new DAOFrequencia();
            DAOReuniao daoReuniao = new DAOReuniao();
            DAOAssociado daoAssociado = new DAOAssociado();
            try {
                Associado associado = daoAssociado.recuperar(codigoAssociado, numAssociacao);
                if (associado == null) {
                    throw new AssociadoNaoExistente();
                } else if (daoReuniao.recuperar(dataReuniao, numAssociacao) == null) {
                    throw new ReuniaoNaoExistente();
                } else if (associado.getDataAssociacao() > dataReuniao) {
                    throw new FrequenciaIncompativel();
                } else {
                    if (daoFrequencia.recuperarFrequenciaEspecifica(codigoAssociado, numAssociacao, dataReuniao) == null) {
                        daoFrequencia.adicionarFrequencia(codigoAssociado, numAssociacao, dataReuniao);
                    } else {
                        throw new FrequenciaJaRegistrada();
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }
    }

    public double calcularFrequencia(int numAssociado, int numAssociacao, long inicio, long fim)
                throws AssociadoNaoExistente, AssociacaoNaoExistente {
        Associacao devidaAssociacao = pesquisarAssociacao(numAssociacao);
        int totalReunioes = 0, totalPartipacoes = 0;
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOAssociado daoAssociado = new DAOAssociado();
            DAOFrequencia daoFrequencia = new DAOFrequencia();
            DAOReuniao daoReuniao = new DAOReuniao();
            try {
                if (daoAssociado.recuperar(numAssociado, numAssociacao) == null) {
                    throw new AssociadoNaoExistente();
                } else {
                    ArrayList<Reuniao> reunioes = new ArrayList<>();;
                    ArrayList<Frequencia> frequencias = new ArrayList<>();;
                    reunioes = daoReuniao.recuperarReunioesDeUmaAssociacao(numAssociacao);
                    frequencias = daoFrequencia.recuperarFrequenciaDeUmAssociado(numAssociacao, numAssociado);
                    for (Reuniao it : reunioes) {
                        if (it.getData() >= inicio && it.getData() <= fim) totalReunioes++;
                    }
                    for (Frequencia it : frequencias) {
                        if (it.getData() >= inicio && it.getData() <= fim) totalPartipacoes++;
                    }

                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if (totalReunioes == 0) {
            return 0;
        }
        return (double)totalPartipacoes/totalReunioes;
    }

    public void registrarPagamento(int numAssociacao, String taxa, int vigencia, int numAssociado, long data, double valor)
            throws AssociacaoNaoExistente, AssociadoNaoExistente, AssociadoJaRemido, TaxaNaoExistente, ValorInvalido {
        if (numAssociacao < 0 || taxa == "" || taxa == null || vigencia < 0 || numAssociacao < 0 || data < 0 || valor < 0) {
            throw new ValorInvalido();
        }
        Associacao devidaAssociacao = pesquisarAssociacao(numAssociacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOAssociado daoAssociado = new DAOAssociado();
            DAOTaxa daoTaxa = new DAOTaxa();
            try {
                Associado associado = daoAssociado.recuperar(numAssociado, numAssociacao);
                Taxa devidaTaxa = daoTaxa.recuperar(taxa, vigencia, numAssociacao);
                if (associado == null) {
                    throw new AssociadoNaoExistente();
                } else if (devidaTaxa == null) {
                    throw new TaxaNaoExistente();
                } else {
                    if (associado instanceof AssociadoRemido && devidaTaxa.isAdministrativa()) {
                        if (((AssociadoRemido) associado).getDataRemissao() < data) {
                            throw new AssociadoJaRemido();
                        }
                    }
                    associado.registroDePagamento(devidaTaxa, data, valor, vigencia, numAssociacao, numAssociado);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }

    public double somarPagamentoDeAssociado (int numAssociacao, int numAssociado, String nomeTaxa, int vigencia, long inicio, long fim)
            throws AssociacaoNaoExistente, AssociadoNaoExistente, TaxaNaoExistente {
        double total = 0;
        Associacao devidaAssociacao = pesquisarAssociacao(numAssociacao);
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOAssociado daoAssociado = new DAOAssociado();
            DAOTaxa daoTaxa = new DAOTaxa();
            DAOPagamentos daoPagamentos = new DAOPagamentos();
            try {
                Associado associado = daoAssociado.recuperar(numAssociado, numAssociacao);
                Taxa devidaTaxa = daoTaxa.recuperar(nomeTaxa, vigencia, numAssociacao);
                if (associado == null) {
                    throw new AssociadoNaoExistente();
                } else if (devidaTaxa == null) {
                    throw new TaxaNaoExistente();
                } else {
                    ArrayList<Pagamento> pagamentosRealizados = daoPagamentos.recuperarPagamentosDeUmaTaxa(nomeTaxa, vigencia, numAssociacao, numAssociado);
                    for(Pagamento pagamento: pagamentosRealizados) {
                        if (pagamento.getData() >= inicio && pagamento.getData() >= fim) {
                            total += pagamento.getValor();
                        }
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return total;
    }

    public double calcularTotalDeTaxas (int numAssociacao, int vigencia) throws AssociacaoNaoExistente {
        Associacao devidaAssociacao = pesquisarAssociacao(numAssociacao);
        double total = 0;
        if (devidaAssociacao == null) {
            throw new AssociacaoNaoExistente();
        } else {
            DAOTaxa daoTaxa = new DAOTaxa();
            try {
                ArrayList<Taxa> taxas = daoTaxa.recuperarTaxasDeUmaAssociacao(vigencia, numAssociacao);
                for (Taxa taxa: taxas) {
                    total+= taxa.getValorAno();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return total;
    }
}
