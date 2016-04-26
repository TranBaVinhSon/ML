package com.bkpirates;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by tbvson on 26/04/2016.
 */
public class Point {
    private double x = 0;
    private double y = 0;
    private int cluster_number = 0;
    //constructor
    public Point(double x, double y){
        this.setX(x);
        this.setY(y);
    }
    // get set
    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public int getCluster_number() {
        return cluster_number;
    }

    public void setCluster_number(int cluster_number) {
        this.cluster_number = cluster_number;
    }
    // calculates the distance between two points
    protected static double distance(Point p , Point centroid){
        return Math.sqrt(Math.pow(centroid.getX() - p.getX(),2) + Math.pow(centroid.getY() - p.getY(),2));

    }
    protected static Point createRandomPoint(int min, int max){
        Random r = new Random();
        double x = min + (max - min) * r.nextDouble();
        double y = min + (max - min) * r.nextDouble();
        return new Point(x,y);
    }
    protected static ArrayList<Point> createRandomPoints(int min,int max, int number){
        ArrayList<Point> points = new ArrayList<Point>(number);
        for(int i = 0; i < number;i++){
            points.add(createRandomPoint(min,max));
        }
        return points;
    }
    public String toString(){
        return "(" + x+ "," +y+")";
    }

}
