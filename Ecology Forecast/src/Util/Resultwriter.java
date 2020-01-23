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

public class Resultwriter {

	private static List<String> columnstring = new ArrayList<String>();
    private static String[] columns;
    private static String outputpath = "poi-generated-file.xlsx";

	// Initializing employees data to insert into the excel file   
    
    public static void setpath(String path)
    {
    	outputpath = path;
    }
    
    public static void writeData(List<Animal> animals, int timeperiod, List<List<Integer>> animaldata, List<Integer> preddata) throws IOException{
    	// Create a Workbook
    	columnstring.add("Animals");
    	columnstring.add("Starting Pop");
    	for (int i = 0; i<timeperiod; i++)
    	{
    		columnstring.add("Year " + String.valueOf(i+1)+ " Pop");
    	}
    	columns = columnstring.toArray(new String[0]);
    	
        Workbook workbook = new XSSFWorkbook(); // new HSSFWorkbook() for generating `.xls` file

        // Create a Sheet
        Sheet sheet = workbook.createSheet("Animal Population Prediction");

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
        for (int j = 0; j<animaldata.size()-1; j++)
        {
        	List<Integer> animalpop = animaldata.get(j);
        	Row row = sheet.createRow(rowNum++);
        	
        	row.createCell(0).setCellValue(animals.get(j).getName());
        	
        	for (int k = 0; k<animalpop.size(); k++)
        	{
        		row.createCell(k+1).setCellValue(String.valueOf(animalpop.get(k)));
        	}
        }

        if (preddata.size() > 0)
        {
        	Row predrow = sheet.createRow(rowNum);
            predrow.createCell(0).setCellValue("Grey Wolves");
            for (int k = 0; k<preddata.size(); k++)
            {
            	predrow.createCell(k+1).setCellValue(String.valueOf(preddata.get(k)));
            }
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