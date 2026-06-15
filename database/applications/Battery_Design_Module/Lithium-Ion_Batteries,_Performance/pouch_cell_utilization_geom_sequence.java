/*
 * pouch_cell_utilization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:24 by COMSOL 6.3.0.290. */
public class pouch_cell_utilization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Battery_Design_Module\\Lithium-Ion_Batteries,_Performance");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("L_sep", "30[um]", "\u9694\u819c\u539a\u5ea6");
    model.param().set("L_pos", "60[um]", "\u6b63\u6781\u539a\u5ea6");
    model.param().set("L_neg", "60[um]", "\u8d1f\u6781\u539a\u5ea6");
    model.param().set("L_pos_cc", "10[um]", "\u6b63\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("L_neg_cc", "10[um]", "\u8d1f\u6781\u96c6\u6d41\u4f53\u539a\u5ea6");
    model.param().set("W_cell", "10[cm]", "\u7535\u6c60\u5bbd\u5ea6");
    model.param().set("H_cell", "20[cm]", "\u7535\u6c60\u9ad8\u5ea6");
    model.param().set("H_tab", "1[cm]", "\u6781\u8033\u9ad8\u5ea6");
    model.param().set("W_tab", "5[cm]", "\u6781\u8033\u5bbd\u5ea6");
    model.param().set("L_cell", "L_sep+L_pos+L_neg+L_neg_cc/2+L_pos_cc/2", "\u7535\u6c60\u539a\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1")
         .set("size", new String[]{"W_cell", "H_cell"});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_neg_cc/2", 0);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_neg_cc/2+L_neg", 1);
    model.component("comp1").geom("geom1").feature("ext1").set("displ", new String[][]{{"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1").set("scale", new String[][]{{"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_neg_cc/2+L_neg+L_sep", 2);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L_neg_cc/2+L_neg+L_sep+L_pos", 3);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0"});
    model.component("comp1").geom("geom1").feature("ext1")
         .setIndex("distance", "L_neg_cc/2+L_neg+L_sep+L_pos+L_pos_cc/2", 4);
    model.component("comp1").geom("geom1").feature("ext1")
         .set("displ", new String[][]{{"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}, {"0", "0"}});
    model.component("comp1").geom("geom1").feature("ext1")
         .set("scale", new String[][]{{"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}, {"1", "1"}});
    model.component("comp1").geom("geom1").feature("ext1").set("twist", new String[]{"0", "0", "0", "0", "0"});
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"W_tab", "H_tab", "L_neg_cc/2"});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new String[]{"0", "H_cell", "0"});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new String[]{"W_tab", "H_tab", "L_neg_cc/2"});
    model.component("comp1").geom("geom1").feature("blk2")
         .set("pos", new String[]{"0", "-H_tab", "L_neg_cc/2+L_neg+L_sep+L_pos"});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").camera().set("viewscaletype", "manual");
    model.component("comp1").view("view1").camera().set("zscale", 100);

    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u6b63\u6781\u7ffc\u7247");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6b63\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 6);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6b63\u6781");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 5);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u8d1f\u6781\u7ffc\u7247");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").set("fin", 7);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u8d1f\u6781\u96c6\u6d41\u4f53");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("fin", 2);
    model.component("comp1").geom("geom1").run("sel5");
    model.component("comp1").geom("geom1").create("sel6", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel6").label("\u8d1f\u6781");
    model.component("comp1").geom("geom1").feature("sel6").selection("selection").set("fin", 3);
    model.component("comp1").geom("geom1").run("sel6");
    model.component("comp1").geom("geom1").create("sel7", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel7").label("\u9694\u819c");
    model.component("comp1").geom("geom1").feature("sel7").selection("selection").set("fin", 4);
    model.component("comp1").geom("geom1").run("sel7");
    model.component("comp1").geom("geom1").create("sel8", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel8").label("\u8d1f\u6781\u7ffc\u7247\u672b\u7aef");
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel8").selection("selection").set("fin", 29);
    model.component("comp1").geom("geom1").run("sel8");
    model.component("comp1").geom("geom1").create("sel9", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel9").label("\u6b63\u6781\u7ffc\u7247\u672b\u7aef");
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel9").selection("selection").set("fin", 2);
    model.component("comp1").geom("geom1").run("sel9");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1")
         .label("\u8d1f\u6781\u96c6\u6d41\u4f53\u548c\u7ffc\u7247");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"sel4", "sel5"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2")
         .label("\u6b63\u6781\u96c6\u6d41\u4f53\u548c\u7ffc\u7247");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"sel1", "sel2"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").label("\u91d1\u5c5e\u7b94\u57df");
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"unisel1", "unisel2"});

    model.title(null);

    model.description("");

    model.label("pouch_cell_utilization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
