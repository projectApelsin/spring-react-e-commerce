/*package com.dreamsdestroyer.CourseWork.api.controller.address;

import com.dreamsdestroyer.CourseWork.api.model.AddressRequest;
import com.dreamsdestroyer.CourseWork.exception.ResourceNotFoundException;
import com.dreamsdestroyer.CourseWork.model.Address;
import com.dreamsdestroyer.CourseWork.model.LocalUser;
import com.dreamsdestroyer.CourseWork.model.repository.LocalUserRepository;
import com.dreamsdestroyer.CourseWork.service.AddressService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
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
*/