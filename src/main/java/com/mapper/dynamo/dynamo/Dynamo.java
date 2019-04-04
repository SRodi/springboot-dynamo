package com.mapper.dynamo.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.mapper.dynamo.model.AppToSchema;
import org.springframework.stereotype.Component;
import java.util.ArrayList;

@Component
public class Dynamo {

    private  AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    /**
     * construct Dynamo instance:
     *  - initializes AmazonDynamoDB client
     *  - initializes DynamoDBMapper
     */
    public Dynamo() {
        client = AmazonDynamoDBClientBuilder.standard().build();
        mapper = new DynamoDBMapper(client);
    }

    /**
     * Read from AppNameToSchema table on DynamoDB, returns model object AppToSchema
     * @param appName
     * @return AppToSchema
     */
    public AppToSchema read(String appName){

        return mapper.load(AppToSchema.class, appName);
    }

    /**
     * Write to AppNameToSchema table on DynamoDB, returns model object AppToSchema
     * @param appToSchema
     * @return
     */
    public AppToSchema write(AppToSchema appToSchema){

        mapper.save(appToSchema);
        return appToSchema;
    }

    /**
     * Retrieves app reference from AppNameToSchema table on DynamoDB and updates it, returns model object AppToSchema
     * @param appName
     * @param newSchema
     * @return
     */
    public AppToSchema update(String appName, AppToSchema.Schema newSchema){

        AppToSchema appRetrieved= mapper.load(AppToSchema.class, appName);
        // if no versioned schema initialize array and increment from base version
        if(appRetrieved.getSchemas() == null){
            appRetrieved.setSchemas(new ArrayList<>());
            newSchema.incrementVersion(appRetrieved.getSchema());
        }
        else // increment from last versioned schema
            newSchema.incrementVersion(appRetrieved.getSchemas().get(appRetrieved.getSchemas().size()-1));
        // add new schema to array list of schemas
        appRetrieved.getSchemas().add(newSchema);
        mapper.save(appRetrieved);
        return appRetrieved;

    }
}