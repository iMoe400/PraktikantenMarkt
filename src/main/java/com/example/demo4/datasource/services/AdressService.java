package com.example.demo4.datasource.services;

import com.example.demo4.datasource.Adress;
import com.example.demo4.datasource.User;
import com.example.demo4.datasource.repositories.AdressRepo;
import com.example.demo4.datasource.repositories.UserRepo;
import com.nimbusds.openid.connect.sdk.claims.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdressService {

    @Autowired
    AdressRepo addressRepo;

    public AdressService(AdressRepo addressRepo) {
        this.addressRepo = addressRepo;
    }

    public List<Adress> getUserById(String city) {
        return addressRepo.findAllByCity(city);
    }

}
