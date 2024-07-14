package com.hanwha.settlement.users.repository;

import com.hanwha.settlement.users.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
