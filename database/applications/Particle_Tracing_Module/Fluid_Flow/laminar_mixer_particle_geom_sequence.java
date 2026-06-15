/*
 * laminar_mixer_particle_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:54 by COMSOL 6.3.0.290. */
public class laminar_mixer_particle_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Fluid_Flow");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("Ra", "3[mm]");
    model.param().descr("Ra", "\u7ba1\u534a\u5f84");

    model.component("comp1").geom("geom1").label("\u6405\u62cc\u5668\u51e0\u4f55\u5e8f\u5217");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "xz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"2.4*Ra", "Ra/8"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "Ra/2", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("twist", 30, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("ext1");
    model.component("comp1").geom("geom1").feature("copy1").set("disply", "-Ra/2");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("copy1");
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 30);
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("copy2", "Copy");
    model.component("comp1").geom("geom1").feature("copy2").selection("input").set("ext1", "rot1");
    model.component("comp1").geom("geom1").feature("copy2").set("disply", "-Ra");
    model.component("comp1").geom("geom1").run("copy2");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("copy2");
    model.component("comp1").geom("geom1").feature("rot2").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", 60);
    model.component("comp1").geom("geom1").run("rot2");
    model.component("comp1").geom("geom1").create("copy3", "Copy");
    model.component("comp1").geom("geom1").feature("copy3").selection("input").set("ext1", "rot1");
    model.component("comp1").geom("geom1").feature("copy3").set("disply", "-2*Ra");
    model.component("comp1").geom("geom1").run("copy3");
    model.component("comp1").geom("geom1").create("rot3", "Rotate");
    model.component("comp1").geom("geom1").feature("rot3").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot3").set("rot", 120);
    model.component("comp1").geom("geom1").feature("rot3").selection("input").set("copy3");
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").create("copy4", "Copy");
    model.component("comp1").geom("geom1").feature("copy4").selection("input").set("ext1", "rot1", "rot2", "rot3");
    model.component("comp1").geom("geom1").feature("copy4").set("disply", "-3*Ra");
    model.component("comp1").geom("geom1").run("copy4");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("copy4");
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("rot4", "Rotate");
    model.component("comp1").geom("geom1").feature("rot4").selection("input").set("mir1");
    model.component("comp1").geom("geom1").feature("rot4").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("rot4").set("rot", 90);
    model.component("comp1").geom("geom1").run("rot4");
    model.component("comp1").geom("geom1").create("copy5", "Copy");
    model.component("comp1").geom("geom1").feature("copy5").selection("input").set("ext1", "rot1", "rot2", "rot3");
    model.component("comp1").geom("geom1").feature("copy5").set("disply", "-6*Ra");
    model.component("comp1").geom("geom1").run("copy5");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Ra");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "14*Ra");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"0", "-12*Ra", "0"});
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "y");
    model.component("comp1").geom("geom1").feature("cyl1").set("rot", -15);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input")
         .set("copy5", "ext1", "rot1", "rot2", "rot3", "rot4");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("uni1");
    model.component("comp1").geom("geom1").run("dif1");

    model.title(null);

    model.description("");

    model.label("laminar_mixer_particle_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
