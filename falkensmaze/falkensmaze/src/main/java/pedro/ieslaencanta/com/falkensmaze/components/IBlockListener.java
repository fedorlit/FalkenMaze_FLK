/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.components;

/**
 *
 * @author Fedor
 */

// Interfaz para los eventos del clic(clic o doble clic) sobre un bloque
public interface IBlockListener {
    public void onClicked(Block b);// Método el cual es llamado cuando se hace clic en un bloque

    public void onDoubleClicked(Block b);// Método el cual es llamado cuando se hace doble clic en un bloque
}
