package com.thoughtworks.parking_lot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "PARKING_LOT")
public class ParkingLot {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @Column(unique = true)
    private String name;

    private int capicity;
    private String position;
    private int isNotCapicity;

    public ParkingLot(String name, int capicity, String position) {
        this.name = name;
        this.capicity = capicity;
        this.position = position;
    }

    public ParkingLot() {
    }

    public int getIsNotCapicity() {
        return isNotCapicity;
    }

    public void setIsNotCapicity(int isNotCapicity) {
        this.isNotCapicity = isNotCapicity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapicity() {
        return capicity;
    }

    public void setCapicity(int capicity) {
        this.capicity = capicity;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
