/*
 * window_and_glazing_thermal_performances_glazing_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:20 by COMSOL 6.3.0.290. */
public class window_and_glazing_thermal_performances_glazing_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Buildings_and_Constructions");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{84, 83});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{26, 0});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{26, 66});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("r2", 3);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", "9/26", 0);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{16, 3});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{26, 66});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("r3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new int[]{6, 54});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{42, 15});
    model.component("comp1").geom("geom1").feature("r4").set("selresult", true);
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new int[]{15, 3});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new int[]{48, 15});
    model.component("comp1").geom("geom1").feature("r5").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new int[]{5, 18});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new int[]{63, 0});
    model.component("comp1").geom("geom1").feature("r6").set("selresult", true);
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("r7", "Rectangle");
    model.component("comp1").geom("geom1").feature("r7").set("size", new int[]{15, 3});
    model.component("comp1").geom("geom1").feature("r7").set("pos", new int[]{95, 46});
    model.component("comp1").geom("geom1").feature("r7").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r7");
    model.component("comp1").geom("geom1").create("r8", "Rectangle");
    model.component("comp1").geom("geom1").feature("r8").set("size", new int[]{5, 34});
    model.component("comp1").geom("geom1").feature("r8").set("pos", new int[]{90, 15});
    model.component("comp1").geom("geom1").feature("r8").set("selresult", true);
    model.component("comp1").geom("geom1").run("r8");
    model.component("comp1").geom("geom1").create("r9", "Rectangle");
    model.component("comp1").geom("geom1").feature("r9").set("size", new int[]{15, 3});
    model.component("comp1").geom("geom1").feature("r9").set("pos", new int[]{95, 15});
    model.component("comp1").geom("geom1").feature("r9").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("r9");
    model.component("comp1").geom("geom1").create("r10", "Rectangle");
    model.component("comp1").geom("geom1").feature("r10").set("size", new int[]{205, 28});
    model.component("comp1").geom("geom1").feature("r10").set("pos", new int[]{95, 18});
    model.component("comp1").geom("geom1").feature("r10").setIndex("layername", "Layer 1", 0);
    model.component("comp1").geom("geom1").feature("r10").setIndex("layer", 4, 0);
    model.component("comp1").geom("geom1").feature("r10").set("layertop", true);
    model.component("comp1").geom("geom1").run("r10");
    model.component("comp1").geom("geom1").create("pare2", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare2").selection("edge").set("r10", 7);
    model.component("comp1").geom("geom1").feature("pare2").setIndex("param", "45/205", 0);
    model.component("comp1").geom("geom1").run("pare2");
    model.component("comp1").geom("geom1").create("r11", "Rectangle");
    model.component("comp1").geom("geom1").feature("r11").set("size", new int[]{10, 20});
    model.component("comp1").geom("geom1").feature("r11").set("pos", new int[]{98, 22});
    model.component("comp1").geom("geom1").run("r11");
    model.component("comp1").geom("geom1").create("r12", "Rectangle");
    model.component("comp1").geom("geom1").feature("r12").set("selresult", true);
    model.component("comp1").geom("geom1").feature("r12").set("size", new int[]{9, 19});
    model.component("comp1").geom("geom1").feature("r12").set("pos", new double[]{98.5, 22.5});
    model.component("comp1").geom("geom1").run("r12");
    model.component("comp1").geom("geom1").create("r13", "Rectangle");
    model.component("comp1").geom("geom1").feature("r13").set("size", new double[]{0.5, 18});
    model.component("comp1").geom("geom1").feature("r13").set("pos", new int[]{98, 23});
    model.component("comp1").geom("geom1").feature("r13").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 5, 58, 60, 62);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"r4"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"r8"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("ige1", 18, 20, 21, 22);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ige1", 4, 5, 7, 57, 58, 59);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("ige1", 3, 9, 60);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("ige1", 2, 19, 23, 54, 55, 56);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("ige1", 9, 11);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("ige1", 16);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("ige1", 13);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("ige1", 10);

    model.title(null);

    model.description("");

    model.label("window_and_glazing_thermal_performances_glazing_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
