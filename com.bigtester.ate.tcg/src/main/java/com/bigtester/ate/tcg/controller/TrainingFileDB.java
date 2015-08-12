/*******************************************************************************
 * ATE, Automation Test Engine
 *
 * Copyright 2015, Montreal PROT, or individual contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Montreal PROT.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.bigtester.ate.tcg.controller;

// TODO: Auto-generated Javadoc
/**
 * This class TestCSV defines ....
 * @author Peidong Hu
 *
 */

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.bigtester.ate.tcg.model.UserInputTrainingRecord;

/**
 * @author ashraf
 * 
 */
@Controller
public class TrainingFileDB implements ServletContextAware {
	
	/** The servlet context. */
	@Autowired
	public static ServletContext servletContext;
	
	/** The Constant NEW_LINE_SEPARATOR. */
	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * Parses the line.
	 *
	 * @param line the line
	 * @return the user input training record
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static UserInputTrainingRecord parseLine(String line)
			throws IOException {
		CSVParser lineParser = CSVParser.parse(line,
				TrainingFileDB.getCSVFormat());
		List<CSVRecord> csvRecords = lineParser.getRecords();
		UserInputTrainingRecord retVal = null;
		for (CSVRecord record : csvRecords) {
			retVal = new UserInputTrainingRecord(record.get(0), record.get(1));
		}
		return retVal;
	}

	/**
	 * Gets the CSV format.
	 *
	 * @return the CSV format
	 */
	public static CSVFormat getCSVFormat() {
		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.TDF
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		csvFileFormat = csvFileFormat.withEscape('^');
		csvFileFormat = csvFileFormat.withQuoteMode(QuoteMode.NONE);
		return csvFileFormat;
	}

	/**
	 * Clean test csv file.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void cleanTestCsvFile() throws IOException {
		// initialize FileWriter object
			
		//FileSystemResource testFile = new FileSystemResource(UserInputsTrainer.TESTFILE);
		
		FileWriter fileWriter = new FileWriter(UserInputsTrainer.TESTFILE);
		fileWriter.write("");
		fileWriter.close();
		
	}
	
	/**
	 * Write test csv file.
	 *
	 * @param mlInputs the ml inputs
	 * @param append the append
	 */
	public static void writeTestCsvFile(List<String> mlInputs, boolean append) {

		if (mlInputs.isEmpty())
			return;

		// Create new students objects
		List<UserInputTrainingRecord> trainings = new ArrayList<UserInputTrainingRecord>();
		for (int index = 0; index < mlInputs.size(); index++) {
			trainings
					.add(new UserInputTrainingRecord(" ", mlInputs.get(index)));
		}

		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = getCSVFormat();
		try {

			// initialize FileWriter object
			//FileSystemResource testFile = new FileSystemResource(UserInputsTrainer.TESTFILE);
			
			fileWriter = new FileWriter(UserInputsTrainer.TESTFILE, append);
			
			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Write a new student object list to the CSV file
			for (UserInputTrainingRecord student : trainings) {
				List<String> studentDataRecord = new ArrayList<String>();
				studentDataRecord.add(student.getInputLabelName());
				studentDataRecord.add(student.getInputMLHtmlCode());
				csvFilePrinter.printRecord(studentDataRecord);
			}

			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * Write cache csv file.
	 *
	 * @param absoluteCacheFilePath the absolute cache file path
	 * @param beginningComments the beginning comments
	 * @param endingComments the ending comments
	 * @param trainedRecords the trained records
	 * @param append the append
	 */
	public static void writeCacheCsvFile(String absoluteCacheFilePath,
			String beginningComments, String endingComments,
			List<UserInputTrainingRecord> trainedRecords, boolean append) {
		// Create new students objects

		FileWriter fileWriter = null;

		CSVPrinter csvFilePrinter = null;

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = getCSVFormat();
		try {
			if (trainedRecords.isEmpty()) {
				fileWriter = new FileWriter(absoluteCacheFilePath, append);

				// initialize CSVPrinter object
				csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

				// Write a new student object list to the CSV file
				csvFilePrinter.printComment(beginningComments);
				csvFilePrinter.printComment(endingComments);

				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
				return;
			}

			// initialize FileWriter object
			fileWriter = new FileWriter(absoluteCacheFilePath, append);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);

			// Write a new student object list to the CSV file
			csvFilePrinter.printComment(beginningComments);
			for (UserInputTrainingRecord student : trainedRecords) {
				List<String> studentDataRecord = new ArrayList<String>();
				studentDataRecord.add(student.getInputLabelName());
				studentDataRecord.add(student.getInputMLHtmlCode());

				csvFilePrinter.printRecord(studentDataRecord);
			}
			csvFilePrinter.printComment(endingComments);
			System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out
						.println("Error while flushing/closing fileWriter/csvPrinter !!!");
				e.printStackTrace();
			}
		}
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	public void setServletContext(ServletContext servletContext) {
		TrainingFileDB.servletContext = servletContext;
		
	}
}
