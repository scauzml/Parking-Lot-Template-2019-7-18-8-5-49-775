package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.dao.ParkingBillResposity;
import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingBillTest {
    @Autowired
    ParkingBillResposity parkingBillResposity;
    @Autowired
    ParkingLotResposity parkingLotResposity;
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
        parkingBill.setStatus(1);
        JSONObject jsonObject = new JSONObject(parkingBill);
        //when
        String result=this.mockMvc.perform(post("/parkingbills").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        //then
        JSONObject jsonObject1=new JSONObject(result);
        Assertions.assertEquals("parkinglot1",jsonObject.getString("parkingLotName"));

    }
    @Test
    public void should_return_bill_status_is_off_when_after_get_a_car_then_put_parkinglot_bill_to_change() throws Exception{
        //given
        ParkingBill parkingBill=new ParkingBill();
        parkingBill.setParkingLotName("parkinglot1");
        parkingBill.setCarNumber("1234");
        LocalDateTime startTime = LocateDateUtil.getLocalDateTime(new Date());
        parkingBill.setStartTime(startTime);
        parkingBill.setStatus(1);
        ParkingBill parkingBill1=parkingBillResposity.saveAndFlush(parkingBill);

        //when
        String result=this.mockMvc.perform(put("/parkingbills/"+parkingBill1.getBillId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        //then
        JSONObject jsonObject1=new JSONObject(result);
        Assertions.assertEquals(0,jsonObject1.getInt("status"));
    }

    @Test
    public void should_return_exptionMessage_created_when_post_parkinglot_bill_to_save() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        parkingLot.setIsNotCapicity(1);
        ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        ParkingBill parkingBill=new ParkingBill();
        parkingBill.setParkingLotName("parkinglot1");
        parkingBill.setCarNumber("1234");
        LocalDateTime startTime = LocateDateUtil.getLocalDateTime(new Date());
        parkingBill.setStartTime(startTime);
        parkingBill.setStatus(0);
        JSONObject jsonObject = new JSONObject(parkingBill);
        //when
        String result=this.mockMvc.perform(post("/parkingbills").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest()).andReturn().getResponse().getContentAsString();
        //then

       Assertions.assertEquals("停车场已经满",result);

    }
}
