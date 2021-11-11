package edu.bsu.cs;

import edu.bsu.cs.moves.MoveFormatter;
import edu.bsu.cs.moves.Move;
import edu.bsu.cs.moves.MoveBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MoveFormatterTest {

    @Test
    public void testBuildMoveHyperbeam(){
        MoveFormatter moveFormatter = new MoveFormatter();
        Move move1 = new Move.Builder().withName("Hyperbeam").withLevel(20).build();
        Assertions.assertEquals("20, Hyperbeam", moveFormatter.format(move1));
    }
    @Test
    public void testBuildMoveWaterCannon(){
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
                Fire punch
                Ice punch
                Thunder punch
                Headbutt
                Body slam
                Double edge
                Disable
                Thunderbolt
                Thunder wave
                Toxic
                Psychic
                Mimic
                Double team
                Confuse ray
                Defense curl
                Light screen
                Reflect
                Swift
                Dream eater
                Flash
                Rest
                Substitute
                Thief
                Nightmare
                Snore
                Protect
                Mud slap
                Destiny bond
                Icy wind
                Endure
                Swagger
                Mean look
                Attract
                Sleep talk
                Return
                Frustration
                Safeguard
                Pain split
                Encore
                Hidden power
                Rain dance
                Sunny day
                Psych up
                Shadow ball
                Torment
                Will o wisp
                Memento
                Facade
                Taunt
                Helping hand
                Trick
                Magic coat
                Recycle
                Skill swap
                Grudge
                Snatch
                Secret power
                Hyper voice
                Signal beam
                Calm mind
                Shock wave
                Natural gift
                Fling
                Shadow sneak
                Zen headbutt
                Trick room
                Captivate
                Grass knot
                Charge beam
                Wonder room
                Psyshock
                Telekinesis
                Magic room
                Synchronoise
                Round
                Stored power
                Draining kiss
                Misty terrain
                Confide
                """, result);
    }
}
