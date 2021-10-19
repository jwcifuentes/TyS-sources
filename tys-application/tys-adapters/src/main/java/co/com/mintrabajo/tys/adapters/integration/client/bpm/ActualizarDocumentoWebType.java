
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for actualizarDocumentoWeb_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="actualizarDocumentoWeb_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoEntrada"/>
 *         &lt;element name="strId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "actualizarDocumentoWeb_Type", propOrder = {
    "encabezadoEntrada",
    "strId"
})
public class ActualizarDocumentoWebType {

    @XmlElement(name = "EncabezadoEntrada", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoEntradaType encabezadoEntrada;
    @XmlElement(required = true)
    protected String strId;

    /**
     * Gets the value of the encabezadoEntrada property.
     * 
     * @return
     *     possible object is
     *     {@link EncabezadoEntradaType }
     *     
     */
    public EncabezadoEntradaType getEncabezadoEntrada() {
        return encabezadoEntrada;
    }

    /**
     * Sets the value of the encabezadoEntrada property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncabezadoEntradaType }
     *     
     */
    public void setEncabezadoEntrada(EncabezadoEntradaType value) {
        this.encabezadoEntrada = value;
    }

    /**
     * Gets the value of the strId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrId() {
        return strId;
    }

    /**
     * Sets the value of the strId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrId(String value) {
        this.strId = value;
    }

}
