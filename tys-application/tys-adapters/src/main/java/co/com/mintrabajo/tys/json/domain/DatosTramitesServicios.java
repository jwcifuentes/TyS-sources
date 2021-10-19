package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;
import java.util.List;

import co.com.mintrabajo.tys.commons.domain.JustaCausa;
import co.com.mintrabajo.tys.commons.domain.OrganizacionSindical;
import co.com.mintrabajo.tys.commons.domain.Parametro;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class DatosTramitesServicios  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private SolicitudTramite  objDatosTramite;
	private List<PersonaTramite> lstPersonas;
	private List<Parametro> lstCondicionDiscapacidad;
	private Empresa objEmpresa;
	private Trazabilidad  objTrazabilidad;
	private CondicionVinculacionTramite objCondVinculacion;
	private List<OrganizacionSindicalTramite> lstOrgSindicales; 
	private List<JustaCausa> lstJustasCausas;
	private String strJsonPracticas;

}
