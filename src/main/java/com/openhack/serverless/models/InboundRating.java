package com.openhack.serverless.models;

/**
 * {
 *   "userId": "cc20a6fb-a91f-4192-874d-132493685376",
 *   "productId": "4c25613a-a3c2-4ef3-8e02-9c335eb23204",
 *   "locationName": "Sample ice cream shop",
 *   "rating": 5,
 *   "userNotes": "I love the subtle notes of orange in this ice cream!"
 * }
 */

public class InboundRating {
    public InboundRating(){

    }

    public InboundRating(String userId, String productId, String locationName, Integer rating, String userNotes) {
        this.userId = userId;
        this.productId = productId;
        this.locationName = locationName;
        this.rating = rating;
        this.userNotes = userNotes;
    }

    private String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String productId;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    private String locationName;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    private Integer rating;

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    private String userNotes;

    public String getUserNotes() {
        return userNotes;
    }

    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
