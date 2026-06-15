/*
 * shell_and_tube_heat_exchanger_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:22 by COMSOL 6.3.0.290. */
public class shell_and_tube_heat_exchanger_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Heat_Exchangers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 100);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 500);
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 50);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 750);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{-125, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 100);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("r", 100);
    model.component("comp1").geom("geom1").feature("sph2").set("pos", new int[]{500, 0, 0});
    model.component("comp1").geom("geom1").run("sph2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 45);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 132.5);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new double[]{50, 0, -132.5});
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl3");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new int[]{450, 0, 0});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("cyl1", "cyl2", "cyl3", "cyl4", "sph1", "sph2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{500, 300, 300});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, -150, -150});
    model.component("comp1").geom("geom1").feature("blk1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 100, 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 100, 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 100, 2);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 100, 3);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{500, 300, 65});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0, -150, -32.5});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("blk1", "blk2");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 7.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new double[]{-75, -43.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{7, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new double[]{25, 43.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2")
         .set("pos", new double[]{-62.5, -65.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").selection("input").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2").set("fullsize", new int[]{6, 4});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr2")
         .set("displ", new double[]{25, 43.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("arr1(1,1)", "arr1(1,3)", "arr1(7,1)", "arr1(7,3)", "arr2(1,1)", "arr2(1,4)", "arr2(6,1)", "arr2(6,4)");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 500, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"1[m]", "150", "400"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{-200, -150, -200});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1", "par1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("igf1", "IgnoreFaces");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("igf1").selection("input")
         .set("fin", 10, 17, 18, 25, 26, 32, 35, 38, 41, 52, 55, 58, 67, 70, 73, 76, 87, 90, 93, 102, 105, 112, 125, 132, 133, 138, 140, 141, 147, 150, 153, 156, 167, 170, 173, 182, 185, 188, 191, 202, 205, 208, 217, 220, 227, 234, 237, 244, 245, 252, 253, 259, 262, 265, 268, 279, 282, 285, 294, 297, 300, 303, 314, 317, 320, 329, 332, 339, 349, 356, 357, 362, 364, 365, 371, 374, 377, 380, 391, 394, 397, 406, 409, 412, 415, 426, 429, 432, 441, 444, 451, 458, 461, 468, 469, 476, 477, 483, 486, 489, 492, 503, 506, 509, 518, 521, 524, 527, 538, 541, 544, 553, 556, 563, 576, 578, 580, 582, 583, 584, 585, 586, 587, 588, 589, 590, 591, 592, 593, 594, 595, 596, 597, 598);
    model.component("comp1").geom("geom1").run("igf1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6c34\u57df");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("igf1", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7a7a\u6c14\u57df");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("igf1", 2);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u6c34\u57df\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2")
         .label("\u7a7a\u6c14\u57df\uff0c\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"sel2"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u6321\u677f");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"sel2"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("interior", true);
    model.component("comp1").geom("geom1").feature("adjsel3").set("exterior", false);
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6c34\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("igf1", 1);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u6c34\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("igf1", 339);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u7a7a\u6c14\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("igf1", 332);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u7a7a\u6c14\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("igf1", 89);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u6c34\u57df\uff0c\u58c1");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"boxsel1", "sel3", "sel4"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").label("\u7a7a\u6c14\u57df\uff0c\u58c1");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"adjsel2", "adjsel3"});
    model.component("comp1").geom("geom1").feature("difsel2")
         .set("subtract", new String[]{"boxsel1", "sel5", "sel6"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"difsel1", "difsel2"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u58c1");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u6c34-\u7a7a\u6c14\u754c\u9762");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"difsel1", "difsel2"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"adjsel3", "intsel1"});
    model.component("comp1").geom("geom1").feature("unisel2").label("\u5185\u58c1");

    model.title(null);

    model.description("");

    model.label("shell_and_tube_heat_exchanger_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
