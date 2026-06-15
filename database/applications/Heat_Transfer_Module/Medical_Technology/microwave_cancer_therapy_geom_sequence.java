/*
 * microwave_cancer_therapy_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:23 by COMSOL 6.3.0.290. */
public class microwave_cancer_therapy_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Medical_Technology");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{30, 80});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{0.595, 70});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 10});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").label("\u4ecb\u7535\u5c42");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{0.335, 69.9});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0.135, 10.1});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{0.895, 70});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new int[]{0, 10});
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("r5", "Rectangle");
    model.component("comp1").geom("geom1").feature("r5").label("\u7a7a\u6c14");
    model.component("comp1").geom("geom1").feature("r5").set("size", new double[]{0.125, 1});
    model.component("comp1").geom("geom1").feature("r5").set("pos", new double[]{0.47, 15.5});
    model.component("comp1").geom("geom1").feature("r5").set("selresult", true);
    model.component("comp1").geom("geom1").run("r5");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 0.895 0.895 0 0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "10 10 10 9.5 9.5 10");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u5bfc\u7ba1");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("pol1", "r4");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1", "uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");

    model.title(null);

    model.description("");

    model.label("microwave_cancer_therapy_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
