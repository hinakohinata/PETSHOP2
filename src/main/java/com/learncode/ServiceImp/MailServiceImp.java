package com.learncode.ServiceImp;

import java.util.ArrayList;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.learncode.Entity.MailInfo;
import com.learncode.Service.MailService;


@Service
public class MailServiceImp implements MailService {

    // @Autowired
    // private JavaMailSender javaMailSender;

    // public boolean sendEmail(String to, String subject, String content) {
    //     try {
    //         SimpleMailMessage msg = new SimpleMailMessage();
    //         MimeMessage mimeMessage = javaMailSender.createMimeMessage();
    //         MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

    //         helper.setSubject(subject);
    //         helper.setFrom("tabletkindfire@gmail.com");
    //         helper.setTo(to);

    //         boolean html = true;
    //         helper.setText(content, html);

    //         javaMailSender.send(msg);
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         System.err.println("error send mail " + e.getMessage());
    //         e.printStackTrace();
    //         return false;
    //     }
    //     return true;
    // }

    // custom sendmail

    private static final String senderEmail = "lamhtpk02207@fpt.edu.vn";// change with your sender email
    private static final String senderPassword = "ghhgjgjczaejduxv";// change with your sender password

    public void sendAsHtml(String to, String title, String html) throws MessagingException {
        System.out.println("Sending email to " + to);

        Session session = createSession();

        // create message using session
        MimeMessage message = new MimeMessage(session);
        prepareEmailMessage(message, to, title, html);

        // sending message
        Transport.send(message);
        System.out.println("Done");
    }

    public void sendAsHtml(String to, String title, String subject,String content) throws MessagingException{
    	System.out.println("Sending email to " + to);

        Session session = createSession();

        // create message using session
        MimeMessage message = new MimeMessage(session);
//        prepareEmailMessage(message, to, title);

        // sending message
        Transport.send(message);
        System.out.println("Done");
    }
    private static void prepareEmailMessage(MimeMessage message, String to, String title, String html)
            throws MessagingException {
        message.setContent(html, "text/html; charset=utf-8");
        message.setFrom(new InternetAddress(senderEmail));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        message.setSubject(title);
    }

    private static Session createSession() {
        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        // Đăng nhập vào email
        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(senderEmail, senderPassword);
                    }
                });

        return session;
    }
    ArrayList<MailInfo> list = new ArrayList<>();

	@Override
	public void queue(MailInfo mail) {
		// TODO Auto-generated method stub
		list.add(mail);
	}

	@Override
	public void queue(String to, String subject, String body) {
		// TODO Auto-generated method stub
		 queue(new MailInfo(to, subject, body));
	}
	
	@Autowired
    private JavaMailSender mailSender;

    @Scheduled(fixedDelay = 5000)
    public void run() {
        while (!list.isEmpty()) {
            MailInfo mail = list.remove(0);
            try {
                send(mail);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void send(MailInfo mail) throws MessagingException {
        // Tạo MimeMessage và cấu hình nội dung email
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(mail.getTo());
        helper.setSubject(mail.getSubject());
        helper.setText(mail.getBody(), true);

        // Gửi email
        mailSender.send(message);
    }

	@Override
	public void sendAsHtml(String to, String title, String subject, String content, String cc, String bcc)
			throws MessagingException {
		// TODO Auto-generated method stub
		
	}
}
