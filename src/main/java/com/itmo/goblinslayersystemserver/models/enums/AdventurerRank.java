package com.itmo.goblinslayersystemserver.models.enums;

import lombok.Getter;

public enum AdventurerRank {
     Platinum(10000),
     Gold(5000),
     Silver(1000),
     Bronze(500),
     Ruby(200),
     Emerald(100),
     Sapphire(50),
     Steel(10),
     Obsidian(5),
     Porcelain(0);

     /**
      * Требуемое количество опыта для ранга (столбец adventurer_experience в БД)
      */
     @Getter
     private final Integer experienceRequired;

     private AdventurerRank(Integer experienceRequired) {
          this.experienceRequired = experienceRequired;
     }
}
