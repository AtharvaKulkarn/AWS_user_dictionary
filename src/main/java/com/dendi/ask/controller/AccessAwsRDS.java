package com.dendi.ask.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import com.dendi.ask.model.User;
import com.dendi.ask.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AccessAwsRDS {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/")
	private ResponseEntity<String> getDefaultPage() {
		return new ResponseEntity<>("Welcome to home.", HttpStatus.OK);
	}

	@GetMapping("/getAllUsers")
	private ResponseEntity<ArrayList<User>> getAllUserDetails() {

		try {
			ArrayList<User> userArr = new ArrayList<User>();
			userArr = userRepository.getAllUserDetails();
			return new ResponseEntity<>(userArr, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PostMapping("/addUser")
	private ResponseEntity<String> AddUser(@RequestBody User user) {

		try {
			String result = userRepository.addUser(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping("/updateUser")
	private ResponseEntity<String> UpdateUser(@RequestBody User user) {

		try {
			String result = userRepository.updateUser(user);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteUser")
	private ResponseEntity<String> DeleteUser(@RequestParam String userName) {

		try {
			String result = userRepository.deleteUser(userName);
			return new ResponseEntity<>(result, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("Internal server error.", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/bucketItems")
	private ResponseEntity<ArrayList<String>> getAllBucketItems(@RequestParam String bucketName) {

		try {
			final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			ArrayList<String> arrayOfS3Objects = new ArrayList<>();
			ListObjectsV2Result result = s3.listObjectsV2(bucketName);
			List<S3ObjectSummary> objects = result.getObjectSummaries();
			System.out.println("ListObjectsV2Result:- " + result);
			System.out.println("S3ObjectSummary:- " + objects);
			for (S3ObjectSummary os : objects) {
				arrayOfS3Objects.add(os.getKey().toString());
			}
			return new ResponseEntity<>(arrayOfS3Objects, HttpStatus.OK);

		} catch (Exception e) {
			System.exit(1);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	
}
