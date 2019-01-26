package com.tradeshift.exercise.nodeapi.service;

import com.tradeshift.exercise.nodeapi.dal.NodeDAO;
import com.tradeshift.exercise.nodeapi.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NodeService {

    @Autowired
    private NodeDAO nodeDAO;

    /**
     * {@inheritDoc}
     */
    public List<Node> getAllChildren(String nodeId) {
        return nodeDAO.findAllChildren(nodeId);
    }

    /**
     * {@inheritDoc}
     */
    public Boolean updateParent(String nodeId) {
        return nodeDAO.updateParent(nodeId);
    }
}
