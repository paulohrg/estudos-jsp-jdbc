package model;

public class Userestudo {
	
	private long id;
	private String nome;
	private String email;
	private String login;
	private String senha;
	private String fotoBase64;
	private String tipoArquivo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getFotoBase64() {
		return fotoBase64;
	}
	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}
	public String getTipoArquivo() {
		return tipoArquivo;
	}
	public void setTipoArquivo(String tipoArquivo) {
		this.tipoArquivo = tipoArquivo;
	}
	
	public void extrairTipoArquivo(String arquivo) {
		
		//extrai o tipo de arquivo a partir do arquivo base64
		String tipo = arquivo.split(",")[0].split(";")[0].split("/")[1];
		this.tipoArquivo = tipo;
	}

}
