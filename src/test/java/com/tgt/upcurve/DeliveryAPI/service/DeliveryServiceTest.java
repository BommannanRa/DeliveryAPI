package com.tgt.upcurve.DeliveryAPI.service;

import com.tgt.upcurve.DeliveryAPI.DeliveryApiApplication;
import com.tgt.upcurve.DeliveryAPI.entity.DeliveryEntity;
import com.tgt.upcurve.DeliveryAPI.utility.JsonUtility;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DeliveryApiApplication.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class DeliveryServiceTest {
    @Autowired
    DeliveryService deliveryService;
    private static final String ORDER_JSON_FILE_PATH = "/deliveryData.json";
    private static final LocalDate DATE_PARAM= LocalDate.parse("2022-08-07");

    @Test
    public void testFindByCustomerIdAndOrderId() throws Exception {
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(ORDER_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryService.saveDelivery(delivery);
        DeliveryEntity existingDelivery = deliveryService.fetchDeliveryByCustomerIdAndOrderId(10, 15);
        assert existingDelivery != null;
    }

    @Test
    public void testFindByImageId() throws Exception {
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(ORDER_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryService.saveDelivery(delivery);
        List< DeliveryEntity> existingDelivery= deliveryService.fetchDeliveryByImageId(25);
        assert existingDelivery.size() > 0;
    }

    @Test
    public void testFindDeliveryByDate() throws Exception {
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(ORDER_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryService.saveDelivery(delivery);
        List< DeliveryEntity> existingDelivery= deliveryService.getDeliveryDetailsByDate(DATE_PARAM);
        assert existingDelivery.size() > 0;
    }

    @Test
    public void testFindDeliveryByCustomerId() throws Exception{
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(ORDER_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryService.saveDelivery(delivery);
        List< DeliveryEntity> existingDelivery = deliveryService.fetchDeliveryByCustomerId(10);
        assert existingDelivery.size() > 0;
    }

    @Test
    public void testDeleteDelivery() throws Exception{
        DeliveryEntity delivery = JsonUtility.getDeliveryRequest(ORDER_JSON_FILE_PATH);
        DeliveryEntity savedDelivery = deliveryService.saveDelivery(delivery);
        DeliveryEntity fetchedDelivery = deliveryService.fetchDeliveryByCustomerIdAndOrderId(10,15);
        assert fetchedDelivery != null;
        deliveryService.deleteDelivery(10,15);
        DeliveryEntity fetchedDelivery1 = deliveryService.fetchDeliveryByCustomerIdAndOrderId(10,15);
        assert fetchedDelivery1 == null;
    }
}
