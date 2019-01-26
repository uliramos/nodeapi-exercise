package com.tradeshift.exercise.nodeapi.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeshift.exercise.nodeapi.model.Node;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NodeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void test_getAllChildren() throws Exception {
        // given
        // TODO setup data
        int nodeId = 1;
        List<Node> expectedResponse = new ArrayList<>();

        // when
        MvcResult mvcResult = mvc.perform(get("/node/" + nodeId + "/children")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
        Assert.assertEquals(asJsonString(expectedResponse), response.getContentAsString());
    }

    @Test
    public void test_updateParent() throws Exception {
        // given
        // TODO setup data
        Node existingNode =  new Node();
        int nodeId = 1;
        List<Node> expectedResponse = new ArrayList<>();

        mvc.perform(post("/node/" + nodeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(existingNode)))
                .andDo(print());

        // when
        // TODO update existingNode
        mvc.perform(put("/node/" + nodeId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(existingNode)))
                .andDo(print());

        MvcResult mvcResult = mvc.perform(get("/node/" + nodeId + "/children")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK, response.getStatus());
        Assert.assertEquals(asJsonString(expectedResponse), response.getContentAsString());
    }

    // helper methods //

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
