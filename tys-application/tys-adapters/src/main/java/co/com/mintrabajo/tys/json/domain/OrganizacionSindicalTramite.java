package co.com.mintrabajo.tys.json.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class OrganizacionSindicalTramite implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;    
    private Long idRegistroTramite;    
    private String nombreOrganizacion;    
    private String sigla;    
    private String numeroPersoneria;    
    private DireccionSucursal objDireccion;

    public OrganizacionSindicalTramite(){
        super();
    }
}
