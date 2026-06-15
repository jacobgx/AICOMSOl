/*
 * piezo_mems_speaker_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:39 by COMSOL 6.3.0.290. */
public class piezo_mems_speaker_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Electroacoustic_Transducers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");

    model.param().set("l_speaker", "4[mm]");
    model.param().descr("l_speaker", "\u626c\u58f0\u5668\u4fa7\u9762");
    model.param().set("th_si", "15[um]");
    model.param().descr("th_si", "\u7845\u5c42\u539a\u5ea6");
    model.param().set("th_pzt", "2[um]");
    model.param().descr("th_pzt", "PZT \u5c42\u539a\u5ea6");
    model.param().set("air_gap", "9[um]");
    model.param().descr("air_gap", "\u6267\u884c\u5668\u4e4b\u95f4\u7684\u6c14\u9699");
    model.param().set("d_speaker", "1.5[mm]");
    model.param().descr("d_speaker", "\u540e\u8154\u4f53\u6df1\u5ea6");

    model.component("comp1").view("view1").set("showgrid", false);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", "l_speaker/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"sqrt(2)/2*l_speaker-air_gap", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("size", "12*air_gap", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"l_speaker/4", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "l_speaker/4", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", "5.5*air_gap", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layertop", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"sqrt(2)/2*l_speaker-16*air_gap", "1"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("size", "12*air_gap", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"l_speaker/4", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "l_speaker/4", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r1", "r2", "sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 1, 6, 13, 17);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "th_si", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "th_si+th_pzt", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "6*air_gap");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "sqrt(2)/2*l_speaker");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 1, 0});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "8*air_gap", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("par1").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("par1");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "-th_pzt-th_si");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("mov1", 2, 4, 6);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("par1", 1, 3, 5);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "1.2*l_speaker");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "1.2*l_speaker");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "-0.6*l_speaker"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "l_speaker/4", 0);
    model.component("comp1").geom("geom1").feature("cyl2").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl2").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl2");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("par2", "Partition");
    model.component("comp1").geom("geom1").feature("par2").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("par2").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par2");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input")
         .set("par2", 1, 2, 3, 4, 9, 10, 13, 14, 17, 18);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"l_speaker/2", "1", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "l_speaker/2", 1);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "d_speaker", 2);
    model.component("comp1").geom("geom1").feature("blk1")
         .set("pos", new String[]{"0", "0", "-th_si-th_pzt-d_speaker"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickplane", "zx");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk1", "del1", "del2", "ext1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("par3", "Partition");
    model.component("comp1").geom("geom1").feature("par3").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("par3").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").feature("par3").set("workplane", "wp2");
    model.component("comp1").geom("geom1").run("par3");
    model.component("comp1").geom("geom1").create("par4", "Partition");
    model.component("comp1").geom("geom1").feature("par4").selection("input").set("par3");
    model.component("comp1").geom("geom1").feature("par4").set("partitionwith", "workplane");
    model.component("comp1").geom("geom1").run("par4");
    model.component("comp1").geom("geom1").create("del3", "Delete");
    model.component("comp1").geom("geom1").feature("del3").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del3").selection("input")
         .set("par4", 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 17, 21, 48, 55);
    model.component("comp1").geom("geom1").run("del3");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u70ed\u9ecf\u6027\u57df 1");
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "6*air_gap");
    model.component("comp1").geom("geom1").feature("cylsel1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cylsel1").set("ax3", new int[]{1, 1, 0});
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "allvertices");
    model.component("comp1").geom("geom1").feature().duplicate("cylsel2", "cylsel1");
    model.component("comp1").geom("geom1").feature("cylsel2").label("\u70ed\u9ecf\u6027\u57df 2");
    model.component("comp1").geom("geom1").feature("cylsel2").set("pos", new String[]{"0", "0", "-th_si-th_pzt"});
    model.component("comp1").geom("geom1").run("cylsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u70ed\u9ecf\u6027\u57df");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"cylsel1", "cylsel2"});
    model.component("comp1").geom("geom1").feature("unisel1").set("color", "5");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u542b\u7a7a\u6c14\u7684\u7845\u5c42");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", "-th_si");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 0);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u542b\u7a7a\u6c14\u7684 PZT \u5c42");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", "-th_si-th_pzt");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", "-th_si");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u7845\u57df");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("color", "8");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("PZT \u57df");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"unisel1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("color", "12");
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u7ed3\u6784\u57df");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"difsel1", "difsel2"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").label("\u6240\u6709\u57df");
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", "2*l_speaker");
    model.component("comp1").geom("geom1").run("ballsel1");
    model.component("comp1").geom("geom1").create("cylsel3", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel3").label("\u5185\u90e8\u57df");
    model.component("comp1").geom("geom1").feature("cylsel3").set("r", "0.8*l_speaker");
    model.component("comp1").geom("geom1").feature("cylsel3").set("top", "0.3*l_speaker");
    model.component("comp1").geom("geom1").run("cylsel3");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").label("PML \u57df");
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"ballsel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"cylsel3"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("difsel4", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel4").label("\u538b\u529b\u58f0\u5b66\u57df");
    model.component("comp1").geom("geom1").feature("difsel4").set("add", new String[]{"ballsel1"});
    model.component("comp1").geom("geom1").feature("difsel4").set("subtract", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").feature("difsel4").set("color", "10");
    model.component("comp1").geom("geom1").run("difsel4");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u9759\u7535\u57df");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"unisel1", "unisel2"});
    model.component("comp1").geom("geom1").run("unisel3");
    model.component("comp1").geom("geom1").create("unisel4", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u7a7a\u6c14\u57df");
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"unisel1", "difsel4"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u975e\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", 0.01);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", 0.01);
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("difsel5", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel5").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel5").set("add", new String[]{"boxsel3"});
    model.component("comp1").geom("geom1").feature("difsel5").set("subtract", new String[]{"boxsel4"});
    model.component("comp1").geom("geom1").run("difsel5");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5916\u573a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("del3", 23, 49);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u63a5\u5730");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .set("del3", 33, 42, 62, 71, 89, 96, 107, 129);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u7ec8\u7aef");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection")
         .set("del3", 28, 39, 59, 67, 86, 93, 102, 126);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u9664 PML \u5916");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"difsel3"});

    model.title(null);

    model.description("");

    model.label("piezo_mems_speaker_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
