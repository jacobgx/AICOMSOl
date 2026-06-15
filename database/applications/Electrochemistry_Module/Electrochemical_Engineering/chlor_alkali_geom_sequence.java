/*
 * chlor_alkali_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:53 by COMSOL 6.3.0.290. */
public class chlor_alkali_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{3.5, 10});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new double[]{-3.5, -4});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -2.5, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.5, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -3, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.8, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", -3.5, 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 1.8, 1, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "-3.5 -3.5 -3.5 -2.25 -2.25 -1.5 -1.5 -2.5");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "1.8 0.3 0.3 -0.3 -0.3 0.8 0.8 1.5");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("pol1", "qb1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -1.75, 0, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 1.7, 1, 0);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -2.55, 0, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 2.2, 1, 1);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", -3.5, 0, 2);
    model.component("comp1").geom("geom1").feature("qb2").setIndex("p", 2.2, 1, 2);
    model.component("comp1").geom("geom1").run("qb2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{-3.5, 2.2});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{-3.5, 2});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", -3.5, 0, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 2, 1, 0);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", -2.63, 0, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 2, 1, 1);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", -1.75, 0, 2);
    model.component("comp1").geom("geom1").feature("qb3").setIndex("p", 1.4, 1, 2);
    model.component("comp1").geom("geom1").run("qb3");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{-1.75, 1.4});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new double[]{-1.75, 1.7});
    model.component("comp1").geom("geom1").run("ls2");
    model.component("comp1").geom("geom1").create("csol2", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol2").selection("input").set("ls1", "ls2", "qb2", "qb3");
    model.component("comp1").geom("geom1").run("csol2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("csol1", "csol2");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{-1.75, 1.55});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("csol2", "rot1(2)");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("csol1", "rot1(1)");

    model.title(null);

    model.description("");

    model.label("chlor_alkali_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
