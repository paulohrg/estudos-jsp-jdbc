package servlets;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserEstudoDao;
import model.Userestudo;
import services.RelatorioDeUsuarios;

@WebServlet("/principal/estudos/ServiletDownloadRelatorio")
public class ServiletDownloadRelatorio extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private RelatorioDeUsuarios relatorio = new RelatorioDeUsuarios();
	private UserEstudoDao usersDao = new UserEstudoDao();
	private List<Userestudo> listaUsers = new ArrayList<Userestudo>();
	
    public ServiletDownloadRelatorio() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			ServletContext context = request.getServletContext();
			String TipoExport = request.getParameter("tipoExport");
			String nomeRelatorio = "rel_usuarios";
			listaUsers = usersDao.listar();
			
			String fileUrl = relatorio.gerarRelatorio(listaUsers, new HashMap(), nomeRelatorio, nomeRelatorio, context, TipoExport);
			
			File downloadFile = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			
			//Obter o MIME do arquivo
			String mimeType = context.getMimeType(fileUrl);
			if(mimeType == null) {
				//define tipo binario se o mapeamento não for encontrado
				mimeType = "application/octet-stream";
			}
			
			//define atributos para resposta
			response.setContentType(mimeType);
			response.setContentLengthLong(downloadFile.length()); 
			
			//Define cabeçalho para resposta
			String headerValue = String .format("attachment; filename=\"%s\"", downloadFile.getName());
			response.setHeader("Content-Disposition", headerValue);
			
			OutputStream outputStream = response.getOutputStream();
			
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			
			while((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			
			inputStream.close();
			outputStream.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
