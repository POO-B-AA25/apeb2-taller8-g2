abstract class Dispositivo {
    protected String ubicacion;

    public Dispositivo(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public abstract String recolectarDatos();
    public abstract String generarReporte();

    @Override
    public String toString() {
        return "Ubicación: " + ubicacion + "\n" + generarReporte();
    }
}

class DispositivoCosta extends Dispositivo {
    public DispositivoCosta(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public String recolectarDatos() {
        // Simulación de recolección de datos para la costa
        return "Temperatura: 32°C, Humedad: 80%, Precipitación: 10mm";
    }

    @Override
    public String generarReporte() {
        // Ejemplo: riesgo de sequía en la costa
        return "Reporte Costa [" + ubicacion + "]:\n" +
               recolectarDatos() + "\n" +
               "Riesgo detectado: Sequía costera\n";
    }
}

class DispositivoSierra extends Dispositivo {
    public DispositivoSierra(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public String recolectarDatos() {
        // Simulación de recolección de datos para la sierra
        return "Temperatura: 15°C, Humedad: 60%, Precipitación: 30mm";
    }

    @Override
    public String generarReporte() {
        // Ejemplo: riesgo de deslizamientos en la sierra
        return "Reporte Sierra [" + ubicacion + "]:\n" +
               recolectarDatos() + "\n" +
               "Riesgo detectado: Deslizamientos\n";
    }
}

class DispositivoOriente extends Dispositivo {
    public DispositivoOriente(String ubicacion) {
        super(ubicacion);
    }

    @Override
    public String recolectarDatos() {
        // Simulación de recolección de datos para el oriente
        return "Temperatura: 28°C, Humedad: 90%, Calidad del aire: Buena";
    }

    @Override
    public String generarReporte() {
        // Ejemplo: riesgo de contaminación del aire en el oriente
        return "Reporte Oriente [" + ubicacion + "]:\n" +
               recolectarDatos() + "\n" +
               "Riesgo detectado: Contaminación del aire\n";
    }
}

public class P4MonitoreoEjecutor {
    public static void main(String[] args) {
        Dispositivo[] dispositivos = {
            new DispositivoCosta("Manta"),
            new DispositivoSierra("Quito"),
            new DispositivoOriente("Tena")
        };

        System.out.println("=== Reportes de Monitoreo Ambiental ===");
        for (Dispositivo d : dispositivos) {
            System.out.println(d);
        }
    }
}