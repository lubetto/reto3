package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class OrtopedicService {

    @Autowired
    private OrtopedicRepository ortopedicRepository;

    public List<Ortopedic> getAll() {
        return ortopedicRepository.getAll();
    }

    public Optional<Ortopedic> getOrtopedic(int id) {
        return  ortopedicRepository.getOrtopedic(id);
    }

    public Ortopedic save(Ortopedic p){
        if(p.getId() == null){
            return ortopedicRepository.save(p);
        }else{
            Optional<Ortopedic> ortopedicOptional = ortopedicRepository.getOrtopedic(p.getId());
            if(ortopedicOptional.isEmpty()){
                return ortopedicRepository.save(p);
            }else{
                return p;
            }
        }
    }

    public Ortopedic update(Ortopedic o){
        if(o.getId()!=null){
            Optional<Ortopedic> e=ortopedicRepository.getOrtopedic(o.getId());
            if(!e.isEmpty()){
                if(o.getName()!=null){
                    e.get().setName(o.getName());
                }
                if(o.getBrand()!=null){
                    e.get().setBrand(o.getBrand());
                }
                if(o.getYear()!=null){
                    e.get().setYear(o.getYear());
                }
                if(o.getDescription()!=null){
                    e.get().setDescription(o.getDescription());
                }
                if(o.getCategory()!=null){
                    e.get().setCategory(o.getCategory());
                }
                ortopedicRepository.save(e.get());
                return e.get();
            }else{
                return o;
            }
        }else{
            return o;
        }
    }

    public boolean deleteOrtesis(int ortesisId) {
        Boolean aBoolean = getOrtopedic(ortesisId).map(ortesis -> {
            ortopedicRepository.delete(ortesis);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}