package com.r3.reto3.repositorios;

import com.r3.reto3.interfaces.MessageCrudRepository;
import com.r3.reto3.modelos.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
@Repository
public class MessageRepository {
    @Autowired
    private MessageCrudRepository messageCrudRepository;

    public List<Message> getAll() {
        return  (List<Message>) messageCrudRepository.findAll();
    }

    public Optional<Message> getMessage(int id) {
        return messageCrudRepository.findById(id);
    }

    public Message save(Message m) {
        return messageCrudRepository.save(m);
    }

    public void delete(Message m){
        messageCrudRepository.delete(m);
    }
}
