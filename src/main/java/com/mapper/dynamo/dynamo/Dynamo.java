package com.mapper.dynamo.dynamo;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.mapper.dynamo.model.TableSchema;
import org.springframework.stereotype.Component;

@Component
public class Dynamo {

    AmazonDynamoDB client;
    private DynamoDBMapper mapper;

    /**
     * construct Dynamo instance:
     *  - initializes AmazonDynamoDB client
     *  - initializes DynamoDBMapper
     */
    public Dynamo() {
        client = AmazonDynamoDBClientBuilder.standard().withRegion("eu-west-1").build();
        mapper = new DynamoDBMapper(client);
    }

    /**
     * Read from MulticloudTest table on DynamoDB, returns model object TableSchema
     * @param name
     * @return TableSchema
     */
    public TableSchema read(String name){

        return mapper.load(TableSchema.class, name);
    }

    /**
     * Write to MulticloudTest table on DynamoDB, returns model object TableSchema
     * @param tableSchema
     * @return
     */
    public TableSchema write(TableSchema tableSchema){

        mapper.save(tableSchema);
        return tableSchema;
    }

    /**
     * Retrieves app reference from MulticloudTest table on DynamoDB and deletes it, returns true if successful
     * @param name
     * @return
     */
    public boolean delete(String name){

        try{
            mapper.delete(read(name));
            return true;
        }catch(Exception e){
            return false;
        }

    }
}