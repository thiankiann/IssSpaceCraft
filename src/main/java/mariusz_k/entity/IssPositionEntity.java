package mariusz_k.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import mariusz_k.dto.IssPositionDto;

import java.util.UUID;

public class IssPositionEntity {


    // TODO: (workbench) create sql statement for table (iss_position) createion
    private final UUID id;

    private final double longitude;

    private final double latitude;

    private final long timestamp;

    public IssPositionEntity(UUID id, double longitude, double latitude, long timestamp) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.timestamp = timestamp;
    }

    public UUID getId() {
        return id;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }
}

