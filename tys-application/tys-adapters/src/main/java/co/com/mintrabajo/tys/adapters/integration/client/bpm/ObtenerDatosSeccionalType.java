
package co.com.mintrabajo.tys.adapters.integration.client.bpm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerDatosSeccional_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerDatosSeccional_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoEntrada"/>
 *         &lt;element name="strCodigoTramite_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strGradoAsociacion_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="strIdSubFondo_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDatosSeccional_Type", propOrder = {
    "encabezadoEntrada",
    "strCodigoTramiteIn",
    "strGradoAsociacionIn",
    "strIdSubFondoIn"
})
public class ObtenerDatosSeccionalType {

    @XmlElement(name = "EncabezadoEntrada", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoEntradaType encabezadoEntrada;
    @XmlElement(name = "strCodigoTramite_in", required = true)
    protected String strCodigoTramiteIn;
    @XmlElement(name = "strGradoAsociacion_in", required = true)
    protected String strGradoAsociacionIn;
    @XmlElement(name = "strIdSubFondo_in", required = true)
    protected String strIdSubFondoIn;

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
     * Gets the value of the strCodigoTramiteIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrCodigoTramiteIn() {
        return strCodigoTramiteIn;
    }

    /**
     * Sets the value of the strCodigoTramiteIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrCodigoTramiteIn(String value) {
        this.strCodigoTramiteIn = value;
    }

    /**
     * Gets the value of the strGradoAsociacionIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrGradoAsociacionIn() {
        return strGradoAsociacionIn;
    }

    /**
     * Sets the value of the strGradoAsociacionIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrGradoAsociacionIn(String value) {
        this.strGradoAsociacionIn = value;
    }

    /**
     * Gets the value of the strIdSubFondoIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIdSubFondoIn() {
        return strIdSubFondoIn;
    }

    /**
     * Sets the value of the strIdSubFondoIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIdSubFondoIn(String value) {
        this.strIdSubFondoIn = value;
    }

}
