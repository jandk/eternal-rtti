package be.twofold.rtti.common.enums;

public record EnumValueInfo(
    String name,
    long value,
    String comment,
    long nameHash
) {
    public EnumValueInfo(String name, long value, String comment) {
        this(name, value, comment, 0L);
    }
}
