/*
 * steam_reformer_geometry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class steam_reformer_geometry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Geometry_Tutorials");

    model.param().set("L", "0.15[m]");
    model.param().descr("L", "\u53cd\u5e94\u5e8a\u957f\u5ea6");
    model.param().set("jr", "33[mm]");
    model.param().descr("jr", "\u5939\u5957\u534a\u5f84");
    model.param().set("br", "30[mm]");
    model.param().descr("br", "\u53cd\u5e94\u5e8a\u534a\u5f84");
    model.param().set("tr", "4[mm]");
    model.param().descr("tr", "\u7ba1\u534a\u5f84");
    model.param().set("nt", "8");
    model.param().descr("nt", "\u7ba1\u6570\u91cf\uff0c\u5fc5\u987b\u662f 4 \u7684\u500d\u6570");
    model.param().set("pt", "20[mm]");
    model.param().descr("pt", "\u7ba1\u4e2d\u5fc3\u5230\u539f\u70b9\u7684\u8ddd\u79bb");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").set("quickplane", "yz");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "jr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", 90);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "jr-br", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c2", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").label("\u7ba1\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("r", "tr");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("pos", new String[]{"0", "pt"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("rot", "-range(0,360/nt,360/4)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("uni1").selection("input").named("c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("co1", "Compose");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").selection("input").set("c1", "uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("co1").set("formula", "c1+c1*uni1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");

    model.param().set("nt", "12");

    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");

    model.param().set("nt", "8");

    model.component("comp1").geom("geom1").feature("wp1").geom().run("co1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("adjsel1")
         .label("\u53cd\u5e94\u5e8a\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("adjsel1").set("input", new String[]{"c2"});
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("adjsel1").set("outputdim", 2);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("adjsel1");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", false);
    model.component("comp1").geom("geom1").feature("wp1").set("selplaneshow", true);
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").feature().create("ext1", "Extrude");
    model.component("comp1").geom("geom1").feature("ext1").set("workplane", "wp1");
    model.component("comp1").geom("geom1").feature("ext1").selection("input").set("wp1");
    model.component("comp1").geom("geom1").feature("ext1").label("\u5939\u5957");
    model.component("comp1").geom("geom1").feature("ext1").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext1").selection("inputface").set("wp1", 3);
    model.component("comp1").geom("geom1").feature("ext1").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext1").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").feature("ext1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext1");
    model.component("comp1").geom("geom1").feature().create("ext2", "Extrude");
    model.component("comp1").geom("geom1").feature("ext2").label("\u50ac\u5316\u5e8a");
    model.component("comp1").geom("geom1").feature("ext2").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext2").selection("inputface").named("wp1_adjsel1");
    model.component("comp1").geom("geom1").feature("ext2").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext2").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").feature("ext2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext2").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext2");
    model.component("comp1").geom("geom1").feature().create("ext3", "Extrude");
    model.component("comp1").geom("geom1").feature("ext3").label("\u52a0\u70ed\u7ba1");
    model.component("comp1").geom("geom1").feature("ext3").set("extrudefrom", "faces");
    model.component("comp1").geom("geom1").feature("ext3").selection("inputface").named("wp1_c2");
    model.component("comp1").geom("geom1").feature("ext3").set("inputhandling", "keep");
    model.component("comp1").geom("geom1").feature("ext3").setIndex("distance", "L", 0);
    model.component("comp1").geom("geom1").feature("ext3").set("selresult", true);
    model.component("comp1").geom("geom1").feature("ext3").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("ext3");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("renderwireframe", true);

    model.component("comp1").geom("geom1").create("intsel1", "IntersectionSelection");
    model.component("comp1").geom("geom1").feature("intsel1").label("\u53cd\u5e94\u5e8a/\u5939\u5957");
    model.component("comp1").geom("geom1").feature("intsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel1").set("input", new String[]{"ext1", "ext2"});
    model.component("comp1").geom("geom1").feature().createAfter("intsel2", "IntersectionSelection", "intsel1");
    model.component("comp1").geom("geom1").feature("intsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel2").label("\u7ba1/\u53cd\u5e94\u5e8a");
    model.component("comp1").geom("geom1").feature("intsel2").set("input", new String[]{"ext2.bnd", "ext3.bnd"});
    model.component("comp1").geom("geom1").run("intsel2");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "inf");
    model.component("comp1").geom("geom1").feature("cylsel1").set("angle1", 180);
    model.component("comp1").geom("geom1").feature("cylsel1").set("angle2", 90);
    model.component("comp1").geom("geom1").feature("cylsel1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("cylsel1").set("selshow", false);
    model.component("comp1").geom("geom1").feature().createAfter("intsel3", "IntersectionSelection", "cylsel1");
    model.component("comp1").geom("geom1").feature("intsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel3").label("\u5939\u5957\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("intsel3").set("input", new String[]{"cylsel1", "ext1.bnd"});
    model.component("comp1").geom("geom1").run("intsel3");
    model.component("comp1").geom("geom1").feature().createAfter("intsel4", "IntersectionSelection", "intsel3");
    model.component("comp1").geom("geom1").feature("intsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel4").label("\u7ba1\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("intsel4").set("input", new String[]{"ext3.bnd", "cylsel1"});
    model.component("comp1").geom("geom1").run("intsel4");
    model.component("comp1").geom("geom1").feature().createAfter("intsel5", "IntersectionSelection", "intsel4");
    model.component("comp1").geom("geom1").feature("intsel5").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("intsel5").label("\u53cd\u5e94\u5e8a\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("intsel5").set("input", new String[]{"ext2.bnd", "cylsel1"});
    model.component("comp1").geom("geom1").run("intsel5");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u5165\u53e3\u548c\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("difsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"ext2", "ext3"});
    model.component("comp1").geom("geom1").feature("difsel1")
         .set("subtract", new String[]{"intsel1", "intsel2", "intsel4", "intsel5"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u7ba1\u5165\u53e3");
    model.component("comp1").geom("geom1").feature("difsel2").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"wp1_c2", "ext2"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").label("\u53cd\u5e94\u5e8a\u51fa\u53e3");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"wp1_adjsel1", "ext3"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(2);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 12);
    model.component("comp1").geom("geom1").feature("sel1").label("\u5939\u5957/\u73af\u5883");
    model.component("comp1").geom("geom1").run("sel1");

    model.title(null);

    model.description("");

    model.label("steam_reformer_geometry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
