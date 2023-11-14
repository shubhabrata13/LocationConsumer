package com.apmm.service;



import static org.mockito.Mockito.*;
import com.apmm.domain.Location;
import com.apmm.repository.LocationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.r2dbc.postgresql.codec.Json;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.AssertionErrors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(SpringExtension.class)
public class ProcessEventServiceTest {
    @InjectMocks
    private ProcessEventService processEventService;

    @Mock
    private LocationRepository locationRepository;

    private static final String location_str = "{\n" +
            "  \"bdas\": [\n" +
            "    {\n" +
            "      \"name\": \"SIKOP\",\n" +
            "      \"type\": \"Business Defined Area\",\n" +
            "      \"bdaType\": \"POOL\",\n" +
            "      \"alternateCodes\": [\n" +
            "        {\n" +
            "          \"code\": \"SVOWWQGRTLKKA\",\n" +
            "          \"codeType\": \"locationId\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"name\": \"GODOVIC\",\n" +
            "  \"status\": \"InActive\",\n" +
            "  \"bdaType\": null,\n" +
            "  \"country\": {\n" +
            "    \"name\": \"Slovenia\",\n" +
            "    \"alternateCodes\": [\n" +
            "      {\n" +
            "        \"code\": \"3P1JF15ZCBG5A\",\n" +
            "        \"codeType\": \"locationId\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"code\": \"SI\",\n" +
            "        \"codeType\": \"localcode1\"\n" +
            "      },\n" +
            "      {\n" +
            "        \"code\": \"140\",\n" +
            "        \"codeType\": \"localcode2\"\n" +
            "      }\n" +
            "    ]\n" +
            "  },\n" +
            "  \"geoType\": \"City\",\n" +
            "  \"parents\": [\n" +
            "    {\n" +
            "      \"name\": \"Slovenia\",\n" +
            "      \"type\": \"Country\",\n" +
            "      \"bdaType\": null,\n" +
            "      \"alternateCodes\": [\n" +
            "        {\n" +
            "          \"code\": \"3P1JF15ZCBG5A\",\n" +
            "          \"codeType\": \"locationId\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"code\": \"SI\",\n" +
            "          \"codeType\": \"localcode1\"\n" +
            "        },\n" +
            "        {\n" +
            "          \"code\": \"140\",\n" +
            "          \"codeType\": \"localcode2\"\n" +
            "        }\n" +
            "      ]\n" +
            "    }\n" +
            "  ],\n" +
            "  \"validTo\": \"2019-08-05\",\n" +
            "  \"hsudName\": null,\n" +
            "  \"latitude\": \"45.5724\",\n" +
            "  \"portFlag\": false,\n" +
            "  \"timeZone\": null,\n" +
            "  \"longitude\": \"10.0532\",\n" +
            "  \"validFrom\": \"1900-01-01\",\n" +
            "  \"locationId\": \"005JOMN2OKB5N\",\n" +
            "  \"restricted\": null,\n" +
            "  \"description\": null,\n" +
            "  \"dialingCode\": null,\n" +
            "  \"bdaLocations\": null,\n" +
            "  \"isMaerskCity\": true,\n" +
            "  \"olsonTimezone\": null,\n" +
            "  \"alternateCodes\": [\n" +
            "    {\n" +
            "      \"code\": \"G./\",\n" +
            "      \"codeType\": \"localcode2\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"code\": \"SIGOD\",\n" +
            "      \"codeType\": \"localcode1\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"code\": \"005JOMN2OKB5N\",\n" +
            "      \"codeType\": \"locationId\"\n" +
            "    }\n" +
            "  ],\n" +
            "  \"alternateNames\": null,\n" +
            "  \"subCityParents\": null,\n" +
            "  \"utcOffsetMinutes\": null,\n" +
            "  \"workaroundReason\": null,\n" +
            "  \"daylightSavingEnd\": null,\n" +
            "  \"daylightSavingTime\": null,\n" +
            "  \"daylightSavingStart\": null,\n" +
            "  \"postalCodeMandatory\": null,\n" +
            "  \"dialingCodeDescription\": null,\n" +
            "  \"stateProvinceMandatory\": null,\n" +
            "  \"daylightSavingShiftMinutes\": null\n" +
            "}";

    @Test
    public void testProcessEvent() {
        Location location = mapObject(location_str);
        Mockito.when(locationRepository.save(any())).thenReturn(Mono.just(location));
        Mono<Location> locationMono = processEventService.processEvent(location_str);
        StepVerifier
                .create(locationMono)
                .consumeNextWith(data -> {
                    AssertionErrors.assertNotNull("location is not null", data);
                    verify(locationRepository, atLeastOnce()).save(any());
                })
                .verifyComplete();
    }

    public Location mapObject(String message) {
        Location readValue=null;
        JsonNode node=null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            node = mapper.readTree(message);
            readValue = new Location();
            readValue.setData(Json.of(message));
            //readValue = mapper.readValue(message, Location.class);
        }catch(Exception e){
            e.printStackTrace();
        }
        return readValue;
    }
}
