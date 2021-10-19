package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.JustaCausa;

public class JustaCausaMapper implements RowMapper<JustaCausa> {

	private JustaCausaMapper() {
	}

	public static JustaCausaMapper newInstance() {
		return new JustaCausaMapper();
	}

	@Override
	public JustaCausa mapRow(ResultSet rs, int rowNum) throws SQLException {

		return JustaCausa.newInstance().idJustasCausas(rs.getLong("ID_JUSTAS_CAUSAS"))
				.idCausalDespido(rs.getLong("ID_CAUSAL_DESPIDO"))
				.description_name(rs.getString("DESCRIPCION_JUSTA_CAUSA")).build();
	}
}
