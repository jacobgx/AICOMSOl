/*
 * probe_tube_microphone_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:59 by COMSOL 6.3.0.290. */
public class probe_tube_microphone_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Pipe_Flow_Module\\Pipe_Acoustics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("L", "20[mm]");
    model.param().descr("L", "\u63a2\u7ba1\u957f\u5ea6");
    model.param().set("R", "5[mm]");
    model.param().descr("R", "\u9ea6\u514b\u98ce\u5916\u58f3\u534a\u5f84");
    model.param().set("H", "5[mm]");
    model.param().descr("H", "\u63a2\u7ba1\u4f53\u79ef\u9ad8\u5ea6");
    model.param().set("dr", "1[mm]");
    model.param().descr("dr", "\u7ba1\u5185\u534a\u5f84");
    model.param().set("dw", "0.3[mm]");
    model.param().descr("dw", "\u7ba1\u58c1\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("cone1", "Cone");
    model.component("comp1").geom("geom1").feature("cone1").set("specifytop", "radius");
    model.component("comp1").geom("geom1").feature("cone1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cone1").set("h", "H");
    model.component("comp1").geom("geom1").feature("cone1").set("rtop", "dr+dw");
    model.component("comp1").geom("geom1").run("cone1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "H");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-H"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "0 0");
    model.component("comp1").geom("geom1").feature("pol1").set("z", "H H+L");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "10[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "35[mm]");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "-H"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("planetype", "faceparallel");
    model.component("comp1").geom("geom1").feature("wp1").selection("face").set("cone1", 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "dr");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("probe_tube_microphone_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
