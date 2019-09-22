package com.assignmentSubmition.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.assignmentSubmition.constants.ApiUrl;

@RestController
@RequestMapping(ApiUrl.BASE_URL_V1)
public class DemoController {
	
    @GetMapping(ApiUrl.HELLO_ENDPOINT)
    public String hello()
    {
        return "hello";
    }
}