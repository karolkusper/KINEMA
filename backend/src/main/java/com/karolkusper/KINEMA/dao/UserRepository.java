package com.karolkusper.KINEMA.dao;


import com.karolkusper.KINEMA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("select u from User u where u.email =:email")
    User getUserByEmail(@Param("email") String email);
}
