package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Frequencia;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOFrequencia {
    public void adicionarFrequencia (int codigoAssociado, int numAssociacao, long dataReuniao) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "insert into frequencia (data, associado, associacao) values ("
                + dataReuniao + ", " + codigoAssociado + ", " + numAssociacao + ")";
        Statement st = con.createStatement();
        st.executeUpdate(cmd);
        st.close();
    }

    public Frequencia recuperarFrequenciaEspecifica(int associado, int associacao, long data) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from frequencia where data = "
                + data + " and associado = " +  associado + " and associacao = " +  associacao;
        Frequencia a = null;
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        if (rs.next()) {
            long dataDB = rs.getLong("data");
            int associadoDB = rs.getInt("associado");
            int associacaoDB = rs.getInt("associacao");
            a = new Frequencia(associadoDB, associacaoDB, dataDB);
        }
        st.close();
        return a;
    }

    public ArrayList<Frequencia> recuperarFrequenciaDeUmAssociado(int associacao, int associado) throws SQLException {
        Connection con = Conexao.getConexao();
        String cmd = "select * from frequencia where associado = "
                + associado + " and associacao = " +  associacao;
        Frequencia a = null;
        ArrayList<Frequencia> array = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(cmd);
        while (rs.next()) {
            long dataDB = rs.getLong("data");
            int associadoDB = rs.getInt("associado");
            int associacaoDB = rs.getInt("associacao");
            a = new Frequencia(associadoDB, associacaoDB, dataDB);
            array.add(a);
        }
        st.close();
        return array;
    }
}
