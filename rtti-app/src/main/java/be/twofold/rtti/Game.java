package be.twofold.rtti;

import be.twofold.rtti.colossus.*;
import be.twofold.rtti.common.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.darkages.*;
import be.twofold.rtti.doom.*;
import be.twofold.rtti.eternal.*;
import be.twofold.rtti.greatcircle.*;
import be.twofold.rtti.neworder.*;
import be.twofold.rtti.rage.*;

import java.util.*;
import java.util.function.*;

public enum Game {
    Colossus("68e6c21a97c2a0824ff75961a771605dacbd73f17e33e8e7265741535a3818c3", ColossusReader::new),
    DarkAges("40c750c04bbbff8b58219de4d78030d91af4a57fb1a965da8c629eea7c3b0024", DarkAgesReader::new),
    Doom("1bddecaa4e86850412f96e3117978bce1ec7bb701ae1b5a33120c21e4421e51e", DoomReader::new),
    DoomVk("3d8b4393649f80bdae85c19ae2c985c45ec9ac54f74ab04a18816e30ead63c21", DoomVkReader::new),
    Eternal("514af5872cec7d7e48f58099456f1404f1ca3102a76e8f93af4eaf5914b9b442", EternalReader::new),
    GreatCircle("68ef9be325743e304062728f667fdc6b3fba42243f9065a9a6b48b1b23418311", GreatCircleReader::new),
    NewOrder("9e413197e097ba41e85f0de47ddd3918f7656c432f8431f5f15e4b7eeb7c371c", NewOrderReader::new),
    Rage("54c80b45833679c62557a3f901aa36887f703729c28ba25acd1c9a1d606d7990", RageReader::new),
    ;

    private final String hash;
    private final Function<PeWrapper, RTTIReader> reader;

    Game(String hash, Function<PeWrapper, RTTIReader> reader) {
        this.hash = hash;
        this.reader = reader;
    }

    public String hash() {
        return hash;
    }

    public Function<PeWrapper, RTTIReader> reader() {
        return reader;
    }

    public static Game fromHash(String hash) {
        return Arrays.stream(values())
            .filter(game -> game.hash.equals(hash))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("Invalid file hash: " + hash));
    }
}
