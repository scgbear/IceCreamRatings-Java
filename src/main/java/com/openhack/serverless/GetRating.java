package com.openhack.serverless;

import java.util.*;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.openhack.serverless.models.OutboundRating;

public class GetRating {
    @FunctionName("GetRating")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.GET},
                    authLevel = AuthorizationLevel.FUNCTION) HttpRequestMessage<Optional<String>> request,
            @CosmosDBInput(name = "database",
                    databaseName = "bfyoc",
                    collectionName = "ratings",
                    sqlQuery = "select * from collection r where r.id = {ratingId}",
                    connectionStringSetting = "CosmosDbConnectionString")
            OutboundRating[] ratings,
            final ExecutionContext context) {

        if (ratings.length == 0) {
            return request.createResponseBuilder(HttpStatus.BAD_REQUEST)
                    .body("Document not found.")
                    .build();
        }
        else {
            return request.createResponseBuilder(HttpStatus.OK)
                    .header("Content-Type", "application/json")
                    .body(ratings[0])
                    .build();
        }
    }
}
