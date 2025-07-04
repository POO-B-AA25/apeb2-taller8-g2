import java.util.Scanner;

public class P1JuegoRolEjecutor {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        Personaje jugador = null;

        System.out.println("🎮 Bienvenido al juego de roles");
        System.out.println("Elige tu personaje:");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Arquero");
        System.out.print(">> ");

        int opc = sc.nextInt();
        sc.nextLine(); // limpiar buffer

        System.out.print("Ingresa el nombre de tu personaje: ");
        String nombre = sc.nextLine();

        switch (opc) {
            case 1 -> jugador = new Guerrero(10, nombre, "Espada legendaria");
            case 2 -> jugador = new Mago(7, nombre, "Hechizo ancestral");
            case 3 -> jugador = new Arquero(8, nombre, "Tiro certero");
            default -> System.out.println("Opción inválida");
        }

        if (jugador == null) return;

        System.out.println("\n🧍 Tu personaje: " + jugador);

        // 2 enemigos predeterminados
        Personaje enemigo1 = new Guerrero(9, "Thorin", "Hacha de guerra");
        Personaje enemigo2 = new Mago(6, "Saruman", "Magia negra");

        combate(jugador, enemigo1);
        if (jugador.estaVivo()) combate(jugador, enemigo2);

        System.out.println("\n🏁 Juego finalizado");
        System.out.println("👉 Resultado final:");
        System.out.println(jugador);
        System.out.println(enemigo1);
        System.out.println(enemigo2);
    }

    public static void combate(Personaje a, Personaje b) throws Exception {
        System.out.println("\n⚔️ Inicia combate entre " + a.nombre + " y " + b.nombre);
        for (int i = 1; i <= 2; i++) {
            if (!a.estaVivo() || !b.estaVivo()) break;

            System.out.println("\n🔁 Turno " + i);
            a.atacar(b);
            Thread.sleep(1500); // Espera 1.5s

            if (b.estaVivo()) {
                b.atacar(a);
                Thread.sleep(1500);
            }
        }

        System.out.println("\n⏸️ Entretiempo - Recuperación");
        if (a.estaVivo()) a.defensa();
        if (b.estaVivo()) b.defensa();
        Thread.sleep(1000); // Espera 1s

        System.out.println("\n📊 Estado tras combate:");
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
            System.out.println(nombre + " bloqueó el ataque. Armadura restante: " + armadura);
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
            System.out.println(nombre + " subió de nivel. Nueva vida: " + vida);
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
