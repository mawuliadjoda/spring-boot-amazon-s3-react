package com.esprit.springbootamazons3.profile;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Repository
public class FakeUserProfileDataStore {

    private static final List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(UUID.fromString("a670a6f0-93bf-4509-8816-9d065e54948c"), "janetjones", null));
        USER_PROFILES.add(new UserProfile(UUID.fromString("9f4be9cc-fdc0-4ba2-8900-af79507599cd"), "antoniojunior", null));
    }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

}