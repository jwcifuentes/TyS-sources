package co.com.mintrabajo.tys.commons.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ProcesoMensajeTyS {

	//IDE
	private Long id;
	//COD_DEPENDENCIA
	private String codDependencia;
	//NUM_RADICADO
	private String numeroRadicado;
	//BASE64_DOC
	byte [] documento;
	//IDE_TIPOLOGIA
	private int idTipoDocumental;
	//COD_PLANTILLA
	private String codPlantilla;
	//NOMBRE_DOC
	private String nombreDocumento;
	//ID_DOCUMENTO
	private int idDocumento;
	//ID_FILENET
	private String idFilenet;
	//ORIGEN
	private String origen;
	//ESTADO
	private String estado;
	//FEC_CREACION
	private Date fechaCreacion;
	//IDE_USUARIO_CREA
	private String idUsuarioCrea;
	//FEC_CAMBIO
	private Date fechaModificacion;
	//IDE_USUARIO_CAMBIO
	private String idUsuarioModifica;
	
	@Override
	public String toString() {
		return "ProcesoMensajeTyS [id=" + id + ", codDependencia=" + codDependencia + ", numeroRadicado="
				+ numeroRadicado + ", idTipoDocumental=" + idTipoDocumental + ", codPlantilla=" + codPlantilla
				+ ", nombreDocumento=" + nombreDocumento + ", idDocumento=" + idDocumento + ", idFilenet=" + idFilenet
				+ ", origen=" + origen + ", estado=" + estado + ", fechaCreacion=" + fechaCreacion + ", idUsuarioCrea="
				+ idUsuarioCrea + ", fechaModificacion=" + fechaModificacion + ", idUsuarioModifica="
				+ idUsuarioModifica + "]";
	}
	

	
	
}
