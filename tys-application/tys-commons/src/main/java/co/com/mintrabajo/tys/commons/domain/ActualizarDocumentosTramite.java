package co.com.mintrabajo.tys.commons.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ActualizarDocumentosTramite {
	
	List<DocumentoTramite> listDocumentoTramite;
	List<DatosDocumentoAdicional> listDocumentoAdicionales;
	
	public ActualizarDocumentosTramite(){}

}
