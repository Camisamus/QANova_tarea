package Utils;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class SendMail {
    public static void enviar(String[] mensaje, String destinatario){
        final String username = ReadProperties.readFromConfig("Propiedades.properties").getProperty("UserMail");
        final String password = ReadProperties.readFromConfig("Propiedades.properties").getProperty("passMail");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            // Define message
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setSubject("asunto");
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(destinatario));
            String mess ="saludos "+ destinatario + "/nl";
            for (int i=0; i< mensaje.length;i++){
                mess = mess + mensaje[i]+"/nl";
            }
            message.setText(mess);
            // Envia el mensaje
            Transport.send(message);
        } catch (Exception e) {
        }
    }
}