/*
 * high_voltage_insulator_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class high_voltage_insulator_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Electromagnetics_and_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new int[]{12, 100});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{6, 1050});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{0, 50});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{12, 100});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new int[]{0, 1050});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 12, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 100, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 12, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 150, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 60, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 150, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 20, 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 153, 3, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 20, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 153, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 13, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 154, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 12, 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 161, 1, 2);
    model.component("comp1").geom("geom1").feature("qb1").set("w", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 12, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 161, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 12, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 200, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 60, 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 200, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 20, 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 203, 3, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 20, 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 203, 1, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 13, 0, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 204, 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 12, 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 211, 1, 2);
    model.component("comp1").geom("geom1").feature("qb2").set("w", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 12, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 211, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 12, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 250, 1, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 80, 2, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 250, 2, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 20, 3, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 253, 3, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 20, 0, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 253, 1, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 13, 0, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 254, 1, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 12, 0, 2);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 261, 1, 2);
    model.component("comp1").geom("geom1").feature("qb3").set("w", new int[]{1, 1, 1});
    model.component("comp1").geom("geom1").run("qb3");
    model.component("comp1").geom("geom1").feature().compositeCurves("pol1", "qb1", "pol2", "qb2", "pol3", "qb3");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cc1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", 5);
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 161});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").feature().duplicate("cc2", "cc1");
    model.component("comp1").geom("geom1").run("cc2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("cc2");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", 794);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 111, 0, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 256, 1, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 6, 2, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 256, 2, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 6, 3, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", -694, 3, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 12, 4, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", -694, 4, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature().removeCurveComponents("qb3");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("arr1", "mov1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r1", 2);
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("r3", 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 8);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "2[m]");
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{0, 500});
    model.component("comp1").geom("geom1").feature("c1").set("angle", 180);
    model.component("comp1").geom("geom1").feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("high_voltage_insulator_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
