package com.ADS_Term_project;

import java.io.*;
import java.util.*;

class Node {
    private int nodeName;
    private Node pastNode;
    private ArrayList<Node> availableNode = new ArrayList<Node>();
    private ArrayList<Float> weightNode = new ArrayList<Float>();
    private ArrayList<Float> convertWeight = new ArrayList<Float>();
    private boolean visited = false;
    private float cost = 0.0F;
    private float convertCost = 0.0F;

    Node (int nodeName) {
        this.nodeName = nodeName;
    }
    float getCost() {
        return cost;
    }

    int getNodeName() {
        return nodeName;
    }

    Node getPastNode() {
        return pastNode;
    }

    ArrayList<Node> getAvailableNode() {
        return availableNode;
    }

    ArrayList<Float> getWeightNode() {
        return weightNode;
    }

    ArrayList<Float> getConvertWeight() {
        return convertWeight;
    }

    boolean getVisited() {
        return visited;
    }

    float getConvertCost() {
        return convertCost;
    }

    void setConvertWeight(float convertWeight) {
        this.convertWeight.add(convertWeight);
    }

    void setAvailableNode(Node availableNode) {
        this.availableNode.add(availableNode);
    }

    void setCost(float cost) {
        this.cost = cost;
    }

    void setConvertCost(float cost) {
        this.convertCost = cost;
    }

    void setPastNode(Node pastNode) {
        this.pastNode = pastNode;
    }

    void setVisited(boolean visited) {
        this.visited = visited;
    }

    void setWeightNode(float weightNode) {
        this.weightNode.add(weightNode);
    }


    boolean findInList(int key) {
        for (Node node : availableNode)
            if (node.getNodeName() == key)
                return false;
        return true;
    }

}

class Navi{

    private ArrayList<Node> nd = new ArrayList<Node>();
    private Queue<Node> nodeQueue = new LinkedList<Node>();


    void DFS(Node nd) {
        this.nd.add(nd);
        nd.setVisited(true);
        for (var i : nd.getAvailableNode()){
            if (!i.getVisited()) {
                DFS(i);
            }
        }

    }

    void init(ArrayList<Node> nodeList, int startNode){
        this.nodeQueue.clear();
        for(var i : nodeList) {
            if (i.getNodeName() == startNode){
                this.nodeQueue.add(i);
                break;
            }
        }
    }


    void BFS(int endNode) {

        var model = this.nodeQueue.poll();
        model.setVisited(true);

        for (var i : model.getAvailableNode()) {
            if (!i.getVisited()) {
                i.setPastNode(model);
                i.setCost(model.getCost() + model.getWeightNode().get(model.getAvailableNode().indexOf(i)));
                if (i.getNodeName() == endNode)
                    return;
                this.nodeQueue.add(i);
            }
        }
        BFS(endNode);
    }

    void printPath(int startNode, int endNode, ArrayList<Node> ndList) {
        Stack<Integer> st = new Stack<Integer>();
        for(var i : ndList) {
            if (i.getNodeName() == endNode) {
                System.out.println("이동 비용은 : "+i.getCost());

                while(i.getPastNode() != null) {
                    st.push(i.getNodeName());
                    i = i.getPastNode();
                    if ( i.getNodeName() == startNode)
                        break;

                }
                st.push(i.getNodeName());
            }
        }
        System.out.print("이동경로 ");
        while (!st.empty()) {

            System.out.print(" -> ");
            System.out.print(st.pop());
        }
    }



    void SSSP(int endNode) {
        var model = this.nodeQueue.poll();
        var movableList = new ArrayList<Node>();
        movableList.add(model);


        while (model.getNodeName() != endNode) {

            movableList.remove(model);
            model.setVisited(true);

            for (var i : model.getAvailableNode()) {
                if (!i.getVisited()) {
                    if (movableList.contains(i)) {
                        if (i.getConvertCost() > model.getConvertCost() + model.getConvertWeight().get(model.getAvailableNode().indexOf(i))) {
                            i.setPastNode(model);
                            i.setCost(model.getCost() + model.getWeightNode().get(model.getAvailableNode().indexOf(i)));
                            i.setConvertCost(model.getConvertCost() + model.getConvertWeight().get(model.getAvailableNode().indexOf(i)));
                        }

                    }else {
                        i.setPastNode(model);
                        i.setCost(model.getCost() + model.getWeightNode().get(model.getAvailableNode().indexOf(i)));
                        i.setConvertCost(model.getConvertCost() + model.getConvertWeight().get(model.getAvailableNode().indexOf(i)));
                        movableList.add(i);
                    }
                }
            }
            int k = 0;
            for (var i:movableList) {
                if (i.getConvertCost() < movableList.get(k).getConvertCost()){
                    k = movableList.indexOf(i);
                }
            }
            model = movableList.get(k);
        }

    }

