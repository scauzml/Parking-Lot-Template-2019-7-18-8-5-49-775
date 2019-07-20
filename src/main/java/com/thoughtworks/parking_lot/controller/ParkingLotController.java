package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(params = {"page","pageSize"})
    public ResponseEntity getParkingLotByPage(@RequestParam("page")String page,@RequestParam("pageSize")String pageSize) {
        List<ParkingLot> parkingLots=parkingLotService.getParkingLotByPage(page,pageSize);
        return ResponseEntity.ok().body(parkingLots);
    }

    @GetMapping("/{id}")
    public ResponseEntity getParkingLotByID(@PathVariable("id") String id) {
        ParkingLot parkingLot=parkingLotService.findParkingLotByID(id);
        return ResponseEntity.ok().body(parkingLot);
    }
//需要修改
    @PutMapping("/{id}")
    public ResponseEntity changeParkingLot(@RequestBody ParkingLot parkingLot,@PathVariable("id")String id) {
        ParkingLot parkingLot1=parkingLotService.changeParkingLot(parkingLot,id);
        return ResponseEntity.ok().body(parkingLot1);
    }
}

