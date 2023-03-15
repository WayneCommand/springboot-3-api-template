package com.wayne.springboot3apitemplate.service.http;

import com.wayne.springboot3apitemplate.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "sys-user")
public interface SysUserService {
    @GetMapping("/user/detail")
    User userDetail(@RequestParam("userId") Integer userId);

}
