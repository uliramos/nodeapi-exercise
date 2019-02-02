package com.tradeshift.exercise.nodeapi.dal;

import com.tradeshift.exercise.nodeapi.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NodeDAO extends JpaRepository<Node, Long> {

    /**
     * Find node matching nodeId.
     * @param nodeId identifier of the node.
     * @return list of matching children nodes.
     */
    @Query("SELECT n FROM Node n WHERE n.parent.id = :id")
    List<Node> findAllChildren(@Param("id") Long nodeId);

}
