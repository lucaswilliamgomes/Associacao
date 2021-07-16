package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Taxa;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOTaxa {
    public void adicionarTaxa (Taxa a, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "insert into taxa (nome, vigencia, valor, parcelas, administrativa, associacao) values ("
                + "\'" + a.getNome() + "\' ," + a.getVigencia() + ", " + a.getValorAno() + ", " + a.getParcelas() + ", " + a.isAdministrativa() + ", " + associacao + ")";
        Statement st = con.createStatement();
        st.executeUpdate(cmd);
        st.close();
    }

    public Taxa recuperar(String nomeTaxa, int vigenciaTaxa, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from taxa where nome = " + "\'" + nomeTaxa + "\'"
                + " and vigencia = " + vigenciaTaxa + " and associacao = " +  associacao;
        Taxa a = null;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        if (rs.next()) {
            String nome  = rs.getString("nome");
            int vigencia = rs.getInt("vigencia");
            double valorAno = rs.getDouble("valor");
            int parcelas = rs.getInt("parcelas");
            boolean administrativa = rs.getBoolean("administrativa");
            a = new Taxa(nome, vigencia, valorAno, parcelas, administrativa);
        }
        st.close();
        return a;
    }

    public ArrayList<Taxa> recuperarTaxasDeUmaAssociacao(int vigenciaTaxa, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from taxa where vigencia = " + vigenciaTaxa + " and associacao = " +  associacao;
        Taxa a = null;
        ArrayList<Taxa> array = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        while (rs.next()) {
            String nome  = rs.getString("nome");
            int vigencia = rs.getInt("vigencia");
            double valorAno = rs.getDouble("valor");
            int parcelas = rs.getInt("parcelas");
            boolean administrativa = rs.getBoolean("administrativa");
            a = new Taxa(nome, vigencia, valorAno, parcelas, administrativa);
            array.add(a);
        }
        st.close();
        return array;
    }
}
