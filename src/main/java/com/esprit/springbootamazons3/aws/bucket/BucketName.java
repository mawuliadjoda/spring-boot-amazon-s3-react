package com.esprit.springbootamazons3.aws.bucket;

public enum BucketName {
    PROFILE_IMAGE("connect-228-file-upload");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
