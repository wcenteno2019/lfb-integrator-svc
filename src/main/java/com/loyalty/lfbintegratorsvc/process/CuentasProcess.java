package com.loyalty.lfbintegratorsvc.process;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loyalty.lfbintegratorsvc.builder.TransaccionesUrlBuilder;
import com.loyalty.lfbintegratorsvc.pojo.response.Cuenta;
import com.loyalty.lfbintegratorsvc.pojo.response.CuentaResponse;
import com.loyalty.lfbintegratorsvc.pojo.response.web.*;
import com.loyalty.lfbintegratorsvc.utility.RestClient;
import com.loyalty.lfbintegratorsvc.utility.ServiceRequestGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class CuentasProcess {
    private Environment env;
    private Logger log;
    private RestClient restClient;
    ObjectMapper mapper = new ObjectMapper();
    public CuentasProcess(Environment env, RestClient restClient){
        this.env= env;
        this.restClient = restClient;
        this.log = LoggerFactory.getLogger(getClass());
    }
    public ResponseEntity process(String idUsuario,String authorization){
        CuentaResponse cuentaResponse= new CuentaResponse();
        int code;
        try {
            TransaccionesUrlBuilder transaccionesUrlBuilderUrl = new TransaccionesUrlBuilder(env);
            ServiceRequestGet<CuentaResponse> req = new ServiceRequestGet<>(restClient);
            cuentaResponse = req.requestGet(transaccionesUrlBuilderUrl.getAccountsUrl() + "/" + idUsuario, new ParameterizedTypeReference<CuentaResponse>() {
            });
            log.info("response process obtener cuentas: " + mapper.writeValueAsString(cuentaResponse));
            code = cuentaResponse.getStatus();
            if(code == 200) {
                ResponseCuentas responseCuentas= getCuentas(cuentaResponse);
                return new ResponseEntity<>(responseCuentas, HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e){
            return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseCuentas getCuentas(CuentaResponse cuentaResponse){
        ResponseCuentas responseCuentas= new ResponseCuentas();
        Accounts accounts = new Accounts();
        List<CreditCard> lstCreditCar = new ArrayList<>();
        List<Personal> lstPersonal = new ArrayList<>();
        List<Loan> lstLoan = new ArrayList<>();
        for(Cuenta cue : cuentaResponse.getCuenta()){
            if(cue.getTipo() == 1){
                Personal personal = new Personal();
                personal.setId(String.valueOf(cue.getId()));
                personal.setName(cue.getName());
                lstPersonal.add(personal);
            }else if(cue.getTipo() == 2){
                CreditCard creditCard = new CreditCard();
                creditCard.setId(String.valueOf(cue.getId()));
                creditCard.setName(cue.getName());
                lstCreditCar.add(creditCard);
            }else if(cue.getTipo()== 3){
                Loan loan = new Loan();
                loan.setId(String.valueOf(cue.getId()));
                loan.setName(cue.getName());
                lstLoan.add(loan);
            }else{
                continue;
            }
        }
        accounts.setLoan(lstLoan);
        accounts.setCreditCard(lstCreditCar);
        accounts.setPersonal(lstPersonal);
        responseCuentas.setAccounts(accounts);
        return responseCuentas;
    }
}
