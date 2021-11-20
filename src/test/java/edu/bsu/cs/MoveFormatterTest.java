package edu.bsu.cs;

import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import edu.bsu.cs.moves.MoveFormatter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MoveFormatterTest {

    @Test
    public void testBuildMoveHyperbeam() {
        MoveFormatter moveFormatter = new MoveFormatter();
        Move move1 = new Move.Builder().withName("Hyperbeam").withLevel(20).build();
        Assertions.assertEquals("20, Hyperbeam", moveFormatter.format(move1));
    }

    @Test
    public void testBuildMoveWaterCannon() {
        MoveFormatter moveFormatter = new MoveFormatter();
        Move move2 = new Move.Builder().withName("Water Cannon").withLevel(0).build();
        Assertions.assertEquals("Water Cannon", moveFormatter.format(move2));
    }

    @Test
    public void testBuildFormattedMoves() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        MoveBuilder moveBuilder = new MoveBuilder(testingData);
        MoveFormatter moveFormatter = new MoveFormatter();
        String result = moveFormatter.buildFormattedMoves(moveBuilder.buildMoves());
        Assertions.assertEquals("""
                Moves that can be Learned:
                1, Growl
                4, Confusion
                9, Teleport
                11, Heal pulse
                14, Lucky chant
                17, Magical leaf
                19, Ally switch
                22, Disarming voice
                29, Imprison
                32, Future sight
                34, Charm
                37, Hypnosis
                42, Echoed voice
                               
                Moves that can be Taught:
                Attract
                Body slam
                Calm mind
                Captivate
                Charge beam
                Confide
                Confuse ray
                Defense curl
                Destiny bond
                Disable
                Double edge
                Double team
                Draining kiss
                Dream eater
                Encore
                Endure
                Facade
                Fire punch
                Flash
                Fling
                Frustration
                Grass knot
                Grudge
                Headbutt
                Helping hand
                Hidden power
                Hyper voice
                Ice punch
                Icy wind
                Light screen
                Magic coat
                Magic room
                Mean look
                Memento
                Mimic
                Misty terrain
                Mud slap
                Natural gift
                Nightmare
                Pain split
                Protect
                Psych up
                Psychic
                Psyshock
                Rain dance
                Recycle
                Reflect
                Rest
                Return
                Round
                Safeguard
                Secret power
                Shadow ball
                Shadow sneak
                Shock wave
                Signal beam
                Skill swap
                Sleep talk
                Snatch
                Snore
                Stored power
                Substitute
                Sunny day
                Swagger
                Swift
                Synchronoise
                Taunt
                Telekinesis
                Thief
                Thunder punch
                Thunder wave
                Thunderbolt
                Torment
                Toxic
                Trick
                Trick room
                Will o wisp
                Wonder room
                Zen headbutt
                """, result);
    }
}
