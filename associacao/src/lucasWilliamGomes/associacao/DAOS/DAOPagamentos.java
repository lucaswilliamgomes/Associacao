package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Pagamento;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOPagamentos {
    public void inserirPagamento (long data, double valor, int associado, int vigencia, String taxa, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "insert into pagamento (data, valor, associado, vigencia, taxa, associacao) values ("
                + data + ", " + valor + ", " + associado + ", " +  vigencia + ", " + "\'" + taxa + "\'"  + ", " + associacao + ")";
        Statement st = con.createStatement();
        st.executeUpdate(cmd);
        st.close();
    }

    public ArrayList<Pagamento> recuperarPagamentosDeUmaTaxa(String nomeTaxa, int vigenciaTaxa, int associacaoTaxa, int associadoTaxa) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from pagamento where taxa = " + "\'" + nomeTaxa + "\'"
                + " and vigencia = " + vigenciaTaxa + " and associacao = " +  associacaoTaxa + " and associado = " +  associadoTaxa;
        Pagamento a = null;
        ArrayList<Pagamento> array = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        while (rs.next()) {
            long data  = rs.getLong("data");
            double valor = rs.getDouble("valor");
            int associado = rs.getInt("associado");
            int vigencia = rs.getInt("vigencia");
            String taxa = rs.getString("taxa");
            int associacao = rs.getInt("associacao");
            a = new Pagamento(data, valor, associado, vigencia, taxa, associacao);
            array.add(a);
        }
        st.close();
        return array;
    }
}
