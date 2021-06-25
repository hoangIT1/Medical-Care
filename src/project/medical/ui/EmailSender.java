package project.medical.ui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

import project.medical.core.Event;

public class EmailSender {
	private String sender ;
    private String password; 
    private String host;
    private Session session;
    Properties properties = System.getProperties();
    private List<String> emailToday;
    private List<String> emailTomorrow;
    private String messToday;
    private String messTomorrow;
    private String messEvent;
    private String header;

    public EmailSender() throws FileNotFoundException, IOException {
		Properties prop = new Properties();
		prop.load(new FileInputStream("sql/email.properties"));
		sender = prop.getProperty("sender");
		password = prop.getProperty("password");
		host = prop.getProperty("host");

 
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");
    	session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(sender, password);

            }

        });
    	header = "Reminder from healthy care center";
    	messToday = " Hi friends, You have an appointment at our center today!";
    	messTomorrow = " Hi friends, You have an appointment at our center tomorrow"; 
    }

    public EmailSender(List<String> thelisttoday, List<String> thelisttomorrow) throws FileNotFoundException, IOException {
    	this();
    	emailToday = thelisttoday;
    	emailTomorrow = thelisttomorrow;
    }
    // Sending email today
    public void sendToday() {
    	JOptionPane.showMessageDialog(null,"Sending today ...");
    	for (String receiver : emailToday) {
    		try {
    	        // Create a default MimeMessage object.
    	        MimeMessage message = new MimeMessage(session);
    	        // Set From: header field of the header.
    	        message.setFrom(new InternetAddress(sender));
    	        // Set To: header field of the header.
    	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
    	        // Set Subject: header field
    	        message.setSubject(header);

    	        // Now set the actual message 
    	        message.setContent(messToday,"text/html");
    	      
    	       
    	        // Send message
    	        Transport.send(message);
    	     
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	    }
    		
    	}
    	JOptionPane.showMessageDialog(null,"Sent email successfully");
    }
    // Sending email tomorrow
  
    public void sendTomorrow() {
    	JOptionPane.showMessageDialog(null,"Sending tomorrow...");
    	for (String receiver : emailTomorrow) {
    		try {
    	        // Create a default MimeMessage object.
    	        MimeMessage message = new MimeMessage(session);
    	        // Set From: header field of the header.
    	        message.setFrom(new InternetAddress(sender));
    	        // Set To: header field of the header.
    	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
    	        // Set Subject: header field
    	        message.setSubject(header);

    	        // Now set the actual message 
    	        message.setContent(messTomorrow,"text/html");
    	     
    	        // Send message
    	        Transport.send(message);

    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Error");
    	    } 	
    	}
    	JOptionPane.showMessageDialog(null,"Sent email successfully");
    }
    // Sending email event
    public void sendEvent(Event event, List<String> emails) {
    	messEvent = " Hi friends, Our center is going to hold an event. This Event: " + event.getName() + 
    			", Date: "+ event.getDate() + ", Description: "+ event.getDescription()+ 
    			". Contact with us to get more details. Have a nice day !"; 
    	int i=0;
    	JOptionPane.showMessageDialog(null,"Sending...");
    	for (String receiver : emails) {
    		i++;
    		if(i == 2) break;
    		try {
    	        // Create a default MimeMessage object.
    	        MimeMessage message = new MimeMessage(session);
    	        // Set From: header field of the header.
    	        message.setFrom(new InternetAddress(sender));
    	        // Set To: header field of the header.
    	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(receiver));
    	        // Set Subject: header field
    	        message.setSubject(header);

    	        // Now set the actual message 
    	        message.setContent(messEvent,"text/html");
    	        
    	        // Send message
    	        Transport.send(message);
    	    } catch (MessagingException mex) {
    	        mex.printStackTrace();
    	        JOptionPane.showMessageDialog(null, "Error");
    	    }
    		
    	}
    	JOptionPane.showMessageDialog(null," Sent email successfully");
    	
    }
    	
 
	

}
