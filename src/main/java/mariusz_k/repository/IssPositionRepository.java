package mariusz_k.repository;

import mariusz_k.dto.IssPositionDto;
import mariusz_k.entity.IssPositionEntity;

import java.util.List;

public interface IssPositionRepository {

    public void saveIssPosition(IssPositionEntity  issPositionEntity);

    public List<IssPositionEntity> getIssPosition();
}
