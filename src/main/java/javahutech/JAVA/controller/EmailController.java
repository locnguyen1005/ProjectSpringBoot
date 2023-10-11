package javahutech.JAVA.controller;

import javahutech.JAVA.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    @RequestMapping("/")
    public String showEmailForm() {
        return "email/emailsender";
    }

    @GetMapping("/send-email") // Add this mapping for GET method
    public String showEmailFormForGet() {
        return "email/emailsender";
    }

    @PostMapping("/send-email")
    public String sendEmail(String to, String subject, String body, String name, String phone) {
        emailService.sendEmail(to, subject, body,phone,name);
        return "home/index";
    }
}