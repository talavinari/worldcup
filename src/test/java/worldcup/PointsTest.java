package worldcup;

import com.google.gson.Gson;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PointsTest {


    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    public void testPoints(){
//        NewGameDto newGame = new NewGameDto("France", "Spain", null, "Group", false);
//        MvcResult response = mvc.perform(post("/games")
//                .header("Content-Type", "application/json")
//                .content(gson.toJson(newGame)))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//
//        GameResponseDto gamesResponseDto = gson.fromJson
//                (response.getResponse().getContentAsString(), GameResponseDto .class);
//
//        Assert.assertNotNull(gamesResponseDto);
//        Long createdGameID = gamesResponseDto.getId();
//        Assert.assertNotNull(createdGameID);
//
//        response = mvc.perform(get("/games/" + gamesResponseDto.getId()))
//                .andExpect(status().is2xxSuccessful())
//                .andReturn();
//
//        gamesResponseDto = gson.fromJson
//                (response.getResponse().getContentAsString(), GameResponseDto .class);
//
//        Assert.assertNotNull(gamesResponseDto);
//        Assert.assertEquals(gamesResponseDto.getId(), createdGameID);
    }
}
