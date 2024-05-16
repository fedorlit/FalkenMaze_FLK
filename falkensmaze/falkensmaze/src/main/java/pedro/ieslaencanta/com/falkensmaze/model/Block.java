/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Block implements Serializable {
    private String value; // Valor del bloque

    // Constructor que inicializa el valor del bloque como null
    public Block() {
        this.value = null;
    }

    // Método para obtener el valor del bloque
    public String getValue() {
        return this.value;
    }

    // Método para establecer el valor del bloque
    public void setValue(String value) {
        this.value = value;
    }

    // Método para comprobar si el bloque está vacío
    public boolean isEmpty() {
        return this.value == null;
    }
}
