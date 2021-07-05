package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connection.SingleConnection;
import model.ModelArquivoUser;

public class ArquivoUserDao {

	private Connection connection;
	
	public ArquivoUserDao() {

		connection = SingleConnection.getConexao();
	}
	
	public void salvaAruqivo(ModelArquivoUser arquivo) throws Exception {
		
		String sql = "insert into arquivosuser (nomearquivo, arquivo, iduser, tipoarquivo) values (?,?,?,?)";
		PreparedStatement prepExec = connection.prepareStatement(sql);
		prepExec.setString(1, arquivo.getNomeArquivo());
		prepExec.setString(2, arquivo.getArquivo());
		prepExec.setLong(3, arquivo.getIdUser());
		prepExec.setString(4, arquivo.getTipoArquivo());
		
		System.out.println(prepExec.toString());
		
		prepExec.execute();
		connection.commit();
	}

	public List<ModelArquivoUser> listarArquivosDoUsuario(long idUser) throws Exception {
		List<ModelArquivoUser> listaArquivos = new ArrayList<ModelArquivoUser>();
				
		String sql = "Select * from arquivosuser where iduser = " + idUser;
		PreparedStatement prepExec = connection.prepareStatement(sql);
		
		ResultSet resultado = prepExec.executeQuery();
		
		while (resultado.next()) {
			ModelArquivoUser arquivo = new ModelArquivoUser();
			
			arquivo.setId(resultado.getLong("id"));
			arquivo.setNomeArquivo(resultado.getString("nomearquivo"));
			arquivo.setArquivo(resultado.getString("arquivo"));
			arquivo.setIdUser(resultado.getLong("iduser"));
			arquivo.setTipoArquivo(resultado.getString("tipoarquivo"));
			
			listaArquivos.add(arquivo);
		}
		
		return listaArquivos;
	}
	public String buscaArquivoParaDownload(long idArquivo) throws Exception {
		String arquivo = null;			
		String sql = "Select arquivo from arquivosuser where id = " + idArquivo;
		PreparedStatement prepExec = connection.prepareStatement(sql);
		
		ResultSet resultado = prepExec.executeQuery();
		
		while (resultado.next()) {
			arquivo = resultado.getString("arquivo");
		}
		
		return arquivo;
	}
}
