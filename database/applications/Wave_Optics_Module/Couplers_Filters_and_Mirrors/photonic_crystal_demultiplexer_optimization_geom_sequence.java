/*
 * photonic_crystal_demultiplexer_optimization_geom_sequence.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:36 by COMSOL 6.3.0.290. */
public class photonic_crystal_demultiplexer_optimization_geom_sequence {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Wave_Optics_Module\\Couplers_Filters_and_Mirrors");

    model.param().set("lambda2", "1.1[um]");
    model.param().descr("lambda2", "\u7b2c\u4e8c\u4e2a\u6ce2\u957f");
    model.param().set("W", "4.25*lambda2");
    model.param().descr("W", "\u8bbe\u8ba1\u57df\u5bbd\u5ea6");
    model.param().set("rHole", "0.07*lambda2");
    model.param().descr("rHole", "\u5b54\u534a\u5f84");
    model.param().set("nCircles", "6");
    model.param().descr("nCircles", "\u6bcf\u901a\u9053\u5708\u6570");
    model.param().set("dPeriod", "W/nCircles/2");
    model.param().descr("dPeriod", "\u5468\u671f");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "sin(5/6*pi)*W/2*2/sqrt(3)", 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2*2/sqrt(3)", 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "sin(1/6*pi)*W/2*2/sqrt(3)", 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "W/2", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "sin(-1/6*pi)*W/2*2/sqrt(3)", 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2*2/sqrt(3)", 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-W/2", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "sin(-5/6*pi)*W/2*2/sqrt(3)", 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").set("selresult", true);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "rHole");
    model.component("comp1").geom("geom1").feature("c1")
         .set("pos", new String[]{"-W/2", "-2*dPeriod*sin(pi/3)*round(W/dPeriod/sin(pi/3)/3)"});
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("mov1", "Move");
    model.component("comp1").geom("geom1").feature("mov1").selection("input").named("c1");
    model.component("comp1").geom("geom1").feature("mov1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mov1").set("displx", "cos(pi/3)*dPeriod");
    model.component("comp1").geom("geom1").feature("mov1").set("disply", "sin(pi/3)*dPeriod");
    model.component("comp1").geom("geom1").run("mov1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").named("c1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new String[]{"round(W/dPeriod)+1", "1"});
    model.component("comp1").geom("geom1").feature("arr1").setIndex("fullsize", "round(W/dPeriod)", 1);
    model.component("comp1").geom("geom1").feature("arr1")
         .set("displ", new String[]{"dPeriod", "2*sin(pi/3)*dPeriod"});
    model.component("comp1").geom("geom1").run("arr1");
    model.component("comp1").geom("geom1").create("boxsel1", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel1").label("\u6240\u6709\u5bf9\u8c61");
    model.component("comp1").geom("geom1").feature("boxsel1").set("entitydim", -1);
    model.component("comp1").geom("geom1").run("boxsel1");
    model.component("comp1").geom("geom1").create("boxsel2", "BoxSelection");
    model.component("comp1").geom("geom1").feature("boxsel2")
         .label("\u8981\u5220\u9664\u7684\u5706\uff0c\u7b2c 1 \u884c");
    model.component("comp1").geom("geom1").feature("boxsel2").set("xmax", "2*rHole");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymin", "-rHole*1.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("ymax", "rHole*1.01");
    model.component("comp1").geom("geom1").feature("boxsel2").set("condition", "inside");
    model.component("comp1").geom("geom1").run("boxsel2");
    model.component("comp1").geom("geom1").create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("rot1").selection("input").named("c1");
    model.component("comp1").geom("geom1").feature("rot1").set("rot", 120);
    model.component("comp1").geom("geom1").feature().duplicate("boxsel3", "boxsel2");
    model.component("comp1").geom("geom1").feature("boxsel3")
         .label("\u8981\u5220\u9664\u7684\u5706\uff0c\u7b2c 2 \u884c");
    model.component("comp1").geom("geom1").feature().duplicate("rot2", "rot1");
    model.component("comp1").geom("geom1").feature().duplicate("boxsel4", "boxsel3");
    model.component("comp1").geom("geom1").feature("boxsel4")
         .label("\u8981\u5220\u9664\u7684\u5706\uff0c\u7b2c 3 \u884c");
    model.component("comp1").geom("geom1").feature().duplicate("rot3", "rot2");
    model.component("comp1").geom("geom1").run("rot3");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1")
         .label("\u8981\u5220\u9664\u7684\u5706\uff0c\u884c\u6570");
    model.component("comp1").geom("geom1").feature("unisel1")
         .set("input", new String[]{"boxsel2", "boxsel3", "boxsel4"});
    model.component("comp1").geom("geom1").run("unisel1");
    model.component("comp1").geom("geom1").create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del1").selection("input").named("unisel1");
    model.component("comp1").geom("geom1").run("del1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").named("boxsel1");
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"c1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"pol1"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("del2", "Delete");
    model.component("comp1").geom("geom1").feature("del2").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("del2").selection("input").named("difsel1");
    model.component("comp1").geom("geom1").run("del2");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").label("\u8f93\u5165\u7aef\u53e3");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new String[]{"-W/2", "-dPeriod/2"});
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new String[]{"-W/2", "dPeriod/2"});
    model.component("comp1").geom("geom1").feature("ls1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("ls2", "ls1");
    model.component("comp1").geom("geom1").feature("ls2").label("\u8f93\u51fa\u7aef\u53e3 1");
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord1", new String[]{"W/4-dPeriod/2*cos(pi*5/6)", "-dPeriod/2"});
    model.component("comp1").geom("geom1").feature("ls2")
         .setIndex("coord1", "(W/2*2/sqrt(3)+sin(1/6*pi)*W/2*2/sqrt(3))/2-dPeriod/2*sin(pi*5/6)", 1);
    model.component("comp1").geom("geom1").feature("ls2")
         .set("coord2", new String[]{"W/4+dPeriod/2*cos(pi*5/6)", "dPeriod/2"});
    model.component("comp1").geom("geom1").feature("ls2")
         .setIndex("coord2", "(W/2*2/sqrt(3)+sin(1/6*pi)*W/2*2/sqrt(3))/2+dPeriod/2*sin(pi*5/6)", 1);
    model.component("comp1").geom("geom1").feature().duplicate("ls3", "ls2");
    model.component("comp1").geom("geom1").feature("ls3").label("\u8f93\u51fa\u7aef\u53e3 2");
    model.component("comp1").geom("geom1").feature("ls3")
         .setIndex("coord1", "(-W/2*2/sqrt(3)-sin(1/6*pi)*W/2*2/sqrt(3))/2-dPeriod/2*sin(-pi*5/6)", 1);
    model.component("comp1").geom("geom1").feature("ls3")
         .setIndex("coord2", "(-W/2*2/sqrt(3)-sin(1/6*pi)*W/2*2/sqrt(3))/2+dPeriod/2*sin(-pi*5/6)", 1);
    model.component("comp1").geom("geom1").run("ls3");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u5706\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"c1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("adjsel2", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u591a\u8fb9\u5f62\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"pol1"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u57df\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").feature("adjsel3").set("outputdim", 2);
    model.component("comp1").geom("geom1").run("adjsel3");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u79fb\u52a8\u57df");
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"adjsel3"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("disksel1").label("\u81ea\u7531\u5f62\u72b6\u57df");
    model.component("comp1").geom("geom1").run("fin");

    model.title(null);

    model.description("");

    model.label("photonic_crystal_demultiplexer_optimization_geom_sequence.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
