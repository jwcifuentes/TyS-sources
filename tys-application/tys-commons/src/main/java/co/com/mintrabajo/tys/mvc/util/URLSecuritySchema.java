package co.com.mintrabajo.tys.mvc.util;

public interface URLSecuritySchema {

    public static final String ADMIN = "/admin";
    public static final String PUBLIC = "/public";

    public static final String PARAMETROS_PUBLIC = PUBLIC + "/parametros-wc";
    public static final String SEGURIDAD_PUBLIC = PUBLIC + "/seguridad-wc";
    public static final String TIPOS_DOCUMENTALES_ADMIN = ADMIN + "/tiposDocumentales-wc";
    public static final String TIPOS_DOCUMENTALES_PUBLIC = PUBLIC + "/tiposDocumentales-wc";
    public static final String TRAMITES_ADMIN = ADMIN + "/tramites-wc";
    public static final String DEPATAMENTOS_PUBLIC = PUBLIC + "/departamentos-wc";
    public static final String DIRECCIONES_TERRITORIALES_PUBLIC = PUBLIC + "/direcciones-territoriales-wc";
    public static final String MUNICIPIOS_PUBLIC = PUBLIC + "/municipios-wc";
    public static final String NOTARIAS_PUBLIC = PUBLIC + "/notarias-wc";
    public static final String PAISES_PUBLIC = PUBLIC + "/paises-wc";
    public static final String TRAMITES_SERVICIOS_PUBLIC = PUBLIC + "/tramites-servicios-wc";
    public static final String TRATAMIENTO_CORTESIA_PUBLIC = PUBLIC + "/tratamiento-cortesia-wc";
    public static final String GESTIONAR_TRAMITES_SERVICIOS_PUBLIC = PUBLIC + "/gestionar-tramites-wc";


}
