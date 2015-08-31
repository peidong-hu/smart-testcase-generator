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
package com.bigtester.ate.tcg.model.repository;

import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.bigtester.ate.tcg.model.domain.ScreenUserInputTrainingRecord;
import com.bigtester.ate.tcg.model.domain.WebElementTrainingRecord;


// TODO: Auto-generated Javadoc
/**
 * This class ScreenNodeRepo defines ....
 * @author Peidong Hu
 *
 */
@Repository
public interface UserInputTrainingRecordRepo extends GraphRepository<ScreenUserInputTrainingRecord> {
	
	/**
	 * Find by pio predict label result value.
	 *
	 * @param value the value
	 * @return the iterable
	 */
	Iterable<ScreenUserInputTrainingRecord> findByPioPredictLabelResultValue(String value);
	
	/**
	 * Find by input ml html code.
	 *
	 * @param htmlCode the html code
	 * @return the iterable
	 */
	Iterable<ScreenUserInputTrainingRecord> findByInputMLHtmlCode(String htmlCode);
	
}
