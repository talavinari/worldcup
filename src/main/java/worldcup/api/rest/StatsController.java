package worldcup.api.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import worldcup.Services.interfaces.StatsService;


@RestController
@RequestMapping(path = "/stats")
public class StatsController {

    private static final Logger logger = LoggerFactory.getLogger(StatsController.class);

    @Autowired
    private StatsService statsService;

    @RequestMapping(method = RequestMethod.GET, path = "")
    @ResponseBody
    public ResponseEntity<?> getStats() {
        return new ResponseEntity<>(statsService.getStats(), HttpStatus.OK);
    }

}