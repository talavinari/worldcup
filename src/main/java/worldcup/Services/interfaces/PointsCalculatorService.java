package worldcup.Services.interfaces;

import java.util.Map;

public interface PointsCalculatorService {
    void calculateUsersPoint();
    Map<String, Integer> getTeamToPointsMap();
}
