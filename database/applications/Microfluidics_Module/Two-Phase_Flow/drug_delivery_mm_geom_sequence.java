/*
 * drug_delivery_mm_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:11 by COMSOL 6.3.0.290. */
public class drug_delivery_mm_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Microfluidics_Module\\Two-Phase_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.05, 1.5});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.9, 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 0.5, 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("e1", "Ellipse");
    model.component("comp1").geom("geom1").feature("e1").set("semiaxes", new double[]{0.09, 0.12});
    model.component("comp1").geom("geom1").feature("e1").set("pos", new double[]{0, 1.15});
    model.component("comp1").geom("geom1").feature("e1").set("type", "curve");
    model.component("comp1").geom("geom1").run("e1");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").set("e1");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("drug_delivery_mm_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
