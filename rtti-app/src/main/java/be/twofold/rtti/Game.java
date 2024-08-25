package be.twofold.rtti;

import be.twofold.rtti.colossus.*;
import be.twofold.rtti.common.*;
import be.twofold.rtti.common.utils.*;
import be.twofold.rtti.doom.*;
import be.twofold.rtti.eternal.*;

import java.util.*;
import java.util.function.*;

public enum Game {
    Colossus("abbee4822d7385cfae791d7be1e55c83f3d6c9965023afb85482351519bbe890", ColossusReader::new),
    Doom("ffd0ba84ad5f296603927a576ac5ce6c683a3a6d8fafc26b50b28db2082abc03", DoomReader::new),
    DoomEternal("bd4d2c95eaa09124a6c159738caf3dd9f53845e846048ecda02fb1661ae50539", EternalReader::new);

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
