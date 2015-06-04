/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.asociate.utils;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

/**
 *
 * @author Ventura
 */
public class Email {

    public static String username = "vepreciado@gmail.com";
    public static String password = "gnidrqhhibztctfb";

    public static void SendMail(String mensage, String to, String subject) {

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject(subject);
            message.setContent(mensage, "text/html");
            //        message.setText(mensage);

            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getMensajeDePlantilla(int tipo, String[] params) {
        
        StringBuffer sb = new StringBuffer();
        sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"es\" lang=\"es\">\n"
                + "<body>\n"
                + "\n"
                + "<h1>Asociate</h1>\n"
                + "<br>\n"
                + "<br>\n"
        );

        switch (tipo) {
            case 1://registro
                sb.append("<p>Gracias por registrarte en ASOCIATE,"+params[0]+", pronto podrás acceder con el usuario con el que te has registrado</p>");
                break;
            case 2://alta asociacion
                sb.append("<p>Gracias por registrarte en ASOCIATE, pronto un administrador dará de alta tu asociación ("+params[0]+") y podras descubrir las posibilidades que te ofrecemos.</p>");
                break;
            case 3://
                break;

        }
        sb.append("<br/>\n"
                + "<br/>\n"
                + "\n"
                + "<a href='#'>ASOCIATE.</a>\n"
                + "\n"
                + "</body>\n"
                + "</html>");
        return sb.toString();

    }

}
