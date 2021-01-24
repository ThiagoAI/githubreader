package com.thiago.githubreader;

import com.thiago.githubreader.adapters.web.api.ReadrepoApi;
import com.thiago.githubreader.adapters.web.model.ReadRepoRequestApiModel;
import org.apache.http.HttpStatus;
import org.apache.http.entity.ContentType;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GithubreaderApiTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ReadrepoApi readrepoApi;

    @Test
    public void emptyString() throws Exception {
        ReadRepoRequestApiModel readRepoRequestApiModel =
                new ReadRepoRequestApiModel().url("");

        mvc
                .perform(post("/readrepo")
                        .contentType(ContentType.APPLICATION_JSON.getMimeType())
                        .content("{\"url\":\"\"}"))
                .andExpect(status().is(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }

    @Test
    public void invalidURL() throws Exception {
        ReadRepoRequestApiModel readRepoRequestApiModel =
                new ReadRepoRequestApiModel().url("");

        mvc
                .perform(post("/readrepo")
                        .contentType(ContentType.APPLICATION_JSON.getMimeType())
                        .content("{\"url\":\"http://invalidurl.com\"}"))
                .andExpect(status().is(HttpStatus.SC_UNPROCESSABLE_ENTITY));
    }

    @Test
    public void simpleRepo() throws Exception {
        ReadRepoRequestApiModel readRepoRequestApiModel =
                new ReadRepoRequestApiModel().url("");

        mvc
                .perform(post("/readrepo")
                        .contentType(ContentType.APPLICATION_JSON.getMimeType())
                        .content("{\"url\":\"https://github.com/sarcasticadmin/empty-repo\"}"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalLines").value("47"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalSize").value("686,00 Bytes"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.fileCount").value("2"));
    }
}
