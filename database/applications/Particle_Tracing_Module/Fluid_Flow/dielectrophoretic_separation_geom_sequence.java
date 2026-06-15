/*
 * dielectrophoretic_separation_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:53 by COMSOL 6.3.0.290. */
public class dielectrophoretic_separation_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"560/2", "40"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{0, -20});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{40, 200});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{9, -9});
    model.component("comp1").geom("geom1").feature("r2").set("rot", 45);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("mir1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new String[]{"560/2", "0"});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 40);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new int[]{20, 20});
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("sq1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{7, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{80, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("arr1", "mir1", "mir2", "r1", "r2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point")
         .set("uni1", 5, 6, 8, 9, 11, 13, 15, 17, 19, 22, 24, 26, 28, 30, 32, 34, 35, 37);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 5);
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("dielectrophoretic_separation_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
