package com.loyalty.lfbintegratorsvc.builder;

import org.springframework.core.env.Environment;

public class TransaccionesUrlBuilder {
    private Environment env;
    private static final String PLECA = "/";

    public enum EntidadTransaciones{
        PAGO_TARJETA("services.transacciones.pago-tarjeta"),
        PAGO_PRESTAMO("services.transacciones.pago-prestamo"),
        TRANSFER("services.transacciones.transfer"),
        GET_ACCOUNTS("services.transacciones.get-all-accounts");
        private final String configuredUrlKey;
        EntidadTransaciones(String configuredUrlKey){
            this.configuredUrlKey = configuredUrlKey;
        }
        String getConfiguredUrlKey(){
            return this.configuredUrlKey;
        }
    };

    public enum EstadoSusbsription{
        ACTIVE("A","Active");
        private final String codigo;
        private final String descripcion;
        EstadoSusbsription(String codigo, String descripcion){this.codigo = codigo; this.descripcion = descripcion;}
        String getCodigo(){ return this.codigo; };
    }

    public TransaccionesUrlBuilder(Environment env) {
        this.env = env;
    }

    public String getAccountsUrl(){
        String url = env.getProperty(EntidadTransaciones.GET_ACCOUNTS.getConfiguredUrlKey());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        return stringBuilder.toString();
    }

    public String getTransferUrl(){
        String url = env.getProperty(EntidadTransaciones.TRANSFER.getConfiguredUrlKey());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        return stringBuilder.toString();
    }

    public String getPagoTarjetaUrl(){
        String url = env.getProperty(EntidadTransaciones.PAGO_TARJETA.getConfiguredUrlKey());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        return stringBuilder.toString();
    }

    public String getPagoPrestamoUrl(){
        String url = env.getProperty(EntidadTransaciones.PAGO_PRESTAMO.getConfiguredUrlKey());
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(url);
        return stringBuilder.toString();
    }

}