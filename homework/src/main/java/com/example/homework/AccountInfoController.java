package com.example.homework;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class AccountInfoController {

    @PostMapping("/getAccountInfo")
    public ResponseEntity<Map<String, String>> getAccountInfo(@RequestBody Map<String, String> request) {
        String clientId = request.get("clientId");
        String account = request.get("account");
        String rqUID = request.get("rqUID");
        String openDate = request.get("openDate");
        String closeDate = request.get("closeDate");

        String currency;
        String maxLimit;
        double balance;

        if (clientId.startsWith("8")) {
            currency = "US";
            maxLimit = "2000.00";
        } else if (clientId.startsWith("9")) {
            currency = "EU";
            maxLimit = "1000.00";
        } else {
            currency = "RUB";
            maxLimit = "10000.00";
        }

        balance = new Random().nextDouble() * Double.parseDouble(maxLimit);

        String balanceFormatted = String.format("%.2f", balance);

        Map<String, String> response = new HashMap<>();
        response.put("rqUID", rqUID);
        response.put("clientId", clientId);
        response.put("account", account);
        response.put("currency", currency);
        response.put("balance", balanceFormatted);
        response.put("maxLimit", maxLimit);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
