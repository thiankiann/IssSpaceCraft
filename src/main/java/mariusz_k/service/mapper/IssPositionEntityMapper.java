package mariusz_k.service.mapper;

import mariusz_k.dto.IssPositionDto;
import mariusz_k.entity.IssPositionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class IssPositionEntityMapper {

    public IssPositionEntity mapFromResultSet(ResultSet rs) throws SQLException {
        return new IssPositionEntity(UUID.fromString(rs.getString("id")),
                rs.getDouble("longitude"),rs.getDouble("latitude"),rs.getLong("exp_time"));
    }
    public IssPositionEntity mapFromDto(IssPositionDto dto){
        return new IssPositionEntity(UUID.randomUUID(),dto.getIssPosition().getLongitude(), dto.getIssPosition().getLatitude(),
                dto.getTimestamp());
    }
    public IssPositionDto mapFromEntity(IssPositionEntity entity){
        return new IssPositionDto(new IssPositionDto.IssPosition(entity.getLongitude(), entity.getLatitude()),
                                    entity.getTimestamp());
    }
}
