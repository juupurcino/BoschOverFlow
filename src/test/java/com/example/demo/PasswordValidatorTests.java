package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.services.UserService;

@SpringBootTest
class PasswordValidatorTests {

	@Autowired
	UserService service;

	@Test
	void validateSmallPass() {
		assertEquals(service.validatePassword("Abc123"), false);
		assertEquals(service.validatePassword("Abcde123456"), false);
		assertEquals(service.validatePassword("Aa1#"), false);
		assertEquals(service.validatePassword("123"), false);
		assertEquals(service.validatePassword("admin"), false);
	}
	
	@Test
	void validateNoNumberPass() {
		assertEquals(service.validatePassword("Aaaaaaaaaaaaaaaaaaaaa"), false);
		assertEquals(service.validatePassword("#############"), false);
	}

	@Test
	void validateNoLetterPass() {
		assertEquals(service.validatePassword("1234567890123"), false);
		assertEquals(service.validatePassword("#23456789012#"), false);
	}
	
	@Test
	void validateNoUppercasePass() {
		assertEquals(service.validatePassword("1234567abcdef"), false);
	}
	
	@Test
	void validateNoLowercasePass() {
		assertEquals(service.validatePassword("1234567ABCDEF"), false);
	}
	
	@Test
	void validatePass() {
		assertEquals(service.validatePassword("ABCdef123456"), true);
		assertEquals(service.validatePassword("ABCdef123456"), true);
		assertEquals(service.validatePassword("ABCdef123456###"), true);
	}
}
