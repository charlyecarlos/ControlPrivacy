package services;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail {
	String userMail;
	String password;
	String filePath;
	String fileName;
	String from;
	String subject;
	String message;

	public Mail(String userMail, String password, String filePath, String fileName, String from,
			String subject, String message) {
		this.userMail = userMail;
		this.password = password;
		this.filePath = filePath;
		this.fileName = fileName;
		this.from = from;
		this.subject = subject;
		this.message = message;
	}
	
	public Mail(String userMail, String password, String from, String subject, String message) {
		this.userMail = userMail;
		this.password = password;
		this.from = from;
		this.subject = subject;
		this.message = message;
	}

	public Mail(String userMail, String password, String from, String message) {
		super();
		this.userMail = userMail;
		this.password = password;
		this.from = from;
		this.message = message;
	}

	public boolean sendMailToGmail() {
		try {
			Properties props = new Properties ();
			props.put("mail.smtp.host", "smtp.controlprivacy.net"); 
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", userMail);
			props.setProperty("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props, null);
			BodyPart text = new MimeBodyPart();
			text.setContent(message,"text/html");
			
			BodyPart attachment = new MimeBodyPart();
			if (filePath != null) {
				attachment.setDataHandler(new DataHandler(new FileDataSource(filePath)));
				attachment.setFileName(fileName);
			}
			
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(text);
			if (filePath != null) {
				multiPart.addBodyPart(attachment);
			}
			
			MimeMessage message = new MimeMessage (session);
			message.setFrom(new InternetAddress(userMail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(multiPart);
			
			Transport t = session.getTransport("smtp");
			t.connect(userMail, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendMail() {
		try {
			Properties props = new Properties ();
			props.put("mail.smtp.host", "104.223.26.171");
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", userMail);
			props.setProperty("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props, null);
			BodyPart text = new MimeBodyPart();
			text.setContent(message,"text/html");
			
			BodyPart attachment = new MimeBodyPart();
			if (filePath != null) {
				attachment.setDataHandler(new DataHandler(new FileDataSource(filePath)));
				attachment.setFileName(fileName);
			}
			
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(text);
			if (filePath != null) {
				multiPart.addBodyPart(attachment);
			}
			
			MimeMessage message = new MimeMessage (session);
			message.setFrom(new InternetAddress(userMail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(multiPart);
			
			Transport t = session.getTransport("smtp");
			t.connect(userMail, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean sendHTMLMail() {
		try {
			Properties props = new Properties ();
			props.put("mail.smtp.host", "smtp.gmail.com");  //Sólo acepta servidores de gmail
			props.setProperty("mail.smtp.starttls.enable", "true");
			props.setProperty("mail.smtp.port", "587");
			props.setProperty("mail.smtp.user", userMail);
			props.setProperty("mail.smtp.auth", "true");
			
			Session session = Session.getDefaultInstance(props, null);
			BodyPart text = new MimeBodyPart();
			text.setText(message);
			
			BodyPart attachment = new MimeBodyPart();
			if (filePath != null) {
				attachment.setDataHandler(new DataHandler(new FileDataSource(filePath)));
				attachment.setFileName(fileName);
			}
			
			MimeMultipart multiPart = new MimeMultipart();
			multiPart.addBodyPart(text);
			if (filePath != null) {
				multiPart.addBodyPart(attachment);
			}
			
			MimeMessage message = new MimeMessage (session);
			message.setFrom(new InternetAddress(userMail));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(from));
			message.setSubject(subject);
			message.setContent(multiPart);
			
			Transport t = session.getTransport("smtp");
			t.connect(userMail, password);
			t.sendMessage(message, message.getAllRecipients());
			t.close();
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) {
		String password = "password";
		Mail e = new Mail ("asdf@gmail.com", password, "asdf@gmail.com", "Prueba", "Hola");
		if (e.sendMail()) {
			System.out.println("Enviado");
		} else
			System.out.println("No enviado");
	}

}
