package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserEstudoDao;
import model.ModelLogin;
import model.Userestudo;

@WebServlet(urlPatterns = {"/ServletLogin", "/principal/ServletLogin", "/principal/estudos/ServletLogin" })
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserEstudoDao userDao = new UserEstudoDao();
	private Userestudo usuario;
    
    public ServletLogin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpServletRequest requisicao = (HttpServletRequest) request;
		HttpSession sessao = requisicao.getSession();
		
		if(Boolean.parseBoolean(request.getParameter("deslogar"))) {
			sessao.invalidate();
			response.sendRedirect("index.jsp");
		}else {
			if(sessao.getAttribute("usuario") == null || (long) sessao.getAttribute("usuario") == 0L){
				doPost(request, response);
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/principal/dashboard.jsp");
				redirecionar.forward(request, response);
				return;
			}
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String url = request.getParameter("url");
		
		try {
			if(login != null && !login.isEmpty() && senha != null && !senha.isEmpty()) {
				ModelLogin loginInformado = new ModelLogin(login, senha);
				
				boolean ehLoginValido = validarLogin(loginInformado);
				
				if(ehLoginValido) {
					request.getSession().setAttribute("usuario", usuario.getId());
					
					if(url == null || url.equals("null")) {
						url = "/principal/dashboard.jsp";
					}
					
					RequestDispatcher redirecionar = request.getRequestDispatcher(url);
					redirecionar.forward(request, response);
				}else {
					RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
					request.setAttribute("msg", "Login e/ou senha invalidos.");
					redirecionar.forward(request, response);
				}
			}else {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp");
				redirecionar.forward(request, response);
			}
		}catch (Exception e) {
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
		}
		
	}

	private boolean validarLogin(ModelLogin modellogin) {
		
		try {
			usuario = userDao.buscaUserPorLogin(modellogin.getLogin());
			
			if(usuario.getSenha().equals(modellogin.getSenha())){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return false;
	}

}
