package com.thoughtworks.parking_lot.dao;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingLotResposity extends JpaRepository<ParkingLot,String> {

    ParkingLot findParkingLotByName(String parkingLotName);
}
