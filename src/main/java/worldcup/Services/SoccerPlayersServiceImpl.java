package worldcup.Services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import worldcup.entities.SoccerPlayer;
import worldcup.repository.SoccerPlayerRepository;

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
}
