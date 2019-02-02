package com.tradeshift.exercise.nodeapi.service;

import com.tradeshift.exercise.nodeapi.model.Node;

import java.util.List;

public interface NodeService {
    Node getNodeById(Long nodeId);
    Node saveNode(Node node);
    List<Node> getAllChildren(Long nodeId);
    Node updateParent(Long nodeId, Node newParent);
}
