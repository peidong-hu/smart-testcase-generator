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

import org.eclipse.jdt.annotation.Nullable;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.stereotype.Repository;

import com.bigtester.ate.tcg.model.domain.TestCase;
import com.bigtester.ate.tcg.model.domain.TestSuite;

// TODO: We will need to add query to get test case by industry, test suites and test case name
/**
 * This class ScreenNodeRepo defines ....
 * @author Peidong Hu
 *
 */
@Repository
public interface TestSuiteRepo extends GraphRepository<TestCase> {
	
	/**
	 * Gets the web domain by domain name.
	 *
	 * @param domainName the domain name
	 * @return the web domain by domain name
	 */
	@Nullable
	TestSuite getTestSuiteByName(String testSuiteName);
}
