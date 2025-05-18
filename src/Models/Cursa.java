package Models;

import java.io.FileWriter;
import java.io.IOException;

public class Cursa {
    private String id;
    private String startLocation;
    private String endLocation;
    private String startTime;
    private String endTime;
    private double distance;
    private String bikeId;
    private String userId;

    // Default constructor
    public Cursa() {
    }

    // All-args constructor
    public Cursa(String id, String startLocation, String endLocation, String startTime,
                 String endTime, double distance, String bikeId, String userId) {
        this.id = id;
        this.startLocation = startLocation;
        this.endLocation = endLocation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.distance = distance;
        this.bikeId = bikeId;
        this.userId = userId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStartLocation() {
        return startLocation;
    }

    public void setStartLocation(String startLocation) {
        this.startLocation = startLocation;
    }

    public String getEndLocation() {
        return endLocation;
    }

    public void setEndLocation(String endLocation) {
        this.endLocation = endLocation;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getBikeId() {
        return bikeId;
    }

    public void setBikeId(String bikeId) {
        this.bikeId = bikeId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void afisareDetalii() {
        System.out.println("ID: " + this.id);
        System.out.println("Locatie de start: " + this.startLocation);
        System.out.println("Locatie de final: " + this.endLocation);
        System.out.println("Timp de start: " + this.startTime);
        System.out.println("Timp de final: " + this.endTime);
        System.out.println("Distanta: " + this.distance + " km");
        System.out.println("ID Models.Bicicleta: " + this.bikeId);
        System.out.println("ID Utilizator: " + this.userId);
    }

    public void startCursa() {
        System.out.println("Models.Cursa a inceput de la " + startLocation + " la " + endLocation);
    }

    public void endCursa() {
        System.out.println("Models.Cursa s-a terminat de la " + startLocation + " la " + endLocation);
    }

    public void writeCursaToFile(Cursa cursa) {
        try (FileWriter writer = new FileWriter("cursa.txt", true)) {
            writer.write(cursa.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}