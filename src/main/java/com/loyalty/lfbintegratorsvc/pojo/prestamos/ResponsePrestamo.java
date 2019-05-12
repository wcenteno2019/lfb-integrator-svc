package com.loyalty.lfbintegratorsvc.pojo.prestamos;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "status",
        "message",
        "numAutorizacion"
})
public class ResponsePrestamo {

        @JsonProperty("status")
        private String status;
        @JsonProperty("message")
        private String message;
        @JsonProperty("numAutorizacion")
        private String numAutorizacion;


        /**
         * No args constructor for use in serialization
         *
         */
        public ResponsePrestamo() {
        }

        /**
         *
         * @param message
         * @param status
         * @param numAutorizacion
         */
        public ResponsePrestamo(String status, String message, String numAutorizacion) {
            super();
            this.status = status;
            this.numAutorizacion = numAutorizacion;
        }

        @JsonProperty("status")
        public String getStatus() {
            return status;
        }

        @JsonProperty("status")
        public void setStatus(String status) {
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
    @JsonProperty("numAutorizacion")
    public String getNumAutorizacion() {
        return numAutorizacion;
    }
    @JsonProperty("numAutorizacion")
    public void setNumAutorizacion(String numAutorizacion) {
        this.numAutorizacion = numAutorizacion;
    }
}
