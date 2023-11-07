package com.qn.qiniudemoapi.controller;


import com.qn.qiniudemoapi.dto.ApplyLiveDto;
import com.qn.qiniudemoapi.service.LiveUserService;
import com.qn.qiniudemoapi.util.ResponseDataStructure;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@Api(tags = "用户直播")
@RequestMapping("/lu")
public class LiveUserController {

    @Autowired
    private  LiveUserService liveUserService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    @ResponseBody
    public Object liveAuth(String email,String auth, HttpServletResponse response){
        int obj = liveUserService.liveAuth(email, auth);
        response.setStatus(200);
        return obj;
    }

    @PostMapping("/")
    public Object applyLive(@RequestHeader("Authorization") String token, @RequestBody ApplyLiveDto applyLiveDto){
        return liveUserService.applyLive(token,applyLiveDto);
    }

    @PostMapping("/email")
    public ResponseDataStructure applyLive(@RequestHeader("Authorization") String token){
        return liveUserService.liveEmail(token);
    }

    @PostMapping("/can")
    public ResponseDataStructure canLive(@RequestHeader("Authorization") String token){
        return liveUserService.canLive(token);
    }


}
