package com.DanCreate.securityTesting.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Token {
    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEADER;
    public boolean revoked;
    public boolean expired;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;
}
