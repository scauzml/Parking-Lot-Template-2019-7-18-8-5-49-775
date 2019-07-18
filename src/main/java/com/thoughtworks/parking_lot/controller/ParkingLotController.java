package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/parkinglots")
public class ParkingLotController {

    @Autowired
    ParkingLotService parkingLotService;

    @PostMapping()
    public ResponseEntity addParkingLot(@RequestBody ParkingLot parkingLot) {
        boolean isSaveSucess=parkingLotService.save(parkingLot);
        return isSaveSucess? ResponseEntity.status(HttpStatus.CREATED).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteParkingLot(@PathVariable("id")String id) {
        parkingLotService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}

