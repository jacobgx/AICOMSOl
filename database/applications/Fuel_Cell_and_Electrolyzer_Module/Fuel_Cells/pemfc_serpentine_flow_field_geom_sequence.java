/*
 * pemfc_serpentine_flow_field_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class pemfc_serpentine_flow_field_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W_plate_min", "20[mm]", "\u6700\u5c0f\u677f\u5bbd");
    model.param().set("N_ch", "4", "\u901a\u9053\u6570");
    model.param().set("N_repeat", "3", "\u91cd\u590d\u5355\u5143\u6570");
    model.param().set("W_rib", "0.7[mm]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("W_ch", "0.8[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("H_ch", "W_ch", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("H_gdl", "0.2[mm]", "Gdl \u9ad8\u5ea6");
    model.param().set("H_mem", "30[um]", "\u819c\u539a\u5ea6");
    model.param()
         .set("FLOW_DIRECTION", "1", "\u4f7f\u7528 1 \u8868\u793a\u9006\u6d41\uff0c0 \u8868\u793a\u987a\u6d41");
    model.param().set("W_plate", "max(W_plate_min,N_ch*W_ribch*2)", "\u677f\u5bbd");
    model.param().set("W_ribch", "W_rib+W_ch", "\u901a\u9053\u95f4\u8ddd");
    model.param().set("L_repeat", "N_ch*2*W_ribch", "\u91cd\u590d\u5355\u5143\u7684\u957f\u5ea6");
    model.param().set("L_plate", "L_repeat*N_repeat", "\u677f\u957f");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "H_gdl+H_mem/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_plate", "W_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-W_plate-W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "W_rib/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "W_rib/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1")
         .set("pos", new String[]{"-W_ribch", "W_ribch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 270);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "W_rib/2+W_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"W_ch", "W_plate"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-W_rib/2-W_ch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "W_ribch", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input")
         .set("dif1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("linearsize", "N_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"-W_ribch", "W_ribch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"W_ribch*N_ch", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("size", "W_plate/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"-W_ribch*N_ch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("int1").selection("input")
         .set("r3", "uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("int1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1")
         .set("pos", new String[]{"0", "W_plate/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir2").selection("input").set("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir2")
         .set("pos", new String[]{"-W_ribch*N_ch/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("int1", "mir2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir3", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir3").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir3").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni3").selection("input")
         .set("mir3", "uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("uni3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("fullsize", new String[]{"N_repeat", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"L_repeat", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni4", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni4").selection("input").set("arr2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").label("\u901a\u9053");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_ch", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp2").set("quicky", "N_ch*W_ribch+W_ch");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").named("ext1");
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp3").set("quicky", "W_plate-N_ch*W_ribch-W_ch");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("pard2", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard2").selection("domain").named("ext1");
    model.component("comp1").geom("geom1").run("pard2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("GDL");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L_plate", "W_plate", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "H_gdl", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-L_repeat/2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "H_mem/2", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u819c");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("blk1", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "H_mem/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "ext2", "pard2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("GDL \u5206\u5272\u9762");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "H_gdl/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "H_gdl*3/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel1").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("parf1", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf1").selection("face").named("boxsel1");
    model.component("comp1").geom("geom1").feature("parf1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("parf1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature().duplicate("parf2", "parf1");
    model.component("comp1").geom("geom1").feature("parf2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").run("parf2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("parf2");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"0", "W_plate/2", "0"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("H2 \u4fa7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "-H_mem/4");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3").label("O2 \u4fa7\u57df");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "H_mem/4");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("H2 GDL");
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"blk1", "boxsel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").label("O2 GDL");
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"blk1", "boxsel3"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").label("H2 \u901a\u9053");
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"ext1", "boxsel2"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").create("intsel4", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel4").label("O2 \u901a\u9053");
    model.component("comp1").geom("geom1").feature("intsel4").set("input", new String[]{"ext1", "boxsel3"});
    model.component("comp1").geom("geom1").run("intsel4");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("H2 \u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4")
         .set("xmin", "if(FLOW_DIRECTION==0, -Inf, L_plate-L_repeat/2-W_ch/10)");
    model.component("comp1").geom("geom1").feature("boxsel4")
         .set("xmax", "if(FLOW_DIRECTION==0, -L_repeat/2+W_ch/10, Inf)");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "-H_mem/2-H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").label("O2 \u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", "-L_repeat/2+W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmin", "H_mem/2+H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").label("H2 \u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel6")
         .set("xmin", "if(FLOW_DIRECTION==1, -Inf, L_plate-L_repeat/2-W_ch/10)");
    model.component("comp1").geom("geom1").feature("boxsel6")
         .set("xmax", "if(FLOW_DIRECTION==1, -L_repeat/2+W_ch/10, Inf)");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmax", "-H_mem/2-H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").label("O2 \u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmin", "L_plate-L_repeat/2-W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmin", "H_mem/2+H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").create("boxsel8", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel8").label("H2 GDE");
    model.component("comp1").geom("geom1").feature("boxsel8").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmin", "-H_mem/2-H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmax", "-H_mem/4");
    model.component("comp1").geom("geom1").feature("boxsel8").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel8");
    model.component("comp1").geom("geom1").create("boxsel9", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel9").label("O2 GDE");
    model.component("comp1").geom("geom1").feature("boxsel9").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmin", "H_mem/4");
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmax", "H_mem/2+H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel9").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel9");
    model.component("comp1").geom("geom1").create("boxsel10", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel10").label("H2 GDL \u4e0b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel10").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmin", "-H_gdl*3/2-H_mem");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmax", "-H_gdl/2-H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmin", "-H_gdl*3/2-H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel10").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel10");
    model.component("comp1").geom("geom1").create("boxsel11", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel11").label("O2 GDL \u4e0a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel11").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel11").set("zmin", "H_gdl/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel11").set("zmax", "H_gdl*3/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel11").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel11").set("selshow", false);
    model.component("comp1").geom("geom1").run("boxsel11");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("H2 \u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel10"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("O2 \u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel11"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("boxsel12", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel12").label("\u901a\u9053\u626b\u63a0\u7f51\u683c\u57df");
    model.component("comp1").geom("geom1").feature("boxsel12").set("ymin", "N_ch*W_ribch+W_ch/4");
    model.component("comp1").geom("geom1").feature("boxsel12").set("ymax", "W_plate-N_ch*W_ribch-W_ch/4");
    model.component("comp1").geom("geom1").feature("boxsel12").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel12");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3")
         .label("\u901a\u9053\u56db\u9762\u4f53\u7f51\u683c\u57df");
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"ext1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"boxsel12"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .label("\u901a\u9053\u626b\u63a0\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel12"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2")
         .label("\u901a\u9053\u56db\u9762\u4f53\u7f51\u683c\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"difsel3"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("intsel5", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel5").label("\u901a\u9053\u7f51\u683c\u626b\u63a0\u9762");
    model.component("comp1").geom("geom1").feature("intsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel5").set("input", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").feature().duplicate("boxsel13", "boxsel10");
    model.component("comp1").geom("geom1").feature("boxsel13")
         .label("H2 GDL \u4e0b\u8fb9\u754c\u7f51\u683c\u626b\u63a0\u533a\u57df");
    model.component("comp1").geom("geom1").feature("boxsel13").set("ymin", "N_ch*W_ribch-W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel13").set("ymax", "W_plate-N_ch*W_ribch+W_ch/10");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel14", "boxsel11");
    model.component("comp1").geom("geom1").feature("boxsel14")
         .label("O2 GDL \u4e0a\u8fb9\u754c\u7f51\u683c\u626b\u63a0\u533a\u57df");
    model.component("comp1").geom("geom1").feature("boxsel14").set("ymin", "N_ch*W_ribch-W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel14").set("ymax", "W_plate-N_ch*W_ribch+W_ch/10");
    model.component("comp1").geom("geom1").run("boxsel14");
    model.component("comp1").geom("geom1").create("intsel6", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel6")
         .label("\u96c6\u6d41\u4f53\u6620\u5c04\u7f51\u683c\u4e0b\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel6").set("input", new String[]{"difsel1", "boxsel13"});
    model.component("comp1").geom("geom1").feature("intsel6").set("selshow", false);
    model.component("comp1").geom("geom1").run("intsel6");
    model.component("comp1").geom("geom1").create("intsel7", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel7")
         .label("\u96c6\u6d41\u4f53\u6620\u5c04\u7f51\u683c\u4e0a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel7").set("input", new String[]{"difsel2", "boxsel14"});
    model.component("comp1").geom("geom1").feature("intsel7").set("selshow", false);
    model.component("comp1").geom("geom1").run("intsel7");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1")
         .label("\u6620\u5c04\u7f51\u683c\u96c6\u6d41\u4f53\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"intsel6", "intsel7"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("difsel4", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel4")
         .label("\u4e09\u89d2\u5f62\u7f51\u683c\u96c6\u6d41\u4f53\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel4").set("add", new String[]{"difsel1", "difsel2"});
    model.component("comp1").geom("geom1").feature("difsel4").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("difsel4");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"difsel1", "difsel2"});

    model.title(null);

    model.description("");

    model.label("pemfc_serpentine_flow_field_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
