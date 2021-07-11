package mariusz_k.service.mapper;

import com.google.gson.Gson;
import mariusz_k.dto.IssPositionDto;
import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.view.IssPositionView;

public class GsonJsonMapper implements JsonMapper{

    private final Gson gson = new Gson();

    @Override
    public PeopleInSpaceDto mapPeopleInSpaceFromJson(String json) {
        return gson.fromJson(json,PeopleInSpaceDto.class);
    }

    @Override
    public IssPositionDto mapIssPositionDtoFromJason(String json) {
        return gson.fromJson(json, IssPositionDto.class );
    }

}
