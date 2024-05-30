package io.nikita.EmpProject;

import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

public class LoadBalancerConfiguration {
    @Bean
    public ServiceInstanceListSupplier instanceSupplier(ConfigurableApplicationContext context) {
        return ServiceInstanceListSupplier.builder()
                .withDiscoveryClient()
                .withHealthChecks()
                .build(context);

//        public ServiceInstanceListSupplier discoveryClientServiceInstanceListSupplier(ConfigurableApplicationContext context) {
//        System.out.println("Configuring Load balancer to prefer same instance");
//        return ServiceInstanceListSupplier.builder()
//                .withBlockingDiscoveryClient()
//                .withSameInstancePreference()
//                .build(context);


//    public ServiceInstanceListSupplier instanceSupplier(ConfigurableApplicationContext context) {
//        return ServiceInstanceListSupplier.builder()
//                .withDiscoveryClient()
//                .withHealthChecks()
//                .build(context);
    }
}