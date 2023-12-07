package com.demo.gameofcricket.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "players")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Player
{
   private String id;

   private String name;

   private int score;
}
