package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.BaseTest;
import com.ajn.springboot.studyspringboot.model.Customer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MongoServiceTest extends BaseTest {

    final private Logger logger = LoggerFactory.getLogger(MongoServiceTest.class);

//    @Autowired
//    private MongoServiceRepository mongoServiceRepository;

    @Test
    public void query() {
        Customer customer = new Customer();
        customer.setId("1");
        customer.setFirstName("Ai");
        customer.setLastName("Jiangnan");
        logger.info("------------exec-------------");
//        mongoServiceRepository.save(customer);
    }

}
