/*
 * rfid_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class rfid_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Magnetostatics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("type", "closed");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "0.8875 0.8875 0.8875 0.9125 0.9125 0.9125 0.9125 0.89 0.89 0.89 0.89 0.91 0.91 0.91 0.91 0.8925 0.8925 0.8925 0.8925 0.9075 0.9075 0.9075 0.9075 0.895 0.895 0.895 0.895 0.8875");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "0 0.01 0.01 0.01 0.01 -0.01 -0.01 -0.01 -0.01 0.0075 0.0075 0.0075 0.0075 -0.0075 -0.0075 -0.0075 -0.0075 0.005 0.005 0.005 0.005 -0.005 -0.005 -0.005 -0.005 0 0 0");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{1.8, 0.8});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{0, -0.4});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 0.2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("wp1.fil1");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", -0.4);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 3);
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new double[]{0, 0, 0.9});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.title(null);

    model.description("");

    model.label("rfid_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
