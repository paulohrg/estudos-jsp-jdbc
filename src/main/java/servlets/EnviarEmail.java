package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import services.EnvioEmail;

@WebServlet("/principal/estudos/enviarEmail")
public class EnviarEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
    public EnviarEmail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String destinatarios = "paulohrg8@gmail.com";
		String remetente = "Paulo Henrique Gonçalves";
		String assunto = "teste html envio java";
		StringBuilder corpoEmail = new StringBuilder();
		
		corpoEmail.append("Olá. <br/><br/>");
		corpoEmail.append("Você está recebendo esse email de teste de envio de email.<br/>");
		corpoEmail.append("Não se preocupe, para mais informações acesse <a href=\"https://www.devmedia.com.br/"
				+ "a-classe-stringbuilder-em-java/25609\">aqui.</a>");
		
		
		
		EnvioEmail email = new EnvioEmail(destinatarios, remetente, assunto, corpoEmail.toString());
		
		try {
			email.enviarComAnexo(true, request);
			response.sendRedirect("Relatorios.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("not yet, bro!");
	}

}
