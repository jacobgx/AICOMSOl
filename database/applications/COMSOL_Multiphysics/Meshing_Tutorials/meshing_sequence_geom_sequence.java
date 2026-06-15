/*
 * meshing_sequence_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:35 by COMSOL 6.3.0.290. */
public class meshing_sequence_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Meshing_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"2[mm]", "2.5[mm]", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "0.5[mm]", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "0", "0.5[mm]/2+0.3[mm]"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"4[mm]", "4[mm]", "1.23[mm]"});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "-1.23[mm]/2"});
    model.component("comp1").geom("geom1").feature("blk2").setIndex("layer", "0.07[mm]", 0);
    model.component("comp1").geom("geom1").feature("blk2").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("blk2").set("layertop", true);
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", "0.4[mm]/2");
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new String[]{"2[mm]/2-0.25[mm]", "0", "0"});
    model.component("comp1").geom("geom1").feature("sph1").setIndex("pos", "2.5[mm]/2-0.25[mm]", 1);
    model.component("comp1").geom("geom1").feature("sph1").setIndex("pos", "0.15[mm]", 2);
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("sph1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("blk1", "blk2");
    model.component("comp1").geom("geom1").feature("dif1").set("keepsubtract", true);
    model.component("comp1").geom("geom1").run("dif1");

    model.component("comp1").view("view1").set("renderwireframe", false);

    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("dif1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{4, 5, 1});
    model.component("comp1").geom("geom1").feature("arr1")
         .set("displ", new String[]{"-(2[mm]-2*0.25[mm])/(4-1)", "0", "0"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("displ", "-(2.5[mm]-2*0.25[mm])/(5-1)", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("transparency", true);

    model.title(null);

    model.description("");

    model.label("meshing_sequence_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
