/*
 * ion_implanter_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class ion_implanter_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.param().set("theta", "30[deg]");
    model.param().descr("theta", "\u6676\u5706\u89d2\u5ea6");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 0.4);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0, 0, -0.3});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0, -0.5, 0.45});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "y");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "0,90,180");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 0.5);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new double[]{-1.3, -1.25, 0});
    model.component("comp1").geom("geom1").feature("cyl3").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 0.2);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 0.3);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new double[]{-1.05, -1.25, -0.3});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.4, 0.2, 0.08});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.2, -0.45, -0.04});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 0.8);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new double[]{-0.8, -0.45});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new double[]{1.3, 1.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new double[]{-1.3, -1.25});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("uni1", 1, 3, 4, 5, 6, 7);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", 0.1);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("type", "curve");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 0.95);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("pos", new double[]{-0.8, -0.45});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").setIndex("layer", 0.3, 0);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("del1").selection("input").set("c1", 1, 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("size", new double[]{0.4, 0.36});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1").set("pos", new double[]{-0.2, -0.81});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").set("size", new double[]{0.5, 0.25});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").set("pos", new double[]{-0.12, -1.2});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2").set("rot", 45);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("uni1").selection("input")
         .set("del1", "r1", "r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.2, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("ext1", 10);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", 0.2);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.2, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("blk1", "cyl1", "cyl3", "cyl4", "ext1", "ext2", "rot1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp4", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp4").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp4").set("planetype", "coordinates");
    model.component("comp1").geom("geom1").feature("wp4").setIndex("genpoints", "sin(theta)", 2, 1);
    model.component("comp1").geom("geom1").feature("wp4").setIndex("genpoints", "cos(theta)", 2, 2);
    model.component("comp1").geom("geom1").feature("wp4").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("c1").set("r", 0.15);
    model.component("comp1").geom("geom1").feature("wp4").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp4").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("sq1").set("size", 0.35);
    model.component("comp1").geom("geom1").feature("wp4").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").run("wp4");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp4");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp4");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", 0.05, 0);
    model.component("comp1").geom("geom1").feature("ext3").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("ext3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1, 16, 18);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("comsel1").label("\u540e\u5904\u7406");

    model.title(null);

    model.description("");

    model.label("ion_implanter_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
