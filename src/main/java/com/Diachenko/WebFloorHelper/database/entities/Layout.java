package com.Diachenko.WebFloorHelper.database.entities;

import java.util.Objects;

public class Layout {
    private String name;
    private String coordinates;
    private int id;


    public Layout( String name, String coordinates) {
        this.name = name;
        this.coordinates = coordinates;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Layout)) return false;
        Layout layout = (Layout) o;
        return name.equals(layout.name) &&
                coordinates.equals(layout.coordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, coordinates);
    }

    @Override
    public String toString() {
        return "Layout{" +
                "name='" + name + '\'' +
                ", coordinates='" + coordinates + '\'' +
                '}';
    }
}
