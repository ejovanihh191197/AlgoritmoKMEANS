/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.util.ArrayList;

/**
 *
 * @author erick
 */
public class Centroide {

    private int cCoordX;
    private int cCoordY;
    private int cClase;
    private ArrayList<Muestra> cercanos = new ArrayList();
    private int tempx;
    private int tempy;

    public int getcCoordX() {
        return cCoordX;
    }

    public void setcCoordX(int cCoordX) {
        this.cCoordX = cCoordX;
    }

    public int getcCoordY() {
        return cCoordY;
    }

    public void setcCoordY(int cCoordY) {
        this.cCoordY = cCoordY;
    }

    public int getcClase() {
        return cClase;
    }

    public void setcClase(int cClase) {
        this.cClase = cClase;
    }

    public int getTempx() {
        return tempx;
    }

    public void setTempx(int tempx) {
        this.tempx = tempx;
    }

    public int getTempy() {
        return tempy;
    }

    public void setTempy(int tempy) {
        this.tempy = tempy;
    }

    public ArrayList<Muestra> getCercanos() {
        return cercanos;
    }

    public void setCercanos(ArrayList<Muestra> cercanos) {
        this.cercanos = cercanos;
    }

    public double distancia(Muestra x) {
        int x1 = this.getcCoordX();
        int y1 = this.getcCoordY();
        int x2 = x.getCoordX();
        int y2 = x.getCoordY();
        double resultado;
        resultado = Math.sqrt((Math.pow((x1 - x2), 2)) + (Math.pow((y1 - y2), 2)));
        return resultado;
    }

    public void insertCercano(Muestra x) {
        this.cercanos.add(x);
    }

    public void imprime() {
        System.out.println(cercanos);
    }

    public void resetArray() {
        cercanos.clear();
    }

    public int getAntX() {
        return this.cCoordX - this.tempx;
    }

    public int getAntY() {
        return this.cCoordY - this.tempy;
    }

    public void reCalcular() {
        int x = 0;
        int y = 0;
        this.tempx = this.cCoordX;
        this.tempy = this.cCoordY;
        for (int i = 0; i < cercanos.size(); i++) {
            Muestra x1 = new Muestra();
            x1 = cercanos.get(i);
            x = x + x1.getCoordX();
            y = y + x1.getCoordY();
        }
        x = x / cercanos.size();
        y = y / cercanos.size();
        this.cCoordX = x;
        this.cCoordY = y;
    }

}
