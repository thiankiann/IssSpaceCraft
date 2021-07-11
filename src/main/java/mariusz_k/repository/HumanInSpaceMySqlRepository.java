package mariusz_k.repository;





import mariusz_k.db.DBSetup;
import mariusz_k.entity.HumanInSpaceEntity;
import mariusz_k.service.logger.LoggerService;
import mariusz_k.service.mapper.HumanInSpaceEntityMapper;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static java.sql.DriverManager.getConnection;


public class HumanInSpaceMySqlRepository implements HumanInSpaceRepository{

    private static final String TABLE = "human_in_space";
    private final HumanInSpaceEntityMapper mapper;
    private final LoggerService loggerService;
    private final DBSetup dbSetup;

    public HumanInSpaceMySqlRepository(HumanInSpaceEntityMapper mapper, LoggerService loggerService, DBSetup dbSetup) {
        this.mapper = mapper;
        this.loggerService = loggerService;
        this.dbSetup = dbSetup;
    }

    @Override
    public void savePeopleInSpace(List<HumanInSpaceEntity> peopleInSpace) {
        try (final var ps = getConnection().prepareStatement("INSERT INTO" + "TABLE" +"VALUES(?)")) {
            getConnection().setAutoCommit(false);
            for (final var entity : peopleInSpace) {
                ps.setString(1, entity.getId().toString());
                ps.setString(2, entity.getName());
                ps.setString(3, entity.getCraft());
                ps.setLong(4, entity.getExpTime());
                ps.execute();

            }
        }catch(SQLException e){
            try{
                getConnection().rollback();
            } catch (SQLException rollbackException) {
                loggerService.logError("savePeopleInSpace rollback failed!", rollbackException);
            }
            loggerService.logError("savePeopleInSpace with entities" + peopleInSpace + "failed!", e);
        }
    }


    @Override
    public List<HumanInSpaceEntity> getPeopleInSpace() {
        return null;
    }

    private Connection getConnection() {
        return this.dbSetup.getDbConnection();
    }

}
