package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosEscenarioPractica {
    private String nombreEntidad;
    private Long idTipoEntidad;
    private Long idTipoIdentificacion;
    private String numeroIdentificacion;
    private Direccion direccion;
    private String correo;
    private String telefonoFijo;
    private String telefonoCelular;
}
