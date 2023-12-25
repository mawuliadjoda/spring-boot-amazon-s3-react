package com.esprit.springbootamazons3.profile;

import com.esprit.springbootamazons3.s3.S3Buckets;
import com.esprit.springbootamazons3.s3.S3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class UserProfileService {
    private final UserProfileDataAccessService userProfileDataAccessService;

    private S3Service s3Service;
    private S3Buckets s3Buckets;

    @Autowired
    public UserProfileService(UserProfileDataAccessService userProfileDataAccessService, S3Service s3Service, S3Buckets s3Buckets) {
        this.userProfileDataAccessService = userProfileDataAccessService;
        this.s3Service = s3Service;
        this.s3Buckets = s3Buckets;
    }

    List<UserProfile> getUserProfiles() {
        return userProfileDataAccessService.getUserProfiles();
    }

    public void uploadUserProfileImage(UUID userProfileId, MultipartFile file)  {
        // 1. Check if image is not empty
        isFileEmpty(file);
        // 2. If file is an image
        isImage(file);

        // 3. The user exist in our database
        UserProfile user = getUserProfileOrThrow(userProfileId);


        // 4. Grab some metadata from file if any
        Map<String, String> metadata = extractMetaData(file);

        // 5. Store the image in s3


        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        /* v2 */
        String profileImageId = UUID.randomUUID().toString();
        String key = "profile-image/%s/%s".formatted(user.getUserProfileId(), profileImageId);
        /* end v2 */
        try {
            s3Service.putObject(
                    s3Buckets.getCustomer(),
                    key,
                    file.getBytes());

            user.setUserProfileImageLink(key);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    byte[] downloadUserProfileImage(UUID userProfileId) {
        UserProfile user = getUserProfileOrThrow(userProfileId);

        /* v2 */
        String key = user.getUserProfileImageLink().get();

        byte[] profileImage = s3Service.getObject(
                s3Buckets.getCustomer(),
                key
        );

        return profileImage;
    }

    private static Map<String, String> extractMetaData(MultipartFile file) {
        Map<String, String>  metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        return metadata;
    }

    private UserProfile getUserProfileOrThrow(UUID userProfileId) {
        return userProfileDataAccessService.getUserProfiles().stream()
                .filter(userProfile -> userProfile.getUserProfileId().equals(userProfileId))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("User profile %s not found", userProfileId)));
    }

    private static void isImage(MultipartFile file) {
        if (!Arrays.asList(
                IMAGE_PNG.getMimeType(),
                IMAGE_JPEG.getMimeType(),
                IMAGE_GIF.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File must be an image");
        }
    }

    private static void isFileEmpty(MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }
    }
}
