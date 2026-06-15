/*
 * pratt_truss_bridge_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:00 by COMSOL 6.3.0.290. */
public class pratt_truss_bridge_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Beams_and_Shells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("width", "7[m]");
    model.param().descr("width", "\u6865\u6881\u5bbd\u5ea6");
    model.param().set("height", "5[m]");
    model.param().descr("height", "\u6865\u6881\u9ad8\u5ea6");
    model.param().set("spacing", "5[m]");
    model.param().descr("spacing", "\u6cbf\u6865\u6881\u65b9\u5411\u7684\u6784\u4ef6\u95f4\u8ddd");
    model.param().set("length", "40[m]");
    model.param().descr("length", "\u6865\u6881\u603b\u957f\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"spacing", "width"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-length/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("linearsize", "length/spacing");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"spacing", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1")
         .set("x", "0 spacing spacing spacing");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("pol1").set("y", "0 height height 0");
    model.component("comp1").geom("geom1").feature("wp2").geom().run("pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord1", new String[]{"0", "height"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls1")
         .set("coord2", new String[]{"spacing", "height"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1").selection("input")
         .set("ls1", "pol1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1")
         .set("linearsize", "length/(2*spacing)-1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("arr1")
         .set("displ", new String[]{"spacing", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2")
         .set("coord1", new String[]{"length/2-spacing", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2").setIndex("coord1", "height", 1);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls2")
         .set("coord2", new String[]{"length/2", "0"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").selection("input")
         .set("arr1", "ls2");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("mir1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("ls3", "LineSegment");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls3").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls3").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("ls3")
         .set("coord2", new String[]{"0", "height"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("ls3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "width");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("wp2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-length/2+spacing", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord1", "height", 2);
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"-length/2+spacing", "0", "0"});
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "width", 1);
    model.component("comp1").geom("geom1").feature("ls1").setIndex("coord2", "height", 2);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("ls1");
    model.component("comp1").geom("geom1").feature("arr1").set("type", "linear");
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"spacing", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").set("linearsize", "length/spacing-1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("pratt_truss_bridge_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
