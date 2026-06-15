/*
 * notched_beam_damage.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:29 by COMSOL 6.3.0.290. */
public class notched_beam_damage {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Damage");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("depth", "50[mm]", "\u6881\u6df1\u5ea6");
    model.param().set("D0", "400[mm]", "\u57fa\u7840\u5927\u5c0f\u53c2\u6570");
    model.param().set("n", "2", "\u6837\u672c\u53c2\u6570");
    model.param().set("m", "0.2", "\u5207\u53e3\u6df1\u5ea6\u53c2\u6570");
    model.param().set("Dn", "D0/2^(n-1)", "\u6837\u672c\u9ad8\u5ea6");
    model.param().set("am", "m*Dn", "\u5207\u53e3\u6df1\u5ea6");
    model.param().set("loadSurf", "Dn/10", "\u8fb9\u754c\u8f7d\u8377\u8868\u9762\u5bbd\u5ea6");
    model.param().set("eSize", "Dn/30", "\u5355\u5143\u5927\u5c0f");
    model.param().set("maxCMOD", "0.5[mm]/n", "\u6700\u5927\u88c2\u7eb9\u5f00\u53e3\u4f4d\u79fb");
    model.param().set("lscale", "10[mm]", "\u957f\u5ea6\u5c3a\u5ea6");
    model.param().set("para", "0", "\u8377\u8f7d\u53c2\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"3.5*Dn", "Dn"});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"0", "Dn/2"});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.5*Dn-loadSurf/2", 0);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "loadSurf", 1);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.75*Dn-loadSurf/2", 2);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.5*Dn-loadSurf/2", 3);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "loadSurf", 4);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.5*Dn-loadSurf/2", 5);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "0.75*Dn-loadSurf/2", 6);
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "loadSurf", 7);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("pch1", "ParameterCheck");
    model.component("comp1").geom("geom1").feature("pch1").set("condition", "am<=0");
    model.component("comp1").geom("geom1").feature("pch1")
         .set("message", "\u7f3a\u53e3\u5c3a\u5bf8\u5fc5\u987b\u5927\u4e8e 0");
    model.component("comp1").geom("geom1").run("pch1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"eSize", "am"});
    model.component("comp1").geom("geom1").feature("r2").set("base", "center");
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "am/2"});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("r1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("r2");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "-Dn*1.75 Dn*1.75");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "am am");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("mce1", "MeshControlEdges");
    model.component("comp1").geom("geom1").feature("mce1").selection("input")
         .set("fin", 4, 6, 8, 9, 11, 13, 14, 16, 18, 19, 21, 23, 24, 27, 30, 31, 33, 34, 36, 38, 39, 41, 43, 44, 46, 48, 49);
    model.component("comp1").geom("geom1").feature("mce1").set("includevtx", false);
    model.component("comp1").geom("geom1").run("mce1");
    model.component("comp1").geom("geom1").create("mcv1", "MeshControlVertices");
    model.component("comp1").geom("geom1").feature("mcv1").selection("input")
         .set("mce1", 2, 5, 6, 8, 9, 10, 11, 12, 13, 14, 20, 21, 23, 24, 25, 27, 28, 30, 31, 33);
    model.component("comp1").geom("geom1").run("mcv1");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").set("opname", "CMOD_right");
    model.component("comp1").cpl("aveop1").label("\u88c2\u53e3\u5f20\u5f00\u4f4d\u79fb\uff0c\u53f3");
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop1").selection().set(8);
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").label("\u88c2\u53e3\u5f20\u5f00\u4f4d\u79fb\uff0c\u5de6");
    model.component("comp1").cpl("aveop2").set("opname", "CMOD_left");
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 0);
    model.component("comp1").cpl("aveop2").selection().set(6);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("CMOD", "CMOD_right(u)-CMOD_left(u)");
    model.component("comp1").variable("var1").descr("CMOD", "\u88c2\u53e3\u5f20\u5f00\u4f4d\u79fb");

    model.component("comp1").physics("solid").prop("Type2D").set("Type2D", "PlaneStress");
    model.component("comp1").physics("solid").prop("d").set("d", "depth");
    model.component("comp1").physics("solid").feature("lemm1").create("dmg1", "Damage", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg1")
         .label("\u635f\u4f24\uff1a\u88c2\u7f1d\u5e26");
    model.component("comp1").physics("solid").feature("lemm1").feature().duplicate("dmg2", "dmg1");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2")
         .label("\u635f\u4f24\uff1a\u9690\u5f0f\u68af\u5ea6");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("regType", "impGradient");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("lint", "lscale");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmg2").set("hdmg", "3*lscale");
    model.component("comp1").physics("solid").create("rig1", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig1").selection().set(4);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 0);
    model.component("comp1").physics("solid").feature("rig1").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").create("rig2", "RigidConnector", 1);
    model.component("comp1").physics("solid").feature("rig2").selection().set(12);
    model.component("comp1").physics("solid").feature("rig2").setIndex("Direction", true, 1);
    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(6);
    model.component("comp1").physics("solid").feature("bndl1").set("forceType", "TotalForce");
    model.component("comp1").physics("solid").feature("bndl1").set("force", new String[]{"0", "-load", "0"});
    model.component("comp1").physics("solid").create("ge1", "GlobalEquations", -1);
    model.component("comp1").physics("solid").feature("ge1").label("\u8f7d\u8377\u63a7\u5236");
    model.component("comp1").physics("solid").feature("ge1").setIndex("name", "load", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").setIndex("equation", "CMOD-para*maxCMOD", 0, 0);
    model.component("comp1").physics("solid").feature("ge1").set("DependentVariableQuantity", "force");
    model.component("comp1").physics("solid").feature("ge1").set("SourceTermQuantity", "displacement");
    model.component("comp1").physics("solid").create("disc1", "Discretization", -1);
    model.component("comp1").physics("solid").feature("disc1").label("\u79bb\u6563\u5316\uff0c\u7ebf\u6027");
    model.component("comp1").physics("solid").feature("disc1").set("order_displacement", 1);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"37[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.21"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"2400"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ScalarDamage", "ScalarDamage", "Scalar_damage");
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").set("sigmap", new String[]{"3.9[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ScalarDamage").set("Gf", new String[]{"85"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "eSize");
    model.component("comp1").mesh("mesh1").feature("map1").set("smoothcontrol", false);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 13);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(4, 12);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(10, 20);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/dmg2"});
    model.study("std1").feature("stat").setEntry("discretization", "solid", "disc1");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "depth", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "depth", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "m", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std1").label("\u7814\u7a76\uff1a\u88c2\u7f1d\u5e26");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 41, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"displacement", "\u4f4d\u79fb", "m", "m"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "mm", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").setIndex("quantityunits", new String[]{"force", "\u529b", "N", "N"}, 2);
    model.result().configuration("prfu1").setIndex("quantityunits", "kN", 2, 3);
    model.result().configuration("prfu1").apply();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").setIndex("looplevel", 41, 0);
    model.result("pg2").label("\u635f\u4f24 (solid)");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.dmgGp"});
    model.result("pg2").feature("surf1").set("inheritplot", "none");
    model.result("pg2").feature("surf1").set("resolution", "normal");
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("bandcount", 11);
    model.result("pg2").feature("surf1").set("colortable", "RainbowLight");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("descr", "\u635f\u4f24");
    model.result("pg2").label("\u635f\u4f24 (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u635f\u4f24\uff0c\u88c2\u7f1d\u5e26");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").set("edges", false);
    model.result("pg2").run();
    model.result("pg2").feature("surf1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def1").set("scale", 100);
    model.result("pg2").run();
    model.result("pg2").create("mesh1", "Mesh");
    model.result("pg2").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg2").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg2").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg2").feature("mesh1").set("elemcolor", "none");
    model.result("pg2").feature("mesh1").set("inheritplot", "surf1");
    model.result("pg2").feature("mesh1").create("def1", "Deform");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u5e94\u53d8\uff0c\u88c2\u7f1d\u5e26");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("edges", false);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.kappadmgGp");
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u5e94\u53d8\u6700\u5927\u503c");
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").set("smooth", "none");
    model.result("pg3").run();
    model.result("pg3").create("mesh1", "Mesh");
    model.result("pg3").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg3").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg3").feature("mesh1").set("elemcolor", "none");
    model.result("pg3").feature("mesh1").set("inheritplot", "surf1");
    model.result("pg3").feature("mesh1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "depth", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "depth", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "para", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "range(0,0.025,1)", 0);
    model.study("std2").label("\u7814\u7a76\uff1a\u9690\u5f0f\u68af\u5ea6");
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result().duplicate("pg5", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u635f\u4f24\uff0c\u9690\u5f0f\u68af\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u53d8\uff0c\u9690\u5f0f\u68af\u5ea6");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u8f7d\u8377 vs. \u6320\u5ea6");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "load", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "\u72b6\u6001\u53d8\u91cf\u8f7d\u8377", 0);
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "-CMOD_left(v)");
    model.result("pg6").feature("glob1").set("xdataunit", "mm");
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u88c2\u7f1d\u5e26", 0);
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("glob2", "glob1");
    model.result("pg6").run();
    model.result("pg6").feature("glob2").set("data", "dset2");
    model.result("pg6").feature("glob2").setIndex("legends", "\u9690\u5f0f\u68af\u5ea6", 0);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u6320\u5ea6 (mm)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u8f7d\u8377 (kN)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u8f7d\u8377 vs. CMOD");
    model.result("pg7").set("xlabel", "CMOD (mm)");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("xdataexpr", "CMOD");
    model.result("pg7").run();
    model.result("pg7").feature("glob2").set("xdataexpr", "CMOD");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").importData("notched_beam_damage_measured.txt");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "none");
    model.result("pg8").create("tblp1", "Table");
    model.result("pg8").feature("tblp1").set("source", "table");
    model.result("pg8").feature("tblp1").set("table", "tbl1");
    model.result("pg8").feature("tblp1").set("linewidth", "preference");
    model.result("pg8").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg8").run();
    model.result("pg7").run();
    model.result("pg7").feature().copy("tblp1", "pg8/tblp1");
    model.result("pg7").run();
    model.result("pg7").feature("tblp1").set("linestyle", "none");
    model.result("pg7").feature("tblp1").set("linemarker", "circle");
    model.result("pg7").feature("tblp1").set("legend", true);
    model.result("pg7").feature("tblp1").set("legendmethod", "manual");
    model.result("pg7").feature("tblp1").setIndex("legends", "\u6d4b\u91cf\u7684\u6570\u636e", 0);
    model.result("pg7").run();
    model.result("pg8").run();
    model.result().remove("pg8");
    model.result().dataset().create("cpt1", "CutPoint2D");
    model.result().dataset("cpt1").label("\u622a\u70b9\uff0c\u7814\u7a76 1");
    model.result().dataset("cpt1").set("pointx", 0);
    model.result().dataset("cpt1").set("pointy", "{3*eSize/2 13*eSize/2 23*eSize/2 33*eSize/2}+am");
    model.result().dataset().duplicate("cpt2", "cpt1");
    model.result().dataset("cpt2").label("\u622a\u70b9\uff0c\u7814\u7a76 2");
    model.result().dataset("cpt2").set("data", "dset2");
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u635f\u4f24\u7684\u5e94\u529b vs. \u5e94\u53d8");
    model.result("pg8").create("ptgr1", "PointGraph");
    model.result("pg8").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg8").feature("ptgr1").set("linewidth", "preference");
    model.result("pg8").feature("ptgr1").set("data", "cpt1");
    model.result("pg8").feature("ptgr1").set("expr", "solid.sdGpxx");
    model.result("pg8").feature("ptgr1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u635f\u4f24\uff0cxx \u5206\u91cf");
    model.result("pg8").feature("ptgr1").set("xdata", "expr");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "solid.eXX");
    model.result("pg8").feature("ptgr1").set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0cXX \u5206\u91cf");
    model.result("pg8").feature("ptgr1").set("linewidth", 2);
    model.result("pg8").feature("ptgr1").set("linemarker", "circle");
    model.result("pg8").feature("ptgr1").set("markerpos", "interp");
    model.result("pg8").feature("ptgr1").set("legend", true);
    model.result("pg8").feature("ptgr1").set("legendprefix", "\u88c2\u7f1d\u5e26");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg8").run();
    model.result("pg8").feature("ptgr2").set("data", "cpt2");
    model.result("pg8").feature("ptgr2").set("legendprefix", "\u9690\u5f0f\u68af\u5ea6");
    model.result("pg8").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg8").feature("ptgr2").set("linemarker", "plus");
    model.result("pg8").run();
    model.result("pg8").set("titletype", "none");
    model.result("pg3").run();
    model.result("pg3").set("view", "new");
    model.result("pg3").run();
    model.result("pg1").run();
    model.result("pg1").set("view", "view2");
    model.result("pg2").run();
    model.result("pg2").set("view", "view2");
    model.result("pg4").run();
    model.result("pg4").set("view", "view2");
    model.result("pg5").run();
    model.result("pg5").set("view", "view2");

    model.title("\u5207\u53e3\u6881\u7684\u5f00\u88c2");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u4e24\u4e2a\u8106\u6027\u635f\u4f24\u6a21\u578b\u6765\u8ba1\u7b97\u53d7\u4e09\u70b9\u5f2f\u66f2\u8f7d\u8377\u7684\u5207\u53e3\u6df7\u51dd\u571f\u6881\u7684\u65ad\u88c2\u80fd\u3002\n\n\u6a21\u578b\u63d0\u4f9b\u4e86\u4eff\u771f\u7ed3\u679c\u4e0e\u5b9e\u9a8c\u6570\u636e\u7684\u6bd4\u8f83\u7ed3\u679c\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("notched_beam_damage.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
