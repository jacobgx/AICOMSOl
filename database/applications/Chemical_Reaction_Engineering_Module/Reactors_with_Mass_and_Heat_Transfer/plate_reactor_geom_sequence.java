/*
 * plate_reactor_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:22 by COMSOL 6.3.0.290. */
public class plate_reactor_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Chemical_Reaction_Engineering_Module\\Reactors_with_Mass_and_Heat_Transfer");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{155, 20, 15});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new int[]{10, 10, 15});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new int[]{5, 0, 0});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 5);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 15);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new int[]{10, 10, 0});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk2", "cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{5, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{30, 0, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u8981\u51cf\u53bb\u7684\u5bf9\u8c61");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("mir1").set("pos", new int[]{0, 10, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("mir1");
    model.component("comp1").geom("geom1").feature("mov1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("mov1").set("displx", 15);
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").named("csel1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pard1", "PartitionDomains");
    model.component("comp1").geom("geom1").feature("pard1").selection("domain").set("dif1", 1);
    model.component("comp1").geom("geom1").feature("pard1").set("partitionwith", "extendedfaces");
    model.component("comp1").geom("geom1").feature("pard1").selection("extendedface").set("dif1", 6, 54);
    model.component("comp1").geom("geom1").run("pard1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new int[]{5, 20, 3});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new int[]{150, 0, 15});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("arr2", "Array");
    model.component("comp1").geom("geom1").feature("arr2").selection("input").set("pard1");
    model.component("comp1").geom("geom1").feature("arr2").set("fullsize", new int[]{1, 1, 4});
    model.component("comp1").geom("geom1").feature("arr2").set("displ", new int[]{0, 0, 18});
    model.component("comp1").geom("geom1").run("arr2");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").set("arr2(1,1,2)", "arr2(1,1,4)");
    model.component("comp1").geom("geom1").feature("mir2").set("pos", new double[]{77.5, 0, 0});
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new int[]{5, 20, 3});
    model.component("comp1").geom("geom1").feature("blk4").set("pos", new int[]{150, 0, 51});
    model.component("comp1").geom("geom1").run("blk4");
    model.component("comp1").geom("geom1").create("blk5", "Block");
    model.component("comp1").geom("geom1").feature("blk5").set("size", new int[]{5, 20, 3});
    model.component("comp1").geom("geom1").feature("blk5").set("pos", new int[]{0, 0, 33});
    model.component("comp1").geom("geom1").run("blk5");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("mir2(1)", 63);
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("mir2(2)", 63);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 10, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().duplicate("ext2", "ext1");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("arr2(1,1,1)", 2);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 35);
    model.component("comp1").geom("geom1").feature("sel1").label("\u5165\u53e3 1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u5165\u53e3 2");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 34);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").set("fin", 2);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u6846\u9009\u62e9 - \u6362\u70ed\u5668 1");
    model.component("comp1").geom("geom1").feature("boxsel1").set("zmin", 33.1);
    model.component("comp1").geom("geom1").feature("boxsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2").label("\u6846\u9009\u62e9 - \u6362\u70ed\u5668 2");
    model.component("comp1").geom("geom1").feature("boxsel2").set("zmax", 33);
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u6846\u9009\u62e9 - \u6362\u70ed\u5668\u8054\u63a5");
    model.component("comp1").geom("geom1").feature("boxsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmin", 34);
    model.component("comp1").geom("geom1").feature("boxsel3").set("zmax", 35);
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel3").set("condition", "intersects");
    model.component("comp1").geom("geom1").run("boxsel3");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1")
         .label("\u76f8\u90bb\u9009\u62e9 - \u6362\u70ed\u5668 1");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2")
         .label("\u76f8\u90bb\u9009\u62e9 - \u6362\u70ed\u5668 2");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u6362\u70ed\u5668 1");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u6362\u70ed\u5668 2");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"sel2", "sel3"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u5916\u58c1");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"boxsel3", "difsel1", "difsel2"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"boxsel1"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("interior", true);
    model.component("comp1").geom("geom1").feature("adjsel3").set("exterior", false);
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u6362\u70ed\u5668 1 \u5185\u58c1");
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").feature().duplicate("adjsel4", "adjsel3");
    model.component("comp1").geom("geom1").feature("adjsel4").label("\u6362\u70ed\u5668 2 \u5185\u58c1");
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"boxsel2"});
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("unisel2", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel2").label("\u5168\u90e8\u5185\u58c1");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"adjsel3", "adjsel4"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("mcf1", "MeshControlFaces");
    model.component("comp1").geom("geom1").feature("mcf1").selection("input").named("unisel2");
    model.component("comp1").geom("geom1").run("mcf1");

    model.title(null);

    model.description("");

    model.label("plate_reactor_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
