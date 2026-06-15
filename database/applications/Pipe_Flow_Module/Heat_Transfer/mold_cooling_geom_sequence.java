/*
 * mold_cooling_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:58 by COMSOL 6.3.0.290. */
public class mold_cooling_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.5, 0.5, 0.15});
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").run("blk1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "4e-2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord1", new double[]{-0.25, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls1")
         .set("coord2", new double[]{0.11, 0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.11, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.2, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.2, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.21, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb1").setIndex("p", 0.15, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb2", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.21, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.15, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.1, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.11, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb2").setIndex("p", 0.1, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord1", new double[]{0.11, 0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls2")
         .set("coord2", new double[]{-0.11, 0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb3", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", -0.11, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 0.1, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", -0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 0.1, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", -0.21, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb3").setIndex("p", 0.05, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb4", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", -0.21, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", 0.05, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", -0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb4").setIndex("p", -0.1, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("coord1", new double[]{-0.1, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls3").set("coord2", new double[]{0.11, 0});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb5", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 0.11, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", 0.21, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb5").setIndex("p", -0.05, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb6", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 0.21, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", -0.05, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", -0.1, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", 0.11, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb6").setIndex("p", -0.1, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb6");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls4", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4")
         .set("coord1", new double[]{0.11, -0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls4")
         .set("coord2", new double[]{-0.1, -0.1});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls4");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb7", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.1, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.1, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.1, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.21, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb7").setIndex("p", -0.15, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb7");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("qb8", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.21, 0, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.15, 1, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.21, 0, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.2, 1, 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.1, 0, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("qb8").setIndex("p", -0.2, 1, 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("qb8");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ls5", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5")
         .set("coord1", new double[]{-0.1, -0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ls5")
         .set("coord2", new double[]{0.25, -0.2});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ls5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ccur1").selection("input")
         .set("ls1", "ls2", "ls3", "ls4", "ls5", "qb1", "qb2", "qb3", "qb4", "qb5", "qb6", "qb7", "qb8");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ccur1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", "-8e-2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{0, 0, -0.04});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("rot1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("rot1");

    model.title(null);

    model.description("");

    model.label("mold_cooling_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
