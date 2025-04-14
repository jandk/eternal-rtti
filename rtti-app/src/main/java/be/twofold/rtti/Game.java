package be.twofold.rtti;

import be.twofold.rtti.colossus.*;
import be.twofold.rtti.common.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.doom.*;
import be.twofold.rtti.eternal.*;
import be.twofold.rtti.greatcircle.*;
import be.twofold.rtti.neworder.*;

import java.util.*;
import java.util.function.*;

public enum Game {
    Colossus("abbee4822d7385cfae791d7be1e55c83f3d6c9965023afb85482351519bbe890", ColossusReader::new),
    Doom("1bddecaa4e86850412f96e3117978bce1ec7bb701ae1b5a33120c21e4421e51e", DoomReader::new),
    DoomVk("3d8b4393649f80bdae85c19ae2c985c45ec9ac54f74ab04a18816e30ead63c21", DoomVkReader::new),
    Eternal("bd4d2c95eaa09124a6c159738caf3dd9f53845e846048ecda02fb1661ae50539", EternalReader::new),
    GreatCircle("11f5a0de39822c977c536c44fb716ed38c750462016c59864153399f891831c9", GreatCircleReader::new),
    NewOrder("1dd79eedc2acf388dcc6d33ff7c3793533a22a9d9a652f6e26de0cfac2253896", NewOrderReader::new);

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
