package com.excelToDB.service.impl;

import com.excelToDB.domain.Customer;
import com.excelToDB.repository.CustomerRepository;
import com.excelToDB.service.CustomerService;
import com.excelToDB.service.ExcelUploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public void saveCustomersToDB(MultipartFile file) {
        if (ExcelUploadServiceImpl.isValidExcelFile(file)){
            try {
                List<Customer> customers =
                        ExcelUploadServiceImpl.getCustomersDataFromExcel(file.getInputStream());
                this.customerRepository.saveAll(customers);

            } catch (IOException e) {
                throw new IllegalArgumentException("The file is not a valid excel file");
            }
        }
    }

    @Override
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }


}
