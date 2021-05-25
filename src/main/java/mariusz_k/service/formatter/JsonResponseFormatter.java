package mariusz_k.service.formatter;

import com.google.gson.Gson;
import mariusz_k.dto.IssPositionDto;
import mariusz_k.dto.PeopleInSpaceDto;

import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.util.stream.Collectors;

public class JsonResponseFormatter implements ResponseFormatter {
    private static final Gson gson = new Gson();

    @Override
    public String formatResponse(HttpResponse<String> response) {
        final var json = response.body();
        final var peopleInSpace = gson.fromJson(json, PeopleInSpaceDto.class);
        return String.format("Currently there are %d people in space:\n%s", peopleInSpace.getNumber(),
                peopleInSpace.getPeople().stream()
                        .map(humanInSpace -> humanInSpace.getName() + " on craft " + humanInSpace.getCraft() + "\n")
                        .collect(Collectors.joining()));
    }

    @Override
    public String formatIssPosition(HttpResponse<String> response) {
        final var json = response.body();
        final var issPosition = gson.fromJson(json, IssPositionDto.class);
        final var timestamp = issPosition.getTimestamp();
        final var date = Instant.ofEpochSecond(timestamp).atZone(ZoneId.systemDefault());
        return String.format("Currently [ date :  %s ] position is %s %s ",date,
                issPosition.getIssPosition().getLatitude(),
                issPosition.getIssPosition().getLongitude());
    }
/*
    @Override
    public String formatResponse (HttpResponse<String> response2) {
        final var json = response2.body();
        final var issPosition = gson.fromJson(json, IssPositionDto.IssPosition.class);

        return String.format("Currently position is %s ", issPosition.toString());
    }
*/
/*
        return String.format("Currently position is %s ", issPosition.getPosition().stream()
                .map(IssPositionDto -> issPosition.getLongitude() +  + issPosition.getLatitude() + "\n")
                .collect(Collectors.joining()) );
*/
       //return String.format("Currently position is %s ", IssPositionDto.getPosition() );
       /* return String.format("Currently possition is  %s people in space:\n%s", IssPositionDto.getPosition(),
                peopleInSpace.getPeople().stream()
                        .map(humanInSpace -> humanInSpace.getName() + " on craft " + humanInSpace.getCraft() + "\n")
                        .collect(Collectors.joining()));
    } */
}
