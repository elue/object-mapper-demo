package com.github.example;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeParseException;

@Component
public class OffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    private static final String CURRENT_ZONE_OFFSET = OffsetDateTime.now().getOffset().toString();

    @Override
    public OffsetDateTime deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        final String value = jsonParser.getValueAsString();
        if (StringUtils.isEmpty(value)) {
            return null;
        }
        try {
            return OffsetDateTime.parse(value);

        } catch (DateTimeParseException exception) {
            return OffsetDateTime.parse(value + CURRENT_ZONE_OFFSET);
        }
    }
}
