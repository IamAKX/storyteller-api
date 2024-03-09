package com.storyteller.services;

import com.storyteller.dto.ResponseData;
import com.storyteller.entities.Subscription;
import com.storyteller.exceptions.EntityValidationException;
import com.storyteller.repositories.SubscriptionRepository;
import com.storyteller.utilities.ServiceHelper;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public ResponseData saveSubscription(Subscription subscription) {
        Optional<Subscription> existingSubscription = subscriptionRepository.findByTitle(subscription.getTitle());
        if (existingSubscription.isPresent()) {
            throw new EntityValidationException("Subscription", "This subscription is already created");
        }
        try {
            Subscription insertedSubscription = subscriptionRepository.save(subscription);
            return ResponseData.builder()
                    .message("Subscription created successfully")
                    .statusCode(HttpStatus.OK.value())
                    .data(insertedSubscription)
                    .build();
        } catch (ConstraintViolationException exception) {
            throw new EntityValidationException("Subscription", "This subscription is already created");
        }
    }

    public ResponseData getSubscriptionById(Long id) {
        Subscription existingSubscription = subscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        return ResponseData.builder()
                .message("Subscription found")
                .statusCode(HttpStatus.OK.value())
                .data(existingSubscription)
                .build();
    }

    public ResponseData getSubscriptionByName(String name) {
        List<Subscription> existingSubscription = subscriptionRepository.findByTitleContaining(name);
        return ResponseData.builder()
                .message("Subscription found")
                .statusCode(HttpStatus.OK.value())
                .data(existingSubscription)
                .build();
    }

    public ResponseData updateSubscription(Long id, Subscription updatedSubscription){
        Subscription existingSubscription = subscriptionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Subscription not found"));
        BeanUtils.copyProperties(updatedSubscription, existingSubscription, ServiceHelper.getNullPropertyNames(updatedSubscription));
        Subscription insertedSubscription = subscriptionRepository.save(existingSubscription);
        return ResponseData.builder()
                .message("Subscription updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(insertedSubscription)
                .build();
    }

    public ResponseData deleteSubscription(Long id){
        subscriptionRepository.deleteById(id);
        return ResponseData.builder()
                .message("Subscription deleted successfully")
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    public ResponseData getAllSubscription() {
        List<Subscription> subscriptionList = subscriptionRepository.findAll();
        return ResponseData.builder()
                .message("Fetched all subscription")
                .statusCode(HttpStatus.OK.value())
                .data(subscriptionList)
                .build();
    }
}
