package it.myevents.myevents.pojos;

public class Location {
    private int id;
    private String nome;
    private String via;
    private int civico;
    private int cap;
    private Citta citta;

    public Location(int id, String nome, String via, int civico, int cap, Citta citta) {
        this.id = id;
        this.nome = nome;
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.citta = citta;
    }

    public Location(String nome, String via, int civico, int cap, Citta citta) {
        this.nome = nome;
        this.via = via;
        this.civico = civico;
        this.cap = cap;
        this.citta = citta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public int getCivico() {
        return civico;
    }

    public void setCivico(int civico) {
        this.civico = civico;
    }

    public int getCap() {
        return cap;
    }

    public void setCap(int cap) {
        this.cap = cap;
    }

    public Citta getCitta() {
        return citta;
    }

    public void setCitta(Citta citta) {
        this.citta = citta;
    }

    @Override
    public String toString() {
        return "Location [nome=" + nome + ", via=" + via + ", civico=" + civico + ", citta=" + citta.getNome() + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + cap;
        result = prime * result + ((citta == null) ? 0 : citta.hashCode());
        result = prime * result + civico;
        result = prime * result + id;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((via == null) ? 0 : via.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Location other = (Location) obj;
        if (cap != other.cap)
            return false;
        if (citta == null) {
            if (other.citta != null)
                return false;
        } else if (!citta.equals(other.citta))
            return false;
        if (civico != other.civico)
            return false;
        if (id != other.id)
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (via == null) {
            if (other.via != null)
                return false;
        } else if (!via.equals(other.via))
            return false;
        return true;
    }

}