/*
 * thermal_controller_rom.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:36 by COMSOL 6.3.0.290. */
public class thermal_controller_rom {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\COMSOL_Multiphysics\\Multiphysics");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("hteq", "HeatEquation", "geom1");
    model.component("comp1").physics("hteq").prop("EquationForm").set("form", "Automatic");
    model.component("comp1").physics("hteq").field("dimensionless").field("T");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{0.3, 0.2});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("sq1", "Square");
    model.component("comp1").geom("geom1").feature("sq1").set("size", 0.04);
    model.component("comp1").geom("geom1").feature("sq1").set("pos", new double[]{0.1, 0.1});
    model.component("comp1").geom("geom1").feature("sq1").set("base", "center");
    model.component("comp1").geom("geom1").feature("sq1").set("selresult", true);
    model.component("comp1").geom("geom1").run("sq1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.05, 0);
    model.component("comp1").geom("geom1").feature("pt1").setIndex("p", 0.1, 1);
    model.component("comp1").geom("geom1").feature("pt1").set("selresult", true);
    model.component("comp1").geom("geom1").feature().duplicate("pt2", "pt1");
    model.component("comp1").geom("geom1").feature("pt2").setIndex("p", 0.2, 0);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");

    model.component("comp1").label("\u7ec4\u4ef6 1\uff1a\u7269\u7406\u573a");

    model.param().set("Tset", "293.15[K]");
    model.param().descr("Tset", "\u8bbe\u5b9a\u70b9\u6e29\u5ea6");
    model.param().set("tmax", "1[h]");
    model.param().set("outputStep", "0.1[min]");
    model.param().set("tstep", "0.5[s]");
    model.param().set("heatSrc", "7.5e6[W/(m^3)]");
    model.param().set("cpl", "(54/1e-2)[W/(m^2*K)]");
    model.param().set("ToutAvg", "(293.15[K]-5[K])");
    model.param().set("ToutAmp", "10[K]");
    model.param().set("ToutPeriod", "20[min]");

    model.common().create("grmi1", "GlobalReducedModelInputs", "");
    model.common("grmi1").setIndex("name", "Tout", 0);
    model.common("grmi1").setIndex("expression", "ToutAvg +(ToutAmp*sin(2*pi*t/ToutPeriod))", 0);
    model.common("grmi1").setIndex("name", "HeatState", 1);
    model.common("grmi1").setIndex("expression", 1, 1);

    model.component("comp1").physics("hteq").feature("hteq1")
         .setIndex("c", new String[]{"mat1.def.k_iso", "0", "0", "mat1.def.k_iso"}, 0);
    model.component("comp1").physics("hteq").feature("hteq1").setIndex("f", 0, 0);
    model.component("comp1").physics("hteq").feature("hteq1").setIndex("da", "mat1.def.rho*mat1.def.Cp", 0);
    model.component("comp1").physics("hteq").feature("init1").set("T", "293.15[K]");
    model.component("comp1").physics("hteq").create("src1", "SourceTerm", 2);
    model.component("comp1").physics("hteq").feature("src1").selection().set(2);
    model.component("comp1").physics("hteq").feature("src1").setIndex("f", "heatSrc*HeatState", 0);
    model.component("comp1").physics("hteq").feature("src1").selection().named("geom1_sq1_dom");
    model.component("comp1").physics("hteq").create("flux1", "FluxBoundary", 1);
    model.component("comp1").physics("hteq").feature("flux1").selection().set(1);
    model.component("comp1").physics("hteq").feature("flux1").setIndex("g", "cpl*(Tout-T)", 0);

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().named("geom1_pt1_pnt");
    model.component("comp1").probe("point1").label("\u6052\u6e29\u5668\u4f4d\u7f6e 1\uff1a\u5b8c\u6574\u6a21\u578b");
    model.component("comp1").probe("point1").set("probename", "ppb1");
    model.component("comp1").probe().duplicate("point2", "point1");
    model.component("comp1").probe("point2").label("\u6052\u6e29\u5668\u4f4d\u7f6e 2\uff1a\u5b8c\u6574\u6a21\u578b");
    model.component("comp1").probe("point2").set("probename", "ppb2");
    model.component("comp1").probe("point2").selection().named("geom1_pt2_pnt");

    model.nodeGroup().create("grp1", "Definitions", "comp1");
    model.nodeGroup("grp1").set("type", "probe");
    model.nodeGroup("grp1").add("probe", "point1");
    model.nodeGroup("grp1").add("probe", "point2");
    model.nodeGroup("grp1").label("\u5b8c\u6574\u6a21\u578b\u7684\u63a2\u9488");

    model.component().create("comp2", true);

    model.component("comp2").label("\u7ec4\u4ef6 2\uff1a\u63a7\u5236\u5668\u4e8b\u4ef6");

    model.component("comp2").variable().create("var1");
    model.component("comp2").variable("var1").set("Tmeasured", "if(Tset < 294[K], comp1.ppb1, comp1.ppb2)");
    model.component("comp2").variable("var1")
         .label("\u53d8\u91cf 1\uff1a\u4f7f\u7528\u5b8c\u6574\u6a21\u578b\u8ba1\u7b97\u7684\u4f4d\u7f6e 1 \u6216 2 \u7684\u6e29\u5ea6");

    model.study().create("std1");
    model.study("std1").label("\u7814\u7a76 1\uff1a6 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").create("eigv", "Eigenvalue");
    model.study("std1").feature("eigv").set("neigsactive", true);
    model.study("std1").feature("eigv").set("probesel", "none");
    model.study("std1").create("mr", "ModelReduction");
    model.study("std1").feature("mr").feature().create("time1", "Transient");
    model.study("std1").feature("mr").feature("time1").set("tunit", "min");
    model.study("std1").feature("mr").feature("time1").set("tlist", "range(0, outputStep, tmax)");
    model.study("std1").feature("mr").set("trainingStudy", "std1");
    model.study("std1").feature("mr").set("unreducedModelStudy", "std1");
    model.study("std1").feature("mr").set("unreducedModelStep", "time1");
    model.study("std1").feature("mr").setIndex("qoiname", "T1", 0);
    model.study("std1").feature("mr").setIndex("qoiexpr", "comp1.ppb1", 0);
    model.study("std1").feature("mr").setIndex("qoidescr", "1 \u5904\u7684\u6e29\u5ea6", 0);
    model.study("std1").feature("mr").setIndex("qoiname", "T2", 1);
    model.study("std1").feature("mr").setIndex("qoiexpr", "comp1.ppb2", 1);
    model.study("std1").feature("mr").setIndex("qoidescr", "2 \u5904\u7684\u6e29\u5ea6", 1);
    model.study("std1").feature("mr").setEntry("trainingExpression", "Tout", 293.15);
    model.study("std1").feature("mr").setEntry("trainingExpression", "HeatState", 1);
    model.study("std1").feature("mr").set("romReconstruct", false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.component("comp2").physics().create("ev", "Events");

    model.study("std1").feature("eigv").setSolveFor("/physics/ev", true);
    model.study("std1").feature("mr").feature("time1").setSolveFor("/physics/ev", true);

    model.component("comp2").physics("ev").create("is1", "IndicatorStates", -1);
    model.component("comp2").physics("ev").feature("is1").setIndex("indDim", "lowtemp", 0, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("g", 0, 0, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("dimInit", 0, 0, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("g", "(Tset-2.5)-Tmeasured", 0, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("dimInit", -1, 0, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("indDim", "hightemp", 1, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("g", 0, 1, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("dimInit", 0, 1, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("dimDescr", "", 1, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("g", "Tmeasured-(Tset+2.5)", 1, 0);
    model.component("comp2").physics("ev").feature("is1").setIndex("dimInit", 1, 1, 0);
    model.component("comp2").physics("ev").create("ds1", "DiscreteStates", -1);
    model.component("comp2").physics("ev").feature("ds1").setIndex("dim", "relay", 0, 0);
    model.component("comp2").physics("ev").feature("ds1").setIndex("dimInit", 0, 0, 0);
    model.component("comp2").physics("ev").feature("ds1").setIndex("dimInit", "if(Tset > 293.15,1,0)", 0, 0);
    model.component("comp2").physics("ev").create("impl1", "ImplicitEvent", -1);
    model.component("comp2").physics("ev").feature("impl1").set("condition", "lowtemp > 0");
    model.component("comp2").physics("ev").feature("impl1").set("useConsistentInit", false);
    model.component("comp2").physics("ev").feature("impl1").setIndex("reInitName", "relay", 0, 0);
    model.component("comp2").physics("ev").feature("impl1").setIndex("reInitValue", 0, 0, 0);
    model.component("comp2").physics("ev").feature("impl1").setIndex("reInitValue", 1, 0, 0);
    model.component("comp2").physics("ev").create("impl2", "ImplicitEvent", -1);
    model.component("comp2").physics("ev").feature("impl2").set("condition", "hightemp > 0");
    model.component("comp2").physics("ev").feature("impl2").set("useConsistentInit", false);
    model.component("comp2").physics("ev").feature("impl2").setIndex("reInitName", "relay", 0, 0);
    model.component("comp2").physics("ev").feature("impl2").setIndex("reInitValue", 0, 0, 0);

    model.common("grmi1").setIndex("expression", "comp2.relay", 1);

    model.study("std1").feature("mr").feature("time1").setSolveFor("/physics/ev", false);
    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/hteq", true);
    model.study("std2").feature("time").setSolveFor("/physics/ev", true);
    model.study("std2").feature("time").set("tunit", "min");
    model.study("std2").feature("time").set("tlist", "range(0, outputStep, tmax)");
    model.study("std2").label("\u7814\u7a76 2\uff1a\u63a7\u5236\u5668\u5168\u5f00");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Tset", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("pname", "Tset", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "K", 0);
    model.study("std2").feature("param").setIndex("plistarr", "293.15[K]  300[K]", 0);
    model.study("std2").feature("param").set("probesel", "none");
    model.study("std2").showAutoSequences("all");

    model.sol("sol3").feature("t1").set("tstepsbdf", "manual");
    model.sol("sol3").feature("t1").set("timestepbdf", "tstep");

    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").setSolveFor("/reduced/rom1", false);
    model.study("std2").feature("time").set("disabledreduced", new String[]{"rom1"});
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std2");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");

    model.component("comp1").probe("point1").genResult("none");
    model.component("comp1").probe("point2").genResult("none");

    model.batch("p1").run("compute");

    model.component("comp1").probe().create("var1", "GlobalVariable");
    model.component("comp1").probe("var1").set("expr", "rom1.T1");
    model.component("comp1").probe("var1").label("\u6052\u6e29\u5668\u4f4d\u7f6e 1\uff1a\u964d\u9636\u6a21\u578b 1");
    model.component("comp1").probe().create("var2", "GlobalVariable");
    model.component("comp1").probe("var2").set("expr", "rom1.T2");
    model.component("comp1").probe("var2").label("\u6052\u6e29\u5668\u4f4d\u7f6e 2\uff1a\u964d\u9636\u6a21\u578b 1");

    model.nodeGroup().create("grp2", "Definitions", "comp1");
    model.nodeGroup("grp2").set("type", "probe");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("probe", "var1");
    model.nodeGroup("grp2").add("probe", "var2");
    model.nodeGroup("grp2").label("\u964d\u9636\u6a21\u578b 1 \u7684\u63a2\u9488");

    model.component("comp2").variable().create("var2");
    model.component("comp2").variable("var2").set("Tmeasured", "if(Tset < 294[K], rom1.T1, rom1.T2)");
    model.component("comp2").variable("var2")
         .label("\u53d8\u91cf 2\uff1a\u4f7f\u7528\u964d\u9636\u6a21\u578b 1 \u8ba1\u7b97\u7684\u4f4d\u7f6e 1 \u6216 2 \u7684\u6e29\u5ea6");

    model.study("std2").feature("time").set("disabledvariables", new String[]{"var2"});
    model.study().create("std3");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3")
         .label("\u7814\u7a76 3\uff1a6 \u4e2a\u6a21\u6001\u7684\u63a7\u5236\u5668\u964d\u9636\u6a21\u578b");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/hteq", false);
    model.study("std3").feature("time").set("tunit", "min");
    model.study("std3").feature("time").set("tlist", "range(0, outputStep, tmax)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledvariables", new String[]{"var1"});
    model.study("std3").feature("time").set("probesel", "manual");
    model.study("std3").feature("time").set("probes", new String[]{"var1", "var2"});
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "Tset", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("pname", "Tset", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "K", 0);
    model.study("std3").feature("param").setIndex("plistarr", "293.15[K]  300[K]", 0);
    model.study("std3").feature("param").set("probesel", "none");
    model.study("std3").showAutoSequences("all");

    model.sol("sol7").feature("t1").set("tstepsbdf", "manual");
    model.sol("sol7").feature("t1").set("timestepbdf", "tstep");

    model.study("std3").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std3");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");

    model.component("comp1").probe("var1").genResult("none");
    model.component("comp1").probe("var2").genResult("none");

    model.batch("p2").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2")
         .label("\u6e29\u5ea6: \u5b8c\u6574\u6a21\u578b\u548c\u964d\u9636\u6a21\u578b\uff0c6 \u4e2a\u6a21\u6001\uff0cTset = 20\u00b0C\uff0c\u4f4d\u7f6e 1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2")
         .set("title", "\u6e29\u5ea6: \u5b8c\u6574\u6a21\u578b\u548c 6 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 20\u00b0C\uff0c\u4f4d\u7f6e 1");
    model.result("pg2").set("data", "none");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("data", "dset5");
    model.result("pg2").feature("glob1").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").feature("glob1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg2").feature("glob1").setIndex("expr", "comp1.ppb1", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "comp1.ppb2", 1);
    model.result("pg2").feature("glob1").label("\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").create("glob2", "Global");
    model.result("pg2").feature("glob2").set("markerpos", "datapoints");
    model.result("pg2").feature("glob2").set("linewidth", "preference");
    model.result("pg2").feature("glob2")
         .label("\u6e29\u5ea6\uff1a6 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b");
    model.result("pg2").feature("glob2").set("data", "dset8");
    model.result("pg2").feature("glob2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").feature("glob2").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg2").feature("glob2").setIndex("expr", "rom1.T1", 0);
    model.result("pg2").feature("glob2").setIndex("expr", "rom1.T2", 1);
    model.result("pg2").feature("glob2").set("xdatasolnumtype", "level1");
    model.result("pg2").feature("glob2").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3")
         .label("\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 6 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 300 K\uff0c\u4f4d\u7f6e 2");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3")
         .set("title", "\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 6 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 300K\uff0c\u4f4d\u7f6e 2");

    model.study().create("std4");
    model.study("std4").label("\u7814\u7a76 4\uff1a40 \u4e2a\u6a21\u6001\u7684\u6a21\u578b\u964d\u9636");
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").create("eigv", "Eigenvalue");
    model.study("std4").feature("eigv").set("neigsactive", true);
    model.study("std4").feature("eigv").set("neigs", 40);
    model.study("std4").create("mr", "ModelReduction");
    model.study("std4").feature("mr").feature().create("time1", "Transient");
    model.study("std4").feature("mr").feature("time1").set("tunit", "min");
    model.study("std4").feature("mr").feature("time1").set("tlist", "range(0, outputStep, tmax)");
    model.study("std4").feature("mr").set("trainingStudy", "std4");
    model.study("std4").feature("mr").set("unreducedModelStudy", "std4");
    model.study("std4").feature("mr").set("unreducedModelStep", "time1");
    model.study("std4").feature("mr").setIndex("qoiname", "T1", 0);
    model.study("std4").feature("mr").setIndex("qoiexpr", "comp1.ppb1", 0);
    model.study("std4").feature("mr").setIndex("qoidescr", "Temperature at 1", 0);
    model.study("std4").feature("mr").setIndex("qoiname", "T2", 1);
    model.study("std4").feature("mr").setIndex("qoiexpr", "comp1.ppb2", 1);
    model.study("std4").feature("mr").setIndex("qoidescr", "Temperature at 2", 1);
    model.study("std4").feature("mr").setEntry("trainingExpression", "Tout", 293.15);
    model.study("std4").feature("mr").setEntry("trainingExpression", "HeatState", 1);
    model.study("std4").feature("mr").set("romReconstruct", false);
    model.study("std4").feature("eigv").set("useadvanceddisable", true);
    model.study("std4").feature("eigv").setSolveFor("/reduced/rom1", false);
    model.study("std4").feature("eigv").set("disabledreduced", new String[]{"rom1"});
    model.study("std4").feature("eigv").set("disabledvariables", new String[]{"var1", "var2"});
    model.study("std4").feature("eigv").set("probesel", "none");
    model.study("std4").feature("mr").feature("time1").set("useadvanceddisable", true);
    model.study("std4").feature("mr").feature("time1").setSolveFor("/reduced/rom1", false);
    model.study("std4").feature("mr").feature("time1").set("disabledreduced", new String[]{"rom1"});
    model.study("std4").feature("mr").feature("time1").setSolveFor("/physics/ev", false);
    model.study("std4").feature("mr").feature("time1").set("disabledvariables", new String[]{"var1", "var2"});
    model.study("std4").createAutoSequences("all");

    model.sol("sol11").runAll();

    model.reduced("rom1")
         .label("\u77ac\u6001\uff0c\u6a21\u5f0f\u964d\u9636\u6a21\u578b 1\uff1a6 \u4e2a\u6a21\u6001");
    model.reduced("rom2")
         .label("\u77ac\u6001\uff0c\u6a21\u5f0f\u964d\u9636\u6a21\u578b 2\uff1a40 \u4e2a\u6a21\u6001");

    model.component("comp1").probe().create("var3", "GlobalVariable");
    model.component("comp1").probe("var3").label("\u6052\u6e29\u5668\u4f4d\u7f6e 1\uff1a\u964d\u9636\u6a21\u578b 2");
    model.component("comp1").probe("var3").set("expr", "rom2.T1");
    model.component("comp1").probe().create("var4", "GlobalVariable");
    model.component("comp1").probe("var4").label("\u6052\u6e29\u5668\u4f4d\u7f6e 2\uff1a\u964d\u9636\u6a21\u578b 2");
    model.component("comp1").probe("var4").set("expr", "rom2.T2");

    model.nodeGroup().create("grp3", "Definitions", "comp1");
    model.nodeGroup("grp3").set("type", "probe");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("probe", "var3");
    model.nodeGroup("grp3").add("probe", "var4");
    model.nodeGroup("grp3").label("\u964d\u9636\u6a21\u578b 2 \u7684\u63a2\u9488");

    model.component("comp2").variable().create("var3");
    model.component("comp2").variable("var3")
         .label("\u53d8\u91cf 3\uff1a\u4f7f\u7528\u964d\u9636\u6a21\u578b 2 \u8ba1\u7b97\u7684\u4f4d\u7f6e 1 \u6216 2 \u7684\u6e29\u5ea6");
    model.component("comp2").variable("var3").set("Tmeasured", "if(Tset < 294[K], rom2.T1, rom2.T2)");

    model.study("std1").feature("eigv").set("useadvanceddisable", true);
    model.study("std1").feature("eigv").set("disabledvariables", new String[]{"var1", "var2", "var3"});
    model.study("std1").feature("eigv").setSolveFor("/reduced/rom1", false);
    model.study("std1").feature("eigv").setSolveFor("/reduced/rom2", false);
    model.study("std1").feature("eigv").set("disabledreduced", new String[]{"rom1", "rom2"});
    model.study("std1").feature("mr").feature("time1").set("useadvanceddisable", true);
    model.study("std1").feature("mr").feature("time1")
         .set("disabledvariables", new String[]{"var1", "var2", "var3"});
    model.study("std1").feature("mr").feature("time1").setSolveFor("/reduced/rom1", false);
    model.study("std1").feature("mr").feature("time1").setSolveFor("/reduced/rom2", false);
    model.study("std1").feature("mr").feature("time1").set("disabledreduced", new String[]{"rom1", "rom2"});
    model.study("std2").feature("time").setSolveFor("/reduced/rom2", false);
    model.study("std2").feature("time").set("disabledreduced", new String[]{"rom1", "rom2"});
    model.study("std2").feature("time").setSolveFor("/reduced/rom2", false);
    model.study("std2").feature("time").set("disabledvariables", new String[]{"var2", "var3"});
    model.study("std2").feature("time").set("probesel", "manual");
    model.study("std2").feature("time").set("probes", new String[]{"point1", "point2"});
    model.study("std3").feature("time").setSolveFor("/reduced/rom2", false);
    model.study("std3").feature("time").set("disabledreduced", new String[]{"rom2"});
    model.study("std3").feature("time").set("disabledvariables", new String[]{"var1", "var3"});
    model.study("std3").feature("time").set("probes", new String[]{"var1", "var2"});
    model.study("std4").feature("eigv").setSolveFor("/reduced/rom1", false);
    model.study("std4").feature("eigv").setSolveFor("/reduced/rom2", false);
    model.study("std4").feature("eigv").set("disabledreduced", new String[]{"rom1", "rom2"});
    model.study("std4").feature("eigv").set("disabledvariables", new String[]{"var1", "var2", "var3"});
    model.study("std4").feature("mr").feature("time1").setSolveFor("/reduced/rom1", false);
    model.study("std4").feature("mr").feature("time1").setSolveFor("/reduced/rom2", false);
    model.study("std4").feature("mr").feature("time1").set("disabledreduced", new String[]{"rom1", "rom2"});
    model.study("std4").feature("mr").feature("time1")
         .set("disabledvariables", new String[]{"var1", "var2", "var3"});
    model.study().create("std5");
    model.study("std5").create("time", "Transient");
    model.study("std5").feature("time").setSolveFor("/physics/hteq", true);
    model.study("std5").feature("time").setSolveFor("/physics/ev", true);
    model.study("std5")
         .label("\u7814\u7a76 5\uff1a40 \u4e2a\u6a21\u6001\u7684\u63a7\u5236\u5668\u964d\u9636\u6a21\u578b");
    model.study("std5").setGenPlots(false);
    model.study("std5").setGenConv(false);
    model.study("std5").feature("time").set("tunit", "min");
    model.study("std5").feature("time").set("tlist", "range(0, outputStep, tmax)");
    model.study("std5").feature("time").set("useadvanceddisable", true);
    model.study("std5").feature("time").setSolveFor("/reduced/rom1", false);
    model.study("std5").feature("time").set("disabledreduced", new String[]{"rom1"});
    model.study("std5").feature("time").setSolveFor("/physics/hteq", false);
    model.study("std5").feature("time").set("disabledvariables", new String[]{"var1", "var2"});
    model.study("std5").feature("time").set("probesel", "manual");
    model.study("std5").feature("time").set("probes", new String[]{"var3", "var4"});
    model.study("std5").create("param", "Parametric");
    model.study("std5").feature("param").setIndex("pname", "Tset", 0);
    model.study("std5").feature("param").setIndex("plistarr", "", 0);
    model.study("std5").feature("param").setIndex("punit", "K", 0);
    model.study("std5").feature("param").setIndex("pname", "Tset", 0);
    model.study("std5").feature("param").setIndex("plistarr", "", 0);
    model.study("std5").feature("param").setIndex("punit", "K", 0);
    model.study("std5").feature("param").setIndex("plistarr", "293.15[K]  300[K]", 0);
    model.study("std5").feature("param").set("probesel", "none");
    model.study("std5").showAutoSequences("all");

    model.sol("sol13").feature("t1").set("tstepsbdf", "manual");
    model.sol("sol13").feature("t1").set("timestepbdf", "tstep");

    model.study("std5").createAutoSequences("all");

    model.sol().create("sol14");
    model.sol("sol14").study("std5");
    model.sol("sol14").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol14");

    model.component("comp1").probe("var3").genResult("none");
    model.component("comp1").probe("var4").genResult("none");

    model.batch("p3").run("compute");

    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4")
         .label("\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 20\u00b0C\uff0c\u4f4d\u7f6e 1");
    model.result("pg4")
         .set("title", "\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 20\u00b0C\uff0c\u4f4d\u7f6e 1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2")
         .label("\u6e29\u5ea6\uff1a40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b");
    model.result("pg4").feature("glob2").set("data", "dset13");
    model.result("pg4").feature("glob2").setIndex("expr", "rom2.T1", 0);
    model.result("pg4").feature("glob2").setIndex("expr", "rom2.T2", 1);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5")
         .label("\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 300 K\uff0c\u4f4d\u7f6e 2");
    model.result("pg5")
         .set("title", "\u6e29\u5ea6\uff1a\u5b8c\u6574\u6a21\u578b\u548c 40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b\uff0cTset = 300 K\uff0c\u4f4d\u7f6e 2");
    model.result("pg5").set("legendpos", "uppermiddle");
    model.result("pg5").run();
    model.result("pg5").feature("glob2")
         .label("\u6e29\u5ea6\uff1a40 \u4e2a\u6a21\u6001\u7684\u964d\u9636\u6a21\u578b");
    model.result("pg5").feature("glob2").set("data", "dset13");
    model.result("pg5").feature("glob2").setIndex("expr", "rom2.T1", 0);
    model.result("pg5").feature("glob2").setIndex("expr", "rom2.T2", 1);
    model.result("pg5").run();

    model.study().create("std6");
    model.study("std6").label("\u7814\u7a76 6\uff1a\u6240\u6709\u7814\u7a76");
    model.study("std6").setGenPlots(false);
    model.study("std6").setGenConv(false);
    model.study("std6").create("ref", "StudyReference");
    model.study("std6").feature("ref").set("studyref", "std1");
    model.study("std6").create("ref2", "StudyReference");
    model.study("std6").feature("ref2").set("studyref", "std2");
    model.study("std6").create("ref3", "StudyReference");

    return model;
  }

  public static Model run2(Model model) {
    model.study("std6").feature("ref3").set("studyref", "std3");
    model.study("std6").create("ref4", "StudyReference");
    model.study("std6").feature("ref4").set("studyref", "std4");
    model.study("std6").create("ref5", "StudyReference");
    model.study("std6").feature("ref5").set("studyref", "std5");

    model.result("pg5").run();

    model.title("\u70ed\u63a7\u5236\u5668\uff0c\u964d\u9636\u6a21\u578b");

    model
         .description("\u5927\u578b\u6709\u9650\u5143\u4eff\u771f\u7684\u6210\u672c\u5f80\u5f80\u8f83\u9ad8\uff0c\u5982\u679c\u9700\u8981\u8fdb\u884c\u91cd\u590d\u4eff\u771f\uff0c\u4f7f\u7528\u964d\u9636\u6a21\u578b (ROM) \u4f1a\u6709\u6240\u5e2e\u52a9\u3002ROM \u901a\u5e38\u53ea\u5728\u63a5\u8fd1\u5176\u8bbe\u8ba1\u6761\u4ef6\u7684\u60c5\u51b5\u4e0b\u6709\u6548\uff0c\u8fd9\u7c7b\u6a21\u578b\u7684\u7cbe\u5ea6\u8f83\u4f4e\uff0c\u4f46\u53ef\u4ee5\u5927\u5e45\u7f29\u77ed\u4eff\u771f\u65f6\u95f4\u3002\u6a21\u578b\u964d\u9636\u7684\u76ee\u7684\u662f\u5728\u7ed9\u5b9a\u7684\u53c2\u6570\u8303\u56f4\u5185\uff0c\u4ee5\u6700\u5c0f\u7684\u603b\u8ba1\u7b97\u6210\u672c\uff08\u5305\u62ec\u521b\u5efa\u964d\u9636\u6a21\u578b\u7684\u6210\u672c\uff09\uff0c\u4e3a\u672a\u964d\u9636\u6a21\u578b\u7684\u8f93\u5165-\u8f93\u51fa\u52a8\u6001\u63d0\u4f9b\u8db3\u591f\u7cbe\u786e\u7684\u8868\u793a\u3002\u672c\u4f8b\u7814\u7a76\u7684\u52a8\u529b\u7cfb\u7edf\u5305\u542b\u4e00\u4e2a\u4e0e\u5916\u90e8\u8fdb\u884c\u70ed\u4ea4\u6362\u7684\u91d1\u5c5e\u5757\uff0c\u5176\u5185\u90e8\u6709\u4e00\u4e2a\u52a0\u70ed\u5668\u548c\u4e00\u4e2a\u6052\u6e29\u5f00\u5173\u3002\u8be5\u7cfb\u7edf\u7684\u5de5\u4f5c\u65b9\u5f0f\u975e\u5e38\u7b80\u5355\uff1a\u5f53\u52a0\u70ed\u5668\u7684\u6e29\u5ea6\u8fc7\u4f4e\u6216\u8fc7\u9ad8\u65f6\uff0c\u6052\u6e29\u5668\u4f1a\u6253\u5f00\u6216\u5173\u95ed\u52a0\u70ed\u5668\u3002\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u6a21\u578b\u964d\u9636\u201d\u7814\u7a76\u6765\u521b\u5efa\u964d\u9636\u6a21\u578b\uff0c\u4ee5\u53ca\u5982\u4f55\u4f7f\u7528\u5f97\u5230\u7684\u964d\u9636\u6a21\u578b\u6765\u7814\u7a76\u4e0d\u540c\u7684\u70ed\u63a7\u5236\u7b56\u7565\u3002\u4e3a\u4e86\u8bf4\u660e\u964d\u9636\u6a21\u578b\u7684\u51c6\u786e\u6027\uff0c\u672c\u4f8b\u8fd8\u5305\u542b\u4e0e\u6709\u9650\u5143\u6a21\u578b\u8f93\u51fa\u7684\u6bd4\u8f83\u7ed3\u679c\u3002");

    model.label("thermal_controller_rom.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
