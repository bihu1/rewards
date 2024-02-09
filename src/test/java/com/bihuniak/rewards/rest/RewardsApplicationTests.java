package com.bihuniak.rewards.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.Month;
import java.time.ZonedDateTime;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class RewardsApplicationTests {

	@Autowired
	private MockMvc mvc;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void shouldAddTransaction() throws Exception
	{
		String content = """
			{
			    "userId" : 1,
			    "amount": 90
			}
			""";
		mvc.perform(post("/api/transactions")
			.content(content)
			.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());
	}

	@Test
	void shouldUpdateTransaction() throws Exception
	{
		String addContent = """
			{
			    "userId" : 1,
			    "amount": 90
			}
			""";
		MvcResult mvcResult = mvc.perform(post("/api/transactions")
				.content(addContent)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();
		RestTransactionId myResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), RestTransactionId.class);

		String content = """
			{
			    "userId" : 1,
			    "amount": 150
			}
			""";
		mvc.perform(put("/api/transactions/" + myResponse.id())
				.content(content)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	void shouldGet3MonthsReport() throws Exception
	{
		String addContent = """
			{
			    "userId" : 1,
			    "amount": 90
			}
			""";
		mvc.perform(post("/api/transactions")
				.content(addContent)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated());

		MockHttpServletResponse response = mvc.perform(get("/api/users/1/rewards/report"))
			.andExpect(status().isOk())
			.andReturn()
			.getResponse();
		RestRewardReport myResponse = objectMapper.readValue(response.getContentAsString(), RestRewardReport.class);
		Month month = ZonedDateTime.now().getMonth();
		assertThat(myResponse).isEqualTo(new RestRewardReport(Map.of(month, 40L), 40L));
	}

}
