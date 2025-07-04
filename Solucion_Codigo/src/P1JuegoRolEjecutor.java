public class P1JuegoRolEjecutor {
    public static void main(String[] args) {
        Personaje guerrero = new Guerrero(10, "Thorin", "Hacha doble");
        Personaje arquero = new Arquero(8, "Legolas", "Precisión");
        Personaje mago = new Mago(7, "Gandalf", "Hechizo de fuego");

        System.out.println("=== Personajes Iniciales ===");
        System.out.println(guerrero);
        System.out.println(arquero);
        System.out.println(mago);

        combate(guerrero, mago);
        combate(mago, arquero);
        combate(arquero, guerrero);

        System.out.println("\n=== Estado Final ===");
        System.out.println(guerrero);
        System.out.println(arquero);
        System.out.println(mago);
    }

    public static void combate(Personaje a, Personaje b) {
        System.out.println("\n💥 Inicia combate entre " + a.nombre + " y " + b.nombre);
        for (int i = 1; i <= 3; i++) {
            System.out.println("\n🔁 Turno " + i);
            a.atacar(b);
            if (!b.estaVivo()) break;

            b.atacar(a);
            if (!a.estaVivo()) break;
        }

        System.out.println("\n⏸️ Entretiempo - Recuperación");
        a.defensa();
        b.defensa();

        System.out.println("🔄 Estado tras combate:");
        System.out.println(a);
        System.out.println(b);
    }
}

// CLASE BASE
abstract class Personaje {
    protected int vida;
    protected int experiencia;
    protected int batallasGanadas;
    protected String nombre;
    protected int armadura;

    public Personaje(int vida, String nombre, int armadura) {
        this.vida = vida;
        this.nombre = nombre;
        this.armadura = armadura;
        this.experiencia = 0;
        this.batallasGanadas = 0;
    }

    public void recibirDanio() {
        if (armadura > 0) {
            armadura--;
            System.out.println(nombre + " bloqueó el ataque con armadura. Armadura restante: " + armadura);
        } else {
            vida -= 2;
            System.out.println(nombre + " recibió 2 de daño. Vida restante: " + vida);
        }
    }

    public boolean estaVivo() {
        return vida > 0;
    }

    public void subirNivel() {
        if (experiencia >= 5) {
            experiencia = 0;
            vida += 2;
            System.out.println(nombre + " ha subido de nivel. Nueva vida: " + vida);
        }
    }

    public abstract void atacar(Personaje personaje);

    public abstract void defensa();

    @Override
    public String toString() {
        String estado = estaVivo() ? "(vivo)" : "(muerto)";
        return nombre + " - Vida: " + vida + ", Exp: " + experiencia + ", Victorias: " + batallasGanadas +
                ", Armadura: " + armadura + " " + estado;
    }
}

// GUERRERO
class Guerrero extends Personaje {
    private String habilidad;

    public Guerrero(int vida, String nombre, String habilidad) {
        super(vida, nombre, 2);
        this.habilidad = habilidad;
    }

    @Override
    public void atacar(Personaje personaje) {
        boolean gana = (int) (Math.random() * 2) == 0;
        if (gana) {
            batallasGanadas++;
            experiencia++;
            personaje.recibirDanio();
            System.out.println(nombre + " ganó el combate contra " + personaje.nombre);
        } else {
            recibirDanio();
            personaje.experiencia++;
            personaje.batallasGanadas++;
            System.out.println(nombre + " perdió el combate contra " + personaje.nombre);
        }
        subirNivel();
    }

    @Override
    public void defensa() {
        if (vida > 3) {
            armadura += 2;
            System.out.println(nombre + " recuperó +2 de armadura.");
        } else if (vida < 3) {
            vida += 2;
            System.out.println(nombre + " recuperó +2 de vida.");
        } else if (armadura < 3) {
            armadura++;
            System.out.println(nombre + " recuperó +1 de armadura.");
        } else {
            vida++;
            System.out.println(nombre + " recuperó +1 de vida.");
        }
    }

    @Override
    public String toString() {
        return "Guerrero [" + habilidad + "] -> " + super.toString();
    }
}

// MAGO
class Mago extends Personaje {
    private String estrategia;

    public Mago(int vida, String nombre, String estrategia) {
        super(vida, nombre, 1);
        this.estrategia = estrategia;
    }

    @Override
    public void atacar(Personaje personaje) {
        System.out.println(nombre + " lanza hechizo a " + personaje.nombre);
        personaje.recibirDanio();
        experiencia++;
        subirNivel();
    }

    @Override
    public void defensa() {
        if (vida > 3) {
            armadura += 2;
            System.out.println(nombre + " recuperó +2 de armadura.");
        } else if (vida < 3) {
            vida += 2;
            System.out.println(nombre + " recuperó +2 de vida.");
        } else if (armadura < 3) {
            armadura++;
            System.out.println(nombre + " recuperó +1 de armadura.");
        } else {
            vida++;
            System.out.println(nombre + " recuperó +1 de vida.");
        }
    }

    @Override
    public String toString() {
        return "Mago [" + estrategia + "] -> " + super.toString();
    }
}

// ARQUERO
class Arquero extends Personaje {
    private String atributos;

    public Arquero(int vida, String nombre, String atributos) {
        super(vida, nombre, 3);
        this.atributos = atributos;
    }

    @Override
    public void atacar(Personaje personaje) {
        System.out.println(nombre + " dispara flecha a " + personaje.nombre);
        personaje.recibirDanio();
        experiencia++;
        subirNivel();
    }

    @Override
    public void defensa() {
        if (vida > 3) {
            armadura += 2;
            System.out.println(nombre + " recuperó +2 de armadura.");
        } else if (vida < 3) {
            vida += 2;
            System.out.println(nombre + " recuperó +2 de vida.");
        } else if (armadura < 3) {
            armadura++;
            System.out.println(nombre + " recuperó +1 de armadura.");
        } else {
            vida++;
            System.out.println(nombre + " recuperó +1 de vida.");
        }
    }

    @Override
    public String toString() {
        return "Arquero [" + atributos + "] -> " + super.toString();
    }
}
