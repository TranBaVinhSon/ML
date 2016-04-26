package com.bkpirates;

import sun.rmi.runtime.Log;

import java.util.ArrayList;
import java.util.List;

public class KMeans {
    private static final int NUM_CLUSTER = 3;
    private static final int NUM_POINTS = 15;

    private static final int MIN_COORDINATE = 0;
    private static final int MAX_COORDINATE = 10;

    private ArrayList<Point> points;
    private ArrayList<Cluster> clusters;

    public KMeans(){
        this.points = new ArrayList<Point>();
        this.clusters = new ArrayList<Cluster>();
    }

    public static void main(String[] args) {
	    KMeans kMeans = new KMeans();
        kMeans.init();
        kMeans.caculate();
    }
    //Initializes the process
    public void init(){
        points = Point.createRandomPoints(MIN_COORDINATE, MAX_COORDINATE, NUM_POINTS);
        // create clusters
        // set random centroids
        for(int i = 0; i < NUM_CLUSTER ; i++){
            Cluster cluster = new Cluster(i);
            Point centroid = Point.createRandomPoint(MIN_COORDINATE, MAX_COORDINATE);
            cluster.setCentroid(centroid);
            clusters.add(cluster);
        }
        // Print Initial state
        plotClusters();
    }
    private void plotClusters(){
        for(Cluster cluster : clusters){
            cluster.plotCluster();
        }
    }
    // The process to calculate the K-Means, with iterating method
    public void caculate(){
        boolean finish = false;
        int iteration = 0;

        while (!finish){
            clearClusters();
            ArrayList<Point> lastCentroids = getCentroids();
            // Assign points to the closer cluster
            assignCluster();
            // Calculate new centroids
            calculateCentroids();

            iteration++;

            ArrayList<Point> currentCentroids = getCentroids();
            //Calculates total distance between new and old Centroids
            double distance = 0;
            for(int i = 0; i< lastCentroids.size();i++){
                distance += Point.distance(lastCentroids.get(i), currentCentroids.get(i));
            }
            System.out.println("####################");
            System.out.println("Iteration : " + iteration);
            System.out.println("Centroid distances : " + distance);
            plotClusters();
            //
            if(distance == 0) finish = true;
        }
    }
    private void clearClusters(){
        for (Cluster cluster : clusters){
            cluster.clear();
        }
    }
    private ArrayList<Point> getCentroids(){
        ArrayList<Point> centroids = new ArrayList<Point>(NUM_CLUSTER);
        for(int i = 0 ;i < clusters.size();i ++){
            Point aux = clusters.get(i).getCentroid();
            Point point = new Point(aux.getX(), aux.getY());
            centroids.add(point);
        }
        return centroids;
    }
    //assign pointer to cluster
    private void assignCluster(){
        double max = Double.MAX_VALUE;
        double min = max;
        int positionCluster = 0;
        double distance = 0.0;

        for (Point point : points){
            min = max;
//            System.out.print("size of cluster : " + clusters.size());
            for(int i = 0; i < NUM_CLUSTER;i ++){
                    Cluster c = (Cluster) clusters.get(i);
                    distance = Point.distance(point, c.getCentroid());
                    if(distance < min){
                        min = distance;
                        positionCluster = i;
                    }
            }
            point.setCluster_number(positionCluster);
            clusters.get(positionCluster).addPoint(point);
        }

    }
    // calculate new centroids
    private void calculateCentroids(){
        for (Cluster cluster : clusters){
            double sumX = 0;
            double sumY = 0;
            ArrayList<Point> listPointsOfCluster = cluster.getPoints();
            int numberPoints = listPointsOfCluster.size();
            for(Point point : listPointsOfCluster){
                //calculate sum of all X,Y in one cluster
                sumX += point.getX();
                sumY += point.getY();
            }
            Point centroid = cluster.getCentroid();
            if (numberPoints > 0 ){
                double newX = sumX / numberPoints;
                double newY = sumY / numberPoints;
                centroid.setX(newX);
                centroid.setY(newY);
            }
        }
    }

}
