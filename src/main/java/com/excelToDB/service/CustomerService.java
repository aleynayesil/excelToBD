package com.excelToDB.service;

import com.excelToDB.domain.Customer;
import com.excelToDB.service.impl.CustomerServiceImpl;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CustomerService {
    void saveCustomersToDB(MultipartFile file);

    List<Customer> getCustomers();
}
