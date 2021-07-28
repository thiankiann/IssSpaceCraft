package mariusz_k.controller;

import mariusz_k.service.PeopleInSpaceService;
//import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.PeopleInSpaceDtoViewMapper;
import mariusz_k.view.PeopleInSpaceView;

public class PeopleInSpaceController {


   // private final OpenNotifyConnector openNotifyConnector;

    private final PeopleInSpaceService peopleInSpaceService;

    private final PeopleInSpaceDtoViewMapper dtoViewMapper;

    public PeopleInSpaceController(PeopleInSpaceService peopleInSpaceService,
                                   PeopleInSpaceDtoViewMapper dtoViewMapper) {
        this.peopleInSpaceService = peopleInSpaceService;
        this.dtoViewMapper = dtoViewMapper;
    }

    public PeopleInSpaceView getPeopleInSpaceInfo() throws Exception {
        final var peopleInSpaceDto = this.peopleInSpaceService.getPeopleInSpace();
        return dtoViewMapper.mapDtoToView(peopleInSpaceDto);
    }
  /*  public PeopleInSpaceView getPeopleInSpaceInfo() throws Exception {
        final var result = this.peopleInSpaceService.getPeopleInSpace();
        return result.map(dtoViewMapper::mapDtoToView)
                .orElseThrow(() -> new Exception("Unable to get info about people in space "));
    } */
}
