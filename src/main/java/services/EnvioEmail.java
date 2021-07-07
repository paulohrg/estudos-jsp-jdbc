package services;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import dao.UserEstudoDao;
import model.Userestudo;

public class EnvioEmail {
	private String username = "paulotestesistemas@gmail.com";
	private String senha = "";
	
	private String destinatarios = "";
	private String nomeRemetente = "";
	private String assuntoEmail = "";
	private String corpoEmail = "";
	
	public EnvioEmail(String destinatarios, String nomeRemetente, String assuntoEmail, String corpoEmail) {
		// TODO Auto-generated constructor stub
		this.destinatarios = destinatarios;
		this.nomeRemetente = nomeRemetente;
		this.assuntoEmail = assuntoEmail;
		this.corpoEmail = corpoEmail;
	}
	
	public void enviarSemAnexo(Boolean ehCorpoHtml) throws Exception {
		Properties properties =  new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); //autorização.
		properties.put("mail.smtp.starttls", "true");//autenticação.
		properties.put("mail.smtp.host", "smtp.gmail.com");//servidor gmail google.
		properties.put("mail.smtp.port", "465");//porta padrão do servidor gmail.
		properties.put("mail.smtp.socketFactory.port", "465");//especifica a porta a ser conectada pelo socket.
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //classe socket de conexao ao SMTP.
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, senha);
			}
		});
		
		Address[] toUser = InternetAddress.parse(destinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		if(ehCorpoHtml) {
			message.setContent(corpoEmail, "text/html; charset=utf-8");
		}else {
			message.setText(corpoEmail);
		}
		Transport.send(message);
	}
	
	public void enviarComAnexo(Boolean ehCorpoHtml, HttpServletRequest request) throws Exception {
		Properties properties =  new Properties();
		
		properties.put("mail.smtp.ssl.trust", "*");
		properties.put("mail.smtp.auth", "true"); //autorização.
		properties.put("mail.smtp.starttls", "true");//autenticação.
		properties.put("mail.smtp.host", "smtp.gmail.com");//servidor gmail google.
		properties.put("mail.smtp.port", "465");//porta padrão do servidor gmail.
		properties.put("mail.smtp.socketFactory.port", "465");//especifica a porta a ser conectada pelo socket.
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //classe socket de conexao ao SMTP.
		
		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, senha);
			}
		});
		
		Address[] toUser = InternetAddress.parse(destinatarios);
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(username, nomeRemetente));
		message.setRecipients(Message.RecipientType.TO, toUser);
		message.setSubject(assuntoEmail);
		
		MimeBodyPart corpo= new MimeBodyPart();
		
		if(ehCorpoHtml) {
			corpo.setContent(corpoEmail, "text/html; charset=utf-8");
		}else {
			corpo.setText(corpoEmail);
		}
		
		MimeBodyPart anexo = new MimeBodyPart();
		anexo.setDataHandler(new DataHandler(new ByteArrayDataSource(gerarAnexoPDF(request), "application/pdf")));
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(corpo);
		multipart.addBodyPart(anexo);
		
		message.setContent(multipart);
		Transport.send(message);
	}
	
	public FileInputStream gerarAnexoPDF(HttpServletRequest request){
		RelatorioDeUsuarios relatorio = new RelatorioDeUsuarios();
		UserEstudoDao usersDao = new UserEstudoDao();
		List<Userestudo> listaUsers = new ArrayList<Userestudo>();
		
		try {
			ServletContext context = request.getServletContext();
			String TipoExport = request.getParameter("tipoExport");
			String nomeRelatorio = "rel_usuarios";
			listaUsers = usersDao.listar();
			
			String fileUrl = relatorio.gerarRelatorio(listaUsers, new HashMap(), nomeRelatorio, nomeRelatorio, context, TipoExport);
			
			File downloadFile = new File(fileUrl);
			FileInputStream inputStream = new FileInputStream(downloadFile);
			return inputStream;
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
}
