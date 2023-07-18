package com.miriblon.sendemail.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import peyload.EmailPayload;

import javax.mail.internet.MimeMessage;

@Service
public class SendEmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(EmailPayload emailPayload) {
        try {
            System.out.println("start sending...");
            MimeMessage msg = javaMailSender.createMimeMessage();

            // true = multipart message
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);
//            helper.setTo(applicationUser.getUsername());
            helper.setTo(new String[]{"anamaria.rus@miriblon.co.uk"});
//            helper.setTo(new String[]{"malifoli.musique@gmail.com"});

            helper.setSubject(emailPayload.getObject());
//            helper.setSubject("Inscription à l'administration de FCS.");

            // default = text/plain
            //helper.setText("Check attachment for image!");

            helper.setText(emailPayload.getMessage(), true);
            javaMailSender.send(msg);
            System.out.println("end sending...");

        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

    public String mailTemplate(String verificationCode) {
        return "<div style=\"border: 1px grey solid; border-radius: 10px; padding: 20px; text-align: center\">\n" +
                "  <h1 style=\"color: #5e9ca0;'\">CONNEXION À E-CONCOURS</h1>\n" +
                " <img style=\"height: 100px;\" class=\"welcomeImg\" src=\"https://firebasestorage.googleapis.com/v0/b/e-concours.appspot.com/o/15820150.jpg?alt=media&token=578e3e06-044b-42b0-b52f-531bbf448172\" alt=\"\">" +
                "  <div style=\"border-bottom: 1px grey solid\">\n" +
                "    \n" +
                "  </div>\n" +
                "\n" +
                "<p>Votre code de vérification pour l'application E-Concours est le suivant:</p>\n" +
                "<h2 style=\"color: #2e6c80;\">"+verificationCode+"</h2>\n" +
                "<p>Ce code est utilisable une seule fois.  Veuillez à ne pas le partager avec quiconque.</p>\n" +
                "<h2 style=\"color: #2e6c80;\">Quelques fonctionnalités de l'application:</h2>\n" +
                "<ol style=\"list-style: none; font-size: 14px; line-height: 32px; font-weight: bold;\">\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/01-interactive-connection.png\" alt=\"interactive connection\" width=\"45\" />Candidature aux concours nationaux</li>\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/02-html-clean.png\" alt=\"html cleaner\" width=\"45\" /> Reception des résultats par email et par notification du téléphone</li>\n" +
                "<li style=\"clear: both;\"><img style=\"float: left;\" src=\"https://html-online.com/img/03-docs-to-html.png\" alt=\"Word to html\" width=\"45\" /> Paiement des frais de concours par Orange money et Moov Money</li>\n" +
                "</ol>\n" +
                "</div>";
    }

    public String mailTemplatePassword(EmailPayload emailPayload) {
        return "<div style=\"border: 1px grey solid; border-radius: 10px; padding: 20px; text-align: center\">\n" +
                "  <h1 style=\"color: #5e9ca0;'\">"+emailPayload.getObject()+"</h1>\n" +
                " <img style=\"height: 100px;\" class=\"welcomeImg\" src=\"https://firebasestorage.googleapis.com/v0/b/miriblon-6f795.appspot.com/o/logo.png?alt=media&token=b17fe96f-2645-4a83-83df-461b85a6854e\" alt=\"\">" +
                "  <div style=\"border-bottom: 1px grey solid\">\n" +
                "    \n" +
                "  </div>\n" +
                "\n" +
                "<p style=\"font-size: 18px;\">Salut Ana, vous avez reçu un message de la part de cette adresse:</p>\n" +
                "<h2 style=\"color: #2e6c80;\">"+emailPayload.getEmail()+"</h2>\n" +
                "<p style=\"font-size: 18px;\">Voici le contenu du message:e</p>\n" +
                "<p style=\"font-size: 18px;\"> "+emailPayload.getMessage()+" </p>" +
                "</div>";
    }

}
