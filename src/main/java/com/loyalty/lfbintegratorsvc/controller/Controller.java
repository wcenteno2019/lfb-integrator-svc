package com.loyalty.lfbintegratorsvc.controller;

import com.loyalty.lfbintegratorsvc.pojo.prestamos.RequestPrestamo;
import com.loyalty.lfbintegratorsvc.pojo.tarjeta.RequestTarjeta;
import com.loyalty.lfbintegratorsvc.pojo.transfer.RequestTransfer;
import com.loyalty.lfbintegratorsvc.process.CuentasProcess;
import com.loyalty.lfbintegratorsvc.process.PrestamoProcess;
import com.loyalty.lfbintegratorsvc.process.TarjetaProcess;
import com.loyalty.lfbintegratorsvc.process.TransferProcess;
import com.loyalty.lfbintegratorsvc.utility.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/lfb/integrator")
public class Controller {
    @Autowired
    Environment env;
    @Autowired
    RestClient restClient;
    @GetMapping("/getAllAccount/{idUsuario}")
    public ResponseEntity getAllAccount(@PathVariable("idUsuario") String idUsuario, @RequestHeader("authorization") String authorization){
        CuentasProcess cuentasProcess = new CuentasProcess(env, restClient);

        return cuentasProcess.process(idUsuario, authorization);
    }
    @PostMapping("/transfer")
    public ResponseEntity tranfer(@RequestBody RequestTransfer requestTransfer, @RequestHeader("authorization") String authorization){
        TransferProcess transferProcess = new TransferProcess(env,restClient);
        return transferProcess.processTransfer(requestTransfer,authorization);
    }
    @PostMapping("/pago-tarjeta")
    public ResponseEntity pagoTarjeta(@RequestBody RequestTarjeta requestTarjeta, @RequestHeader("authorization") String authorization){
        TarjetaProcess tarjetaProcess = new TarjetaProcess(env,restClient);
        return tarjetaProcess.processPagoTarjeta(requestTarjeta,authorization);
    }

    @PostMapping("/pago-prestamo")
    public ResponseEntity pagoPrestamo(@RequestBody RequestPrestamo requestPrestamo, @RequestHeader("authorization") String authorization){
        PrestamoProcess tarjetaProcess = new PrestamoProcess(env,restClient);
        return tarjetaProcess.processPagoPrestamo(requestPrestamo,authorization);
    }
}
