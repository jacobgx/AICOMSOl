/*
 * surface_mount_resistor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:01 by COMSOL 6.3.0.290. */
public class surface_mount_resistor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fatigue_Module\\Energy_Based");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{3.1, 0.8});
    model.component("comp1").geom("geom1").feature("r1").set("selresult", true);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{1.6, 0.55});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{1.5, 0.9});
    model.component("comp1").geom("geom1").feature("r2").set("selresult", true);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{1.05, 0.025});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0.825, 0.8});
    model.component("comp1").geom("geom1").feature("r3").set("selresult", true);
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("r4", "Rectangle");
    model.component("comp1").geom("geom1").feature("r4").set("size", new double[]{0.35, 0.6});
    model.component("comp1").geom("geom1").feature("r4").set("pos", new double[]{1.475, 0.875});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7d2f\u79ef\u9009\u62e9 1");
    model.component("comp1").geom("geom1").feature("r4").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.825, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.825, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.35, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 0.95, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.475, 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.475, 1, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.475, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.475, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.475, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.875, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 1.825, 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0.875, 2, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new double[]{1.875, 0.875});
    model.component("comp1").geom("geom1").feature("ca1").set("r", 0.05);
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 180);
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", 270);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex1").set("ca1", 2);
    model.component("comp1").geom("geom1").feature("ls1").selection("vertex2").set("qb1", 1);
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ca1", "ls1", "pol1", "qb1");
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7d2f\u79ef\u9009\u62e9 2");
    model.component("comp1").geom("geom1").feature("csol1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 1.48, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.87, 1);
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pt1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");
    model.component("comp1").geom("geom1").feature("ige1").selection("input").set("fin", 14);
    model.component("comp1").geom("geom1").run("ige1");
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("surface_mount_resistor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
