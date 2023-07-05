package com.sparta.spring_lv1.repository;

import com.sparta.spring_lv1.entity.User_bata;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository_beta extends JpaRepository<User_bata, Long>{ // Jap사용할거라 extends, <엔티티 클래스명, 식별자타입>
    Optional<User_bata> findByUsername(String username); // DB에서 해당 username이 있는지 찾아오기

    Optional<User_bata> findByEmail(String email);
}


