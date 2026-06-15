/*
 * packed_bed_latent_heat_storage_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:12 by COMSOL 6.3.0.290. */
public class packed_bed_latent_heat_storage_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Porous_Media_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.65, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.537, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.18, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.49, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.18, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.02, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.067, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.18, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 6, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", -0.18, 6, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 7, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.65, 7, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.05, 8, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.65, 8, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("thi1", "Thicken2D");
    model.component("comp1").geom("geom1").feature("thi1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("thi1").selection("input").set("pol1", 4, 5, 6, 7, 8);
    model.component("comp1").geom("geom1").feature("thi1").set("offset", "asymmetric");
    model.component("comp1").geom("geom1").feature("thi1").set("upthick", 0.05);
    model.component("comp1").geom("geom1").feature("thi1").set("convexcorner", "extend");
    model.component("comp1").geom("geom1").feature("thi1").set("keep", true);
    model.component("comp1").geom("geom1").run("thi1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("pol1", 4, 5, 7, 8);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("thi1", 2, 3, 6, 7, 9, 10, 11, 12);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 0.01);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.18, 0.47});
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("packed_bed_latent_heat_storage_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
