package co.com.mintrabajo.tys.commons.constants;

public enum EstadoTramite {

    ACTIVO      (1,"Activo"),
    INACTIVO    (0, "Inactivo");

    private final String name;
    private final int id;

    private EstadoTramite(int id, String name) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;

    }

}
