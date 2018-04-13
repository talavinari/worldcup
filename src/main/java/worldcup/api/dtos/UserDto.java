package worldcup.api.dtos;

import worldcup.persistance.entities.Bet;
import worldcup.persistance.entities.Group;

import javax.validation.constraints.Email;
import java.util.Objects;

public class UserDto {

    private Long id;
    private String name;
    private String email;
    private Integer points;
    private Group group;
    private Bet bet;

    public UserDto(String name, @Email String email, Integer points, Group group, Bet bet) {
        this.name = name;
        this.email = email;
        this.points = points;
        this.group = group;
        this.bet = bet;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Bet getBet() {
        return bet;
    }

    public void setBet(Bet bet) {
        this.bet = bet;
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
                Objects.equals(group, userDto.group) &&
                Objects.equals(bet, userDto.bet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, name, email, points, group, bet);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", points=" + points +
                ", group=" + group +
                ", bet=" + bet +
                '}';
    }
}
