package com.thoughtworks.parking_lot.service;

import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ParkingLotService {
    @Autowired
    private ParkingLotResposity parkingLotResposity;

    public List<ParkingLot> getParkingLotByPage(String page, String pageSize) {
        Page<ParkingLot> parkingLots=parkingLotResposity.findAll(PageRequest.of(Integer.valueOf(page)-1,Integer.valueOf(pageSize)));
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

    public ParkingLot changeParkingLot(ParkingLot parkingLot) {
        ParkingLot parkingLot1 = parkingLotResposity.saveAndFlush(parkingLot);
        return parkingLot1;
    }
}
