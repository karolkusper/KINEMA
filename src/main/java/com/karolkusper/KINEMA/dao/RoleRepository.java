package com.karolkusper.KINEMA.dao;


import com.karolkusper.KINEMA.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    @Query("select r from Role r where r.name =:name")
    Role getRoleByName(@Param("name") String name);
}
