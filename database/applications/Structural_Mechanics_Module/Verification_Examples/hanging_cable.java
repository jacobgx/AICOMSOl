/*
 * hanging_cable.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:23 by COMSOL 6.3.0.290. */
public class hanging_cable {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("wire", "Wire", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/wire", true);

    model.param().set("L_cable", "5.4[m]");
    model.param().descr("L_cable", "\u7535\u7f06\u957f\u5ea6");
    model.param().set("D_cable", "5[mm]");
    model.param().descr("D_cable", "\u7535\u7f06\u76f4\u5f84");
    model.param().set("A_cable", "pi/4*D_cable^2");
    model.param().descr("A_cable", "\u7535\u7f06\u6a2a\u622a\u9762\u79ef");
    model.param().set("u_support", "0.2[m]");
    model.param().descr("u_support", "\u7aef\u70b9\u4f4d\u79fb");
    model.param().set("d_support", "L_cable - 2*u_support");
    model.param().descr("d_support", "\u652f\u627f\u8ddd\u79bb");
    model.param().set("m_lamp", "0.5[kg]");
    model.param().descr("m_lamp", "\u706f\u8d28\u91cf");
    model.param().set("D_lamp", "400[mm]");
    model.param().descr("D_lamp", "\u706f\u76f4\u5f84");
    model.param().set("E", "2.0e11[Pa]");
    model.param().descr("E", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("rho", "7850[kg/m^3]");
    model.param().descr("rho", "\u5bc6\u5ea6");
    model.param().set("q_dyn", "(1.225[kg/m^3]/2)*(15[m/s])^2");
    model.param().descr("q_dyn", "\u52a8\u6001\u538b\u529b");

    model.group().create("lg1", "LoadGroup");
    model.group("lg1").label("\u706f\u91cd\u91cf");
    model.group("lg1").paramName("lgLamp");
    model.group().create("lg2", "LoadGroup");
    model.group("lg2").label("\u98ce\u8f7d\u8377");
    model.group("lg2").paramName("lgWind");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "-L_cable/2", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "L_cable/2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pare1", "PartitionEdges");
    model.component("comp1").geom("geom1").feature("pare1").selection("edge").set("pol1", 1);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.3, 0);
    model.component("comp1").geom("geom1").feature("pare1").setIndex("param", 0.8, 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("ElasticWire", "ElasticWire", "Elastic_wire");
    model.component("comp1").material("mat1").propertyGroup("ElasticWire").set("k_A", new String[]{"A_cable*E"});
    model.component("comp1").material("mat1").propertyGroup("ElasticWire").set("rho_L", new String[]{"rho*A_cable"});

    model.component("comp1").physics("wire").feature("elw1").set("ecl", "-5e-3");
    model.component("comp1").physics("wire").feature("elw1").set("area", "A_cable");
    model.component("comp1").physics("wire").create("disp1", "Displacement0", 0);
    model.component("comp1").physics("wire").feature("disp1").selection().set(1, 4);
    model.component("comp1").physics("wire").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("wire").feature("disp1").setIndex("U0", "-sign(X)*u_support", 0);
    model.component("comp1").physics("wire").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("wire").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("wire").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("wire").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("wire").feature("el1")
         .label("\u8fb9\u8f7d\u8377\uff1a\u7535\u7f06\u4e0a\u7684\u98ce");
    model.component("comp1").physics("wire").feature("el1").selection().all();
    model.component("comp1").physics("wire").feature("el1").set("forceType", "TotalForce");
    model.component("comp1").physics("wire").feature("el1")
         .set("force", new String[]{"0", "1.1*q_dyn*(L_cable*D_cable)", "0"});
    model.component("comp1").physics("wire").feature("el1").set("loadGroup", "lg2");
    model.component("comp1").physics("wire").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("wire").feature("pl1")
         .label("\u70b9\u8f7d\u8377\uff1a\u706f\u4e0a\u7684\u98ce");
    model.component("comp1").physics("wire").feature("pl1").selection().set(2, 3);
    model.component("comp1").physics("wire").feature("pl1")
         .set("forcePoint", new String[]{"0", "0.45*q_dyn*pi*(D_lamp/2)^2", "0"});
    model.component("comp1").physics("wire").feature("pl1").set("loadGroup", "lg2");
    model.component("comp1").physics("wire").create("pm1", "PointMass", 0);
    model.component("comp1").physics("wire").feature("pm1").label("\u70b9\u8d28\u91cf\uff1a\u706f");
    model.component("comp1").physics("wire").feature("pm1").selection().set(2, 3);
    model.component("comp1").physics("wire").feature("pm1")
         .set("pointmass", "if(group.lgLamp, group.lgLamp*m_lamp, 0)");
    model.component("comp1").physics("wire").feature("init1")
         .set("u", new String[]{"0", "0", "(-L_cable/2 + X)*(L_cable/2 + X)*(2/L_cable^2)*sqrt(L_cable^2 - d_support^2)"});

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().all();
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "L_cable/60");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "L_cable/60");
    model.component("comp1").mesh("mesh1").run();

    model.func().create("an1", "Analytic");
    model.func("an1").set("funcname", "y_catenary");
    model.func("an1").set("expr", "a*(cosh(X/a) - cosh(0.5*d_support/a))");
    model.func("an1").set("args", "X, a, d_support");
    model.func("an1").set("fununit", "m");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").setIndex("argunit", "m", 1);
    model.func("an1").setIndex("argunit", "m", 2);

    model.component().create("comp2", true);

    model.component("comp2").physics().create("ge", "GlobalEquations");

    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

    model.component("comp2").physics("ge").prop("EquationForm").set("form", "Automatic");
    model.component("comp2").physics("ge").feature("ge1").setIndex("name", "a", 0, 0);
    model.component("comp2").physics("ge").feature("ge1")
         .setIndex("equation", "L_cable - 2*a*sinh(d_support/(2*a))", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").setIndex("initialValueU", 3, 0, 0);
    model.component("comp2").physics("ge").feature("ge1")
         .setIndex("description", "\u60ac\u94fe\u7ebf\u53c2\u6570", 0, 0);
    model.component("comp2").physics("ge").feature("ge1").set("DependentVariableQuantity", "length");
    model.component("comp2").physics("ge").feature("ge1").set("SourceTermQuantity", "length");

    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 3", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 2, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 2, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u81ea\u91cd", 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u6709\u706f", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u6709\u706f\u548c\u98ce", 2);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 2, 1);
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-6");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("s1").feature("fc1").set("maxiter", 250);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 3, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"wire.Nxl"});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("colortable", "Rainbow");
    model.result("pg1").feature("line1").set("colortabletrans", "none");
    model.result("pg1").feature("line1").set("colorscalemode", "linear");
    model.result("pg1").label("\u529b (wire)");
    model.result("pg1").feature("line1").set("colortable", "Wave");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", "wire.re");
    model.result("pg1").feature("line1").set("resolution", "extrafine");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line1").feature("def").set("scale", "1");
    model.result("pg1").feature("line1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("line1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("data", "dset1");
    model.result().numerical("gev1").set("expr", new String[]{"comp2.a"});
    model.result().numerical("gev1").set("descr", new String[]{"\u60ac\u94fe\u7ebf\u53c2\u6570"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").set("tuberadiusscale", 10);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u7f06\u6320\u5ea6");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u5f53\u524d\u6c34\u5e73\u4f4d\u7f6e (m)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u4f4d\u79fb (m)");
    model.result("pg2").set("legendpos", "uppermiddle");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1").set("expr", "w");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "X+u");
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("legendmethod", "manual");
    model.result("pg2").feature("lngr1").setIndex("legends", "\u81ea\u91cd", 0);
    model.result("pg2").feature("lngr1").setIndex("legends", "\u6709\u706f", 1);
    model.result("pg2").feature("lngr1").setIndex("legends", "\u6709\u706f\u548c\u98ce\uff08\u5782\u76f4\uff09", 2);
    model.result("pg2").feature("lngr1").set("linestyle", "cycle");
    model.result("pg2").feature("lngr1").set("linewidth", 2);
    model.result("pg2").feature("lngr1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("lngr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg2").run();
    model.result("pg2").feature("lngr1").feature("gmrk1").set("display", "min");
    model.result("pg2").feature("lngr1").feature("gmrk1").set("precision", 3);
    model.result("pg2").feature("lngr1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").run();
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").set("data", "dset1");
    model.result("pg2").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg2").feature("lngr2").set("expr", "-sqrt(v^2+w^2)");
    model.result("pg2").feature("lngr2").setIndex("legends", "\u6709\u706f\u548c\u98ce\uff08\u603b\u8ba1\uff09", 0);
    model.result("pg2").feature("lngr2").setIndex("legends", "", 1);
    model.result("pg2").feature("lngr2").setIndex("legends", "", 2);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u529b");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u5f53\u524d\u6c34\u5e73\u4f4d\u7f6e (m)");
    model.result("pg3").set("legendpos", "uppermiddle");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().all();
    model.result("pg3").feature("lngr1").set("expr", "wire.Nxl");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "X+u");
    model.result("pg3").feature("lngr1").set("linestyle", "cycle");
    model.result("pg3").feature("lngr1").set("linewidth", 2);
    model.result("pg3").feature("lngr1").set("legend", true);
    model.result("pg3").feature("lngr1").set("resolution", "norefine");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u89e3\u6790\u89e3 vs. \u8ba1\u7b97\u89e3");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u6c34\u5e73\u4f4d\u7f6e (m)");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u4f4d\u79fb (m)");
    model.result("pg4").setIndex("looplevelinput", "first", 0);
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg4").feature("lngr1").set("linewidth", "preference");
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", "y_catenary(X, comp2.a, d_support)");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "X");
    model.result("pg4").feature("lngr1").set("linewidth", 2);
    model.result("pg4").feature("lngr1").set("linemarker", "cycle");
    model.result("pg4").feature("lngr1").set("markerpos", "interp");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "\u89e3\u6790", 0);
    model.result("pg4").feature().duplicate("lngr2", "lngr1");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").create("gmrk1", "GraphMarker");
    model.result("pg4").feature("lngr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg4").run();
    model.result("pg4").feature("lngr1").feature("gmrk1").set("display", "min");
    model.result("pg4").feature("lngr1").feature("gmrk1").set("precision", 3);
    model.result("pg4").feature("lngr1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg4").run();
    model.result("pg4").feature("lngr2").set("expr", "w");
    model.result("pg4").feature("lngr2").set("xdataexpr", "X+u");
    model.result("pg4").feature("lngr2").setIndex("legends", "\u8ba1\u7b97\u503c", 0);
    model.result("pg4").run();
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").setIndex("looplevel", 3, 0);
    model.result("pg6").label("\u70b9\u8f7d\u8377 (wire)");
    model.result("pg6").set("showlegends", true);
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsunit", true);
    model.result("pg6").create("arpt1", "ArrowPoint");
    model.result("pg6").feature("arpt1").set("expr", new String[]{"wire.pl1.fx", "wire.pl1.fy", "wire.pl1.fz"});
    model.result("pg6").feature("arpt1").set("arrowbase", "tail");
    model.result("pg6").feature("arpt1").label("\u70b9\u8f7d\u8377\uff1a\u706f\u4e0a\u7684\u98ce");
    model.result("pg6").feature("arpt1").set("inheritplot", "none");
    model.result("pg6").feature("arpt1").create("col", "Color");
    model.result("pg6").feature("arpt1").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arpt1").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arpt1").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arpt1").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arpt1").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arpt1").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arpt1").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arpt1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arpt1").set("color", "blue");
    model.result("pg6").feature("arpt1").create("def", "Deform");
    model.result("pg6").feature("arpt1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arpt1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arpt1").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arpt1").feature("def").set("scale", 1);
    model.result("pg6").create("arpt2", "ArrowPoint");
    model.result("pg6").feature("arpt2").set("expr", new String[]{"wire.pm1.fX", "wire.pm1.fY", "wire.pm1.fZ"});
    model.result("pg6").feature("arpt2").set("arrowbase", "tail");
    model.result("pg6").feature("arpt2").label("\u70b9\u8d28\u91cf\uff1a\u706f");
    model.result("pg6").feature("arpt2").set("inheritplot", "arpt1");
    model.result("pg6").feature("arpt2").create("col", "Color");
    model.result("pg6").feature("arpt2").feature("col").set("colortable", "Rainbow");
    model.result("pg6").feature("arpt2").feature("col").set("colortabletrans", "none");
    model.result("pg6").feature("arpt2").feature("col").set("colorscalemode", "linear");
    model.result("pg6").feature("arpt2").feature("col").set("colordata", "arrowlength");
    model.result("pg6").feature("arpt2").feature("col").set("coloring", "gradient");
    model.result("pg6").feature("arpt2").feature("col").set("topcolor", "red");
    model.result("pg6").feature("arpt2").feature("col").set("bottomcolor", "custom");
    model.result("pg6").feature("arpt2").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg6").feature("arpt2").set("color", "blue");
    model.result("pg6").feature("arpt2").create("def", "Deform");
    model.result("pg6").feature("arpt2").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg6").feature("arpt2").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg6").feature("arpt2").feature("def").set("scaleactive", true);
    model.result("pg6").feature("arpt2").feature("def").set("scale", 1);
    model.result("pg6").label("\u70b9\u8f7d\u8377 (wire)");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").label("\u7f29\u7565\u56fe");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").setIndex("looplevel", 2, 0);
    model.result("pg5").run();
    model.result("pg5").feature("line1").set("colortable", "Prism");
    model.result("pg5").feature("line1").set("tuberadiusscale", 15);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("arwl1", "ArrowLine");
    model.result("pg5").feature("arwl1").set("expr", new String[]{"wire.fgx", "wire.fgy", "wire.fgz"});
    model.result("pg5").feature("arwl1").set("descr", "\u91cd\u529b");
    model.result("pg5").feature("arwl1").set("arrowcount", 30);
    model.result("pg5").feature("arwl1").set("scaleactive", true);
    model.result("pg5").feature("arwl1").set("scale", 4.0E-6);
    model.result("pg5").feature("arwl1").set("inheritplot", "line1");
    model.result("pg5").feature("arwl1").set("inheritarrowscale", false);
    model.result("pg5").feature("arwl1").set("inheritcolor", false);
    model.result("pg5").feature("arwl1").set("inheritrange", false);
    model.result("pg5").feature("arwl1").feature().copy("def", "pg5/line1/def");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").create("pt1", "Point");
    model.result("pg5").feature("pt1").set("expr", "if(abs(X) < L_cable/2, 1, nan)");
    model.result("pg5").feature("pt1").set("radiusexpr", "0.15");
    model.result("pg5").feature("pt1").set("sphereradiusscaleactive", true);
    model.result("pg5").feature("pt1").set("coloring", "uniform");
    model.result("pg5").feature("pt1").set("color", "gray");
    model.result("pg5").feature("pt1").set("inheritplot", "line1");
    model.result("pg5").feature("pt1").set("inheritcolor", false);
    model.result("pg5").feature("pt1").set("inheritrange", false);
    model.result("pg5").feature("pt1").set("inheritspherescale", false);
    model.result("pg5").feature("pt1").create("def1", "Deform");
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").feature().copy("arpt2", "pg6/arpt2");
    model.result("pg5").run();
    model.result("pg5").feature("arpt2").set("inheritplot", "line1");
    model.result("pg5").feature("arpt2").set("inheritarrowscale", false);
    model.result("pg5").feature("arpt2").set("inheritcolor", false);
    model.result("pg5").feature("arpt2").set("inheritrange", false);
    model.result("pg5").feature("arpt2").set("scaleactive", true);
    model.result("pg5").feature("arpt2").set("scale", 0.1);
    model.result("pg5").feature("arpt2").create("trn1", "Transformation");
    model.result("pg5").run();
    model.result("pg5").feature("arpt2").feature("trn1").set("move", new double[]{0, 0, -0.15});
    model.result("pg5").feature("arpt2").feature("trn1").set("applytodatasetedges", false);
    model.result("pg5").run();
    model.result("pg5").run();

    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg5").set("showlegends", false);

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().remove("pg5");
    model.result("pg6").run();
    model.result().remove("pg6");

    model.component("comp1").view("view1").set("showgrid", true);
    model.component("comp1").view("view1").set("showaxisorientation", true);

    model.result("pg1").run();

    model.title("\u60ac\u6302\u7535\u7f06");

    model
         .description("\u7535\u7f06\u662f\u4e00\u79cd\u7ed3\u6784\u4ef6\uff0c\u4ec5\u5728\u5176\u5207\u5411\u5177\u6709\u521a\u5ea6\uff0c\u4f46\u51e0\u4e4e\u6ca1\u6709\u5f2f\u66f2\u521a\u5ea6\u3002\u5f53\u5b83\u53ea\u5728\u4e24\u7aef\u53d7\u5230\u652f\u6491\u65f6\uff0c\u4f1a\u5728\u91cd\u529b\u8f7d\u8377\u4e0b\u53d1\u751f\u504f\u8f6c\uff0c\u5f62\u6210\u4e00\u6761\u79f0\u4e3a\u60ac\u94fe\u7ebf\u7684\u66f2\u7ebf\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u7ebf\u7f06\u201d\u63a5\u53e3\u5bf9\u7535\u7f06\u3001\u7535\u7ebf\u6216\u7ebf\u4e32\u7b49\u7ec4\u4ef6\u8fdb\u884c\u5efa\u6a21\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("hanging_cable.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
