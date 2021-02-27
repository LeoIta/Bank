package com.finalproject.BankApplication.repository;

import com.finalproject.BankApplication.model.Address;
import com.finalproject.BankApplication.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
    public Address findByStreet(String street);
}
