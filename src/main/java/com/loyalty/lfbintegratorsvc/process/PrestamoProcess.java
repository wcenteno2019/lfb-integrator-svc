package com.loyalty.lfbintegratorsvc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbintegratorsvc.builder.TransaccionesUrlBuilder;
import com.loyalty.lfbintegratorsvc.pojo.prestamos.RequestPrestamo;
import com.loyalty.lfbintegratorsvc.pojo.prestamos.ResponsePrestamo;
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

public class PrestamoProcess {
    private Environment env;
    private RestClient restClient;
    private Logger log;
    private ObjectMapper mapper =new ObjectMapper();

    public PrestamoProcess(Environment env, RestClient restClient){
        this.env = env;
        this.restClient= restClient;
        this.log = LoggerFactory.getLogger(getClass());
    }
    public ResponseEntity processPagoPrestamo(RequestPrestamo requestPrestamo, String autorizacion){
        ResponsePrestamo responsePrestamo;
        String code;
        try {
            TransaccionesUrlBuilder transaccionesUrlBuilderUrl = new TransaccionesUrlBuilder(env);
            ServiceRequestPost<ResponsePrestamo,RequestPrestamo> req = new ServiceRequestPost<>(restClient);
            responsePrestamo = req.RequestPost(transaccionesUrlBuilderUrl.getPagoPrestamoUrl(),new ParameterizedTypeReference<ResponsePrestamo>() {
            },requestPrestamo);
            log.info("response process pago prestamo: " + mapper.writeValueAsString(responsePrestamo));
            code = responsePrestamo.getStatus();
            if("200".equals(code)) {
                ResponsePrestamo response = new ResponsePrestamo();
                response.setNumAutorizacion(responsePrestamo.getNumAutorizacion());
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
