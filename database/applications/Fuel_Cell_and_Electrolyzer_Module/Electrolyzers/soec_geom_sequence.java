/*
 * soec_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:06 by COMSOL 6.3.0.290. */
public class soec_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Electrolyzers");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("W_ch", "0.5[mm]", "\u901a\u9053\u5bbd\u5ea6");
    model.param().set("W_rib", "W_ch*3", "\u808b\u677f\u5bbd\u5ea6");
    model.param().set("L_ch", "10[mm]", "\u901a\u9053\u957f\u5ea6");
    model.param().set("N_ch", "3", "\u901a\u9053\u6570");
    model.param().set("H_gde", "0.1[mm]", "\u6c14\u4f53\u6269\u6563\u7535\u6781\u539a\u5ea6");
    model.param().set("H_ch", "W_ch", "\u901a\u9053\u9ad8\u5ea6");
    model.param().set("H_el", "H_gde", "\u7535\u89e3\u8d28\u5c42\u539a\u5ea6");
    model.param().set("D_cell", "N_ch*(W_ch+W_rib)", "\u7535\u89e3\u69fd\u6df1\u5ea6\uff08y \u65b9\u5411\uff09");
    model.param().set("W_cell", "L_ch+2*W_ch+W_rib", "\u7535\u89e3\u69fd\u5bbd\u5ea6\uff08x \u65b9\u5411\uff09");
    model.param().set("H_cell", "H_gde*2+H_el+H_ch", "\u7535\u89e3\u69fd\u539a\u5ea6\uff08z \u65b9\u5411\uff09");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_cell", "D_cell", "1"});
    model.component("comp1").geom("geom1").feature("blk1").setIndex("size", "H_gde", 2);
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("blk2", "blk1");
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new String[]{"0", "0", "H_gde"});
    model.component("comp1").geom("geom1").feature().duplicate("blk3", "blk2");
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new String[]{"0", "0", "H_gde+H_el"});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", "H_cell-H_ch");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_ch", "N_ch*(W_ch+W_rib)"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("pos", new String[]{"W_rib/2", "W_rib/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2")
         .set("pos", new String[]{"W_rib/2+L_ch+W_ch", "W_rib/2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r2").setIndex("pos", "-W_rib/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("size", new String[]{"L_ch", "W_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3")
         .set("pos", new String[]{"W_rib/2+W_ch", "0"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r3").setIndex("pos", "W_rib/2", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("r3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("fullsize", new String[]{"1", "N_ch"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1")
         .set("displ", new String[]{"0", "W_ch+W_rib"});
    model.component("comp1").geom("geom1").feature("wp1").geom().run("arr1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "H_ch", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 19);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 42);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 10, 26, 33, 40);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"ext1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1", "sel2"});

    model.title(null);

    model.description("");

    model.label("soec_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
