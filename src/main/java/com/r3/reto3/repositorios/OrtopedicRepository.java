package com.r3.reto3.repositorios;

import com.r3.reto3.interfaces.OrtopedicCrudRepository;
import com.r3.reto3.modelos.Ortopedic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
@Repository
public class OrtopedicRepository {
    @Autowired
    private OrtopedicCrudRepository ortopedicCrudRepository;

    public List<Ortopedic> getAll() {
        return  (List<Ortopedic>) ortopedicCrudRepository.findAll();
    }

    public Optional<Ortopedic> getOrtopedic(int id) {
        return ortopedicCrudRepository.findById(id);
    }

    public Ortopedic save(Ortopedic o) {
        return ortopedicCrudRepository.save(o);
    }

    public void delete(Ortopedic o){
        ortopedicCrudRepository.delete(o);
    }
}