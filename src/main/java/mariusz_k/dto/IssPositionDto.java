package mariusz_k.dto;

public class IssPositionDto {
    private final int timestamp;

    private final IssPosition position;

    public IssPositionDto(int timestamp, IssPosition position) {
        this.timestamp = timestamp;
        this.position = position;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public IssPosition getPosition() {
        return position;
    }

    public static class IssPosition {


        private final long longitude ;


        private final long latitude;

        public IssPosition(long longitude, long latitude) {
            this.longitude = longitude;
            this.latitude = latitude;
        }

        public long getLongitude() {
            return longitude;
        }

        public long getLatitude() {
            return latitude;
        }

    }
}
