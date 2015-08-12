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
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import com.bigtester.ate.tcg.model.domain.UserInputTrainingRecord;
import com.google.common.collect.ImmutableMap;
import com.google.gson.Gson;
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
public class PredictionIOTrainer {
	public static final String SAMPLETEXTCLASSIFIERACCESSKEY = "qdQkFtusKxikK8n3L7fjorqbTeNIeRa7z2NbXwvsd4m95WSC6kVPuKNrw4baiJTl";
	public static final String EVENTSERVERURL = "http://172.16.173.50:7070";
	public static final String ENGINESERVERURL = "http://172.16.173.50:8000";
	public static List<String> categories = new ArrayList<String>();

	public static String sentTrainingEntity(UserInputTrainingRecord record)
			throws ExecutionException, InterruptedException, IOException {
		EventClient client = new EventClient(SAMPLETEXTCLASSIFIERACCESSKEY,
				EVENTSERVERURL);

		Event event = new Event()
				.event("userfield")
				.entityType("userfieldentity")
				.entityId(UUID.randomUUID().toString())
				.properties(
						ImmutableMap.<String, Object> of("label",
								toDouble(record.getInputLabelName()), "text",
								record.getInputMLHtmlCode(), "category",
								record.getInputLabelName()));
		String retVal = client.createEvent(event);
		client.close();
		return retVal;
	}

	private static Double toDouble(String str) {
		Double retVal;
		Integer index = categories.indexOf(str);
		if (index.equals(-1)) {
			categories.add(str);
			retVal = ((Integer) categories.indexOf(str)).doubleValue();
		} else {
			retVal = index.doubleValue();
		}
		return retVal;
	}

	public static UserInputTrainingRecord quertEntity(
			UserInputTrainingRecord record) throws ExecutionException,
			InterruptedException, IOException {
		EngineClient client = new EngineClient(ENGINESERVERURL);

		JsonObject jObj = client.sendQuery(ImmutableMap.<String, Object> of(
				"text", record.getInputMLHtmlCode()));
		client.close();
		record.setPioPredictLabelResult(jObj.get("category").getAsString());
		record.setPioPredictConfidence(jObj.get("confidence").getAsDouble());

		return record;
	}
}
