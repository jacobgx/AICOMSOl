/*
 * biased_resonator_3d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:52 by COMSOL 6.3.0.290. */
public class biased_resonator_3d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{38.9, 12, 4.7});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-38.9, -6, -1.2});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 1.5, 0);
    model.component("comp1").geom("geom1").feature("blk1").setIndex("layer", 0.5, 1);
    model.component("comp1").geom("geom1").feature("blk1").set("layerfront", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerback", true);
    model.component("comp1").geom("geom1").feature("blk1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", -6);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{0.15, 38.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new double[]{-0.45, -38.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new double[]{0.2, 1.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new double[]{-0.3, -36.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new double[]{1.9, 1.3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("pos", new double[]{-0.1, -36.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("size", new double[]{1.9, 0.7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r4").set("pos", new double[]{0.2, -35.6});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("size", new double[]{1.9, 14});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r5").set("pos", new double[]{0, -34.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("size", new double[]{1.9, 0.7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r6").set("pos", new double[]{0.2, -20.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("size", new double[]{0.2, 0.7});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r7").set("pos", new double[]{0, -20.9});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("size", new double[]{1.9, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r8").set("pos", new double[]{-0.1, -20.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("size", new double[]{0.2, 10.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r9").set("pos", new double[]{-0.3, -20.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10").set("size", new double[]{0.3, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r10")
         .set("pos", new double[]{-0.1, -10.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r10");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r11").set("size", new double[]{0.2, 10});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r11").set("pos", new int[]{0, -10});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r11");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r12", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r12").set("size", new double[]{1.9, 10.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r12").set("pos", new double[]{0.2, -10.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r12");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r13", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r13").set("size", new double[]{0.1, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r13")
         .set("pos", new double[]{-0.1, -20.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r13");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r14", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r14").set("size", new double[]{0.3, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r14")
         .set("pos", new double[]{-0.1, -35.6});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r14");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r15", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r15").set("size", new double[]{0.2, 0.5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r15").set("pos", new double[]{0, -35.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r15");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input")
         .set("r12", "r3", "r4", "r5", "r6", "r8");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").selection("input")
         .set("r10", "r11", "r13", "r14", "r15", "r2", "r7", "r9");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 12, 0);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u51e0\u4f55\u7ed3\u6784");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u63a5\u5730\u5e73\u9762");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", -2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", -1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u6c27\u5316\u7269");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmin", -1);
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", -0.9);
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("boxsel3", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel3").label("\u6c2e\u5316\u7269");
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", -0.4);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", -0.35);
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("boxsel4", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel4").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmin", -0.1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("xmax", 0.1);
    model.component("comp1").geom("geom1").feature("boxsel4").set("ymin", -4.2);
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmin", -0.15);
    model.component("comp1").geom("geom1").feature("boxsel4").set("zmax", -0.1);
    model.component("comp1").geom("geom1").run("boxsel4");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").set("posz", 1);
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", 0.1);
    model.component("comp1").geom("geom1").run("ballsel1");
    model.component("comp1").geom("geom1").create("boxsel5", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel5").set("ymax", 4.8);
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmin", -0.35);
    model.component("comp1").geom("geom1").feature("boxsel5").set("zmax", 0.05);
    model.component("comp1").geom("geom1").feature("boxsel5").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel5");
    model.component("comp1").geom("geom1").create("boxsel6", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmin", -15);
    model.component("comp1").geom("geom1").feature("boxsel6").set("xmax", 15);
    model.component("comp1").geom("geom1").feature("boxsel6").set("ymax", 4.8);
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmin", -0.35);
    model.component("comp1").geom("geom1").feature("boxsel6").set("zmax", 0.05);
    model.component("comp1").geom("geom1").feature("boxsel6").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel6");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"ballsel1", "boxsel5"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"boxsel6"});
    model.component("comp1").geom("geom1").feature("difsel1").label("\u8c10\u632f\u5668");
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"boxsel4", "difsel1"});
    model.component("comp1").geom("geom1").feature("unisel1").label("\u591a\u6676\u7845");
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("difsel2")
         .set("subtract", new String[]{"boxsel2", "boxsel3", "unisel1"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u8c10\u632f\u5668\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u7535\u6781\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"boxsel4"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u6c2e\u5316\u7269\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"boxsel3"});
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("adjsel4")
         .label("\u51e0\u4f55\u7ed3\u6784\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel3").label("\u8c10\u632f\u5668\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"adjsel4"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("difsel4", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel4").label("\u7535\u6781\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("difsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel4").set("add", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("difsel4").set("subtract", new String[]{"adjsel4"});
    model.component("comp1").geom("geom1").run("difsel4");
    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u56fa\u5b9a\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"adjsel3", "difsel3"});
    model.component("comp1").geom("geom1").run("intsel1");
    model.component("comp1").geom("geom1").create("boxsel7", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel7").label("\u5bf9\u79f0\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("boxsel7").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmin", -0.1);
    model.component("comp1").geom("geom1").feature("boxsel7").set("xmax", 0.1);
    model.component("comp1").geom("geom1").feature("boxsel7").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel7");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel2").label("\u81ea\u7531\u4e09\u89d2\u5f62\u7f51\u683c");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection")
         .add("fin", 2, 5, 8, 55, 58, 112, 163, 254);

    model.title(null);

    model.description("");

    model.label("biased_resonator_3d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
