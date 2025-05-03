package io.nikita.BankApp.repository;

import io.nikita.BankApp.Models.NoticeDetails;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoticeDetailsRepository extends CrudRepository<NoticeDetails,Long> {

    @Query(value = "from NoticeDetails n  where CURDATE() BETWEEN noticBegDt and noticEndDt")
    List<NoticeDetails> findAllActiveNotices();
}