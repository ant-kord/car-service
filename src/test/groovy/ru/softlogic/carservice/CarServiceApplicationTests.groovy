package ru.softlogic.carservice

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;

import ru.softlogic.carservice.model.Order;
import ru.softlogic.carservice.service.CarService;
import ru.softlogic.carservice.service.OrderService;

@AutoConfigureMockMvc
@SpringBootTest
@ActiveProfiles("test")
class CarServiceApplicationTests {

	String noScopesToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJzdWJqZWN0IiwiaWF0IjoxNTE2MjM5MDIyfQ.xxochATBWZHO_3QSoFnms1GbFP6Ar_P8bikVGSeNyEtnkgGM9YVe5k40KcFmKjCtMf9ZPBmqqq10j_GudBF-PNtls9DY4i0mEsBckTObNc3JaUFynRWWuNsoA-GPdu6fveb-hwnbpKHiG5mXz73DqqRcyHYEMQmkoHN6PK3iL121ihpQMafUpmxLos1J9AzvFUmWcNDnO-3zXpcx4QBwXbd6Vtu5HKTNJmvWRy7lcFMS_MpeYOrhm9EWXeQAPzFQrbKsX0eVWLxQHygS7j6DhZnG3B4fTvrkSJzdiQ_0HycwzTao9jko3My1PRJ0kAF5NtVEZwb3HGhoAq5Br30IHg";

	String serviceScopeToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiLQnNC40YXQsCDQnNC10YXQsNC90LjQuiIsImlhdCI6MTUxNjIzOTAyMiwic2NvcGUiOiJzZXJ2aWNlIn0.ikL_nWMX17XIqv7an9bCQ8a_bcaXB1HqmtRwgNRP5CkfXkKirpr1XUSAjua6MgJ62udR0kWQNAQe48NcmYszAoaANnN-97y6sKpzVqbxDpkANkfycb_LO_M0UBZIfQ6OKX1XDwCbWuT9E20XfH4CB-IIW77B8rmOMS3EgOxN6xiZ4lxh0GOcafKTCmJYHCnnLIcnNLPV9gXwV3MmLj2gEAoHlAIbjmzJpMsHMkMb-TPQ1vGcBIvSIIjAyYnbErGKZ4q3V-Vs2fp9kABrtew94E8d6a7n_GfnFRY2qVkUT_jbJyOn5p4OsFjXcEwE7-iyqg9AfatbugEaEccgCZR4DQ";

	@Autowired
	MockMvc mvc;

	@MockBean
	OrderService orderService;

	@MockBean
	CarService carService;

	@Test
	void testOrderToken() throws Exception {

		this.mvc.perform(get("/api/v1/service/port").with(bearerToken(this.serviceScopeToken)))
				.andExpect(status().isOk()).andExpect(content().string(containsString("Service is working at port")));
	}

	@Test
	void testNoScopeToken() throws Exception {

		this.mvc.perform(get("/api/v1/service/port").with(bearerToken(this.noScopesToken)))
				.andExpect(status().isForbidden()).andExpect(header().string(HttpHeaders.WWW_AUTHENTICATE,
						containsString("Bearer error=\"insufficient_scope\"")));
	}

	@Test
	void testAddOrder() throws Exception {

		String orderJson = 
		'{"car": {"brand": "Subaru", "model": "Tribeca"},"mechanic": {"firstName": "Иван", "lastName": "Иванов"}}';

		this.mvc.perform(post("/api/v1/service/order/add").with(bearerToken(this.serviceScopeToken))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(orderJson))
				.andExpect(status().isCreated());

		verify(orderService).add(any(Order.class));
	}

	@Test
	public void testGetOrders() throws Exception {
		this.mvc.perform(get("/api/v1/service/order/list").with(bearerToken(this.serviceScopeToken))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	public void testGetCars() throws Exception {
		this.mvc.perform(get("/api/v1/service/car/list").with(bearerToken(this.serviceScopeToken))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());

	}

	@Test
	void testInvalidAddOrder() throws Exception {

		String orderJson = 
		'{"car": {"brand": "Subaru"},"mechanic": {"firstName": "Иван", "lastName": "Иванов"}}';

		this.mvc.perform(post("/api/v1/service/order/add").with(bearerToken(this.serviceScopeToken))
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(orderJson))
				.andDo(print())
				.andExpect(status().isBadRequest());

	}
	
	private static BearerTokenRequestPostProcessor bearerToken(String token) {
		return new BearerTokenRequestPostProcessor(token);
	}

	private static class BearerTokenRequestPostProcessor implements RequestPostProcessor {

		private String token;

		BearerTokenRequestPostProcessor(String token) {
			this.token = token;
		}

		@Override
		public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
			request.addHeader("Authorization", "Bearer " + this.token);
			return request;
		}

	}

	@Test
	void contextLoads() {
	}

}