    ArrayList<Node> getNd() {
        return this.nd;
    }
}

public class Main {

    public static void main(String[] args) {
        try {
            File file = new File("/Users/sigae/Downloads/mapss.txt");
            ArrayList<ArrayList<String>> nodeList = new ArrayList<ArrayList<String>>();
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                ArrayList<String> listLine = new ArrayList<String>(Arrays.asList(line.split("\t")).subList(0, 3));
                nodeList.add(listLine);
            }

            ArrayList<Integer> arr = new ArrayList<Integer>();
            for (var st : nodeList) {
                for (int i = 0 ; i < 2 ; i +=1)
                    arr.add(Integer.parseInt(st.get(i)));
            }
            TreeSet<Integer> arr2 = new TreeSet<Integer>(arr);
            ArrayList<Integer> arr3 = new ArrayList<Integer>(arr2);

            Node[] node = new Node[arr3.size()];
            for (int i = 0 ; i < node.length ; i+=1) {
                node[i] = new Node(arr3.get(i));
            }

            for (var i : nodeList) {
                for (var j:node) {
                    if (j.getNodeName() == Integer.parseInt(i.get(0))) {
                        for (var k : node ) {
                            if (k.getNodeName() == Integer.parseInt(i.get((1)))) {
                                insertNodeInfo(i, j, k);
                            }
                        }
                    } else if (j.getNodeName() == Integer.parseInt(i.get(1))) {
                        for (var k : node) {
                            if (k.getNodeName() == Integer.parseInt(i.get((0)))) {
                                insertNodeInfo(i, j, k);
                            }
                        }
                    }
                }
            }

            ArrayList<ArrayList<Node>> setupNode = new ArrayList<ArrayList<Node>>();
            Navi navi = new Navi();


            for (var i : node) {
                navi.getNd().clear();
                if (!i.getVisited()){
                    navi.DFS(i);
                    setupNode.add(new ArrayList<Node>(navi.getNd()));
                }
            }


            Scanner sc = new Scanner(System.in);
            System.out.print("input start node : ");
            int startNode = sc.nextInt();
            System.out.print("input end node : ");
            int endNode = sc.nextInt();

            boolean checkPath = false;

            for (ArrayList<Node> nodes : setupNode) {

                boolean checkStart = false;
                boolean checkEnd = false;
                for (var i : nodes) {
                    if (i.getNodeName() == startNode) {
                        checkStart = true;
                        break;
                    }
                }
                for (var i : nodes) {
                    if (i.getNodeName() == endNode) {
                        checkEnd = true;
                        break;
                    }
                }

                if (checkStart && checkEnd) {
                    System.out.println("경로 존재함");
                    checkPath= true;
                    for (var i : nodes )
                        i.setVisited(false);
                    navi.init(nodes, startNode);
                    navi.BFS(endNode);
                    System.out.println("case : BFS");
                    navi.printPath(startNode, endNode, nodes);
                    for (var i : nodes )
                        i.setVisited(false);
                    System.out.println("\n----------------------");
                    navi.init(nodes, startNode);
                    navi.SSSP(endNode);
                    System.out.println("case : SSSP");
                    navi.printPath(startNode, endNode, nodes);
                }

            }
            if (!checkPath) {
                System.out.println("경로가 존재하지않습니다");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not Found!");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static void insertNodeInfo(ArrayList<String> i, Node j, Node k) {
        if (j.findInList(k.getNodeName())){
            j.setAvailableNode(k);
            k.setAvailableNode(j);
            j.setConvertWeight(1.0F - Float.parseFloat(i.get(2)));
            k.setConvertWeight(1.0F - Float.parseFloat(i.get(2)));
            j.setWeightNode(Float.parseFloat(i.get(2)));
            k.setWeightNode(Float.parseFloat(i.get(2)));
        }
    }
}