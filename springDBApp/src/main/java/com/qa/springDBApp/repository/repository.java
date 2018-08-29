package com.qa.springDBApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springDBApp.model.*;

@Repository
public interface repository extends JpaRepository<mySpringBootDataModel, Long> {

}
