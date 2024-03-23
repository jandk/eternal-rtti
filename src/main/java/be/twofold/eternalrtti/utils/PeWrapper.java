package be.twofold.eternalrtti.utils;

import com.kichik.pecoff4j.*;

import java.nio.*;
import java.util.*;

public final class PeWrapper {
    private final ByteBuffer rdataBuffer;
    private final long rdataSectionOffset;

    private final ByteBuffer dataBuffer;
    private final long dataSectionOffset;

    public PeWrapper(PE pe, byte[] dataSection) {
        var sectionTable = pe.getSectionTable();

        // 0x7ff6da360000L
        rdataBuffer = ByteBuffer.wrap(sectionTable.findSection(".rdata").getData()).order(ByteOrder.LITTLE_ENDIAN);
        rdataSectionOffset = pe.getOptionalHeader().getImageBase() + sectionTable.findHeader(".rdata").getVirtualAddress();

        if (dataSection == null) {
            dataBuffer = ByteBuffer.wrap(sectionTable.findSection(".data").getData()).order(ByteOrder.LITTLE_ENDIAN);
        } else {
            dataBuffer = ByteBuffer.wrap(dataSection).order(ByteOrder.LITTLE_ENDIAN);
        }
        dataSectionOffset = pe.getOptionalHeader().getImageBase() + sectionTable.findHeader(".data").getVirtualAddress();
    }

    public ByteBuffer getBuffer(long offset) {
        return getBufferOptional(offset).orElseThrow(
            () -> new IllegalArgumentException("Offset out of bounds: " + offset)
        );
    }

    public Optional<ByteBuffer> getBufferOptional(long offset) {
        if (offset > dataSectionOffset && offset < dataSectionOffset + dataBuffer.capacity()) {
            return Optional.of(dataBuffer.position((int) (offset - dataSectionOffset)));
        }
        if (offset > rdataSectionOffset && offset < rdataSectionOffset + rdataBuffer.capacity()) {
            return Optional.of(rdataBuffer.position((int) (offset - rdataSectionOffset)));
        }
        return Optional.empty();
    }

    public ByteBuffer getDataBuffer(int offset) {
        return dataBuffer.position(offset);
    }

    public String getCString(long offset) {
        if (offset == 0) {
            return "";
        }

        return getBufferOptional(offset)
            .map(this::readCString)
            .orElse("");
    }

    private String readCString(ByteBuffer buffer) {
        var builder = new StringBuilder();
        while (true) {
            var b = buffer.get();
            if (b == 0) {
                break;
            }
            builder.append((char) b);
        }
        return builder.toString();
    }

}
