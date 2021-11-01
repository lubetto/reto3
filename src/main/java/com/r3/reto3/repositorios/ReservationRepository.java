package com.r3.reto3.repositorios;

import com.r3.reto3.interfaces.ReservationCrudRepository;
import com.r3.reto3.modelos.Client;
import com.r3.reto3.modelos.Reservation;
import com.r3.reto3.reportes.ContadorClientes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
@Repository
public class ReservationRepository {

    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll() {
        return  (List<Reservation>) reservationCrudRepository.findAll();
    }

    public Optional<Reservation> getReservation(int id) {
        return reservationCrudRepository.findById(id);
    }

    public Reservation save(Reservation r) {
        return reservationCrudRepository.save(r);
    }

    public void delete(Reservation r){
        reservationCrudRepository.delete(r);
    }

    public List<Reservation> ResStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }

    public List<Reservation> ResTime (Date a, Date b){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(a, b);
    }

    public List<ContadorClientes> getTopClients(){
        List<ContadorClientes> res=new ArrayList<>();
        List<Object[]>report = reservationCrudRepository.countTotalReservationsByClient();
        for(int i=0; i<report.size();i++){
            res.add(new ContadorClientes((Long)report.get(i)[1],(Client) report.get(i)[0]));

        }
        return res;
    }
}
