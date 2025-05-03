package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.ContactMessages;
import io.nikita.BankApp.repository.ContactMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactMessagesRepository contactMessagesRepository;

    @PostMapping("/contact")
    public ContactMessages getContactDetails(@RequestBody ContactMessages contactMessages) {
        contactMessages.setContactId(getServiceReqNumber());
        contactMessages.setCreateDt(new Date(System.currentTimeMillis()));
        return contactMessagesRepository.save(contactMessages);
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ran = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ran;
    }

}