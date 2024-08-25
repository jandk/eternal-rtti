package be.twofold.eternalrtti.rtti.base;

public interface SpecifierFlag {
    String name();

    default String shortName() {
        return name().substring("SPECIFIERFLAG_".length());
    }
}
