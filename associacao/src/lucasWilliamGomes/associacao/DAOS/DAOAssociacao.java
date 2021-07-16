package lucasWilliamGomes.associacao.DAOS;

import lucasWilliamGomes.associacao.models.Associacao;
import lucasWilliamGomes.associacao.models.Associado;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DAOAssociacao {
	public void inserir(Associacao a) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "insert into associacao (numero, nome) values ("
		     + a.getNum() + ", \'" + a.getNome() + "\')";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}

	public void removerTodos() throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "delete from associacao";
		Statement st = con.createStatement();
	    st.executeUpdate(cmd);
	    st.close();
	}

	public Associacao recuperar(int n) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from associacao where numero = " + n;
		Associacao a = null;
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(cmd);
	    if (rs.next()) {
	    	int num  = rs.getInt("numero");
	    	String nome = rs.getString("nome");
	    	a = new Associacao(num, nome);
	    }
	    st.close();
	    return a;
	}

	public ArrayList<Associado> recuperarAssociadosDeUmaAssociacao(int numero) throws SQLException {
		Connection con = Conexao.getConexao();
		String cmd = "select * from associado where associacao = " + numero;
		Associado a = null;
		ArrayList<Associado> array = new ArrayList<>();
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(cmd);
		while (rs.next()) {
			int num  = rs.getInt("numero");
			String nome = rs.getString("nome");
			String telefone = rs.getString("telefone");
			long dataAssociacao = rs.getLong("dataAssociacao");
			long nascimento = rs.getLong("nascimento");
			int associacao = rs.getInt("associacao");
			a = new Associado(num, nome, telefone, dataAssociacao, nascimento);
			array.add(a);
		}
		st.close();
		return array;
	}
}
