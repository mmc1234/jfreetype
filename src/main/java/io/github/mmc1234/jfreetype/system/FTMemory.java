package io.github.mmc1234.jfreetype.system;

import io.github.mmc1234.jfreetype.util.StructLayoutBuilder;
import jdk.incubator.foreign.MemoryLayout;

import java.lang.invoke.VarHandle;

/**
 * A structure used to describe a given memory manager to FreeType 2.
 *
 * @implNote In freetype/ftsystem.h
 * <pre>{@code
 *   typedef struct FT_MemoryRec_*  FT_Memory;
 *   struct  FT_MemoryRec_
 *   {
 *     void*            user;
 *     FT_Alloc_Func    alloc;
 *     FT_Free_Func     free;
 *     FT_Realloc_Func  realloc;
 *   };
 * }</pre>
 */
public final class FTMemory {

    public static final MemoryLayout STRUCT_LAYOUT;
    public static final MemoryLayout SEQUENCE_LAYOUT;

    /**
     * A generic typeless pointer for user data.
     */
    public static final VarHandle USER;

    /**
     * A pointer type to an allocation function.
     *
     * @implNote Function: (FT_Memory memory, long size)<br/>
     * Input:
     * <ul>
     *     <li>memory - A handle to the source memory manager.</li>
     *     <li>size - The size in bytes to allocate.</li>
     * </ul>
     * Return: Address of new memory block. 0 in case of failure.
     */
    public static final VarHandle ALLOC;

    /**
     * A pointer type to a memory freeing function.
     *
     * @implNote Function: (FT_Memory memory, void* block)<br/>
     * Input:
     * <ul>
     *     <li>memory - A handle to the source memory manager.</li>
     *     <li>block - The address of the target memory block.</li>
     * </ul>
     */
    public static final VarHandle FREE;

    /**
     * A pointer type to a reallocation function.
     *
     * @implNote Function: (FT_Memory memory, long cur_size, long new_size, void* block)<br/>
     * Input:
     * <ul>
     *     <li>memory - A handle to the source memory manager.</li>
     *     <li>cur_size - The block's current size in bytes.</li>
     *     <li>new_size - The block's requested new size.</li>
     *     <li>block - The block's current address.</li>
     * </ul>
     * Return: New block address. 0 in case of memory shortage.<br/>
     * Note: In case of error, the old block must still be available.
     */
    public static final VarHandle REALLOC;

    static {
        StructLayoutBuilder builder = new StructLayoutBuilder("AAAA", new String[]{
                "user", "alloc", "free", "realloc"
        });
        STRUCT_LAYOUT = builder.getStructLayout();
        SEQUENCE_LAYOUT = builder.getSequenceLayout();
        USER = builder.varHandle("user");
        ALLOC = builder.varHandle("alloc");
        FREE = builder.varHandle("free");
        REALLOC = builder.varHandle("realloc");
    }
}