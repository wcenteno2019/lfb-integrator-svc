package com.loyalty.lfbintegratorsvc.pojo.response;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.ArrayList;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "cuenta",
        "status",
        "message"
})
public class CuentaResponse {

        @JsonProperty("cuenta")
        private ArrayList<Cuenta> cuenta = null;
        @JsonProperty("status")
        private int status;
        @JsonProperty("message")
        private String message;

        /**
         * No args constructor for use in serialization
         *
         */

        /**
         *
         * @param status
         * @param cuenta
         */
        public CuentaResponse(ArrayList<Cuenta> cuenta, int status, String message) {
            super();
            this.cuenta = cuenta;
            this.status = status;
            this.message = message;
        }
    public CuentaResponse() {

    }

        @JsonProperty("cuenta")
        public ArrayList<Cuenta> getCuenta() {
            return cuenta;
        }

        @JsonProperty("cuenta")
        public void setCuenta(ArrayList<Cuenta> cuenta) {
            this.cuenta = cuenta;
        }

        @JsonProperty("status")
        public int getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(int status) {
            this.status = status;
        }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }
    @JsonProperty("message")
    public void setMessage(String message) {
        this.message = message;
    }
}
