/*
 * surface_mount_package_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:28 by COMSOL 6.3.0.290. */
public class surface_mount_package_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Power_Electronics_and_Electronic_Cooling");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").label("\u5370\u5237\u7535\u8def\u677f");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new int[]{20, 10, 1});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-10, -5, -1.9});
    model.component("comp1").geom("geom1").feature("blk1").set("selresult", true);
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{9.9, 3.9, 0.2});
    model.component("comp1").geom("geom1").feature("blk2").set("base", "center");
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("hex1", "Hexahedron");
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.95, 0, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.95, 0, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.95, 0, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.95, 0, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.95, 0, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.95, 0, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 4.95, 0, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -4.95, 0, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.95, 1, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.95, 1, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.95, 1, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.95, 1, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.713419348, 1, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.713419348, 1, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.713419348, 1, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.713419348, 1, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.1, 2, 0);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.1, 2, 1);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.1, 2, 2);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.1, 2, 3);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.75, 2, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.75, 2, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.75, 2, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 0.75, 2, 7);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.72, 1, 4);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", -1.72, 1, 5);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.72, 1, 6);
    model.component("comp1").geom("geom1").feature("hex1").setIndex("p", 1.72, 1, 7);
    model.component("comp1").geom("geom1").run("hex1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("hex1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").label("\u82af\u7247\u5c01\u88c5");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("blk2", "hex1", "mir1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").feature("uni1").set("selresult", true);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{0.4, 0.26, 0.2});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{-4.645, -2.21, 0});
    model.component("comp1").geom("geom1").feature("blk3").setIndex("pos", -0.1, 2);
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").feature().create("rev1", "Revolve");
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev1").selection("inputface").set("blk3", 3);
    model.component("comp1").geom("geom1").feature("rev1").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("rev1").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev1").set("pos3", new double[]{0, -2.211, -0.24});
    model.component("comp1").geom("geom1").feature("rev1").set("axis3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("rev1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("rev1", 2);
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 0.322, 0);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("rev2", "Revolve");
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "full");
    model.component("comp1").geom("geom1").feature("rev2").selection("inputface").set("ext1", 3);
    model.component("comp1").geom("geom1").feature("rev2").set("angtype", "specang");
    model.component("comp1").geom("geom1").feature("rev2").set("angle2", -90);
    model.component("comp1").geom("geom1").feature("rev2").set("axistype", "3d");
    model.component("comp1").geom("geom1").feature("rev2").set("pos3", new double[]{0, -2.69, -0.561});
    model.component("comp1").geom("geom1").feature("rev2").set("axis3", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("rev2");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").set("rev2", 2);
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", 0.16, 0);
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("ext2");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("uni2");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{8, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new double[]{1.27, 0, 0});
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u9488\u811a");
    model.component("comp1").geom("geom1").feature("arr1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{0, 1, 0});
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -0.9);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("size", new int[]{6, 4});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("pos", new int[]{-10, -5});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").setIndex("layer", 2, 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").label("\u4e92\u8054\u7ebf");
    model.component("comp1").geom("geom1").feature("wp2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("size", new double[]{4.145, 2.15});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r1")
         .set("pos", new double[]{-4.645, -1.95});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("size", new double[]{3.745, 1.75});
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("r2")
         .set("pos", new double[]{-4.245, -1.95});
    model.component("comp1").geom("geom1").feature("wp2").geom().run("r2");
    model.component("comp1").geom("geom1").feature("wp2").geom().create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("blk4", "Block");
    model.component("comp1").geom("geom1").feature("blk4").label("\u82af\u7247");
    model.component("comp1").geom("geom1").feature("blk4").set("size", new double[]{1, 1, 0.1});
    model.component("comp1").geom("geom1").feature("blk4").set("selresult", true);
    model.component("comp1").geom("geom1").feature("blk4").set("base", "center");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u51e0\u4f55");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u5916\u90e8\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 7, 37);
    model.component("comp1").geom("geom1").feature("sel2").label("\u94dc\u5c42");

    model.title(null);

    model.description("");

    model.label("surface_mount_package_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
