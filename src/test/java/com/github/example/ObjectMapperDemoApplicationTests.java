package com.github.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ObjectMapperDemoApplicationTests {

    private static final String DATA = "{\"offsetDateTime\": \"2020-01-01T00:00:00\"}";

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    @Qualifier("failingObjectMapper")
    private ObjectMapper otherObjectMapper;

    @Test
    public void testObjectMapperForZoneOffset() throws IOException {
        final DemoPojo demoPojo = objectMapper.readValue(DATA, DemoPojo.class);

        assertNotNull(demoPojo.getOffsetDateTime());
    }

    @Test(expected = InvalidFormatException.class)
    public void testFailingObjectMapperForZoneOffset() throws IOException {
        otherObjectMapper.readValue(DATA, DemoPojo.class);

    }

}
