/*
 * acoustic_cloaking_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class acoustic_cloaking_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("R1", "1[m]", "\u6597\u7bf7\u5185\u534a\u5f84");
    model.param().set("R2", "2[m]", "\u6597\u7bf7\u5916\u534a\u5f84");
    model.param().set("x1", "0[m]", "\u5747\u8d28\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("y1", "5[m]", "\u5747\u8d28\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("x2", "10[m]", "\u65e0\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("y2", "5[m]", "\u65e0\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("x3", "0[m]", "50 \u5c42\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("y3", "0[m]", "50 \u5c42\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cy \u5750\u6807");
    model.param().set("x4", "10[m]", "20 \u5c42\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cx \u5750\u6807");
    model.param().set("y4", "0[m]", "20 \u5c42\u6597\u7bf7\u6a21\u578b\u4e2d\u5fc3\uff0cy \u5750\u6807");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 4);
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new String[]{"x1", "y1"});
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "R2", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layername", "\u5c42 2", 0);
    model.component("comp1").geom("geom1").feature("c1").setIndex("layer", "R1", 1);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "2*R2");
    model.component("comp1").geom("geom1").feature("c2").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c2").set("pos", new String[]{"x2", "y2"});
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "R2", 0);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layername", "\u5c42 2", 0);
    model.component("comp1").geom("geom1").feature("c2").setIndex("layer", "R1", 1);
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "2*R2");
    model.component("comp1").geom("geom1").feature("c3").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c3").set("pos", new String[]{"x3", "y3"});
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "R2", 0);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 1);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 2);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 3);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 4);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 5);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 6);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 7);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 8);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 9);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 10);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 11);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 12);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 13);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 14);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 15);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 16);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 17);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 18);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 19);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 20);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 21);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 22);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 23);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 24);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 25);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 26);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 27);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 28);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 29);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 30);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 31);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 32);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 33);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 34);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 35);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 36);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 37);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 38);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 39);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 40);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 41);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 42);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 43);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 44);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 45);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 46);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 47);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 48);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 49);
    model.component("comp1").geom("geom1").feature("c3").setIndex("layer", "(R2-R1)/50", 50);
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "2*R2");
    model.component("comp1").geom("geom1").feature("c4").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c4").set("pos", new String[]{"x4", "y4"});
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 1", 0);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layer", "R2", 0);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 2", 1);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 2", 1);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layer", "(R2-R1)/20", 1);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 3", 2);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 3", 2);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 4", 3);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 4", 3);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 5", 4);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 5", 4);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 6", 5);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 6", 5);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 7", 6);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 7", 6);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 8", 7);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 8", 7);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 9", 8);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 9", 8);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 10", 9);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 10", 9);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 11", 10);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 11", 10);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 12", 11);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 12", 11);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 13", 12);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 13", 12);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 14", 13);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 14", 13);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 15", 14);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 15", 14);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 16", 15);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 16", 15);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 17", 16);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 17", 16);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 18", 17);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 18", 17);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 19", 18);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 19", 18);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 20", 19);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 20", 19);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "Layer 21", 20);
    model.component("comp1").geom("geom1").feature("c4").setIndex("layername", "\u5c42 21", 20);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c1", 3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c2", 3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c3", 52);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("c4", 22);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").set("ignorevtx", false);
    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 183, 184, 185, 186, 187, 188, 189, 190, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("igv1", "IgnoreVertices");
    model.component("comp1").geom("geom1").feature("igv1").selection("input")
         .set("ige1", 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 92, 93, 94, 95, 96, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 191, 192, 193, 194, 195, 196, 197, 198, 199, 200, 201, 202, 203, 204, 205, 206, 207, 208, 209, 210, 211, 212, 213, 214, 215);
    model.component("comp1").geom("geom1").run("igv1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9009\u62e9\uff1a\u6750\u6599 1");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection")
         .set("igv1", 5, 7, 9, 11, 13, 15, 17, 19, 21, 23, 25, 27, 29, 31, 33, 35, 37, 39, 41, 43, 45, 47, 49, 51, 53, 58, 60, 62, 64, 66, 68, 70, 72, 74, 76);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u9009\u62e9\uff1a\u6750\u6599 2");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("igv1", 3, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 56, 59, 61, 63, 65, 67, 69, 71, 73, 75);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u9009\u62e9\uff1a\u5747\u5300\u5316\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("igv1", 4);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9009\u62e9\uff1a50 \u5c42\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "-2.5+x3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", "2.5+x3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "0+y3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "2.5+y3");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u9009\u62e9\uff1a20 \u5c42\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmin", "-2.5+x4");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "2.5+x4");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "0+y4");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "2.5+y4");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u9009\u62e9\uff1a\u58f0\u5b66\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"sel3", "boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u9009\u62e9\uff1a\u5bf9\u79f0\u8fb9\u754c\uff0c\u5747\u5300\u5316\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmin", "-5+x1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("xmax", "5+x1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "-0.5+y1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "0.5+y1");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4")
         .label("\u9009\u62e9\uff1a\u5bf9\u79f0\u8fb9\u754c\uff0c\u65e0\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", "-5+x2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "5+x2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-0.5+y2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "0.5+y2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5")
         .label("\u9009\u62e9\uff1a\u5bf9\u79f0\u8fb9\u754c\uff0c50 \u5c42\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmin", "-5+x3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", "5+x3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymin", "-0.5+y3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymax", "0.5+y3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6")
         .label("\u9009\u62e9\uff1a\u5bf9\u79f0\u8fb9\u754c\uff0c20 \u5c42\u6597\u7bf7");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", "-5+x4");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmax", "5+x4");
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymin", "-0.5+y4");
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymax", "0.5+y4");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7")
         .label("\u9009\u62e9\uff1a\u5747\u5300\u5316\u6597\u7bf7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmin", "-5+x1");
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmax", "5+x1");
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymin", "-0.5+y1");
    model.component("comp1").geom("geom1").feature("boxsel7").set("ymax", "0.5+y1");
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").create("boxsel8", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel8").label("\u9009\u62e9\uff1a\u65e0\u6597\u7bf7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel8").set("xmin", "-5+x2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("xmax", "5+x2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("ymin", "-0.5+y2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("ymax", "0.5+y2");
    model.component("comp1").geom("geom1").run("boxsel8");
    model.component("comp1").geom("geom1").create("boxsel9", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel9").label("\u9009\u62e9\uff1a50 \u5c42\u6597\u7bf7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel9").set("xmin", "-5+x3");
    model.component("comp1").geom("geom1").feature("boxsel9").set("xmax", "5+x3");
    model.component("comp1").geom("geom1").feature("boxsel9").set("ymin", "-0.5+y3");
    model.component("comp1").geom("geom1").feature("boxsel9").set("ymax", "0.5+y3");
    model.component("comp1").geom("geom1").run("boxsel9");
    model.component("comp1").geom("geom1").create("boxsel10", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel10")
         .label("\u9009\u62e9\uff1a20 \u5c42\u6597\u7bf7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel10").set("xmin", "-5+x4");
    model.component("comp1").geom("geom1").feature("boxsel10").set("xmax", "5+x4");
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymin", "-0.5+y4");
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymax", "0.5+y4");
    model.component("comp1").geom("geom1").run("boxsel10");

    model.title(null);

    model.description("");

    model.label("acoustic_cloaking_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
