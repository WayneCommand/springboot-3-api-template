package com.wayne.springboot3apitemplate.rest;

import com.wayne.springboot3apitemplate.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
@RequiredArgsConstructor
public class DemoRest {

    private final DemoService demoService;

    @GetMapping("/version")
    public String version() {

        return demoService.version();
    }

}
