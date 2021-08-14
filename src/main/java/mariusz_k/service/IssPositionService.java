package mariusz_k.service;

import mariusz_k.dto.IssPositionDto;
import mariusz_k.entity.IssPositionEntity;
import mariusz_k.repository.IssPositionRepository;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.IssPositionEntityMapper;

public class IssPositionService {

    OpenNotifyConnector openNotifyConnector;
    IssPositionEntityMapper issPositionEntityMapper;
    IssPositionRepository issPositionRepository;

    public IssPositionService(OpenNotifyConnector openNotifyConnector, IssPositionEntityMapper issPositionEntityMapper,
                              IssPositionRepository issPositionRepository) {
        this.openNotifyConnector = openNotifyConnector;
        this.issPositionEntityMapper = issPositionEntityMapper;
        this.issPositionRepository = issPositionRepository;
    }

    public IssPositionDto getIssPosition() throws Exception {

        final var result = openNotifyConnector.getIssPosition();
        result.ifPresent(result1 -> {
            IssPositionEntity issPositionEntity= issPositionEntityMapper.mapFromDto(result1);
            issPositionRepository.saveIssPosition(issPositionEntity);

                });
        return result.orElseThrow(() -> new Exception("Unable to get info about people in space "));

    }
}
