package io.github.mmc1234.jfreetype.color;

import io.github.mmc1234.jfreetype.util.LayoutBuilder;
import jdk.incubator.foreign.MemoryLayout;

import java.lang.invoke.VarHandle;

/**
 * A structure representing a ColorIndex value of the ‘COLR’ v1 extensions,
 * see ‘https://github.com/googlefonts/colr-gradients-spec’.
 *
 * @implNote In freetype/ftcolor.h
 * <pre>{@code
 *   typedef struct FT_ColorIndex_
 *   {
 *     FT_UInt16   palette_index;
 *     FT_F2Dot14  alpha;
 *   } FT_ColorIndex;
 * }</pre>
 */
public final class FTColorIndex {

    public static final MemoryLayout STRUCT_LAYOUT;
    public static final MemoryLayout SEQUENCE_LAYOUT;

    /**
     * The palette index into a ‘CPAL’ palette.
     */
    public static final VarHandle PALETTE_INDEX;

    /**
     * Alpha transparency value multiplied with the value from ‘CPAL’.
     */
    public static final VarHandle ALPHA;

    static {
        LayoutBuilder builder = new LayoutBuilder("SS", new String[] { "palette_index", "alpha" });
        STRUCT_LAYOUT = builder.getGroupLayout();
        SEQUENCE_LAYOUT = builder.getSequenceLayout();
        PALETTE_INDEX = builder.primitiveField("palette_index");
        ALPHA = builder.primitiveField("alpha");
    }
}
