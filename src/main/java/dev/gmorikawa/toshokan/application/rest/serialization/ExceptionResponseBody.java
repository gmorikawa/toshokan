package dev.gmorikawa.toshokan.application.rest.serialization;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

public class ExceptionResponseBody implements JsonSerializable{
    private final int status;
    private final String message;

    public ExceptionResponseBody(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

    public ExceptionResponseBody(HttpStatus status) {
        this.status = status.value();
        this.message = "An exception occured";
    }

    @Override
    public void serialize(JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("status", status);
        generator.writeStringField("message", message);
        generator.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator generator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
    }
}
