package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    public List<Message> getAll() {
        return messageRepository.getAll();
    }

    public Optional<Message> getMessage(int id) {
        return  messageRepository.getMessage(id);
    }

    public Message save(Message p){
        if(p.getIdMessage() == null){
            return messageRepository.save(p);
        }else{
            Optional<Message> messageOptional = messageRepository.getMessage(p.getIdMessage());
            if(messageOptional.isEmpty()){
                return messageRepository.save(p);
            }else{
                return p;
            }
        }
    }
}
