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
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord;
import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord.UserInputType;

/**
 * @author ashraf
 * 
 */
@Controller
public class TrainingFileDB implements ServletContextAware {

	/** The servlet context. */
	@Autowired
	@Nullable
	public static ServletContext servletContext;

	/** The Constant NEW_LINE_SEPARATOR. */
	// Delimiter used in CSV file
	private static final String NEW_LINE_SEPARATOR = "\n";

	/**
	 * Parses the line.
	 *
	 * @param line
	 *            the line
	 * @return the user input training record
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static WebElementTrainingRecord parseLine(String line)
			throws IOException {
		CSVParser lineParser = CSVParser.parse(line,
				TrainingFileDB.getCSVFormat());
		List<CSVRecord> csvRecords = lineParser.getRecords();
		WebElementTrainingRecord retVal = null; // NOPMD
		for (CSVRecord record : csvRecords) {
			if (null != record) {
				String temp = record.get(0);
				String temp2 = record.get(1);
				if (null == temp)
					temp = "";
				if (null == temp2)
					temp2 = "";
				retVal = new WebElementTrainingRecord(temp, // NOPMD
						temp2, UserInputType.USERINPUT);
			}
		}
		if (null == retVal)
			throw new IOException();
		return retVal;
	}

	/**
	 * Gets the CSV format.
	 *
	 * @return the CSV format
	 * @throws IOException
	 */
	public static CSVFormat getCSVFormat() throws IOException {
		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = CSVFormat.TDF // NOPMD
				.withRecordSeparator(NEW_LINE_SEPARATOR);
		csvFileFormat = csvFileFormat.withEscape('^');
		csvFileFormat = csvFileFormat.withQuoteMode(QuoteMode.NONE);
		if (null == csvFileFormat)
			throw new IOException();
		return csvFileFormat;
	}

	/**
	 * Clean test csv file.
	 *
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void cleanTestCsvFile() throws IOException {
		// initialize FileWriter object

		// FileSystemResource testFile = new
		// FileSystemResource(UserInputsTrainer.TESTFILE);

		FileWriter fileWriter = new FileWriter(UserInputsTrainer.TESTFILE);
		fileWriter.write("");
		fileWriter.close();

	}

	/**
	 * Write test csv file.
	 *
	 * @param mlInputs
	 *            the ml inputs
	 * @param append
	 *            the append
	 * @throws IOException
	 */
	public static void writeTestCsvFile(List<String> mlInputs, boolean append)
			throws IOException {

		if (mlInputs.isEmpty())
			return;

		// Create new students objects
		List<WebElementTrainingRecord> trainings = new ArrayList<WebElementTrainingRecord>();
		for (int index = 0; index < mlInputs.size(); index++) {
			String temp = mlInputs.get(index);
			if (null != temp) {
				trainings.add(new WebElementTrainingRecord(" ", temp, WebElementTrainingRecord.UserInputType.USERINPUT));
			}
		}

		FileWriter fileWriter = null; // NOPMD

		CSVPrinter csvFilePrinter = null; // NOPMD

		// Create the CSVFormat object with "\n" as a record delimiter
		CSVFormat csvFileFormat = getCSVFormat();
		try {

			// initialize FileWriter object
			// FileSystemResource testFile = new
			// FileSystemResource(UserInputsTrainer.TESTFILE);

			fileWriter = new FileWriter(UserInputsTrainer.TESTFILE, append);

			// initialize CSVPrinter object
			csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);// NOPMD

			// Write a new student object list to the CSV file
			for (WebElementTrainingRecord student : trainings) {
				List<String> studentDataRecord = new ArrayList<String>();
				studentDataRecord.add(student.getInputLabelName());
				studentDataRecord.add(student.getInputMLHtmlCode());
				csvFilePrinter.printRecord(studentDataRecord);
			}

			// System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {// NOPMD
			throw new IOException("Error in CsvFileWriter !!!");// NOPMD
			// e.printStackTrace();
		} finally {// NOPMD
			try {
				if (null != fileWriter) {
					fileWriter.flush();
					fileWriter.close();

				}
				if (null != csvFilePrinter) {
					csvFilePrinter.close();
				}
			} catch (IOException e) { // NOPMD
				throw new IOException(//NOPMD
						"Error while flushing/closing fileWriter/csvPrinter !!!"); // NOPMD
				// e.printStackTrace();
			}
		}
	}

	/**
	 * Write cache csv file.
	 *
	 * @param absoluteCacheFilePath
	 *            the absolute cache file path
	 * @param beginningComments
	 *            the beginning comments
	 * @param endingComments
	 *            the ending comments
	 * @param trainedRecords
	 *            the trained records
	 * @param append
	 *            the append
	 * @throws IOException
	 */
	public static void writeCacheCsvFile(String absoluteCacheFilePath,
			String beginningComments, String endingComments,
			List<WebElementTrainingRecord> trainedRecords, boolean append)
			throws IOException {
		// Create new students objects

		FileWriter fileWriter = null;// NOPMD

		CSVPrinter csvFilePrinter = null;// NOPMD

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
			for (WebElementTrainingRecord student : trainedRecords) {
				List<String> studentDataRecord = new ArrayList<String>();
				studentDataRecord.add(student.getInputLabelName());
				studentDataRecord.add(student.getInputMLHtmlCode());

				csvFilePrinter.printRecord(studentDataRecord);
			}
			csvFilePrinter.printComment(endingComments);
			// System.out.println("CSV file was created successfully !!!");

		} catch (Exception e) {// NOPMD
			throw new IOException("Error in CsvFileWriter !!!");// NOPMD
			// e.printStackTrace();
		} finally { //NOPMD
			try {
				if (null != fileWriter) {
					fileWriter.flush();
					fileWriter.close();
				}
				if (null != csvFilePrinter)
					csvFilePrinter.close();
			} catch (IOException e) {//NOPMD
				//System.out
				throw new IOException("Error while flushing/closing fileWriter/csvPrinter !!!");//NOPMD
				//e.printStackTrace();
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setServletContext(@Nullable ServletContext servletContext) {
		TrainingFileDB.servletContext = servletContext;

	}
}
