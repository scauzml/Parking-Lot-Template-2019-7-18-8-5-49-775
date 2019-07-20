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
        boolean isSaveSucess=parkingBillService.save(parkingBill);
        return isSaveSucess? ResponseEntity.status(HttpStatus.CREATED).build():ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }


}
