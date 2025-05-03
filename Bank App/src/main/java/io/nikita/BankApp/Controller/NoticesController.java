package io.nikita.BankApp.Controller;

import io.nikita.BankApp.Models.NoticeDetails;
import io.nikita.BankApp.repository.NoticeDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticesController {

    private final NoticeDetailsRepository noticeDetailsRepository;

    @GetMapping("/myNotices")
    public ResponseEntity<List<NoticeDetails>> getNoticesDetails(){
        List<NoticeDetails> noticeDetails = noticeDetailsRepository.findAllActiveNotices();
        if (noticeDetails != null) {
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(noticeDetails);
        } else {
            return null;
        }

    }

}