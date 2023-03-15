package com.wayne.springboot3apitemplate.service;

import org.springframework.stereotype.Service;

@Service
public class DemoService {

    public String version() {
        return "1.0";
    }

}
