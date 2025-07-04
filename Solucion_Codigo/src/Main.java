public class Main {
    public static void main(String[] args) {
        Personaje guerrero;
        Personaje arquero;
        Personaje mago;

        guerrero = new Guerrero(6, "Gigante", "Fuerza");
        arquero = new Arquero(3, "Arquero", "");
        mago = new Mago(4, "Mago", "");

        System.out.println(guerrero);
        System.out.println(mago);
        guerrero.atacar(mago);
    }
}

abstract class Personaje {
    public int vida, exp, batallasGanadas;
    public String nombre;

    public Personaje(int vida, String nombre) {
        this.vida = vida;
        this.nombre = nombre;
    }

    public abstract void atacar(Personaje personaje);

    public abstract int defensa();

    @Override
    public String toString() {
        return "Personaje{" +
                "vida=" + vida +
                ", exp=" + exp +
                ", batallasGanadas=" + batallasGanadas +
                '}';
    }
}

class Guerrero extends Personaje {
    public String habilidad;

    public Guerrero(int vida, String nombre, String habilidad) {
        super(vida, nombre);
        this.habilidad = habilidad;
    }

    public void atacar(Personaje personaje) {
        boolean gana = (int) (Math.random() * 3) == 1;

        if (gana) {
            this.batallasGanadas += 1;
            this.exp += 1;
            personaje.vida -= 1;
        } else {
            this.vida -= 1;
            personaje.exp += 1;
            personaje.batallasGanadas += 1;
        }

    }

    public int defensa() {
        return 0;
    }

    @Override
    public String toString() {
        return "Guerrero{" +
                "vida=" + vida +
                ", exp=" + exp +
                ", batallasGanadas=" + batallasGanadas +
                "} " + super.toString();
    }
}

class Mago extends Personaje {
    public String estrategia;

    public Mago(int vida, String nombre, String estrategia) {
        super(vida, nombre);
        this.estrategia = estrategia;
    }

    public void atacar(Personaje personaje) {
    }

    public int defensa() {
        return 0;
    }

    @Override
    public String toString() {
        return "Mago{" +
                "vida=" + vida +
                ", exp=" + exp +
                ", batallasGanadas=" + batallasGanadas +
                "} " + super.toString();
    }
}

class Arquero extends Personaje {
    public String atributos;

    public Arquero(int vida, String nombre, String atributos) {
        super(vida, nombre);
        this.atributos = atributos;
    }

    public void atacar(Personaje personaje) {
    }

    public int defensa() {
        return 0;
    }

    @Override
    public String toString() {
        return "Arquero{" +
                "vida=" + vida +
                ", exp=" + exp +
                ", batallasGanadas=" + batallasGanadas +
                "} " + super.toString();
    }
}
