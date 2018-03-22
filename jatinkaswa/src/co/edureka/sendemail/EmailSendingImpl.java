package co.edureka.sendemail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSendingImpl {
	
	public EmailSendingImpl() {

	}

	public void sendReport() {

		Properties props = new Properties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "587");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.starttls.enable", "true");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("Enter your email id", "Enter your password");
			}
		});

		try {
			Message message = new MimeMessage(session);

			message.setFrom(new InternetAddress("Enter Sender's email id"));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("Enter receiver's email id"));// need to enter receiver's email
			message.setSubject("Test Report");
			BodyPart messageBodyPartOne = new MimeBodyPart();
			messageBodyPartOne.setText("PFA test report");

			MimeBodyPart messageBodyPartTwo = new MimeBodyPart();
			String filename = System.getProperty("user.dir") + "/test_report.pdf";
			DataSource dataSource = new FileDataSource(filename);
			messageBodyPartTwo.setDataHandler(new DataHandler(dataSource));
			messageBodyPartTwo.setFileName(filename);
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPartTwo);
			multipart.addBodyPart(messageBodyPartOne);
			message.setContent(multipart);
			Transport.send(message);

			System.out.println("******************* Email Successfully Sent ********************");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}