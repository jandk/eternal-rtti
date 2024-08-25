package be.twofold.rtti.common.enums;

public enum EnumType {
    ENUM_S8(0, "int8_t"),
    ENUM_U8(1, "uint8_t"),
    ENUM_S16(2, "int16_t"),
    ENUM_U16(3, "uint16_t"),
    ENUM_S32(4, "int32_t"),
    ENUM_U32(5, "uint32_t"),
    ENUM_S64(6, "int64_t"),
    ENUM_U64(7, "uint64_t");

    private final int value;
    private final String type;

    EnumType(int value, String type) {
        this.value = value;
        this.type = type;
    }

    public int getValue() {
        return value;
    }

    public String getType() {
        return type;
    }

    public static EnumType fromValue(int value) {
        for (var type : EnumType.values()) {
            if (type.value == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid value: " + value);
    }
}
