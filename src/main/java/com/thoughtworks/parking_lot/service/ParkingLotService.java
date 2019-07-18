package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotResposity parkingLotResposity;


    public boolean save(ParkingLot parkingLot) {
        ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        return parkingLot1.getId()!=null?true:false;
    }
}
