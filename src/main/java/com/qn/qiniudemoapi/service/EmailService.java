package com.qn.qiniudemoapi.service;

public interface EmailService {

    /**
     * 邮件发送
     * @param email email
     * @return 结果
     */
    boolean sendSimpleCode(String email);

    boolean sendSimpleCode(String email, String subject, String content);
}
