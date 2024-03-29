/*
 * Copyright 2022. mmc1234
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.mmc1234.jfreetype.example;

import io.github.mmc1234.jfreetype.core.*;
import io.github.mmc1234.jfreetype.image.FTBBox;
import io.github.mmc1234.jfreetype.util.VarUtils;
import jdk.incubator.foreign.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FaceTest {

    @Test
    public void main() {
        FreeType.load();
        
        String fontName = "C:\\Windows\\Fonts\\Arial.ttf";
        MemorySegment libPtr = VarUtils.newAddress();
        MemorySegment facePtr = VarUtils.newAddress();
        MemorySegment filePath = VarUtils.newString(fontName);

        // Init
        Assert.assertEquals(FreeType.OK, FreeTypeLibrary.FTInitFreeType(libPtr));
        Assert.assertEquals(FreeType.OK, FreeTypeFace.FTNewFace(
                VarUtils.starAddress(libPtr), filePath.address(), 0, facePtr));
        Assert.assertEquals(FreeType.OK, FreeTypeFace.FTSetCharSize(
                VarUtils.starAddress(facePtr), 0, 16 * 64, 300, 300));

        // Print debug info
        System.out.println(FTFace.STRUCT_LAYOUT.byteSize());
        MemorySegment face = VarUtils.star(facePtr, FTFace.STRUCT_LAYOUT);
        MemorySegment bbox = VarUtils.getSegment(FTFace.BBOX.handle(), face, FTBBox.STRUCT_LAYOUT);
        System.out.println(FTBBox.X_MAX.get(bbox));
        System.out.println(VarUtils.getLong(VarUtils.createAccess(FTFace.SEQUENCE_LAYOUT, "bbox.xMax"), face));
        System.out.println(VarUtils.getString(FTFace.STYLE_NAME.handle(), face));
        System.out.println(FTFace.ASCENDER.get(face));
        System.out.println(FTFace.DESCENDER.get(face));
        System.out.println(FTFace.HEIGHT.get(face));
        System.out.println(FTFace.UNITS_PER_EM.get(face));

        // Done
        FreeTypeFace.FTDoneFace(VarUtils.starAddress(facePtr));
        FreeTypeLibrary.FTDoneFreeType(VarUtils.starAddress(libPtr));
    }
}
