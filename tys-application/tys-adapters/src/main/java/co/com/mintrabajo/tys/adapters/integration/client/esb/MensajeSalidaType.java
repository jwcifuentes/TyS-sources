
package co.com.mintrabajo.tys.adapters.integration.client.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MensajeSalidaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MensajeSalidaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoSalida"/>
 *         &lt;element name="json" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensajeSalidaType", namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", propOrder = {
    "encabezadoSalida",
    "json"
})
public class MensajeSalidaType {

    @XmlElement(name = "EncabezadoSalida", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoSalidaType encabezadoSalida;
    @XmlElement(required = true,namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico")
    protected String json;

    /**
     * Gets the value of the encabezadoSalida property.
     * 
     * @return
     *     possible object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public EncabezadoSalidaType getEncabezadoSalida() {
        return encabezadoSalida;
    }

    /**
     * Sets the value of the encabezadoSalida property.
     * 
     * @param value
     *     allowed object is
     *     {@link EncabezadoSalidaType }
     *     
     */
    public void setEncabezadoSalida(EncabezadoSalidaType value) {
        this.encabezadoSalida = value;
    }

    /**
     * Gets the value of the json property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJson() {
        return json;
    }

    /**
     * Sets the value of the json property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJson(String value) {
        this.json = value;
    }

}
