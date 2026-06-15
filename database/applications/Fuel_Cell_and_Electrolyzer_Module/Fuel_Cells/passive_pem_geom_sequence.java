/*
 * passive_pem_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:09 by COMSOL 6.3.0.290. */
public class passive_pem_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.param().set("L", "10[cm]");
    model.param().descr("L", "\u7535\u6c60\u957f\u5ea6");
    model.param().set("D", "2[cm]");
    model.param().descr("D", "\u7535\u6c60\u5bbd\u5ea6");
    model.param().set("H_film", "0.035[mm]");
    model.param().descr("H_film", "\u94dc\u819c\u539a\u5ea6");
    model.param().set("H_GDL", "0.3[mm]");
    model.param().descr("H_GDL", "GDL \u539a\u5ea6");
    model.param().set("H_membrane", "0.02[mm]");
    model.param().descr("H_membrane", "\u819c\u539a\u5ea6");
    model.param().set("H_plate", "0.5[mm]");
    model.param().descr("H_plate", "\u94a2\u677f\u539a\u5ea6");
    model.param().set("r_film", "2[mm]");
    model.param().descr("r_film", "\u94dc\u819c\u4e2d\u7684\u5b54\u534a\u5f84");
    model.param().set("r_plate", "4[mm]");
    model.param().descr("r_plate", "\u94a2\u677f\u4e2d\u7684\u5b54\u534a\u5f84");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "D", "H_film"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "r_film");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "H_film");
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new String[]{"1[cm]", "1[cm]", "0"});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{5, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"2e-2", "0", "0"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("arr1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"L", "D", "H_GDL"});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "H_film"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new String[]{"L", "D", "H_membrane"});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "H_film+H_GDL"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new String[]{"L", "D", "H_GDL"});
    model.component("comp1").geom("geom1").feature("blk4")
         .set("pos", new String[]{"0", "0", "H_film+H_GDL+H_membrane"});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new String[]{"L", "D", "H_plate"});
    model.component("comp1").geom("geom1").feature("blk5")
         .set("pos", new String[]{"0", "0", "H_film+H_GDL+H_membrane+H_GDL"});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", "r_plate");
    model.component("comp1").geom("geom1").feature("cyl2").set("h", "H_plate");
    model.component("comp1").geom("geom1").feature("cyl2")
         .set("pos", new String[]{"5e-3", "5e-3", "H_film+H_GDL+H_membrane+H_GDL"});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("cyl2");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{10, 2, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new String[]{"1e-2", "1e-2", "0"});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("dif2", "Difference");
    model.component("comp1").geom("geom1").feature("dif2").selection("input").set("blk2", "blk3", "blk4", "blk5");
    model.component("comp1").geom("geom1").feature("dif2").selection("input2").set("arr2");
    model.component("comp1").geom("geom1").run("dif2");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("passive_pem_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
