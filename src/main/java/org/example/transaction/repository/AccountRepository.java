package org.example.transaction.repository;

import org.example.transaction.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

//    @Query(value ="select * from Account where account_id = ?1",nativeQuery = true)
//    Optional<Account> findByAccountId(Long accountId);

}
