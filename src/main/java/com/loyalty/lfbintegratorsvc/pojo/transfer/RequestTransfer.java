package com.loyalty.lfbintegratorsvc.pojo.transfer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cuentaOrigen",
        "cuentaDestino",
        "monto",
        "idUsuario"
})
public class RequestTransfer {
        @JsonProperty("cuentaOrigen")
        private String cuentaOrigen;
        @JsonProperty("cuentaDestino")
        private String cuentaDestino;
        @JsonProperty("monto")
        private Double monto;
        @JsonProperty("idUsuario")
        private Integer idUsuario;

        /**
         * No args constructor for use in serialization
         *
         */
        public RequestTransfer() {
        }

        /**
         *
         * @param cuentaOrigen
         * @param idUsuario
         * @param cuentaDestino
         * @param monto
         */
        public RequestTransfer(String cuentaOrigen, String cuentaDestino, Double monto, Integer idUsuario) {
            super();
            this.cuentaOrigen = cuentaOrigen;
            this.cuentaDestino = cuentaDestino;
            this.monto = monto;
            this.idUsuario = idUsuario;
        }

        @JsonProperty("cuentaOrigen")
        public String getCuentaOrigen() {
            return cuentaOrigen;
        }

        @JsonProperty("cuentaOrigen")
        public void setCuentaOrigen(String cuentaOrigen) {
            this.cuentaOrigen = cuentaOrigen;
        }

        @JsonProperty("cuentaDestino")
        public String getCuentaDestino() {
            return cuentaDestino;
        }

        @JsonProperty("cuentaDestino")
        public void setCuentaDestino(String cuentaDestino) {
            this.cuentaDestino = cuentaDestino;
        }

        @JsonProperty("monto")
        public Double getMonto() {
            return monto;
        }

        @JsonProperty("monto")
        public void setMonto(Double monto) {
            this.monto = monto;
        }

        @JsonProperty("idUsuario")
        public Integer getIdUsuario() {
            return idUsuario;
        }

        @JsonProperty("idUsuario")
        public void setIdUsuario(Integer idUsuario) {
            this.idUsuario = idUsuario;
        }

    }
