package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dao.ParkingBillResposity;
import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParkingBillService {

    @Autowired
    ParkingBillResposity parkingBillResposity;
    @Autowired
    ParkingLotResposity parkingLotResposity;
    public ParkingBill save(ParkingBill parkingBill) {
        ParkingLot parkingLot = parkingLotResposity.findParkingLotByName(parkingBill.getParkingLotName());
        if (parkingLot.getIsNotCapicity()==1){
            throw new MyException("停车场已经满");
        }
        ParkingBill parkingBill1=parkingBillResposity.save(parkingBill);
        return parkingBill;
    }

    public ParkingBill changeParkingLot(String id) {
        ParkingBill parkingBill=parkingBillResposity.findById(id).get();
        Optional<ParkingBill> optionalParkingBill = Optional.of(parkingBill);
        if (optionalParkingBill.isPresent()) {
           parkingBill.setStatus(0);
        }
        return   parkingBillResposity.saveAndFlush(parkingBill);
    }
}
