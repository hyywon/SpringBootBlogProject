package com.project.blog.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

// JSP의  DAO
// 자동으로 bean 등록
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String userName);
}



//    Optional<UserEntity> findByName(String name);
//
//    JPA Naming query
//    SELECT * FROM user WHERE name =? AND password = ?;
//    UserEntity findByNameAndPassword(String name, String password);
//
//    @Query(value = "SELECT * FROM user WHERE name = ?1 AND password = ?2",nativeQuery = true)
//    UserEntity signin(String name, String password); // 위와 동일