package CommonClasses;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email
{
	public void send(String mailTo, String subject, String body)
	{

		// Need to "Turn on" "Access for less secure apps" of "mailFrom" email
		// account by following given address below
		// https://www.google.com/settings/security/lesssecureapps
		String mailFrom = "rparbez142071@bscse.uiu.ac.bd";
		String mailPassword = "ranadepto";

		// Get the session object
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator()
		{
			protected PasswordAuthentication getPasswordAuthentication()
			{
				return new PasswordAuthentication(mailFrom, mailPassword);
			}
		});

		// Compose Mail
		try
		{
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(mailFrom));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
			message.setSubject(subject);
			message.setText(body);

			// send message
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
		}

	}
}