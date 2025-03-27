package com.mohitjoi.library.core.repository;

import com.mohitjoi.library.core.model.BorrowRecord;
import com.mohitjoi.library.core.type.BorrowStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BorrowRecordRepository extends MongoRepository<BorrowRecord, String> {

    boolean existsByBookIdAndStatus(String id, BorrowStatus borrowStatus);

}
