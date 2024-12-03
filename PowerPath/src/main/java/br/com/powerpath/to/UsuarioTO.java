package br.com.powerpath.to;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class UsuarioTO {
    @Positive
    private int idUsuario;
    @NotBlank
    private String senha;
    @Email
    private String email;
    @PositiveOrZero
    private Long pontos;
    @NotBlank
    private String nome;

    public UsuarioTO() {
    }

    public UsuarioTO(int idUsuario, String senha, String email, Long pontos, String nome) {
        this.idUsuario = idUsuario;
        this.senha = senha;
        this.email = email;
        this.pontos = pontos;
        this.nome = nome;
    }

    @Positive public int getIdUsuario() {return idUsuario;}
    public void setIdUsuario(@Positive int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public @NotBlank String getSenha() {
        return senha;
    }
    public void setSenha(@NotBlank String senha) {
        this.senha = senha;
    }
    public @PositiveOrZero Long getPontos() {
        return pontos;
    }
    public void setPontos(@PositiveOrZero Long pontos) {
        this.pontos = pontos;
    }
    public @Email String getEmail() {
        return email;
    }
    public void setEmail(@Email String email) {
        this.email = email;
    }
    public @NotBlank String getNome() {
        return nome;
    }
    public void setNome(@NotBlank String nome) {
        this.nome = nome;
    }
}
