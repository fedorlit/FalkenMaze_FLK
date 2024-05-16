/*
* Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
* Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
*/
package pedro.ieslaencanta.com.falkensmaze;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement
public class Size implements Cloneable, Comparable<Size>, Serializable {
    private int width; // variable ancho del objeto
    private int height; // variable alto del objeto

    // Constructor del tamaño (vacio)
    public Size() {
    }

    // Constructor para establecer el ancho y el alto
    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    // Método para clonar un objeto Size
    public Object clone() throws CloneNotSupportedException {
        return new Size(this.getWidth(), this.getHeight());
    }

    // Método para comparar dos objetos Size
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Size)) {
            return false;
        }
        if (this.getWidth() == ((Size) (o)).getWidth() && this.getHeight() == ((Size) (o)).getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    // Método para comparar dos objetos Size para ordenarlos
    @Override
    public int compareTo(Size o) {
        if (this.getWidth() == o.getWidth() && this.getHeight() == o.getHeight())
            return 0;
        if (this.getWidth() < o.getWidth())
            return -1;
        else
            return 1;
    }

    // Método para representar el objeto Size como String
    public String toString() {
        return "W:" + this.width + " H:" + this.height;
    }

    /**
     * @return the width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param width the width to set
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }
}
