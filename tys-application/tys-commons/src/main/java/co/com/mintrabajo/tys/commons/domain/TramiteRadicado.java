package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class TramiteRadicado implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long idTramiteRadicado;
	private String numeroRadicado;
	private Long idTramite;
	private String nombreTramite;
	private Date fechaRadiacionTramite;
	private String fechaRadicacion;
	private Long idDireccionTerritorial;
	private String direccionTerritorial;
	private Long idDependencia;
	private String dependencia;
	private Long idEstadoTramite;
	private String nombreEstado;
	private boolean indTienePactosColectivos;
	private boolean indTieneReglamentoTrabajo;
	private boolean indTieneAsociacionSAS;
	private Long  idSolicitadoPor;
	private Long idJustSolicitud;
	private Long idGradoAsociacion;
	private String codDependencia;
	private String idFilenetDocProducido;
	private boolean indRequiereActualizacion;
	private Long indEstadoEntrevista;
	private Long indEstadoVisita;

}
