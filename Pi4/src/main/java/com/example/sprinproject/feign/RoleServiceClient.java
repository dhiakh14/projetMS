package com.example.sprinproject.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

@FeignClient(name = "role-service", url = "http://localhost:8092")
public interface RoleServiceClient {
    @GetMapping("/roles/user/{userId}")
    List<String> getRolesByUserId(@PathVariable("userId") String userId);
}
