package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.ContactMessages;
import io.nikita.BankApp.repository.ContactMessagesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {
    private final ContactMessagesRepository contactMessagesRepository;

    @PostMapping("/contact")
//    @PreFilter("filterObject.contactName != 'Test'")
    @PreFilter("filterObject.contactName != 'Test'")
    public List<ContactMessages> getContactDetails(@RequestBody List<ContactMessages> contactMessages) {
        List<ContactMessages> contacts = new ArrayList<>();
        if (!contactMessages.isEmpty()) {
            ContactMessages contact = contactMessages.getFirst();
            contact.setContactId(getServiceReqNumber());
            contact.setCreateDt(new Date(System.currentTimeMillis()));
            ContactMessages savedContact = contactMessagesRepository.save(contact);
            contacts.add(savedContact);
        }
        return contacts;
    }

    public String getServiceReqNumber() {
        Random random = new Random();
        int ran = random.nextInt(999999999 - 9999) + 9999;
        return "SR" + ran;
    }

}