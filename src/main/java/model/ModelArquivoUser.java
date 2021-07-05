package model;

public class ModelArquivoUser {
	private long id;
	private String nomeArquivo;
	private String arquivo;
	private long idUser;
	private String tipoArquivo;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeArquivo() {
		return nomeArquivo;
	}
	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}
	public String getArquivo() {
		return arquivo;
	}
	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	public long getIdUser() {
		return idUser;
	}
	public void setIdUser(long idUser) {
		this.idUser = idUser;
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
