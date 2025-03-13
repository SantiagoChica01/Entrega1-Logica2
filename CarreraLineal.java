
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

class ListaSimple {
    private Nodo cabeza = null;
    private Random rand = new Random();

    public void insertar(String nombre) {
        int posicion = 0;
        Nodo nuevo = new Nodo(nombre, posicion);
        if (cabeza == null) {
            cabeza = nuevo;
        } else {
            Nodo temp = cabeza;
            while (temp.siguiente != null) {
                temp = temp.siguiente;
            }
            temp.siguiente = nuevo;
        }
    }

    public Nodo getCabeza() {
        return cabeza;
    }
}

public class CarreraLineal extends JPanel {
    private ListaSimple lista;
    private Timer timer;

    public CarreraLineal() {
        lista = new ListaSimple();
        lista.insertar("Corredor 1");
        lista.insertar("Corredor 2");
        lista.insertar("Corredor 3");
        
        timer = new Timer(1000, e -> avanzarCarrera());
        timer.start();
    }

    private void avanzarCarrera() {
        Nodo temp = lista.getCabeza();
        while (temp != null) {
            temp.posicion += new Random().nextInt(10);
            if (temp.posicion >= 100) {
                JOptionPane.showMessageDialog(this, temp.nombre + " ha ganado la carrera!");
                timer.stop();
                return;
            }
            temp = temp.siguiente;
        }
        repaint();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Nodo temp = lista.getCabeza();
        int y = 50;
        while (temp != null) {
            g.drawString(temp.nombre + " - Posición: " + temp.posicion, 50, y);
            y += 20;
            temp = temp.siguiente;
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Carrera Lineal");
        CarreraLineal panel = new CarreraLineal();
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
