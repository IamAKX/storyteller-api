package com.storyteller.entities;

import com.storyteller.enums.ChatSender;
import com.storyteller.enums.MessageType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name = "StoryChat")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryChat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id", insertable=false, updatable=false)
    private Story story;

    private String text;

    private String mediaUrl;

    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    private String reactionType;

    @Builder.Default
    private boolean reactionEnabled = false;

    @Enumerated(EnumType.STRING)
    private ChatSender sender;

    private Date chatTimestamp;
}
