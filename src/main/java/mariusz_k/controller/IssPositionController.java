package mariusz_k.controller;

import mariusz_k.service.IssPositionService;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.IssPositionDtoViewMapper;
import mariusz_k.view.IssPositionView;

public class IssPositionController {
  OpenNotifyConnector openNotifyConnector;
    IssPositionService issPositionService;
    IssPositionDtoViewMapper mapper;

    public IssPositionController(OpenNotifyConnector openNotifyConnector, IssPositionService issPositionService, IssPositionDtoViewMapper mapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.issPositionService = issPositionService;
        this.mapper = mapper;
    }

    public  IssPositionView getIssPositionView() throws Exception {

      //  throw new UnsupportedOperationException();                              // to bylo jak metoda nie miala ciala - zeby przeszlo kompilowanie
        final var issPositionDto = this.issPositionService.getIssPosition();
        return mapper.mapDtoToView(issPositionDto);
    }

}
