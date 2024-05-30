package io.nikita.EmpProject;

import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@LoadBalancerClient(name = "department-service",
        configuration=LoadBalancerConfiguration.class)
//@LoadBalancerClients(defaultConfiguration = LoadBalancerConfiguration.class)
@FeignClient(name="department-service")
public interface DepartmentClient {
    @GetMapping("/departments/{id}")
    ResponseEntity<DepartmentDetails> getDepartmentDetails(@PathVariable Long id);
}