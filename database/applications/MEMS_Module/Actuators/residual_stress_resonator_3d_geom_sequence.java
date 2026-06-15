/*
 * residual_stress_resonator_3d_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:55 by COMSOL 6.3.0.290. */
public class residual_stress_resonator_3d_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Actuators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{250, 120});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{2, 200});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new int[]{100, 120});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{2, 2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("displ", new int[]{48, -320});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 2.25, 0);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);
    model.component("comp2").geom("geom2").geomRep("comsol");

    model.component("comp2").mesh().create("mesh2");
    model.component("comp2").mesh("mesh2").contribute("geom/detail", true);

    model.component("comp2").geom("geom2").lengthUnit("\u00b5m");
    model.component("comp2").geom("geom2").create("wp1", "WorkPlane");
    model.component("comp2").geom("geom2").feature("wp1").set("unite", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r1").set("size", new int[]{250, 120});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r1");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r2").set("size", new int[]{2, 172});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r2").set("pos", new int[]{100, 120});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r2");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r3").set("size", new int[]{12, 2});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r3").set("pos", new int[]{100, 290});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r3");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("r4", "Rectangle");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r4").set("size", new int[]{2, 148});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("r4").set("pos", new int[]{110, 144});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("r4");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("mir1", "Mirror");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir1").selection("input")
         .set("r2", "r3", "r4");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir1").set("keep", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir1").set("pos", new int[]{125, 0});
    model.component("comp2").geom("geom2").feature("wp1").geom().run("mir1");
    model.component("comp2").geom("geom2").feature("wp1").geom().create("mir2", "Mirror");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir2").selection("input")
         .set("mir1", "r2", "r3", "r4");
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir2").set("keep", true);
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir2").set("pos", new int[]{0, 60});
    model.component("comp2").geom("geom2").feature("wp1").geom().feature("mir2").set("axis", new int[]{0, 1});
    model.component("comp2").geom("geom2").run("wp1");
    model.component("comp2").geom("geom2").feature().create("ext1", "Extrude");
    model.component("comp2").geom("geom2").feature("ext1").setIndex("distance", 2.25, 0);
    model.component("comp2").geom("geom2").runPre("fin");

    model.title(null);

    model.description("");

    model.label("residual_stress_resonator_3d_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
