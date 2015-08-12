package com.bigtester.ate.tcg.controller;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;

//import com.google.common.collect.Iterables;





import com.bigtester.ate.tcg.model.domain.UserInputTrainingRecord;

import edu.stanford.nlp.classify.Classifier;
import edu.stanford.nlp.classify.ColumnDataClassifier;
//import edu.stanford.nlp.classify.LinearClassifier;
import edu.stanford.nlp.ling.Datum;
import edu.stanford.nlp.objectbank.ObjectBank;
//import edu.stanford.nlp.util.ErasureUtils;
// TODO: Auto-generated Javadoc

/**
 * The Class UserInputsTrainer.
 */
@Controller
public class UserInputsTrainer {
	
	/** The propertyfile. */
	public final static String PROPERTYFILE = System.getProperty("user.dir") + "/trainer/userinput/fieldtest.prop";//NOPMD
	
	/** The trainingfile. */
	public final static String TRAININGFILE = System.getProperty("user.dir") + "/trainer/userinput/train.txt";
	
	/** The testfile. */
	public final static String TESTFILE = System.getProperty("user.dir") + "/trainer/userinput/test.txt";
	
	/** The cachepath. */
	public final static String CACHEPATH = System.getProperty("user.dir") + "/trainer/userinput/cache/";
	
	/**
	 * Gets the all file basenames in cache.
	 *
	 * @return the all file basenames in cache
	 */
	public static List<String> getAllFileBasenamesInCache() {
		String directory = UserInputsTrainer.CACHEPATH;
		File[] files = new File(directory).listFiles();
		List<String> filenames = new ArrayList<String>();
		
		for (File file : files) {
			if (file.isFile()) {
				filenames.add(FilenameUtils.getBaseName(file.getAbsolutePath()));
			}
		}
		return filenames;
	}
	
	
	/**
	 * Train.
	 *
	 * @return the list
	 * @throws ClassNotFoundException the class not found exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public List<UserInputTrainingRecord> train() throws ClassNotFoundException, IOException {
		//ColumnDataClassifier cdc = new ColumnDataClassifier(
		//		 PROPERTYFILE);
		//Classifier<String, String> cl = cdc.makeClassifier(cdc //NOPMD
		//		.readTrainingExamples( TRAININGFILE));
		//for (String line : ObjectBank.getLineIterator( TESTFILE, "utf-8")) {
			// instead of the method in the line below, if you have the
			// individual elements
			// already you can use cdc.makeDatumFromStrings(String[])
			//Datum<String, String> d = cdc.makeDatumFromLine(line);
			//System.out.println(line + "  ==>  " + cl.classOf(d));
		//}

		return demonstrateSerialization();
	}

	/**
	 * Demonstrate serialization.
	 *
	 * @return the list
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws ClassNotFoundException the class not found exception
	 */
	public List<UserInputTrainingRecord> demonstrateSerialization() throws IOException,
			ClassNotFoundException {
		//System.out
		//		.println("Demonstrating working with a serialized classifier");
		ColumnDataClassifier cdc = new ColumnDataClassifier(
				 PROPERTYFILE);
		Classifier<String, String> classifier = cdc.makeClassifier(cdc
				.readTrainingExamples( TRAININGFILE));
		
		//Classifier<String, String> cl2 = cdc.makeClassifier(cdc
		//		.readTrainingExamples( TESTFILE));
		
		
		// Exhibit serialization and deserialization working. Serialized to
		// bytes in memory for simplicity
		//System.out.println();
		//System.out.println();
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ObjectOutputStream oos = new ObjectOutputStream(baos);
		oos.writeObject(classifier);
		oos.close();
		byte[] object = baos.toByteArray();
		ByteArrayInputStream bais = new ByteArrayInputStream(object);
		ObjectInputStream ois = new ObjectInputStream(bais);
		//LinearClassifier<String, String> linerCls = ErasureUtils.uncheckedCast(ois //NOPMD
		//		.readObject());
		ois.close();
		//ColumnDataClassifier cdc2 = new ColumnDataClassifier(
		//		 PROPERTYFILE);

		// We compare the output of the deserialized classifier lc versus the
		// original one cl
		// For both we use a ColumnDataClassifier to convert text lines to
		// examples
		List<UserInputTrainingRecord> retVal = new ArrayList<UserInputTrainingRecord>();
		for (String line : ObjectBank.getLineIterator(
				 TESTFILE, "utf-8")) {
			Datum<String, String> datum1 = cdc.makeDatumFromLine(line);
			//Datum<String, String> datum2 = cdc2.makeDatumFromLine(line);
			String temp = classifier.classOf(datum1);
			
			if (null != line && null != temp && TrainingFileDB.parseLine(line) != null) {
				
				retVal.add(new UserInputTrainingRecord(temp,TrainingFileDB.parseLine(line).getInputMLHtmlCode()));
			}
//			System.out.println(classifier.classOf(datum1) + "  =origi=>  " + line );
//			System.out.println(cl2.classOf(datum1)+ "  =test origi=> " + line);
//			System.out.println("score against email: " + linerCls.scoreOf(datum2, "email") + "  =deser=>  " +  line );
//			System.out.println("score against firstname: " + linerCls.scoreOf(datum2, "firstname") + "  =deser=>  " +  line );
//			System.out.println("score against reenterpassword: " + linerCls.scoreOf(datum2, "reenterpassword") + "  =deser=>  " +  line );
//			System.out.println("score against password: " + linerCls.scoreOf(datum2, "password") + "  =deser=>  " +  line );
//			System.out.println("score against lastname: " + linerCls.scoreOf(datum2, "lastname") + "  =deser=>  " +  line );
//			System.out.println("score against "+ classifier.classOf(datum1) + ": " + linerCls.scoreOf(datum2, classifier.classOf(datum1)) + "  =deser=>  " +  line );
//			System.out.println("==========================");
		}
		return retVal;
	}

}
