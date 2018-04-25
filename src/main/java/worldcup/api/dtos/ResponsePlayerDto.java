package worldcup.api.dtos;

public class ResponsePlayerDto {
    private String id;
    private String name;
    private String goalsNumber;

    public ResponsePlayerDto(String id, String name, String goalsNumber) {
        this.id = id;
        this.name = name;
        this.goalsNumber = goalsNumber;
    }

    public ResponsePlayerDto() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGoalsNumber() {
        return goalsNumber;
    }

    public void setGoalsNumber(String goalsNumber) {
        this.goalsNumber = goalsNumber;
    }
}

