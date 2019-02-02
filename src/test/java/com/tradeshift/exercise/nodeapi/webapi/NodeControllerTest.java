package com.tradeshift.exercise.nodeapi.webapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tradeshift.exercise.nodeapi.model.Node;
import com.tradeshift.exercise.nodeapi.service.DefaultNodeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class NodeControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private DefaultNodeService defaultNodeService;

    @Test
    public void test_getAllChildren() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        Node nodeChild1 = new Node(parentNode, parentNode, 1);
        nodeChild1 = defaultNodeService.saveNode(nodeChild1);

        Node nodeChild2 = new Node(parentNode, parentNode, 1);
        nodeChild2 = defaultNodeService.saveNode(nodeChild2);

        List<Node> expectedResponse = Arrays.asList(nodeChild1, nodeChild2);

        // when and then
        MvcResult mvcResult = mvc.perform(get("/nodes/" + parentNode.getId() + "/children")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(asJsonString(expectedResponse), response.getContentAsString());
    }

    @Test
    public void test_getAllChildren_none_found() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        List<Node> expectedResponse = Collections.emptyList();

        // when and then
        MvcResult mvcResult = mvc.perform(get("/nodes/" + parentNode.getId() + "/children")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();

        MockHttpServletResponse response = mvcResult.getResponse();
        Assert.assertEquals(asJsonString(expectedResponse), response.getContentAsString());
    }


    @Test
    public void test_updateParent() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        Node nodeChild = new Node(parentNode, parentNode, 1);
        nodeChild = defaultNodeService.saveNode(nodeChild);

        Node nodeGrandChild = new Node(parentNode, parentNode, 2);
        nodeGrandChild = defaultNodeService.saveNode(nodeGrandChild);

        // when
        // change nodeGrandChild parent to nodeChild
        mvc.perform(put("/nodes/" + nodeGrandChild.getId() + "/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(nodeChild)))
                .andDo(print());

        Node nodeUpdated = defaultNodeService.getNodeById(nodeGrandChild.getId());

        // then
        Assert.assertEquals(nodeGrandChild.getId(), nodeUpdated.getId());
        Assert.assertEquals(nodeUpdated.getParent().getId(), nodeChild.getId());
        Assert.assertEquals(nodeUpdated.getHeight(), nodeChild.getHeight() + 1);
    }

    @Test
    public void test_fail_updateParent_non_Existing_Node() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        Node nodeChild = new Node(parentNode, parentNode, 1);
        nodeChild = defaultNodeService.saveNode(nodeChild);

        Node nodeGrandChild = new Node(parentNode, parentNode, 2);
        defaultNodeService.saveNode(nodeGrandChild);

        // when and then
        long nonExistingId = 55;
        mvc.perform(put("/nodes/" + nonExistingId + "/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(nodeChild)))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void test_fail_updateParent_set_parent_of_root() throws Exception {
        // given
        Node rootNode = new Node(null, null, 0);
        rootNode = defaultNodeService.saveNode(rootNode);

        Node nodeChild = new Node(rootNode, rootNode, 1);
        nodeChild = defaultNodeService.saveNode(nodeChild);

        Node nodeGrandChild = new Node(rootNode, rootNode, 2);
        nodeGrandChild = defaultNodeService.saveNode(nodeGrandChild);

        // when and then
        mvc.perform(put("/nodes/" + rootNode.getId() + "/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(nodeChild)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    @Test
    public void test_fail_updateParent_with_nonexisting_parent() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        Node nodeChild = new Node(parentNode, parentNode, 1);
        nodeChild = defaultNodeService.saveNode(nodeChild);

        Node nodeGrandChild = new Node(parentNode, parentNode, 2);
        defaultNodeService.saveNode(nodeGrandChild);

        // when and then
        Node nonExistingParent = nodeChild;
        nonExistingParent.setId(33L);
        mvc.perform(put("/nodes/" + nodeGrandChild.getId() + "/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(nonExistingParent)))
                .andExpect(status().isNotFound()).andReturn();
    }

    @Test
    public void test_fail_updateParent_causing_circular_dependency() throws Exception {
        // given
        Node parentNode = new Node(null, null, 0);
        parentNode = defaultNodeService.saveNode(parentNode);

        Node nodeChild = new Node(parentNode, parentNode, 1);
        nodeChild = defaultNodeService.saveNode(nodeChild);

        Node nodeGrandChild = new Node(nodeChild, parentNode, 2);
        defaultNodeService.saveNode(nodeGrandChild);

        // when and then
        mvc.perform(put("/nodes/" + nodeChild.getId() + "/parent")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(nodeGrandChild)))
                .andExpect(status().isBadRequest()).andReturn();
    }

    // helper methods //

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Node asNode(final String jsonStr) {
        try {
            return new ObjectMapper().readValue(jsonStr, Node.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
