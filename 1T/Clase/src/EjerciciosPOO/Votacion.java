//ejercicio 8

public class Votacion {
    private String candidato;
    private int votos;

    private static String nombreMasVotado = null;
    private static int votosMasVotado = 0;

    public Votacion(String candidato) {
        this.candidato = candidato;
        this.votos = 0;
    }

    public void votar() {
        this.votos++;
        if (this.votos > votosMasVotado) {
            votosMasVotado = this.votos;
            nombreMasVotado = this.candidato;
        }
    }

    public String getCandidato() { return candidato; }
    public int getVotos() { return votos; }

    public static String getNombreMasVotado() { return nombreMasVotado; }
    public static int getVotosMasVotado() { return votosMasVotado; }

    public static void main(String[] args) {
        System.out.println("Líder inicial: " + getNombreMasVotado());

        Votacion ana = new Votacion("Ana");
        Votacion bruno = new Votacion("Bruno");
        Votacion carla = new Votacion("Carla");

        Votacion[] votosSecuencia = {ana, bruno, bruno, carla, carla, ana, ana};

        for (Votacion v : votosSecuencia) {
            v.votar();
            System.out.printf("Voto para %-5s -> Ana: %d, Bruno: %d, Carla: %d | Líder: %s (%d votos)%n",
                    v.getCandidato(), ana.getVotos(), bruno.getVotos(), carla.getVotos(),
                    getNombreMasVotado(), getVotosMasVotado());
        }
    }
}

//si los votos fuera static seria global en vez de particular
//si votosMasVotado fuera un atributo de instancia cada candidato tendría su propio registro de "máximo", perdiendo la capacidad de comparar el resultado frente a los demás competidores