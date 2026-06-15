/*
 * argon_gec_icp_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:05 by COMSOL 6.3.0.290. */
public class argon_gec_icp_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Inductively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0.01 0.01 0.14 0.14 0.07 0.07 0 0 0.01");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("y", "-0.015 -0.025 -0.025 0.08 0.08 0.05 0.05 -0.015 -0.015");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.064, 0.01});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{0, 0.04});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.06, 0.03});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 0.05});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.0825, 0.0025});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, -0.0025});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new String[]{"0.05", "0.015-0.0025"});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{0, -0.015});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{0.04, 0.01});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{0.01, -0.025});
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2").set("x", "0.057 0.057 0.0825 0.0825 0.064 0.064 0.057");
    model.component("comp1").geom("geom1").feature("pol2").set("y", "0.04 0.034 0.034 0.05 0.05 0.04 0.04");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("r6", "Rectangle");
    model.component("comp1").geom("geom1").feature("r6").set("size", new double[]{0.003, 0.003});
    model.component("comp1").geom("geom1").feature("r6").set("pos", new double[]{0.005, 0.05});
    model.component("comp1").geom("geom1").run("r6");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r6");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{5, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.012, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("argon_gec_icp_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
