package Modelo.Clases;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Persona {

    private String nombre;
    private String apellido1;
    private String apellido2;
    private int edad;
    private int telefono;
    private String correoElectronico;
    private String direccion;
    private String dni;
    private static final String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    public Persona(String nombre, String apellido1, String apellido2, int edad, int telefono, String correoElectronico, String direccion, String dni) {
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.edad = edad;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean esPalabraCaracteres(String palabra) {
        for (int i = 0; i < palabra.length(); i++) {
            if (!((palabra.charAt(i) > 64 && palabra.charAt(i) < 91)
                    || (palabra.charAt(i) > 96 && palabra.charAt(i) < 123))) {
                return false;
            }
        }
        return true;
    }

    public boolean comprobarEmail(String email) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public boolean compruebaEdad(int edad) {
        if (edad > 0 && edad < 99) {
            return true;
        } else {
            return false;
        }
    }

    public boolean compruebaNumeroTelefono(String numero) {
        boolean comprobacion = false;
        if (numero.length() == 9) {
            if (numero.substring(0, 1).equals("6") || numero.substring(0, 1).equals("9")) {
                return comprobacion = true;
            }
        }
        return comprobacion;
    }

    public boolean comprobarDni(String nif) {
        boolean comprobacion = false;
        Pattern pattern = Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            String letra = matcher.group(2);
            String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
            int index = Integer.parseInt(matcher.group(1));
            index = index % 23;
            String reference = letras.substring(index, index + 1);

            if (reference.equalsIgnoreCase(letra)) {
                comprobacion = true;
            } else {
                comprobacion = false;
            }
        } else {
            comprobacion = false;
        }
        return comprobacion;
    }
}
