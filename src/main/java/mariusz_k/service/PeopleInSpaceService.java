package mariusz_k.service;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.entity.HumanInSpaceEntity;
import mariusz_k.repository.HumanInSpaceRepository;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.HumanInSpaceEntityMapper;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class PeopleInSpaceService {
    private final OpenNotifyConnector openNotifyConnector;

    private final HumanInSpaceRepository humanInSpaceRepository;

    private final HumanInSpaceEntityMapper humanInSpaceEntityMapper;

    public PeopleInSpaceService(OpenNotifyConnector openNotifyConnector, HumanInSpaceRepository humanInSpaceRepository,
                                HumanInSpaceEntityMapper humanInSpaceEntityMapper) {
        this.openNotifyConnector = openNotifyConnector;
        this.humanInSpaceRepository = humanInSpaceRepository;
        this.humanInSpaceEntityMapper = humanInSpaceEntityMapper;
    }

    public PeopleInSpaceDto getPeopleInSpace() throws Exception {
        final var peopleInSpaceFromDb = this.humanInSpaceRepository.getPeopleInSpace();
        if (!peopleInSpaceFromDb.isEmpty()) {
            return new PeopleInSpaceDto(peopleInSpaceFromDb.size(),
                    peopleInSpaceFromDb.stream().map(humanInSpaceEntityMapper::mapToDto).collect(Collectors.toList()));
        }

        final var result = this.openNotifyConnector.getPeopleInSpace();
        // @formatter:off
        result.ifPresent(dto -> {                 // jesli !=null to rob 
            List<HumanInSpaceEntity> peopleInSpaceEntities = dto.getPeople().stream()
                    .map(humanInSpaceDto ->
                            humanInSpaceEntityMapper.mapFromDto(
                                    humanInSpaceDto,
                                    UUID.randomUUID(),
                                    Instant.now().getEpochSecond() + (60 * 60 * 24)
                            )
                    ).collect(Collectors.toList());
            this.humanInSpaceRepository.savePeopleInSpace(peopleInSpaceEntities);
        });
        // @formatter:on
        return result.orElseThrow(() -> new Exception("Unable to get info about people in space."));
    }
}
