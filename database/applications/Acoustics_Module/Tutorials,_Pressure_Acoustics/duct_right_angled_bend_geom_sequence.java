/*
 * duct_right_angled_bend_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:40 by COMSOL 6.3.0.290. */
public class duct_right_angled_bend_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Tutorials,_Pressure_Acoustics");

    model.param().set("L", "120[cm]");
    model.param().descr("L", "\u6ce2\u5bfc\u957f\u5ea6");
    model.param().set("W", "30[cm]");
    model.param().descr("W", "\u6ce2\u5bfc\u5bbd\u5ea6");
    model.param().set("H", "20[cm]");
    model.param().descr("H", "\u6ce2\u5bfc\u9ad8\u5ea6");
    model.param().set("Rcurv", "8[cm]");
    model.param().descr("Rcurv", "\u5f2f\u5934\u66f2\u7387\u534a\u5f84");
    model.param().set("Rcyl", "20[cm]");
    model.param().descr("Rcyl", "\u5706\u67f1\u534a\u5f84\uff08\u51f9\u75d5\uff09");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"L", "W"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("size", new String[]{"W", "L"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("pos", new String[]{"L-W", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("r1", "r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point").set("uni1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "Rcurv");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("point").set("fil1", 7);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "1.2*Rcurv");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Rcyl");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"L/2", "L/2", "H+0.8*Rcyl"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("duct_right_angled_bend_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
