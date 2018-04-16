package worldcup.Services.interfaces;

import worldcup.api.dtos.*;
import worldcup.persistance.entities.*;

import java.util.List;

public interface ConverterService {
    TeamDto convertTeamToTeamDto(Team team);
    List<TeamDto> convertTeamsToTeamsDto(List<Team> teams);

    Bet covertBetDtoToBet(BetDto betRequest, User newUser);
    BetDtoResponse covertBetToBetDtoResponse(Bet x);

    GameResponseDto covertGameToGameDto(Game game);

    GroupDto covertGroupToGroupDto(Group group);
    List<GroupDto> covertGroupsToGroupDtos(List<Group> groups);

    UserDto covertUserToUserDto(User user);
    List<UserDto> covertUsersToUserDtos(List<User> users);
}
