package com.revature.services;

import com.revature.models.Messages;
import com.revature.models.Product;
import com.revature.models.User;
import com.revature.repositories.MessageRepository;
import com.revature.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Messages> findAll() {
        return messageRepository.findAll();
    }

    public List<Messages> findByReceiver(int id) {
        return messageRepository.findByReceiver(id);
    }


    public Messages save(Messages messages) {
        return messageRepository.save(messages);
    }
}

