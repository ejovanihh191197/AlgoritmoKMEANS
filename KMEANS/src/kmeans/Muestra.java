/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

/**
 *
 * @author erick
 */
public class Muestra {

    private int coordX;
    private int coordY;
    private int clase;
    private int x=500;
    private int y=500;

    public Muestra() {
    }

    public Muestra(int coordX, int coordY) {
        this.coordX = coordX;
        this.coordY = coordY;
    }

    public Muestra(int coordX, int coordY, int clase) {
        this.coordX = coordX;
        this.coordY = coordY;
        this.clase = clase;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    public int getClase() {
        return clase;
    }

    public void setClase(int clase) {
        this.clase = clase;
    }
    
    public void recogeR(int x, int y){
        this.x = x;
        this.y = y;
    }

    public void llena_aleatoriamente() {
        this.setClase(0);
        this.setCoordX((int) (Math.random() * (0 - x + 1) + x));
        this.setCoordY((int) (Math.random() * (0 - y + 1) + y));
    }
    
    

    public String toString() {
        return "Muestra: " + "coordX=" + this.getCoordX() + ", coordY=" + this.getCoordY() + ", clase=" + this.getClase();
    }

}
