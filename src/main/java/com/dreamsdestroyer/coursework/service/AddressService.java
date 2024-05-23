package com.dreamsdestroyer.coursework.service;


import com.dreamsdestroyer.coursework.model.Address;
import com.dreamsdestroyer.coursework.model.repository.AddressRepository;
import org.springframework.stereotype.Service;

@Service
public class AddressService {

    AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Address saveAddress(Address address){
        return addressRepository.save(address);
    }

}
