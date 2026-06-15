/*
 * einzel_lens_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:49 by COMSOL 6.3.0.290. */
public class einzel_lens_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Particle_Tracing_Module\\Charged_Particle_Tracing");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_vac", "120[cm]", "\u771f\u7a7a\u5ba4\u5bbd\u5ea6");
    model.param().set("R_vac", "10[cm]", "\u771f\u7a7a\u5ba4\u9ad8\u5ea6");
    model.param().set("L_cyl", "6[cm]", "\u900f\u955c\u4e2d\u7684\u5706\u67f1\u957f\u5ea6");
    model.param().set("T_cyl", "0.25[cm]", "\u900f\u955c\u4e2d\u7684\u5706\u67f1\u539a\u5ea6");
    model.param().set("R_cyl_fil", "T_cyl/2", "\u900f\u955c\u4e2d\u5706\u67f1\u7684\u5706\u89d2\u534a\u5f84");
    model.param().set("R_cyl", "2[cm]", "\u5706\u67f1\u534a\u5f84");
    model.param().set("d_lens", "40[cm]", "\u900f\u955c\u5f00\u59cb\u90e8\u5206\u7684\u4e0b\u6e38\u8ddd\u79bb");
    model.param().set("cyl_sep", "2[cm]", "\u5706\u67f1\u95f4\u8ddd");
    model.param()
         .set("initial_beam_radius", "1[cm]", "\u771f\u7a7a\u5ba4\u5165\u53e3\u7684\u5149\u675f\u534a\u5f84");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "zx");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"T_cyl", "L_cyl"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"R_cyl", "d_lens"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("r1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "R_cyl_fil");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{1, 3});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "L_cyl + cyl_sep"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"R_vac", "L_vac"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pt1")
         .setIndex("p", "initial_beam_radius", 0);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").runPre("fin");

    model.title(null);

    model.description("");

    model.label("einzel_lens_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
