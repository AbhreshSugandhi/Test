package helper;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmailReport {
	
	public SendEmailReport() {
		  System.out.println("Empty constructor.");
	}

  /**
 * This method is for send the report in a mail.
 * @param from send from mail id
 * @param pwd send from mail password
 * @param to send to mail
 * @param sub mail subject
 * @param body mail body
 */
  public static void sendPdfReport(String from, String pwd, String to, String sub, String body) {

    Properties mailProp = System.getProperties();

    String host = "smtp.gmail.com";

    mailProp.put("mail.smtp.starttls.enable", "true");

    mailProp.put("mail.smtp.host", host);

    mailProp.put("mail.smtp.user", from);

    mailProp.put("mail.smtp.password", pwd);

    mailProp.put("mail.smtp.port", "465");

    mailProp.put("mail.smtp.auth", "true");

    Session session = Session.getDefaultInstance(mailProp);

    MimeMessage message = new MimeMessage(session);

    try {

      message.setFrom(new InternetAddress(from));

      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

      message.setSubject(sub);

      message.setText(body);

      BodyPart objMessageBodyPart = new MimeBodyPart();

      objMessageBodyPart.setText("Please find the attached test results report.");

      Multipart multipart = new MimeMultipart();

      multipart.addBodyPart(objMessageBodyPart);

      objMessageBodyPart = new MimeBodyPart();

      String filename = System.getProperty("user.dir");

      DataSource source = new FileDataSource(filename);

      objMessageBodyPart.setDataHandler(new DataHandler(source));

      objMessageBodyPart.setFileName(filename);

      multipart.addBodyPart(objMessageBodyPart);

      message.setContent(multipart);

      Transport transport = session.getTransport("smtp");

      transport.connect(host, from, pwd);

      transport.sendMessage(message, message.getAllRecipients());

      transport.close();
    } catch (AddressException addrExcep) {

      addrExcep.printStackTrace();
    } catch (MessagingException msgExcep) {

      msgExcep.printStackTrace();

    }
  }

}
