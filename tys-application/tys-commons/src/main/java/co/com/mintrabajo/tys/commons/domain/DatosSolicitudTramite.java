package co.com.mintrabajo.tys.commons.domain;

import java.util.List;

import co.com.mintrabajo.tys.commons.domain.messages.ObtenerDatosSeccionalResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class DatosSolicitudTramite {

	private CorrespondenciaTramite correspondencia;
	private SolicitudTramite solicitudTramite;
	private DatosEmpresa empresa;
	private DatosCondicionVinculacion condicionVinculacion;
	private List<DocumentoTramite> listDocumentos;
	private ObtenerDatosSeccionalResponse dependencias;
	private List<JustaCausa> listaJustaCausa;
	
	private List<OrganizacionSindical> listaOrganizacionesSindicales;

	private DatosFormacionAdolecente datosFormacion;
	private DatosEscenarioPractica datosEscenarioPractica;
	private DatosVinculacionPracticasAdolecente datosVinculacion;
	private String strJsonDatosBasicosPdf;
	public DatosSolicitudTramite(){
		
	}
}
