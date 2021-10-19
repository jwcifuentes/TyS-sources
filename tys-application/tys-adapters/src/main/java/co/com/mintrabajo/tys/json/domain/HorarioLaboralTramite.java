package co.com.mintrabajo.tys.json.domain;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

@Data
@Builder(builderMethodName = "newInstance")
public class HorarioLaboralTramite implements Serializable {

	private static final long serialVersionUID = 1L;

	private String strTotalSemana;
	private String strDomingoInicioAM;
	private String strDomingoFinAM;
	private String strDomingoInicioPM;
	private String strDomingoFinPM;
	private String strDomingoTotal;
	private String strJuevesInicioAM;
	private String strJuevesFinAM;
	private String strJuevesInicioPM;
	private String strJuevesFinPM;
	private String strJuevesTotal;
	private String strViernesInicioAM;
	private String strViernesFinAM;
	private String strViernesInicioPM;
	private String strViernesFinPM;
	private String strViernesTotal;
	private String strSabadoInicioAM;
	private String strSabadoFinAM;
	private String strSabadoInicioPM;
	private String strSabadoFinPM;
	private String strSabadoTotal;
	private String strLunesInicioAM;
	private String strLunesFinAM;
	private String strLunesInicioPM;
	private String strLunesFinPM;
	private String strLunesTotal;
	private String strMartesInicioAM;
	private String strMartesFinAM;
	private String strMartesInicioPM;
	private String strMartesFinPM;
	private String strMartesTotal;
	private String strMiercolesInicioAM;
	private String strMiercolesFinAM;
	private String strMiercolesInicioPM;
	private String strMiercolesFinPM;
	private String strMiercolesTotal;

}
