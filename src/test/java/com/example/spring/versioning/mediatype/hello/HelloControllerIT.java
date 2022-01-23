package com.example.spring.versioning.mediatype.hello;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerIT
{
    // ============================== [Fields] ==============================

    // -------------------- [Private Fields] --------------------

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    // ============================== [Unit Tests] ==============================

    // -------------------- [Test Helper Classes] --------------------

    // -------------------- [Test Helper Methods] --------------------

    // -------------------- [Test Initialization] --------------------

    // -------------------- [Tests] --------------------

    @Test
    void getV1() throws Exception
    {
        // @formatter:off
        mvc.perform(get("/hello").accept("application/vnd.hello.v1+json"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.text").value("Version 1"));
        // @formatter:on
    }

    @Test
    void getV2() throws Exception
    {
        // @formatter:off
        mvc.perform(get("/hello").accept("application/vnd.hello.v2+json"))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.text").value("Version 2"))
           .andExpect(jsonPath("$.number").value(2));
        // @formatter:on
    }

    @Test
    void postV1() throws Exception
    {
        HelloV1 hello = new HelloV1("Version 1");
        String jsonStr = this.objectMapper.writeValueAsString(hello);

        // @formatter:off
        mvc.perform(post("/hello").contentType("application/vnd.hello.v1+json").content(jsonStr))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.text").value("New Version 1"));
        // @formatter:on
    }

    @Test
    void postV2() throws Exception
    {
        HelloV2 hello = new HelloV2("Version 2", 2);
        String jsonStr = this.objectMapper.writeValueAsString(hello);

        // @formatter:off
        mvc.perform(post("/hello").contentType("application/vnd.hello.v2+json").content(jsonStr))
           .andDo(print())
           .andExpect(status().isOk())
           .andExpect(jsonPath("$.text").value("New Version 2"))
           .andExpect(jsonPath("$.number").value(22));
        // @formatter:on
    }
}
