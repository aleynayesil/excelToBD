package com.excelToDB.controller;

import com.excelToDB.domain.Customer;
import com.excelToDB.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping("/upload-customer-data")
    public ResponseEntity<?> uploadCustomersData(@RequestParam("file")MultipartFile file){
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("Message", "Customers data uploaded and saved to DB successfully");

        this.customerService.saveCustomersToDB(file);

        return ResponseEntity.ok(map);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getCustomerList(){
        return new ResponseEntity<>(customerService.getCustomers(), HttpStatus.FOUND);
    }
}
