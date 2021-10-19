package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosFormacionAdolecente {
    private String nombreInstitucion;
    private String nit;
    private Long idTipoEducacion;
    private Long idTipoInstitucion;
    private String numeroTelefonico;
    private Direccion direccion;
    private String nombreProgramaAcademico;
    private String grado;
    private Long idPeriodoAcademico;
    private String anio;
    private String fechaInicio;
    private String fechaFin;
}
