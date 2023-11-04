package com.qn.qiniudemoapi.service.impl;


import com.qn.qiniudemoapi.service.EmailService;
import com.qn.qiniudemoapi.util.VerCodeGenerateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.from}")
    private String from;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 邮件发送
     * @param email email
     * @return 结果
     */
    @Override
    public boolean sendSimpleCode(String email) {
        boolean flag = false;
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(email);
            String code = VerCodeGenerateUtil.generateVerCode();
            System.out.println("code:"+code);
            redisTemplate.opsForValue().set(email,code,60, TimeUnit.SECONDS);
            mail.setText(code);
            javaMailSender.send(mail);
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }

    @Override
    public boolean sendSimpleCode(String email, String subject, String content) {
        boolean flag = false;
        try {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setFrom(from);
            mail.setTo(email);
            mail.setSubject(subject);
            mail.setText(content);
            javaMailSender.send(mail);
            flag = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return flag;
    }
}