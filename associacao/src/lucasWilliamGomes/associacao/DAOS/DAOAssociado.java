package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Associado;
import lucasWilliamGomes.associacao.models.AssociadoRemido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DAOAssociado {
    public void inserir(Associado a, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        int discriminador = 0;
        long dataDeRemissao = -1;
        if (a instanceof AssociadoRemido) {
            discriminador = 1;
            dataDeRemissao = ((AssociadoRemido) a).getDataRemissao();
        }
        String cmd = "insert into associado (numero, nome, telefone, data_Associacao, nascimento, associacao, discriminador, data_remissao) values ("
                + a.getNumero() + ", \'" + a.getNome() + "\' ," + "\'" + a.getTelefone() + "\' ," + a.getDataAssociacao() + ", " +
                a.getNascimento() + " ," + associacao + " ," + discriminador + " ," + dataDeRemissao + ")";
        Statement st = con.createStatement();
        st.executeUpdate(cmd);
        st.close();
    }

    public Associado recuperar(int n, int numeroAssociacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from associado where numero = " + n + " and associacao = " + numeroAssociacao;
        Associado a = null;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        if (rs.next()) {
            int num = rs.getInt("numero");
            String nome = rs.getString("nome");
            String telefone = rs.getString("telefone");
            long dataAssociacao = rs.getLong("data_Associacao");
            long nascimento = rs.getLong("nascimento");
            int associacao = rs.getInt("associacao");
            int discriminador = rs.getInt("discriminador");
            if (discriminador == 1) {
                long dataRemissao = rs.getLong("data_remissao");
                a = new AssociadoRemido(num, nome, telefone, dataAssociacao, nascimento, dataRemissao);
            } else {
                a = new Associado(num, nome, telefone, dataAssociacao, nascimento);
            }
        }
        st.close();
        return a;
    }
}
