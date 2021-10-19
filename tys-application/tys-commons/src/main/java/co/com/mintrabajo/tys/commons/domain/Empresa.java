package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;

	// IDE_EMPRESA
	private Long id;
	// NUM_ESCRITURA
	private String numeroEscritura;
	// FEC_EXP_ESCRITURA
	private Date fechaExpedicionEscritura;
	// IDE_DPTO_EXPE
	private Long idDepartamentoExpedicion;
	// IDE_MPIO_EXP
	private Long idMunicipioExpedicion;
	// IDE_NOTARIA
	private Long idNotaria;
	// NUM_POLIZA
	private String numeroPoliza;
	// VAL_POLIZA
	private Long valorPoliza;
	// NOM_ASEGURADORA
	private String nombreAseguradora;
	// FEC_IINI_POLIZA
	private Date fechaInicialPoliza;
	// FEC_FIN_POLIZA
	private Date fechaFinPoliza;
	// IDE_SOLICITANTE
	private Long idSolicitante;
	// IDE_DIRECCION
	private Long idDireccion;
	// IDE_CIIU CODIGO ACTIVIDAD
	private Long idCIIU;
	// IDE_REG_TRAMITE
	private Long idRegistroTramite;
	// IDE_TIP_EMPRESA
	private Long idTipoEmpresa;
	// IND_NOT_ELECT
	private boolean idNotificacionElectronica;
	// IND_COMOF_ELECT
	private boolean autorizacionNotificacionElectronica;
	// IND_PACT_CONV_COL
	private boolean tieneConveniosColectivos;
	// IND_OBLI_REGL_TRAB
	private boolean tieneReglamentoTrabajo;
	// IND_EMP_SAS
	private boolean esSAS;
	// IDE_REP_LEGAL
	private Long idRepresentanteLegal;
	// IDE_DIR_SUCURSAL
	private Long idDireccionSucuarsal;
	// IDE_TIP_EMPLEADOR
	private Long idTipoEmpleador;
	//NIT
	private String nit;
	//NOM_RAZON_SOCIAL
	private String razonSocial;
	//IDE_TIP_GESTION
	private Long idTipoGestion;

	private ActividadEconomica grupoActividadEconomica;
	public Empresa() {
	}

}
