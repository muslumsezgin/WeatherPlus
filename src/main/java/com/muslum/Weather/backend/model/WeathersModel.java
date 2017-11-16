package com.muslum.Weather.backend.model;

import java.util.List;

public class WeathersModel {

    private int dt;
    private Temperature temp;
    private double pressure;
    private int humidity;
    private List<Weather> weather;
    private double speed;
    private int deg;
    private int clouds;
    private double rain;

    public int getDt() {
        return dt;
    }

    public void setDt(int dt) {
        this.dt = dt;
    }

    public Temperature getTemp() {
        return temp;
    }

    public void setTemp(Temperature temp) {
        this.temp = temp;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public int getDeg() {
        return deg;
    }

    public void setDeg(int deg) {
        this.deg = deg;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getRain() {
        return rain;
    }

    public void setRain(double rain) {
        this.rain = rain;
    }

    @Override
    public String toString() {
        return "List{" +
                "dt=" + dt +
                ", temp=" + temp +
                ", pressure=" + pressure +
                ", humidity=" + humidity +
                ", weather=" + weather +
                ", speed=" + speed +
                ", deg=" + deg +
                ", clouds=" + clouds +
                ", rain=" + rain +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeathersModel that = (WeathersModel) o;

        if (dt != that.dt) return false;
        if (Double.compare(that.pressure, pressure) != 0) return false;
        if (humidity != that.humidity) return false;
        if (Double.compare(that.speed, speed) != 0) return false;
        if (deg != that.deg) return false;
        if (clouds != that.clouds) return false;
        if (Double.compare(that.rain, rain) != 0) return false;
        if (!temp.equals(that.temp)) return false;
        return weather.equals(that.weather);
    }

    @Override
    public int hashCode() {
        int result;
        long temp1;
        result = dt;
        result = 31 * result + temp.hashCode();
        temp1 = Double.doubleToLongBits(pressure);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + humidity;
        result = 31 * result + weather.hashCode();
        temp1 = Double.doubleToLongBits(speed);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        result = 31 * result + deg;
        result = 31 * result + clouds;
        temp1 = Double.doubleToLongBits(rain);
        result = 31 * result + (int) (temp1 ^ (temp1 >>> 32));
        return result;
    }
}
