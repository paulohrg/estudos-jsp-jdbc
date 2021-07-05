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

import dao.UserEstudoDao;
import model.Userestudo;

@WebServlet("/principal/estudos/UploadImage")
public class UploadImage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserEstudoDao dao = new UserEstudoDao();
       
    public UploadImage() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		try {
			String acao = request.getParameter("acao");
			
			if(acao.equalsIgnoreCase("carregar")) {
				
				RequestDispatcher retornoTelaUpload = request.getRequestDispatcher("/principal/estudos/upload-imagem.jsp");
				request.setAttribute("ListaUserImagens", dao.listar());
				retornoTelaUpload.forward(request, response);
			}else if(acao.equalsIgnoreCase("download")){
				String tipoArquivo =  request.getParameter("tipoArquivo");
				long idUser = Long.parseLong(request.getParameter("idUser"));
				String foto = dao.buscaFotoParaDownload(idUser);
				
				if(foto != null && !foto.equals("")) {
					
					response.setHeader("Content-Disposition", "attachment;filename=imagem." + tipoArquivo);
					
					//extrai imagem da base64
					String imagemReal = foto.split(",")[1];
					
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
		
		Userestudo usuario = new Userestudo();
		HttpSession sessao = request.getSession();
		try {
			usuario.setId((long)sessao.getAttribute("usuario"));
			String arquivo = request.getParameter("fileUpload");
			usuario.setFotoBase64(arquivo);
			usuario.extrairTipoArquivo(arquivo);
			
			dao.atualizaFoto(usuario);
			
			response.getWriter().write("Imagem salva com sucesso!");
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		
		
		
	}

}
