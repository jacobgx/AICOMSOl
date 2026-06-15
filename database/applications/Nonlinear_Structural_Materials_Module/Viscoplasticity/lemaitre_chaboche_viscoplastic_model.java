/*
 * lemaitre_chaboche_viscoplastic_model.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:38 by COMSOL 6.3.0.290. */
public class lemaitre_chaboche_viscoplastic_model {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Viscoplasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics().create("ev", "Events", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ev", true);

    model.param().set("e0t", "1e-3[1/s]");
    model.param().descr("e0t", "\u6307\u5b9a\u7684\u5e94\u53d8\u7387");
    model.param().set("L0", "(35/2+14+10)[mm]");
    model.param().descr("L0", "\u4e00\u534a\u957f\u5ea6");
    model.param().set("gamma0", "1200");
    model.param().descr("gamma0", "\u521d\u59cb\u8fd0\u52a8\u786c\u5316\u53c2\u6570");
    model.param().set("gammas", "1540");
    model.param().descr("gammas", "\u9971\u548c\u8fd0\u52a8\u786c\u5316\u53c2\u6570");
    model.param().set("beta", "1000");
    model.param().descr("beta", "\u8fd0\u52a8\u786c\u5316\u53c2\u6570\u6307\u6570");

    model.func().create("step1", "Step");
    model.func("step1").set("location", "5[ms]");
    model.func("step1").set("smooth", 0.01);

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"7/2", "35/2"});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new int[]{5, 10});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, 21.5});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new int[]{8, 10});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 31.5});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("ca1", "CircularArc");
    model.component("comp1").geom("geom1").feature("ca1").set("specify", "endsangle1");
    model.component("comp1").geom("geom1").feature("ca1").set("point1", new double[]{3.5, 17.5});
    model.component("comp1").geom("geom1").feature("ca1").set("point2", new double[]{5, 21.5});
    model.component("comp1").geom("geom1").feature("ca1").set("angle1", 180);
    model.component("comp1").geom("geom1").feature("ca1").set("clockwise", true);
    model.component("comp1").geom("geom1").run("ca1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "5 0 0 3.5");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "21.5 21.5 17.5 17.5");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("ca1", "pol1");
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").cpl().create("intop1", "Integration");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(6);
    model.component("comp1").cpl("intop1").set("axisym", false);

    model.component("comp1").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dim", "LoadingType", 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("ds1").setIndex("dimInit", 1, 0, 0);
    model.component("comp1").physics("ev").create("is1", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is1")
         .label("\u6307\u793a\u5668\u72b6\u6001\uff1a\u5e94\u53d8\u3001\u5bf9\u79f0");
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "Tension", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", "intop1(solid.el33)-0.004", 0, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("indDim", "Compression", 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is1").setIndex("g", "intop1(solid.el33)+0.004", 1, 0);
    model.component("comp1").physics("ev").create("is2", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is2")
         .label("\u6307\u793a\u5668\u72b6\u6001\uff1a\u5e94\u529b\u3001\u5bf9\u79f0");
    model.component("comp1").physics("ev").feature("is2").setIndex("indDim", "Tension", 0, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("g", "intop1(solid.sl33)-500[MPa]", 0, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("indDim", "Compression", 1, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is2").setIndex("g", "intop1(solid.sl33)+500[MPa]", 1, 0);
    model.component("comp1").physics("ev").create("is3", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is3")
         .label("\u6307\u793a\u5668\u72b6\u6001\uff1a\u5e94\u53d8\u3001\u975e\u5bf9\u79f0");
    model.component("comp1").physics("ev").feature("is3").setIndex("indDim", "Tension", 0, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("g", "intop1(solid.el33)-0.006", 0, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("indDim", "Compression", 1, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is3").setIndex("g", "intop1(solid.el33)+0.002", 1, 0);
    model.component("comp1").physics("ev").create("is4", "IndicatorStates", -1);
    model.component("comp1").physics("ev").feature("is4")
         .label("\u6307\u793a\u5668\u72b6\u6001\uff1a\u5e94\u529b\u3001\u975e\u5bf9\u79f0");
    model.component("comp1").physics("ev").feature("is4").setIndex("indDim", "Tension", 0, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("g", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("dimInit", 0, 0, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("g", "intop1(solid.sl33)-500[MPa]", 0, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("indDim", "Compression", 1, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("g", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("dimInit", 0, 1, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("dimDescr", "", 1, 0);
    model.component("comp1").physics("ev").feature("is4").setIndex("g", "intop1(solid.sl33)+100[MPa]", 1, 0);
    model.component("comp1").physics("ev").create("impl1", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl1").set("condition", "Tension>0");
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitName", "LoadingType", 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("impl1").setIndex("reInitValue", -1, 0, 0);
    model.component("comp1").physics("ev").create("impl2", "ImplicitEvent", -1);
    model.component("comp1").physics("ev").feature("impl2").set("condition", "Compression<0");
    model.component("comp1").physics("ev").feature("impl2").setIndex("reInitName", "LoadingType", 0, 0);
    model.component("comp1").physics("ev").feature("impl2").setIndex("reInitValue", 0, 0, 0);
    model.component("comp1").physics("ev").feature("impl2").setIndex("reInitValue", 1, 0, 0);
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);
    model.component("comp1").physics("solid").create("vel1", "Velocity", 1);
    model.component("comp1").physics("solid").feature("vel1").selection().set(9);
    model.component("comp1").physics("solid").feature("vel1").setIndex("Direction", true, 2);
    model.component("comp1").physics("solid").feature("vel1").setIndex("v", "e0t*L0*step1(t)*LoadingType", 2);
    model.component("comp1").physics("solid").feature("lemm1").create("vpl1", "Viscoplasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1")
         .set("ViscoplasticityModel", "Chaboche");
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1")
         .set("IsotropicHardeningModel", "Voce");
    model.component("comp1").physics("solid").feature("lemm1").feature("vpl1")
         .set("KinematicHardeningModel", "ArmstrongFrederick");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7500"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche_viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .set("A_cha", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .set("sigRef_cha", new String[]{"490[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .set("n_cha", new String[]{"9"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"60[MPa]"});
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce[Material_model]");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", new String[]{"-35[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", new String[]{"200"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick")
         .set("Ck", new String[]{"362.5[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick")
         .addInput("effectiveviscoplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick")
         .set("gammak", new String[]{"gammas+(gamma0-gammas)*exp(-beta*evpe)"});

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(10);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 16);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis5", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis5").selection().set(5);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis6", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").selection().set(7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis6").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "0 40");
    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"ev/is2", "ev/is3", "ev/is4"});
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("eventtol", 0.001);
    model.sol("sol1").feature("t1").set("tout", "tsteps");
    model.sol("sol1").feature("t1").set("storeudot", false);

    model.study("std1").label("\u7814\u7a76 1\uff0c\u6307\u5b9a\u7684\u5bf9\u79f0\u5e94\u53d8");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"strain", "\u5e94\u53d8\u5f20\u91cf", "1", "1"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "%", 0, 3);
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 1);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 1, 3);
    model.result().configuration("prfu1").set("applytosamedims", true);
    model.result().configuration("prfu1").apply();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").label("\u6307\u5b9a\u7684\u5bf9\u79f0\u5e94\u53d8");
    model.nodeGroup("grp1").set("type", "plotgroup");

    model.result().create("pg1", "PlotGroup1D");

    model.nodeGroup("grp1").add("plotgroup", "pg1");

    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b vs. \u5e94\u53d8 1");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u8f74\u5411\u5e94\u53d8 (%)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u8f74\u5411\u5e94\u529b (MPa)");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "\u8f74\u5411\u5e94\u529b vs. \u8f74\u5411\u5e94\u53d8");
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").selection().set(6);
    model.result("pg1").feature("ptgr1").set("expr", "solid.slGp33");
    model.result("pg1").feature("ptgr1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c33 \u5206\u91cf");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "solid.el33");
    model.result("pg1").feature("ptgr1")
         .set("xdatadescr", "\u5e94\u53d8\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c33 \u5206\u91cf");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0.37);
    model.result("pg1").set("xmax", 0.405);
    model.result("pg1").set("ymin", 475);
    model.result("pg1").set("ymax", 505);
    model.result("pg1").run();
    model.result("pg1").set("axislimits", false);
    model.result().create("pg2", "PlotGroup1D");

    model.nodeGroup("grp1").add("plotgroup", "pg2");

    model.result("pg2").run();
    model.result("pg2").label("\u5e94\u529b vs. \u65f6\u95f4 1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5e94\u529b vs. \u65f6\u95f4");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").selection().set(6);
    model.result("pg2").feature("ptgr1").set("expr", "solid.slGp33");
    model.result("pg2").feature("ptgr1")
         .set("descr", "\u5e94\u529b\u5f20\u91cf\uff0c\u5c40\u90e8\u5750\u6807\u7cfb\uff0c33 \u5206\u91cf");
    model.result("pg2").feature("ptgr1").set("titletype", "none");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u8f74\u5411\u5e94\u529b", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").set("expr", "max(0,solid.lemm1.vpl1.Fyield)");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u9ecf\u6027\u5e94\u529b", 0);
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("expr", "solid.lemm1.vpl1.Sl_back33");
    model.result("pg2").feature("ptgr3").setIndex("legends", "\u80cc\u5e94\u529b", 0);
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "solid.lemm1.vpl1.sY");
    model.result("pg2").feature("ptgr4").setIndex("legends", "\u5c48\u670d\u5f3a\u5ea6", 0);
    model.result("pg2").run();
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5e94\u529b (MPa)");
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 12);
    model.result("pg2").run();
    model.result("pg2").set("axislimits", false);
    model.result().dataset().create("rev1", "Revolve2D");
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result().create("pg3", "PlotGroup3D");

    model.nodeGroup("grp1").add("plotgroup", "pg3");

    model.result("pg3").run();
    model.result("pg3").label("\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8 1");
    model.result("pg3").set("data", "mir1");
    model.result("pg3").set("edges", false);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", "solid.evpeGp");
    model.result("pg3").feature("surf1").set("descr", "\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8");
    model.result("pg3").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("surf2", "Surface");
    model.result("pg3").feature("surf2").set("expr", "0");
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("inheritplot", "surf1");
    model.result("pg3").feature("surf2").create("def1", "Deform");
    model.result("pg3").feature("surf2").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf2").create("sel1", "Selection");
    model.result("pg3").feature("surf2").feature("sel1").selection().set(4);
    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").feature("time").set("tlist", "0 40");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"ev/is1", "ev/is3", "ev/is4"});
    model.study("std2").label("\u7814\u7a76 2\uff0c\u6307\u5b9a\u7684\u5bf9\u79f0\u5e94\u529b");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("eventtol", 0.001);
    model.sol("sol2").feature("t1").set("tout", "tsteps");
    model.sol("sol2").feature("t1").set("storeudot", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("rev2", "Revolve2D");
    model.result().dataset("rev2").set("data", "dset2");
    model.result().dataset().create("mir2", "Mirror3D");
    model.result().dataset("mir2").set("data", "rev2");
    model.result().dataset("mir2").set("quickplane", "xy");

    model.nodeGroup().duplicate("grp2", "grp1");
    model.nodeGroup("grp2").label("\u6307\u5b9a\u7684\u5bf9\u79f0\u5e94\u529b");

    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b vs. \u5e94\u53d8 2");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").run();
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0.36);
    model.result("pg4").set("xmax", 0.45);
    model.result("pg4").set("ymin", 470);
    model.result("pg4").set("ymax", 505);
    model.result("pg4").run();
    model.result("pg4").set("axislimits", false);
    model.result("pg5").run();
    model.result("pg5").label("\u5e94\u529b vs. \u65f6\u95f4 2");
    model.result("pg5").set("data", "dset2");
    model.result("pg6").run();
    model.result("pg6").label("\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8 2");
    model.result("pg6").set("data", "mir2");
    model.result("pg6").set("view", "view3");
    model.result("pg6").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/solid", true);
    model.study("std3").feature("time").setSolveFor("/physics/ev", true);
    model.study("std3").feature("time").set("tlist", "0 40");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledphysics", new String[]{"ev/is1", "ev/is2", "ev/is4"});
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("eventtol", 0.001);
    model.sol("sol3").feature("t1").set("tout", "tsteps");
    model.sol("sol3").feature("t1").set("storeudot", false);

    model.study("std3").label("\u7814\u7a76 3\uff0c\u6307\u5b9a\u7684\u975e\u5bf9\u79f0\u5e94\u53d8");
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("rev3", "Revolve2D");
    model.result().dataset("rev3").set("data", "dset3");
    model.result().dataset().create("mir3", "Mirror3D");
    model.result().dataset("mir3").set("data", "rev3");
    model.result().dataset("mir3").set("quickplane", "xy");

    model.nodeGroup().duplicate("grp3", "grp1");
    model.nodeGroup("grp3").label("\u6307\u5b9a\u7684\u975e\u5bf9\u79f0\u5e94\u53d8");

    model.result("pg7").run();
    model.result("pg7").label("\u5e94\u529b vs. \u5e94\u53d8 3");
    model.result("pg7").set("data", "dset3");
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg8").label("\u5e94\u529b vs. \u65f6\u95f4 3");
    model.result("pg8").set("data", "dset3");
    model.result("pg9").run();
    model.result("pg9").label("\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8 3");
    model.result("pg9").set("data", "mir3");
    model.result("pg9").set("view", "view3");
    model.result("pg9").run();

    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/solid", true);
    model.study("std4").feature("time").setSolveFor("/physics/ev", true);
    model.study("std4").feature("time").set("tlist", "0 30");
    model.study("std4").feature("time").set("useadvanceddisable", true);
    model.study("std4").feature("time").set("disabledphysics", new String[]{"ev/is1", "ev/is2", "ev/is3"});
    model.study("std4").showAutoSequences("all");

    model.sol("sol4").feature("t1").set("eventtol", 0.001);
    model.sol("sol4").feature("t1").set("tout", "tsteps");
    model.sol("sol4").feature("t1").set("storeudot", false);

    model.study("std4").label("\u7814\u7a76 4\uff0c\u6307\u5b9a\u7684\u975e\u5bf9\u79f0\u5e94\u529b");
    model.study("std4").setGenPlots(false);
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("rev4", "Revolve2D");
    model.result().dataset("rev4").set("data", "dset4");
    model.result().dataset().create("mir4", "Mirror3D");
    model.result().dataset("mir4").set("data", "rev4");
    model.result().dataset("mir4").set("quickplane", "xy");

    model.nodeGroup().duplicate("grp4", "grp1");
    model.nodeGroup("grp4").label("\u6307\u5b9a\u7684\u975e\u5bf9\u79f0\u5e94\u529b");

    model.result("pg10").run();
    model.result("pg10").label("\u5e94\u529b vs. \u5e94\u53d8 4");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").run();
    model.result("pg11").run();
    model.result("pg11").label("\u5e94\u529b vs. \u65f6\u95f4 4");
    model.result("pg11").set("data", "dset4");
    model.result("pg12").run();
    model.result("pg12").label("\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8 4");
    model.result("pg12").set("data", "mir4");
    model.result("pg12").set("view", "view3");
    model.result("pg12").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").label("\u7b49\u6548\u9ecf\u5851\u6027\u5e94\u53d8");
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("maxframes", 50);
    model.result().export("anim1").run();
    model.result("pg3").run();

    model.title("Lemaitre-Chaboche \u9ecf\u5851\u6027\u6a21\u578b");

    model
         .description("\u5927\u591a\u6570\u91d1\u5c5e\u548c\u5408\u91d1\u5728\u9ad8\u6e29\u4e0b\u90fd\u4f1a\u53d1\u751f\u9ecf\u5851\u6027\u53d8\u5f62\u3002\u5728\u5faa\u73af\u8f7d\u8377\u7684\u60c5\u51b5\u4e0b\uff0c\u9700\u8981\u4f7f\u7528\u5177\u6709\u5404\u5411\u540c\u6027\u786c\u5316\u548c\u8fd0\u52a8\u786c\u5316\u7684\u672c\u6784\u5b9a\u5f8b\u6765\u63cf\u8ff0\u68d8\u8f6e\u3001\u5faa\u73af\u8f6f\u5316\u6216\u786c\u5316\uff0c\u4ee5\u53ca\u5e94\u529b\u677e\u5f1b\u7b49\u6548\u5e94\u3002\n\n\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5c06 Lemaitre-Chaboche \u9ecf\u5851\u6027\u672c\u6784\u5b9a\u5f8b\u4f5c\u4e3a\u5404\u5411\u540c\u6027\u786c\u5316\u548c\u975e\u7ebf\u6027\u8fd0\u52a8\u786c\u5316\u7684\u7ec4\u5408\u6765\u5b9e\u73b0\u3002\u8fd9\u79cd\u9ecf\u5851\u6027\u6a21\u578b\u901a\u5e38\u5e94\u7528\u4e8e\u591a\u4e2a\u9886\u57df\uff0c\u5305\u62ec\u589e\u6750\u5236\u9020\u3001\u6fc0\u5149\u710a\u63a5\u3001\u6fc0\u5149\u5207\u5272\u4ee5\u53ca\u9ad8\u6e29\u4e0b\u91d1\u5c5e\u4e0e\u5408\u91d1\u7684\u70ed\u5904\u7406\u7b49\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("lemaitre_chaboche_viscoplastic_model.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
