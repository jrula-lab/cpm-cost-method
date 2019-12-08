package com.jrula.sample.model;

import java.util.List;
import java.util.stream.Collectors;

public class Path implements Comparable<Path> {

    private List<Edge> edges;
    private double pathWeight;

    public Path(List<Edge> edges){
        this.edges = edges;
        pathWeight = 0.0;
    }

    public boolean isPathCompressed() {
        for(Edge edge: edges){
            if(edge.getTgr() != edge.getTn()){
                return false;
            }
        }
        return true;
    }

    public Edge findActivityToOptimize(){
        double minS =Double.MAX_EXPONENT;
        Edge edge = null;
        for(Edge edge1: edges){
            if(edge1.getS() >0 && edge1.getS() < minS){
                if(edge1.getTn() > edge1.getTgr()) {
                    minS = edge1.getS();
                    edge = edge1;
                }
            }
        }
        return edge;
    }


    public void calculatePathWeight(){
        this.pathWeight = 0.0;
        for(Edge edge: edges){
            this.pathWeight = this.pathWeight + edge.getTn();
        }
    }

    public double valueToOptimize(Path path){
        return this.pathWeight - path.pathWeight;
    }

    @Override
    public int compareTo(Path path) {
        return (int)(path.pathWeight - this.pathWeight);
    }

    public String getCriticalPath(){
        StringBuilder stringBuilder = new StringBuilder();
        String lastVertex ="";
        for(Edge edge : edges){
            stringBuilder.append(edge.getId().split("-")[0]+"-");
            lastVertex = edge.getId().split("-")[1];
        }
        stringBuilder.append(lastVertex);
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return "Path{" +
                "edges=" + edges +
                ", pathWeight=" + pathWeight +
                '}';
    }

    public List<Edge> getEdges() {
        return edges;
    }

    public void setEdges(List<Edge> edges) {
        this.edges = edges;
    }

    public double getPathWeight() {
        return pathWeight;
    }

    public void setPathWeight(double pathWeight) {
        this.pathWeight = pathWeight;
    }

    public List<Edge> sortBySValue(){
        return edges.stream().sorted().collect(Collectors.toList());
    }

    public double completeValueToOptimize(){
        double completeValueToOptimize = 0.0;
        for(Edge edge: edges){
            completeValueToOptimize = completeValueToOptimize +(edge.getTn() -edge.getTgr());
        }
        return completeValueToOptimize;
    }

    public double completeOptimizePathCost(double value){
        List<Edge> edges = sortBySValue();
        double unitCost = 0.0;
        for(Edge edge: edges){

            double tn_minus_tgr = edge.getTn() - edge.getTgr();
            if(tn_minus_tgr > 0) {
                if (value <= 0) {
                    break;
                }
                if(value -tn_minus_tgr >0) {
                    unitCost = unitCost + tn_minus_tgr * edge.getS();
                    value = value - tn_minus_tgr;
                    edge.setTn(edge.getTgr());
                }
                else{
                    unitCost = unitCost +value*edge.getS();
                    edge.setTn(edge.getTn() - value);
                    value = 0;
                }
            }
        }
        for(Edge edge : edges){
            this.edges.get(this.edges.indexOf(edge)).setTn(edge.getTn());
        }
        return unitCost;
    }
}
