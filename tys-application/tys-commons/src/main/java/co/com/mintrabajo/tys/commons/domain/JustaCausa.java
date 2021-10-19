package co.com.mintrabajo.tys.commons.domain;

import java.util.List;

import co.com.mintrabajo.tys.commons.domain.DatosSolicitudTramite.DatosSolicitudTramiteBuilder;
import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class JustaCausa {

	private Long idRegistroTramite;
	private Long idJustasCausas;
	private Long idCausalDespido;
	private String description_name;
	private Long idTipo;
	private String observacion;

	public JustaCausa() {
		super();
	}

}
