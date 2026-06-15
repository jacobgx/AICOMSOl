/*
 * step_thrust_bearing.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:48 by COMSOL 6.3.0.290. */
public class step_thrust_bearing {

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

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("N", "6", "\u74e6\u6570\u91cf");
    model.param().set("Ro", "0.1[m]", "\u74e6\u7684\u5916\u534a\u5f84");
    model.param().set("Ri", "0.05[m]", "\u74e6\u7684\u5185\u534a\u5f84");
    model.param().set("gAng", "15[deg]", "\u51f9\u69fd\u89d2\u5ea6 (deg)");
    model.param().set("padAng", "360[deg]/N-gAng", "\u74e6\u89d2\u5ea6 (deg)");
    model.param().set("secAng", "gAng+padAng", "\u622a\u9762\u89d2\u5ea6 (deg)");
    model.param().set("angSpeed", "1000[rad/s]", "\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("hg", "2e-4[m]", "\u69fd\u6df1");
    model.param().set("h_film", "1.6e-4[m]", "\u819c\u539a");
    model.param().set("rho_c", "866[kg/m^3]", "\u6d41\u4f53\u5bc6\u5ea6");
    model.param().set("mu_f", "0.072[Pa*s]", "\u6d41\u4f53\u7684\u52a8\u529b\u9ecf\u5ea6");

    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "Ro");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").setIndex("layer", "Ro-Ri", 0);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("del1", "Delete");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").init(2);
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("del1").selection("input").set("c1", 5);
    model.component("comp1").geom("geom1").feature("wp1").geom().run("del1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("hdb").prop("EquationType")
         .set("EquationType", "ReynoldsEquationWithCavitation");
    model.component("comp1").physics("hdb").prop("EquationType").set("sftransition", "0.5[MPa]");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu_f"});

    model.component("comp1").physics("hdb").create("htb1", "HydrodynamicThrustBearing", 2);
    model.component("comp1").physics("hdb").feature("htb1").selection().all();
    model.component("comp1").physics("hdb").feature("htb1").set("RefNormal", "InverseOrientation");
    model.component("comp1").physics("hdb").feature("htb1").set("BearingType", "StepBearing");
    model.component("comp1").physics("hdb").feature("htb1").set("gamma_p", "padAng");
    model.component("comp1").physics("hdb").feature("htb1").set("di", "2*Ri");
    model.component("comp1").physics("hdb").feature("htb1").set("do", "2*Ro");
    model.component("comp1").physics("hdb").feature("htb1").set("hs", "hg");
    model.component("comp1").physics("hdb").feature("htb1").set("h_film", "h_film");
    model.component("comp1").physics("hdb").feature("htb1").set("GrooveType", "ConstantArc");
    model.component("comp1").physics("hdb").feature("htb1").set("Ow", "angSpeed");
    model.component("comp1").physics("hdb").feature("htb1").set("rho_c", "rho_c");
    model.component("comp1").physics("hdb").feature("bax1").set("bearingAxis", "zAxis");
    model.component("comp1").physics("hdb").feature("bax1").set("e_aux", new int[]{1, 0, 0});
    model.component("comp1").physics("hdb").feature("bax1").set("ang_bearing", "gAng");
    model.component("comp1").physics("hdb").feature("init1").set("pfilm", "100000[Pa]");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(4, 5, 8, 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 90);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(1, 6, 9, 12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 20);
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
    model.result().dataset().create("surf1", "Surface");
    model.result().dataset("surf1").selection().all();
    model.result("pg1").run();
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("showlegendsunit", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u538b\u529b\uff08\u9ad8\u5ea6\uff09");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("showlegendsunit", true);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "hdb.p");
    model.result("pg2").feature("surf1").set("colortable", "Traffic");
    model.result("pg2").feature("surf1").create("hght1", "Height");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("hght1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("hght1").set("scale", "2e-3");
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").run();
    model.result("pg3").label("\u8d28\u91cf\u5206\u6570");
    model.result("pg3").set("titletype", "label");
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
    model.result("pg4").set("titletype", "label");
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
    model.result("pg8").set("legendpos", "upperright");
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
    model.result("pg2").run();

    model.title("\u7acb\u5f0f\u63a8\u529b\u8f74\u627f");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u5206\u6790\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\uff0c\u8fd9\u7c7b\u8f74\u627f\u7531\u4e00\u4e2a\u9636\u68af\u8f74\u627f\u8868\u9762\u7ec4\u6210\uff0c\u8f74\u7684\u7aef\u90e8\u5728\u8be5\u8868\u9762\u4e0a\u65cb\u8f6c\u3002\u6574\u4e2a\u88c5\u914d\u6d78\u5728\u6da6\u6ed1\u6cb9\u4e2d\u3002\u672c\u4f8b\u7814\u7a76\u516d\u74e6\u7acb\u5f0f\u63a8\u529b\u8f74\u627f\uff0c\u5176\u4e2d\u5047\u8bbe\u8f74\u73af\u5728\u65cb\u8f6c\u65f6\uff0c\u8f74\u627f\u4e2d\u6ca1\u6709\u4efb\u4f55\u8f74\u5411\u8fd0\u52a8\u3002\u5728\u53c2\u6570\u5316\u7814\u7a76\u4e2d\uff0c\u819c\u539a\u548c\u8f74\u73af\u7684\u65cb\u8f6c\u901f\u5ea6\u662f\u53d8\u5316\u7684\u3002\n\n\u7ed3\u679c\u5305\u542b\u8f74\u627f\u7684\u538b\u529b\u5206\u5e03\u3001\u8f74\u74e6\u5f84\u5411\u548c\u5468\u5411\u7684\u538b\u529b\u53d8\u5316\u4ee5\u53ca\u8f74\u627f\u7684\u627f\u8f7d\u80fd\u529b\u3002");

    model.label("step_thrust_bearing.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
