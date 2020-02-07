package com.mapper.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

@DynamoDBTable(tableName = "MulticloudTest")
public class TableSchema {

    private String name;
    private String description;

    @DynamoDBAttribute(attributeName = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Partition key
    @DynamoDBHashKey(attributeName = "Name")
    public String getName() {
        return name;
    }
    public void setName(String appName) {
        this.name = appName;
    }

}
