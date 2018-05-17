package worldcup.api.dtos;

import javax.validation.constraints.Email;
import java.util.Objects;

public class UserDto {
    private Long id;
    private String name;
    private String email;
    private Integer points;
    private String group;

    public UserDto(String name, @Email String email, Integer points, String group) {
        this.name = name;
        this.email = email;
        this.points = points;
        this.group = group;
    }

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(id, userDto.id) &&
                Objects.equals(name, userDto.name) &&
                Objects.equals(email, userDto.email) &&
                Objects.equals(points, userDto.points) &&
                Objects.equals(group, userDto.group) ;
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, points, group);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", group=" + group +
                '}';
    }
}
