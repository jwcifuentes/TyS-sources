package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosEmpleador {
    //IDE_EMPLEADOR
    private Long idEmpleador;
    private String celular;
    private Long departamento;
    private String direccion;
    private String email;
    private Long municipio;
    private String nombreRazonSocial;
    private String nombreRepresentanteLegat;
    private String nroNit;
    private String telefono;
    private String tipoEmpleador;
    public DatosEmpleador(){}
}
