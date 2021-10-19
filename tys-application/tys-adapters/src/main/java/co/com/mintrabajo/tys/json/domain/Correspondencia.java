package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class Correspondencia implements Serializable {

	private static final long serialVersionUID = 1L;

	private TipoComunicacion tipoComunicacion;
	private MedioRecepcion medioRecepcion;
	private TipologiaDocumental tipologiaDocumental;
	private String fechaRadicacion;
	private String numeroRadicado;
	private Long tiempoRespuesta;
	private Long numeroFolios;
	private Long numeroAnexos;
	private boolean requiereDigitalizacion;
	private boolean requiereDistribFisica;
	private String asunto;
	private String descripcion;
	private List<String> referidos;
	private Long sedeNacional;


}
