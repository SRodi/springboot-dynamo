package com.mapper.dynamo.model;

import com.amazonaws.services.dynamodbv2.datamodeling.*;
import java.util.List;

@DynamoDBTable(tableName = "AppNameToAppSchema")
public class AppToSchema {

    private String appName;
    private Schema baseSchema;
    private List<Schema> revisions;

    // Partition key
    @DynamoDBHashKey(attributeName = "AppName")
    public String getAppName() {
        return appName;
    }
    public void setAppName(String appName) {
        this.appName = appName;
    }

    @DynamoDBAttribute(attributeName = "BaseSchema")
    public Schema getSchema() {
        return baseSchema;
    }
    public void setSchema(Schema baseSchema) {
        this.baseSchema = baseSchema;
    }

    @DynamoDBAttribute(attributeName = "Revisions")
    public List<Schema> getSchemas() {return revisions;}
    public void setSchemas(List<Schema> revisions) {this.revisions = revisions;}

    @DynamoDBDocument
    public static class Schema {

        private int version=1;
        private String schemaString;

        @DynamoDBAttribute(attributeName = "Version")
        public int getVersion() {
            return version;
        }
        public void setVersion(int version) {
            this.version = version;
        }

        @DynamoDBAttribute(attributeName = "SchemaString")
        public String getSchemaString() {
            return schemaString;
        }
        public void setSchemaString(String schemaString) {
            this.schemaString = schemaString;
        }

        public void incrementVersion(Schema latestSchema){
            this.setVersion(latestSchema.getVersion()+1);
        }
    }

}
