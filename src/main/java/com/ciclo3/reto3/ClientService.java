package com.ciclo3.reto3;

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
}
