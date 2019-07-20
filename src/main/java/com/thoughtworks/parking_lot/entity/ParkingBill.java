package com.thoughtworks.parking_lot.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "parking_bill")
public class ParkingBill {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String billId;
    private String parkingLotName;
    private String carNumber;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer status;

    public ParkingBill() {
    }

    public ParkingBill(String billId, String parkingLotName, String carNumber,
                       LocalDateTime startTime, LocalDateTime endTime, Integer status) {
        this.billId = billId;
        this.parkingLotName = parkingLotName;
        this.carNumber = carNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.status = status;
    }

    public String getBillId() {
        return billId;
    }

    public void setBillId(String billId) {
        this.billId = billId;
    }

    public String getParkingLotName() {
        return parkingLotName;
    }

    public void setParkingLotName(String parkingLotName) {
        this.parkingLotName = parkingLotName;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
