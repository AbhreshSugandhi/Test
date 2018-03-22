package com.project.webtours.test.utility;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
//import javax.activation.*;
//import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
/*Email Report*/
public class  MailUtility {
	
	public MailUtility()
	{
		
	}

public static void sentemail() 
{

    final String username = "dummyuser396@gmail.com";
    final String password = "Testpassword";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", true);
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
            new javax.mail.Authenticator()
    {
                protected PasswordAuthentication getPasswordAuthentication() 
                {
                 return new PasswordAuthentication(username, password);
                }
            }
    );

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("from.mail.id91@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse("to.mail.dummyuser396@gmail.com"));
        message.setSubject("Test Report");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        String file = System.getProperty("user.dir")+"\\src\\TestResult.pdf";
        String fileName = "TestResult.pdf";
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);
        message.setContent(multipart);
        Transport.send(message);

    } catch (MessagingException e) {
        e.printStackTrace();
    }
}
}