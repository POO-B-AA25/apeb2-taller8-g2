import java.util.*;

abstract class Nacion {
    protected String nombre;
    protected int poblacion;
    protected double recursos;
    protected int poderMilitar; // 1-100
    protected boolean enConflicto;
    protected List<Nacion> aliados = new ArrayList<>();

    public Nacion(String nombre, int poblacion, double recursos, int poderMilitar) {
        this.nombre = nombre;
        this.poblacion = poblacion;
        this.recursos = recursos;
        this.poderMilitar = Math.max(1, Math.min(100, poderMilitar));
        this.enConflicto = false;
    }

    public void agregarAliado(Nacion aliado) {
        if (!aliados.contains(aliado) && aliado != this) {
            aliados.add(aliado);
        }
    }

    public abstract int calcularImpacto();

    public void setEnConflicto(boolean estado) {
        this.enConflicto = estado;
    }

    public boolean isEnConflicto() {
        return enConflicto;
    }

    public int getPoderMilitar() {
        return poderMilitar;
    }

    public void setPoderMilitar(int poderMilitar) {
        this.poderMilitar = Math.max(1, Math.min(100, poderMilitar));
    }

    public void reducirPoblacion(double porcentaje) {
        poblacion -= (int)(poblacion * porcentaje / 100.0);
        if (poblacion < 0) poblacion = 0;
    }

    public void reducirRecursos(double porcentaje) {
        recursos -= recursos * porcentaje / 100.0;
        if (recursos < 0) recursos = 0;
    }

    @Override
    public String toString() {
        return String.format(
            "Nación: %s\nPoblación: %d\nRecursos: %.2f\nPoder Militar: %d\nEn Conflicto: %s\nAliados: %s\n",
            nombre, poblacion, recursos, poderMilitar, enConflicto ? "Sí" : "No",
            aliados.isEmpty() ? "Ninguno" : aliadosNombres()
        );
    }

    private String aliadosNombres() {
        List<String> n = new ArrayList<>();
        for (Nacion a : aliados) n.add(a.nombre);
        return String.join(", ", n);
    }
}

class NacionDesarrollada extends Nacion {
    private boolean tecnologiaAvanzada;

    public NacionDesarrollada(String nombre, int poblacion, double recursos, int poderMilitar, boolean tecnologiaAvanzada) {
        super(nombre, poblacion, recursos, poderMilitar);
        this.tecnologiaAvanzada = tecnologiaAvanzada;
    }

    @Override
    public int calcularImpacto() {
        int impacto = poderMilitar;
        if (tecnologiaAvanzada) {
            impacto += 20; // bono de tecnología
        }
        if (!aliados.isEmpty()) {
            impacto += 10; // bono por aliados
        }
        if (impacto > 100) impacto = 100;
        return impacto;
    }
}

class NacionEnDesarrollo extends Nacion {
    private boolean recursosLimitados;

    public NacionEnDesarrollo(String nombre, int poblacion, double recursos, int poderMilitar, boolean recursosLimitados) {
        super(nombre, poblacion, recursos, poderMilitar);
        this.recursosLimitados = recursosLimitados;
    }

    @Override
    public int calcularImpacto() {
        int impacto = poderMilitar;
        if (recursosLimitados) {
            impacto -= 15; // penalización por recursos limitados
        }
        if (!aliados.isEmpty()) {
            impacto += 5; // bono por aliados
        }
        if (impacto < 1) impacto = 1;
        if (impacto > 100) impacto = 100;
        return impacto;
    }
}

class Conflicto {
    private Nacion nacionA;
    private Nacion nacionB;

    public Conflicto(Nacion nacionA, Nacion nacionB) {
        this.nacionA = nacionA;
        this.nacionB = nacionB;
    }

    public void simular() {
        nacionA.setEnConflicto(true);
        nacionB.setEnConflicto(true);

        int impactoA = nacionA.calcularImpacto();
        int impactoB = nacionB.calcularImpacto();

        if (impactoA > impactoB) {
            // A gana, B pierde
            int diferencia = impactoA - impactoB;
            nacionB.reducirPoblacion(5.0 * diferencia);
            nacionB.reducirRecursos(10.0);
        } else if (impactoB > impactoA) {
            // B gana, A pierde
            int diferencia = impactoB - impactoA;
            nacionA.reducirPoblacion(5.0 * diferencia);
            nacionA.reducirRecursos(10.0);
        } else {
            // Empate
            nacionA.reducirRecursos(5.0);
            nacionB.reducirRecursos(5.0);
        }

        nacionA.setEnConflicto(false);
        nacionB.setEnConflicto(false);
    }
}

public class P6SimuladorEjecutor {
    public static void main(String[] args) {
        // Crear naciones
        NacionDesarrollada usa = new NacionDesarrollada("Estados Unidos", 330_000_000, 2000000, 95, true);
        NacionDesarrollada alemania = new NacionDesarrollada("Alemania", 83_000_000, 800000, 85, true);
        NacionEnDesarrollo ecuador = new NacionEnDesarrollo("Ecuador", 18_000_000, 100000, 60, true);
        NacionEnDesarrollo nigeria = new NacionEnDesarrollo("Nigeria", 200_000_000, 120000, 55, true);

        // Aliados
        usa.agregarAliado(alemania);
        alemania.agregarAliado(usa);
        ecuador.agregarAliado(nigeria);

        List<Nacion> naciones = Arrays.asList(usa, alemania, ecuador, nigeria);

        // Simular conflictos aleatorios
        Random rand = new Random();
        int totalConflictos = 3;
        List<Conflicto> conflictos = new ArrayList<>();
        for (int i = 0; i < totalConflictos; i++) {
            int idxA = rand.nextInt(naciones.size());
            int idxB;
            do {
                idxB = rand.nextInt(naciones.size());
            } while (idxB == idxA);
            Conflicto c = new Conflicto(naciones.get(idxA), naciones.get(idxB));
            c.simular();
            conflictos.add(c);
        }

        // Reporte final
        System.out.println("=== REPORTE FINAL DE NACIONES ===");
        for (Nacion n : naciones) {
            System.out.println(n);
        }
        System.out.println("Total de conflictos simulados: " + totalConflictos);
    }
}