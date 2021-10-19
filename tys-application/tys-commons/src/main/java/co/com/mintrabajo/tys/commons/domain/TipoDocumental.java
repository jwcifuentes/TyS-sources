package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by jrodriguez on 08/11/2017.
 */
@Data
@Builder(builderMethodName = "newInstance")
public class TipoDocumental implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nombre;
    private boolean estado;

}
