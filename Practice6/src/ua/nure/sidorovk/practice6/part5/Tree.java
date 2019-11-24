package ua.nure.sidorovk.practice6.part5;

public class Tree <E extends Comparable<E>> {
 private Node<E> root;

boolean add(E element) {
    if(root  == null){
        this.root = new Node<>(element, null, null);
        return true;
    }
    return add(root, element);
}

private boolean add(Node<E> node, E value) {
        if(value.compareTo(node.value)>0){
            if (node.right == null){
                node.right = new Node<>(value, null, null);
                return true;
            }
            return add(node.right, value);
        }
        if(value.compareTo(node.value)<0){
            if (node.left == null){
                node.left = new Node<>(value, null, null);
                return true;
            }
            return add(node.left , value);
        }
        return false;
}

void add(E[] elements) {
    for (E i :elements) {
        add(i);
    }
}

public boolean remove(E value){
    Node<E> current = this.root;
    Node<E> parent = this.root;
    boolean isLeftChild = false;
    while(current.value != value){
        parent = current;
        if(value.compareTo(current.value) < 0){
            current = current.left;
            isLeftChild = true;
        }else{
            current = current.right;
            isLeftChild = false;
        }
        if(current == null){
            return false;
        }
    }
    if(current.left == null && current.right == null){
        changeLinks(current, parent, isLeftChild, null);
    }else if(current.left == null){
        changeLinks(current, parent, isLeftChild, current.right);
    }else if(current.right == null){
        changeLinks(current, parent, isLeftChild, current.left);
    }else{
        Node<E> successor = findSuccessor(current);
        changeLinks(current, parent, isLeftChild, successor);
        successor.left = current.left;
    }
    return true;
}

    private void changeLinks(Node<E> current, Node<E> parent, boolean isLeftChild, Node<E> o) {
        if (current == this.root) {
            root = o;
        } else if (isLeftChild) {
            parent.left = o;
        } else {
            parent.right = o;
        }
    }

    private Node<E> findSuccessor(Node<E> node){
        Node<E> successor = node;
        Node<E> successorParent = node;
        Node<E> current = node.right;

        while(current != null){
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if(successor != node.right){
            successorParent.left = successor.right;
            successor.right = node.right;
        }
        return successor;
    }

void print(){
    print("", this.root);
}

private void print(String prefix, Node n) {
    String space = "  ";
        if (n != null) {
            print(prefix + space, n.left);
            if(n.equals(this.root)) {
                System.out.println(n.value);
            }else{
                System.out.println(prefix + n.value);
            }
            print(prefix + space, n.right);
        }
    }

private static class Node<E>{
        E value;
        Node <E> left;
        Node <E> right;

        Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.right = right;
            this.left = left;
        }
    }
}