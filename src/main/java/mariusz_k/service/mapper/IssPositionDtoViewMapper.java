package mariusz_k.service.mapper;

import mariusz_k.dto.IssPositionDto;
import mariusz_k.view.IssPositionView;

public class IssPositionDtoViewMapper {
 public IssPositionView mapDtoToView(IssPositionDto dto) {
        return new IssPositionView(dto.getTimestamp(), mapIssPositionFromDtoToView(dto.getIssPosition()));
    }

    private IssPositionView.IssCurrentPositionView mapIssPositionFromDtoToView(
            IssPositionDto.IssPosition position ) {
        return new IssPositionView.IssCurrentPositionView(position.getLongitude(), position.getLatitude());
    }
}




/* //to compare with PPlInSpace

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
 */