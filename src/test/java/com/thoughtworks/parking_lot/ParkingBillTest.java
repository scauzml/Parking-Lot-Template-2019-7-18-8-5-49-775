package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.dao.ParkingBillResposity;
import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.util.LocateDateUtil;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingBillTest {
    @Autowired
    ParkingBillResposity parkingBillResposity;
    @Autowired
    MockMvc mockMvc;
    @BeforeEach
    public void beforeEach() {
        parkingBillResposity.deleteAll();
    }

    @Test
    public void should_return_status_created_when_post_parkinglot_bill_to_save() throws Exception{
        //given
        ParkingBill parkingBill=new ParkingBill();
        parkingBill.setParkingLotName("parkinglot1");
        parkingBill.setCarNumber("1234");
        LocalDateTime startTime = LocateDateUtil.getLocalDateTime(new Date());
        parkingBill.setStartTime(startTime);
//        LocalDateTime endTime = LocateDateUtil.getLocalDateTime(new Date());
//        endTime.plusHours(2);
//        parkingBill.setEndTime(endTime);
        parkingBill.setStatus(1);

        //when
        JSONObject jsonObject = new JSONObject(parkingBill);

        String result=this.mockMvc.perform(post("/parkingbills").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        //then
        JSONObject jsonObject1=new JSONObject(result);
        Assertions.assertEquals("pa1",jsonObject.getString("name"));

    }
}
