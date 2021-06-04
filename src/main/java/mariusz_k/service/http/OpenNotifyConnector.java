package mariusz_k.service.http;

import mariusz_k.dto.PeopleInSpaceDto;
import mariusz_k.service.formatter.ResponseFormatter;
import mariusz_k.service.mapper.JsonMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

public class OpenNotifyConnector {

    private static final HttpRequest request =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/astros.json")).build();

   private static final HttpRequest request2 =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/iss-now.json")).build();

  //  private final ResponseFormatter responseFormatter;

    private final HttpClient httpClient;
    private final JsonMapper jsonMapper;

    public OpenNotifyConnector(HttpClient httpClient, JsonMapper jsonMapper) {
        this.httpClient = httpClient;
        this.jsonMapper = jsonMapper;
    }

    public Optional<PeopleInSpaceDto> getPeopleInSpace() {
        try {
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 200 ) {
                return Optional.of(jsonMapper.mapFromJson(response.body()));
            }
            return Optional.empty();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }
/*    //change later using mapper instead formatter
    public String getIssPosition() {
        try {
            final var response2 = httpClient.send(request2, HttpResponse.BodyHandlers.ofString());
        return responseFormatter.formatIssPosition(response2);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error while getting I ss Position.";
        }

    }
 */
}
