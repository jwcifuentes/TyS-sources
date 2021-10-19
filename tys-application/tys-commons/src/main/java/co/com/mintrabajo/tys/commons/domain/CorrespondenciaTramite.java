package co.com.mintrabajo.tys.commons.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CorrespondenciaTramite {

	private Long idTipoComunicacion;
	private Long idMedioRecepcion;
	private Long idTipologiaDocumental;
	private Long tiempoRespuesta;
	private String fechaRadicacion;
	private String numeroRadicado;
	private String asunto;
	private String descripcion;
	private List<String> referidos;
	private Remitente remitente;
	private List<DireccionTerritorial> listaDireccionTerritoriales;
	private String idUsuario;
	private String nombreDependencia;
	
	public CorrespondenciaTramite(){
		
	}

}
