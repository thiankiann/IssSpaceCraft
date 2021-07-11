package mariusz_k.service.mapper;

import mariusz_k.dto.IssPositionDto;
import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.view.IssPositionView;

public interface JsonMapper {
    PeopleInSpaceDto mapPeopleInSpaceFromJson(String json);
    IssPositionDto mapIssPositionDtoFromJason(String jason);
}
