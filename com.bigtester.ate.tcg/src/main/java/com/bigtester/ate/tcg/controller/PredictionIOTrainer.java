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

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javassist.bytecode.Descriptor.Iterator;

import com.bigtester.ate.tcg.model.domain.HTMLSource;
import com.bigtester.ate.tcg.model.domain.UserInputTrainingRecord;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;

import io.prediction.EngineClient;
import io.prediction.Event;
import io.prediction.EventClient;

// TODO: Auto-generated Javadoc
/**
 * This class PredictionIOTrainer defines ....
 * 
 * @author Peidong Hu
 *
 */
final public class PredictionIOTrainer {
	
	/** The Constant SAMPLETEXTCLASSIFIERACCESSKEY. */
	//public static final String SAMPLETEXTCLASSIFIERACCESSKEY = "qdQkFtusKxikK8n3L7fjorqbTeNIeRa7z2NbXwvsd4m95WSC6kVPuKNrw4baiJTl";
	public static final String SAMPLETEXTCLASSIFIERACCESSKEY = "KoKbS9tPyzLxZpaRunfoCUWNxd4o7YOK4h29nPTnMPKHykBDyDZuaS74CFd3AX1o";
	  
	/** The Constant EVENTSERVERURL. */
	public static final String EVENTSERVERURL = "http://172.16.173.50:7070";
	
	/** The Constant ENGINESERVERURL. */
	public static final String ENGINESERVERURL = "http://172.16.173.50:8000";
	
	/** The categories. */
	public static List<String> categories = new ArrayList<String>();

	/**
	 * Instantiates a new prediction io trainer.
	 */
	private PredictionIOTrainer(){};
	
	/**
	 * Sent training entity.
	 *
	 * @param record the record
	 * @return the string
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String sentTrainingEntity(UserInputTrainingRecord record)
			throws ExecutionException, InterruptedException, IOException {
		EventClient client = new EventClient(SAMPLETEXTCLASSIFIERACCESSKEY,
				EVENTSERVERURL);

		Event event = new Event()
		.event("$set")
		.entityType("phrase")
		.entityId(UUID.randomUUID().toString())
		.properties(
				ImmutableMap.<String, Object> of( "phrase",
						record.getInputMLHtmlCode(), "Interest",
						record.getInputLabelName()));
		
//		Event event = new Event()
//				.event("userfield")
//				.entityType("userfieldentity")
//				.entityId(UUID.randomUUID().toString())
//				.properties(
//						ImmutableMap.<String, Object> of("label",
//								toDouble(record.getInputLabelName()), "text",
//								record.getInputMLHtmlCode(), "category",
//								record.getInputLabelName()));
		String retVal = client.createEvent(event);
		client.close();
		if (null == retVal) {
			retVal = "";
		} 
		return retVal;
	}

	/**
	 * Sent training entity.
	 *
	 * @param pageFrames the page frames
	 * @param screenName the screen name
	 * @return the string
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String sentTrainingEntity(Set<HTMLSource> pageFrames, String screenName)
			throws ExecutionException, InterruptedException, IOException {
		EventClient client = new EventClient(SAMPLETEXTCLASSIFIERACCESSKEY,
				EVENTSERVERURL);
		
		StringBuilder tmp = new StringBuilder("");
		for (java.util.Iterator<HTMLSource> itr=pageFrames.iterator(); itr.hasNext();){
			tmp.append(" ");//NOPMD
			tmp.append(itr.next().getDocText());
		}
		
		Event event = new Event()
		.event("$set")
		.entityType("phrase")
		.entityId(UUID.randomUUID().toString())
		.properties(
				ImmutableMap.<String, Object> of("phrase",
						tmp.toString(), "Interest",
						screenName));
		
//		Event event = new Event()
//				.event("userfield")
//				.entityType("userfieldentity")
//				.entityId(UUID.randomUUID().toString())
//				.properties(
//						ImmutableMap.<String, Object> of("label",
//								toDouble(screenName), "text",
//								tmp.toString(), "category",
//								screenName));
		String retVal = client.createEvent(event);
		client.close();
		if (null == retVal) {
			retVal = "";
		} 
		return retVal;
	}

	
	/**
	 * To double.
	 *
	 * @param str the str
	 * @return the double
	 */
	private static Double toDouble(String str) {
		Double retVal;
		Integer index = categories.indexOf(str);
		if (index.equals(-1)) {
			categories.add(str);
			retVal = ((Integer) categories.indexOf(str)).doubleValue();
		} else {
			retVal = index.doubleValue();
		}
		if (null == retVal) retVal = 0.0;
		return retVal;
	}

	/**
	 * Quert entity.
	 *
	 * @param record the record
	 * @return the user input training record
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static UserInputTrainingRecord queryEntity(
			UserInputTrainingRecord record) throws ExecutionException,
			InterruptedException, IOException {
		EngineClient client = new EngineClient(ENGINESERVERURL);

		JsonObject jObj = client.sendQuery(ImmutableMap.<String, Object> of(
				"sentence", record.getInputMLHtmlCode()));
		client.close();
		String cat = jObj.get("interest").getAsString();
		if (null == cat) cat = "";
		record.setPioPredictLabelResult(cat);
		Double con = jObj.get("confidence").getAsDouble();
		if (null == con) con = 0.0;
//		Double con = 0.0;
		record.setPioPredictConfidence(con);

		return record;
	}
	
	/**
	 * Query entity.
	 *
	 * @param pageFrames the page frames
	 * @return the map
	 * @throws ExecutionException the execution exception
	 * @throws InterruptedException the interrupted exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Map<String, Double> queryEntity(
			Set<HTMLSource> pageFrames) throws ExecutionException,
			InterruptedException, IOException {
		EngineClient client = new EngineClient(ENGINESERVERURL);
		StringBuilder tmp = new StringBuilder("");
		for (java.util.Iterator<HTMLSource> itr=pageFrames.iterator(); itr.hasNext();){
			tmp.append(" ");
			tmp.append(itr.next().getDocText());
		}
		JsonObject jObj = client.sendQuery(ImmutableMap.<String, Object> of(
				"sentence", tmp.toString()));
		client.close();
		String cat = jObj.get("interest").getAsString();
		if (null == cat) cat = "";
		
		Double con = jObj.get("confidence").getAsDouble();
		if (null == con) con = 0.0;
		//Double con = 0.0;
		Map<String, Double> retVal = new HashMap<String, Double>();
		retVal.put(cat, con);
		return retVal;
	}
}
