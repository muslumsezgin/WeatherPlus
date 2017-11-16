package com.muslum.Weather.backend.model;

public class Weather {
    private String main;
    private String description;
    private String icon;


    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (!main.equals(weather.main)) return false;
        if (!description.equals(weather.description)) return false;
        return icon.equals(weather.icon);
    }

    @Override
    public int hashCode() {
        int result = main.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + icon.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
