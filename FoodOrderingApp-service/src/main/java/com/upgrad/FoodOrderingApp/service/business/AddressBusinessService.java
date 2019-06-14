package com.upgrad.FoodOrderingApp.service.business;

import com.upgrad.FoodOrderingApp.service.dao.AddressDao;
import com.upgrad.FoodOrderingApp.service.dao.CustomerDao;
import com.upgrad.FoodOrderingApp.service.dao.StateDao;
import com.upgrad.FoodOrderingApp.service.entity.AddressEntity;
import com.upgrad.FoodOrderingApp.service.exception.AddressNotFoundException;
import com.upgrad.FoodOrderingApp.service.exception.AuthorizationFailedException;
import com.upgrad.FoodOrderingApp.service.exception.SaveAddressException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressBusinessService {

    @Autowired
    AddressDao addressDao;

    @Autowired
    StateDao  stateDao;

    @Autowired
    CustomerDao customerDao;

    public AddressEntity saveAddress(AddressEntity addressEntity) throws AuthorizationFailedException, AddressNotFoundException, SaveAddressException {

    }
}
