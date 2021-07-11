package mariusz_k;

import mariusz_k.config.AppConfig;
import mariusz_k.controller.IssPositionController;
import mariusz_k.controller.PeopleInSpaceController;
import mariusz_k.db.DBSetup;
import mariusz_k.repository.HumanInSpaceMySqlRepository;
import mariusz_k.service.PeopleInSpaceService;
import mariusz_k.service.cli.CliArgsParser;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.logger.LoggerService;
import mariusz_k.service.mapper.*;
import mariusz_k.view.IssPositionView;
import mariusz_k.view.PeopleInSpaceView;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import java.net.http.HttpClient;
import java.sql.SQLException;
import java.time.Duration;
import java.util.Scanner;

import static mariusz_k.config.CliUsageConfig.CLI_OPTIONS;

public class Main {
    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    private static final JsonMapper jsonMapper = new GsonJsonMapper();
    //private static final JsonMapper jsonMapper = new JacksonJsonMapper();
    private static final OpenNotifyConnector openNotifyConnector = new OpenNotifyConnector(httpClient, jsonMapper);
    private static final PeopleInSpaceDtoViewMapper peopleInSpaceDtoViewMapper = new PeopleInSpaceDtoViewMapper();
    private static final IssPositionDtoViewMapper issPositionDtoViewMapper= new IssPositionDtoViewMapper();

    private static PeopleInSpaceService peopleInSpaceService;
    private static PeopleInSpaceController peopleInSpaceController =
            new PeopleInSpaceController(peopleInSpaceService,peopleInSpaceDtoViewMapper );
    private static final Scanner keyboardScanner = new Scanner(System.in);
    private static final CliArgsParser cliArgsParser = new CliArgsParser();
    private static IssPositionController issPositionController = new IssPositionController(openNotifyConnector, issPositionDtoViewMapper );
    private static AppConfig appConfig;
    private static LoggerService loggerService;
    private static DBSetup dbSetup;
    private static HumanInSpaceMySqlRepository humanInSpaceRepository;
    private static HumanInSpaceEntityMapper humanInSpaceEntityMapper;


    public static void main(String[] args) {
      // initAppConfig(args);
      //  initDb();
        showAppTitle();

        var programRunning = true;
        while (programRunning) {
            showMenu();
            var chosenOption = keyboardScanner.nextLine();
            switch (chosenOption) {
                case "1":
                    showPeopleInSpace();
                    waitForUserAcknowledge();
                    break;
                case "2":
                    showCurrentLocationOfISS();
                    waitForUserAcknowledge();
                    break;
                case "3":
                    programRunning = false;
                    System.out.println("Good bye!");
                    break;
                default:
                    showUnknownOperationInfo(chosenOption);
            }
        }
    }
    private static void showAppTitle() {
        final var appTitle = "\" ISS Project!";
        System.out.println(appTitle);
    }
    private static void showMenu() {
        // @formatter:off
        final var menu = "SDA javaLon4 project one!\n" +
                "Choose menu option:\n" +
                "1 - show people in space\n" +
                "2 - show current location of ISS\n" +
                "3 - exit";
        // @formatter:on
        System.out.println(menu);
    }
    private static void initAppConfig(String[] args) {

        try {
            appConfig = cliArgsParser.parseAppConfig(args);
         //   loggerService.setIsDebugMode(appConfig.isDebugMode());
        } catch (ParseException e) {
            System.err.println(e.getMessage());
            final var helpFormatter = new HelpFormatter();
            helpFormatter.printHelp("following program args are allowed", CLI_OPTIONS);
            System.exit(1);
        }
    }
    private static void initDb() {
        try {
            dbSetup = new DBSetup(appConfig.getDbUser(), appConfig.getDbPass(), appConfig.getDbHost(),
                    appConfig.getDbName(), loggerService);

            humanInSpaceRepository = new HumanInSpaceMySqlRepository(humanInSpaceEntityMapper, loggerService, dbSetup);

            peopleInSpaceService = new PeopleInSpaceService(openNotifyConnector, humanInSpaceRepository, humanInSpaceEntityMapper);

            peopleInSpaceController = new PeopleInSpaceController(peopleInSpaceService, peopleInSpaceDtoViewMapper);


            issPositionController = new IssPositionController(openNotifyConnector, issPositionDtoViewMapper);



        } catch (SQLException e) {
            loggerService.logError("Setup of database connection failed!", e);
            System.err.println("There is a problem with database connection, app cannot start...");
            System.exit(1);
        }
    }
    private static void waitForUserAcknowledge() {
        System.out.println("Press any key to continue...");
        keyboardScanner.nextLine();
    }

    private static void showPeopleInSpace() {
        try {
           // final var start = System.currentTimeMillis();
            final var peopleInSpaceInfo = peopleInSpaceController.getPeopleInSpaceInfo();
           // final var stop = System.currentTimeMillis();
            System.out.println(peopleInSpaceInfo.getInfoAboutPeopleInSpace());
           //  System.out.println("Fetched in " + (stop - start) + "ms");
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

   private static  void showCurrentLocationOfISS() {
       try {
           final var issPositionView = issPositionController.getIssPositionView();
           System.out.println(issPositionView.showIssLocation());
       } catch (Exception e) {
           System.err.println(e.getMessage());
       }
    }

    private static void showUnknownOperationInfo(String chosenOption) {
        final var unknownOperationInfo =
                String.format("\"%s\" option is unknown. Please specify one of the menu options!", chosenOption);
        System.out.println(unknownOperationInfo);
    }
}
