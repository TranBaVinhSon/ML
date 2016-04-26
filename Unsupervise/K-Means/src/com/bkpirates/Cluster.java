package com.bkpirates;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tbvson on 26/04/2016.
 */
public class Cluster {
    //one cluster has list of points and one centroid
    private ArrayList<Point> points;
    private Point centroid;
    private int id;
    //constructor
    public Cluster(int id){
        this.id = id;
        this.centroid = null;
        this.points = new ArrayList<>();

    }
    // get and set

    public ArrayList<Point> getPoints() {
        return points;
    }

    public void setPoints(ArrayList<Point> points) {
        this.points = points;
    }

    public Point getCentroid() {
        return centroid;
    }

    public void setCentroid(Point centroid) {
        this.centroid = centroid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    //
    public void clear(){
        points.clear();
    }
    public void addPoint(Point point){
        points.add(point);

    }
    public void plotCluster(){
        System.out.println("Cluster : " + this.id);
        System.out.println("Centroid : " + this.centroid);
        System.out.println("Points : ");
        for(Point point: points){
            System.out.println(point.toString());
        }
        System.out.println("------------------------------------------------");

    }

}
