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
    Colossus("abbee4822d7385cfae791d7be1e55c83f3d6c9965023afb85482351519bbe890", ColossusReader::new),
    DarkAges("4f425c098bcea6bd95d08c01801715e7df7ff9e81a1629952f36dfc8fb444bb3", DarkAgesReader::new),
    Doom("1bddecaa4e86850412f96e3117978bce1ec7bb701ae1b5a33120c21e4421e51e", DoomReader::new),
    DoomVk("3d8b4393649f80bdae85c19ae2c985c45ec9ac54f74ab04a18816e30ead63c21", DoomVkReader::new),
    Eternal("514af5872cec7d7e48f58099456f1404f1ca3102a76e8f93af4eaf5914b9b442", EternalReader::new),
    GreatCircle("3d909d1ec8149c65f4734a1219285e3ec2e446bc1425f7e032a50b148ba012b9", GreatCircleReader::new),
    NewOrder("1dd79eedc2acf388dcc6d33ff7c3793533a22a9d9a652f6e26de0cfac2253896", NewOrderReader::new),
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
