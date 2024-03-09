package com.storyteller.controllers;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Subscription;
import com.storyteller.services.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(value = "/subscription")
public class SubscriptionController {

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping
    private ResponseEntity<ResponseData> createSubscription(@RequestBody Subscription subscription){
        return new ResponseEntity<>(subscriptionService.saveSubscription(subscription), HttpStatus.OK);
    }

    @GetMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> getSubscriptionById(@PathVariable Long id){
        return new ResponseEntity<>(subscriptionService.getSubscriptionById(id), HttpStatus.OK);
    }

    @GetMapping(value = { "/name/{name}" })
    private ResponseEntity<ResponseData> getSubscriptionByName(@PathVariable String name){
        return new ResponseEntity<>(subscriptionService.getSubscriptionByName(name), HttpStatus.OK);
    }

    @PutMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscription){
        return new ResponseEntity<>(subscriptionService.updateSubscription(id, subscription), HttpStatus.OK);
    }

    @DeleteMapping(value = { "/{id}" })
    private ResponseEntity<ResponseData> deleteSubscription(@PathVariable Long id){
        return new ResponseEntity<>(subscriptionService.deleteSubscription(id), HttpStatus.OK);
    }

    @GetMapping
    private ResponseEntity<ResponseData> getAllSubscription(){
        return new ResponseEntity<>(subscriptionService.getAllSubscription(), HttpStatus.OK);
    }
}
