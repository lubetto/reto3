package com.r3.reto3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
@Service
/**
 *
 * Definicion clase ReservationService
 */
public class ReservationService {
    /**
     * creación de variable de tipo Repositorio con la anotación
     */
    @Autowired
    private ReservationRepository resRepository;
    /**
     * metodo para obtener todos los datos de la tabla reservaciones
     * @return List de clase Reservation
     */
    public List<Reservation> getAll() {
        return resRepository.getAll();
    }
    /**
     * metodo para obtener dato de la tabla reservaciones por Id
     */
    public Optional<Reservation> getReservation(int idReservation) {
        return  resRepository.getReservation(idReservation);
    }

    /**
     *
     * Definición Clase Save
     */
    public Reservation save(Reservation guardar){
        if(guardar.getIdReservation() == null){
            return resRepository.save(guardar);
        }else{
            Optional<Reservation> resOptional = resRepository.getReservation(guardar.getIdReservation());
            if(resOptional.isEmpty()){
                return resRepository.save(guardar);
            }else{
                return guardar;
            }
        }
    }
    /**
     *
     * Definición Clase Update
     */
    public Reservation update(Reservation actual){
        if(actual.getIdReservation()!=null){
            Optional<Reservation> empt= resRepository.getReservation(actual.getIdReservation());
            if(!empt.isEmpty()){
                if(actual.getStartDate()!=null){
                    empt.get().setStartDate(actual.getStartDate());
                }
                if(actual.getDevolutionDate()!=null){
                    empt.get().setDevolutionDate(actual.getDevolutionDate());
                }
                if(actual.getStatus()!=null){
                    empt.get().setStatus(actual.getStatus());
                }
                resRepository.save(empt.get());
                return empt.get();
            }else{
                return actual;
            }
        }else{
            return actual;
        }
    }
    /**
     *
     * Definición Clase Delete
     */
    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            resRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
