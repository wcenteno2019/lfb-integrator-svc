package com.loyalty.lfbintegratorsvc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbintegratorsvc.builder.TransaccionesUrlBuilder;
import com.loyalty.lfbintegratorsvc.pojo.tarjeta.RequestTarjeta;
import com.loyalty.lfbintegratorsvc.pojo.tarjeta.ResponseTarjeta;
import com.loyalty.lfbintegratorsvc.utility.RestClient;
import com.loyalty.lfbintegratorsvc.utility.ServiceRequestPost;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TarjetaProcess {
    private Environment env;
    private RestClient restClient;
    private Logger log;
    private ObjectMapper mapper =new ObjectMapper();

    public TarjetaProcess(Environment env, RestClient restClient){
        this.env = env;
        this.restClient= restClient;
        this.log = LoggerFactory.getLogger(getClass());
    }
    public ResponseEntity processPagoTarjeta(RequestTarjeta requestTarjeta, String autorizacion){
        ResponseTarjeta responseTarjeta;
        String code;
        try {
            TransaccionesUrlBuilder transaccionesUrlBuilderUrl = new TransaccionesUrlBuilder(env);
            ServiceRequestPost<ResponseTarjeta,RequestTarjeta> req = new ServiceRequestPost<>(restClient);
            responseTarjeta = req.RequestPost(transaccionesUrlBuilderUrl.getPagoTarjetaUrl(),new ParameterizedTypeReference<ResponseTarjeta>() {
            },requestTarjeta);
            log.info("response process pago tarjeta: " + mapper.writeValueAsString(responseTarjeta));
            code = responseTarjeta.getStatus();
            if("200".equals(code)) {
                ResponseTarjeta response = new ResponseTarjeta();
                response.setNumAutorizacion(responseTarjeta.getNumAutorizacion());
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            else if ("404".equals(code)){
                return new ResponseEntity<>("", HttpStatus.NOT_FOUND);
            }else if("409".equals(code)){
                return new ResponseEntity<>("", HttpStatus.CONFLICT);
            }else{
                return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
