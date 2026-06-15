/*
 * bracket_uncertainty_quantification_fillet_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:34 by COMSOL 6.3.0.290. */
public class bracket_uncertainty_quantification_fillet_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Uncertainty_Quantification_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").geomRep("cadps");
    model.component("comp1").geom("geom1")
         .insertFile("bracket_uncertainty_quantification_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("ige1", "IgnoreEdges");

    model.component("comp1").view("view1").set("transparency", true);

    model.component("comp1").geom("geom1").feature("ige1").selection("input")
         .set("fin", 28, 35, 37, 41, 120, 124, 125, 128);
    model.component("comp1").geom("geom1").run("ige1");

    model.param().set("fr1", "0.01[m]");
    model.param().descr("fr1", "\u5706\u89d2\u534a\u5f84");

    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet3D");
    model.component("comp1").geom("geom1").feature("fil1").selection("edge")
         .set("uni1", 27, 30, 32, 33, 68, 71, 72, 77, 119, 121, 122, 123);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", "fr1");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("ige1");

    model.title(null);

    model.description("");

    model.label("bracket_uncertainty_quantification_fillet_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
