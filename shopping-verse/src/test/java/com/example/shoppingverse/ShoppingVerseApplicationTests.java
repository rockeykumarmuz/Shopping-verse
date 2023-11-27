package com.example.shoppingverse;

import com.example.shoppingverse.Dto.Request.CustomerRequestDto;
import com.example.shoppingverse.Enum.Gender;
import com.example.shoppingverse.Model.Customer;
import com.example.shoppingverse.Repositry.CustomerRepositry;
import com.example.shoppingverse.Service.CustomerService;
import com.example.shoppingverse.Transformer.CustomerTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShoppingVerseApplicationTests {

	@Autowired
	CustomerService customerService;

	@Autowired
	CustomerRepositry customerRepositry;

	@Test
	void contextLoads() {
	}

	@Test
	void addCustomerTest() {
//		CustomerRequestDto originalOutput = new CustomerRequestDto("Rockey", "rockeykr@123gmail.com", "1234567890", Gender.MALE);

		customerService.addCustomer(new CustomerRequestDto("Rockey kumar", "rockeykrs@123gmail.com", "12345678910", Gender.MALE));
		Customer originalOutput = customerRepositry.findByEmailId("rockeykrs@123gmail.com");

		CustomerRequestDto expectedOutput = new CustomerRequestDto("Rockey kumar", "rockeykrs@123gmail.com", "12345678910", Gender.MALE);

		Assertions.assertEquals(originalOutput.getName(), expectedOutput.getName());
		Assertions.assertEquals(originalOutput.getGender(), expectedOutput.getGender());
		Assertions.assertEquals(originalOutput.getEmailId(), expectedOutput.getEmailId());
		Assertions.assertEquals(originalOutput.getMobNo(), expectedOutput.getMobNo());
	}


}
