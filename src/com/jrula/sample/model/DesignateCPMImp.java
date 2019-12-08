package com.jrula.sample.model;

import com.jrula.sample.Element;
import javafx.collections.ObservableList;
import org.jgrapht.Graph;
import org.jgrapht.GraphPath;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.graph.DirectedWeightedMultigraph;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DesignateCPMImp implements DesignateCPM {

    Graph<Vertex, Edge> graph = null;

    List<Path> allGraphPaths = new ArrayList<>();
    int minVertex;
    int maxVertex;

    @Override
    public String designateCriticalPath() {

        getAllPathsInGraph(minVertex, maxVertex);
        allGraphPaths = allGraphPaths.stream().peek(Path::calculatePathWeight).sorted(Path::compareTo).collect(Collectors.toList());
        System.out.println(allGraphPaths);
        String initialTime = Double.toString(allGraphPaths.get(0).getPathWeight());
        String criticalPath = allGraphPaths.get(0).getCriticalPath();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(initialTime);
        stringBuilder.append("  ");
        stringBuilder.append(criticalPath);
        return stringBuilder.toString();
    }

    @Override
    public String designateCPMMethodCost() {
        double totalCost =0.0;
        double optimalValue =0.0;
        while(!checkIfOptimizationCompleted()){
            List<Path> pathList = findCriticalList();
            Double unitCost = calculateUnitPathCost(pathList);
            if(unitCost != null){
                totalCost = totalCost +unitCost.doubleValue();
            }
            else{
                break;
            }
            allGraphPaths = allGraphPaths.stream().peek(Path::calculatePathWeight).sorted(Path::compareTo).collect(Collectors.toList());
        }
        String finalTime = Double.toString(allGraphPaths.get(0).getPathWeight());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(finalTime);
        stringBuilder.append("  ");
        stringBuilder.append(totalCost);
        return stringBuilder.toString();
    }

    @Override
    public void loadDataFromController(ObservableList<Element> observableList) {
        clearToLoadData();
        List<Vertex> vertexList = new ArrayList<>();

        for(Element element: observableList){
            Edge edge =new Edge(element.getI_j());
            setEdge(edge, element);
            addVertexToList(element, vertexList);
            String [] vertexPair = element.getI_j().split("-");
            Vertex vertex1 = new Vertex(vertexPair[0]);
            Vertex vertex2 = new Vertex(vertexPair[1]);
            graph.addVertex(vertex1);
            graph.addVertex(vertex2);
            graph.addEdge(vertex1, vertex2, edge);
            graph.setEdgeWeight(edge, edge.getKn());

            int vertexMin = Integer.parseInt(vertexPair[0]);
            int vertexMax = Integer.parseInt(vertexPair[1]);
            if(vertexMin < minVertex){
                minVertex = vertexMin;
            }
            if(vertexMax > maxVertex){
                maxVertex = vertexMax;
            }
        }
    }

    private static void setEdge(Edge edge, Element element){
        edge.setKgr(Double.parseDouble(element.getKgr()));
        edge.setKn(Double.parseDouble(element.getKn()));
        edge.setTgr(Double.parseDouble(element.getTgr()));
        edge.setTn(Double.parseDouble(element.getTn()));
        if(element.getS().equals("-")){
            edge.setS(0.0);
        }
        else {
            edge.setS(Double.parseDouble(element.getS()));
        }
    }

    private static void addVertexToList(Element element, List<Vertex> vertexList){
        String [] vertexPair = element.getI_j().split("-");
        Vertex vertex = new Vertex(vertexPair[0]);
        if(!vertexList.contains(vertex)){
            vertex.setT0(0.0);
            vertexList.add(vertex);
        }
        Vertex vertex1 = new Vertex(vertexPair[1]);
        if(!vertexList.contains(vertex1)){
            vertex1.setT0(Double.parseDouble(element.getTn()));
            vertexList.add(vertex1);
        }
        else{
            double vertex1T0 = vertex.getT0() + Double.parseDouble(element.getTn());
            if(vertexList.get(vertexList.indexOf(vertex1)).getT0() < vertex1T0){
                vertexList.get(vertexList.indexOf(vertex1)).setT0(vertex1T0);
            }
        }
    }

    private void getAllPathsInGraph(int minVertex, int maxVertex){

        AllDirectedPaths<Vertex, Edge> allDirectedPaths = new AllDirectedPaths<>(graph);
        List<GraphPath<Vertex, Edge>> graphPathList = allDirectedPaths.getAllPaths(new Vertex(""+minVertex),new Vertex(""+maxVertex),true,Integer.MAX_VALUE);
        for(GraphPath<Vertex, Edge> path: graphPathList){
            allGraphPaths.add(new Path(path.getEdgeList()));
        }
    }

    private void clearToLoadData(){
        minVertex =Integer.MAX_VALUE;
        maxVertex =-1;
        graph = new DirectedWeightedMultigraph<>(Edge.class);
        allGraphPaths.clear();
    }

    private boolean checkIfOptimizationCompleted(){
        for(Path path: allGraphPaths){
            if(path.isPathCompressed()){
                return true;
            }
        }
        return false;
    }

    private List<Path> findCriticalList(){
        List<Path> criticalList = new ArrayList<>();
        Path criticalPath = allGraphPaths.get(0);
        for(Path path: allGraphPaths){
            if(path.getPathWeight() == criticalPath.getPathWeight()){
                criticalList.add(path);
            }
        }
        return criticalList;
    }

    private Double calculateUnitPathCost(List<Path> pathList){
        double optimalValue, unitCost =0.0;
        if(pathList.size() ==1) {
            Path criticalPath = pathList.get(0);
            Path secondCriticalPath = allGraphPaths.get(1);
            double amountToOptimize = criticalPath.valueToOptimize(secondCriticalPath);
            Edge edge = criticalPath.findActivityToOptimize();
            if (edge == null)
                return null;
            double tn_minus_tgr = edge.getTn() - edge.getTgr();
            if (amountToOptimize > tn_minus_tgr) {
                optimalValue = tn_minus_tgr;
            } else {
                optimalValue = amountToOptimize;
            }
            criticalPath.getEdges().get(criticalPath.getEdges().indexOf(edge)).setTn(edge.getTn() - optimalValue);
            unitCost =  optimalValue*edge.getS();
            return unitCost;
        }
        else{
            double minValue = Double.MAX_VALUE;
            for(Path path: pathList){
                double completeValue = path.completeValueToOptimize();
                if(completeValue < minValue){
                    minValue = completeValue;
                }
            }

            for(Path path: pathList){
                unitCost = unitCost + path.completeOptimizePathCost(minValue);
            }
            return unitCost;
        }
    }
}
