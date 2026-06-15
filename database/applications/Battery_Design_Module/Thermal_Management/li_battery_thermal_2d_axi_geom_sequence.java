/*
 * li_battery_thermal_2d_axi_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:31 by COMSOL 6.3.0.290. */
public class li_battery_thermal_2d_axi_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Thermal_Management");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("d_can", "0.25[mm]", "\u7535\u6c60\u7f50\u539a\u5ea6");
    model.param().set("r_batt", "9[mm]", "\u7535\u6c60\u534a\u5f84");
    model.param().set("h_batt", "65[mm]", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("r_mandrel", "2[mm]", "\u82af\u8f74\u534a\u5f84");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r_batt", "h_batt"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "d_can", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "h_batt-d_can*2", 1);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"r_batt-d_can", "1"});
    model.component("comp1").geom("geom1").feature("r2").setIndex("size", "h_batt", 1);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", "r_mandrel", 0);
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r2").set("layerbottom", false);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input").set("fin", 8, 12, 15, 18, 19, 20);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u7535\u6c60\u7f50");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("mce1", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u7535\u6c60\u6d3b\u6027\u6750\u6599");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("mce1", 3);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u82af\u8f74");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("mce1", 2);

    model.title(null);

    model.description("");

    model.label("li_battery_thermal_2d_axi_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
