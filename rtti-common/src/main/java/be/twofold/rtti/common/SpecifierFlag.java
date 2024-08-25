package be.twofold.rtti.common;

public interface SpecifierFlag {
    String name();

    default String shortName() {
        return name().substring("SPECIFIERFLAG_".length());
    }
}
