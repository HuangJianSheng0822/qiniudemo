package com.qn.qiniudemoapi.qiniutest;

import com.qiniu.util.Auth;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class Test1 {
    @Test
    void testAuth(){
        String accessKey = "NpzEEIGAfBdwV3Pk-tvLOg7cQxXVxLcfZ9VaLGJS";
        String secretKey = "LU2rebbpMS8hSlLF-u_SS03qh4E6AXgKO4pDIdK4";
        String bucket = "1024hjsdemo";
        String key = "filename";

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket, key);
        System.out.println(upToken);

    }
}
