/*
 * capacitor_tunable_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class capacitor_tunable_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Capacitive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{22, 60, 8});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{0, 240, 46});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{40, 22, 8});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{22, 259, 46});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{176, 262, 8});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{62, 19, 46});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new int[]{40, 22, 8});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{238, 259, 46});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new int[]{22, 60, 8});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new int[]{278, 240, 46});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("blk6", "Block");
    model.component("comp1").geom("geom1").feature("blk6").set("size", new int[]{40, 22, 8});
    model.component("comp1").geom("geom1").feature("blk6").set("pos", new int[]{238, 19, 46});
    model.component("comp1").geom("geom1").run("blk6");
    model.component("comp1").geom("geom1").create("blk7", "Block");
    model.component("comp1").geom("geom1").feature("blk7").set("size", new int[]{22, 60, 8});
    model.component("comp1").geom("geom1").feature("blk7").set("pos", new int[]{278, 0, 46});
    model.component("comp1").geom("geom1").run("blk7");
    model.component("comp1").geom("geom1").create("blk8", "Block");
    model.component("comp1").geom("geom1").feature("blk8").set("size", new int[]{40, 22, 8});
    model.component("comp1").geom("geom1").feature("blk8").set("pos", new int[]{22, 19, 46});
    model.component("comp1").geom("geom1").run("blk8");
    model.component("comp1").geom("geom1").create("blk9", "Block");
    model.component("comp1").geom("geom1").feature("blk9").set("size", new int[]{22, 229, 8});
    model.component("comp1").geom("geom1").feature("blk9").set("pos", new int[]{0, 41, 0});
    model.component("comp1").geom("geom1").run("blk9");
    model.component("comp1").geom("geom1").create("blk10", "Block");
    model.component("comp1").geom("geom1").feature("blk10").set("size", new int[]{40, 22, 8});
    model.component("comp1").geom("geom1").feature("blk10").set("pos", new int[]{-40, 139, 0});
    model.component("comp1").geom("geom1").run("blk10");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 5.5);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 38);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{11, 250, 8});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("blk1", "blk10", "blk2", "blk3", "blk4", "blk5", "blk6", "blk7", "blk8", "blk9", "cyl1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("blk11", "Block");
    model.component("comp1").geom("geom1").feature("blk11").set("size", new int[]{176, 262, 8});
    model.component("comp1").geom("geom1").feature("blk11").set("pos", new int[]{62, 19, 8});
    model.component("comp1").geom("geom1").run("blk11");
    model.component("comp1").geom("geom1").create("blk12", "Block");
    model.component("comp1").geom("geom1").feature("blk12").set("size", new int[]{181, 22, 8});
    model.component("comp1").geom("geom1").feature("blk12").set("pos", new int[]{139, 139, 0});
    model.component("comp1").geom("geom1").run("blk12");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("blk11", "blk12");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");

    model.title(null);

    model.description("");

    model.label("capacitor_tunable_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
