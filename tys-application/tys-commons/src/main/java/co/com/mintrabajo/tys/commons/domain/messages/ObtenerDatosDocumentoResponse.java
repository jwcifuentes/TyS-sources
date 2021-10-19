package co.com.mintrabajo.tys.commons.domain.messages;

import co.com.mintrabajo.tys.commons.domain.DocumentoTramite;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObtenerDatosDocumentoResponse {

	private EncabezadoSalida encabezadoSalida;
	private List<DocumentoTramite> documentosOut;

	public ObtenerDatosDocumentoResponse() {
		super();
	}

}
