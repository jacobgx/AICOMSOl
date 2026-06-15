/*
 * loaded_knee_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:47 by COMSOL 6.3.0.290. */
public class loaded_knee_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Topology_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.param().set("a", "45[cm]");
    model.param().descr("a", "\u603b\u5bbd\u5ea6");
    model.param().set("b", "50[cm]");
    model.param().descr("b", "\u603b\u9ad8\u5ea6");
    model.param().set("c", "18[cm]");
    model.param().descr("c", "\u6bb5\u5bbd");
    model.param().set("r", "5[cm]");
    model.param().descr("r", "\u5706\u89d2\u534a\u5f84");
    model.param().set("L", "5[cm]");
    model.param().descr("L", "\u8d1f\u8f7d\u8fb9\u957f");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"a", "b"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"a-c", "b-c"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"c", "c"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("dif1", 3);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "r");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "a-L", 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", "c", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("loaded_knee_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
