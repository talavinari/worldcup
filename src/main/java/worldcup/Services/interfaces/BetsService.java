package worldcup.Services.interfaces;

import worldcup.api.dtos.BetDto;
import worldcup.persistance.entities.Bet;

import java.util.ArrayList;

public interface BetsService {

    ArrayList<Bet> getAllBets();
    void validateBet(BetDto bet);
    void save(Bet bet);
    Bet findBetForUser();
}
