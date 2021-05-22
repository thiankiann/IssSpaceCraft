package mariusz_k.service.http;

import mariusz_k.service.formatter.ResponseFormatter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class OpenNotifyConnector {

    private static final HttpRequest request =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/astros.json")).build();

  /*  private static final HttpRequest request2 =
            HttpRequest.newBuilder().GET().uri(URI.create("http://api.open-notify.org/iss-now.json ")).build();  */

    private final ResponseFormatter responseFormatter;

    private final HttpClient httpClient;

    public OpenNotifyConnector(ResponseFormatter responseFormatter, HttpClient httpClient) {
        this.responseFormatter = responseFormatter;
        this.httpClient = httpClient;
    }

    public String getPeopleInSpace() {
        try {
            final var response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return responseFormatter.formatResponse(response);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error while getting people in space...";
        }
    }
}
