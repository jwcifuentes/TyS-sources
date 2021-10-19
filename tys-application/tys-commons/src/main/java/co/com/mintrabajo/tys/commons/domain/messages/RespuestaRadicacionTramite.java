package co.com.mintrabajo.tys.commons.domain.messages;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class RespuestaRadicacionTramite {

	private String nombreDireccion;
	private String numeroRadicado;
	private String codigoSeguridad;
	private List<String> documentosAdicionalesTramite;

	public RespuestaRadicacionTramite() {
		super();
	}

}
