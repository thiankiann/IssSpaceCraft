package mariusz_k.service.mapper;

import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.entity.HumanInSpaceEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class HumanInSpaceEntityMapper {
    public HumanInSpaceEntity mapFromResultSet(ResultSet rs) throws SQLException {
        return new HumanInSpaceEntity(UUID.fromString(rs.getString("id")),rs.getString("craft"),rs.getString("name"),
                rs.getLong("exp_time"));
    }

    public HumanInSpaceEntity mapFromDto(PeopleInSpaceDto.HumanInSpace humanInSpace , UUID id , long expTime) {
        return new HumanInSpaceEntity(id, humanInSpace.getCraft(), humanInSpace.getName(), expTime);
    }

    public PeopleInSpaceDto.HumanInSpace mapToDto(HumanInSpaceEntity entity){
        return new PeopleInSpaceDto.HumanInSpace(entity.getCraft(), entity.getName());
    }
}
