/*
 * mast_diagonal_mounting_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:37 by COMSOL 6.3.0.290. */
public class mast_diagonal_mounting_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Structural_Mechanics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("h_t", "200[mm]");
    model.param().descr("h_t", "\u7ba1\u9ad8\u5ea6");
    model.param().set("r_t", "50[mm]");
    model.param().descr("r_t", "\u7ba1\u5916\u534a\u5f84");
    model.param().set("t_t", "10[mm]");
    model.param().descr("t_t", "\u7ba1\uff0c\u58c1\u539a");
    model.param().set("t_p", "10[mm]");
    model.param().descr("t_p", "\u5e73\u677f\u539a\u5ea6");
    model.param().set("r_p", "60[mm]");
    model.param().descr("r_p", "\u5e73\u677f\u534a\u5f84");
    model.param().set("r_ph", "5[mm]");
    model.param().descr("r_ph", "\u5e73\u677f\u5b54\u534a\u5f84");
    model.param().set("o_p", "65[mm]");
    model.param().descr("o_p", "\u5e73\u677f\u5b54\u504f\u79fb");
    model.param().set("t_m", "10[mm]");
    model.param().descr("t_m", "\u5e95\u5ea7\u539a\u5ea6");
    model.param().set("w_m", "55[mm]");
    model.param().descr("w_m", "\u5e95\u5ea7\u5bbd\u5ea6");
    model.param().set("r_mh", "12[mm]");
    model.param().descr("r_mh", "\u5e95\u5ea7\u5b54\u534a\u5f84");
    model.param().set("o_mh", "70[mm]");
    model.param().descr("o_mh", "\u5e95\u5ea7\u5b54\u5bf9\u5e73\u677f\u7684\u504f\u79fb");
    model.param().set("o_m", "22[mm]");
    model.param().descr("o_m", "\u5e95\u5ea7\u95f4\u8ddd");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_t");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "h_t");
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "t_t", 0);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_p");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "t_p");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "h_t"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("cyl2", 1);
    model.component("comp1").geom("geom1").feature("sel1").label("\u5e95\u677f");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r_ph");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "h_t+2*t_p");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "o_p/2", "0"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("cyl3");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "-o_p");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1", "cyl3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"w_m", "o_mh"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-w_m/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "w_m/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new String[]{"0", "o_mh"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").set("c1", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "r_mh");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new String[]{"0", "o_mh"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "t_m", 0);
    model.component("comp1").geom("geom1").run("ext1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("ext1", 1);
    model.component("comp1").geom("geom1").feature("sel2").label("\u5e95\u5ea7");
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "o_m/2");
    model.component("comp1").geom("geom1").feature("mov1").set("displz", "h_t+t_p");
    model.component("comp1").geom("geom1").run("mov1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("mov1");
    model.component("comp1").geom("geom1").feature("copy2").set("displx", "-(o_m+t_m)");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("mast_diagonal_mounting_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
