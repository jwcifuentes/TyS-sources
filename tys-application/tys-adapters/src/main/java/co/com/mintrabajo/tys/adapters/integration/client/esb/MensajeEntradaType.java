
package co.com.mintrabajo.tys.adapters.integration.client.esb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for MensajeEntradaType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="MensajeEntradaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoEntrada"/>
 *         &lt;element name="json" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="store_procedure" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "MensajeEntradaType", namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico", propOrder = {
    "encabezadoEntrada",
    "json",
    "storeProcedure"
})
public class MensajeEntradaType {

    @XmlElement(name = "EncabezadoEntrada", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoEntradaType encabezadoEntrada;
    @XmlElement(required = true,namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico")
    protected String json;
    @XmlElement(name = "store_procedure", namespace = "http://soaint.com/Servicios/Utilitario/xsd/JSONGenerico",required = true)
    protected String storeProcedure;

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

    /**
     * Gets the value of the storeProcedure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStoreProcedure() {
        return storeProcedure;
    }

    /**
     * Sets the value of the storeProcedure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStoreProcedure(String value) {
        this.storeProcedure = value;
    }

}
