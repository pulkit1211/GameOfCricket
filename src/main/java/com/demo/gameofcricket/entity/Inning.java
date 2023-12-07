package com.demo.gameofcricket.entity;

import com.demo.gameofcricket.enums.MatchStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "innings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inning
{
    @Id
    private String id;
    private String teamName;
    private int totalScore;
    private int wickets;

    private MatchStatus matchStatus;
}
