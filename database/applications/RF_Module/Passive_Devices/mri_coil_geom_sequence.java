/*
 * mri_coil_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:39 by COMSOL 6.3.0.290. */
public class mri_coil_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\RF_Module\\Passive_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("f0", "63.87[MHz]", "\u62c9\u83ab\u5c14\u9891\u7387");
    model.param().set("lda0", "c_const/f0", "\u62c9\u83ab\u5c14\u9891\u7387\u5bf9\u5e94\u7684\u6ce2\u957f");
    model.param().set("c_value", "28.5[pF]", "\u6a2a\u6863\u4e0a\u4f7f\u7528\u7684\u7535\u5bb9");
    model.param().set("r_coil", "0.24[m]", "\u7ebf\u5708\u534a\u5f84");
    model.param().set("h_coil", "0.3[m]", "\u7ebf\u5708\u9ad8\u5ea6");
    model.param().set("l_element", "0.01[m]", "\u7535\u5bb9\u5143\u4ef6\u957f\u5ea6");
    model.param().set("r_coil_1", "0.24[m]");
    model.param().set("l_element_1", "0.01[m]");
    model.param().set("h_coil_1", "0.3[m]");
    model.param().rename("r_coil_1", "t_ring");
    model.param().set("t_ring", "0.015[m]");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_coil");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "h_coil");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-h_coil/2"});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", "t_ring", 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl1").set("layerbottom", true);
    model.component("comp1").geom("geom1").feature("cyl1").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-h_coil/2+t_ring");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "r_coil");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 6);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("rot", -22.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("ccur1", "ConvertToCurve");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("ccur1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("ccur1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .set("ccur1", 2, 3);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "h_coil-2*t_ring", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "l_element", 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("mov1")
         .set("displz", "0 (h_coil-2*t_ring)/2-l_element/2 (h_coil-2*t_ring)-l_element");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("ext1", "mov1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "0 45 90 135 180 225 270 315");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("csur1", "ConvertToSurface");
    model.component("comp1").geom("geom1").feature("csur1").selection("input").set("cyl1", "rot1");
    model.component("comp1").geom("geom1").run("csur1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("csur1", 3, 4, 5, 6, 9, 10, 21, 22, 33, 34, 36, 39, 51, 52, 63, 64);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 0.15);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "h_coil");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new String[]{"0", "0", "-h_coil/2"});
    model.component("comp1").geom("geom1").feature("cyl2").setIndex("layer", "h_coil/2", 0);
    model.component("comp1").geom("geom1").feature("cyl2").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl2").set("layertop", true);
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", "r_coil+0.1");
    model.component("comp1").geom("geom1").feature("cyl3").set("h", "h_coil+0.1");
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new String[]{"0", "0", "-(h_coil+0.1)/2"});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 0.5);
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("mri_coil_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
