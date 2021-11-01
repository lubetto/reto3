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
     * @param id
     * @return Optional de clase Reservacion
     */
    public Optional<Reservation> getReservation(int id) {
        return  resRepository.getReservation(id);
    }

    /**
     *
     * Definición Clase Save
     */
    public Reservation save(Reservation p){
        if(p.getIdReservation() == null){
            return resRepository.save(p);
        }else{
            Optional<Reservation> reservationOptional = resRepository.getReservation(p.getIdReservation());
            if(reservationOptional.isEmpty()){
                return resRepository.save(p);
            }else{
                return p;
            }
        }
    }
    /**
     *
     * Definición Clase Update
     */
    public Reservation update(Reservation r){
        if(r.getIdReservation()!=null){
            Optional<Reservation> e= resRepository.getReservation(r.getIdReservation());
            if(!e.isEmpty()){
                if(r.getStartDate()!=null){
                    e.get().setStartDate(r.getStartDate());
                }
                if(r.getDevolutionDate()!=null){
                    e.get().setDevolutionDate(r.getDevolutionDate());
                }
                if(r.getStatus()!=null){
                    e.get().setStatus(r.getStatus());
                }
                resRepository.save(e.get());
                return e.get();
            }else{
                return r;
            }
        }else{
            return r;
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
