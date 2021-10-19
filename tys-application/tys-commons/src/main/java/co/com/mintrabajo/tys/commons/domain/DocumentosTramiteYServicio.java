package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DocumentosTramiteYServicio {

	private boolean obligatorio;
	private String nombre;
	private String descripcion;
	private String idFilenet;
	private String nroRadicado;
	private Long tramiteTipologia;
	private Long idTipologia;
	private Long idDocumento;
	private String subclasificacion;
	//Estado del documento
	private Parametro estadoDoc;
	//Documento Otros
	private boolean other;
	public DocumentosTramiteYServicio(){

	}

}
