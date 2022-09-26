package com.hq.test.controller;

import com.hq.test.service.UserService;
import com.hq.test.utils.QRCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lichaojie
 * @date 2021/11/1 16:37
 * @ClassName UserController
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping(value = "/getUser")
    public String getUser(@RequestParam("userName") String userName) {
        return userService.getUserByUserName(userName);
    }


    @PostMapping(value = "/generateQRCode", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void generateQRCode(HttpServletRequest request, HttpServletResponse response, @RequestBody List<String> ids) throws Exception {
        String idStr = ids.toString();
        QRCodeUtils.encode(
                idStr,
                response.getOutputStream()
        );
    }

}