package co.com.mintrabajo.tys.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder(builderMethodName = "newInstance")
public class ObjetoPractica {
    private boolean isSelected;
    private String objetoPractica;
    private Long id;
}
