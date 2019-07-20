package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dao.ParkingBillResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ParkingBillService {

    @Autowired
    ParkingBillResposity parkingBillResposity;
    public boolean save(ParkingBill parkingBill) {
        ParkingBill parkingBill1=parkingBillResposity.save(parkingBill);
        return parkingBill1.getBillId()!=null?true:false;
    }
}
