package domain;

import java.util.Date;

public class Auto {
    private Integer idAuto;
    private String modelo;
    private String marca;
    private Date ano;
    private Double precio;

    public Auto() {
    }

    public Auto(Integer idAuto, String modelo, String marca, Date ano, Double precio) {
        this.idAuto = idAuto;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.precio = precio;
    }

    public Integer getIdAuto() {
        return idAuto;
    }

    public void setIdAuto(Integer idAuto) {
        this.idAuto = idAuto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Date getAno() {
        return ano;
    }

    public void setAno(Date ano) {
        this.ano = ano;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "idAuto=" + idAuto +
                ", modelo='" + modelo + '\'' +
                ", marca='" + marca + '\'' +
                ", ano=" + ano +
                ", precio=" + precio +
                '}';
    }
}
