package com.qn.qiniudemoapi.controller;

import com.qiniu.util.Auth;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class UploadController {

    @GetMapping("/up/token")
    String getAuth(){
        String accessKey = "NpzEEIGAfBdwV3Pk-tvLOg7cQxXVxLcfZ9VaLGJS";
        String secretKey = "LU2rebbpMS8hSlLF-u_SS03qh4E6AXgKO4pDIdK4";
        String bucket = "1024hjsdemo";
        Random random = new Random();
        String key = null;
        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        return upToken;
    }
}
