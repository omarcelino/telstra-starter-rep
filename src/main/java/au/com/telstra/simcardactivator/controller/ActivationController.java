package au.com.telstra.simcardactivator.controller;

import au.com.telstra.simcardactivator.dto.SimCardResponse;
import au.com.telstra.simcardactivator.model.*;
import au.com.telstra.simcardactivator.repository.SimCardRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@RestController
public class ActivationController {

    private static final Logger log = LoggerFactory.getLogger(ActivationController.class);

    private final RestTemplate restTemplate;

    private final SimCardRecordRepository repository;

    public ActivationController(RestTemplate restTemplate, SimCardRecordRepository repository) {
        this.restTemplate = restTemplate;
        this.repository = repository;
    }

    @PostMapping("/activate")
    public ResponseEntity<String> activateSim(@RequestBody SimCard request) {
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

    @GetMapping("/query")
    public Object getSimRecord(@RequestParam Long simCardId) {
        Optional<SimCardRecord> result = repository.findById(simCardId);

        return result.map(r -> new SimCardResponse(r.getIccid(), r.getCustomerEmail(), r.isActive()))
                .orElse(null); // come back here and change this to "Not found"
    }

}
