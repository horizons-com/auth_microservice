package com.horizons.authmicroservice.controllers;

import com.horizons.authmicroservice.models.AppUser;
import com.horizons.authmicroservice.repositories.AppUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @PostMapping("/")
    public AppUser createStudent( @Valid @RequestBody AppUser appUser) {
        appUser.setId(ObjectId.get());
        appUserRepository.save(appUser);
        return appUser;
    }

    @GetMapping("/")
    public List<AppUser> getAllUsers()  {
        return appUserRepository.findAll();
    }
}
