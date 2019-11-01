package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProductController {

    @GetMapping("/product")
    public String getProduct() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("PRODUCTID=1");
        stringBuffer.append("|");
        return stringBuffer.toString();
    }


    @RequestMapping("/")
    public String index() {
        return "welcome product";
    }
}
