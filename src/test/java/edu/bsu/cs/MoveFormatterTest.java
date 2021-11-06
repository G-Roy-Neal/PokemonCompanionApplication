package edu.bsu.cs;

import edu.bsu.cs.moves.MoveFormatter;
import edu.bsu.cs.moves.PokemonMove;
import edu.bsu.cs.moves.PokemonMoveBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

public class MoveFormatterTest {

    @Test
    public void testBuildMoveHyperbeam(){
        MoveFormatter moveFormatter = new MoveFormatter();
        PokemonMove move1 = new PokemonMove.Builder().withName("Hyperbeam").withLevel(20).build();
        Assertions.assertEquals("20, Hyperbeam", moveFormatter.format(move1));
    }
    @Test
    public void testBuildMoveWaterCannon(){
        MoveFormatter moveFormatter = new MoveFormatter();
        PokemonMove move2 = new PokemonMove.Builder().withName("Water Cannon").withLevel(0).build();
        Assertions.assertEquals("Water Cannon", moveFormatter.format(move2));
    }

    @Test
    public void testBuildFormattedMoves() throws IOException {
        InputStream testingData = Thread.currentThread().getContextClassLoader().getResourceAsStream("ralts-test.json");
        assert testingData != null;
        PokemonMoveBuilder moveBuilder = new PokemonMoveBuilder(testingData);
        MoveFormatter moveFormatter = new MoveFormatter();
        String result = moveFormatter.buildFormattedMoves(moveBuilder.buildMoves());
        Assertions.assertEquals("""
                Moves that can be Learned:
                1, Growl
                4, Confusion
                37, Hypnosis
                9, Teleport
                34, Charm
                32, Future sight
                29, Imprison
                17, Magical leaf
                14, Lucky chant
                42, Echoed voice
                19, Ally switch
                11, Heal pulse
                22, Disarming voice
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
