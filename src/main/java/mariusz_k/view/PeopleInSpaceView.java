package mariusz_k.view;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class PeopleInSpaceView {
    private final int number;

    private final List<HumanInSpaceView> people;
    @JsonCreator
    public PeopleInSpaceView(@JsonProperty int number, @JsonProperty List<HumanInSpaceView> people) {
        this.number = number;
        this.people = people;
    }

    public String getInfoAboutPeopleInSpace() {
        return String.format("Currently there are %d people in space:\n%s", this.number, this.people.stream()
                .map(humanInSpace -> humanInSpace.getName() + " on craft " + humanInSpace.getCraft() + "\n")
                .collect(Collectors.joining()));
    }

    public static class HumanInSpaceView {
        private final String craft;

        private final String name;
        @JsonCreator
        public HumanInSpaceView(@JsonProperty String craft,@JsonProperty String name) {
            this.craft = craft;
            this.name = name;
        }

        public String getCraft() {
            return craft;
        }

        public String getName() {
            return name;
        }
    }
}
