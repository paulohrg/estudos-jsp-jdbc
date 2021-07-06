package email;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import services.EnvioEmail;

public class AppTest {

	@Test
	public void testeEmailCorpoSimples() throws Exception{
			
			String destinatarios = "paulohrg8@gmail.com";
			String remetente = "Paulo Henrique Gonçalves";
			String assunto = "teste html envio java";
			String corpo = "Deu certo, seu primeiro envio foi feito com sucesso! \n aaa";
			
			EnvioEmail email = new EnvioEmail(destinatarios, remetente, assunto, corpo);
			
			email.enviarSemAnexo(false);
		
	}
	
	@Test
	public void testeEmailCorpoHtml() throws Exception{
			
			String destinatarios = "paulohrg8@gmail.com";
			String remetente = "Paulo Henrique Gonçalves";
			String assunto = "teste html envio java";
			StringBuilder corpoEmail = new StringBuilder();
			
			corpoEmail.append("Olá. <br/><br/>");
			corpoEmail.append("Você está recebendo esse email de teste de envio de email.<br/>");
			corpoEmail.append("Não se preocupe, para mais informações acesse <a href=\"https://www.devmedia.com.br/"
					+ "a-classe-stringbuilder-em-java/25609\">aqui.</a>");
			
			
			
			EnvioEmail email = new EnvioEmail(destinatarios, remetente, assunto, corpoEmail.toString());
			
			email.enviarSemAnexo(true);
		
	}
}
