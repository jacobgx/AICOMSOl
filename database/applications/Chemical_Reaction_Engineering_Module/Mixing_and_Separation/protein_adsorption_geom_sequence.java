/*
 * protein_adsorption_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:21 by COMSOL 6.3.0.290. */
public class protein_adsorption_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Mixing_and_Separation");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 4.25);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 2.15);
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{4.25, 4.25, 2.15});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("int1", "Intersection");
    model.component("comp1").geom("geom1").feature("int1").selection("input").set("blk1", "cyl1");
    model.component("comp1").geom("geom1").run("int1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new double[]{0.45, 0.45, 0.45});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 4, 2});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{0.95, 0.95, 0.95});
    model.component("comp1").geom("geom1").feature("arr1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("arr1").set("selresultshow", "bnd");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set("arr1(3,4,1)", "arr1(3,4,2)", "arr1(4,3,1)", "arr1(4,3,2)", "arr1(4,4,1)", "arr1(4,4,2)");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("int1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("arr1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 37);
    model.component("comp1").geom("geom1").feature("sel1").label("\u67f1\u58c1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 4);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u67f1\u6bb5\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 1, 2);

    model.title(null);

    model.description("");

    model.label("protein_adsorption_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
