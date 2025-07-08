import java.util.*;

abstract class Jugador {
    protected String nombre;
    protected int dorsal;
    protected String rut;
    protected int goles;

    public Jugador(String nombre, int dorsal, String rut, int goles) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.rut = rut;
        this.goles = goles;
    }

    public abstract double calcularValoracion();

    @Override
    public String toString() {
        return String.format("Nombre: %s | Dorsal: %d | RUT: %s | Goles: %d | Valoración: %.2f",
                nombre, dorsal, rut, goles, calcularValoracion());
    }
}

class Atacante extends Jugador {
    private int recuperaciones;

    public Atacante(String nombre, int dorsal, String rut, int goles, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.recuperaciones = recuperaciones;
    }

    @Override
    public double calcularValoracion() {
        // Ejemplo: cada gol vale 4 puntos, cada recuperacion 1 punto
        return goles * 4 + recuperaciones * 1.0;
    }

    @Override
    public String toString() {
        return "Atacante - " + super.toString() + String.format(" | Recuperaciones: %d", recuperaciones);
    }
}

class Defensor extends Jugador {
    private int recuperaciones;

    public Defensor(String nombre, int dorsal, String rut, int goles, int recuperaciones) {
        super(nombre, dorsal, rut, goles);
        this.recuperaciones = recuperaciones;
    }

    @Override
    public double calcularValoracion() {
        // Ejemplo: cada gol vale 2 puntos, cada recuperacion 2 puntos
        return goles * 2 + recuperaciones * 2.0;
    }

    @Override
    public String toString() {
        return "Defensor - " + super.toString() + String.format(" | Recuperaciones: %d", recuperaciones);
    }
}

class Portero extends Jugador {
    private int atajadas;

    public Portero(String nombre, int dorsal, String rut, int goles, int atajadas) {
        super(nombre, dorsal, rut, goles);
        this.atajadas = atajadas;
    }

    @Override
    public double calcularValoracion() {
        // Ejemplo: cada gol vale 1 punto, cada atajada 3 puntos
        return goles * 1 + atajadas * 3.0;
    }

    @Override
    public String toString() {
        return "Portero - " + super.toString() + String.format(" | Atajadas: %d", atajadas);
    }
}

public class P3EstadisticaEjecutor {
    public static void main(String[] args) {
        List<Jugador> jugadores = new ArrayList<>();
        jugadores.add(new Atacante("Lionel Messi", 10, "12.345.678-9", 2, 5));
        jugadores.add(new Defensor("Sergio Ramos", 4, "11.222.333-4", 1, 8));
        jugadores.add(new Portero("Manuel Neuer", 1, "22.333.444-5", 0, 7));

        System.out.println("=== Estadísticas de Jugadores ===");
        for (Jugador j : jugadores) {
            System.out.println(j);
        }
    }
}