package com.mapper.dynamo.model;

public class Schema {
    private int version=1;
    private String schemaString;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getSchemaString() {
        return schemaString;
    }

    public void setSchemaString(String schemaString) {
        this.schemaString = schemaString;
    }

    public void incrementVersion(Schema previousSchema){
        this.setVersion(previousSchema.getVersion()+1);
    }

}
