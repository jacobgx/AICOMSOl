/*
 * disc_brake_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:02 by COMSOL 6.3.0.290. */
public class disc_brake_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.14);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.013);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.08);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.01);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0, 0, 0.013});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 0.013);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.135, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.02, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.135, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.05, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.13, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.04, 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("p", 0.105, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb1").setIndex("w", 2.5, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb2", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.04, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.105, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.03, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.08, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.035, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.09, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb2").setIndex("p", 0.09, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb3", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.09, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.035, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.09, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.03, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.08, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", -0.04, 0, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb3").setIndex("p", 0.105, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("cb4", "CubicBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.04, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.05, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.105, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.13, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", -0.02, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.135, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("p", 0.135, 1, 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("cb4").setIndex("w", 2.5, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("cb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("csol1").selection("input")
         .set("cb1", "cb2", "cb3", "cb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("csol1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.0065, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("disc_brake_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
