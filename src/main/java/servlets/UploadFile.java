package servlets;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.codec.binary.Base64;

import dao.ArquivoUserDao;
import model.ModelArquivoUser;


@WebServlet("/principal/estudos/UploadFile")
public class UploadFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ArquivoUserDao arquivoDao = new ArquivoUserDao();
       
    public UploadFile() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sessao = request.getSession();
		long idUser = (long) sessao.getAttribute("usuario");
		
		try {
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {

				RequestDispatcher retornoTelaUpload = request.getRequestDispatcher("upload-arquivo.jsp");
				request.setAttribute("listaArquivosUsuario", arquivoDao.listarArquivosDoUsuario(idUser));
				retornoTelaUpload.forward(request, response);
			}else if(acao.equalsIgnoreCase("download")){
				
				String nomeArquivo =  request.getParameter("nomeArquivo");
				long idArquivo = Long.parseLong(request.getParameter("idArquivo"));
				String arquivo = arquivoDao.buscaArquivoParaDownload(idArquivo);
				
				if(arquivo != null) {
					
					response.setHeader("Content-Disposition", "attachment;filename="+nomeArquivo);
					
					//extrai imagem da base64
					String imagemReal = arquivo.split(",")[1];
					
					byte [] imageBytes = new Base64().decodeBase64(imagemReal);
					
					//objeto de entrada para processar
					InputStream is = new ByteArrayInputStream(imageBytes);
					
					//escreve dados de resposta
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					
					while((read = is.read(bytes)) != -1) {
						
						os.write(bytes, 0, read);
					}
					
					os.flush();os.close();
				}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ModelArquivoUser file = new ModelArquivoUser();
		HttpSession sessao = request.getSession();
		long idUser = (Long) sessao.getAttribute("usuario");
		String arquivo = request.getParameter("fileUpload");
		String nomearquivo = request.getParameter("fileName");
		
		try {
			if(arquivo != null && !arquivo.equals("")) {
				file.setIdUser(idUser);
				file.setArquivo(arquivo);
				file.setNomeArquivo(nomearquivo);
				file.extrairTipoArquivo(arquivo);
				
				arquivoDao.salvaAruqivo(file);
				
				response.getWriter().write("arquivo salvo com sucesso!");
			}else {
				response.getWriter().write("Adicione um arquivo!");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
