package com.ghawk1ns.server.io;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Mappers {
    public static final ObjectMapper lenient_mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.READ_ENUMS_USING_TO_STRING, true)
            .configure(SerializationFeature.WRITE_ENUMS_USING_TO_STRING, true)
            .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
            .configure(JsonGenerator.Feature.IGNORE_UNKNOWN, true)
            .configure(JsonGenerator.Feature.AUTO_CLOSE_TARGET, true)
            .configure(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT, true)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
}
