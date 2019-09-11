/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmeans;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author erick
 */
public class Lienzo extends JPanel implements Runnable {

    private int muestras;
    private int nClases;
    private int x1;
    private int y1;

    private ArrayList<Muestra> puntos;
    private ArrayList<Centroide> centroides;

    public Lienzo() {
        puntos = new ArrayList();
        centroides = new ArrayList();
    }

    public Lienzo(int muestras) {
        this.muestras = muestras;
        puntos = new ArrayList();
        centroides = new ArrayList();
    }

    public int getMuestras() {
        return muestras;
    }

    public void setMuestras(int muestras) {
        this.muestras = muestras;
    }

    public int getnClases() {
        return nClases;
    }

    public void setnClases(int nClases) {
        this.nClases = nClases;
    }

    public int getX1() {
        return x1;
    }

    public void setX1(int x) {
        this.x1 = x;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y) {
        this.y1 = y;
    }
    
    

    public void tomaCentroide(Centroide x) {
        centroides.add(x);
    }

    public ArrayList<Integer> seleccion(ArrayList<Double> ob) {
        ArrayList<Integer> distancia = new ArrayList();
        for (int i = 0; i < ob.size(); i++) {
            distancia.add(i);
        }
        int menor;
        int aux;
        for (int i = 0; i < ob.size() - 1; i++) {
            menor = i;
            for (int j = i + 1; j < ob.size(); j++) {
                if (ob.get(distancia.get(j)) < ob.get(distancia.get(menor))) {
                    menor = j;
                }
            }
            aux = distancia.get(i);
            distancia.set(i, distancia.get(menor));
            distancia.set(menor, aux);
        }
        return distancia;
    }

    public void cercano() {
        double valor;
        ArrayList<Double> temp = new ArrayList();
        ArrayList<Integer> indextemp = new ArrayList();
        for (int i = 0; i < centroides.size(); i++) {
            Centroide x = new Centroide();
            x = centroides.get(i);
            x.resetArray();
        }

        for (int i = 0; i < puntos.size(); i++) {
            Muestra x = new Muestra();
            x = puntos.get(i);
            Centroide vmin = new Centroide();
            for (int j = 0; j < centroides.size(); j++) {
                Centroide aux = new Centroide();
                aux = centroides.get(j);
                valor = aux.distancia(x);
                temp.add(valor);
            }
            indextemp = seleccion(temp);
            x.setClase(indextemp.get(0) + 1);
            vmin = centroides.get(indextemp.get(0));
            vmin.insertCercano(x);
            temp.clear();
            indextemp.clear();
        }
        for (int i = 0; i < centroides.size(); i++) {
            Centroide x = new Centroide();
            x = centroides.get(i);
            x.reCalcular();
        }
    }

    public boolean condicion() {
        boolean res = false;
        for (int i = 0; i < centroides.size(); i++) {
            Centroide x = new Centroide();
            x = centroides.get(i);
            if(x.getAntX()==0 && x.getAntY()==0){
                res = true;
            }else{
                res = false;
            }

        }
        return res;
    }

    public void iniciar() throws InterruptedException {

        do {
            this.cercano();
            this.repaint();
            Thread.sleep(500);
        } while (!condicion());

    }

    public void init() {
        for (int i = 0; i < this.getMuestras(); i++) {
            Muestra x = new Muestra();
            x.recogeR(this.getX1(), this.getY1());
            x.llena_aleatoriamente();
            puntos.add(x);
        }
    }

    public Color elige(int i) {
        Color opcion = Color.WHITE;
            switch (i) {
                case 1:
                    opcion = Color.RED;
                    break;
                case 2:
                    opcion = Color.BLUE;
                    break;
                case 3:
                    opcion = Color.CYAN;
                    break;
                case 4:
                    opcion = Color.GREEN;
                    break;
                case 5:
                    opcion = Color.MAGENTA;
                    break;
                case 6:
                    opcion = Color.ORANGE;
                    break;
                case 7:
                    opcion = Color.PINK;
                    break;
                case 8:
                    opcion = Color.YELLOW;
                    break;
                case 9:
                    opcion = Color.LIGHT_GRAY;
                    break;
                case 10:
                    opcion = Color.DARK_GRAY;
                    break;
        }
        return opcion;
    }

    public void limpiaCentroide() {
        centroides.clear();
    }

    public void limpiaMuestras() {
        puntos.clear();
    }

    public void paintComponent(Graphics g) {
        super.paintComponents(g);
         g.setColor(Color.BLACK);
        g.fillRect(0, 0, this.getX1(), this.getY1());
        g.setColor(Color.white);
        g.drawLine(0, this.getY1()/2, this.getX1(), this.getY1()/2);
        g.drawLine(this.getX1()/2, 0, this.getX1()/2, this.getY1());
        g.drawLine(this.getX1()-1, 0, this.getX1()-1, this.getY1()-1);
        g.drawLine(0, this.getY1()-1, this.getX1()-1, this.getY1()-1);

        for (int i = 0; i < puntos.size(); i++) {
            Muestra x = new Muestra();
            x = puntos.get(i);
            g.fillOval(x.getCoordX(), x.getCoordY(), 5, 5);
        }
        for (int i = 0; i < centroides.size(); i++) {
            Centroide x = new Centroide();
            x = centroides.get(i);
            g.setColor(this.elige(x.getcClase()));
            g.fillOval(x.getcCoordX(), x.getcCoordY(), 15, 15);
        }

        for (int i = 0; i < centroides.size(); i++) {
            Centroide x = new Centroide();
            x = centroides.get(i);
            ArrayList<Muestra> y = new ArrayList();
            y = x.getCercanos();
            for (int j = 0; j < y.size(); j++) {
                Muestra z = y.get(j);
                g.setColor(this.elige(z.getClase()));
                g.fillOval(z.getCoordX(), z.getCoordY(), 5, 5);
            }
            

        }

    }

    public void run() {
        try {
            this.iniciar();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lienzo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
