package worldcup.Services.interfaces;

import worldcup.api.dtos.*;
import worldcup.persistance.entities.*;

import java.util.List;
import java.util.Map;

public interface ConverterService {
    TeamDto convertTeamToTeamDto(Team team);
    List<TeamDto> convertTeamsToTeamsDto(List<Team> teams);
    Bet covertBetDtoToBet(BetDto betRequest, User newUser);
    BetDtoResponse covertBetToBetDtoResponse(Bet x, Map<String, Integer> teamToPointsMap);
    GameResponseDto covertGameToGameDto(Game game);
    UserDto covertUserToUserDto(User user);
    List<UserDto> covertUsersToUserDtos(List<User> users);
    List<ResponsePlayerDto> convertPlayersToPlayerString(List<SoccerPlayer> soccerPlayers);
}
