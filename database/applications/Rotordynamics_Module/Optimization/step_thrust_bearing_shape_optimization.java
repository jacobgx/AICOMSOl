/*
 * step_thrust_bearing_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:46 by COMSOL 6.3.0.290. */
public class step_thrust_bearing_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("hdb", "HydrodynamicBearing", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/hdb", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("N", "5", "\u74e6\u6570\u91cf");
    model.param().set("Ro", "0.1[m]", "\u74e6\u7684\u5916\u534a\u5f84");
    model.param().set("Ri", "0.5*Ro", "\u74e6\u7684\u5185\u534a\u5f84");
    model.param().set("gAng", "15[deg]", "\u51f9\u69fd\u89d2\u5ea6 (deg)");
    model.param().set("padAng", "360[deg]/N-gAng", "\u74e6\u89d2\u5ea6 (deg)");
    model.param().set("secAng", "gAng+padAng", "\u622a\u9762\u89d2\u5ea6 (deg)");
    model.param().set("angSpeed", "1000[rad/s]", "\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("hg", "2e-4[m]", "\u69fd\u6df1");
    model.param().set("h_film", "1e-4[m]", "\u819c\u539a");
    model.param().set("rho_c", "866[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu_f", "0.072[Pa*s]", "\u6d41\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");
    model.param().set("maxDisp", "0.15*Ro", "\u6700\u5927\u4f4d\u79fb");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Ro");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("angle", "gAng");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "Ro-Ri", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c2", "c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("angle", "padAng/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c2").set("rot", "gAng");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("c3", "c2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c3").set("rot", "gAng+padAng/2");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c3");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("disksel1", "DiskSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel1")
         .label("\u8981\u5220\u9664\u7684\u57df");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel1").set("r", "Ri*1.01");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("disksel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input")
         .named("disksel1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").set("selresult", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("disksel2", "DiskSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2")
         .label("\u5706\u76d8\u9009\u62e9\uff1a\u524d\u7f18");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("r", "1.01*Ro");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("rin", "0.99*Ri");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("angle1", "gAng-1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("angle2", "gAng+1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel2").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("disksel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("disksel3", "disksel2");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel3").set("angle1", "secAng-1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel3").set("angle2", "secAng+1");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("disksel3");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel3")
         .label("\u5706\u76d8\u9009\u62e9\uff1a\u540e\u7f18");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("disksel4", "DiskSelection");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel4").set("r", "1.01*Ro");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel4").set("rin", "0.99*Ri");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel4").set("angle2", "gAng");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel4").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("disksel4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel4")
         .label("\u5706\u76d8\u9009\u62e9\uff1a\u8f74\u627f\u69fd");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature().duplicate("disksel5", "disksel4");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel5")
         .label("\u5706\u76d8\u9009\u62e9\uff1a\u8f74\u74e6");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel5").set("angle1", "gAng");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("disksel5").set("angle2", "360/N");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("disksel5");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("rot1", "Rotate");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1").selection("input").named("del1");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("rot1")
         .set("rot", "range(0,secAng,360-secAng)");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("rot1");
    model.component("comp1").geom("geom1").run("wp1");
    model.component("comp1").geom("geom1").create("unisel1", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel1").label("\u8f74\u74e6\u7684\u524d\u7f18");
    model.component("comp1").geom("geom1").feature("unisel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel1").set("input", new String[]{"wp1_disksel2"});
    model.component("comp1").geom("geom1").feature().duplicate("unisel2", "unisel1");
    model.component("comp1").geom("geom1").feature("unisel2").label("\u8f74\u74e6\u7684\u540e\u7f18");
    model.component("comp1").geom("geom1").runPre("unisel2");
    model.component("comp1").geom("geom1").feature("unisel2").set("input", new String[]{"wp1_disksel3"});
    model.component("comp1").geom("geom1").run("unisel2");
    model.component("comp1").geom("geom1").create("unisel3", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("unisel3").set("input", new String[]{"wp1_disksel4"});
    model.component("comp1").geom("geom1").feature("unisel3").label("\u8f74\u627f\u69fd");
    model.component("comp1").geom("geom1").feature().duplicate("unisel4", "unisel3");
    model.component("comp1").geom("geom1").feature("unisel4").label("\u8f74\u74e6");
    model.component("comp1").geom("geom1").runPre("unisel4");
    model.component("comp1").geom("geom1").feature("unisel4").set("input", new String[]{"wp1_disksel5"});
    model.component("comp1").geom("geom1").run("unisel4");
    model.component("comp1").geom("geom1").create("adjsel1", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel1").set("input", new String[]{"unisel3"});
    model.component("comp1").geom("geom1").feature("adjsel1").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel1").label("\u8f74\u627f\u69fd\u8fb9");
    model.component("comp1").geom("geom1").run("adjsel1");
    model.component("comp1").geom("geom1").create("cylsel1", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel1").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("cylsel1").set("inputent", "selections");
    model.component("comp1").geom("geom1").feature("cylsel1").set("input", new String[]{"adjsel1"});
    model.component("comp1").geom("geom1").feature("cylsel1").set("r", "1.01*Ri");
    model.component("comp1").geom("geom1").feature("cylsel1").set("rin", "0.99*Ri");
    model.component("comp1").geom("geom1").feature("cylsel1").set("condition", "inside");
    model.component("comp1").geom("geom1").feature("cylsel1").label("\u8f74\u627f\u69fd\u5185\u8fb9");
    model.component("comp1").geom("geom1").feature().duplicate("adjsel2", "adjsel1");
    model.component("comp1").geom("geom1").feature().duplicate("cylsel2", "cylsel1");
    model.component("comp1").geom("geom1").feature("adjsel2").label("\u8f74\u74e6\u8fb9");
    model.component("comp1").geom("geom1").runPre("adjsel2");
    model.component("comp1").geom("geom1").feature("adjsel2").set("input", new String[]{"unisel4"});
    model.component("comp1").geom("geom1").run("adjsel2");
    model.component("comp1").geom("geom1").feature("cylsel2").label("\u8f74\u74e6\u5185\u8fb9");
    model.component("comp1").geom("geom1").feature("cylsel2").set("input", new String[]{"adjsel2"});
    model.component("comp1").geom("geom1").create("cylsel3", "CylinderSelection");
    model.component("comp1").geom("geom1").feature("cylsel3").label("\u63a7\u5236\u8fb9\u754c");
    model.component("comp1").geom("geom1").feature("cylsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("cylsel3").set("r", "INf");
    model.component("comp1").geom("geom1").feature("cylsel3").set("angle1", "-padAng*0.51");
    model.component("comp1").geom("geom1").feature("cylsel3").set("angle2", "gAng+0.51*padAng");
    model.component("comp1").geom("geom1").feature("cylsel3").set("condition", "inside");
    model.component("comp1").geom("geom1").run("cylsel3");
    model.component("comp1").geom("geom1").create("comsel1", "ComplementSelection");
    model.component("comp1").geom("geom1").feature("comsel1").label("\u6247\u533a\u5bf9\u79f0");
    model.component("comp1").geom("geom1").feature("comsel1").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("comsel1").set("input", new String[]{"cylsel3"});
    model.component("comp1").geom("geom1").run("comsel1");
    model.component("comp1").geom("geom1").create("adjsel3", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel3").label("\u56fa\u5b9a\u8fb9");
    model.component("comp1").geom("geom1").feature("adjsel3").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel3").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel3").set("input", new String[]{"cylsel3"});

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").selection().geom("geom1", 2);
    model.component("comp1").variable("var1").selection().named("geom1_unisel3");
    model.component("comp1").variable("var1").set("hf", "hg+h_film");
    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff1a\u8f74\u627f\u69fd");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").selection().named("geom1_unisel4");
    model.component("comp1").variable("var2").set("hf", "h_film");
    model.component("comp1").variable("var2").label("\u53d8\u91cf\uff1a\u8f74\u74e6");

    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").prop("EquationType").set("sftransition", "0.5[MPa]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("hdb").create("htb1", "HydrodynamicThrustBearing", 2);
    model.component("comp1").physics("hdb").feature("htb1").selection().all();
    model.component("comp1").physics("hdb").feature("htb1").set("RefNormal", "InverseOrientation");
    model.component("comp1").physics("hdb").feature("htb1").set("BearingType", "UserDef");
    model.component("comp1").physics("hdb").feature("htb1").set("hB1", "hf");
    model.component("comp1").physics("hdb").feature("htb1").set("Ow", "angSpeed");
    model.component("comp1").physics("hdb").feature("htb1").set("rho_c", "rho_c");
    model.component("comp1").physics("hdb").feature("bax1").set("bearingAxis", "zAxis");
    model.component("comp1").physics("hdb").feature("bax1").set("e_aux", new int[]{1, 0, 0});
    model.component("comp1").physics("hdb").feature("init1").set("pfilm", "100000[Pa]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().named("geom1_cylsel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", "round(gAng)");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().named("geom1_cylsel2");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", "round(padAng/2)");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "N", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "N", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").feature("stat").setIndex("pname", "h_film", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(6e-5,2e-5,16e-5)", 0);
    model.study("std1").feature("stat").setIndex("pname", "N", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("pname", "N", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "", 1);
    model.study("std1").feature("stat").setIndex("punit", "", 1);
    model.study("std1").feature("stat").setIndex("pname", "angSpeed", 1);
    model.study("std1").feature("stat").setIndex("plistarr", "range(500,100,1000)", 1);
    model.study("std1").feature("stat").set("sweeptype", "filled");
    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u8bbe\u8ba1");
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
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().all();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u538b\u529b\uff08\u9ad8\u5ea6\uff09");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "hdb.p");
    model.result("pg2").feature("surf1").set("colortable", "Traffic");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("hght1").set("scale", "2e-8");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u8d28\u91cf\u5206\u6570");
    model.result("pg3").create("con1", "Contour");
    model.result("pg3").feature("con1").set("expr", "hdb.theta");
    model.result("pg3").feature("con1").set("descr", "\u8d28\u91cf\u5206\u6570");
    model.result("pg3").feature("con1").set("contourtype", "filled");
    model.result("pg3").feature("con1").set("number", 5);
    model.result("pg3").feature("con1").set("colortable", "JupiterAuroraBorealis");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").run();
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", "hg-hdb.h");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("hght1").set("scale", 100);
    model.result("pg4").run();
    model.result("pg4").label("\u8f74\u74e6\u5256\u9762");
    model.result("pg4").set("edges", false);
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").setIndex("genpoints", 0, 1, 0);
    model.result().dataset("cln1").setIndex("genpoints", "Ro", 1, 1);
    model.result().dataset("cln1").label("\u4e09\u7ef4\u622a\u7ebf\uff1a\u5f84\u5411\u7ebf");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "last", 0);
    model.result("pg5").label("\u538b\u529b\u5f84\u5411\u5206\u5e03\uff08\u819c\u539a\uff09");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "hdb.p");
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg5").feature("lngr1").set("legendpattern", "h = eval(h_film, um) \\mu m");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("legendpos", "upperleft");
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").setIndex("looplevelinput", "last", 1);
    model.result("pg6").setIndex("looplevelinput", "all", 0);
    model.result("pg6").label("\u538b\u529b\u5f84\u5411\u5206\u5e03\uff08\u89d2\u901f\u5ea6\uff09");
    model.result("pg6").run();
    model.result("pg6").feature("lngr1").set("legendpattern", "\\Omega = eval(angSpeed) rad/s");
    model.result("pg6").run();
    model.result().dataset().create("pc1", "ParCurve3D");
    model.result().dataset("pc1").set("parmax1", "2*pi/N");
    model.result().dataset("pc1").set("exprx", "0.5*(Ro+Ri)*cos(s)");
    model.result().dataset("pc1").set("expry", "0.5*(Ro+Ri)*sin(s)");
    model.result().dataset("pc1").label("\u4e09\u7ef4\u53c2\u6570\u5316\u66f2\u7ebf\uff1a\u5468\u5411\u7ebf");
    model.result("pg5").run();
    model.result().duplicate("pg7", "pg5");
    model.result().duplicate("pg8", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u538b\u529b\u5468\u5411\u5206\u5e03\uff08\u819c\u539a\uff09");
    model.result("pg7").set("data", "pc1");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").label("\u538b\u529b\u5468\u5411\u5206\u5e03\uff08\u89d2\u901f\u5ea6\uff09");
    model.result("pg8").set("data", "pc1");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").label("\u62ac\u5347\u529b");
    model.result("pg9").set("titletype", "label");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("expr", new String[]{"hdb.htb1.Fcz"});
    model.result("pg9").feature("glob1")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result("pg9").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg9").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg9").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg9").feature("glob1").set("legendpattern", "h = eval(h_film, um) \\mu m");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("legendpos", "upperleft");
    model.result("pg9").set("xlabelactive", true);
    model.result("pg9").set("xlabel", "\u8f74\u7684\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg9").run();

    model.param().set("N", "4");
    model.param().descr("N", "\u8f74\u74e6\u6570");
    model.param().set("gAng", "125[deg]/N");
    model.param().descr("gAng", "\u8f74\u627f\u69fd\u89d2\u5ea6 (deg)");

    model.component("comp1").geom("geom1").run("cylsel2");
    model.component("comp1").geom("geom1").create("adjsel4", "AdjacentSelection");
    model.component("comp1").geom("geom1").feature("adjsel4").set("entitydim", 2);
    model.component("comp1").geom("geom1").feature("adjsel4").set("input", new String[]{"unisel3", "unisel4"});
    model.component("comp1").geom("geom1").feature("adjsel4").set("outputdim", 1);
    model.component("comp1").geom("geom1").feature("adjsel4").label("\u5706\u5f62\u8fb9\u754c");
    model.component("comp1").geom("geom1").run("adjsel4");
    model.component("comp1").geom("geom1").create("unisel5", "UnionSelection");
    model.component("comp1").geom("geom1").feature("unisel5").set("entitydim", 1);
    model.component("comp1").geom("geom1").feature("unisel5")
         .set("input", new String[]{"unisel1", "unisel2", "adjsel4"});
    model.component("comp1").geom("geom1").feature("unisel5").label("\u5706\u5f62\u8fb9 & \u8f74\u74e6\u8fb9");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();

    model.component("comp1").geom("geom1").run();

    model.component("comp1").common().create("pls1", "PolynomialShell");
    model.component("comp1").common("pls1").selection().named("geom1_cylsel3");
    model.component("comp1").common("pls1").set("maximumDisplacementType", "Custom");
    model.component("comp1").common("pls1").setIndex("move_lBound", "-maxDisp", 0);
    model.component("comp1").common("pls1").setIndex("move_uBound", "maxDisp", 0);
    model.component("comp1").common("pls1").setIndex("move_lBound", "-maxDisp", 1);
    model.component("comp1").common("pls1").setIndex("move_uBound", "maxDisp", 1);
    model.component("comp1").common("pls1").setIndex("move_lock", true, 2);
    model.component("comp1").common("pls1").set("order", "3");
    model.component("comp1").common().create("sss1", "SectorShape");
    model.component("comp1").common("sss1").selection().geom("geom1", 2);
    model.component("comp1").common("sss1").selection().named("geom1_comsel1");
    model.component("comp1").common("sss1").set("transformationType", "rotation");
    model.component("comp1").common().remove("fsd1");
    model.component("comp1").common().create("fse1", "FixedShapeEdge");
    model.component("comp1").common("fse1").selection().named("geom1_adjsel3");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/hdb", true);
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 15);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.hdb.htb1.Fcz"});
    model.study("std2").feature("sho")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("probesel", "none");
    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5f62\u72b6\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg10", "PlotGroup3D");
    model.result("pg10").label("\u6d41\u4f53\u538b\u529b (hdb) 1");
    model.result("pg10").set("data", "dset2");
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg10").feature("surf1").set("smooth", "internal");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature().create("con1", "Contour");
    model.result("pg10").feature("con1").set("levelrounding", false);
    model.result("pg10").feature("con1").set("colorlegend", false);
    model.result("pg10").feature("con1").set("smooth", "internal");
    model.result("pg10").feature("con1").set("inherittubescale", false);
    model.result("pg10").feature("con1").set("data", "parent");
    model.result("pg10").feature("con1").set("inheritplot", "surf1");
    model.result().create("pg11", "PlotGroup3D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1")
         .label("\u7814\u7a76 2\uff1a\u5f62\u72b6\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg11").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg11").set("data", "dset2");
    model.result("pg11").set("frametype", "geometry");
    model.result("pg11").set("edgecolor", "gray");
    model.result("pg11").set("titletype", "none");
    model.result("pg11").create("line1", "Line");
    model.result("pg11").feature("line1").set("expr", "1");
    model.result("pg11").feature("line1").set("coloring", "uniform");
    model.result("pg11").feature("line1").set("color", "fromtheme");
    model.result("pg11").create("con1", "Surface");
    model.result("pg11").feature("con1").set("expr", "pls1.rel_disp");
    model.result("pg11").feature("con1").set("colortabletype", "discrete");
    model.result("pg11").feature("con1").set("bandcount", 20);
    model.result("pg11").feature("con1").set("rangecoloractive", true);
    model.result("pg11").feature("con1").set("rangecolormin", 0);
    model.result("pg11").feature("con1").set("rangecolormax", 1);
    model.result("pg11").feature("con1").set("colortable", "RainbowLight");
    model.result("pg11").feature("con1").set("smooth", "none");
    model.result("pg11").create("arws1", "ArrowSurface");
    model.result("pg11").feature("arws1").set("data", "dset2g1");
    model.result("pg11").feature("arws1").set("expr", new String[]{"pls1.dXg", "pls1.dYg", "pls1.dZg"});
    model.result("pg11").feature("arws1").set("scaleactive", true);
    model.result("pg11").feature("arws1").set("scale", "1");
    model.result("pg11").feature("arws1").set("placement", "uniform");
    model.result("pg11").feature("arws1").set("arrowcount", 200);
    model.result("pg11").feature("arws1").create("sel1", "Selection");
    model.result("pg11").feature("arws1").feature("sel1").selection().named("dsel_pls1");
    model.result("pg10").run();

    model.sol("sol2").feature("o1").set("gcmma", false);
    model.sol("sol2").feature("o1").set("nojacmethod", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg10").run();

    model.study("std2").feature("sho").set("probewindow", "");
    model.study("std2").feature("sho").set("plotgroup", "pg2");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg10").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg10").label("\u6d41\u4f53\u538b\u529b\uff0c\u5f62\u72b6\u4f18\u5316 (hdb)");
    model.result("pg11").run();
    model.result().remove("pg11");
    model.result("pg2").run();
    model.result().duplicate("pg11", "pg2");
    model.result().duplicate("pg12", "pg3");
    model.result().duplicate("pg13", "pg4");
    model.result("pg11").run();
    model.result().dataset().duplicate("surf2", "surf1");
    model.result().dataset("surf2").set("data", "dset2");
    model.result().dataset("surf2").label("\u8868\u9762\uff08\u5f62\u72b6\u4f18\u5316\uff09");
    model.result("pg11").run();
    model.result("pg11").set("data", "surf2");
    model.result("pg11").label("\u538b\u529b\uff0c\u5f62\u72b6\u4f18\u5316\uff08\u9ad8\u5ea6\uff09");
    model.result("pg11").run();
    model.result("pg12").run();
    model.result("pg12").set("data", "dset2");
    model.result("pg12").label("\u8d28\u91cf\u5206\u6570\uff0c\u5f62\u72b6\u4f18\u5316");
    model.result("pg12").run();
    model.result("pg13").run();
    model.result("pg13").set("data", "surf2");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg13").label("\u8f74\u74e6\u5256\u9762\uff0c\u5f62\u72b6\u4f18\u5316");
    model.result("pg13").run();
    model.result("pg5").run();
    model.result().duplicate("pg14", "pg5");
    model.result().duplicate("pg15", "pg7");
    model.result("pg14").run();
    model.result().dataset().duplicate("cln2", "cln1");
    model.result().dataset().duplicate("pc2", "pc1");
    model.result().dataset("cln2").label("\u4e09\u7ef4\u622a\u7ebf\uff1a\u5f84\u5411\u7ebf\uff08\u4f18\u5316\uff09");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().dataset("pc2")
         .label("\u4e09\u7ef4\u53c2\u6570\u5316\u66f2\u7ebf\uff1a\u5468\u5411\u7ebf\uff08\u4f18\u5316\uff09");
    model.result().dataset("pc2").set("data", "dset2");
    model.result("pg14").run();
    model.result("pg14").set("data", "cln2");
    model.result("pg14").label("\u538b\u529b\u7684\u5f84\u5411\u5206\u5e03\uff08\u5f62\u72b6\u4f18\u5316\uff09");
    model.result("pg14").run();
    model.result("pg14").set("showlegends", false);
    model.result("pg15").run();
    model.result("pg15").set("data", "pc2");
    model.result("pg15").label("\u538b\u529b\u7684\u5468\u5411\u5206\u5e03\uff08\u5f62\u72b6\u4f18\u5316\uff09");
    model.result("pg15").run();
    model.result("pg15").set("showlegends", false);
    model.result().create("pg16", "PlotGroup3D");
    model.result("pg16").run();
    model.result("pg16").set("data", "dset2");
    model.result("pg16").label("\u7f51\u683c");
    model.result("pg16").create("mesh1", "Mesh");
    model.result("pg16").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg16").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg16").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg16").run();
    model.result().create("pg17", "PlotGroup3D");
    model.result("pg17").run();
    model.result("pg17").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg17").set("data", "dset2");
    model.result("pg17").create("line1", "Line");
    model.result("pg17").feature("line1").set("expr", "1");
    model.result("pg17").feature("line1").set("coloring", "uniform");
    model.result("pg17").feature("line1").set("color", "gray");
    model.result("pg17").feature("line1").create("def1", "Deform");
    model.result("pg17").run();
    model.result("pg17").feature("line1").feature("def1").set("expr", new String[]{"-material.dX", "", ""});
    model.result("pg17").feature("line1").feature("def1").setIndex("expr", "-material.dY", 1);
    model.result("pg17").feature("line1").feature("def1").setIndex("expr", 0, 2);
    model.result("pg17").feature("line1").feature("def1").set("scaleactive", true);
    model.result("pg17").feature("line1").feature("def1").set("scale", 1);
    model.result("pg17").run();
    model.result("pg17").run();
    model.result("pg17").create("arwl1", "ArrowLine");
    model.result("pg17").feature("arwl1").setIndex("expr", "material.dX", 0);
    model.result("pg17").feature("arwl1").setIndex("expr", "material.dY", 1);
    model.result("pg17").feature("arwl1").setIndex("expr", "material.dZ", 2);
    model.result("pg17").feature("arwl1").set("placement", "elements");
    model.result("pg17").feature("arwl1").set("arrowbase", "head");
    model.result("pg17").feature("arwl1").set("scaleactive", true);
    model.result("pg17").feature("arwl1").create("col1", "Color");
    model.result("pg17").run();
    model.result("pg17").feature("arwl1").feature("col1").set("expr", "pls1.rel_disp");
    model.result("pg17").feature("arwl1").feature("col1").set("descr", "\u76f8\u5bf9\u4f4d\u79fb");
    model.result("pg17").feature("arwl1").feature("col1").set("rangecoloractive", true);
    model.result("pg17").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg17").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg17").run();
    model.result("pg17").create("surf1", "Surface");
    model.result("pg17").feature("surf1").set("expr", "hf");
    model.result("pg17").feature("surf1").set("colortabletrans", "reverse");
    model.result("pg17").run();
    model.result("pg17").run();
    model.result("pg17").set("legendpos", "rightdouble");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").add("plotgroup", "pg5");
    model.nodeGroup("grp1").add("plotgroup", "pg6");
    model.nodeGroup("grp1").add("plotgroup", "pg7");
    model.nodeGroup("grp1").add("plotgroup", "pg8");
    model.nodeGroup("grp1").add("plotgroup", "pg9");
    model.nodeGroup("grp1").label("\u521d\u59cb\u8bbe\u8ba1");

    model.result("pg10").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg10");
    model.nodeGroup("grp2").add("plotgroup", "pg11");
    model.nodeGroup("grp2").add("plotgroup", "pg12");
    model.nodeGroup("grp2").add("plotgroup", "pg13");
    model.nodeGroup("grp2").add("plotgroup", "pg14");
    model.nodeGroup("grp2").add("plotgroup", "pg15");
    model.nodeGroup("grp2").add("plotgroup", "pg16");
    model.nodeGroup("grp2").add("plotgroup", "pg17");
    model.nodeGroup("grp2").label("\u5f62\u72b6\u4f18\u5316");

    model.result().dataset().create("filt1", "Filter");
    model.result().dataset("filt1").set("data", "dset2");
    model.result().dataset("filt1").set("expr", "1");
    model.result().dataset("filt1").createMeshPart("mcomp1", "mgeom1", "mpart1", "imp1");

    model.mesh("mpart1").feature("imp1").set("facepartition", "minimal");
    model.mesh("mpart1").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 3);

    model.component("comp2").mesh().create("mesh2");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("mesh", "mpart1");
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").feature("imp1").set("simplifymesh", false);
    model.component("comp2").geom("geom2").feature("imp1").set("formsolid", false);
    model.component("comp2").geom("geom2").run("imp1");
    model.component("comp2").geom("geom2").run();

    model.component("comp2").material().copy("mat2", "mat1");

    model.component("comp2").physics().copy("hdb2", "hdb");
    model.component("comp2").physics("hdb2").feature("init1")
         .set("pfilm", "100000[Pa]*hdb2.max(hdb2.hB1)/(0.1*hdb2.max(hdb2.hB1)+hdb2.hB1)");

    model.component("comp2").variable().copy("var3", "var1");
    model.component("comp2").variable().copy("var4", "var2");
    model.component("comp2").variable("var3").selection().geom("geom2", 2);
    model.component("comp2").variable("var3").selection().named("geom2_imp1_mpart1_imp1_geom1_unisel3");
    model.component("comp2").variable("var4").selection().geom("geom2", 2);
    model.component("comp2").variable("var4").selection().named("geom2_imp1_mpart1_imp1_geom1_unisel4");

    model.component("comp2").mesh("mesh2").create("ftri1", "FreeTri");
    model.component("comp2").mesh("mesh2").feature("ftri1").selection().remaining();
    model.component("comp2").mesh("mesh2").feature("size").set("hmax", "2*pi*Ro/360");
    model.component("comp2").mesh("mesh2").feature("size").set("hmin", "2*pi*Ro/720");
    model.component("comp2").mesh("mesh2").run();

    model.study("std2").feature("stat").setSolveFor("/physics/hdb2", false);
    model.study("std1").feature("stat").setSolveFor("/physics/hdb2", false);
    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/hdb", false);
    model.study("std3").feature("stat").setSolveFor("/physics/hdb2", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u91cd\u65b0\u5212\u5206\u7f51\u683c\u5e76\u9a8c\u8bc1");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg18", "PlotGroup3D");
    model.result("pg18").run();
    model.result("pg18").set("data", "dset4");
    model.result("pg18").label("\u9a8c\u8bc1");
    model.result("pg18").create("surf1", "Surface");
    model.result("pg18").feature("surf1").create("mrkr1", "Marker");
    model.result("pg18").run();
    model.result("pg18").feature("surf1").feature("mrkr1").set("precision", 4);
    model.result("pg18").run();
    model.result("pg18").feature().duplicate("surf2", "surf1");
    model.result("pg18").run();
    model.result("pg18").feature("surf2").set("data", "dset2");
    model.result("pg18").feature("surf2").create("trn1", "Transformation");
    model.result("pg18").run();
    model.result("pg18").feature("surf2").feature("trn1").set("move", new double[]{0.2, 0, 0});
    model.result("pg18").run();
    model.result("pg18").run();
    model.result("pg18").feature("surf2").set("inheritplot", "surf1");
    model.result("pg18").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/hdb", true);
    model.study("std4").feature("stat").setSolveFor("/physics/hdb2", false);
    model.study("std4").feature("stat").set("probesel", "none");
    model.study("std4").create("sho", "ShapeOptimization");
    model.study("std4").feature("sho").set("mmamaxiter", 15);
    model.study("std4").feature("sho").set("optobj", new String[]{"comp1.hdb.htb1.Fcz"});
    model.study("std4").feature("sho")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.study("std4").feature("sho").set("objectivetype", "maximization");
    model.study("std4").feature("sho").set("objectivescaling", "init");
    model.study("std4").feature("sho").set("probesel", "none");
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "N", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("pname", "N", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("plistarr", "3 4 5", 0);
    model.study("std4").feature("param").set("probesel", "none");
    model.study("std4").label("\u7814\u7a76 4\uff1a\u5f62\u72b6\u4f18\u5316\u626b\u63cf");
    model.study("std4").setGenPlots(false);
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("o1").set("gcmma", false);
    model.sol("sol4").feature("o1").set("nojacmethod", false);

    model.study("std4").createAutoSequences("all");

    model.sol().create("sol5");
    model.sol("sol5").study("std4");
    model.sol("sol5").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol5");
    model.batch("p1").run("compute");

    model.study("std4").feature("sho").set("probewindow", "");

    model.result("pg17").run();
    model.result().duplicate("pg19", "pg17");

    model.nodeGroup("grp2").add("plotgroup", "pg19");

    model.result("pg19").run();
    model.result("pg19").label("\u5f62\u72b6\u4f18\u5316\u626b\u63cf");
    model.result("pg19").set("data", "dset7");
    model.result("pg19").setIndex("looplevel", 1, 0);
    model.result("pg19").run();
    model.result().create("pg20", "PlotGroup1D");
    model.result("pg20").run();
    model.result("pg20").label("\u76ee\u6807 vs. \u8f74\u74e6\u6570");
    model.result("pg20").set("data", "dset7");
    model.result("pg20").create("glob1", "Global");
    model.result("pg20").feature("glob1").set("markerpos", "datapoints");
    model.result("pg20").feature("glob1").set("linewidth", "preference");
    model.result("pg20").feature("glob1").set("expr", new String[]{"hdb.htb1.Fcz"});
    model.result("pg20").feature("glob1")
         .set("descr", new String[]{"\u8f74\u73af\u4e0a\u7684\u6d41\u4f53\u8f7d\u8377\uff0cz \u5206\u91cf"});
    model.result("pg20").run();
    model.result("pg20").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg20").feature("glob1").set("legend", false);
    model.result("pg20").run();
    model.result("pg17").run();
    model.result().duplicate("pg21", "pg17");

    model.nodeGroup("grp2").add("plotgroup", "pg21");

    model.result("pg21").run();
    model.result("pg21").label("\u7f29\u7565\u56fe");

    model.nodeGroup("grp2").remove("plotgroup", "pg21", false);

    model.result("pg21").run();
    model.result().move("pg21", 19);
    model.result().move("pg21", 20);
    model.result("pg21").run();
    model.result("pg21").feature().remove("surf1");
    model.result("pg21").run();
    model.result("pg21").feature("arwl1").set("placement", "uniform");
    model.result("pg21").feature("arwl1").set("arrowcount", 500);
    model.result("pg21").run();
    model.result("pg21").feature("arwl1").feature("col1").set("rangecoloractive", false);
    model.result("pg21").run();
    model.result("pg21").run();
    model.result("pg21").feature("line1").set("linetype", "tube");
    model.result("pg21").feature("line1").set("radiusexpr", "4e-4");
    model.result("pg21").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg21").feature().duplicate("line2", "line1");
    model.result("pg21").run();
    model.result("pg21").feature("line2").set("color", "black");
    model.result("pg21").run();
    model.result("pg21").feature("line2").feature().remove("def1");
    model.result("pg21").run();
    model.result("pg21").run();

    model.title("\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\u7684\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u5bf9\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\u8fdb\u884c\u5f62\u72b6\u4f18\u5316\uff0c\u4ee5\u6700\u5927\u9650\u5ea6\u5730\u63d0\u9ad8\u627f\u8f7d\u529b\u3002\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\u7531\u4e00\u4e2a\u9636\u68af\u8f74\u627f\u8868\u9762\u7ec4\u6210\uff0c\u8f74\u7684\u7aef\u90e8\u5728\u8be5\u8868\u9762\u4e0a\u65cb\u8f6c\u3002\u6574\u4e2a\u88c5\u914d\u6d78\u5728\u6da6\u6ed1\u6cb9\u4e2d\u3002\u5176\u4e2d\u5047\u8bbe\u8f74\u73af\u5728\u65cb\u8f6c\u65f6\uff0c\u8f74\u627f\u4e2d\u6ca1\u6709\u4efb\u4f55\u8f74\u5411\u8fd0\u52a8\u3002\n\n\u6700\u7ec8\u8bbe\u8ba1\u663e\u793a\u4e86\u53f0\u9636\u7684\u6700\u4f73\u5f62\u72b6\u3002\u6b64\u5916\uff0c\u6a21\u578b\u4e2d\u8fd8\u5bf9\u53f0\u9636\u6570\u8fdb\u884c\u4e86\u626b\u63cf\uff0c\u4ee5\u786e\u5b9a\u6700\u4f73\u6570\u91cf\u3002");

    model.component("comp1").mesh("mesh1").clearMesh();
    model.mesh("mpart1").clearMesh();
    model.component("comp2").mesh("mesh2").clearMesh();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();

    model.label("step_thrust_bearing_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
