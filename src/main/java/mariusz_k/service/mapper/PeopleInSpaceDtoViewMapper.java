package mariusz_k.service.mapper;

import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.view.PeopleInSpaceView;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleInSpaceDtoViewMapper {
    public PeopleInSpaceView mapDtoToView(PeopleInSpaceDto dto) {
        return new PeopleInSpaceView(dto.getNumber(), mapHumanInSpaceFromDtoToView(dto.getPeople()));
    }

    private List<PeopleInSpaceView.HumanInSpaceView> mapHumanInSpaceFromDtoToView(
            List<PeopleInSpaceDto.HumanInSpace> people) {
        return people.stream().map(humanInSpace -> new PeopleInSpaceView.HumanInSpaceView(humanInSpace.getCraft(),
                humanInSpace.getName())).collect(Collectors.toList());
    }
}
