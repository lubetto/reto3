package com.r3.reto3;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author LUIS GERMAN ORTEGA M.
 */
@Entity
@Table(name = "ortopedic")
/**
 *
 * Definicion clase Ortopedic
 */
public class Ortopedic implements Serializable {
    /**
     *
     * Atributo ID de la Clase
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrtopedic;
    /**
     *
     * Atributo name de la Clase
     */
    @Column(length = 45)
    private String name;
    /**
     *
     * Atributo brand de la Clase
     */
    @Column(length = 45)
    private String brand;
    /**
     *
     * Atributo year de la Clase
     */
    @Column(length = 4)
    private Integer year;
    /**
     *
     * Atributo description de la Clase
     */
    @Column(length = 250)
    private String description;
    /**
     * Relacion con Category
     */
    @ManyToOne
    @JoinColumn(name = "categoryID")
    @JsonIgnoreProperties("ortopedics")
    private Category category;
    /**
     * Relacion con Cliente
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "ortopedic")
    @JsonIgnoreProperties({"ortopedic","client"})
    public List<Message> messages;
    /**
     * Relacion con Reservaciones
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy = "ortopedic")
    @JsonIgnoreProperties("ortopedic")
    public List<Reservation> reservations;
    /**
     * Getters and Setters de la clase
     */
    public Integer getId() {
        return idOrtopedic;
    }

    public void setId(Integer idOrtopedic) {
        this.idOrtopedic = idOrtopedic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }
}