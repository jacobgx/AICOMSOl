/*
 * three_phase_motor_frequency_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:24 by COMSOL 6.3.0.290. */
public class three_phase_motor_frequency_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Verifications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("r1", "2[cm]");
    model.param().descr("r1", "\u8f6c\u5b50\u94a2\u7684\u5916\u534a\u5f84");
    model.param().set("r2", "3[cm]");
    model.param().descr("r2", "\u8f6c\u5b50\u94dd\u7684\u5916\u534a\u5f84");
    model.param().set("r3", "3.2[cm]");
    model.param().descr("r3", "\u7ed5\u7ec4\u7684\u5185\u534a\u5f84");
    model.param().set("r4", "5.2[cm]");
    model.param().descr("r4", "\u5b9a\u5b50\u94a2\u7684\u5185\u534a\u5f84");
    model.param().set("r5", "5.7[cm]");
    model.param().descr("r5", "\u5b9a\u5b50\u94a2\u7684\u5916\u534a\u5f84");
    model.param().set("win_angle", "45[deg]");
    model.param().descr("win_angle", "\u7ed5\u7ec4\u7684\u89d2\u8de8\u5ea6");
    model.param().set("airgap", "r3-r2");
    model.param().descr("airgap", "\u6c14\u9699\u7684\u5927\u5c0f");

    model.component("comp1").geom("geom1").lengthUnit("cm");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "r5");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("c2").set("r", "r4");
    model.component("comp1").geom("geom1").run("c2");
    model.component("comp1").geom("geom1").create("c3", "Circle");
    model.component("comp1").geom("geom1").feature("c3").set("r", "r4");
    model.component("comp1").geom("geom1").feature("c3").set("angle", "win_angle");
    model.component("comp1").geom("geom1").run("c3");
    model.component("comp1").geom("geom1").create("c4", "Circle");
    model.component("comp1").geom("geom1").feature("c4").set("r", "r3");
    model.component("comp1").geom("geom1").feature("c4").set("angle", "win_angle");
    model.component("comp1").geom("geom1").run("c4");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c3");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("c4");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,60,300)");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("c5", "Circle");
    model.component("comp1").geom("geom1").feature("c5").set("r", "r3");
    model.component("comp1").geom("geom1").run("c5");
    model.component("comp1").geom("geom1").create("c6", "Circle");
    model.component("comp1").geom("geom1").feature("c6").set("r", "r2+airgap/2");
    model.component("comp1").geom("geom1").run("c6");
    model.component("comp1").geom("geom1").create("c7", "Circle");
    model.component("comp1").geom("geom1").feature("c7").set("r", "r5*1.2");
    model.component("comp1").geom("geom1").run("c7");
    model.component("comp1").geom("geom1").create("c8", "Circle");
    model.component("comp1").geom("geom1").feature("c8").set("r", "r5");
    model.component("comp1").geom("geom1").run("c8");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-r5*1.2", "0"});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"r5*1.2", "0"});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("co1").selection("input").set("c7", "c8", "ls1");
    model.component("comp1").geom("geom1").feature("co1").set("formula", "c7+ls1-c8");
    model.component("comp1").geom("geom1").run("co1");
    model.component("comp1").geom("geom1").create("c9", "Circle");
    model.component("comp1").geom("geom1").feature("c9").set("r", "r2+airgap/2");
    model.component("comp1").geom("geom1").run("c9");
    model.component("comp1").geom("geom1").create("c10", "Circle");
    model.component("comp1").geom("geom1").feature("c10").set("r", "r2");
    model.component("comp1").geom("geom1").run("c10");
    model.component("comp1").geom("geom1").create("c11", "Circle");
    model.component("comp1").geom("geom1").feature("c11").set("r", "r1");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("three_phase_motor_frequency_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
