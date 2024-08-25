package be.twofold.eternalrtti;

import be.twofold.rtti.common.*;
import be.twofold.rtti.common.utils.*;

import java.util.*;
import java.util.function.*;

public enum Game {
    Doom("ffd0ba84ad5f296603927a576ac5ce6c683a3a6d8fafc26b50b28db2082abc03", DoomReader::new),
    DoomEternal("7352dc4da0b107939c7d4d1e2c42ea087c566c26e79f8e01270678a0c61f6b39", EternalReader::new),
    NewColossus("abbee4822d7385cfae791d7be1e55c83f3d6c9965023afb85482351519bbe890", NewColossusReader::new);

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
