import java.util.*;

abstract class Emprendimiento {
    protected String nombre;
    protected String mision;
    protected List<String> productos = new ArrayList<>();
    protected List<Mentor> mentores = new ArrayList<>();

    public Emprendimiento(String nombre, String mision) {
        this.nombre = nombre;
        this.mision = mision;
    }

    public void registrarProducto(String nombre) {
        productos.add(nombre);
    }

    public void agregarMentor(Mentor mentor) {
        mentores.add(mentor);
    }

    public abstract String participarFeria(String tipo);

    public abstract void evolucionar();

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Emprendimiento: ").append(nombre).append("\n");
        sb.append("Misión: ").append(mision).append("\n");
        sb.append("Productos/Servicios: ").append(productos).append("\n");
        if (!mentores.isEmpty()) {
            sb.append("Mentores:\n");
            for (Mentor m : mentores) {
                sb.append("  - ").append(m).append("\n");
            }
        }
        return sb.toString();
    }
}

class EmprendimientoTecnologico extends Emprendimiento {
    private boolean tieneAppMovil = false;

    public EmprendimientoTecnologico(String nombre, String mision) {
        super(nombre, mision);
    }

    @Override
    public String participarFeria(String tipo) {
        return nombre + " participa en la feria tecnológica: " + tipo +
               (tieneAppMovil ? " (presentando su app móvil)" : "");
    }

    @Override
    public void evolucionar() {
        if (!tieneAppMovil) {
            tieneAppMovil = true;
            registrarProducto("App móvil oficial");
        }
    }
}

class EmprendimientoArtesanal extends Emprendimiento {
    private int sucursales = 1;

    public EmprendimientoArtesanal(String nombre, String mision) {
        super(nombre, mision);
    }

    @Override
    public String participarFeria(String tipo) {
        return nombre + " expone artesanías en la feria: " + tipo +
               " (sucursales: " + sucursales + ")";
    }

    @Override
    public void evolucionar() {
        sucursales++;
    }
}

class EmprendimientoAgropecuario extends Emprendimiento {
    private boolean exporta = false;

    public EmprendimientoAgropecuario(String nombre, String mision) {
        super(nombre, mision);
    }

    @Override
    public String participarFeria(String tipo) {
        return nombre + " presenta productos agrícolas en la feria: " + tipo +
               (exporta ? " (ahora exporta al extranjero)" : "");
    }

    @Override
    public void evolucionar() {
        exporta = true;
    }
}

class EmprendimientoGastronomico extends Emprendimiento {
    private List<String> nuevasRecetas = new ArrayList<>();

    public EmprendimientoGastronomico(String nombre, String mision) {
        super(nombre, mision);
    }

    @Override
    public String participarFeria(String tipo) {
        return nombre + " ofrece degustaciones en la feria: " + tipo +
               (nuevasRecetas.isEmpty() ? "" : " (nuevas recetas: " + nuevasRecetas + ")");
    }

    @Override
    public void evolucionar() {
        String receta = "Receta especial " + (nuevasRecetas.size() + 1);
        nuevasRecetas.add(receta);
        registrarProducto(receta);
    }
}

class Mentor {
    private String nombre;
    private String especialidad;

    public Mentor(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    @Override
    public String toString() {
        return nombre + " (" + especialidad + ")";
    }
}

public class P5EmprendimientoEjecutor {
    public static void main(String[] args) {
        // Crear mentores
        Mentor m1 = new Mentor("Ana Torres", "Marketing");
        Mentor m2 = new Mentor("Carlos Ruiz", "Desarrollo de Software");
        Mentor m3 = new Mentor("Lucía Pérez", "Contabilidad");

        // Crear emprendimientos
        Emprendimiento e1 = new EmprendimientoTecnologico("LojaTech", "Innovar con tecnología para la ciudad");
        e1.registrarProducto("Plataforma web");
        e1.agregarMentor(m2);

        Emprendimiento e2 = new EmprendimientoArtesanal("Manos de Loja", "Difundir el arte local");
        e2.registrarProducto("Cerámica decorativa");
        e2.registrarProducto("Tejidos tradicionales");
        e2.agregarMentor(m1);

        Emprendimiento e3 = new EmprendimientoAgropecuario("AgroLoja", "Producir alimentos saludables");
        e3.registrarProducto("Café orgánico");
        e3.registrarProducto("Frutas deshidratadas");

        Emprendimiento e4 = new EmprendimientoGastronomico("Sabores de Loja", "Promover la gastronomía local");
        e4.registrarProducto("Humitas");
        e4.registrarProducto("Tamales");
        e4.agregarMentor(m3);

        // Simular evolución
        e1.evolucionar();
        e2.evolucionar();
        e3.evolucionar();
        e4.evolucionar();

        // Participación en ferias
        System.out.println(e1.participarFeria("Feria de Innovación"));
        System.out.println(e2.participarFeria("Feria Artesanal"));
        System.out.println(e3.participarFeria("Feria Agropecuaria"));
        System.out.println(e4.participarFeria("Feria Gastronómica"));

        // Mostrar información completa
        System.out.println("\n=== Información de Emprendimientos ===");
        System.out.println(e1);
        System.out.println(e2);
        System.out.println(e3);
        System.out.println(e4);
    }
}