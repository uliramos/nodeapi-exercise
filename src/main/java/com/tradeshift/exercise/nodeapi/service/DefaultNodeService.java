package com.tradeshift.exercise.nodeapi.service;

import com.tradeshift.exercise.nodeapi.CannotSetParentOfRootException;
import com.tradeshift.exercise.nodeapi.CircularTreeDependencyException;
import com.tradeshift.exercise.nodeapi.TradeShiftNodeNotFoundException;
import com.tradeshift.exercise.nodeapi.dal.NodeDAO;
import com.tradeshift.exercise.nodeapi.model.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class DefaultNodeService implements NodeService {

    @Autowired
    private NodeDAO nodeDAO;

    /**
     * Retrieve @{@link Node} by id.
     * @param nodeId
     * @return
     */
    @Override
    public Node getNodeById(Long nodeId) {
        return nodeDAO.findById(nodeId)
                .orElseThrow(() ->
                        new TradeShiftNodeNotFoundException("Node not found. NodeId " + nodeId));
    }

    /**
     * Add @{@link Node} to be persisted.
     * @param node
     * @return
     */
    @Override
    public Node saveNode(Node node) {
        Node nodeReturned = nodeDAO.save(node);

        if (node == null) {
            throw new RuntimeException("Failed to add node ");
        }
        return nodeReturned;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Node> getAllChildren(Long nodeId) {
        List<Node> children = nodeDAO.findAllChildren(nodeId);

        if (children == null) {
            throw new RuntimeException("Failed to retrieve children for node " + nodeId);
        }
        return children;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Node updateParent(Long nodeId, Node newParent) {
        Node nodeExisting = nodeDAO.findById(nodeId)
                .orElseThrow(() -> new TradeShiftNodeNotFoundException("Node not found to update. NodeId " + nodeId));

        if (nodeExisting.getRoot() == null) {
            throw new CannotSetParentOfRootException();
        }

        Node newParentExisting = nodeDAO.findById(newParent.getId())
                .orElseThrow(() -> new TradeShiftNodeNotFoundException("Parent Node not found to update with. NodeId " + nodeId));

        if (newParentExisting.getParent().getId() == nodeExisting.getId()) {
            throw new CircularTreeDependencyException();
        }
        nodeExisting.setParent(newParentExisting);
        nodeExisting.setHeight(newParentExisting.getHeight() + 1);
        return nodeDAO.save(nodeExisting);
    }
}
