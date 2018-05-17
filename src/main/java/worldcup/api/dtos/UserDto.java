package worldcup.api.dtos;

import java.util.Objects;

public class UserDto {
    private String name;
    private String email;
    private Integer points;
    private String group;
    private String thumbNailData;

    public UserDto(String name, String email, Integer points, String group, String thumbNailData) {
        this.name = name;
        this.email = email;
        this.points = points;
        this.group = group;
        this.thumbNailData =  thumbNailData;
    }

    public UserDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getThumbNailData() {
        return thumbNailData;
    }

    public void setThumbNailData(String thumbNailData) {
        this.thumbNailData = thumbNailData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(name, userDto.name) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(group, userDto.group);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, email, group);
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", group='" + group + '\'' +
                '}';
    }
}
