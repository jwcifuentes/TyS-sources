package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import co.com.mintrabajo.tys.commons.domain.ActividadEconomica;
import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.Empresa;

public class EmpresasMapper implements RowMapper<Empresa> {
	
	private EmpresasMapper() {
	}

	public static EmpresasMapper newInstance() {
		return new EmpresasMapper();
	}

	@Override
	public Empresa mapRow(ResultSet rs, int rowNum) throws SQLException {
		return Empresa.newInstance().nit(rs.getString("NIT"))
				.razonSocial(rs.getString("NOM_RAZON_SOCIAL"))
				.numeroEscritura(rs.getString("NUM_ESCRITURA"))
				.fechaExpedicionEscritura(rs.getDate("FEC_EXP_ESCRITURA"))
				.idDepartamentoExpedicion(rs.getLong("IDE_DPTO_EXPE"))
				.idMunicipioExpedicion(rs.getLong("IDE_MPIO_EXP"))
				.idNotaria(rs.getLong("IDE_NOTARIA"))
				.numeroPoliza(rs.getString("NUM_POLIZA"))
				.valorPoliza(rs.getLong("VAL_POLIZA"))
				.nombreAseguradora(rs.getString("NOM_ASEGURADORA"))
				.fechaInicialPoliza(rs.getDate("FEC_IINI_POLIZA"))
				.fechaFinPoliza(rs.getDate("FEC_FIN_POLIZA"))
				.grupoActividadEconomica(ActividadEconomica.newInstance()
						.idActividadEconomica(rs.getLong("ID_ACTIVIDAD_ECONOMICA"))
						.build())
				.build();
	}

}
