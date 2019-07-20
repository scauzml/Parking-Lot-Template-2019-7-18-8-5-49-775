package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotResposity parkingLotResposity;

    public List<ParkingLot> getParkingLotByPage(String page, String pageSize) {
        Page<ParkingLot> parkingLots=parkingLotResposity.findAll
                (PageRequest.of(Integer.valueOf(page)-1,Integer.valueOf(pageSize)));
        return parkingLots.getContent();

    }


    public boolean save(ParkingLot parkingLot) {
        ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        return parkingLot1.getId()!=null?true:false;
    }

    public void deleteById(String id) {
        parkingLotResposity.deleteById(id);

    }

    public ParkingLot findParkingLotByID(String id) {
       return parkingLotResposity.findById(id).get();

    }

    public ParkingLot changeParkingLot(ParkingLot parkingLot,String id) {
        ParkingLot parkingLot1=parkingLotResposity.findById(id).get();
        Optional<ParkingLot> optionalParkingLot = Optional.of(parkingLot1);
        if (optionalParkingLot.isPresent()) {
          parkingLot1.setName(parkingLot.getPosition());
          parkingLot1.setCapicity(parkingLot.getCapicity());
          parkingLot1.setPosition(parkingLot.getPosition());
        }
        return   parkingLotResposity.saveAndFlush(parkingLot1);
    }
}
