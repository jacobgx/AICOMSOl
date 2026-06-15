/*
 * battery_pack_6s2p_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class battery_pack_6s2p_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().label("\u51e0\u4f55\u53c2\u6570");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_batt", "21[mm]", "\u7535\u6c60\u76f4\u5f84");
    model.param().set("r_batt", "d_batt/2", "\u7535\u6c60\u534a\u5f84");
    model.param().set("h_batt", "70[mm]", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("h_term", "1[mm]", "\u7ec8\u7aef\u539a\u5ea6");
    model.param().set("r_term", "3[mm]", "\u7ec8\u7aef\u534a\u5f84");
    model.param().set("d_sc", "2[mm]", "\u4e32\u8054\u8fde\u63a5\u5668\u6df1\u5ea6");
    model.param().set("h_sc", "1[mm]", "\u4e32\u8054\u8fde\u63a5\u5668\u9ad8\u5ea6");
    model.param().set("h_pc", "0.5[mm]", "\u5e76\u8054\u8fde\u63a5\u5668\u9ad8\u5ea6");
    model.param().set("w_pc", "1[mm]", "\u5e76\u8054\u8fde\u63a5\u5668\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_batt");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "h_batt");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_term");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "h_term");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "-h_term"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 1, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0", "0", "h_batt+h_term"});
    model.component("comp1").geom("geom1").feature().duplicate("arr2", "arr1");
    model.component("comp1").geom("geom1").runPre("arr2");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("arr1", "cyl1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{3, 1, 2});
    model.component("comp1").geom("geom1").feature("arr2").setIndex("displ", "d_batt", 0);
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"d_batt", "0", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"d_batt+d_sc", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "d_sc", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "h_sc", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"-d_sc/2", "-d_sc/2", "0"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("pos", "-h_sc-h_term", 2);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"(d_batt+d_sc)/2", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "d_sc", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("size", "h_sc", 2);
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"-d_sc/2+(d_batt)*2", "0", "0"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-d_sc/2", 1);
    model.component("comp1").geom("geom1").feature("blk2").setIndex("pos", "-h_term-h_sc", 2);
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3")
         .set("size", new String[]{"(d_batt)/2+d_sc", "d_sc", "h_sc"});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "-d_sc/2-d_batt/2", 0);
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", "h_batt+h_term", 2);
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4")
         .set("size", new String[]{"w_pc", "d_batt/2+w_pc/2", "h_sc"});
    model.component("comp1").geom("geom1").feature("blk4").setIndex("size", "h_pc", 2);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "-w_pc/2", 0);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "-w_pc/2", 1);
    model.component("comp1").geom("geom1").feature("blk4").setIndex("pos", "-h_term-h_sc-h_pc", 2);
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("mov1", "Move");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "d_batt");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "h_batt+h_term*2+h_sc");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("arr3", "Array");
    model.component("comp1").geom("geom1").feature("arr3").selection("input").set("blk4");
    model.component("comp1").geom("geom1").feature("arr3").set("fullsize", new int[]{3, 1, 2});
    model.component("comp1").geom("geom1").feature("arr3")
         .set("displ", new String[]{"d_batt", "0", "h_batt+2*(h_term+h_sc)+h_pc"});
    model.component("comp1").geom("geom1").run("arr3");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-(h_term+h_sc+h_pc)");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_batt");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "r_batt");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1")
         .set("pos", new String[]{"-r_batt", "-r_batt"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"3*(d_batt)", "d_batt"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-r_batt", "-r_batt"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif2").selection("input2").set("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_term+h_sc+h_pc", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_batt+h_term+h_sc+h_pc", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_batt+2*(h_term+h_sc+h_pc)", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 4);
    model.component("comp1").geom("geom1").feature("sel1").label("\u7535\u6c60 1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 14);
    model.component("comp1").geom("geom1").feature("sel2").label("\u7535\u6c60 2");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 22);
    model.component("comp1").geom("geom1").feature("sel3").label("\u7535\u6c60 3");
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .set("fin", 2, 3, 5, 12, 13, 20, 21, 28, 29);
    model.component("comp1").geom("geom1").feature("sel4").label("\u7a7a\u6c14\u57df");
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1")
         .set("input", new String[]{"sel1", "sel2", "sel3", "sel4"});
    model.component("comp1").geom("geom1").feature("comsel1").label("\u5bfc\u4f53");
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel1", "sel2", "sel3"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u7535\u6c60");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"comsel1", "unisel1"});
    model.component("comp1").geom("geom1").feature("unisel2").label("\u7535\u6c60\u548c\u5bfc\u4f53");
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").label("\u70ed\u901a\u91cf\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection")
         .set("fin", 2, 3, 4, 5, 6, 7, 8, 9, 10, 14, 15, 22, 44, 49, 54, 56, 58, 92, 97, 103, 138, 143, 148);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u7f51\u683c\u626b\u63a0\u57df");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "-h_pc/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", "h_batt+h_pc/2");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");

    model.title(null);

    model.description("");

    model.label("battery_pack_6s2p_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
