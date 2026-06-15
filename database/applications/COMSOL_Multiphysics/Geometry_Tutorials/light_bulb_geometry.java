/*
 * light_bulb_geometry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 18:34 by COMSOL 6.3.0.290. */
public class light_bulb_geometry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Geometry_Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 45, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 15, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 45, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 15, 0, 0);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 45, 1, 0);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 44, 0, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 36, 1, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 9, 0, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 31, 1, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 35.07620122663079, 0, 3);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 17.907798138878782, 1, 3);
    model.component("comp1").geom("geom1").run("cb1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new double[]{3.659617963406731, 0});
    model.component("comp1").geom("geom1").feature("ca1").setIndex("center", 12.121488506355607, 1);
    model.component("comp1").geom("geom1").feature("ca1").set("r", 31.945000909352324);
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 10.435789584389491);
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", -96.4997123292218);
    model.component("comp1").geom("geom1").feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0.04350055522003604, 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -19.618183482950297, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 45, 1, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").feature().compositeCurves("pol1", "cb1", "ca1", "pol2");
    model.component("comp1").geom("geom1").feature("cc1").set("type", "solid");
    model.component("comp1").geom("geom1").run("cc1");
    model.component("comp1").geom("geom1").feature("cc1").feature("pol1").setIndex("table", 0, 0, 0);
    model.component("comp1").geom("geom1").feature("cc1").feature("pol1").setIndex("table", -25, 0, 1);
    model.component("comp1").geom("geom1").feature("cc1").feature("pol1").setIndex("table", 42, 1, 1);
    model.component("comp1").geom("geom1").feature("cc1").feature("pol1").setIndex("table", 10, 2, 0);
    model.component("comp1").geom("geom1").feature("cc1").feature("pol1").setIndex("table", 42, 2, 1);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", 18, 0, 1);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", 41, 1, 1);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", 10, 0, 2);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", 29, 1, 2);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", "13*sqrt(2)", 0, 3);
    model.component("comp1").geom("geom1").feature("cc1").feature("cb1").setIndex("p", "13*sqrt(2)+1", 1, 3);
    model.component("comp1").geom("geom1").feature("cc1").feature("ca1").setIndex("center", 0, 0);
    model.component("comp1").geom("geom1").feature("cc1").feature("ca1").set("center", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("cc1").feature("ca1").set("r", 26);
    model.component("comp1").geom("geom1").feature("cc1").feature("ca1").set("angle1", 45);
    model.component("comp1").geom("geom1").feature("cc1").feature("ca1").set("angle2", -90);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").feature("cc1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cc1").set("selresultshow", false);
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("cc1", 3);
    model.component("comp1").geom("geom1").run("pare1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 2.5, 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 38, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 6, 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 38, 1, 1);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("cb1", "CubicBezier");
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 6, 0, 0);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 38, 1, 0);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 15, 0, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 35, 1, 1);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 8, 0, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 25, 1, 2);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 12.93784755544245, 0, 3);
    model.component("comp1").geom("geom1").feature("cb1").setIndex("p", 13.93784755544245, 1, 3);
    model.component("comp1").geom("geom1").run("cb1");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("center", new String[]{"1e-5", "1"});
    model.component("comp1").geom("geom1").feature("ca1").set("r", 18.296879480822305);
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 45);
    model.component("comp1").geom("geom1").feature("ca1").set("angle2", -90);
    model.component("comp1").geom("geom1").feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "1e-5", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", -17.296879480822305, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "1e-5", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 11.706083127983744, 1, 1);
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("ca2", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca2").set("center", new String[]{"1e-5", "15.520226996272523"});
    model.component("comp1").geom("geom1").feature("ca2").set("r", 3.814143868288779);
    model.component("comp1").geom("geom1").feature("ca2").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("ca2").set("angle2", 0);
    model.component("comp1").geom("geom1").run("ca2");
    model.component("comp1").geom("geom1").create("pol3", "Polygon");
    model.component("comp1").geom("geom1").feature("pol3").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol3").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 3.814143868288779, 0, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 15.520226996272523, 0, 1);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 2.2555374909647536, 1, 0);
    model.component("comp1").geom("geom1").feature("pol3").setIndex("table", 23.000253822625236, 1, 1);
    model.component("comp1").geom("geom1").run("pol3");
    model.component("comp1").geom("geom1").create("ca3", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca3").set("center", new String[]{"1e-5", "25"});
    model.component("comp1").geom("geom1").feature("ca3").set("r", 3.01437126895022);
    model.component("comp1").geom("geom1").feature("ca3").set("angle1", 318.4399935940567);
    model.component("comp1").geom("geom1").feature("ca3").set("angle2", 33.7895206711841);
    model.component("comp1").geom("geom1").run("ca3");
    model.component("comp1").geom("geom1").create("ic1", "InterpolationCurve");
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 2.5052023668289563, 0, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 26.676423349964786, 0, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 0.5, 1, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 29, 1, 1);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 2.5052023668289563, 2, 0);
    model.component("comp1").geom("geom1").feature("ic1").setIndex("table", 31, 2, 1);
    model.component("comp1").geom("geom1").run("ic1");
    model.component("comp1").geom("geom1").create("pol4", "Polygon");
    model.component("comp1").geom("geom1").feature("pol4").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol4").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 2.5052023668289563, 0, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 31, 0, 1);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 2.5, 1, 0);
    model.component("comp1").geom("geom1").feature("pol4").setIndex("table", 38, 1, 1);
    model.component("comp1").geom("geom1").run("pol4");
    model.component("comp1").geom("geom1").feature()
         .compositeCurves("pol1", "cb1", "ca1", "pol2", "ca2", "pol3", "ca3", "ic1", "pol4");
    model.component("comp1").geom("geom1").feature("cc2").set("type", "solid");
    model.component("comp1").geom("geom1").run("cc2");
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 4, 0, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 4, 1, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 41, 1, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 10, 2, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol1").setIndex("table", 41, 2, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", 18, 0, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", 40, 1, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", 9, 0, 2);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", 29, 1, 2);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", "12.5*sqrt(2)", 0, 3);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("p", "12.5*sqrt(2)+1", 1, 3);
    model.component("comp1").geom("geom1").feature("cc2").feature("cb1").setIndex("w", "3/4", 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca1").set("center", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("cc2").feature("ca1").set("r", 25);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca1").set("angle1", 45);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol2").setIndex("table", 0, 1, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol2").setIndex("table", 11, 1, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca2").setIndex("center", 0, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca2").set("center", new int[]{0, 13});
    model.component("comp1").geom("geom1").feature("cc2").feature("ca2").set("r", 2);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca2").set("angle1", 270);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 1, 1, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("pol3").setIndex("table", 24, 1, 1);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca3").set("center", new int[]{1, 27});
    model.component("comp1").geom("geom1").feature("cc2").feature("ca3").set("r", 3);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca3").set("angle1", -90);
    model.component("comp1").geom("geom1").feature("cc2").feature("ca3").set("angle2", 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").setIndex("table", 3, 1, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").setIndex("table", 4, 2, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").set("startcond", "tangent");
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").setIndex("starttang", 0, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").set("starttang", new int[]{0, 1});
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").set("endcond", "tangent");
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").setIndex("endtang", 0, 0);
    model.component("comp1").geom("geom1").feature("cc2").feature("ic1").set("endtang", new int[]{0, 1});
    model.component("comp1").geom("geom1").run("cc2");
    model.component("comp1").geom("geom1").feature("cc2").set("selresult", true);
    model.component("comp1").geom("geom1").feature("cc2").set("selresultshow", false);
    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").label("\u94a8\u4e1d");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.5);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new int[]{10, 0});
    model.component("comp1").geom("geom1").feature("c1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("c1").set("selresultshow", "all");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("difsel1", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel1").label("\u73bb\u7483");
    model.component("comp1").geom("geom1").feature("difsel1").set("add", new String[]{"cc1"});
    model.component("comp1").geom("geom1").feature("difsel1").set("subtract", new String[]{"cc2"});
    model.component("comp1").geom("geom1").run("difsel1");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u73bb\u7483\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"difsel1"});
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("difsel2", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel2").label("\u6c29\u6c14");
    model.component("comp1").geom("geom1").feature("difsel2").set("add", new String[]{"cc2"});
    model.component("comp1").geom("geom1").feature("difsel2").set("subtract", new String[]{"c1"});
    model.component("comp1").geom("geom1").run("difsel2");
    model.component("comp1").geom("geom1").create("difsel3", "DifferenceSelection");
    model.component("comp1").geom("geom1").feature("difsel3").label("\u5185\u90e8\u8f90\u5c04");
    model.component("comp1").geom("geom1").feature("difsel3").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("difsel3").set("add", new String[]{"c1", "adjsel1"});
    model.component("comp1").geom("geom1").feature("difsel3").set("subtract", new String[]{"cc1"});
    model.component("comp1").geom("geom1").run("difsel3");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u5916\u90e8\u8f90\u5c04");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").init(1);
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 14, 15);
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u8f90\u5c04");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"difsel3", "sel1"});
    model.component("comp1").geom("geom1").run("unisel1");

    model.component("comp1").view("view1").set("showgrid", true);

    model.title(null);

    model.description("");

    model.label("light_bulb_geometry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
