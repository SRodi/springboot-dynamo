package com.mapper.dynamo.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.mapper.dynamo.model.AppToSchema;
import com.mapper.dynamo.model.Schema;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class Dynamo {

    private  AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    public Dynamo() {
        client = AmazonDynamoDBClientBuilder.standard().build();
        mapper = new DynamoDBMapper(client);
    }


    public AppToSchema read(String appName){

        return mapper.load(AppToSchema.class, appName);
    }

    public AppToSchema write(AppToSchema appToSchema){

        mapper.save(appToSchema);
        return appToSchema;
    }

    public AppToSchema update(String appName, Schema newSchema){

        AppToSchema appRetrieved= mapper.load(AppToSchema.class, appName);

        // if no versioned schema initialize array and increment from base version
        if(appRetrieved.getSchemas() == null){
            appRetrieved.setSchemas(new ArrayList<>());
            newSchema.incrementVersion(appRetrieved.getSchema());
        }
        else // increment from last versioned schema
            newSchema.incrementVersion(appRetrieved.getSchemas().get(appRetrieved.getSchemas().size()-1));

        appRetrieved.getSchemas().add(newSchema);

        mapper.save(appRetrieved);

        return appRetrieved;

    }
}