package com.horizons.authmicroservice.repositories;

import com.horizons.authmicroservice.models.AppUser;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AppUserRepository extends MongoRepository<AppUser, String> {
    AppUser findById(ObjectId id);
    AppUser findByUsername(String username);
    AppUser deleteAppUserById(ObjectId id);
}

