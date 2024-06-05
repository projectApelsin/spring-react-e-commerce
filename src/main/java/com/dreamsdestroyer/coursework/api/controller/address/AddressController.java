package com.dreamsdestroyer.coursework.api.controller.address;


import com.dreamsdestroyer.coursework.api.model.AddressRequest;
import com.dreamsdestroyer.coursework.exception.ResourceNotFoundException;
import com.dreamsdestroyer.coursework.model.Address;
import com.dreamsdestroyer.coursework.model.LocalUser;
import com.dreamsdestroyer.coursework.model.repository.LocalUserRepository;
import com.dreamsdestroyer.coursework.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost")
@RestController
@RequestMapping("/address")
public class AddressController {

    private AddressService addressService;
    private LocalUserRepository localUserRepository;

    public AddressController(AddressService addressService, LocalUserRepository localUserRepository) {
        this.addressService = addressService;
        this.localUserRepository = localUserRepository;
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@RequestBody AddressRequest addressRequest) {
        LocalUser user = localUserRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        Address address = new Address();
        address.setAddressLine1(addressRequest.getAddressLine1());
        address.setAddressLine2(addressRequest.getAddressLine2());
        address.setCity(addressRequest.getCity());
        address.setCountry(addressRequest.getCountry());
        address.setUser(user);

        Address savedAddress = addressService.saveAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }
}
