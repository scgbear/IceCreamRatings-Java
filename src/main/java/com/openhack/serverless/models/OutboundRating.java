package com.openhack.serverless.models;

import java.util.UUID;
import java.time.Instant;

/**
 * {
 *   "id": "79c2779e-dd2e-43e8-803d-ecbebed8972c",
 *   "userId": "cc20a6fb-a91f-4192-874d-132493685376",
 *   "productId": "4c25613a-a3c2-4ef3-8e02-9c335eb23204",
 *   "timestamp": "2018-05-21 21:27:47Z",
 *   "locationName": "Sample ice cream shop",
 *   "rating": 5,
 *   "userNotes": "I love the subtle notes of orange in this ice cream!"
 * }
 */
public class OutboundRating {
    public OutboundRating(){
        this.id = UUID.randomUUID().toString();
    }

    public OutboundRating(InboundRating inboundRating) {
        this.userId = inboundRating.getUserId();
        this.productId = inboundRating.getProductId();
        this.locationName = inboundRating.getLocationName();
        this.rating = inboundRating.getRating();
        this.userNotes = inboundRating.getUserNotes();
        this.timestamp = Instant.now();
        this.id = UUID.randomUUID().toString();
    }
    private String id;

    public String getId() {
        return id;
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

    private Instant timestamp;

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
