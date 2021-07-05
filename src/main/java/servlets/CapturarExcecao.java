package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/principal/estudos/capturarExcecao")
public class CapturarExcecao extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public CapturarExcecao() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		try {
			int valor = Integer.parseInt(request.getParameter("valorParam"));
			System.out.println(valor);
			response.setStatus(200);
		}catch(Exception e) {
			response.setStatus(500);
			response.getWriter().write("Erro ao processar: " + e.getMessage());	
		}
		
	}

}
