package com.babytrak24.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.babytrak24.model.Baby;
import com.babytrak24.service.BabyService;
import com.babytrak24.util.CustomErrorType;


@RestController
@RequestMapping("/api/v1")
public class BabyTrak24RestController {
    @Autowired
    private BabyService babyService;

    public static final Logger logger = LoggerFactory.getLogger(BabyTrak24RestController.class);

    @RequestMapping(value = "")
    public List<Baby> list(ModelMap model){
       return babyService.findAll();
    }

    // -------------------Retrieve All Babies ---------------------------------------

    @RequestMapping(value = "/babies/", method = RequestMethod.GET)
    public ResponseEntity<List<Baby>> listAllUsers() {
        List<Baby> babies = babyService.findAll();
        if (babies.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Baby>>(babies, HttpStatus.OK);
    }

    // -------------------Retrieve one Baby------------------------------------------

    @RequestMapping(value = "/babies/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getBabies(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        Baby baby = babyService.findOne(id);
        if (baby == null) {
            logger.error("Baby with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Baby with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Baby>(baby, HttpStatus.OK);
    }

    // -------------------Create a Baby-------------------------------------------

    @RequestMapping(value = "/babies/", method = RequestMethod.POST)
    public ResponseEntity<?> createBaby(@RequestBody Baby baby, UriComponentsBuilder ucBuilder) {
        logger.info("Creating Baby : {}", baby);

        if (babyService.exists(Example.<Baby>of(baby, ExampleMatcher.matching()
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.ignoreCase())))) {
            logger.error("Unable to create. A Baby with email {} already exist", baby.getFirstname().concat(baby.getLastname()));
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " +
                    baby.getFirstname().concat(" ".concat(baby.getLastname())) + " already exist."),HttpStatus.CONFLICT);
        }
        babyService.save(baby);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/api/baby/{id}").buildAndExpand(baby.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    // ------------------- Update a User ------------------------------------------------

    @RequestMapping(value = "/baby/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateBaby(@PathVariable("id") long id, @RequestBody Baby baby) {
        logger.info("Updating User with id {}", id);

        Baby currentBaby = babyService.findOne(id);

        if (currentBaby == null) {
            logger.error("Unable to update. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentBaby.setFirstname(baby.getFirstname());
        currentBaby.setLastname(baby.getLastname());

        babyService.save(currentBaby);
        return new ResponseEntity<Baby>(currentBaby, HttpStatus.OK);
    }

    // ------------------- Delete a User-----------------------------------------

    @RequestMapping(value = "/baby/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteBaby(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        Baby currentBaby = babyService.findOne(id);
        if (currentBaby == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        babyService.delete(id);
        return new ResponseEntity<Baby>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Users-----------------------------

    @RequestMapping(value = "/baby/", method = RequestMethod.DELETE)
    public ResponseEntity<Baby> deleteAllBabies() {
        logger.info("Deleting All Users");

        babyService.deleteAll();
        return new ResponseEntity<Baby>(HttpStatus.NO_CONTENT);
    }
}
