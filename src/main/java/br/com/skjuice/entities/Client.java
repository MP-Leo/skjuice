package br.com.skjuice.entities;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "tb_client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    // CONSTRUCTORS
    public Client() {
    }

    public Client(String nome) {
        this.nome = nome;
    }

    // HASH CODE AND EQUALS
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(nome, client.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }


    // GETTERS AND SETTERS
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
