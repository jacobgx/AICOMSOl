/*
 * rotating_machinery_3d_tutorial_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:21 by COMSOL 6.3.0.290. */
public class rotating_machinery_3d_tutorial_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Motors_and_Generators");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("R1", "1[cm]");
    model.param().descr("R1", "\u51e0\u4f55\u5916\u534a\u5f84");
    model.param().set("R2", "6[mm]");
    model.param().descr("R2", "\u6ed1\u52a8\u754c\u9762\u7684\u4f4d\u7f6e");
    model.param().set("R3", "5[mm]");
    model.param().descr("R3", "\u65cb\u8f6c\u5bf9\u8c61\u7684\u534a\u5f84");
    model.param().set("H1", "5[mm]");
    model.param().descr("H1", "\u78c1\u82af\u548c\u65cb\u8f6c\u76d8\u7684\u9ad8\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "R1");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "3*H1");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "0", "-1.5*H1"});
    model.component("comp1").geom("geom1").run("cyl1");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "R2");
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").feature().duplicate("cyl3", "cyl2");
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("cyl3");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").feature().duplicate("cyl4", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", "R3");
    model.component("comp1").geom("geom1").feature("cyl4").set("h", "H1");
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new String[]{"0", "0", "-H1/2"});
    model.component("comp1").geom("geom1").feature("cyl4").setIndex("layer", "H1/2", 0);
    model.component("comp1").geom("geom1").feature("cyl4").set("layerside", false);
    model.component("comp1").geom("geom1").feature("cyl4").set("layerbottom", true);
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl2", "cyl4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "-H1/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"(R1-R2)/3", "(R1-R2)/6"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"-R1+(R1-R2)/2", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("pos", "-(R1-R2)/12", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H1", 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("dif1", "ext1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("rotating_machinery_3d_tutorial_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
