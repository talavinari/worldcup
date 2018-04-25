package worldcup.Services.impls;

import org.springframework.stereotype.Service;
import worldcup.Services.interfaces.ConverterService;
import worldcup.api.dtos.*;
import worldcup.persistance.entities.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ConverterServiceImpl implements ConverterService {

    private static final String DATE_FORMAT_FOR_UI = "dd/MM HH:mm";

    @Override
    public TeamDto convertTeamToTeamDto(Team team) {
        return new TeamDto(
                team.getName(),
                team.getRank(),
                team.getFinalPosInGroupStage()
        );
    }

    @Override
    public List<TeamDto> convertTeamsToTeamsDto(List<Team> teams) {
        List<TeamDto> teamDtos = new ArrayList<>();
        teams.forEach(x-> teamDtos.add(convertTeamToTeamDto(x)));
        return teamDtos;
    }

    @Override
    public Bet covertBetDtoToBet(BetDto betRequest, User newUser) {
        return new Bet(
                betRequest.getRankA(),
                betRequest.getRankB(),
                betRequest.getRankC(),
                betRequest.getRankD(),
                betRequest.getBestScorer(),
                betRequest.getBestAttack(),
                betRequest.getWorstDefence(), newUser);
    }

    @Override
    public BetDtoResponse covertBetToBetDtoResponse(Bet x) {
        return new BetDtoResponse
                (x.getRankA(), x.getRankB(), x.getRankC(), x.getRankD(), x.getBestScorer(),
                        x.getBestAttack(), x.getWorstDefence(), x.getUser().getName(),
                        x.getUser().getEmail(), x.getUser().getPoints(), x.getUser().getGroup().getName());
    }

    @Override
    public GameResponseDto covertGameToGameDto(Game game) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(game.getGameTime());
        String dateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_UI).format(instance.getTime());
        return new GameResponseDto(
                game.getId(),
                game.getTeam1(),
                game.getTeam2(),
                game.getScore1(),
                game.getScore2(),
                game.getExtraTimeScore1(),
                game.getExtraTimeScore2(),
                game.getPenaltyScore1(),
                game.getPenaltyScore2(),
                dateFormat,
                game.getStage().name(),
                game.getShouldOverride()
        );
    }

    @Override
    public GroupDto covertGroupToGroupDto(Group group) {
        return new GroupDto(
                group.getId(),
                group.getName()
        );
    }

    @Override
    public List<GroupDto> covertGroupsToGroupDtos(List<Group> groups) {
        List<GroupDto> groupDtos = new ArrayList<>();
        groups.forEach(x-> groupDtos.add(covertGroupToGroupDto(x)));
        return groupDtos;
    }

    @Override
    public UserDto covertUserToUserDto(User user) {
        return new UserDto(
                user.getName(),
                user.getEmail(),
                user.getPoints(),
                user.getGroup(),
                user.getBet()
        );
    }

    @Override
    public List<UserDto> covertUsersToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(x-> userDtos.add(covertUserToUserDto(x)));
        return userDtos;
    }

    @Override
    public List<ResponsePlayerDto> convertPlayersToPlayerString(List<SoccerPlayer> soccerPlayers) {
        return soccerPlayers.stream().
                map(x->new ResponsePlayerDto(x.getId().toString(), x.getName(), x.getNumberOfGoals().toString())).
                collect(Collectors.toList());
    }
}
