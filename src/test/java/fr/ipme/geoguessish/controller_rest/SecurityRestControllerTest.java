package fr.ipme.geoguessish.controller_rest;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.hasSize;

import fr.ipme.geoguessish.dto.RegisterDTO;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

@SpringBootTest
@AutoConfigureMockMvc
public class SecurityRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testLoginSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("matteo.lefevre@gmail.com", "12345")));

        resultActions.andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    public void testLoginFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("matteo.lefevre@gmail.com", "12346")));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void testLoginValidationFailed() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getLoginJsonFromData("matteo.lefebre@gmail.com", "")));

        resultActions.andExpect(status().is4xxClientError());
    }

    @Test
    public void testRegisterSuccess() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromDTO(new RegisterDTO(
                        "totoIsEmail@gmail.com",
                        "Toto",
                        "fakePathToAvatar",
                        "12345",
                        "12345",
                        LocalDate.now().minusYears(24)
                ))));

        resultActions.andExpect(status().is2xxSuccessful())
            .andExpectAll(
                jsonPath("$.id").exists(),
                jsonPath("$.username").exists(),
                jsonPath("$.email").exists(),
                jsonPath("$.avatar").exists(),
                jsonPath("$.birthedAt").exists(),
                jsonPath("$.createdAt").exists(),
                jsonPath("$.level").value(1),
                jsonPath("$.admin").value(Boolean.FALSE),
                jsonPath("$.*", hasSize(8)));
    }

    @Test
    public void testRegisterFailNotEmail() throws Exception {
        ResultActions resultActions = mockMvc.perform(
            post("/api/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(getRegisterJsonFromDTO(new RegisterDTO(
                    "totoIsNotAnEmail",
                    "Toto",
                    "fakePathToAvatar",
                    "12345",
                    "12345",
                    LocalDate.now().minusYears(24)
                ))));

        resultActions.andExpect(status().is4xxClientError());
    }

    private String getLoginJsonFromData(String email, String pwd) throws JSONException  {
        JSONObject object = new JSONObject();
        object.put("email", email);
        object.put("password", pwd);
        return object.toString();
    }

    private String getRegisterJsonFromDTO(RegisterDTO user) throws JSONException {
        JSONObject object = new JSONObject();
        object.put("email", user.getEmail());
        object.put("username", user.getUsername());
        object.put("avatar", user.getAvatar());
        object.put("password", user.getPassword());
        object.put("confirmedPassword", user.getConfirmedPassword());
        object.put("birthedAt", user.getBirthedAt());
        return object.toString();
    }

}