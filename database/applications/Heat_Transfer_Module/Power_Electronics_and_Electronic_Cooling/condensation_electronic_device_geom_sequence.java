/*
 * condensation_electronic_device_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:26 by COMSOL 6.3.0.290. */
public class condensation_electronic_device_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 50);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("sq2", "Square");
    model.component("comp1").geom("geom1").feature("sq2").set("size", 49);
    model.component("comp1").geom("geom1").feature("sq2").set("pos", new double[]{0.5, 0.5});
    model.component("comp1").geom("geom1").run("sq2");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").label("\u7535\u5b50\u5143\u4ef6");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{14, 4});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{18, 0.5});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.5, 1});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 41});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{49.5, 15});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r2", "r3", "sq2");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6e7f\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 2);

    model.title(null);

    model.description("");

    model.label("condensation_electronic_device_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
