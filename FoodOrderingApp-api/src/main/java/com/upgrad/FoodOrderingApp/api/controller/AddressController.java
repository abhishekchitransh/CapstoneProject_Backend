package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.StatesList;
import com.upgrad.FoodOrderingApp.api.model.StatesListResponse;
import com.upgrad.FoodOrderingApp.service.business.StateBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.StateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
@CrossOrigin
public class AddressController {

    @Autowired
    StateBusinessService stateBusinessService;


    @RequestMapping(method = RequestMethod.GET, path = "/states", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<List<StatesListResponse>> getAllStates(){
        List<StateEntity> SB = stateBusinessService.getAllStates();

        List<StatesListResponse> states = new ArrayList<>();

        for(StateEntity stateEntity : SB) {
            StatesList statesList = new StatesList().id(stateEntity.getUuid()).stateName(stateEntity.getState_name());
            StatesListResponse statesListResponse = new StatesListResponse().addStatesItem(statesList);
            states.add(statesListResponse);
        }

        return new ResponseEntity<List<StatesListResponse>>(states, HttpStatus.OK);

    }
}
