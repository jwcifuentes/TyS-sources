
package co.com.mintrabajo.tys.adapters.integration.client.ecm;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obtenerDocumentoPorIdFilenet_Type complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obtenerDocumentoPorIdFilenet_Type">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://soaint.com/Servicios/Base/EncabezadosSOA}EncabezadoEntrada"/>
 *         &lt;element name="strIdFilenet_in" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obtenerDocumentoPorIdFilenet_Type", propOrder = {
    "encabezadoEntrada",
    "strIdFilenetIn"
})
public class ObtenerDocumentoPorIdFilenetType {

    @XmlElement(name = "EncabezadoEntrada", namespace = "http://soaint.com/Servicios/Base/EncabezadosSOA", required = true)
    protected EncabezadoEntradaType encabezadoEntrada;
    @XmlElement(name = "strIdFilenet_in", required = true)
    protected String strIdFilenetIn;

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
     * Gets the value of the strIdFilenetIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrIdFilenetIn() {
        return strIdFilenetIn;
    }

    /**
     * Sets the value of the strIdFilenetIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrIdFilenetIn(String value) {
        this.strIdFilenetIn = value;
    }

}
