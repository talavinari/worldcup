package worldcup.Services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import worldcup.Services.Person;
import worldcup.Services.interfaces.ConverterService;
import worldcup.Services.interfaces.LdapDataService;
import worldcup.Services.interfaces.UsersService;
import worldcup.api.dtos.*;
import worldcup.persistance.entities.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConverterServiceImpl implements ConverterService {

    private static final String DATE_FORMAT_FOR_UI = "dd/MM HH:mm";

    @Autowired
    private LdapDataService ldapDataService;

    @Autowired
    private UsersService usersService;

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
        teams.forEach(x -> teamDtos.add(convertTeamToTeamDto(x)));
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
    public BetDtoResponse covertBetToBetDtoResponse(Bet x, Map<String, Integer> teamToPointsMap) {

        return new BetDtoResponse
                (x.getRankA(),
                        teamToPointsMap.get(x.getRankA()),
                        x.getRankB(),
                        teamToPointsMap.get(x.getRankB()),
                        x.getRankC(),
                        teamToPointsMap.get(x.getRankC()),
                        x.getRankD(),
                        teamToPointsMap.get(x.getRankD()),
                        x.getBestScorer(),
                        x.getBestAttack(), x.getWorstDefence(), x.getUser().getName(),
                        x.getUser().getEmail(), x.getUser().getPoints(), x.getUser().getGroup());
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
    public UserDto covertUserToUserDto(User user) {
        if (user == null) {
            return new UserDto();
        }

        String userName = usersService.getCurrentLoggedInUserName();

        Person moreDataFromLdap = ldapDataService.findBysAMAccountName(userName);

        return new UserDto(
                user.getName(),
                moreDataFromLdap.getMail(),
                user.getPoints()== null ? 0 : user.getPoints(),
                StringUtils.isEmpty(user.getGroup()) ? moreDataFromLdap.getDepartment() : user.getGroup(),
                moreDataFromLdap.getThumbnailPhoto());
    }

    @Override
    public List<UserDto> covertUsersToUserDtos(List<User> users) {
        List<UserDto> userDtos = new ArrayList<>();
        users.forEach(x -> userDtos.add(covertUserToUserDto(x)));
        return userDtos;
    }

    @Override
    public List<ResponsePlayerDto> convertPlayersToPlayerString(List<SoccerPlayer> soccerPlayers) {
        return soccerPlayers.stream().
                map(x -> new ResponsePlayerDto(x.getId().toString(), x.getName(), x.getNumberOfGoals().toString())).
                collect(Collectors.toList());
    }
}
