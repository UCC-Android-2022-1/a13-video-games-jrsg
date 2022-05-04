package com.example.videogames;

public class VideoGame {
    private int id;
    private String nombre, estado;
    private boolean xbox, play_station, nintendo, pc;
    private double precio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isXbox() {
        return xbox;
    }

    public void setXbox(boolean xbox) {
        this.xbox = xbox;
    }

    public boolean isPlay_station() {
        return play_station;
    }

    public void setPlay_station(boolean play_station) {
        this.play_station = play_station;
    }

    public boolean isNintendo() {
        return nintendo;
    }

    public void setNintendo(boolean nintendo) {
        this.nintendo = nintendo;
    }

    public boolean isPc() {
        return pc;
    }

    public void setPc(boolean pc) {
        this.pc = pc;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
