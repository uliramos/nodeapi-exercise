package com.tradeshift.exercise.nodeapi.webapi;


import com.tradeshift.exercise.nodeapi.model.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController("/node")
public class NodeController {

    @RequestMapping(
            value = "/{id}/children",
            method = RequestMethod.GET)
    public ResponseEntity<List<Node>> getAllChildren(@RequestParam(value="id") String nodeId) {
        List<Node> nodeList = new ArrayList<>();
        return new ResponseEntity<>(nodeList, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/{id}",
            method = RequestMethod.PUT)
    public ResponseEntity<Boolean> updateParent(@RequestParam(value="id") String nodeId) {
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}


