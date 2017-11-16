package com.muslum.Weather.backend.model;

import com.vaadin.spring.annotation.SpringComponent;

import java.util.List;

@SpringComponent
public class ResponseData {
    private City city;
    private String cod;
    private double message;
    private int cnt;
    private List<WeathersModel> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public double getMessage() {
        return message;
    }

    public void setMessage(double message) {
        this.message = message;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public List<WeathersModel> getList() {
        return list;
    }

    public void setList(List<WeathersModel> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResponseData that = (ResponseData) o;

        if (Double.compare(that.message, message) != 0) return false;
        if (cnt != that.cnt) return false;
        if (!city.equals(that.city)) return false;
        if (!cod.equals(that.cod)) return false;
        return list.equals(that.list);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = city.hashCode();
        result = 31 * result + cod.hashCode();
        temp = Double.doubleToLongBits(message);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + cnt;
        result = 31 * result + list.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ResponseData{" +
                "city=" + city +
                ", cod='" + cod + '\'' +
                ", message=" + message +
                ", cnt=" + cnt +
                ", list=" + list +
                '}';
    }
}