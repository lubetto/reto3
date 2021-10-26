package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAll() {
        return clientRepository.getAll();
    }

    public Optional<Client> getClient(int id) {
        return  clientRepository.getClient(id);
    }

    public Client save(Client p){
        if(p.getIdClient() == null){
            return clientRepository.save(p);
        }else{
            Optional<Client> clientOptional = clientRepository.getClient(p.getIdClient());
            if(clientOptional.isEmpty()){
                return clientRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Client update(Client c){
        if(c.getIdClient()!=null){
            Optional<Client> e= clientRepository.getClient(c.getIdClient());
            if(!e.isEmpty()){
                if(c.getName()!=null){
                    e.get().setName(c.getName());
                }
                if(c.getAge()!=null){
                    e.get().setAge(c.getAge());
                }
                if(c.getPassword()!=null){
                    e.get().setPassword(c.getPassword());
                }
                clientRepository.save(e.get());
                return e.get();
            }else{
                return c;
            }
        }else{
            return c;
        }
    }

    public boolean deleteClient(int IdClient) {
        Boolean aBoolean = getClient(IdClient).map(client -> {
            clientRepository.delete(client);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
