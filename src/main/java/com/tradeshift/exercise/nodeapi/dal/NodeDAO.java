package com.tradeshift.exercise.nodeapi.dal;

import com.tradeshift.exercise.nodeapi.model.Node;

import java.util.List;

public interface NodeDAO {

    /**
     * Find all Children associated to the node matching the nodeId param.
     * @param nodeId identifier of the node to find children from.
     * @return list of matching children nodes.
     */
    List<Node> findAllChildren(String nodeId);

    /**
     * Updates the parent attribute of the matching node.
     * @param nodeId
     * @return
     */
    Boolean updateParent(String nodeId);
}
