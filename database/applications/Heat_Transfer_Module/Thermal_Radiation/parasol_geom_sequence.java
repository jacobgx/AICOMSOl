/*
 * parasol_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:32 by COMSOL 6.3.0.290. */
public class parasol_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Thermal_Radiation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{6, 6, 1});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{0, 0, -0.5});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{0.3, 0.22, 0.18});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0.5, 0, 0.09});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{0.26, 0.18, 0.14});
    model.component("comp1").geom("geom1").run("blk3");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.03);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.125);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0.42, 0.04, 0.02});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{3, 2, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.08, -0.08, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("arr1", "blk2", "blk3");
    model.component("comp1").geom("geom1").feature("copy1").set("displx", -1.5);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("h", 0.3);
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "angle");
    model.component("comp1").geom("geom1").feature("cone1").set("ang", 70);
    model.component("comp1").geom("geom1").feature("cone1").set("pos", new double[]{0, 0, 1.5});
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").feature().duplicate("cone2", "cone1");
    model.component("comp1").geom("geom1").feature("cone2").set("pos", new double[]{0, 0, 1.4});
    model.component("comp1").geom("geom1").run("cone2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cone1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cone2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 1.7);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("parasol_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
