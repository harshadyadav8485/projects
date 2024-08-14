package com.example.demo.test.service;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import com.example.demo.test.repository.EmployeeRepository;
import com.example.demo.test.entity.Employee;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public Employee saveEmployee(Employee employee) {

        Employee employee1=new Employee();

        return employeeRepository.save(employee);
    }


    public ByteArrayInputStream generateEmployeeExcel(List<Employee> employees) throws IOException {
        try (Workbook workbook = new XSSFWorkbook();
             ByteArrayOutputStream out = new ByteArrayOutputStream()) {

            Sheet sheet = workbook.createSheet("Employees");

            // Create header row
            Row headerRow = sheet.createRow(0);
            String[] headers = {"ID", "Name", "Email", "Phone Number", "Password", "Mobile Pin", "PAN Number"};
            for (int i = 0; i < headers.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Populate employee data rows
            int rowIndex = 1;
            for (Employee employee : employees) {
                Row dataRow = sheet.createRow(rowIndex++);

                dataRow.createCell(0).setCellValue(employee.getId());
                dataRow.createCell(1).setCellValue(employee.getName());
                dataRow.createCell(2).setCellValue(employee.getEmail());
                dataRow.createCell(3).setCellValue(employee.getPhoneNumber());
                dataRow.createCell(4).setCellValue(employee.getPassword());
                dataRow.createCell(5).setCellValue(employee.getMobilePin());
                dataRow.createCell(6).setCellValue(employee.getPanNumber());
            }

            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

}
