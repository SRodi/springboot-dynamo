package com.mapper.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;
import com.mapper.dynamo.helper.SchemaConverter;
import com.mapper.dynamo.helper.SchemasConverter;

import java.util.ArrayList;

@DynamoDBTable(tableName = "AppNameToAppSchema")
public class AppToSchema {

    private String appName;
    private Schema baseSchema;
    private ArrayList<Schema> revisions;

    // Partition key
    @DynamoDBHashKey(attributeName = "AppName")
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @DynamoDBTypeConverted(converter = SchemaConverter.class)
    @DynamoDBAttribute(attributeName = "Schema")
    public Schema getSchema() {
        return baseSchema;
    }
    public void setSchema(Schema baseSchema) {
        this.baseSchema = baseSchema;
    }

    @DynamoDBTypeConverted(converter = SchemasConverter.class)
    @DynamoDBAttribute(attributeName = "Schemas")
    public ArrayList<Schema> getSchemas() {return revisions;}
    public void setSchemas(ArrayList<Schema> revisions) {this.revisions = revisions;}

}
