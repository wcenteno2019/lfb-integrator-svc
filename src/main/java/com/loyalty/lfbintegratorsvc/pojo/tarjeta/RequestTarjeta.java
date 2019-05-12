package com.loyalty.lfbintegratorsvc.pojo.tarjeta;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cuentaTarjeta",
        "cuentaPersonal",
        "monto",
        "idUsuario"
})
public class RequestTarjeta {
        @JsonProperty("cuentaTarjeta")
        private String cuentaTarjeta;
        @JsonProperty("cuentaPersonal")
        private String cuentaPersonal;
        @JsonProperty("monto")
        private Double monto;
        @JsonProperty("idUsuario")
        private Integer idUsuario;

        /**
         * No args constructor for use in serialization
         *
         */
        public RequestTarjeta() {
        }

        /**
         *
         * @param cuentaTarjeta
         * @param idUsuario
         * @param cuentaPersonal
         * @param monto
         */
        public RequestTarjeta(String cuentaTarjeta, String cuentaPersonal, Double monto, Integer idUsuario) {
            super();
            this.cuentaTarjeta = cuentaTarjeta;
            this.cuentaPersonal = cuentaPersonal;
            this.monto = monto;
            this.idUsuario = idUsuario;
        }

    public String getCuentaTarjeta() {
        return cuentaTarjeta;
    }

    public void setCuentaTarjeta(String cuentaTarjeta) {
        this.cuentaTarjeta = cuentaTarjeta;
    }

    public String getCuentaPersonal() {
        return cuentaPersonal;
    }

    public void setCuentaPersonal(String cuentaPersonal) {
        this.cuentaPersonal = cuentaPersonal;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
