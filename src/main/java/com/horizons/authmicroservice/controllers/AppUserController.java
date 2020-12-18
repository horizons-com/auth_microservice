package com.horizons.authmicroservice.controllers;

import com.horizons.authmicroservice.models.AppUser;
import com.horizons.authmicroservice.repositories.AppUserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AppUserController {
    @Autowired
    private AppUserRepository appUserRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/")
    public AppUser createUser( @Valid @RequestBody AppUser appUser) {
        appUser.setId(ObjectId.get());
        appUser.setPassword(encoder.encode(appUser.getPassword()));
        appUserRepository.save(appUser);
        return appUser;
    }

    @GetMapping("/")
    public List<AppUser> getAllUsers()  {
        return appUserRepository.findAll();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AppUser getAppUserById(@PathVariable("id") ObjectId id) {
        return appUserRepository.findById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public AppUser deleteAppUserById(@PathVariable("id") ObjectId id) {
        return appUserRepository.deleteAppUserById(id);
    }
}
