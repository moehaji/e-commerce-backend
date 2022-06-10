package com.revature.repositories;

import com.revature.models.Messages;
import com.revature.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Messages, Integer> {

    List<Messages> findByReceiver(int id);
}
