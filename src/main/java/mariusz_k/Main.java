package mariusz_k;

import mariusz_k.controller.IssPositionController;
import mariusz_k.controller.PeopleInSpaceController;
import mariusz_k.service.http.OpenNotifyConnector;
import mariusz_k.service.mapper.GsonJsonMapper;
import mariusz_k.service.mapper.IssPositionDtoViewMapper;
import mariusz_k.service.mapper.JsonMapper;
import mariusz_k.service.mapper.PeopleInSpaceDtoViewMapper;
import mariusz_k.view.IssPositionView;
import mariusz_k.view.PeopleInSpaceView;

import java.net.http.HttpClient;
import java.time.Duration;
import java.util.Scanner;

public class Main {
    private static final HttpClient httpClient =
            HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).connectTimeout(Duration.ofSeconds(10)).build();

    private static final JsonMapper jsonMapper = new GsonJsonMapper();
    //private static final JsonMapper jsonMapper = new JacksonJsonMapper();
    private static final OpenNotifyConnector openNotifyConnector = new OpenNotifyConnector(httpClient, jsonMapper);
    private static final PeopleInSpaceDtoViewMapper peopleInSpaceDtoViewMapper = new PeopleInSpaceDtoViewMapper();
    private static final IssPositionDtoViewMapper issPositionDtoViewMapper= new IssPositionDtoViewMapper();
    private static final PeopleInSpaceController peopleInSpaceController =
            new PeopleInSpaceController(openNotifyConnector, peopleInSpaceDtoViewMapper);
    private static final Scanner keyboardScanner = new Scanner(System.in);
    private static IssPositionController issPositionController = new IssPositionController(openNotifyConnector, issPositionDtoViewMapper );

    public static void main(String[] args) {
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

    private static void waitForUserAcknowledge() {
        System.out.println("Press any key to continue...");
        keyboardScanner.nextLine();
    }

    private static void showPeopleInSpace() {
        try {
            final var peopleInSpaceInfo = peopleInSpaceController.getPeopleInSpaceInfo();
            System.out.println(peopleInSpaceInfo.getInfoAboutPeopleInSpace());
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
