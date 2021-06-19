package mariusz_k.controller;

import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.IssPositionDtoViewMapper;
import mariusz_k.view.IssPositionView;

public class IssPositionController {
  OpenNotifyConnector openNotifyConnector;
  //  IssPositionView issPositionView;
    IssPositionDtoViewMapper mapper;

    public IssPositionController(OpenNotifyConnector openNotifyConnector, IssPositionDtoViewMapper dtoViewMapper) {
        this.openNotifyConnector = openNotifyConnector;
        //this.issPositionView = issPositionView;
        this.mapper = dtoViewMapper;
    }

    public  IssPositionView getIssPositionView() throws Exception {
        //TODO: implement method like  PeopleInSpaceController                    // done
      //  throw new UnsupportedOperationException();                              // to bylo jak metoda niemiala ciala - zeby przeszlo kompilowanie
        final var result = this.openNotifyConnector.getIssPosition();
        return result.map(mapper::mapDtoToView)
                .orElseThrow(() -> new Exception("Unable to get info about people in space "));

    }

}
