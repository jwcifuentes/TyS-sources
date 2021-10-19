package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ActividadEconomica {
    private Long idActividadEconomica;
    private String descripcion;
    private Long idActividadEconomicaPadre;
    private String seccion;
    private Long divsionGrupo;
    public ActividadEconomica(){}
}
