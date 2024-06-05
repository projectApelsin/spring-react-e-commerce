package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}