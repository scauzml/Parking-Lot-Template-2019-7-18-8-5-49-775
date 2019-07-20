package com.thoughtworks.parking_lot.controller;

import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import com.thoughtworks.parking_lot.service.ParkingBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("parkingbills")
public class ParkingBillController {

    @Autowired
    ParkingBillService parkingBillService;
    @PostMapping
    public ResponseEntity addParkingBill(@RequestBody ParkingBill parkingBill) {
        ParkingBill parkingBill1=parkingBillService.save(parkingBill);
        return parkingBill!=null? ResponseEntity.status(HttpStatus.CREATED).body(parkingBill):ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
