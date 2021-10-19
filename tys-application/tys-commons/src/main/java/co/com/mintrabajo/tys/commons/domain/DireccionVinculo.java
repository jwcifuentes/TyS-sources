package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class DireccionVinculo implements Serializable {

	private static final long serialVersionUID = 1L;
	// IDE_DIR_VINCULO
	private Long id;
	// IDE_COND_VINCULO
	private Long idCondVinculo;
	// IDE_DIRECCION
	private Long idDireccionDireccion;
	// FEC_CREACION
	private Date fechaCreacion;
	// FEC_CAMBIO
	private Date fechaModificacion;
	// USUARIO_CREA
	private String usuarioCrea;
	// USUARIO_CAMBIO
	private String usuarioModificacion;

}
