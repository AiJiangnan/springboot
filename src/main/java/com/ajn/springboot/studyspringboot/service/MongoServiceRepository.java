package com.ajn.springboot.studyspringboot.service;

import com.ajn.springboot.studyspringboot.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author 艾江南
 */
public interface MongoServiceRepository extends MongoRepository<Customer, String> {

}
