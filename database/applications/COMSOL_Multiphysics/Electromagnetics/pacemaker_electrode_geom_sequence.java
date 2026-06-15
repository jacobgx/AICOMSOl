/*
 * pacemaker_electrode_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class pacemaker_electrode_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Electromagnetics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"2.1[mm]", "20[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("size", new String[]{"0.5[mm]", "3[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"1.6[mm]", "12[mm]"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("dif1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", "0.5[mm]");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("point")
         .set("dif1", 5, 6, 7);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("rev1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").set("origfaces", false);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u7535\u6781");
    model.component("comp1").geom("geom1").feature("rev1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").label("\u7403\u5f62\u7535\u6781");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "1[mm]");
    model.component("comp1").geom("geom1").feature("sph1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("sph1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("sph1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new String[]{"0.5[mm]", "5[mm]"});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new String[]{"0.8[mm]", "2[mm]"});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cha1").selection("pointinsketch")
         .set("r1", 3);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("cha1").set("dist", "0.3[mm]");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("workplane", "wp2");
    model.component("comp1").geom("geom1").feature("rev2").selection("input").set("wp2");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev2").set("origfaces", false);
    model.component("comp1").geom("geom1").feature("rev2").set("pos", new String[]{"0.8[mm]", "0"});
    model.component("comp1").geom("geom1").run("rev2");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("rev2");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", -60);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new String[]{"0", "0.8[mm]", "2[mm]"});
    model.component("comp1").geom("geom1").feature("rot1").set("axistype", "x");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("rot2", "Rotate");
    model.component("comp1").geom("geom1").feature("rot2").selection("input").set("rot1");
    model.component("comp1").geom("geom1").feature("rot2").set("rot", "range(90,360/4,360)");
    model.component("comp1").geom("geom1").feature("rot2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("pacemaker_electrode_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
