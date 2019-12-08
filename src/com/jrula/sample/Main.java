package com.jrula.sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.AllDirectedPaths;
import org.jgrapht.alg.shortestpath.BellmanFordShortestPath;
import org.jgrapht.graph.DirectedWeightedMultigraph;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CPM method");
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
        testGraphLibrary();
    }

    public static void testGraphLibrary(){
        Graph<Integer, Integer> graph = new SimpleGraph<>(Integer.class);
        Graph<Integer, Integer> graph1 = new DirectedWeightedMultigraph<>(Integer.class);
        int a =1;
        int b =2;
        int c =3;
        int d =4;
        int e = 5;

        graph.addVertex(a);
        graph.addVertex(b);
        graph.addVertex(c);
        graph.addVertex(d);

        graph1.addVertex(a);
        graph1.addVertex(b);
        graph1.addVertex(c);
        graph1.addVertex(d);

        graph.addEdge(a,b, 1);
        graph.addEdge(a,c, 1);
        graph.addEdge(a,d, 1);

        graph1.addEdge(a,b,1);
        graph1.addEdge(b,c, 2);
        graph1.addEdge(a,d, 3);
        graph1.addEdge(d,c,4);
        graph1.setEdgeWeight(1, 5);
        graph1.setEdgeWeight(2,5);
        graph1.setEdgeWeight(3,2);
        graph1.setEdgeWeight(4,1);
        BellmanFordShortestPath<Integer, Integer> bellmanFordShortestPath = new BellmanFordShortestPath<>(graph1);

        System.out.println(bellmanFordShortestPath.getPath(a,c).getEdgeList().toString());

        AllDirectedPaths<Integer, Integer> allDirectedPaths = new AllDirectedPaths<>(graph1);

        System.out.println(allDirectedPaths.getAllPaths(a,c,true,100));
//
//        System.out.println(graph.edgeSet());
//        System.out.println(new BellmanFordShortestPath<>(graph));
    }


    public static void main(String[] args) {
        launch(args);
    }
}
