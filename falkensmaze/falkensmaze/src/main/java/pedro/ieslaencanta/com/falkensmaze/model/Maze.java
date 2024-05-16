/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pedro.ieslaencanta.com.falkensmaze.model;

import com.google.gson.Gson;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;

import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Serializable;

import java.io.UnsupportedEncodingException;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import pedro.ieslaencanta.com.falkensmaze.Size;

@XmlRootElement
public class Maze implements Serializable {

    private Size size; // Tamaño del laberinto
    private Block[][] blocks; // Bloques del laberinto
    private double time; // Tiempo del laberinto
    private String sound; // Sonido del laberinto

    public Maze() {
    }

    // Método para inicializar el laberinto con los bloques vacíos
    public void init() {
        this.setBlocks(new Block[this.getSize().getHeight()][this.getSize().getWidth()]);
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = new Block();
            }
        }
    }

    // Método para restablecer el laberinto al estado inicial
    public void reset() {
        for (int i = 0; i < this.getBlocks().length; i++) {
            for (int j = 0; j < this.getBlocks()[i].length; j++) {
                this.getBlocks()[i][j] = null;
            }
        }
        this.setBlocks(null);
    }

    // Método para restablecer el tamaño del laberitno
    public void reset(Size newsize) {
        this.reset();
        ;
        this.setSize(newsize);
        this.init();
    }

    // Método para establecer el valor de un bloque en una posición específica
    public void setBlockValue(String value, int row, int col) {
        this.getBlocks()[col][row].setValue(value);
    }

    // Método para obtener el valor de un bloque en una posición específica
    public String getBlockValue(int row, int col) {
        return this.getBlocks()[row][col].getValue();
    }

    // getters y setters para el tamaño del laberinto
    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    // getters y setters para el tiempo del laberinto
    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    // getters y setters para el sonido del laberinto
    public String getSound() {
        return sound;
    }

    public void setSound(String sound) {
        this.sound = sound;
    }

    // getters y setters para los bloques del laberinto
    public Block[][] getBlocks() {
        return blocks;
    }

    public void setBlocks(Block[][] blocks) {
        this.blocks = blocks;
    }

    // Método para cargar un laberinto desde un archivo
    public static Maze load(File file)
            throws JAXBException, IOException, FileNotFoundException, ClassNotFoundException, Exception {
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        Maze m = null;
        if (extension.equals("xml")) {
            m = Maze.loadXML(file);
        } else {
            if (extension.equals("json")) {
                m = Maze.loadJSON(file);
            } else {
                if (extension.equals("bin")) {
                    m = Maze.loadBin(file);
                } else {
                    throw new Exception("Extensión " + extension + " no permitida");
                }
            }
        }
        return m;
    }

    // Método para guardar un laberinto en un archivo
    public static void save(Maze maze, File file) throws JAXBException, Exception {
        if (maze.sound == null || maze.sound.equals("")) {
            throw new Exception("Es necesario indicar el sonido del laberinto");
        }
        String extension = file.getName().substring(file.getName().lastIndexOf(".") + 1);
        if (extension.equals("xml")) {
            Maze.saveXML(maze, file);
        } else {
            if (extension.equals("json")) {
                Maze.saveJSON(maze, file);
            } else {
                if (extension.equals("bin")) {
                    Maze.saveBin(maze, file);
                } else {
                    throw new Exception("Extensión " + extension + " no permitida");
                }
            }
        }
    }

    // Método para cargar un laberinto desde un archivo JSON
    private static Maze loadJSON(File file) throws FileNotFoundException, IOException {
        Gson gson = new Gson();
        Reader reader = Files.newBufferedReader(file.toPath());
        Maze m = gson.fromJson(reader, Maze.class);
        reader.close();
        return m;
    }

    // Método para cargar un laberinto desde un archivo XML
    private static Maze loadXML(File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(Maze.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        Maze maze = (Maze) unmarshaller.unmarshal(file);
        return maze;
    }

    // Método para cargar un laberinto desde un archivo binario
    public static Maze loadBin(File file) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream os = new FileInputStream(file);
        ObjectInputStream oos = new ObjectInputStream(os);
        Maze m = (Maze) oos.readObject();
        oos.close();
        ;
        os.close();
        return m;
    }

    // Método para guardar un laberinto en un archivo JSON
    private static void saveJSON(Maze maze, File file) throws FileNotFoundException, UnsupportedEncodingException {
        Gson gson = new Gson();
        String json = gson.toJson(maze);
        PrintWriter pw = new PrintWriter(file, "UTF-8");
        pw.print(json);
        pw.close();
    }

    // Método para guardar un laberinto en un archivo XML
    private static void saveXML(Maze maze, File file) throws JAXBException, IOException {
        JAXBContext context = JAXBContext.newInstance(maze.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        FileWriter fw = new FileWriter(file, StandardCharsets.UTF_8);
        marshaller.marshal(maze, fw);
        fw.close();
    }

    // Método para guardar un laberinto en un archivo binario
    public static void saveBin(Maze maze, File file) throws FileNotFoundException, IOException {
        OutputStream os = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(maze);
        oos.close();
        ;
        os.close();
    }
}