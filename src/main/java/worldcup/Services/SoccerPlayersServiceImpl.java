package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.entities.SoccerPlayer;
import worldcup.repository.SoccerPlayerRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SoccerPlayersServiceImpl implements SoccerPlayersService {

    @Autowired
    private SoccerPlayerRepository soccerPlayerRepository;

    @Override
    public Map<String, SoccerPlayer> getAllSoccerPlayersByName() {
        Iterable<SoccerPlayer> allScorers = soccerPlayerRepository.findAll();
        Map<String, SoccerPlayer> scorersMap = Lists.newArrayList(allScorers)
                .stream()
                .collect(Collectors.toMap(x -> x.getName(), Function.identity()));
        return scorersMap;
    }

    @Override
    public SoccerPlayer save(SoccerPlayer soccerPlayer) {
        return soccerPlayerRepository.save(soccerPlayer);
    }

    @Override
    public List<SoccerPlayer> getBestScorer() {
        List<SoccerPlayer> soccerPlayers =
                Lists.newArrayList(soccerPlayerRepository.findAll())
                        .stream()
                        .sorted(Comparator.comparing(SoccerPlayer::getNumberOfGoals).reversed())
                        .collect(Collectors.toList());
        if (soccerPlayers.size() > 0) {
            Integer numberOfGoals = soccerPlayers.get(0).getNumberOfGoals();
            return soccerPlayers
                    .stream()
                    .filter(x->x.getNumberOfGoals().equals(numberOfGoals))
                    .collect(Collectors.toList());
        } else {
            return null;
        }
    }
}
