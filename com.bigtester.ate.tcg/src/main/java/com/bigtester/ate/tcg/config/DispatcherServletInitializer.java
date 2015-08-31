/*
 * Copyright 2002-2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bigtester.ate.tcg.config;

import javax.servlet.ServletRegistration.Dynamic;

import org.eclipse.jdt.annotation.NonNull;
import org.eclipse.jdt.annotation.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// TODO: Auto-generated Javadoc
/**
 * The Class DispatcherServletInitializer.
 */
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	/**
	 * {@inheritDoc}
	*/
	@Override
	
	protected @Nullable Class<?>[] getRootConfigClasses() {//NOPMD
		return null;
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebMvcConfig.class, Neo4jConfig.class };
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	/**
	 * {@inheritDoc}
	*/
	@Override
	protected void customizeRegistration(@Nullable Dynamic registration) {
		if (null == registration) return;
		registration.setAsyncSupported(true);
	}

}
