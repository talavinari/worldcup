package worldcup.Services;

import worldcup.entities.SoccerPlayer;

import java.util.List;
import java.util.Map;

public interface SoccerPlayersService {
    Map<String, SoccerPlayer> getAllSoccerPlayersByName();
    SoccerPlayer save(SoccerPlayer soccerPlayer);
    List<SoccerPlayer> getBestScorer();
}
