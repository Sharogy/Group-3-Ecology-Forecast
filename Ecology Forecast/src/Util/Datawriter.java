package Util;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Animal;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Datawriter {

    private static String[] columns = {"Name", "Pop", "Growth", "Death", "Consumption"};
    private static String outputpath = "poi-generated-file.xlsx";

	// Initializing employees data to insert into the excel file   
    
    public static void setpath(String path)
    {
    	outputpath = path;
    }
    
    public static void writeData(List<Animal> animals) throws IOException{
    	// Create a Workbook
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        /* CreationHelper helps us create instances of various things like DataFormat, 
           Hyperlink, RichTextString etc, in a format (HSSF, XSSF) independent way */
        CreationHelper createHelper = workbook.getCreationHelper();

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Animals");

        // Create a Font for styling header cells
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 14);
        headerFont.setColor(IndexedColors.RED.getIndex());

        // Create a CellStyle with the font
        CellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Create a Row
        Row headerRow = sheet.createRow(0);

        // Create cells
        for(int i = 0; i < columns.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(columns[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Create Other rows and cells with employees data
        int rowNum =1;
        for (Animal animal: animals)
        {
        	Row row = sheet.createRow(rowNum++);
        	
        	row.createCell(0).setCellValue(animal.getName());
        	
        	row.createCell(1).setCellValue(animal.getNumber());
        	
        	row.createCell(2).setCellValue(animal.getGrowthrate());
        	
        	row.createCell(3).setCellValue(animal.getDeathrate());
        	
        	row.createCell(4).setCellValue(animal.getConsumptionrate());
        }

		// Resize all columns to fit the content size
        for(int i = 0; i < columns.length; i++) {
            sheet.autoSizeColumn(i);
        }

        // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream(outputpath);
        workbook.write(fileOut);
        fileOut.close();

        // Closing the workbook
        workbook.close();
    }
    	
}