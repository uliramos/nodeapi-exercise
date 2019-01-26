package com.tradeshift.exercise.nodeapi.model;

import java.io.Serializable;

public class Node implements Serializable {

    private int id;
    private Node parent;
    private Node root;
    private int height;

    /**
     * Default constructor.
     */
    public Node() {
    }

    /**
     * Constructor with all attributes.
     * @param id
     * @param parent
     * @param root
     * @param height
     */
    public Node(int id, Node parent, Node root, int height) {
        this.id = id;
        this.parent = parent;
        this.root = root;
        this.height = height;
    }

    // getters and setters //

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}