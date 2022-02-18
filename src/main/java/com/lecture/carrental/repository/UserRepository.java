package com.lecture.carrental.repository;


import com.lecture.carrental.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

  @Repository
  @Transactional  //bir hata aldigimizda geri dönmek icinde kullanilir islem geri alma düzeltme vs rollback ozelligi var
  public interface UserRepository extends JpaRepository<User, Long> {


   // @Query("SELECT u from User u where u.email = ?1")
     Optional<User>findByEmail(String email);

}
