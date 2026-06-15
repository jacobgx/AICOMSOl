/*
 * water_purification_reactor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:32 by COMSOL 6.3.0.290. */
public class water_purification_reactor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\CFD_Module\\Single-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{5, 2, 1});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.5, 0, 0});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{-0.5, 1, 0.5});
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{5, 1, 0.5});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{0.1, 1, 1});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{0.95, 1, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{2.95, 1, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk2");
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new double[]{1.95, 0, 0});
    model.component("comp1").geom("geom1").feature().duplicate("blk5", "blk4");
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new double[]{3.95, 0, 0});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk2", "blk3", "blk4", "blk5");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "cyl2", "dif1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new double[]{-0.5, 0, 0.5});
    model.component("comp1").geom("geom1").feature("blk6").set("size", new double[]{6, 2, 0.5});
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("blk6");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("water_purification_reactor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
