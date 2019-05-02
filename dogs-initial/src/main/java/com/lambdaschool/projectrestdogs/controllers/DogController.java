package com.lambdaschool.projectrestdogs.controllers;

import com.lambdaschool.projectrestdogs.ProjectrestdogsApplication;
import com.lambdaschool.projectrestdogs.exceptions.ResourceNotFoundException;
import com.lambdaschool.projectrestdogs.models.Dog;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/dogs")
public class DogController
{

    private static final Logger logger = LoggerFactory.getLogger(Dog.class);
    // localhost:6969/dogs/alldogs
    @GetMapping(value = "/dogs")
    public ResponseEntity<?> getAllDogs()
    {
        logger.info("message: all the doggies");
        return new ResponseEntity<>(ProjectrestdogsApplication.ourDogList.dogList, HttpStatus.OK);
    }

    // localhost:6969/dogs/{id}
    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getDogDetail(@PathVariable long id)
    {
        Dog rtnDog;
        logger.info("message: " + id + " doggy viewed homie");

        if ((ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId()) == id)) == null) {

            throw new ResourceNotFoundException(id + "aint no doggy");

        } else {

            rtnDog = ProjectrestdogsApplication.ourDogList.findDog(e -> (e.getId() == id));
        }
        return new ResponseEntity<>(rtnDog, HttpStatus.OK);
    }

    // localhost:6969/dogs/breeds/{breed}
    @GetMapping(value = "/breeds/{breed}")
    public ResponseEntity<?> getDogBreeds (@PathVariable String breed) {
        ArrayList<Dog> rtnDogs = ProjectrestdogsApplication.ourDogList.
                findDogs(d -> d.getBreed().toUpperCase().equals(breed.toUpperCase()));

        logger.info("message:" + breed + " is the doggy you lookin at homie");

        return new ResponseEntity<>(rtnDogs, HttpStatus.OK);

    }

    // localhost:2019/dog/table
    @GetMapping(value = "/table")
    public ModelAndView displayDogTable()
    {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("dogs");
        mav.addObject("dogList", ProjectrestdogsApplication.ourDogList.dogList);
        return mav;
    }


}
