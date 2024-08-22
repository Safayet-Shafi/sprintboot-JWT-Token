package com.springboot.jwt.repository;

import com.springboot.jwt.model.UserInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserInformationRepo extends JpaRepository<UserInformation,Long> {

    @Query(
            nativeQuery = true,
            value = "SELECT PASSWORD\n" +
                    "  FROM USERINFORMATION\n" +
                    " WHERE USER_ID = :userId"
    )
    String getUserPasswordByUserId(@Param("userId") String userId);
}
