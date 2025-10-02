package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class ActivationController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody ActivationRequest request) {
        String actuatorUrl = "http://localhost:8444/actuate";

        // Forward only ICCID
        ActuatorRequest actuatorRequest = new ActuatorRequest(request.getIccid());

        ActuatorResponse response = restTemplate.postForObject(
                actuatorUrl, actuatorRequest, ActuatorResponse.class);

        if (response != null && response.isSuccess()) {
            System.out.println("SIM " + request.getIccid() + " activated successfully for " + request.getCustomerEmail());
            return ResponseEntity.ok("Activation successful");
        } else {
            System.out.println("SIM " + request.getIccid() + " activation failed for " + request.getCustomerEmail());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Activation failed");
        }
    }
}
