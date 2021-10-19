package co.com.mintrabajo.tys.json.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(builderMethodName = "newInstance")
public class CondicionVinculacionTramite implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long strIdCondicionVinculo;
	private Long strIdTipoVinculo;
	private Long strIdTipoActividad;
	private Long strTipoPago;
	private Long decValRemuneracion;
	private Long decValAuxilioTransporte;
	private String strValDescripcion;
	private String strValCargo;
	private List<DireccionVinculacionTramite> lstDireccionVinculacion;
	private HorarioLaboralTramite objHorarioLaboral;
	

}
