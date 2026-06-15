/*
 * ht_pem_flow_field_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:08 by COMSOL 6.3.0.290. */
public class ht_pem_flow_field_geom_sequence {

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
    model.param().set("W_rib", "0.7[mm]", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("W_ch", "0.8[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_plate", "50[mm]", "\u677f\u5bbd");
    model.param().set("H_ch", "W_ch", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("H_gdl", "0.38[mm]", "Gdl \u9ad8\u5ea6");
    model.param().set("H_mem", "100[um]", "\u819c\u539a\u5ea6");
    model.param().set("W_ribch", "W_rib+W_ch", "\u901a\u9053\u95f4\u8ddd");
    model.param().set("N_ch", "3", "\u901a\u9053\u6570");
    model.param().set("r_ch", "0.25[mm]", "\u901a\u9053\u62d0\u89d2\u7684\u5185\u534a\u5f84");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "H_gdl+H_mem/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_ch", "W_plate-W_rib"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"W_rib/2-N_ch*W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "W_rib/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"N_ch*2", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"2*N_ch*W_ribch", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "W_ch", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"-N_ch*W_ribch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "W_rib/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("fullsize", new String[]{"1", "N_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"0", "W_ribch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").selection("input").set("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1")
         .set("pos", new String[]{"0", "W_plate/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .set("size", "sqrt(2)*W_ribch*(N_ch+1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("sq2", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq2").set("rot", "45+90");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("sq3", "sq2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq3").set("rot", "45-90");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input")
         .set("sq1", "sq2", "sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1")
         .set("disply", "W_plate-(+W_ribch*(N_ch))");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input").set("mir1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input2")
         .set("copy1(2)", "copy1(3)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif3", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif3").selection("input").set("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif3").selection("input2")
         .set("copy1(1)", "sq2", "sq3");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("dif1", "dif2", "dif3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("uni1", 7, 10, 11, 14, 15, 18, 20, 21, 24, 25, 28, 29);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "r_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point")
         .set("fil1", 9, 10, 15, 16, 21, 22, 26, 29, 32, 35, 38, 41);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "W_ch+r_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
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
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", "N_ch*W_ribch-W_ch");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "W_plate-N_ch*W_ribch+W_ch");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"ext1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2*N_ch*W_ribch", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "W_plate", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "H_gdl", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-N_ch*W_ribch", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "H_mem/2", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("blk1", 1);
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "H_mem/2", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "ext2", "pard2");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"0", "W_plate/2", "0"});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "-H_mem/4");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"ext1", "boxsel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("intsel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", "H_gdl/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", "H_gdl*3/2+H_mem");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel3"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("parf1", "PartitionFaces");
    model.component("comp1").geom("geom1").feature("parf1").selection("face").named("difsel2");
    model.component("comp1").geom("geom1").feature("parf1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("parf1");
    model.component("comp1").geom("geom1").feature().duplicate("parf2", "parf1");
    model.component("comp1").geom("geom1").feature("parf2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("parf2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", "-W_ribch*3+W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", "H_mem/2+H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel5", "boxsel4");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmin", "W_ribch*3-W_ch/10");
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", Double.POSITIVE_INFINITY);
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"blk1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("difsel4", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel4").set("add", new String[]{"ext1"});
    model.component("comp1").geom("geom1").feature("difsel4").set("subtract", new String[]{"boxsel4", "boxsel5"});
    model.component("comp1").geom("geom1").run("difsel4");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", "H_mem/4");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmin", "H_mem/4");
    model.component("comp1").geom("geom1").feature("boxsel7").set("zmax", "H_mem/2+H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature().duplicate("boxsel8", "boxsel7");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmin", "-H_mem/2-H_gdl/2");
    model.component("comp1").geom("geom1").feature("boxsel8").set("zmax", "-H_mem/4");
    model.component("comp1").geom("geom1").run("boxsel8");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"ext1", "boxsel6"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"boxsel2", "blk1"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").feature().duplicate("intsel4", "intsel3");
    model.component("comp1").geom("geom1").feature("intsel4").set("input", new String[]{"blk1", "boxsel6"});
    model.component("comp1").geom("geom1").run("intsel4");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"intsel2", "intsel4"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("boxsel9", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel9").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmin", "-H_gdl*3/2-H_mem");
    model.component("comp1").geom("geom1").feature("boxsel9").set("zmax", "-H_gdl/2-H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel9").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel9");
    model.component("comp1").geom("geom1").create("difsel5", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel5").set("add", new String[]{"boxsel9"});
    model.component("comp1").geom("geom1").feature("difsel5").set("subtract", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("difsel5");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature().duplicate("adjsel2", "adjsel1");
    model.component("comp1").geom("geom1").runPre("adjsel2");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("intsel5", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel5").set("input", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").run("intsel5");
    model.component("comp1").geom("geom1").create("boxsel10", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel10").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymin", "N_ch*W_ribch+W_ch-W_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("ymax", "W_plate-N_ch*W_ribch-W_ch+W_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmin", "H_gdl/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("zmax", "H_gdl*3/2+H_mem/2");
    model.component("comp1").geom("geom1").feature("boxsel10").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel10");
    model.component("comp1").geom("geom1").create("intsel6", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel6").set("input", new String[]{"difsel2", "boxsel10"});
    model.component("comp1").geom("geom1").run("intsel6");
    model.component("comp1").geom("geom1").create("difsel6", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel6").set("add", new String[]{"difsel2"});
    model.component("comp1").geom("geom1").feature("difsel6").set("subtract", new String[]{"intsel6"});

    model.title(null);

    model.description("");

    model.label("ht_pem_flow_field_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
