/*
 * evaporator_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:18 by COMSOL 6.3.0.290. */
public class evaporator_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Molecular_Flow_Module\\Industrial_Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", 254);
    model.component("comp1").geom("geom1").feature("cyl1").set("h", 381);
    model.component("comp1").geom("geom1").feature("cyl1").set("pos", new double[]{0, 25.4, -50.8});
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("sph1", "Sphere");
    model.component("comp1").geom("geom1").feature("sph1").set("r", 254);
    model.component("comp1").geom("geom1").feature("sph1").set("pos", new double[]{0, 25.4, 330.2});
    model.component("comp1").geom("geom1").run("sph1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("cyl1", "sph1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cyl2", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl2").set("r", 82.55);
    model.component("comp1").geom("geom1").feature("cyl2").set("h", 12.7);
    model.component("comp1").geom("geom1").feature("cyl2").set("pos", new double[]{0, 0, 279.4});
    model.component("comp1").geom("geom1").run("cyl2");
    model.component("comp1").geom("geom1").create("cyl3", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl3").set("r", 19.05);
    model.component("comp1").geom("geom1").feature("cyl3").set("h", 50.8);
    model.component("comp1").geom("geom1").feature("cyl3").set("pos", new double[]{-79, -34.8, -50.8});
    model.component("comp1").geom("geom1").run("cyl3");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").set("keep", true);

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").feature("rot1").selection("input").set("cyl3");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", "range(0,30,60)");
    model.component("comp1").geom("geom1").feature("rot1").set("pos", new double[]{0, 102.03, 0});
    model.component("comp1").geom("geom1").run("rot1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{2.54, 63.5, 76.2});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-34.5, -27.2, -50.8});
    model.component("comp1").geom("geom1").feature("blk1").set("rot", -15);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5c4f\u853d\u7f51");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("blk2", "Block");
    model.component("comp1").geom("geom1").feature("blk2").set("size", new double[]{12.7, 85, 1.27});
    model.component("comp1").geom("geom1").feature("blk2").set("pos", new double[]{-6.35, -40, -1.27});
    model.component("comp1").geom("geom1").run("blk2");
    model.component("comp1").geom("geom1").create("sph2", "Sphere");
    model.component("comp1").geom("geom1").feature("sph2").set("r", 6.35);
    model.component("comp1").geom("geom1").feature("sph2").set("pos", new double[]{0, 0, 2.54});
    model.component("comp1").geom("geom1").run("sph2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("sph2");
    model.component("comp1").geom("geom1").feature("copy1").set("displz", 1.27);
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("blk3", "Block");
    model.component("comp1").geom("geom1").feature("blk3").set("size", new double[]{120, 34.35, 1.27});
    model.component("comp1").geom("geom1").feature("blk3").set("pos", new double[]{-60, 55.2, -1.27});
    model.component("comp1").geom("geom1").run("blk3");
    model.component("comp1").geom("geom1").create("cyl4", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl4").set("r", 101.6);
    model.component("comp1").geom("geom1").feature("cyl4").set("h", 1.27);
    model.component("comp1").geom("geom1").feature("cyl4").set("pos", new double[]{0, 137.2, -1.27});
    model.component("comp1").geom("geom1").run("cyl4");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("blk3", "cyl4");
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").feature().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init();
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del1").selection("input").set("uni2", 1, 2, 5);
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("blk2", "del1");
    model.component("comp1").geom("geom1").feature("uni3").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni3");
    model.component("comp1").geom("geom1").create("uni4", "Union");
    model.component("comp1").geom("geom1").feature("uni4").selection("input").set("copy1", "sph2", "uni3");
    model.component("comp1").geom("geom1").run("uni4");
    model.component("comp1").geom("geom1").feature().create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init();
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(3);
    model.component("comp1").geom("geom1").feature("del2").selection("input").set("uni4", 2, 3, 4, 7);
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("uni5", "Union");
    model.component("comp1").geom("geom1").feature("uni5").selection("input").set("del2");
    model.component("comp1").geom("geom1").feature("uni5").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni5");
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickz", -1.27);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("x", "-19.05 0 0 19.05 19.05 6.35 6.35 0 0 -6.35 -6.35 -19.05");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("pol1")
         .set("y", "63.5 82.55 82.55 63.5 63.5 45.55 45.55 44.45 44.45 45.54 45.54 63.5");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", 49.53, 0);
    model.component("comp1").geom("geom1").feature("ext1").set("reverse", true);
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").create("wp2", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp2").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp2").set("quickz", -50.8);
    model.component("comp1").geom("geom1").feature("wp2").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("r", 63.5);
    model.component("comp1").geom("geom1").feature("wp2").geom().feature("c1").set("pos", new double[]{0, 203.2});
    model.component("comp1").geom("geom1").run("wp2");
    model.component("comp1").geom("geom1").create("wp3", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp3").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp3").set("quickz", 279.4);
    model.component("comp1").geom("geom1").feature("wp3").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("r", 101.6);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("pos", new double[]{-38.1, 38.1});
    model.component("comp1").geom("geom1").feature("wp3").geom().feature("c1").set("rot", -90);
    model.component("comp1").geom("geom1").run("wp3");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("uni1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2")
         .set("blk1", "cyl2", "cyl3", "ext1", "mir1", "rot1", "uni5", "wp2", "wp3");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("cmf1", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf1").selection("input").set("fin", 34, 36);
    model.component("comp1").geom("geom1").run("cmf1");
    model.component("comp1").geom("geom1").create("cmf2", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").set("cmf1", 1);
    model.component("comp1").geom("geom1").feature("cmf2").selection("input").clear("cmf1");
    model.component("comp1").geom("geom1").feature("cmf2").selection("input")
         .set("cmf1", 18, 19, 22, 23, 39, 40, 60);
    model.component("comp1").geom("geom1").run("cmf2");
    model.component("comp1").geom("geom1").create("cmf3", "CompositeFaces");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").set("cmf2", 1);
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").clear("cmf2");
    model.component("comp1").geom("geom1").feature("cmf3").selection("input").set("cmf2", 36, 38);
    model.component("comp1").geom("geom1").run("cmf3");
    model.component("comp1").geom("geom1").create("clf1", "CollapseFaces");
    model.component("comp1").geom("geom1").feature("clf1").selection("input").set("cmf3", 17, 64);
    model.component("comp1").geom("geom1").run("clf1");
    model.component("comp1").geom("geom1").create("ballsel1", "BallSelection");
    model.component("comp1").geom("geom1").feature("ballsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("ballsel1").set("r", 10);
    model.component("comp1").geom("geom1").feature("ballsel1").label("\u94a8\u821f");
    model.component("comp1").geom("geom1").run("ballsel1");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u51e0\u4f55\u5f62\u72b6");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init();
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("clf1");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u6240\u6709\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"sel1"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("interior", true);
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel1").label("\u94a8\u821f\u548c\u5c4f\u853d\u7f51");
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"csel1", "ballsel1"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").label("\u524d\u56db\u5206\u4e4b\u4e00\u5706");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("clf1", 1, 4);
    model.component("comp1").geom("geom1").run("sel2");
    model.component("comp1").geom("geom1").create("sel3", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel3").label("\u6837\u54c1\u652f\u67b6\u80cc\u9762");
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel3").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel3").selection("selection").add("clf1", 9, 10, 12, 44, 52);
    model.component("comp1").geom("geom1").run("sel3");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u6c89\u79ef\u8868\u9762");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"unisel1", "sel2", "sel3"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("sel4", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel4").label("\u7f51\u683c\u5212\u5206 1");
    model.component("comp1").geom("geom1").feature("sel4").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel4").set("groupcontang", true);
    model.component("comp1").geom("geom1").feature("sel4").selection("selection")
         .add("clf1", 1, 2, 4, 5, 25, 42, 43, 49, 53, 59);
    model.component("comp1").geom("geom1").run("sel4");
    model.component("comp1").geom("geom1").create("sel5", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel5").label("\u7f51\u683c\u5212\u5206 2");
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel5").selection("selection").set("clf1", 21);

    model.title(null);

    model.description("");

    model.label("evaporator_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
