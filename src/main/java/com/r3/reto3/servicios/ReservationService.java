package com.r3.reto3.servicios;

import com.r3.reto3.modelos.Reservation;
import com.r3.reto3.reportes.ContadorClientes;
import com.r3.reto3.reportes.StatusReservas;
import com.r3.reto3.repositorios.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    /**
     *
     * Definición Estado Reserevas
     */
    public StatusReservas getReporteStatusReservations(){
        List<Reservation>completed = resRepository.ResStatus("completed");
        List<Reservation>cancelled = resRepository.ResStatus("cancelled");
        return new StatusReservas(completed.size(), cancelled.size());
    }
    /**
     *
     * Definición Reporte fecha especifica
     */
    public List<Reservation> getReportesTiempoReservaciones(String datoA, String datoB){
        SimpleDateFormat parser=new SimpleDateFormat ("yyyy-MM-dd");
        Date datoUno = new Date();
        Date datoDos = new Date();

        try{
            datoUno = parser.parse(datoA);
            datoDos = parser.parse(datoB);
        }catch(ParseException evt){
            evt.printStackTrace();
        }if(datoUno.before(datoDos)){
            return resRepository.ResTime(datoUno, datoDos);
        }else{
            return new ArrayList<>();
        }
    }
    /**
     *
     * Definición Servicio Top Clientes
     */
    public List<ContadorClientes> servicioTopClientes(){
        return resRepository.getTopClients();
    }
}
