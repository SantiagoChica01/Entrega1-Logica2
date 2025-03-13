package listas_enlazadas;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

class Nodo {
    String nombre;
    int posicion;
    Nodo siguiente;

    public Nodo(String nombre, int posicion) {
        this.nombre = nombre;
        this.posicion = posicion;
        this.siguiente = null;
    }
}

class ListaCircular {
    private Nodo cabeza = null;
    private Nodo cola = null;
    private Random rand = new Random();

    public void insertar(String nombre) {
        int posicion = rand.nextInt(100); // Posición inicial aleatoria
        Nodo nuevo = new Nodo(nombre, posicion);
        if (cabeza == null) {
            cabeza = nuevo;
            cola = nuevo;
            cabeza.siguiente = cabeza;
        } else {
            cola.siguiente = nuevo;
            nuevo.siguiente = cabeza;
            cola = nuevo;
        }
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}

public class CarreraCircular extends JPanel {
    private ListaCircular lista;
    private Nodo actual;
    private Timer timer;

    public CarreraCircular() {
        lista = new ListaCircular();
        lista.insertar("Corredor 1");
        lista.insertar("Corredor 2");
        lista.insertar("Corredor 3");
        actual = lista.getCabeza();
        
        timer = new Timer(1000, e -> avanzarCarrera());
        timer.start();
    }

    private void avanzarCarrera() {
        actual.posicion += new Random().nextInt(10); // Avance aleatorio
        actual = actual.siguiente;
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Nodo temp = lista.getCabeza();
        int y = 50;
        do {
            g.drawString(temp.nombre + " - Posición: " + temp.posicion, 50, y);
            y += 20;
            temp = temp.siguiente;
        } while (temp != lista.getCabeza());
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Carrera Circular");
        CarreraCircular panel = new CarreraCircular();
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);

        JButton addButton = new JButton("Agregar Corredor");
        addButton.addActionListener(e -> {
            String nombre = "Corredor " + (new Random().nextInt(100));
            panel.lista.insertar(nombre);
        });
        frame.add(addButton, BorderLayout.SOUTH);
        
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

