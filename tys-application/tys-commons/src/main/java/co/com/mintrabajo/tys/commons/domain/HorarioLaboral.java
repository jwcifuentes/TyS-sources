package co.com.mintrabajo.tys.commons.domain;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class HorarioLaboral implements Serializable {

	private static final long serialVersionUID = 1L;

	// IDE_HORARIO
	private Long id;
	// LUNES_INI_AM
	private String lunesInicioAM;
	// LUNES_FIN_AM
	private String lunesFinAM;
	// LUNES_INI_PM
	private String lunesInicioPM;
	// LUNES_FIN_PM
	private String lunesFinPM;
	// LUNES_TOTAL
	private String lunesTotal;
	// MARTES_INI_AM
	private String martesInicioAM;
	// MARTES_FIN_AM
	private String martesFinAM;
	// MARTES_INI_PM
	private String martesInicioPM;
	// MARTES_FIN_PM
	private String martesFinPM;
	// MARTES_TOTAL
	private String martesTotal;
	// MIERCOLES_INI_AM
	private String miercolesInicioAM;
	// MIERCOLES_FIN_AM
	private String miercolesFinAM;
	// MIERCOLES_INI_PM
	private String miercolesInicioPM;
	// MIERCOLES_FIN_PM
	private String miercolesFinPM;
	// MIERCOLES_TOTAL
	private String miercolesTotal;
	// JUEVES_INI_AM
	private String juevesInicioAM;
	// JUEVES_FIN_AM
	private String juevesFinAM;
	// JUEVES_INI_PM
	private String juevesInicioPM;
	// JUEVES_FIN_PM
	private String juevesFinPM;
	// JUEVES_TOTAL
	private String juevesTotal;
	// VIERNES_INI_AM
	private String viernesInicioAM;
	// VIERNES_FIN_AM
	private String viernesFinAM;
	// VIERNES_INI_PM
	private String viernesInicioPM;
	// VIERNES_FIN_PM
	private String viernesFinPM;
	// VIERNES_TOTAL
	private String viernesTotal;
	// SABADO_INI_AM
	private String sabadoInicioAM;
	// SABADO_FIN_AM
	private String sabadoFinAM;
	// SABADO_INI_PM
	private String sabadoInicioPM;
	// SABADO_FIN_PM
	private String sabadoFinPM;
	// SABADO_TOTAL
	private String sabadoTotal;
	// DOMINGO_INI_AM
	private String domingoInicioAM;
	// DOMINGO_FIN_AM
	private String domingoFinAM;
	// DOMINGO_INI_PM
	private String domingoInicioPM;
	// DOMINGO_FIN_PM
	private String domingoFinPM;
	// DOMINGO_TOTAL
	private String domingoTotal;
	// TOTAL_SEMANAL
	private String totalSemanal;
	
	public HorarioLaboral(){
		
	}


}
