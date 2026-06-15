/*
 * residual_stress_resonator_2d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:55 by COMSOL 6.3.0.290. */
public class residual_stress_resonator_2d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{250, 120});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{2, 200});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{100, 120});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{48, -320});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").lengthUnit("\u00b5m");
    model.component("comp2").geom("geom2").create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("r1").set("size", new int[]{250, 120});
    model.component("comp2").geom("geom2").run("r1");
    model.component("comp2").geom("geom2").create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("r2").set("size", new int[]{2, 172});
    model.component("comp2").geom("geom2").feature("r2").set("pos", new int[]{100, 120});
    model.component("comp2").geom("geom2").run("r2");
    model.component("comp2").geom("geom2").create("r3", "Rectangle");
    model.component("comp2").geom("geom2").feature("r3").set("size", new int[]{12, 2});
    model.component("comp2").geom("geom2").feature("r3").set("pos", new int[]{100, 290});
    model.component("comp2").geom("geom2").run("r3");
    model.component("comp2").geom("geom2").create("r4", "Rectangle");
    model.component("comp2").geom("geom2").feature("r4").set("size", new int[]{2, 148});
    model.component("comp2").geom("geom2").feature("r4").set("pos", new int[]{110, 144});
    model.component("comp2").geom("geom2").run("r4");
    model.component("comp2").geom("geom2").create("mir1", "Mirror");
    model.component("comp2").geom("geom2").feature("mir1").selection("input").set("r2", "r3", "r4");
    model.component("comp2").geom("geom2").feature("mir1").set("keep", true);
    model.component("comp2").geom("geom2").feature("mir1").set("pos", new int[]{125, 0});
    model.component("comp2").geom("geom2").run("mir1");
    model.component("comp2").geom("geom2").create("mir2", "Mirror");
    model.component("comp2").geom("geom2").feature("mir2").selection("input").set("mir1", "r2", "r3", "r4");
    model.component("comp2").geom("geom2").feature("mir2").set("keep", true);
    model.component("comp2").geom("geom2").feature("mir2").set("pos", new int[]{0, 60});
    model.component("comp2").geom("geom2").feature("mir2").set("axis", new int[]{0, 1});
    model.component("comp2").geom("geom2").runPre("fin");

    model.title(null);

    model.description("");

    model.label("residual_stress_resonator_2d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
