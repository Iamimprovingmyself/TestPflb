package ru.framework.db.pflb.model;

public class Car {
    public Car(String engineType, String mark, String model, Double price) {
        this.engineType = engineType;
        this.mark = mark;
        this.model = model;
        this.price = price;
    }

    private String engineType;
    private String mark;
    private String model;
    private Double price;
    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }


}