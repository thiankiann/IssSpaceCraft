package controller;

import mariusz_k.controller.PeopleInSpaceController;
import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.service.PeopleInSpaceService;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.PeopleInSpaceDtoViewMapper;
import mariusz_k.view.PeopleInSpaceView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

public class PeopleInSpaceControllerTest {

    @Test
    public void shouldReturnInfoAboutPeopleInSpaceWhenConnectorReturnsData() throws Exception {
        //given
        final var mockConnector = Mockito.mock(PeopleInSpaceService.class);

        Mockito.when(mockConnector.getPeopleInSpace()).thenReturn(new PeopleInSpaceDto(1,
                Collections.singletonList(new PeopleInSpaceDto.HumanInSpace("ISS","test name"))));

        final var controller = new PeopleInSpaceController(mockConnector, new PeopleInSpaceDtoViewMapper());
        final var expectedResult = new PeopleInSpaceView(1,
                Collections.singletonList((new PeopleInSpaceView.HumanInSpaceView("ISS", "test name"))));
        //when

        final var result = controller.getPeopleInSpaceInfo();

        //then
        Assertions.assertEquals(expectedResult,result);

    }
}
