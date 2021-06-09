package mariusz_k.service.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import mariusz_k.dto.PeopleInSpaceDto;

public class JacksonJsonMapper implements JsonMapper {

    private final ObjectMapper objectMapper;

    public JacksonJsonMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public PeopleInSpaceDto mapFromJson(String json) {
        try {
            return objectMapper.readValue(json, PeopleInSpaceDto.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }
}
