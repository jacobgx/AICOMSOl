/*
 * busbar_terminal_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:23 by COMSOL 6.3.0.290. */
public class busbar_terminal_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Introductory_Electric_Currents");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("L", "9[cm]");
    model.param().descr("L", "\u957f\u5ea6");
    model.param().set("rad_1", "6[mm]");
    model.param().descr("rad_1", "\u87ba\u6813\u534a\u5f84");
    model.param().set("tbb", "5[mm]");
    model.param().descr("tbb", "\u539a\u5ea6");
    model.param().set("wbb", "5[cm]");
    model.param().descr("wbb", "\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");

    model.component("comp1").view("view2").axis().set("xmin", "-1e-2");
    model.component("comp1").view("view2").axis().set("xmax", 0.11);
    model.component("comp1").view("view2").axis().set("ymin", "-1e-2");
    model.component("comp1").view("view2").axis().set("ymax", 0.11);
    model.component("comp1").view("view2").axis().set("manualgrid", true);
    model.component("comp1").view("view2").axis().set("xspacing", "5e-3");
    model.component("comp1").view("view2").axis().set("yspacing", "5e-3");

    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"L+2*tbb", "0.1[m]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"L+tbb", "0.1[m]-tbb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new String[]{"0", "tbb"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("dif1", 3);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "tbb");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").set("fil1", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "2*tbb");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "wbb", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp2").selection("face").set("ext1", 8);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", "rad_1");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("c1");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("ext2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "-2*tbb", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp3").selection("face").set("ext1", 4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", "rad_1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1")
         .set("pos", new String[]{"-L/2+1.5[cm]", "0"});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").setIndex("pos", "-wbb/4", 1);
    model.component("comp1").geom("geom1").feature("wp3").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("copy1").set("disply", "wbb/2");
    model.component("comp1").geom("geom1").feature("wp3").geom().run("copy1");
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").set("workplane", "wp3");
    model.component("comp1").geom("geom1").feature("ext3").selection("input").set("wp3");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "-2*tbb", 0);
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("busbar_terminal_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
