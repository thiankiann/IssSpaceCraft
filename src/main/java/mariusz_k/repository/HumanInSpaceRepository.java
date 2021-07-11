package mariusz_k.repository;

import mariusz_k.entity.HumanInSpaceEntity;

import java.util.List;

public interface HumanInSpaceRepository {
    void savePeopleInSpace(List<HumanInSpaceEntity> peopleInSpace);

    List<HumanInSpaceEntity> getPeopleInSpace();
}
