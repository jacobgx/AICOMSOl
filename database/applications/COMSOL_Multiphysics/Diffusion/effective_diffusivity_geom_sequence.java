/*
 * effective_diffusivity_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:33 by COMSOL 6.3.0.290. */
public class effective_diffusivity_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Diffusion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", "0.08[mm]");
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new String[]{"0.01[mm]", "0.01[mm]"});
    model.component("comp1").geom("geom1").feature("sq1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("sq1").set("selresultshow", false);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").named("sq1");
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "0.016[mm]");
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{8, 9});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"0.1[mm]", "0.1[mm]"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "-0.05[mm]");
    model.component("comp1").geom("geom1").feature("mov1").selection("input")
         .set("arr1(2,1)", "arr1(2,2)", "arr1(2,3)", "arr1(2,4)", "arr1(2,5)", "arr1(2,6)", "arr1(2,7)", "arr1(2,8)", "arr1(2,9)", "arr1(4,1)", "arr1(4,2)", "arr1(4,3)", "arr1(4,4)", "arr1(4,5)", "arr1(4,6)", "arr1(4,7)", "arr1(4,8)", "arr1(4,9)", "arr1(6,1)", "arr1(6,2)", "arr1(6,3)", "arr1(6,4)", "arr1(6,5)", "arr1(6,6)", "arr1(6,7)", "arr1(6,8)", "arr1(6,9)", "arr1(8,1)", "arr1(8,2)", "arr1(8,3)", "arr1(8,4)", "arr1(8,5)", "arr1(8,6)", "arr1(8,7)", "arr1(8,8)", "arr1(8,9)");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"0.8[mm]", "0.8[mm]"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("sq1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(0);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 532);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 276);
    model.component("comp1").geom("geom1").run("sel3");

    model.title(null);

    model.description("");

    model.label("effective_diffusivity_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
