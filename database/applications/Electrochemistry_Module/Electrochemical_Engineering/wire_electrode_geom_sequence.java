/*
 * wire_electrode_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:54 by COMSOL 6.3.0.290. */
public class wire_electrode_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Electrochemical_Engineering");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{22, 8, 5});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{-11, -4, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5e76\u96c6");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -1);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("size", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("pos", new int[]{-6, -1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("sq1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").selection("pointinsketch")
         .set("sq1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("fil1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").selection("input").set("fil1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("fullsize", new int[]{3, 1});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("arr1").set("displ", new int[]{5, 0});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 6, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp2").set("quickx", 11);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("size", 2);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("sq1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().run("sq1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").selection("pointinsketch")
         .set("sq1", 1, 2, 3, 4);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("fil1").set("radius", 0.5);
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 22, 0);
    model.component("comp1").geom("geom1").feature("ext2").set("reverse", true);
    model.component("comp1").geom("geom1").feature("ext2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni1", 1, 16);
    model.component("comp1").geom("geom1").feature("del1").set("selresult", true);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").named("del1");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("uni2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("uni2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", -60);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 60);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 2);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u9634\u6781");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u9633\u6781");
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").runPre("sel1");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("dif1", 2, 5);
    model.component("comp1").geom("geom1").feature().move("sel1", 11);
    model.component("comp1").geom("geom1").feature().move("boxsel1", 10);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 1);
    model.component("comp1").geom("geom1").feature().move("sel1", 11);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 52);
    model.component("comp1").geom("geom1").run("sel3");

    model.title(null);

    model.description("");

    model.label("wire_electrode_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
