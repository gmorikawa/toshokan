package dev.gmorikawa.toshokan.application.rest.serialization;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsontype.TypeSerializer;

import dev.gmorikawa.toshokan.shared.exceptions.DomainException;

public class ExceptionResponseBody implements JsonSerializable{
    private final int status;
    private final DomainException exception;

    public ExceptionResponseBody(HttpStatus status, DomainException exception) {
        this.status = status.value();
        this.exception = exception;
    }

    @Override
    public void serialize(JsonGenerator generator, SerializerProvider serializerProvider) throws IOException {
        generator.writeStartObject();
        generator.writeNumberField("status", status);
        generator.writeStringField("exception", exception.getClass().getSimpleName());
        generator.writeStringField("message", exception.getMessage());
        generator.writeStringField("timestamp", exception.getTimestamp().toString());
        generator.writeEndObject();
    }

    @Override
    public void serializeWithType(JsonGenerator generator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException {
    }
}
