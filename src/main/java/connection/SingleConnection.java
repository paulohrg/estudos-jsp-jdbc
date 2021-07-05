package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	private static String banco = "jdbc:postgresql://localhost:5432/EstudosJava?autoReconnect=true";
	private static String user = "postgres";
	private static String senha = "123";
	private static Connection conexao = null;
	
	public static Connection getConexao() {
		return conexao;
	}
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}
	
	private static void conectar() {
		try {
			if(conexao == null) {
				Class.forName("org.postgresql.Driver"); //carrega driver de conexão do banco
				conexao = DriverManager.getConnection(banco, user, senha);
				conexao.setAutoCommit(false);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
