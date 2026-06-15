/*
 * ecore_transformer_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class ecore_transformer_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").set("quicky", 10);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{80, 70});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new int[]{60, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("size", new int[]{20, 50});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").selection("input")
         .set("r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("formula", "r1-r2+r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 20, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", -23);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 24);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", 4, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", 4, 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 46, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{150, 130, 150});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("ecore_transformer_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
