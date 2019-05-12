package com.loyalty.lfbintegratorsvc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbintegratorsvc.builder.TransaccionesUrlBuilder;
import com.loyalty.lfbintegratorsvc.pojo.transfer.RequestTransfer;
import com.loyalty.lfbintegratorsvc.utility.RestClient;
import com.loyalty.lfbintegratorsvc.utility.ServiceRequestPost;
import com.loyalty.lfbtransaccionalsvc.pojo.transfer.ResponseTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TransferProcess {
     private Environment env;
     private RestClient restClient;
     private Logger log;
     private ObjectMapper mapper =new ObjectMapper();

    public TransferProcess(Environment env, RestClient restClient){
        this.env = env;
        this.restClient= restClient;
        this.log = LoggerFactory.getLogger(getClass());
    }
    public ResponseEntity processTransfer(RequestTransfer requestTransfert, String autorizacion){
        ResponseTransfer responseTransfer;
        String code;
               try {
            TransaccionesUrlBuilder transaccionesUrlBuilderUrl = new TransaccionesUrlBuilder(env);
            ServiceRequestPost<ResponseTransfer,RequestTransfer> req = new ServiceRequestPost<>(restClient);
                   responseTransfer = req.RequestPost(transaccionesUrlBuilderUrl.getTransferUrl() , new ParameterizedTypeReference<ResponseTransfer>() {
            },requestTransfert);
            log.info("response process transfer: " + mapper.writeValueAsString(responseTransfer));
            code = responseTransfer.getStatus();
            if("200".equals(code)) {
                ResponseTransfer response = new ResponseTransfer();
                response.setNumAutorizacion(responseTransfer.getNumAutorizacion());
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
