package com.thoughtworks.parking_lot;

import com.thoughtworks.parking_lot.dao.ParkingLotResposity;
import com.thoughtworks.parking_lot.entity.ParkingBill;
import com.thoughtworks.parking_lot.entity.ParkingLot;
import org.json.JSONArray;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;



@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ParkingLotTest {

    @Autowired
    ParkingLotResposity parkingLotResposity;

    @BeforeEach
    public void beforeEach() {
        parkingLotResposity.deleteAll();
    }

    @Autowired
    MockMvc mockMvc;
    @Test
    public void should_return_status_created_when_add_parking_lot_to_system() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        JSONObject jsonObject = new JSONObject(parkingLot);
        //when
        //then
        this.mockMvc.perform(post("/parkinglots").content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());

    }

    @Test
    public void should_return_is_ok_when_delete_parking_lot_by_id() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        JSONObject jsonObject = new JSONObject(parkingLot);
      ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        //when
        //then
        this.mockMvc.perform(delete("/parkinglots/"+parkingLot1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

    }

    @Test
    public void should_return_is_page_with_parkingLot_when_search_parking_lot_() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        ParkingLot parkingLot1=new ParkingLot();
        parkingLot1.setCapicity(16);
        parkingLot1.setName("parkinglot2");
        parkingLot1.setPosition("address2");
        parkingLotResposity.save(parkingLot);
        parkingLotResposity.save(parkingLot1);

        //when
       String result= this.mockMvc.perform(get("/parkinglots/").param("page","1").param("pageSize","15")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

        //then
        JSONArray jsonArray = new JSONArray(result);
        Assertions.assertEquals("parkinglot1",jsonArray.getJSONObject(0).getString("name"));
        Assertions.assertEquals("parkinglot2",jsonArray.getJSONObject(1).getString("name"));

    }

    @Test
    public void should_return_parkinglot_when_search_parking_lot_by_id() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        //when

        String result=this.mockMvc.perform(get("/parkinglots/"+parkingLot1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        //then
        JSONObject jsonObject=new JSONObject(result);
        Assertions.assertEquals("parkinglot1",jsonObject.getString("name"));

    }

    @Test
    public void should_return_parkinglot_when_put_parkinglot() throws Exception{
        //given
        ParkingLot parkingLot=new ParkingLot();
        parkingLot.setCapicity(16);
        parkingLot.setName("parkinglot1");
        parkingLot.setPosition("address1");
        ParkingLot parkingLot1=parkingLotResposity.save(parkingLot);
        //when
        parkingLot.setName("pa1");
        JSONObject jsonObject = new JSONObject(parkingLot);

        String result=this.mockMvc.perform(put("/parkinglots/"+parkingLot1.getId()).content(jsonObject.toString())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
        //then
        JSONObject jsonObject1=new JSONObject(result);
        Assertions.assertEquals("pa1",jsonObject.getString("name"));

    }

}
