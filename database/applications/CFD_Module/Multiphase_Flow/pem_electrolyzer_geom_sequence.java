/*
 * pem_electrolyzer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class pem_electrolyzer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Multiphase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("N_ch", "23", "\u7535\u6781\u901a\u9053\u6570");
    model.param().set("L_ch", "118*h_a", "\u7535\u6781\u901a\u9053\u957f\u5ea6");
    model.param().set("ang_inout", "22.5[deg]", "\u65cb\u8f6c\u89d2\u5ea6\uff0c\u5165\u53e3/\u51fa\u53e3");
    model.param().set("h_a", "0.889[mm]", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("L_inout", "2[cm]", "\u5165\u53e3/\u51fa\u53e3\u901a\u9053\u957f\u5ea6");
    model.param().set("R_in", "1.27[cm]/2", "\u5165\u53e3\u7ba1\u534a\u5f84");
    model.param().set("w_ch", "2.07[mm]", "\u901a\u9053\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R_in");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L_inout*3/4", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "w_ch", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"0", "-w_ch*2.5"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"L_inout*1/4", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "w_ch", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"L_inout*3/4", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "-w_ch*2.5", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "2*w_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "L_inout*3/4", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "2*w_ch*1.25", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "L_inout*3/4", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-2*w_ch*1.25", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "L_inout*3/4-5*w_ch*tan(ang_inout/2)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .setIndex("table", "-2*w_ch*1.25", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input")
         .set("arr1(1,3,1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input")
         .set("arr1(1,1,1)", "arr1(1,2,1)", "arr1(1,3,1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2")
         .set("c1", "pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "L_inout*3/4", 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "2*w_ch*1.25", 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "L_inout*3/4", 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "-2*w_ch*1.25", 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "L_inout*3/4+5*w_ch*tan(ang_inout/2)", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol2")
         .setIndex("table", "-2*w_ch*1.25", 2, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input")
         .set("arr1(1,1,2)", "arr1(1,2,2)", "arr1(1,3,2)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input2").set("pol2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").set("rot", "ang_inout");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("pos", new String[]{"L_inout*3/4", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").setIndex("pos", "2*w_ch*1.25", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("dif2", "rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("displx", "-L_inout-w_ch*0.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("mov1").set("disply", "-w_ch*2.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"w_ch", "L_ch+10*w_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"-w_ch/2", "-5*w_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("fullsize", new String[]{"N_ch", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new String[]{"w_ch*2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("size", new String[]{"(N_ch-0.5)*(2*w_ch)", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").setIndex("size", "w_ch", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4")
         .set("pos", new String[]{"-w_ch/2", "-w_ch*5"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr3").selection("input").set("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr3").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr3")
         .set("displ", new String[]{"0", "w_ch*2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").selection("input").set("arr3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("copy1").set("disply", "L_ch+5*w_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("copy1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2")
         .set("pos", new String[]{"(N_ch-1)*w_ch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").setIndex("pos", "L_ch/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("arr2", "arr3", "copy1", "mov1", "rot2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1")
         .label("\u5706\u89d2\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("xmax", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel1").set("ymin", "L_ch+w_ch*4.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("boxsel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2")
         .label("\u5706\u89d2\u9009\u62e9 2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("entitydim", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("xmin", "2*w_ch*(N_ch-1)");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("boxsel2").set("ymax", "-w_ch*4.5");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("boxsel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").named("boxsel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "w_ch/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("fil2", "fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").named("boxsel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_a", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R_in");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "3*h_a");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "ang_inout");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"L_inout*3/4", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot1").setIndex("pos", "2*w_ch*1.25", 1);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "-L_inout-w_ch*0.5");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-w_ch*2.5");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("rot2").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot2").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot2").set("pos", new String[]{"(N_ch-1)*w_ch", "0", "0"});
    model.component("comp1").geom("geom1").feature("rot2").setIndex("pos", "L_ch/2", 1);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5165\u53e3\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", "-L_inout");
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", "w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u51fa\u53e3\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "N_ch*w_ch*2+L_inout-w_ch");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "L_ch-w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u7535\u6781\u8868\u9762\u4e0a\u65b9\u7684\u901a\u9053");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymin", "-w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("ymax", "L_ch+w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u7535\u6781\u8868\u9762");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", "-w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymax", "L_ch+w_ch/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", "h_a/2");
    model.component("comp1").geom("geom1").feature("boxsel4").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel5").set("xmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmin", "h_a*2");
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel6").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", "N_ch*w_ch*2");
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", "h_a*2");
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .label("\u7535\u6781\u901a\u9053\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel3"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2")
         .label("\u5165\u53e3\u6b67\u7ba1\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u7535\u6781\u901a\u9053\u7684\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel1", "adjsel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel1", "boxsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u6b67\u7ba1\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("intsel2", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel2")
         .label("\u7535\u6781\u901a\u9053\u7684\u5165\u53e3\u548c\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"adjsel1", "adjsel3"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u7535\u6781\u901a\u9053\u548c\u6b67\u7ba1");
    model.component("comp1").geom("geom1").feature("unisel2")
         .set("input", new String[]{"boxsel1", "boxsel2", "boxsel3"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4")
         .label("\u51fa\u53e3\u6b67\u7ba1\u7684\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("intsel3", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel3").label("\u7535\u6781\u901a\u9053\u7684\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("intsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"adjsel1", "adjsel4"});

    model.title(null);

    model.description("");

    model.label("pem_electrolyzer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
