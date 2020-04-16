package cn.ych.tendering.service.imp;

import cn.ych.tendering.service.EmailService;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImp implements EmailService {
    public JavaMailSender emailSender;

    public EmailServiceImp(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }


    @Override
    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setFrom("670989882@qq.com");
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }
}
