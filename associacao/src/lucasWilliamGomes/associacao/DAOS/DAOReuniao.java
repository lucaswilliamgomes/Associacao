package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Reuniao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOReuniao {
    public void adicionarReuniao (Reuniao a, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "insert into reuniao (pauta, data, associacao) values ("
                + "\'" + a.getAta() + "\' ," + a.getData() + ", " + associacao + ")";
        System.out.println(cmd);
        Statement st = con.createStatement();
        st.executeUpdate(cmd);
        st.close();
    }

    public Reuniao recuperar(long dataReuniao, int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from reuniao where"
                + " data = " + dataReuniao + " and associacao = " +  associacao;
        Reuniao a = null;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        if (rs.next()) {
            String pauta  = rs.getString("pauta");
            Long data = rs.getLong("data");
            a = new Reuniao(data, pauta);
        }
        st.close();
        return a;
    }

    public ArrayList<Reuniao> recuperarReunioesDeUmaAssociacao(int associacao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from reuniao where" + " associacao = " +  associacao;
        Reuniao a = null;
        ArrayList<Reuniao> array = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        while (rs.next()) {
            String pauta  = rs.getString("pauta");
            Long data = rs.getLong("data");
            int associacaoDB = rs.getInt("associacao");
            a = new Reuniao(data, pauta);
            array.add(a);
        }
        st.close();
        return array;
    }
}
