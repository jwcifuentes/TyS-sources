package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.DatosDocumentoAdicional;

public class DocumentosAdicionalesMapper implements RowMapper<DatosDocumentoAdicional>{
	
	private DocumentosAdicionalesMapper() {
	}

	public static DocumentosAdicionalesMapper newInstance() {
		return new DocumentosAdicionalesMapper();
	}
	
	@Override
	public DatosDocumentoAdicional mapRow(ResultSet rs, int rowNum) throws SQLException {
		return DatosDocumentoAdicional.newInstance()
				.nombreDocumento(rs.getString("VAL_ASUNTO"))
				.idFilenet(rs.getString("VAL_FILENET"))
				.idRegistraTramite(rs.getLong("IDE_REG_TRAMITE"))
				.build();
	}

}
