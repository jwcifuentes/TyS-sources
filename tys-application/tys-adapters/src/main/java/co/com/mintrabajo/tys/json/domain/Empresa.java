package co.com.mintrabajo.tys.json.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder(builderMethodName = "newInstance")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long strIdSolicitante;
	private Long strIdCiiu;
	private List<Socio> lstSocio;
	private Long strIdEmpresa;
	private String strNumeroEscritura;
	private String datFechaExpedicionEscritura;
	private String strIdDepartamentoExpedicion;
	private String strIdMunicipioExpedicion;
	private String strIdNotaria;
	private DireccionSucursal objDireccionSucursal;
	private String strNumeroPoliza;
	private String strValPoliza;
	private String strNombreAseguradora;
	private String datFechaInicioPoliza;
	private String datFechaFinPoliza;
	private long strIndicaNotificacionElec;
	private long strIndicaCOFElectronica;
	private String strIndicaConvPactosColectivos;
	private String strIndicaObligReglaTrabajo;
	private String strIndicaEmpresaSAS;
	private Long strIdTipoEmpresa;
	private Long strIdTipoEmpleador;
	private Long strIdTipoGestion;
	private Long strIdActEconomica;
	
	private String strRazonSocial;
	private Long strIdPersoneria; 
	private Long strIdTipoIdentificacion;
	private String strNumeroIdentificacion;
	private String strValCelular;
	private String strValTelefono;
	private String strValRepresentanteLegal;
}
