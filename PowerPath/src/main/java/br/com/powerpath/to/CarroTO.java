package br.com.powerpath.to;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;


public class CarroTO {
    @Positive
    private int idCarro;
    @Positive
    private int idUsuario;
    @NotBlank
    private String modelo;
    @NotBlank
    private String marca;
    @NotBlank
    private String tipo;
    @PositiveOrZero
    private Long quantidadeCarbono;
    @PastOrPresent
    private int ano;
    @PositiveOrZero
    private Long recarga;

    public CarroTO() {
    }

    public CarroTO(int idCarro, int idUsuario, String modelo, String marca, String tipo, int ano, Long quantidadeCarbono, Long recarga) {
        this.idCarro = idCarro;
        this.idUsuario = idUsuario;
        this.modelo = modelo;
        this.marca = marca;
        this.tipo = tipo;
        this.ano = ano;
        this.quantidadeCarbono = quantidadeCarbono;
        this.recarga = recarga;
    }

    @Positive
    public int getIdCarro() {
        return idCarro;
    }
    public void setIdCarro(@Positive int idCarro) {
        this.idCarro = idCarro;
    }
    @Positive
    public int getIdUsuario() {
        return idUsuario;
    }
    public void setIdUsuario(@Positive int idUsuario) {
        this.idUsuario = idUsuario;
    }
    public @NotBlank String getModelo() {
        return modelo;
    }
    public void setModelo(@NotBlank String modelo) {
        this.modelo = modelo;
    }
    public @NotBlank String getMarca() {
        return marca;
    }
    public void setMarca(@NotBlank String marca) {
        this.marca = marca;
    }
    public @NotBlank String getTipo() {
        return tipo;
    }
    public void setTipo(@NotBlank String tipo) {
        this.tipo = tipo;
    }
    public @PastOrPresent int getAno() {
        return ano;
    }
    public void setAno(@PastOrPresent int ano) {
        this.ano = ano;
    }
    public Long getQuantidadeCarbono() {
        return quantidadeCarbono != null ? quantidadeCarbono : 0L;
    }
    public void setQuantidadeCarbono(@PositiveOrZero Long quantidadeCarbono) {
        this.quantidadeCarbono = quantidadeCarbono;
    }
    public @PositiveOrZero Long getRecarga() {
        return recarga;
    }
    public void setRecarga(@PositiveOrZero Long recarga) {
        this.recarga = recarga;
    }

    public void atualizarQuantidadeCarbonoParaHibrido() {
        if (this.tipo.equalsIgnoreCase("hibrido")) {
            this.quantidadeCarbono /= 3;
        }
    }

    public void calcularCarbonoComRecarga() {
        if (this.recarga != null) {
            if (this.quantidadeCarbono == null) {
                this.quantidadeCarbono = 0L;
            }
            this.quantidadeCarbono += this.recarga * 650;
        }
    }


}
