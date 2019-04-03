package com.mapper.dynamo.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SchemaMixIn {
    SchemaMixIn(
            @JsonProperty("version") int version,
            @JsonProperty("schemaString") String schemaString
    ) {}
}
