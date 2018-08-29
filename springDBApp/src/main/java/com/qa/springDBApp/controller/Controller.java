package com.qa.springDBApp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.springDBApp.exceptions.ResourceNotFoundException;
import com.qa.springDBApp.model.*;
import com.qa.springDBApp.repository.repository;

@RestController
@RequestMapping("/api")
public class Controller {

	@Autowired
	repository myRepository;
	
	@PostMapping("/mySpringDataModel")
	public mySpringBootDataModel createPerson(@Valid @RequestBody mySpringBootDataModel mSDM) {
		return myRepository.save(mSDM);
	}
	
	@GetMapping("/person/{id}")
	public mySpringBootDataModel getPersonByID(@PathVariable(value = "id") Long personID) {
		return myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("mySpringBootDataModel", "id", personID));
	}
@GetMapping("/person")
	public List<mySpringBootDataModel> getAllPeople(){
		return myRepository.findAll();
	}
	
	@PutMapping("/person/{id}")
	public mySpringBootDataModel updatePerson(@PathVariable(value = "id") Long personID, @Valid @RequestBody mySpringBootDataModel personDetails) {
		mySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id",personID));
		mSDM.setName(personDetails.getName());
		mSDM.setAddress(personDetails.getAddress());
		mSDM.setAge(personDetails.getAge());
		
		mySpringBootDataModel updateData = myRepository.save(mSDM);
		return updateData;
		}
	@DeleteMapping("/person/{id}")
	public ResponseEntity<?> deletePerson(@PathVariable(value = "id")Long personID){
		mySpringBootDataModel mSDM = myRepository.findById(personID).orElseThrow(() -> new ResourceNotFoundException("Person", "id",personID));
		myRepository.delete(mSDM);
		return ResponseEntity.ok().build();
	}
}