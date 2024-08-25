package be.twofold.rtti.common;

import be.twofold.rtti.common.classes.*;
import be.twofold.rtti.common.constants.*;
import be.twofold.rtti.common.enums.*;
import be.twofold.rtti.common.typedefs.*;

import java.util.*;

public interface RTTIReader {

    List<ConstantInfo> readConstants();

    List<EnumTypeInfo> readEnums();

    List<ClassTypeInfo> readClasses();

    List<TypedefInfo> readTypedefs();

}
