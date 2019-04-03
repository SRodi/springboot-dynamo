package com.mapper.dynamo.helper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.dynamo.model.Schema;
import com.mapper.dynamo.model.SchemaMixIn;

import java.io.IOException;
import java.util.ArrayList;

public class SchemasConverter implements DynamoDBTypeConverter<String, ArrayList<Schema>> {
    @Override
    public String convert(ArrayList<Schema> schemas) {
        ObjectMapper mapper = new ObjectMapper();
        String json = null;
        try {
            json = mapper.writeValueAsString(schemas);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public ArrayList<Schema> unconvert(String s) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        mapper.addMixIn(Schema.class, SchemaMixIn.class);
        ArrayList<Schema> schemas = new ArrayList<>();
        JavaType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, Schema.class);
        try {
            schemas = mapper.readValue(s, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return schemas;
    }
}
