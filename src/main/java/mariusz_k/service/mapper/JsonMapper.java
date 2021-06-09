package mariusz_k.service.mapper;

import mariusz_k.dto.PeopleInSpaceDto;

public interface JsonMapper {
    PeopleInSpaceDto mapFromJson(String json);
}
