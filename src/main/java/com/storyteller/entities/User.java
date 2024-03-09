package com.storyteller.entities;


import com.storyteller.enums.SubscribtionStatus;
import com.storyteller.enums.UserStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "User")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Column(unique = true)
    @Size(max = 10)
    private String mobile;

    @Column(unique = true)
    @Email
    private String email;

    private String image;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private UserStatus status = UserStatus.ACTIVE;

    private Date subscribedOn;

    private Date subscriptionEndsOn;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SubscribtionStatus subscribtionStatus = SubscribtionStatus.NOT_SUBSCRIBED;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subscription_id")
    private Subscription subscription;

    private String bio;

    private Date createdOn;

    private Date updatedOn;

    @PrePersist
    protected void onCreate(){
        createdOn = new Date();
        updatedOn = createdOn;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedOn = new Date();
    }
}
