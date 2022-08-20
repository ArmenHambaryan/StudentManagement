package storage;


import exception.AutherNotFaundException;
import model.Author;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class AutherStorage {
    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        if (array.length == size) {
            extend();
        }
        array[size++] = author;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + ". " + array[i] + " ");
        }

    }

    private void extend() {
        Author[] temp = new Author[array.length + 10];
        for (int i = 0; i < array.length; i++) {
            temp[i] = array[i];
        }
        array = temp;
    }

    public int getSize() {
        return size;
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[i] = array[i + 1];

            }
            size--;
            System.out.println("auther deleted");
        } else {
            System.out.println("index out of bounds");
        }
    }


    public Author getAutherByIndex(int index) throws AutherNotFaundException {
        if (index >= 0 && index < size) {
            return array[index];
        } else
            throw new AutherNotFaundException("Auther with " + index + " index does not exist");
    }


    public void writeAuthersToExcel(String fileDir) throws IOException, InvalidFormatException {
        File directory = new File(fileDir);
        if (directory.isFile()) {
            throw new RuntimeException("fileDir must be directory");
        }
        File excelFile = new File(directory, "authers_" + System.currentTimeMillis() + ".xlsx");
        excelFile.createNewFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("authers");
        Row headerRow = sheet.createRow(0);

        Cell nameCell = headerRow.createCell(0);
        nameCell.setCellValue("name");

        Cell surnameCell = headerRow.createCell(1);
        surnameCell.setCellValue("surname");

        Cell emailCell = headerRow.createCell(2);
        emailCell.setCellValue("email");

        Cell genderCell = headerRow.createCell(3);
        genderCell.setCellValue("gender");


        for (int i = 0; i < size; i++) {
            Author author = array[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(author.getName());
            row.createCell(1).setCellValue(author.getSurname());
            row.createCell(2).setCellValue(author.getEmail());
            row.createCell(3).setCellValue(author.getGender().name());
        }

workbook.write(new FileOutputStream(excelFile));
        System.out.println("excel was created successfully");
    }
}
