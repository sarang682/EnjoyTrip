package com.ssafy.enjoytrip.repository;

import com.ssafy.enjoytrip.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findById(String id);

    @Modifying
    @Query("update Member m " +
            "set m.name=:name, m.password=:password, m.emailId=:emailId, m.emailDomain=:emailDomain " +
            "where m.id=:id")
    int updateMember(@Param("id") String id, @Param("name")String name, @Param("password")String password, @Param("emailId")String emailId, @Param("emailDomain")String emailDomain);
}
