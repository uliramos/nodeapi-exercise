package com.tradeshift.exercise.nodeapi.webapi;


import com.tradeshift.exercise.nodeapi.model.Node;
import com.tradeshift.exercise.nodeapi.service.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/node")
public class NodeController {

    @Autowired
    private NodeService nodeService;

    @GetMapping(value = "/{id}/children", produces = "application/json")
    public ResponseEntity<List<Node>> getAllChildren(@PathVariable(value="id") Long nodeId) {
        List<Node> nodeList = nodeService.getAllChildren(nodeId);
        return new ResponseEntity<>(nodeList, HttpStatus.OK);
    }

    @PutMapping(value = "/{id}/parent", produces = "application/json")
    public ResponseEntity<Boolean> updateParent(@PathVariable(value="id") Long nodeId,
                                                @RequestBody Node newParent) {
        nodeService.updateParent(nodeId, newParent);
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}


