package be.twofold.eternalrtti;

import be.twofold.eternalrtti.rtti.classes.model.*;
import be.twofold.eternalrtti.rtti.enums.model.*;
import be.twofold.eternalrtti.rtti.typedefs.model.*;

import java.util.*;

public interface RTTIReader {

    List<EnumTypeInfo> readEnums();

    List<ClassTypeInfo> readClasses();

    List<TypedefInfo> readTypedefs();

}
