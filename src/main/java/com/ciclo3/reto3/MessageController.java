package com.ciclo3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/Message")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class MessageController {

    @Autowired
    MessageService messageService;

    @GetMapping("/all")
    public List<Message> getMessages() {
        return  messageService.getAll();
    }

    @GetMapping("{id}")
    public Optional<Message> getMessage(@PathVariable("id")int id) {
        return messageService.getMessage(id);
    }

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Message save(@RequestBody Message m) {
        return messageService.save(m);
    }
}
