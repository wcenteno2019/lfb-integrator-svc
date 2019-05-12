package com.loyalty.lfbintegratorsvc.pojo.prestamos;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cuentaPrestamo",
        "cuentaPersonal",
        "monto",
        "idUsuario"
})
public class RequestPrestamo {
        @JsonProperty("cuentaPrestamo")
        private String cuentaPrestamo;
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
        public RequestPrestamo() {
        }

        /**
         *
         * @param cuentaPrestamo
         * @param idUsuario
         * @param cuentaPersonal
         * @param monto
         */
        public RequestPrestamo(String cuentaPrestamo, String cuentaPersonal, Double monto, Integer idUsuario) {
            super();
            this.cuentaPrestamo = cuentaPrestamo;
            this.cuentaPersonal = cuentaPersonal;
            this.monto = monto;
            this.idUsuario = idUsuario;
        }

    public String getCuentaPrestamo() {
        return cuentaPrestamo;
    }

    public void setCuentaPrestamo(String cuentaPrestamo) {
        this.cuentaPrestamo = cuentaPrestamo;
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
