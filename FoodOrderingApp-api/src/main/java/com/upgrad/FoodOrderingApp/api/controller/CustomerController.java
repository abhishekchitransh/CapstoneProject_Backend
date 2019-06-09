package com.upgrad.FoodOrderingApp.api.controller;

import com.upgrad.FoodOrderingApp.api.model.LoginResponse;
import com.upgrad.FoodOrderingApp.api.model.LogoutResponse;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerRequest;
import com.upgrad.FoodOrderingApp.api.model.SignupCustomerResponse;
import com.upgrad.FoodOrderingApp.service.business.LoginBusinessService;
import com.upgrad.FoodOrderingApp.service.business.LogoutBusinessService;
import com.upgrad.FoodOrderingApp.service.business.SignupBusinessService;
import com.upgrad.FoodOrderingApp.service.entity.CustomerAuthTokenEntity;
import com.upgrad.FoodOrderingApp.service.entity.CustomerEntity;
import com.upgrad.FoodOrderingApp.service.exception.AuthenticationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SignUpRestrictedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;
import java.util.UUID;


@RestController
@RequestMapping("/")
@CrossOrigin
public class CustomerController {
    @Autowired
    SignupBusinessService signupBusinessService;
    @Autowired
    LoginBusinessService loginBusinessService;
    @Autowired
    LogoutBusinessService logoutBusinessService;

    @RequestMapping(method = RequestMethod.POST, path = "/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<SignupCustomerResponse> signup(final SignupCustomerRequest signupCustomerRequest) throws SignUpRestrictedException{
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setUuid(UUID.randomUUID().toString());
        customerEntity.setEmail(signupCustomerRequest.getEmailAddress());
        customerEntity.setFirstname(signupCustomerRequest.getFirstName());
        customerEntity.setLastname(signupCustomerRequest.getLastName());
        customerEntity.setPassword(signupCustomerRequest.getPassword());
        customerEntity.setContact_Number(signupCustomerRequest.getContactNumber());
        CustomerEntity createdCustomer=signupBusinessService.signup(customerEntity);

        // we need to convert the user entity object to signup response object
        // as the user will get status and its id
        SignupCustomerResponse signupCustomerResponse = new SignupCustomerResponse().id(createdCustomer.getUuid())
                    .status("customer registered successfully");
        return new ResponseEntity<SignupCustomerResponse>(signupCustomerResponse ,HttpStatus.CREATED);
    }
    @RequestMapping(method= RequestMethod.POST, path="/customer/login", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LoginResponse> signIn(@RequestHeader("authorization") final String authorization) throws AuthenticationFailedException {
        byte[] decode = Base64.getDecoder().decode(authorization.split("Basic ")[1]);
        String decodedText = new String(decode);
        String[] decodedArray = decodedText.split(":");

        CustomerAuthTokenEntity customerAuthTokenEntity = loginBusinessService.authenticate(decodedArray[0],decodedArray[1]);
        CustomerEntity customer = customerAuthTokenEntity.getCustomer();
        LoginResponse loginResponse= new LoginResponse().id(customer.getUuid()).message("LOGGED IN SUCCESSFULLY");
        HttpHeaders headers = new HttpHeaders();
        // to send the auth token as header as it can not go in payload
        headers.add("access-token", customerAuthTokenEntity.getAccessToken());
        return new ResponseEntity<LoginResponse>(loginResponse , headers, HttpStatus.OK);
    }

    @RequestMapping(method=RequestMethod.POST, path="/customer/logout", consumes= MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<LogoutResponse> signOut(@RequestHeader("authorization") final String authorization) throws AuthorizationFailedException {
        CustomerEntity logoutUser = logoutBusinessService.logOut(authorization);
        LogoutResponse logoutResponse = new LogoutResponse().id(logoutUser.getUuid()).message("LOGGED OUT SUCCESSFULLY");
        return new ResponseEntity<LogoutResponse>(logoutResponse,HttpStatus.OK);
    }
}
