package be.twofold.rtti.common.utils;

import com.kichik.pecoff4j.*;

import java.nio.*;
import java.util.*;

public final class PeWrapper {
    private final List<Segment> segments;

    public PeWrapper(PE pe, byte[] dataSection) {
        var sectionTable = pe.getSectionTable();

        List<Segment> segments = new ArrayList<>();
        var sections = Set.of(".didata", ".link", ".data", ".rdata");
        for (int i = 0; i < sectionTable.getNumberOfSections(); i++) {
            var section = sectionTable.getSection(i);
            if (!sections.contains(sectionTable.getHeader(i).getName())) {
                continue;
            }

            var buffer = ByteBuffer.wrap(section.getData()).order(ByteOrder.LITTLE_ENDIAN);
            var offset = pe.getOptionalHeader().getImageBase() + sectionTable.getHeader(i).getVirtualAddress();
            segments.add(new Segment(buffer, offset));
        }
        this.segments = List.copyOf(segments);
    }

    public ByteBuffer getBuffer(long offset) {
        return getBufferOptional(offset).orElseThrow(
            () -> new IllegalArgumentException("Offset out of bounds: " + offset)
        );
    }

    public Optional<ByteBuffer> getBufferOptional(long offset) {
        for (Segment segment : segments) {
            if (offset > segment.offset() && offset < segment.offset() + segment.buffer().capacity()) {
                return Optional.of(segment.buffer().position(Math.toIntExact(offset - segment.offset())));
            }
        }
        return Optional.empty();
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
            if(builder.length() > 2000){
                System.out.println("Warning: readCString too long");
            }
            builder.append((char) b);
        }
        return builder.toString();
    }

    private record Segment(
        ByteBuffer buffer,
        long offset
    ) {
    }
}
