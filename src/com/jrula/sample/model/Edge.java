package com.jrula.sample.model;


import java.util.Objects;

public class Edge implements Comparable<Edge> {

    private double tn = 0.0;
    private double tgr = 0.0;
    private double Kn = 0.0;
    private double Kgr = 0.0;
    private double S = 0.0;
    private String id;

    public Edge(String id){
        this.id = id;
    }

    public double getTn() {
        return tn;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public double getTgr() {
        return tgr;
    }

    public void setTgr(double tgr) {
        this.tgr = tgr;
    }

    public double getKn() {
        return Kn;
    }

    public void setKn(double kn) {
        Kn = kn;
    }

    public double getKgr() {
        return Kgr;
    }

    public void setKgr(double kgr) {
        Kgr = kgr;
    }

    public double getS() {
        return S;
    }

    public void setS(double s) {
        S = s;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge edge = (Edge) o;
        return id.equals(edge.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Edge{" +
                "id='" + id + '\'' +
                '}';
    }

    @Override
    public int compareTo(Edge edge) {
        return (int)(this.S - edge.S);
    }
}
