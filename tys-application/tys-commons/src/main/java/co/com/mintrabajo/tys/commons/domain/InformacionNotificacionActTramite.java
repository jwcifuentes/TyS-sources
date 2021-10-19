package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class InformacionNotificacionActTramite {
	
	private String numeroRadicado;
	private String codigoSeguridad;
	private Long idTramite;
	private String tramite;
	private Long idTerritorial;
	private String territorial;
	private String correo;
	private String correoFuncinario;

}
