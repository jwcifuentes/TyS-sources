package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class DireccionSucursal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long strIdDireccion;
	private String strNumDireccion;
	private String strNumVia;
	private String strNumPlaca;
	private Long strIdTipoVia;
	private Long strIdPrefijoCuadrante;
	private String strCodigoPostal;
	private String strValDireccion;
	private Long strIdPais;
	private Long strIdDepartamento;
	private Long strIdMunicipio;
	private String strValCiudad;
	private String strValCorreoElectronico;
	private Long strIdTipoUbicacion;
	private String strNombreUbicacion;
	private Long strIdTipoZona;

}
