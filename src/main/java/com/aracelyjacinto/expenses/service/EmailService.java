package com.aracelyjacinto.expenses.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

  private static final String TEMPLATE_ID = "d-6084b949f006480f89f89888d6a38a43";
  private final SendGrid sendGrid;

  public EmailService(SendGrid sendGrid) {
    this.sendGrid = sendGrid;
  }

  public void sendEmail(String to, String from, String subject) {
    Email fromEmail = new Email(from);
    Email toEmail = new Email(to);

    Mail mail = new Mail();
    mail.setFrom(fromEmail);
    mail.setSubject(subject);
    mail.setTemplateId(TEMPLATE_ID);

    Personalization personalization = new Personalization();
    personalization.addTo(toEmail);
    mail.addPersonalization(personalization);

    try {
      Request request = new Request();
      request.setMethod(Method.POST);
      request.setEndpoint("mail/send");
      request.setBody(mail.build());
      Response response = sendGrid.api(request);
      System.out.println("Email sent, Status: " + response.getStatusCode());
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

}
