package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.TelefoneUser;
import model.Userestudo;

public class UserEstudoDao {
	private Connection connection;
	
	public UserEstudoDao() {
		// TODO Auto-generated constructor stub
		connection = SingleConnection.getConexao();
	}
	
	public void salvarUsuario(Userestudo userEstudo) {
		
		try {
			
			String sql = "insert into usersestudo (nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userEstudo.getNome());
			insert.setString(2, userEstudo.getEmail());
			insert.execute();
			connection.commit();
			
		} catch (Exception e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void SalvarTelefone(TelefoneUser telefone) {
		
		String sql = "insert into telefoneuser (numero, tipo, id_user) values (?, ?, ?);";
		PreparedStatement prepSql;
		try {
			prepSql = connection.prepareStatement(sql);
			prepSql.setString(1, telefone.getNumero());
			prepSql.setLong(2, telefone.getTipo());
			prepSql.setLong(3, telefone.getId_user());
			prepSql.execute();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
			
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
		
	}
	
	public List<Userestudo> listar() throws Exception {
		
		ArrayList<Userestudo> lista = new ArrayList<Userestudo>();
		
		String sql = "select * from usersestudo";
		PreparedStatement listaUsers = connection.prepareStatement(sql);
		
		ResultSet resultados = listaUsers.executeQuery();
		
		while(resultados.next()){
			
			Userestudo user = new Userestudo();
			
			user.setId(resultados.getLong("id"));
			user.setNome(resultados.getString("nome"));
			user.setEmail(resultados.getString("email"));
			user.setFotoBase64(resultados.getString("arquivo"));
			user.setTipoArquivo(resultados.getString("tipoarquivo"));
			user.setLogin(resultados.getString("login"));
			lista.add(user);
			
		}
		
		return lista;
		
	}
	
	public Userestudo buscaUserPorId(Long id) throws Exception {
		
		Userestudo user = new Userestudo();
		
		String sql = "select * from usersestudo where id = " + id;
		PreparedStatement usuario = connection.prepareStatement(sql);
		ResultSet resultado = usuario.executeQuery();
		
		resultado.next();
		
		user.setId(resultado.getInt("id"));
		user.setNome(resultado.getString("nome"));
		user.setEmail(resultado.getString("email"));
		
		return user;
	}
	
	public Userestudo buscaUserPorLogin(String login) throws Exception {
		
		Userestudo user = new Userestudo();
		
		String sql = "select * from usersestudo where login = '" + login + "'";
		PreparedStatement usuario = connection.prepareStatement(sql);
		ResultSet resultado = usuario.executeQuery();
		
		resultado.next();
		
		user.setId(resultado.getInt("id"));
		user.setNome(resultado.getString("nome"));
		user.setEmail(resultado.getString("email"));
		user.setSenha(resultado.getString("senha"));
		return user;
	}
	
	public void atualizarEmailDoUsuario(Userestudo usuario) throws Exception {
		
		String sql = "update usersestudo set email = ? where id = ?";
		PreparedStatement user = connection.prepareStatement(sql);
		user.setString(1, usuario.getEmail());
		user.setLong(2, usuario.getId());
		user.execute();
		connection.commit();
	}
	
	public void removeUsuario(long usuario) throws Exception {
		
		String sql = "delete from public.usersestudo where id = " + usuario;
		PreparedStatement prepExec = connection.prepareStatement(sql);
		prepExec.execute();
		connection.commit();
	}
	
	public void atualizaFoto(Userestudo usuario) throws Exception {
		
		String sql = "update usersestudo set arquivo = ?, tipoarquivo=? where id = ?";
		PreparedStatement prepExec = connection.prepareStatement(sql);
		prepExec.setString(1, usuario.getFotoBase64());
		prepExec.setString(2, usuario.getTipoArquivo());
		prepExec.setLong(3, usuario.getId());
		prepExec.execute();
		connection.commit();
	}
	
	public String buscaFotoParaDownload(long idUser) throws Exception {
		String arquivo = null;			
		String sql = "Select arquivo from usersestudo where id = " + idUser;
		PreparedStatement prepExec = connection.prepareStatement(sql);
		
		ResultSet resultado = prepExec.executeQuery();
		
		while (resultado.next()) {
			arquivo = resultado.getString("arquivo");
		}
		
		return arquivo;
	}
}
