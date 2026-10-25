public class Alumno {
    private String id;
    private String nombre;
    private String apellidos;
    private String ciclo;
    private double nota;

    // Getters y Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCiclo() { return ciclo; }
    public void setCiclo(String ciclo) { this.ciclo = ciclo; }

    public double getNota() { return nota; }
    public void setNota(double nota) { this.nota = nota; }
}