package worldcup.Services;

import worldcup.dtos.BetDto;
import worldcup.entities.Bet;

import java.util.ArrayList;

public interface BetsService {

    ArrayList<Bet> getAllBets();
    void validateBet(BetDto bet);
    void save(Bet bet);
}
