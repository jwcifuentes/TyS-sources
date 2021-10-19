package co.com.mintrabajo.tys.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import co.com.mintrabajo.tys.commons.domain.DocumentoProducido;

public class DocumentoProducidoMapper implements RowMapper<DocumentoProducido> {
	
	private DocumentoProducidoMapper() {
	}

	public static DocumentoProducidoMapper newInstance() {
		return new DocumentoProducidoMapper();
	}

	@Override
	public DocumentoProducido mapRow(ResultSet rs, int rowNum) throws SQLException {
		return DocumentoProducido.newInstance().idFilenet(rs.getString("VAL_FILENET")).build();
	}

}
