package com.qn.qiniudemoapi.controller;



import com.qn.qiniudemoapi.service.EmailService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 邮箱发送
 */
@RestController
@Api(tags = "邮件服务")
@RequestMapping("/email")
public class EmailController {

    private final EmailService emailService;

    @Autowired
    public EmailController(EmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * 邮件发送
     * @param email 邮箱
     * @return 结果
     */
    @ApiOperation("邮件验证码发送")
    @ApiImplicitParam(name = "email", value = "邮箱")
    @PostMapping("/{email}")
    ResponseDataStructure sendCode(@PathVariable("email") String email) {
        boolean b = emailService.sendSimpleCode(email);
        return new ResponseDataStructure(ResponseDataStructure.Code_Auth,null,b);
    }
}
