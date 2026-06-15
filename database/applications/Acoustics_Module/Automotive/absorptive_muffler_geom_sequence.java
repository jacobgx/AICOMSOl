/*
 * absorptive_muffler_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:25 by COMSOL 6.3.0.290. */
public class absorptive_muffler_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Acoustics_Module\\Automotive");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L", "600[mm]", "\u6d88\u58f0\u5668\u957f\u5ea6");
    model.param().set("H", "150[mm]", "\u6d88\u58f0\u5668\u9ad8\u5ea6");
    model.param().set("W", "300[mm]", "\u6d88\u58f0\u5668\u5bbd\u5ea6");
    model.param().set("L_io", "150[mm]", "\u5165\u53e3\u548c\u51fa\u53e3\u957f\u5ea6");
    model.param().set("R_io", "40[mm]", "\u5165\u53e3\u548c\u51fa\u53e3\u534a\u5f84");
    model.param().set("D", "15[mm]", "\u5185\u886c\u539a\u5ea6");
    model.param()
         .set("d_center", "60[mm]", "\u5165\u53e3\u548c\u51fa\u53e3\u5230\u6d88\u58f0\u5668\u4e2d\u5fc3\u7684\u8ddd\u79bb");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new String[]{"W", "H"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "H/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"W-2*D", "H-2*D"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil2", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").selection("pointinsketch")
         .set("r2", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil2").set("radius", "(H-2*D)/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil2");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R_io");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "L_io");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"-L_io", "d_center", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl1").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R_io");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "L_io");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"L", "-d_center", "0"});
    model.component("comp1").geom("geom1").feature("cyl2").set("axistype", "cartesian");
    model.component("comp1").geom("geom1").feature("cyl2").set("ax3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("absorptive_muffler_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
