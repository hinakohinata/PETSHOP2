package com.learncode.Service;

import javax.mail.MessagingException;


public interface MailService {

    // boolean sendEmail(String to, String subject, String content);

    void sendAsHtml(String to, String title, String html) throws MessagingException;
    void sendAsHtml(String to, String title, String subject,String content) throws MessagingException;
    void queue(com.learncode.Entity.MailInfo mail);
    void queue(String to, String subject, String body);
    void sendAsHtml(String to, String title, String subject,String content,String cc, String bcc) throws MessagingException;
}
