package mariusz_k.repository;

import mariusz_k.db.DBSetup;
import mariusz_k.entity.IssPositionEntity;
import mariusz_k.service.logger.LoggerService;
import mariusz_k.service.mapper.IssPositionEntityMapper;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class IssPositionMySqlRepository implements IssPositionRepository{

    private static final String TABLE = "iss_posion";

    private final IssPositionEntityMapper issPositionEntityMapper;

    private final LoggerService loggerService;

    private final DBSetup dbSetup;

    public IssPositionMySqlRepository(IssPositionEntityMapper issPositionEntityMapper, LoggerService loggerService, DBSetup dbSetup) {
        this.issPositionEntityMapper = issPositionEntityMapper;
        this.loggerService = loggerService;
        this.dbSetup = dbSetup;
    }

    @Override
    public void saveIssPosition(IssPositionEntity entity) {

        try(final PreparedStatement preparedStatement = getConnection().prepareStatement("INSERT INTO" + TABLE + " VALUES(? , ? ,? , ?)")) {
            getConnection().setAutoCommit(false);

            preparedStatement.setString(1,entity.getId().toString());
            preparedStatement.setDouble(2,entity.getLongitude());
            preparedStatement.setDouble(3,entity.getLatitude());
            preparedStatement.setLong(3,entity.getTimestamp());
            preparedStatement.execute();

            getConnection().commit();
        } catch (SQLException e) {
            loggerService.logError("Can't save to database", e);
        }
    }

    @Override
    public List<IssPositionEntity> getIssPosition() {
        final var issPositionEntities = new ArrayList<IssPositionEntity>();

        try (final var ps = getConnection().prepareStatement("SELECT*FROM" + TABLE + "ORDER by timestamp DESC LIMIT 2")){

            final var resultSet = ps.executeQuery();
            while (resultSet.next()){
                IssPositionEntity issPositionEntity= new IssPositionEntity(
                        UUID.fromString(resultSet.getString("id")),
                        resultSet.getDouble("longitude"),
                        resultSet.getDouble("latitude"),
                        resultSet.getLong("timestamp")
                );
            issPositionEntities.add(issPositionEntity);
            }
        } catch (SQLException e) {
            loggerService.logError("Can't get IssPosition", e);
        }
        return null;
    }

    private Connection getConnection() {

        return dbSetup.getDbConnection();

    }
}
