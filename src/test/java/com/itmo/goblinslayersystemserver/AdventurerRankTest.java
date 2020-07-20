package com.itmo.goblinslayersystemserver;

import com.itmo.goblinslayersystemserver.models.enums.AdventurerRank;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AdventurerRankTest {
    @Test
    public void Should_GetCorrectLessOrEqualRanks_When_GetRanks() {
        // Given
        AdventurerRank givenRank = AdventurerRank.Sapphire;

        // When
        List<AdventurerRank> ranksTest = AdventurerRank.GetRanksThatLessOrEqual(givenRank);

        // Then
        assertFalse(ranksTest.isEmpty());
        assertEquals(4, ranksTest.size());
        assertTrue(ranksTest.contains(AdventurerRank.Porcelain));
        assertTrue(ranksTest.contains(AdventurerRank.Steel));
        assertTrue(ranksTest.contains(AdventurerRank.Obsidian));
        assertTrue(ranksTest.contains(AdventurerRank.Sapphire));
    }

    @Test
    public void Should_LessOrEqual_When_Less() {
        // Given
        AdventurerRank givenRank = AdventurerRank.Sapphire;
        AdventurerRank givenRankLess = AdventurerRank.Emerald;

        // When

        // Then
        assertTrue(givenRank.IsLessOrEqual(givenRankLess));
    }

    @Test
    public void Should_LessOrEqual_When_Equal() {
        // Given
        AdventurerRank givenRank = AdventurerRank.Sapphire;
        AdventurerRank givenRankEqual = AdventurerRank.Sapphire;

        // When

        // Then
        assertTrue(givenRank.IsLessOrEqual(givenRankEqual));
    }
}
