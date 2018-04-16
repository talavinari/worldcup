package worldcup;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import worldcup.api.dtos.GameResponseDto;
import worldcup.api.dtos.GamesResponseDto;
import worldcup.api.dtos.NewGameDto;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FirstTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Before
    public void init(){
        jdbcTemplate.update("TRUNCATE SCHEMA PUBLIC RESTART IDENTITY AND COMMIT NO CHECK");
    }

    @Test
    public void first() throws Exception {
        MvcResult controllerResponse =
                mvc.perform(get("/games"))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        GamesResponseDto gamesResponseDto = gson.fromJson
                (controllerResponse.getResponse().getContentAsString(), GamesResponseDto .class);

        Assert.assertNotNull(gamesResponseDto);
    }

    @Test
    public void createGame() throws Exception {
        NewGameDto newGame = new NewGameDto("France", "Spain", null, "Group", false);
        MvcResult response = mvc.perform(post("/games")
                .header("Content-Type", "application/json")
                .content(gson.toJson(newGame)))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        GameResponseDto gamesResponseDto = gson.fromJson
                (response.getResponse().getContentAsString(), GameResponseDto .class);

        Assert.assertNotNull(gamesResponseDto);
        Long createdGameID = gamesResponseDto.getId();
        Assert.assertNotNull(createdGameID);

        response = mvc.perform(get("/games/" + gamesResponseDto.getId()))
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        gamesResponseDto = gson.fromJson
                (response.getResponse().getContentAsString(), GameResponseDto .class);

        Assert.assertNotNull(gamesResponseDto);
        Assert.assertEquals(gamesResponseDto.getId(), createdGameID);
    }


}
