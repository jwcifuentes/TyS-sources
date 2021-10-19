package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.CausalDespido;

public class CausalDespidoMapper implements RowMapper<CausalDespido> {

	private CausalDespidoMapper() {
	}

	public static CausalDespidoMapper newInstance() {
		return new CausalDespidoMapper();
	}

	@Override
	public CausalDespido mapRow(ResultSet rs, int rowNum) throws SQLException {
		return CausalDespido.newInstance().idCausalDespido(rs.getLong("ID_CAUSAL_DESPIDO"))
				.codCausalDespido(rs.getLong("COD_CAUSAL_DESPIDO"))
				.descripcionCausalDespido(rs.getString("DESCRIPCION_CAUSAL_DESPIDO")).build();
	}

}
