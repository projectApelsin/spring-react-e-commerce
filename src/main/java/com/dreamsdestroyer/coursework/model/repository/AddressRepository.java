package com.dreamsdestroyer.coursework.model.repository;


import com.dreamsdestroyer.coursework.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}