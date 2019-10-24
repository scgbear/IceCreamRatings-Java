package com.openhack.serverless;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.openhack.serverless.models.*;
import okhttp3.*;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class CreateRating {
    @FunctionName("CreateRating")
    public HttpResponseMessage run(
            @HttpTrigger(name = "req",
                    methods = {HttpMethod.POST},
                    authLevel = AuthorizationLevel.FUNCTION
                    ) HttpRequestMessage<Optional<String>> request,
            @CosmosDBOutput(name = "database",
                    databaseName = "bfyoc",
                    collectionName = "ratings",
                    connectionStringSetting = "CosmosDbConnectionString"
            ) OutputBinding<String> outputItem,
            final ExecutionContext context) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        OutboundRating rating = new OutboundRating(mapper.readValue(request.getBody().get(), InboundRating.class));

        String jsonDocument = mapper.writeValueAsString(rating);
        Config config = ConfigFactory.load();
        String userPath = String.format("%s?userId=%s", config.getString("validation.userUrl"), rating.getUserId());
        String productPath = String.format("%s?productId=%s", config.getString("validation.productUrl"), rating.getProductId());

        AtomicReference<HttpResponseMessage> response = new AtomicReference<>(TestPath(request, userPath));
        if(null != response.get()) return response.get();
        response.set(TestPath(request, productPath));
        if(response.get() != null) return response.get();

        outputItem.setValue(jsonDocument);

        return request
                .createResponseBuilder(HttpStatus.OK)
                .body(jsonDocument)
                .header("Content-Type", "application/json")
                .build();
    }

    private HttpResponseMessage TestPath(HttpRequestMessage<Optional<String>> request, String path) throws Exception {
        Response response = HttpHelper.sendGet(path);
        if(!response.isSuccessful()) {
            if(response.code() == 404)
                return request.createResponseBuilder(HttpStatus.NOT_FOUND).body("User Id not found").build();
            else
                return request.createResponseBuilder(HttpStatus.valueOf(response.code())).body("User ID invalid").build();
        } else
            return null;

    }

}
