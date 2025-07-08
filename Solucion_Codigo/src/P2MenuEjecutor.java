import java.util.*;

abstract class Menu {
    protected String nombrePlato;
    protected double valorInicial;

    public Menu(String nombrePlato, double valorInicial) {
        this.nombrePlato = nombrePlato;
        this.valorInicial = valorInicial;
    }

    public abstract double calcularValorMenu();

    @Override
    public String toString() {
        return String.format("Plato: %s | Valor Inicial: %.2f | Valor Menú: %.2f",
                nombrePlato, valorInicial, calcularValorMenu());
    }
}

class MenuCarta extends Menu {
    private double guarnicion;
    private double bebida;
    private double porcServicio;

    public MenuCarta(String nombrePlato, double valorInicial, double guarnicion, double bebida, double porcServicio) {
        super(nombrePlato, valorInicial);
        this.guarnicion = guarnicion;
        this.bebida = bebida;
        this.porcServicio = porcServicio;
    }

    @Override
    public double calcularValorMenu() {
        return valorInicial + guarnicion + bebida + (valorInicial * porcServicio / 100.0);
    }

    @Override
    public String toString() {
        return String.format("Menú a la Carta - %s | Guarnición: %.2f | Bebida: %.2f | Servicio: %.2f%% | Total: %.2f",
                nombrePlato, guarnicion, bebida, porcServicio, calcularValorMenu());
    }
}

class MenuDia extends Menu {
    private double postre;
    private double bebida;

    public MenuDia(String nombrePlato, double valorInicial, double postre, double bebida) {
        super(nombrePlato, valorInicial);
        this.postre = postre;
        this.bebida = bebida;
    }

    @Override
    public double calcularValorMenu() {
        return valorInicial + postre + bebida;
    }

    @Override
    public String toString() {
        return String.format("Menú del Día - %s | Postre: %.2f | Bebida: %.2f | Total: %.2f",
                nombrePlato, postre, bebida, calcularValorMenu());
    }
}

class MenuNinos extends Menu {
    private double helado;
    private double pastel;

    public MenuNinos(String nombrePlato, double valorInicial, double helado, double pastel) {
        super(nombrePlato, valorInicial);
        this.helado = helado;
        this.pastel = pastel;
    }

    @Override
    public double calcularValorMenu() {
        return valorInicial + helado + pastel;
    }

    @Override
    public String toString() {
        return String.format("Menú de Niños - %s | Helado: %.2f | Pastel: %.2f | Total: %.2f",
                nombrePlato, helado, pastel, calcularValorMenu());
    }
}

class MenuEconomico extends Menu {
    private double descuento; // porcentaje

    public MenuEconomico(String nombrePlato, double valorInicial, double descuento) {
        super(nombrePlato, valorInicial);
        this.descuento = descuento;
    }

    @Override
    public double calcularValorMenu() {
        return valorInicial - (valorInicial * descuento / 100.0);
    }

    @Override
    public String toString() {
        return String.format("Menú Económico - %s | Descuento: %.2f%% | Total: %.2f",
                nombrePlato, descuento, calcularValorMenu());
    }
}

class Cuenta {
    private String cliente;
    private List<Menu> menus;
    private static final double IVA = 0.12;

    public Cuenta(String cliente, List<Menu> menus) {
        this.cliente = cliente;
        this.menus = menus;
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (Menu m : menus) {
            subtotal += m.calcularValorMenu();
        }
        return subtotal;
    }

    public double calcularTotal() {
        return calcularSubtotal() * (1 + IVA);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente: ").append(cliente).append("\n");
        sb.append("---- Menús solicitados ----\n");
        for (Menu m : menus) {
            sb.append(m.toString()).append("\n");
        }
        sb.append(String.format("Subtotal: %.2f\n", calcularSubtotal()));
        sb.append(String.format("IVA (12%%): %.2f\n", calcularSubtotal() * IVA));
        sb.append(String.format("Total a pagar: %.2f\n", calcularTotal()));
        return sb.toString();
    }
}

public class P2MenuEjecutor {
    public static void main(String[] args) {
        List<Menu> menus = new ArrayList<>();
        menus.add(new MenuCarta("Lomo Saltado", 8.0, 2.0, 1.5, 10));
        menus.add(new MenuDia("Pollo Guisado", 6.0, 1.0, 1.2));
        menus.add(new MenuNinos("Hamburguesa", 5.0, 0.8, 0.7));
        menus.add(new MenuEconomico("Ensalada", 4.0, 20));

        Cuenta cuenta = new Cuenta("Juan Pérez", menus);

        System.out.println(cuenta);
    }
}