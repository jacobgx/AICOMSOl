/*
 * hydrodynamic_bearings_comparison.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:47 by COMSOL 6.3.0.290. */
public class hydrodynamic_bearings_comparison {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);

    model.param().set("Rj", "0.1[m]");
    model.param().descr("Rj", "\u8f74\u9888\u534a\u5f84");
    model.param().set("H", "0.1[m]");
    model.param().descr("H", "\u8f74\u9888\u9ad8\u5ea6");
    model.param().set("C", "0.001[m]");
    model.param().descr("C", "\u5e73\u5747\u8f74\u627f\u95f4\u9699");
    model.param().set("d", "0.1*C");
    model.param().descr("d", "\u57ab\u4e2d\u5fc3\u504f\u79fb");
    model.param().set("Cmax", "C+d");
    model.param().descr("Cmax", "\u6700\u5927\u8f74\u627f\u95f4\u9699");
    model.param().set("Cmin", "C-d");
    model.param().descr("Cmin", "\u6700\u5c0f\u8f74\u627f\u95f4\u9699");
    model.param().set("Ow", "200[rad/s]");
    model.param().descr("Ow", "RPS");
    model.param().set("W", "100[N]");
    model.param().descr("W", "\u8f74\u627f\u8f7d\u8377\uff0cz \u5206\u91cf");
    model.param().set("mu", "0.072[Pa*s]");
    model.param().descr("mu", "\u52a8\u529b\u9ecf\u5ea6");

    model.component("comp1").geom("geom1").create("cyl1", "Cylinder");
    model.component("comp1").geom("geom1").feature("cyl1").set("r", "Rj");
    model.component("comp1").geom("geom1").feature("cyl1").set("h", "H");
    model.component("comp1").geom("geom1").feature("cyl1").set("axistype", "x");
    model.component("comp1").geom("geom1").feature("cyl1").set("type", "surface");
    model.component("comp1").geom("geom1").run("cyl1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("cyl1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{8, 1, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new String[]{"2*H", "0", "0"});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(1);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection().duplicate("sel2", "sel1");
    model.component("comp1").selection("sel2").remove(1, 2, 3, 4);
    model.component("comp1").selection("sel2").add(5, 6, 7, 8);
    model.component("comp1").selection().duplicate("sel3", "sel2");
    model.component("comp1").selection("sel3").label("\u5bf9\u5f00\u8f74\u627f");
    model.component("comp1").selection("sel3").remove(5, 6, 7, 8);
    model.component("comp1").selection("sel3").add(9);
    model.component("comp1").selection().duplicate("sel4", "sel3");
    model.component("comp1").selection("sel4").label("\u4e24\u53f6\u8f74\u627f");
    model.component("comp1").selection("sel4").remove(9, 10, 11, 12);
    model.component("comp1").selection("sel4").add(13);
    model.component("comp1").selection().duplicate("sel5", "sel4");
    model.component("comp1").selection("sel5").label("\u4e09\u53f6\u8f74\u627f (LOP)");
    model.component("comp1").selection("sel5").remove(13, 14, 15, 16);
    model.component("comp1").selection("sel5").add(17);
    model.component("comp1").selection().duplicate("sel6", "sel5");
    model.component("comp1").selection("sel6").label("\u4e09\u53f6\u8f74\u627f (LBP)");
    model.component("comp1").selection("sel6").remove(17, 18, 19, 20);
    model.component("comp1").selection("sel6").add(21);
    model.component("comp1").selection().duplicate("sel7", "sel6");
    model.component("comp1").selection("sel7").label("\u56db\u53f6\u8f74\u627f (LOP)");
    model.component("comp1").selection("sel7").remove(21, 22, 23, 24);
    model.component("comp1").selection("sel7").add(25);
    model.component("comp1").selection().duplicate("sel8", "sel7");
    model.component("comp1").selection("sel8").label("\u56db\u53f6\u8f74\u627f (LBP)");
    model.component("comp1").selection("sel8").remove(25, 26, 27, 28);
    model.component("comp1").selection("sel8").add(29);

    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").prop("Stabilization").set("delta_artificial", 20);
    model.component("comp1").physics("hdb").feature("hjb1").set("C_plain", "C");
    model.component("comp1").physics("hdb").feature("hjb1").set("Specify", "Load");
    model.component("comp1").physics("hdb").feature("hjb1").set("W", new String[]{"0", "0", "-W"});
    model.component("comp1").physics("hdb").feature("hjb1").set("uJInit", new String[]{"0", "0", "-0.1*C"});
    model.component("comp1").physics("hdb").feature("hjb1").set("Ow", "Ow");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure_mat", "userdef");
    model.component("comp1").physics("hdb").feature("hjb1").set("mure", "mu");
    model.component("comp1").physics("hdb").feature("hjb1").set("BearingCenterType", "fromGeom");
    model.component("comp1").physics("hdb").feature().duplicate("hjb2", "hjb1");
    model.component("comp1").physics("hdb").feature("hjb2").selection().named("sel2");
    model.component("comp1").physics("hdb").feature("hjb2").set("BearingType", "Elliptic");
    model.component("comp1").physics("hdb").feature("hjb2").set("C_min", "Cmin");
    model.component("comp1").physics("hdb").feature("hjb2").set("C_max", "Cmax");
    model.component("comp1").physics("hdb").feature().duplicate("hjb3", "hjb2");
    model.component("comp1").physics("hdb").feature("hjb3")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u5bf9\u5f00\uff09");
    model.component("comp1").physics("hdb").feature("hjb3").selection().named("sel3");
    model.component("comp1").physics("hdb").feature("hjb3").set("BearingType", "SplitHalves");
    model.component("comp1").physics("hdb").feature("hjb3").set("C_split", "C");
    model.component("comp1").physics("hdb").feature("hjb3").set("PreLoadFactor_split", "Compute");
    model.component("comp1").physics("hdb").feature("hjb3").set("d_split", "d");
    model.component("comp1").physics("hdb").feature().duplicate("hjb4", "hjb3");
    model.component("comp1").physics("hdb").feature("hjb4")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u4e24\u53f6\uff09");
    model.component("comp1").physics("hdb").feature("hjb4").selection().named("sel4");
    model.component("comp1").physics("hdb").feature("hjb4").set("BearingType", "Multilobe");
    model.component("comp1").physics("hdb").feature("hjb4").set("C_multi", "Cmax");
    model.component("comp1").physics("hdb").feature("hjb4").set("PreLoadFactor_multi", "Compute");
    model.component("comp1").physics("hdb").feature("hjb4").set("d_multi", "2*d");
    model.component("comp1").physics("hdb").feature().duplicate("hjb5", "hjb4");
    model.component("comp1").physics("hdb").feature("hjb5")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u4e09\u53f6 LOP\uff09");
    model.component("comp1").physics("hdb").feature("hjb5").selection().named("sel5");
    model.component("comp1").physics("hdb").feature("hjb5").set("C_multi", "2*Cmax-Cmin");
    model.component("comp1").physics("hdb").feature("hjb5").set("d_multi", "4*d");
    model.component("comp1").physics("hdb").feature("hjb5").set("N_multi", 3);
    model.component("comp1").physics("hdb").feature().duplicate("hjb6", "hjb5");
    model.component("comp1").physics("hdb").feature("hjb6")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u4e09\u53f6 LBP\uff09");
    model.component("comp1").physics("hdb").feature("hjb6").selection().named("sel6");
    model.component("comp1").physics("hdb").feature().duplicate("hjb7", "hjb6");
    model.component("comp1").physics("hdb").feature("hjb7")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u56db\u53f6 LOP\uff09");
    model.component("comp1").physics("hdb").feature("hjb7").selection().named("sel7");
    model.component("comp1").physics("hdb").feature("hjb7").set("C_multi", "(sqrt(2)*Cmax-Cmin)/(sqrt(2)-1)");
    model.component("comp1").physics("hdb").feature("hjb7").set("d_multi", "sqrt(2)*(Cmax-Cmin)/(sqrt(2)-1)");
    model.component("comp1").physics("hdb").feature("hjb7").set("N_multi", 4);
    model.component("comp1").physics("hdb").feature().duplicate("hjb8", "hjb7");
    model.component("comp1").physics("hdb").feature("hjb8")
         .label("\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u56db\u53f6 LBP\uff09");
    model.component("comp1").physics("hdb").feature("hjb8").selection().named("sel8");
    model.component("comp1").physics("hdb").create("bax2", "BearingOrientation", 2);
    model.component("comp1").physics("hdb").feature("bax2")
         .label("\u8f74\u627f\u65b9\u5411\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u4e09\u53f6 LOP\uff09");
    model.component("comp1").physics("hdb").feature("bax2").selection().named("sel5");
    model.component("comp1").physics("hdb").feature("bax2").set("ang_bearing", "-pi/6");
    model.component("comp1").physics("hdb").feature().duplicate("bax3", "bax2");
    model.component("comp1").physics("hdb").feature("bax3")
         .label("\u8f74\u627f\u65b9\u5411\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u4e09\u53f6 LBP\uff09");
    model.component("comp1").physics("hdb").feature("bax3").selection().named("sel6");
    model.component("comp1").physics("hdb").feature("bax3").set("ang_bearing", "pi/6");
    model.component("comp1").physics("hdb").feature().duplicate("bax4", "bax3");
    model.component("comp1").physics("hdb").feature("bax4")
         .label("\u8f74\u627f\u65b9\u5411\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\uff08\u56db\u53f6 LOP\uff09");
    model.component("comp1").physics("hdb").feature("bax4").selection().named("sel7");
    model.component("comp1").physics("hdb").feature("bax4").set("ang_bearing", "pi/4");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 15);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "Rj", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "Rj", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "W", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1000,200,10000)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u6d41\u4f53\u538b\u529b (hdb)");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature().create("con1", "Contour");
    model.result("pg1").feature("con1").set("levelrounding", false);
    model.result("pg1").feature("con1").set("colorlegend", false);
    model.result("pg1").feature("con1").set("smooth", "internal");
    model.result("pg1").feature("con1").set("inherittubescale", false);
    model.result("pg1").feature("con1").set("data", "parent");
    model.result("pg1").feature("con1").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"pressure", "\u538b\u529b", "Pa", "Pa"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "bar", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegendsunit", true);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "hdb.p");
    model.result("pg1").run();
    model.result("pg1").feature("con1").set("expr", "hdb.p");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u76f8\u5bf9\u504f\u5fc3\u7387 vs. \u8f7d\u8377");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "W (N)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "e/C (1)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("expr", new String[]{"hdb.hjb1.ec_rel"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg2").feature("glob1").set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel", "hdb.hjb4.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel", "hdb.hjb4.ec_rel", "hdb.hjb5.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel", "hdb.hjb4.ec_rel", "hdb.hjb5.ec_rel", "hdb.hjb6.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel", "hdb.hjb4.ec_rel", "hdb.hjb5.ec_rel", "hdb.hjb6.ec_rel", "hdb.hjb7.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.ec_rel", "hdb.hjb2.ec_rel", "hdb.hjb3.ec_rel", "hdb.hjb4.ec_rel", "hdb.hjb5.ec_rel", "hdb.hjb6.ec_rel", "hdb.hjb7.ec_rel", "hdb.hjb8.ec_rel"});
    model.result("pg2").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387", "\u76f8\u5bf9\u504f\u5fc3\u7387"});
    model.result("pg2").feature("glob1").setIndex("descr", "\u5bf9\u5f00", 2);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4e24\u53f6", 3);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4e09\u53f6 (LOP)", 4);
    model.result("pg2").feature("glob1").setIndex("descr", "\u4e09\u53f6 (LBP)", 5);
    model.result("pg2").feature("glob1").setIndex("descr", "\u56db\u53f6 (LOP)", 6);
    model.result("pg2").feature("glob1").setIndex("descr", "\u56db\u53f6 (LBP)", 7);
    model.result("pg2").feature("glob1").set("linestyle", "cycle");
    model.result("pg2").feature("glob1").set("linewidth", 3);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u59ff\u6001\u89d2 vs. \u8f7d\u8377");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "W (N)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\\phi\uff08\u5ea6\uff09");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"hdb.hjb1.phia"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"rad"});
    model.result("pg3").feature("glob1").set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia", "hdb.hjb4.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia", "hdb.hjb4.phia", "hdb.hjb5.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia", "hdb.hjb4.phia", "hdb.hjb5.phia", "hdb.hjb6.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia", "hdb.hjb4.phia", "hdb.hjb5.phia", "hdb.hjb6.phia", "hdb.hjb7.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1")
         .set("expr", new String[]{"hdb.hjb1.phia", "hdb.hjb2.phia", "hdb.hjb3.phia", "hdb.hjb4.phia", "hdb.hjb5.phia", "hdb.hjb6.phia", "hdb.hjb7.phia", "hdb.hjb8.phia"});
    model.result("pg3").feature("glob1")
         .set("descr", new String[]{"\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2", "\u59ff\u6001\u89d2"});
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 2);
    model.result("pg3").feature("glob1").setIndex("descr", "\u5bf9\u5f00", 2);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 3);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4e24\u53f6", 3);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 4);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4e09\u53f6 (LOP)", 4);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 5);
    model.result("pg3").feature("glob1").setIndex("descr", "\u4e09\u53f6 (LBP)", 5);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 6);
    model.result("pg3").feature("glob1").setIndex("descr", "\u56db\u53f6 (LOP)", 6);
    model.result("pg3").feature("glob1").setIndex("unit", "deg", 7);
    model.result("pg3").feature("glob1").setIndex("descr", "\u56db\u53f6 (LBP)", 7);
    model.result("pg3").feature("glob1").set("linestyle", "cycle");
    model.result("pg3").feature("glob1").set("linewidth", 3);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u9888\u4f4d\u7f6e");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "u<sub>y</sub>/C (1)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "u<sub>z</sub>/C (1)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "hdb.hjb1.uJz/C", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "hdb.hjb1.uJy/C");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linewidth", 3);
    model.result("pg4").feature("glob1").set("autosolution", false);
    model.result("pg4").feature("glob1").set("autodescr", false);
    model.result("pg4").feature("glob1").set("autoplotlabel", true);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "hdb.hjb2.uJz/C", 0);
    model.result("pg4").feature("glob2").set("xdataexpr", "hdb.hjb2.uJy/C");
    model.result("pg4").feature().duplicate("glob3", "glob2");
    model.result("pg4").run();
    model.result("pg4").feature("glob3").label("\u5bf9\u5f00");
    model.result("pg4").feature("glob3").setIndex("expr", "hdb.hjb3.uJz/C", 0);
    model.result("pg4").feature("glob3").set("xdataexpr", "hdb.hjb3.uJy/C");
    model.result("pg4").feature().duplicate("glob4", "glob3");
    model.result("pg4").run();
    model.result("pg4").feature("glob4").label("\u4e24\u53f6");
    model.result("pg4").feature("glob4").setIndex("expr", "hdb.hjb4.uJz/C", 0);
    model.result("pg4").feature("glob4").set("xdataexpr", "hdb.hjb4.uJy/C");
    model.result("pg4").feature().duplicate("glob5", "glob4");
    model.result("pg4").run();
    model.result("pg4").feature("glob5").label("\u4e09\u53f6 LOP");
    model.result("pg4").feature("glob5").setIndex("expr", "hdb.hjb5.uJz/C", 0);
    model.result("pg4").feature("glob5").set("xdataexpr", "hdb.hjb5.uJy/C");
    model.result("pg4").feature().duplicate("glob6", "glob5");
    model.result("pg4").run();
    model.result("pg4").feature("glob6").label("\u4e09\u53f6 LBP");
    model.result("pg4").feature("glob6").setIndex("expr", "hdb.hjb6.uJz/C", 0);
    model.result("pg4").feature("glob6").set("xdataexpr", "hdb.hjb6.uJy/C");
    model.result("pg4").feature().duplicate("glob7", "glob6");
    model.result("pg4").run();
    model.result("pg4").feature("glob7").label("\u56db\u53f6 LOP");
    model.result("pg4").feature("glob7").setIndex("expr", "hdb.hjb7.uJz/C", 0);
    model.result("pg4").feature("glob7").set("xdataexpr", "hdb.hjb7.uJy/C");
    model.result("pg4").feature().duplicate("glob8", "glob7");
    model.result("pg4").run();
    model.result("pg4").feature("glob8").label("\u56db\u53f6 LBP");
    model.result("pg4").feature("glob8").setIndex("expr", "hdb.hjb8.uJz/C", 0);
    model.result("pg4").feature("glob8").set("xdataexpr", "hdb.hjb8.uJy/C");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").run();
    model.result().create("pg5", "PolarGroup");
    model.result("pg5").run();
    model.result("pg5").label("\u521d\u59cb\u539a\u5ea6\u5256\u9762\u56fe");
    model.result("pg5").setIndex("looplevelinput", "first", 0);
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("axislimits", true);
    model.result("pg5").set("rmin", 0.6);
    model.result("pg5").set("rmax", 1.2);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").selection().set(1, 2, 4, 6);
    model.result("pg5").feature("lngr1").set("expr", "hdb.hi_rel");
    model.result("pg5").feature("lngr1").set("descr", "\u76f8\u5bf9\u819c\u539a\uff0c\u521d\u59cb");
    model.result("pg5").feature("lngr1").set("descractive", true);
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "hdb.Th+hdb.ang_bearing");
    model.result("pg5").feature("lngr1").set("linestyle", "cycle");
    model.result("pg5").feature("lngr1").set("linewidth", 3);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("autosolution", false);
    model.result("pg5").feature("lngr1").set("autoplotlabel", true);
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").selection().set(13, 14, 16, 18);
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").label("\u5bf9\u5f00");
    model.result("pg5").feature("lngr3").selection().set(25, 26, 28, 30);
    model.result("pg5").feature("lngr3").set("descr", "\u5bf9\u5f00");
    model.result("pg5").feature().duplicate("lngr4", "lngr3");
    model.result("pg5").run();
    model.result("pg5").feature("lngr4").label("\u4e24\u53f6");
    model.result("pg5").feature("lngr4").selection().set(37, 38, 40, 42);
    model.result("pg5").feature("lngr4").set("descr", "\u4e24\u53f6");
    model.result("pg5").feature().duplicate("lngr5", "lngr4");
    model.result("pg5").run();
    model.result("pg5").feature("lngr5").label("\u4e09\u53f6 LOP");
    model.result("pg5").feature("lngr5").selection().set(49, 50, 52, 54);
    model.result("pg5").feature("lngr5").set("descr", "\u4e09\u53f6 LOP");
    model.result("pg5").feature().duplicate("lngr6", "lngr5");
    model.result("pg5").run();
    model.result("pg5").feature("lngr6").label("\u4e09\u53f6 LBP");
    model.result("pg5").feature("lngr6").selection().set(61, 62, 64, 66);
    model.result("pg5").feature("lngr6").set("descr", "\u4e09\u53f6 LBP");
    model.result("pg5").feature().duplicate("lngr7", "lngr6");
    model.result("pg5").run();
    model.result("pg5").feature("lngr7").label("\u56db\u53f6 LOP");
    model.result("pg5").feature("lngr7").selection().set(73, 74, 76, 78);
    model.result("pg5").feature("lngr7").set("descr", "\u56db\u53f6 LOP");
    model.result("pg5").feature().duplicate("lngr8", "lngr7");
    model.result("pg5").run();
    model.result("pg5").feature("lngr8").label("\u56db\u53f6 LBP");
    model.result("pg5").feature("lngr8").selection().set(85, 86, 88, 90);
    model.result("pg5").feature("lngr8").set("descr", "\u56db\u53f6 LBP");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5f53\u524d\u539a\u5ea6\u5256\u9762\u56fe");
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u539a\u5ea6\u5256\u9762\u56fe\uff0cW = 2800 N");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr1").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr2").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr2").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr3").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr3").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr4").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr4").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr5").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr5").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr6").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr6").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr7").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr7").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").feature("lngr8").set("expr", "hdb.h_rel");
    model.result("pg6").feature("lngr8").set("xdataexpr", "mod(hdb.Th+hdb.ang_bearing,2*pi)");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "manualindices", 0);
    model.result("pg6").setIndex("looplevelindices", 15, 0);
    model.result("pg6").set("rmin", "0.0");
    model.result("pg6").set("rmax", "2.0");
    model.result("pg6").run();
    model.result().dataset().create("surfdset1hjb1hdb", "Surface");
    model.result().dataset("surfdset1hjb1hdb").label("\u8868\u9762 (hjb1)");
    model.result().dataset("surfdset1hjb1hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb1hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb1hdb").selection().set(1, 2, 3, 4);
    model.result().dataset("surfdset1hjb1hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb1hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb1hdb").set("param", "expr");

    return model;
  }

  public static Model run2(Model model) {
    model.result().dataset("surfdset1hjb1hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb1.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb1.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb1.Rj))");
    model.result().dataset("surfdset1hjb1hdb").set("expry", "hdb.hjb1.r1");
    model.result().dataset("surfdset1hjb1hdb").set("defaultPlotIDs", new String[]{"pg2|hdb", "pg3|hdb"});
    model.result().dataset("surfdset1hjb1hdb").label("\u8868\u9762 (hjb1)");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb1)");
    model.result("pg7").set("data", "surfdset1hjb1hdb");
    model.result("pg7").set("edges", false);
    model.result("pg7").create("surf1", "Surface");
    model.result("pg7").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg7").feature("surf1").set("colortabletype", "discrete");
    model.result("pg7").feature("surf1").set("expr", "hdb.p");
    model.result("pg7").feature("surf1").create("hght1", "Height");
    model.result("pg7").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb1)");

    model.nodeGroup().create("grphjb1", "Results");
    model.nodeGroup("grphjb1").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb1)");
    model.nodeGroup("grphjb1").set("type", "plotgroup");
    model.nodeGroup("grphjb1").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb1").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb1)");
    model.nodeGroup("grphjb1").placeAfter("plotgroup", "pg7");

    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb1)");
    model.result("pg8").set("data", "surfdset1hjb1hdb");
    model.result("pg8").set("edges", false);
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").label("\u538b\u529b");
    model.result("pg8").feature("surf1").set("colortable", "Prism");
    model.result("pg8").feature("surf1").set("colortabletype", "discrete");
    model.result("pg8").feature("surf1").set("expr", "hdb.p");
    model.result("pg8").create("arws1", "ArrowSurface");
    model.result("pg8").feature("arws1").label("\u901f\u5ea6");
    model.result("pg8").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg8").feature("arws1").set("placement", "elements");
    model.result("pg8").feature("arws1").set("maxpointcount", 500);
    model.result("pg8").feature("arws1").set("scale", 0.001);
    model.result("pg8").feature("arws1").set("color", "black");
    model.result("pg8").feature("arws1").set("scaleactive", true);
    model.result("pg8").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg8").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb1)");

    model.nodeGroup("grphjb1").add("plotgroup", "pg7");
    model.nodeGroup("grphjb1").add("plotgroup", "pg8");

    model.result().dataset().create("surfdset1hjb2hdb", "Surface");
    model.result().dataset("surfdset1hjb2hdb").label("\u8868\u9762 (hjb2)");
    model.result().dataset("surfdset1hjb2hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb2hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb2hdb").selection().set(5, 6, 7, 8);
    model.result().dataset("surfdset1hjb2hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb2hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb2hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb2hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb2.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb2.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb2.Rj))");
    model.result().dataset("surfdset1hjb2hdb").set("expry", "hdb.hjb2.r1");
    model.result().dataset("surfdset1hjb2hdb").set("defaultPlotIDs", new String[]{"pg4|hdb", "pg5|hdb"});
    model.result().dataset("surfdset1hjb2hdb").label("\u8868\u9762 (hjb2)");
    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb2)");
    model.result("pg9").set("data", "surfdset1hjb2hdb");
    model.result("pg9").set("edges", false);
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg9").feature("surf1").set("colortabletype", "discrete");
    model.result("pg9").feature("surf1").set("expr", "hdb.p");
    model.result("pg9").feature("surf1").create("hght1", "Height");
    model.result("pg9").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb2)");

    model.nodeGroup().create("grphjb2", "Results");
    model.nodeGroup("grphjb2").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb2)");
    model.nodeGroup("grphjb2").set("type", "plotgroup");
    model.nodeGroup("grphjb2").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb2").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb2)");
    model.nodeGroup("grphjb2").placeAfter("plotgroup", "pg9");

    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb2)");
    model.result("pg10").set("data", "surfdset1hjb2hdb");
    model.result("pg10").set("edges", false);
    model.result("pg10").create("surf1", "Surface");
    model.result("pg10").feature("surf1").label("\u538b\u529b");
    model.result("pg10").feature("surf1").set("colortable", "Prism");
    model.result("pg10").feature("surf1").set("colortabletype", "discrete");
    model.result("pg10").feature("surf1").set("expr", "hdb.p");
    model.result("pg10").create("arws1", "ArrowSurface");
    model.result("pg10").feature("arws1").label("\u901f\u5ea6");
    model.result("pg10").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg10").feature("arws1").set("placement", "elements");
    model.result("pg10").feature("arws1").set("maxpointcount", 500);
    model.result("pg10").feature("arws1").set("scale", 0.001);
    model.result("pg10").feature("arws1").set("color", "black");
    model.result("pg10").feature("arws1").set("scaleactive", true);
    model.result("pg10").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg10").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb2)");

    model.nodeGroup("grphjb2").add("plotgroup", "pg9");
    model.nodeGroup("grphjb2").add("plotgroup", "pg10");

    model.result().dataset().create("surfdset1hjb3hdb", "Surface");
    model.result().dataset("surfdset1hjb3hdb").label("\u8868\u9762 (hjb3)");
    model.result().dataset("surfdset1hjb3hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb3hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb3hdb").selection().set(9, 10, 11, 12);
    model.result().dataset("surfdset1hjb3hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb3hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb3hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb3hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb3.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb3.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb3.Rj))");
    model.result().dataset("surfdset1hjb3hdb").set("expry", "hdb.hjb3.r1");
    model.result().dataset("surfdset1hjb3hdb").set("defaultPlotIDs", new String[]{"pg6|hdb", "pg7|hdb"});
    model.result().dataset("surfdset1hjb3hdb").label("\u8868\u9762 (hjb3)");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb3)");
    model.result("pg11").set("data", "surfdset1hjb3hdb");
    model.result("pg11").set("edges", false);
    model.result("pg11").create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg11").feature("surf1").set("colortabletype", "discrete");
    model.result("pg11").feature("surf1").set("expr", "hdb.p");
    model.result("pg11").feature("surf1").create("hght1", "Height");
    model.result("pg11").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb3)");

    model.nodeGroup().create("grphjb3", "Results");
    model.nodeGroup("grphjb3").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb3)");
    model.nodeGroup("grphjb3").set("type", "plotgroup");
    model.nodeGroup("grphjb3").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb3").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb3)");
    model.nodeGroup("grphjb3").placeAfter("plotgroup", "pg11");

    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb3)");
    model.result("pg12").set("data", "surfdset1hjb3hdb");
    model.result("pg12").set("edges", false);
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").label("\u538b\u529b");
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").feature("surf1").set("colortabletype", "discrete");
    model.result("pg12").feature("surf1").set("expr", "hdb.p");
    model.result("pg12").create("arws1", "ArrowSurface");
    model.result("pg12").feature("arws1").label("\u901f\u5ea6");
    model.result("pg12").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg12").feature("arws1").set("placement", "elements");
    model.result("pg12").feature("arws1").set("maxpointcount", 500);
    model.result("pg12").feature("arws1").set("scale", 0.001);
    model.result("pg12").feature("arws1").set("color", "black");
    model.result("pg12").feature("arws1").set("scaleactive", true);
    model.result("pg12").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg12").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb3)");

    model.nodeGroup("grphjb3").add("plotgroup", "pg11");
    model.nodeGroup("grphjb3").add("plotgroup", "pg12");

    model.result().dataset().create("surfdset1hjb4hdb", "Surface");
    model.result().dataset("surfdset1hjb4hdb").label("\u8868\u9762 (hjb4)");
    model.result().dataset("surfdset1hjb4hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb4hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb4hdb").selection().set(13, 14, 15, 16);
    model.result().dataset("surfdset1hjb4hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb4hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb4hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb4hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb4.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb4.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb4.Rj))");
    model.result().dataset("surfdset1hjb4hdb").set("expry", "hdb.hjb4.r1");
    model.result().dataset("surfdset1hjb4hdb").set("defaultPlotIDs", new String[]{"pg8|hdb", "pg9|hdb"});
    model.result().dataset("surfdset1hjb4hdb").label("\u8868\u9762 (hjb4)");
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb4)");
    model.result("pg13").set("data", "surfdset1hjb4hdb");
    model.result("pg13").set("edges", false);
    model.result("pg13").create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg13").feature("surf1").set("colortabletype", "discrete");
    model.result("pg13").feature("surf1").set("expr", "hdb.p");
    model.result("pg13").feature("surf1").create("hght1", "Height");
    model.result("pg13").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb4)");

    model.nodeGroup().create("grphjb4", "Results");
    model.nodeGroup("grphjb4").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb4)");
    model.nodeGroup("grphjb4").set("type", "plotgroup");
    model.nodeGroup("grphjb4").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb4").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb4)");
    model.nodeGroup("grphjb4").placeAfter("plotgroup", "pg13");

    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb4)");
    model.result("pg14").set("data", "surfdset1hjb4hdb");
    model.result("pg14").set("edges", false);
    model.result("pg14").create("surf1", "Surface");
    model.result("pg14").feature("surf1").label("\u538b\u529b");
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").feature("surf1").set("colortabletype", "discrete");
    model.result("pg14").feature("surf1").set("expr", "hdb.p");
    model.result("pg14").create("arws1", "ArrowSurface");
    model.result("pg14").feature("arws1").label("\u901f\u5ea6");
    model.result("pg14").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg14").feature("arws1").set("placement", "elements");
    model.result("pg14").feature("arws1").set("maxpointcount", 500);
    model.result("pg14").feature("arws1").set("scale", 0.001);
    model.result("pg14").feature("arws1").set("color", "black");
    model.result("pg14").feature("arws1").set("scaleactive", true);
    model.result("pg14").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg14").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb4)");

    model.nodeGroup("grphjb4").add("plotgroup", "pg13");
    model.nodeGroup("grphjb4").add("plotgroup", "pg14");

    model.result().dataset().create("surfdset1hjb5hdb", "Surface");
    model.result().dataset("surfdset1hjb5hdb").label("\u8868\u9762 (hjb5)");
    model.result().dataset("surfdset1hjb5hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb5hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb5hdb").selection().set(17, 18, 19, 20);
    model.result().dataset("surfdset1hjb5hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb5hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb5hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb5hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb5.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb5.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb5.Rj))");
    model.result().dataset("surfdset1hjb5hdb").set("expry", "hdb.hjb5.r1");
    model.result().dataset("surfdset1hjb5hdb").set("defaultPlotIDs", new String[]{"pg10|hdb", "pg11|hdb"});
    model.result().dataset("surfdset1hjb5hdb").label("\u8868\u9762 (hjb5)");
    model.result().create("pg15", "PlotGroup2D");
    model.result("pg15").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb5)");
    model.result("pg15").set("data", "surfdset1hjb5hdb");
    model.result("pg15").set("edges", false);
    model.result("pg15").create("surf1", "Surface");
    model.result("pg15").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg15").feature("surf1").set("colortabletype", "discrete");
    model.result("pg15").feature("surf1").set("expr", "hdb.p");
    model.result("pg15").feature("surf1").create("hght1", "Height");
    model.result("pg15").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb5)");

    model.nodeGroup().create("grphjb5", "Results");
    model.nodeGroup("grphjb5").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb5)");
    model.nodeGroup("grphjb5").set("type", "plotgroup");
    model.nodeGroup("grphjb5").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb5").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb5)");
    model.nodeGroup("grphjb5").placeAfter("plotgroup", "pg15");

    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb5)");
    model.result("pg16").set("data", "surfdset1hjb5hdb");
    model.result("pg16").set("edges", false);
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").label("\u538b\u529b");
    model.result("pg16").feature("surf1").set("colortable", "Prism");
    model.result("pg16").feature("surf1").set("colortabletype", "discrete");
    model.result("pg16").feature("surf1").set("expr", "hdb.p");
    model.result("pg16").create("arws1", "ArrowSurface");
    model.result("pg16").feature("arws1").label("\u901f\u5ea6");
    model.result("pg16").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg16").feature("arws1").set("placement", "elements");
    model.result("pg16").feature("arws1").set("maxpointcount", 500);
    model.result("pg16").feature("arws1").set("scale", 0.001);
    model.result("pg16").feature("arws1").set("color", "black");
    model.result("pg16").feature("arws1").set("scaleactive", true);
    model.result("pg16").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg16").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb5)");

    model.nodeGroup("grphjb5").add("plotgroup", "pg15");
    model.nodeGroup("grphjb5").add("plotgroup", "pg16");

    model.result().dataset().create("surfdset1hjb6hdb", "Surface");
    model.result().dataset("surfdset1hjb6hdb").label("\u8868\u9762 (hjb6)");
    model.result().dataset("surfdset1hjb6hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb6hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb6hdb").selection().set(21, 22, 23, 24);
    model.result().dataset("surfdset1hjb6hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb6hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb6hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb6hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb6.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb6.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb6.Rj))");
    model.result().dataset("surfdset1hjb6hdb").set("expry", "hdb.hjb6.r1");
    model.result().dataset("surfdset1hjb6hdb").set("defaultPlotIDs", new String[]{"pg12|hdb", "pg13|hdb"});
    model.result().dataset("surfdset1hjb6hdb").label("\u8868\u9762 (hjb6)");
    model.result().create("pg17", "PlotGroup2D");
    model.result("pg17").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb6)");
    model.result("pg17").set("data", "surfdset1hjb6hdb");
    model.result("pg17").set("edges", false);
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg17").feature("surf1").set("colortabletype", "discrete");
    model.result("pg17").feature("surf1").set("expr", "hdb.p");
    model.result("pg17").feature("surf1").create("hght1", "Height");
    model.result("pg17").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb6)");

    model.nodeGroup().create("grphjb6", "Results");
    model.nodeGroup("grphjb6").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb6)");
    model.nodeGroup("grphjb6").set("type", "plotgroup");
    model.nodeGroup("grphjb6").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb6").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb6)");
    model.nodeGroup("grphjb6").placeAfter("plotgroup", "pg17");

    model.result().create("pg18", "PlotGroup2D");
    model.result("pg18").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb6)");
    model.result("pg18").set("data", "surfdset1hjb6hdb");
    model.result("pg18").set("edges", false);
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").label("\u538b\u529b");
    model.result("pg18").feature("surf1").set("colortable", "Prism");
    model.result("pg18").feature("surf1").set("colortabletype", "discrete");
    model.result("pg18").feature("surf1").set("expr", "hdb.p");
    model.result("pg18").create("arws1", "ArrowSurface");
    model.result("pg18").feature("arws1").label("\u901f\u5ea6");
    model.result("pg18").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg18").feature("arws1").set("placement", "elements");
    model.result("pg18").feature("arws1").set("maxpointcount", 500);
    model.result("pg18").feature("arws1").set("scale", 0.001);
    model.result("pg18").feature("arws1").set("color", "black");
    model.result("pg18").feature("arws1").set("scaleactive", true);
    model.result("pg18").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg18").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb6)");

    model.nodeGroup("grphjb6").add("plotgroup", "pg17");
    model.nodeGroup("grphjb6").add("plotgroup", "pg18");

    model.result().dataset().create("surfdset1hjb7hdb", "Surface");
    model.result().dataset("surfdset1hjb7hdb").label("\u8868\u9762 (hjb7)");
    model.result().dataset("surfdset1hjb7hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb7hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb7hdb").selection().set(25, 26, 27, 28);
    model.result().dataset("surfdset1hjb7hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb7hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb7hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb7hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb7.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb7.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb7.Rj))");
    model.result().dataset("surfdset1hjb7hdb").set("expry", "hdb.hjb7.r1");
    model.result().dataset("surfdset1hjb7hdb").set("defaultPlotIDs", new String[]{"pg14|hdb", "pg15|hdb"});
    model.result().dataset("surfdset1hjb7hdb").label("\u8868\u9762 (hjb7)");
    model.result().create("pg19", "PlotGroup2D");
    model.result("pg19").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb7)");
    model.result("pg19").set("data", "surfdset1hjb7hdb");
    model.result("pg19").set("edges", false);
    model.result("pg19").create("surf1", "Surface");
    model.result("pg19").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg19").feature("surf1").set("colortabletype", "discrete");
    model.result("pg19").feature("surf1").set("expr", "hdb.p");
    model.result("pg19").feature("surf1").create("hght1", "Height");
    model.result("pg19").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb7)");

    model.nodeGroup().create("grphjb7", "Results");
    model.nodeGroup("grphjb7").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb7)");
    model.nodeGroup("grphjb7").set("type", "plotgroup");
    model.nodeGroup("grphjb7").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb7").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb7)");
    model.nodeGroup("grphjb7").placeAfter("plotgroup", "pg19");

    model.result().create("pg20", "PlotGroup2D");
    model.result("pg20").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb7)");
    model.result("pg20").set("data", "surfdset1hjb7hdb");
    model.result("pg20").set("edges", false);
    model.result("pg20").create("surf1", "Surface");
    model.result("pg20").feature("surf1").label("\u538b\u529b");
    model.result("pg20").feature("surf1").set("colortable", "Prism");
    model.result("pg20").feature("surf1").set("colortabletype", "discrete");
    model.result("pg20").feature("surf1").set("expr", "hdb.p");
    model.result("pg20").create("arws1", "ArrowSurface");
    model.result("pg20").feature("arws1").label("\u901f\u5ea6");
    model.result("pg20").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg20").feature("arws1").set("placement", "elements");
    model.result("pg20").feature("arws1").set("maxpointcount", 500);
    model.result("pg20").feature("arws1").set("scale", 0.001);
    model.result("pg20").feature("arws1").set("color", "black");
    model.result("pg20").feature("arws1").set("scaleactive", true);
    model.result("pg20").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg20").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb7)");

    model.nodeGroup("grphjb7").add("plotgroup", "pg19");
    model.nodeGroup("grphjb7").add("plotgroup", "pg20");

    model.result().dataset().create("surfdset1hjb8hdb", "Surface");
    model.result().dataset("surfdset1hjb8hdb").label("\u8868\u9762 (hjb8)");
    model.result().dataset("surfdset1hjb8hdb").set("data", "dset1");
    model.result().dataset("surfdset1hjb8hdb").selection().geom("geom1", 2);
    model.result().dataset("surfdset1hjb8hdb").selection().set(29, 30, 31, 32);
    model.result().dataset("surfdset1hjb8hdb").selection().inherit(false);
    model.result().dataset("surfdset1hjb8hdb").selection().embedded(false);
    model.result().dataset("surfdset1hjb8hdb").set("param", "expr");
    model.result().dataset("surfdset1hjb8hdb")
         .set("exprx", "if(elemgpmax(2,hdb.Th)-elemgpmin(2,hdb.Th)<pi,gpeval(2,hdb.Th)*hdb.hjb8.Rj,if(hdb.Th>pi,hdb.Th*hdb.hjb8.Rj,(elemgpmax(2,hdb.Th)+2*elemgpmin(2,hdb.Th))*hdb.hjb8.Rj))");
    model.result().dataset("surfdset1hjb8hdb").set("expry", "hdb.hjb8.r1");
    model.result().dataset("surfdset1hjb8hdb").set("defaultPlotIDs", new String[]{"pg16|hdb", "pg17|hdb"});
    model.result().dataset("surfdset1hjb8hdb").label("\u8868\u9762 (hjb8)");
    model.result().create("pg21", "PlotGroup2D");
    model.result("pg21").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb8)");
    model.result("pg21").set("data", "surfdset1hjb8hdb");
    model.result("pg21").set("edges", false);
    model.result("pg21").create("surf1", "Surface");
    model.result("pg21").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg21").feature("surf1").set("colortabletype", "discrete");
    model.result("pg21").feature("surf1").set("expr", "hdb.p");
    model.result("pg21").feature("surf1").create("hght1", "Height");
    model.result("pg21").label("\u5c55\u5f00\u7684\u6d41\u4f53\u538b\u529b (hjb8)");

    model.nodeGroup().create("grphjb8", "Results");
    model.nodeGroup("grphjb8").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb8)");
    model.nodeGroup("grphjb8").set("type", "plotgroup");
    model.nodeGroup("grphjb8").placeAfter("plotgroup", "pg1");
    model.nodeGroup("grphjb8").label("\u5c55\u5f00\u7684\u7ed8\u56fe (hjb8)");
    model.nodeGroup("grphjb8").placeAfter("plotgroup", "pg21");

    model.result().create("pg22", "PlotGroup2D");
    model.result("pg22").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb8)");
    model.result("pg22").set("data", "surfdset1hjb8hdb");
    model.result("pg22").set("edges", false);
    model.result("pg22").create("surf1", "Surface");
    model.result("pg22").feature("surf1").label("\u538b\u529b");
    model.result("pg22").feature("surf1").set("colortable", "Prism");
    model.result("pg22").feature("surf1").set("colortabletype", "discrete");
    model.result("pg22").feature("surf1").set("expr", "hdb.p");
    model.result("pg22").create("arws1", "ArrowSurface");
    model.result("pg22").feature("arws1").label("\u901f\u5ea6");
    model.result("pg22").feature("arws1").set("expr", new String[]{"hdb.vave_c", "hdb.vave_1"});
    model.result("pg22").feature("arws1").set("placement", "elements");
    model.result("pg22").feature("arws1").set("maxpointcount", 500);
    model.result("pg22").feature("arws1").set("scale", 0.001);
    model.result("pg22").feature("arws1").set("color", "black");
    model.result("pg22").feature("arws1").set("scaleactive", true);
    model.result("pg22").feature("arws1").set("descr", "\u6d41\u4f53\u901f\u5ea6");
    model.result("pg22").label("\u5c55\u5f00\u7684\u901f\u5ea6 (hjb8)");

    model.nodeGroup("grphjb8").add("plotgroup", "pg21");
    model.nodeGroup("grphjb8").add("plotgroup", "pg22");
    model.nodeGroup().ungroup("grphjb1");

    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("showlegendsunit", true);
    model.result("pg7").set("plotarrayenable", true);
    model.result("pg7").set("arrayshape", "square");
    model.result("pg7").set("paddingsquare", "absolute");
    model.result("pg7").set("rowpadding", "3*H");
    model.result("pg7").set("columnpadding", "3*H");
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf2", "surf1");
    model.result("pg7").feature("surf2").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("data", "surfdset1hjb2hdb");
    model.result("pg7").feature("surf2").set("inheritplot", "surf1");
    model.result("pg7").feature("surf2").set("manualindexing", true);
    model.result("pg7").feature("surf2").set("rowindex", 1);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf3", "surf1");
    model.result("pg7").feature("surf3").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").label("\u538b\u529b\uff08\u5bf9\u5f00\uff09");
    model.result("pg7").feature("surf3").set("data", "surfdset1hjb3hdb");
    model.result("pg7").feature("surf3").set("inheritplot", "surf1");
    model.result("pg7").feature("surf3").set("manualindexing", true);
    model.result("pg7").feature("surf3").set("rowindex", 2);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf4", "surf1");
    model.result("pg7").feature("surf4").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").label("\u538b\u529b\uff08\u4e24\u53f6\uff09");
    model.result("pg7").feature("surf4").set("data", "surfdset1hjb4hdb");
    model.result("pg7").feature("surf4").set("inheritplot", "surf1");
    model.result("pg7").feature("surf4").set("manualindexing", true);
    model.result("pg7").feature("surf4").set("rowindex", 3);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf5", "surf1");
    model.result("pg7").feature("surf5").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf5").label("\u538b\u529b\uff08\u4e09\u53f6 LOP\uff09");
    model.result("pg7").feature("surf5").set("data", "surfdset1hjb5hdb");
    model.result("pg7").feature("surf5").set("inheritplot", "surf1");
    model.result("pg7").feature("surf5").set("manualindexing", true);
    model.result("pg7").feature("surf5").set("colindex", 1);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf6", "surf1");
    model.result("pg7").feature("surf6").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf6").label("\u538b\u529b\uff08\u4e09\u53f6 LBP\uff09");
    model.result("pg7").feature("surf6").set("data", "surfdset1hjb6hdb");
    model.result("pg7").feature("surf6").set("inheritplot", "surf1");
    model.result("pg7").feature("surf6").set("manualindexing", true);
    model.result("pg7").feature("surf6").set("rowindex", 1);
    model.result("pg7").feature("surf6").set("colindex", 1);
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf7", "surf1");
    model.result("pg7").feature("surf7").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf7").label("\u538b\u529b\uff08\u56db\u53f6 LOP\uff09");
    model.result("pg7").feature("surf7").set("data", "surfdset1hjb7hdb");
    model.result("pg7").feature("surf7").set("inheritplot", "surf1");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg7").feature("surf7").set("manualindexing", true);
    model.result("pg7").feature("surf7").set("rowindex", 2);
    model.result("pg7").feature("surf7").set("colindex", 1);
    model.result("pg7").feature("surf1").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature().duplicate("surf8", "surf1");
    model.result("pg7").feature("surf8").set("arraydim", "2");
    model.result("pg7").run();
    model.result("pg7").feature("surf8").label("\u538b\u529b\uff08\u56db\u53f6 LBP\uff09");
    model.result("pg7").feature("surf8").set("data", "surfdset1hjb8hdb");
    model.result("pg7").feature("surf8").set("inheritplot", "surf1");
    model.result("pg7").feature("surf8").set("manualindexing", true);
    model.result("pg7").feature("surf8").set("rowindex", 3);
    model.result("pg7").feature("surf8").set("colindex", 1);
    model.result("pg7").run();
    model.result("pg7").create("tlan1", "TableAnnotation");
    model.result("pg7").feature("tlan1").set("arraydim", "2");
    model.result("pg7").feature("tlan1").set("source", "localtable");
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 0, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-3*H/2", 0, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 1, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "5*H/2", 1, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 2, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "13*H/2", 2, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u5bf9\u5f00", 2, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 3, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "21*H/2", 3, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u4e24\u53f6", 3, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+3*H", 4, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "-3*H/2", 4, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u4e09\u53f6 (LOP)", 4, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+3*H", 5, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "5*H/2", 5, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u4e09\u53f6 (LBP)", 5, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+3*H", 6, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "13*H/2", 6, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u56db\u53f6 (LOP)", 6, 2);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+3*H", 7, 0);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "21*H/2", 7, 1);
    model.result("pg7").feature("tlan1").setIndex("localtablematrix", "\u56db\u53f6 (LBP)", 7, 2);
    model.result("pg7").feature("tlan1").set("showpoint", false);
    model.result("pg7").feature("tlan1").set("anchorpoint", "center");
    model.result("pg7").run();

    model.view("view2").set("showgrid", true);

    model.result("pg8").run();
    model.result("pg8").set("titletype", "manual");
    model.result("pg8").set("title", "\u6d41\u4f53\u538b\u529b\uff0c\u6d41\u4f53\u901f\u5ea6");
    model.result("pg8").set("showlegendsunit", true);
    model.result("pg8").set("plotarrayenable", true);
    model.result("pg8").set("arrayshape", "square");
    model.result("pg8").set("paddingsquare", "absolute");
    model.result("pg8").set("rowpadding", "2*H");
    model.result("pg8").set("columnpadding", "2*H");
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("manualindexing", true);
    model.result("pg8").feature("arws1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws1").set("manualindexing", true);
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").feature().duplicate("arws2", "arws1");
    model.result("pg8").feature("surf2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").set("data", "surfdset1hjb2hdb");
    model.result("pg8").feature("surf2").set("inheritplot", "surf1");
    model.result("pg8").feature("surf2").set("rowindex", 1);
    model.result("pg8").feature("arws2").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws2").set("data", "surfdset1hjb2hdb");
    model.result("pg8").feature("arws2").set("inheritplot", "arws1");
    model.result("pg8").feature("arws2").set("rowindex", 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf1");
    model.result("pg8").feature().duplicate("arws3", "arws1");
    model.result("pg8").feature("surf3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").label("\u538b\u529b\uff08\u5bf9\u5f00\uff09");
    model.result("pg8").feature("surf3").set("data", "surfdset1hjb3hdb");
    model.result("pg8").feature("surf3").set("inheritplot", "surf1");
    model.result("pg8").feature("surf3").set("rowindex", 2);
    model.result("pg8").feature("arws3").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws3").label("\u901f\u5ea6\uff08\u5bf9\u5f00\uff09");
    model.result("pg8").feature("arws3").set("data", "surfdset1hjb3hdb");
    model.result("pg8").feature("arws3").set("inheritplot", "arws1");
    model.result("pg8").feature("arws3").set("rowindex", 2);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf4", "surf1");
    model.result("pg8").feature().duplicate("arws4", "arws1");
    model.result("pg8").feature("surf4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf4").label("\u538b\u529b\uff08\u4e24\u53f6\uff09");
    model.result("pg8").feature("surf4").set("data", "surfdset1hjb4hdb");
    model.result("pg8").feature("surf4").set("inheritplot", "surf1");
    model.result("pg8").feature("surf4").set("rowindex", 3);
    model.result("pg8").feature("arws4").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws4").set("data", "surfdset1hjb4hdb");
    model.result("pg8").feature("arws4").label("\u901f\u5ea6\uff08\u4e24\u53f6\uff09");
    model.result("pg8").feature("arws4").set("inheritplot", "arws1");
    model.result("pg8").feature("arws4").set("rowindex", 3);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf5", "surf1");
    model.result("pg8").feature().duplicate("arws5", "arws1");
    model.result("pg8").feature("surf5").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf5").label("\u538b\u529b\uff08\u4e09\u53f6 LOP\uff09");
    model.result("pg8").feature("surf5").set("data", "surfdset1hjb5hdb");
    model.result("pg8").feature("surf5").set("inheritplot", "surf1");
    model.result("pg8").feature("surf5").set("colindex", 1);
    model.result("pg8").feature("arws5").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws5").label("\u901f\u5ea6\uff08\u4e09\u53f6 LOP\uff09");
    model.result("pg8").feature("arws5").set("data", "surfdset1hjb5hdb");
    model.result("pg8").feature("arws5").set("inheritplot", "arws1");
    model.result("pg8").feature("arws5").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf6", "surf1");
    model.result("pg8").feature().duplicate("arws6", "arws1");
    model.result("pg8").feature("surf6").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf6").label("\u538b\u529b\uff08\u4e09\u53f6 LBP\uff09");
    model.result("pg8").feature("surf6").set("data", "surfdset1hjb6hdb");
    model.result("pg8").feature("surf6").set("inheritplot", "surf1");
    model.result("pg8").feature("surf6").set("rowindex", 1);
    model.result("pg8").feature("surf6").set("colindex", 1);
    model.result("pg8").feature("arws6").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws6").label("\u901f\u5ea6\uff08\u4e09\u53f6 LBP\uff09");
    model.result("pg8").feature("arws6").set("data", "surfdset1hjb6hdb");
    model.result("pg8").feature("arws6").set("inheritplot", "arws1");
    model.result("pg8").feature("arws6").set("rowindex", 1);
    model.result("pg8").feature("arws6").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf7", "surf1");
    model.result("pg8").feature().duplicate("arws7", "arws1");
    model.result("pg8").feature("surf7").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf7").label("\u538b\u529b\uff08\u56db\u53f6 LOP\uff09");
    model.result("pg8").feature("surf7").set("data", "surfdset1hjb7hdb");
    model.result("pg8").feature("surf7").set("inheritplot", "surf1");
    model.result("pg8").feature("surf7").set("rowindex", 2);
    model.result("pg8").feature("surf7").set("colindex", 1);
    model.result("pg8").feature("arws7").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws7").label("\u901f\u5ea6\uff08\u56db\u53f6 LOP\uff09");
    model.result("pg8").feature("arws7").set("data", "surfdset1hjb7hdb");
    model.result("pg8").feature("arws7").set("inheritplot", "arws1");
    model.result("pg8").feature("arws7").set("rowindex", 2);
    model.result("pg8").feature("arws7").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf8", "surf1");
    model.result("pg8").feature().duplicate("arws8", "arws1");
    model.result("pg8").feature("surf8").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("surf8").label("\u538b\u529b\uff08\u56db\u53f6 LBP\uff09");
    model.result("pg8").feature("surf8").set("data", "surfdset1hjb8hdb");
    model.result("pg8").feature("surf8").set("inheritplot", "surf1");
    model.result("pg8").feature("surf8").set("rowindex", 3);
    model.result("pg8").feature("surf8").set("colindex", 1);
    model.result("pg8").feature("arws8").set("arraydim", "2");
    model.result("pg8").run();
    model.result("pg8").feature("arws8").set("data", "surfdset1hjb8hdb");
    model.result("pg8").feature("arws8").label("\u901f\u5ea6\uff08\u56db\u53f6 LBP\uff09");
    model.result("pg8").feature("arws8").set("inheritplot", "arws1");
    model.result("pg8").feature("arws8").set("rowindex", 3);
    model.result("pg8").feature("arws8").set("colindex", 1);
    model.result("pg8").run();
    model.result("pg8").create("tlan1", "TableAnnotation");
    model.result("pg8").feature("tlan1").set("arraydim", "2");
    model.result("pg8").feature("tlan1").set("source", "localtable");
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 0, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "-H", 0, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 1, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "2*H", 1, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 2, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "5*H", 2, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u5bf9\u5f00", 2, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "Rj*pi", 3, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "8*H", 3, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u4e24\u53f6", 3, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+2*H", 4, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "-H", 4, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u4e09\u53f6 (LOP)", 4, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+2*H", 5, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "2*H", 5, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u4e09\u53f6 (LBP)", 5, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+2*H", 6, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "5*H", 6, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u56db\u53f6 (LOP)", 6, 2);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "3*Rj*pi+2*H", 7, 0);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "8*H", 7, 1);
    model.result("pg8").feature("tlan1").setIndex("localtablematrix", "\u56db\u53f6 (LBP)", 7, 2);
    model.result("pg8").feature("tlan1").set("showpoint", false);
    model.result("pg8").feature("tlan1").set("anchorpoint", "center");
    model.result("pg8").run();

    model.nodeGroup().remove("grphjb2");
    model.nodeGroup().remove("grphjb3");
    model.nodeGroup().remove("grphjb4");
    model.nodeGroup().remove("grphjb5");
    model.nodeGroup().remove("grphjb6");
    model.nodeGroup().remove("grphjb7");
    model.nodeGroup().remove("grphjb8");

    model.result("pg1").run();

    model.title("\u4e0d\u540c\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u7684\u6bd4\u8f83");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5229\u7528\u5efa\u6a21\u6765\u7814\u7a76\u4e0d\u540c\u6db2\u4f53\u52a8\u538b\u8f74\u9888\u8f74\u627f\u7684\u6027\u80fd\uff0c\u5176\u4e2d\u4f7f\u7528\u201c\u6db2\u4f53\u52a8\u538b\u8f74\u627f\u201d\u63a5\u53e3\u6c42\u89e3\u96f7\u8bfa\u65b9\u7a0b\uff0c\u4ee5\u8ba1\u7b97\u56db\u79cd\u4e0d\u540c\u7c7b\u578b\u8f74\u627f\uff08\u5706\u67f1\u5f62\u3001\u692d\u5706\u5f62\u3001\u5bf9\u5f00\u548c\u591a\u6cb9\u53f6\u8f74\u627f\uff09\u7684\u8584\u6db2\u819c\u4e2d\u4ea7\u751f\u7684\u538b\u529b\u3002\n\n\u5206\u6790\u7ed3\u679c\u6db5\u76d6\u4e86\u8f74\u627f\u4e0a\u7684\u6d41\u4f53\u538b\u529b\u5206\u5e03\u3001\u8f74\u9888\u504f\u5fc3\u7387\u4e0e\u8f7d\u8377\u7684\u5173\u7cfb\u56fe\u3001\u8f74\u9888\u7684\u7a33\u6001\u4f4d\u7f6e\uff0c\u4ee5\u53ca\u8f74\u9888\u4e0e\u8f74\u627f\u540c\u5fc3\u65f6\u7684\u6d41\u4f53\u539a\u5ea6\u5206\u5e03\u3002\u6b64\u5916\uff0c\u8fd8\u5c55\u793a\u4e86\u4ece\u8f74\u627f\u8868\u9762\u5c55\u5f00\u540e\u7684\u6d41\u4f53\u538b\u529b\u548c\u6d41\u4f53\u901f\u5ea6\u5206\u5e03\u56fe\u3002\n\n\u5728\u672c\u6559\u7a0b\u4e2d\uff0c\u6211\u4eec\u6bd4\u8f83\u4e86\u4e0d\u540c\u8f74\u627f\u5728\u5e73\u8861\u72b6\u6001\u4e0b\u8f74\u9888\u4f4d\u7f6e\u548c\u6db2\u819c\u539a\u5ea6\u5206\u5e03\u7684\u53d8\u5316\u60c5\u51b5\uff0c\u901a\u8fc7\u6bd4\u8f83\u8fd9\u4e9b\u91cf\uff0c\u53ef\u4ee5\u627e\u5230\u76f8\u4f3c\u5de5\u4f5c\u6761\u4ef6\u4e0b\u7684\u6700\u4f73\u8f74\u627f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hydrodynamic_bearings_comparison.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
