package filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import connection.SingleConnection;

@WebFilter(urlPatterns = {"/principal/*"})
public class FilterAutenticacao implements Filter {
    
	private static Connection conexao;
	
    public FilterAutenticacao() {
        
    }
    
    /*encerra os processo quando servidor é parado*/
    //encerra conexao com banco de dados
	public void destroy() {
		try {
			conexao.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		try {
			HttpServletRequest requisicao = (HttpServletRequest) request;
			HttpSession sessao = requisicao.getSession();
			String urlParaAutenticar = requisicao.getServletPath();
			
			if(sessao.getAttribute("usuario") == null && !urlParaAutenticar.contains("/ServletLogin")) {
				RequestDispatcher redirecionar = request.getRequestDispatcher("/index.jsp?url=" + urlParaAutenticar);
				redirecionar.forward(request, response);
				return;
			}else {
				chain.doFilter(request, response);
			}
			
			conexao.commit();
			
		} catch (Exception e) {
			
			e.printStackTrace();
			RequestDispatcher redirecionar = request.getRequestDispatcher("/erro.jsp");
			request.setAttribute("msg", e.getMessage());
			redirecionar.forward(request, response);
			
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
			
		
	}

	/*inicia processos e recursos quando servidor é startado*/
	//inicia conexao com banco de dados
	public void init(FilterConfig fConfig) throws ServletException {
		conexao = SingleConnection.getConexao();
	}

}
