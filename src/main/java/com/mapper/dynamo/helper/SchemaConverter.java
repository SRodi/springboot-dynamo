package com.mapper.dynamo.helper;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mapper.dynamo.model.Schema;
import com.mapper.dynamo.model.SchemaMixIn;

public class SchemaConverter implements DynamoDBTypeConverter<String, Schema> {
    @Override
    public String convert(Schema schema) {
        String jsonInString = null;
        ObjectMapper mapper = new ObjectMapper();
        try {
            if (schema != null)
                jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(schema);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonInString;
    }

    @Override
    public Schema unconvert(String s) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES);
        mapper.addMixIn(Schema.class, SchemaMixIn.class);
        Schema schema = null;
        try {
            if (s != null && s.length() != 0)
                schema = mapper.readValue(s, Schema.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return schema;
    }
}
