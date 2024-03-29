package io.github.mmc1234.jfreetype.color;

import io.github.mmc1234.jfreetype.image.FTVector;
import io.github.mmc1234.jfreetype.util.LayoutBuilder;
import io.github.mmc1234.jfreetype.struct.LongField;
import io.github.mmc1234.jfreetype.struct.StructField;
import jdk.incubator.foreign.MemoryLayout;

/**
 * A structure representing a PaintRadialGradient value of the ‘COLR’ v1 extensions,
 * see ‘https://github.com/googlefonts/colr-gradients-spec’.
 * The glyph layer filled with this paint is drawn filled filled with a radial gradient.
 *
 * @implNote In freetype/ftcolor.h
 * <pre>{@code
 *   typedef struct FT_PaintRadialGradient_
 *   {
 *     FT_ColorLine  colorline;
 *
 *     FT_Vector  c0;
 *     FT_Pos     r0;
 *     FT_Vector  c1;
 *     FT_Pos     r1;
 *   } FT_PaintRadialGradient;
 * }</pre>
 */
public final class FTPaintRadialGradient {

    public static final MemoryLayout STRUCT_LAYOUT;
    public static final MemoryLayout SEQUENCE_LAYOUT;

    /**
     * The {@link FTColorLine} information for this paint, i.e., the list of color stops along the gradient.
     */
    public static final StructField COLORLINE;

    /**
     * The center of the starting point of the radial gradient in font units represented
     * as a 16.16 fixed-point {@link FTVector}.
     */
    public static final StructField C0;

    /**
     * The radius of the starting circle of the radial gradient in font units represented as a 16.16 fixed-point value.
     */
    public static final LongField R0;

    /**
     * The center of the end point of the radial gradient in font units represented as a 16.16 fixed-point {@link FTVector}.
     */
    public static final StructField C1;

    /**
     * The radius of the end circle of the radial gradient in font units represented as a 16.16 fixed-point value.
     */
    public static final LongField R1;

    static {
        LayoutBuilder builder = new LayoutBuilder("01L1L", new String[] {
                "colorline", "c0", "r0", "c1", "r1"
        }, FTColorLine.STRUCT_LAYOUT, FTVector.STRUCT_LAYOUT);
        STRUCT_LAYOUT = builder.getGroupLayout();
        SEQUENCE_LAYOUT = builder.getSequenceLayout();
        COLORLINE = builder.newStruct("colorline", FTColorLine.STRUCT_LAYOUT);
        C0 = builder.newStruct("c0", FTVector.STRUCT_LAYOUT);
        R0 = builder.newLong("r0");
        C1 = builder.newStruct("c1", FTVector.STRUCT_LAYOUT);
        R1 = builder.newLong("r1");
    }
}
