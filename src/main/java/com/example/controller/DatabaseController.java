package com.example.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.db.dto.LoginInfo;
import com.example.db.repositories.LoginInfoRepository;
import com.example.vo.PersonInfo;

@RestController
@CrossOrigin
public class DatabaseController {

	@Autowired
	LoginInfoRepository loginInfoRepo;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@RequestMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String addUser(@RequestBody PersonInfo person) {

		if (person != null) {
			System.out.println("Input " + person.toString());
			if (StringUtils.isEmpty(person.getName()) && StringUtils.isEmpty(person.getPassword())) {
				throw new IllegalArgumentException("Request parameter username or password cannot be null");
			} else {
				LoginInfo loginInfo = new LoginInfo();

				loginInfo.setLoginId(new Random().nextInt());

				loginInfo.setLoginName(person.getName());
				loginInfo.setLoginPassword(bcryptEncoder.encode(person.getPassword()));

				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				loginInfo.setLoginUpdatedDate(date);

				loginInfoRepo.save(loginInfo);
			}
		} else {
			throw new IllegalArgumentException("Payload cannot be null");
		}

		return person.toString();
	}

	@RequestMapping(value = "/updateUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String updateUser(@RequestBody PersonInfo person) {

		if (person != null) {
			System.out.println("Input " + person.toString());
			if (StringUtils.isEmpty(person.getName()) && StringUtils.isEmpty(person.getPassword())) {
				throw new IllegalArgumentException("Request parameter username or password cannot be null");
			} else {
				LoginInfo loginInfo = new LoginInfo();

				loginInfo.setLoginId(new Random().nextInt());

				loginInfo.setLoginName(person.getName());
				loginInfo.setLoginPassword(bcryptEncoder.encode(person.getPassword()));

				long millis = System.currentTimeMillis();
				java.sql.Date date = new java.sql.Date(millis);
				loginInfo.setLoginUpdatedDate(date);

				loginInfoRepo.save(loginInfo);
			}
		} else {
			throw new IllegalArgumentException("Payload cannot be null");
		}

		return person.toString();
	}
}