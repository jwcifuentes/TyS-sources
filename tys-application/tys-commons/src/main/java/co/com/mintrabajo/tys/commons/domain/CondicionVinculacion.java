package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class CondicionVinculacion implements Serializable {

	private static final long serialVersionUID = 1L;

	// IDE_COND_VINCULO
	private Long id;
	// IDE_TIPO_VINCULO
	private Long idTipoVinculo;
	// IDE_TIPO_ACTIVIDAD
	private Long idTipoActividad;
	// IDE_TIPO_PAGO
	private Long idTipoPago;
	// VAL_REMUNERACION
	private Long valRemuneracion;
	// VAL_AUX_TRANSP
	private Long valAuxTransp;
	// VAL_DESCRIPCION
	private String valDescripcion;
	// IDE_EMPRESA
	private Long idEmpresa;
	// VAL_CARGO
	private String valCargo;
	
	public CondicionVinculacion(){
		
	}

}
