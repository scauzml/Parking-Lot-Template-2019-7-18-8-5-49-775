package com.thoughtworks.parking_lot.dao;

import com.thoughtworks.parking_lot.entity.ParkingBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParkingBillResposity extends JpaRepository<ParkingBill,String> {
}
