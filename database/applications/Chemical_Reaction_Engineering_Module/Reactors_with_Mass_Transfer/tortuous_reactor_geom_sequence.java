/*
 * tortuous_reactor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:25 by COMSOL 6.3.0.290. */
public class tortuous_reactor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{10, 80, 5});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new int[]{30, -40, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u901a\u9053");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{5, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{30, 0, 0});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 20);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 5);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{50, -40, 0});
    model.component("comp1").geom("geom1").feature("cyl1").setIndex("layer", 10, 0);
    model.component("comp1").geom("geom1").feature("cyl1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("cyl2", "cyl1");
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new int[]{20, 40, 0});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("cyl1", "cyl2");
    model.component("comp1").geom("geom1").feature("arr2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{3, 1, 1});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{60, 0, 0});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{30, 30, 5});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{-10, -30, 0});
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u8fc7\u6e21\u6bb5");
    model.component("comp1").geom("geom1").feature("blk2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("pos", new double[]{4.6, -15.4});
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").named("csel2");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 180);
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new int[]{95, 0, 0});
    model.component("comp1").geom("geom1").feature("rot1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{10, 40, 5});
    model.component("comp1").geom("geom1").feature("blk3").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature().duplicate("blk4", "blk3");
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{180, -40, 0});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input")
         .set(new String[]{"arr2(1,1,1,1)", "arr2(1,1,1,2)", "arr2(2,1,1,1)", "arr2(2,1,1,2)", "arr2(3,1,1,1)", "arr2(3,1,1,2)"}, new int[][]{{2, 3, 5}, {1, 3, 4}, {2, 3, 5}, {1, 3, 4}, {2, 3, 5}, {1, 3, 4}});
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 6);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 109);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("boxsel1").set("input", new String[]{"csel1"});
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmin", 20);
    model.component("comp1").geom("geom1").feature("boxsel1").set("xmax", 170);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymin", -41);
    model.component("comp1").geom("geom1").feature("boxsel1").set("ymax", 41);
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmax", 0.01);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u50ac\u5316\u8868\u9762");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"csel1", "csel2"});
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u76f8\u90bb\u9009\u62e9 - \u58c1");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u5916\u58c1");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1", "sel2"});

    model.title(null);

    model.description("");

    model.label("tortuous_reactor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
