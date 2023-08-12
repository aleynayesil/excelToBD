package com.excelToDB.service.impl;

import com.excelToDB.domain.Customer;
import com.excelToDB.service.ExcelUploadService;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Service
public class ExcelUploadServiceImpl implements ExcelUploadService {

    public static boolean isValidExcelFile(MultipartFile file) {
        //excel MIME Type
        return Objects.equals(file.getContentType(),"application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Customer> getCustomersDataFromExcel(InputStream inputStream) {
        List<Customer> customerList = new ArrayList<>();

        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("customers");

            int rowIndex = 0;
            for(Row row : sheet){
                if(rowIndex == 0){
                    rowIndex++;
                    continue;
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;

                Customer customer = new Customer();

                while (cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    switch (cellIndex){
                        case 1 : customer.setFirstName(cell.getStringCellValue());
                            break;
                        case 2 : customer.setLastName(cell.getStringCellValue());
                            break;
                        case 3 : customer.setCountry(cell.getStringCellValue());
                            break;
                        case 4 : customer.setTelephone((int)cell.getNumericCellValue());
                            break;
                        default:
                    }
                    cellIndex++;
                }
                customerList.add(customer);
            }
        } catch (IOException e) {
            e.getStackTrace();
        }
        return customerList;
    }

}
