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
package com.bigtester.ate.tcg.test.chat;//NOPMD

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.request;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;

import com.bigtester.ate.tcg.chat.ChatController;
import com.bigtester.ate.tcg.chat.ChatRepository;

// TODO: Auto-generated Javadoc
/**
 * The Class ChatControllerTests.
 */
public class ChatControllerTests {

	/** The mock mvc. */
	private transient MockMvc mockMvc;

	/** The chat repository. */
	private transient ChatRepository chatRepository;

	/**
	 * Setup.
	 */
	@Before
	public void setup() {
		this.chatRepository = EasyMock.createMock(ChatRepository.class);
		this.mockMvc = standaloneSetup(new ChatController(this.chatRepository)).build();
	}

	/**
	 * Gets the messages.
	 *
	 * @return the messages
	 * @throws Exception the exception
	 */
	@Test
	public void getMessages() throws Exception {//NOPMD
		List<String> messages = Arrays.asList("a", "b", "c");
		expect(this.chatRepository.getMessages(9)).andReturn(messages);
		replay(this.chatRepository);

		this.mockMvc.perform(get("/mvc/chat").param("messageIndex", "9"))
				.andExpect(status().isOk())
				.andExpect(request().asyncStarted())
				.andExpect(request().asyncResult(messages));

		verify(this.chatRepository);
	}

	/**
	 * Gets the messages start async.
	 *
	 * @return the messages start async
	 * @throws Exception the exception
	 */
	@Test
	public void getMessagesStartAsync() throws Exception { //NOPMD
		expect(this.chatRepository.getMessages(9)).andReturn(Arrays.<String>asList());
		replay(this.chatRepository);

		this.mockMvc.perform(get("/mvc/chat").param("messageIndex", "9"))
				.andExpect(request().asyncStarted());

		verify(this.chatRepository);
	}

}
