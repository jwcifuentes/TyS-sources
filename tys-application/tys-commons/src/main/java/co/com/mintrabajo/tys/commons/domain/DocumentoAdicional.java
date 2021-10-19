package co.com.mintrabajo.tys.commons.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DocumentoAdicional {

	// IDE_DOC_ADIC
	private Long id;
	// IDE_REG_TRAMITE
	private Long idResgistroTramite;
	// IDE_PPD_DOCUMENTO
	private Long idDocumentosProducido;
	// FEC_CREACION
	private Date fechaCreacion;
	// IDE_USUARIO_CREA
	private String usuarioCrea;
	// FEC_CAMBIO
	private Date fechaModifica;
	// IDE_USUARIO_CAMBIO
	private String usuarioModifica;
	// VAL_IP
	private String ip;
}
