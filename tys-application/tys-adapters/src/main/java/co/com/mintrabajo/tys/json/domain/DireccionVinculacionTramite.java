package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class DireccionVinculacionTramite implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long strIdTipoVia;
	private Long strIdPrefijoCuadrante;
	private String strCodigoPostal;
	private String strValDireccion;
	private Long strIdPais;
	private Long strIdDepartamento;
	private Long strIdMunicipio;
	private String strValCiudad;
	
}
