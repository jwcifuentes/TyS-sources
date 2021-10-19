package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class OrganizacionSindical implements Serializable {
    private static final long serialVersionUID = 1L;
    //IDE_ORG_SINDI
    private Long id;
    //IDE_REG_TRAMITE
    private Long idRegistroTramite;
    //NOMBRE
    private String nombreOrganizacion;
    //SIGLA
    private String sigla;
    //NUMERO_PERSONERIA
    private String numeroPersoneria;
    //DIRECCION
    private Direccion address;

    public OrganizacionSindical(){
        super();
    }
}
