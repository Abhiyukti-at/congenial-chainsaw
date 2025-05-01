package io.nikita.BankApp.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticesController {
    @GetMapping("/myNotices")
    public String getNoticesDetails(){
        return "NoticesDetails";

    }

}