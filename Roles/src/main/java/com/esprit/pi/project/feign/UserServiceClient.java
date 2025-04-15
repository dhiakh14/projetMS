package com.esprit.pi.project.feign;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "UserManagementClient", url = "http://localhost:8082/GE")
public interface UserServiceClient {
    @GetMapping("/users/find/{username}")
    String getUserIdByUsername(@PathVariable String username);
}


