package co.com.mintrabajo.tys.commons.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by jrodriguez on 07/11/2017.
 */

@Data
@Builder(builderMethodName = "newInstance")
public class Parametro implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String codigo;
    private String nombre;
    private boolean estado;

}
