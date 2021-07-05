package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ArquivoUserDao;
import model.ModelArquivoUser;

@WebServlet("principal/estudos/carregarDadosDataTable")
public class CarregarDadosDataTable extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ArquivoUserDao arquivoDao = new ArquivoUserDao();
       
    public CarregarDadosDataTable() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<ModelArquivoUser> listaArquivos = new ArrayList<ModelArquivoUser>();
		String resposta = "";
		HttpSession sessao = request.getSession();
		long user = (long) sessao.getAttribute("usuario");
		
		try {
		listaArquivos = arquivoDao.listarArquivosDoUsuario(user);
		
		if(!listaArquivos.isEmpty()) {
			String dados = "";
			int tamanhoLista = listaArquivos.size();
			int index = 1;
			for (ModelArquivoUser arquivo : listaArquivos) {
				dados += "{"+
						  "\"id\":\"" + arquivo.getId() + "\","+
					      "\"arquivo\":\"" + arquivo.getNomeArquivo() + "\"" +
					      "}";
				
				if(index < tamanhoLista) {
					dados += ",";
				}
				index++;
		
			}
			resposta = "{"+
					  "\"draw\": 1,"+
					  "\"recordsTotal\": " + tamanhoLista + ","+
					  "\"recordsFiltered\": " + tamanhoLista + ","+
					  "\"data\": [" + dados + "]}";
		}
		response.setStatus(200);
		response.getWriter().write(resposta);
		}catch(Exception e) {
			e.printStackTrace();
			response.setStatus(500);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
