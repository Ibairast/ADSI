package packModelo;


import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * Clase obtenida de la web:
 * <p>
 * https://www.journaldev.com/2532/javamail-example-send-mail-in-java-smtp
 * <p>
 * Autor: About Pankaj
 */
public class EmailUtil {

    /**
     * Utility method to send simple HTML email
     */
    public static boolean sendEmail(Session session, String toEmail, String subject, String body) {
        try {
            MimeMessage msg = new MimeMessage(session);
            //set message headers
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
            msg.addHeader("format", "flowed");
            msg.addHeader("Content-Transfer-Encoding", "8bit");

            msg.setFrom(new InternetAddress("no_reply@barbes.upv", "info-BarBes"));

            msg.setReplyTo(InternetAddress.parse("no_reply@barbes.upv", false));

            msg.setSubject(subject, "UTF-8");

            msg.setText(body, "UTF-8");

            msg.setSentDate(new Date());

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
            //System.out.println("Message is ready");
            Transport.send(msg);

            //System.out.println("EMail Sent Successfully!!");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
