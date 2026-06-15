/*
 * ac_corrosion.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:44 by COMSOL 6.3.0.290. */
public class ac_corrosion {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Corrosion_Module\\General_Corrosion");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cO2");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cO2"});
    model.component("comp1").physics().create("ge", "GlobalEquations", "geom1");
    model.component("comp1").physics("ge").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std1").feature("stat").setSolveFor("/physics/ge", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("Ecorr", "-0.67[V]", "\u91d1\u5c5e\u6eb6\u89e3\u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("icorr", "5e-5[A/cm^2]", "\u91d1\u5c5e\u6eb6\u89e3\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("ba", "0.089[V]", "\u91d1\u5c5e\u6eb6\u89e3\u7684\u9633\u6781 Tafel \u659c\u7387");
    model.param().set("bc", "-0.352[V]", "\u6c27\u8fd8\u539f\u7684\u9634\u6781 Tafel \u659c\u7387");
    model.param().set("i_O2_lim", "7e-5[A/cm^2]", "\u6c27\u8fd8\u539f\u7684\u6781\u9650\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("i0_H2", "1e-7[A/cm^2]", "\u6790\u6c22\u53cd\u5e94\u7684\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("E_H2", "-0.244[V]", "\u6790\u6c22\u53cd\u5e94\u7684\u5e73\u8861\u7535\u4f4d");
    model.param().set("b_H2", "-0.205[V]", "\u6790\u6c22\u53cd\u5e94\u7684\u9634\u6781 Tafel \u659c\u7387");
    model.param().set("C", "6.38[F/m^2]", "\u53cc\u7535\u5c42\u7535\u5bb9");
    model.param().set("Rs", "8.7[ohm*cm^2]", "\u6eb6\u6db2\u7535\u963b");
    model.param().set("E_DC", "-0.67[V]", "\u5916\u52a0\u76f4\u6d41\u7535\u52bf");
    model.param().set("E_RMS", "0.6[V]", "\u4ea4\u6d41\u7535\u52bf\u7684\u5927\u5c0f");
    model.param().set("f", "60[Hz]", "\u4ea4\u6d41\u7535\u52bf\u7684\u9891\u7387");
    model.param().set("omega", "2*pi*f", "\u89d2\u9891\u7387");
    model.param()
         .set("E0", "E_DC-(icorr*exp(2.3*(E_DC-Ecorr)/ba)-icorr*exp(-2.3*(E_DC-Ecorr)/bc)/(1-icorr/i_O2_lim+icorr/i_O2_lim*exp(-2.3*(E_DC-Ecorr)/bc))-i0_H2*exp(-2.3*(E_DC-E_H2)/b_H2))*Rs", "\u521d\u59cb\u7535\u52bf");
    model.param().set("tf", "3/f", "\u6700\u7ec8\u65f6\u95f4");
    model.param().set("D_O2", "2e-5[cm^2/s]", "\u6eb6\u89e3\u6c27\u7684\u6269\u6563\u7cfb\u6570");
    model.param().set("c_O2_sat", "2e-7[mol/ml]", "\u6eb6\u89e3\u6c27\u7684\u9971\u548c\u6d53\u5ea6");
    model.param().set("L", "4*F_const*c_O2_sat*D_O2/i_O2_lim", "\u6269\u6563\u5c42\u539a\u5ea6");
    model.param().set("t", "0", "\u521d\u59cb\u65f6\u95f4");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

//    To import content from file, use:
//    model.component("comp1").variable("var1").loadFile("FILENAME");
    model.component("comp1").variable("var1")
         .set("ia", "intop1(tcd.iloc_er1)", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.component("comp1").variable("var1")
         .set("E_app", "E_DC+E_RMS*sin(omega*t)", "\u5916\u52a0\u76f4\u6d41\u548c\u4ea4\u6d41\u7535\u52bf");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 0);
    model.component("comp1").cpl("intop1").selection().set(1);

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cO2", new String[]{"D_O2", "0", "0", "0", "D_O2", "0", "0", "0", "D_O2"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_O2_sat", 0);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_O2_sat", 0);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("phisext0", "E");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Eeq", "Ecorr");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .set("ElectrodeKinetics", "AnodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0", "icorr");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("Aa", "ba");
    model.component("comp1").physics("tcd").feature("es1").feature("er1")
         .label("\u7535\u6781\u53cd\u5e94\uff1a\u9633\u6781\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es1").create("er2", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .label("\u7535\u6781\u53cd\u5e94\uff1a\u6c27\u8fd8\u539f\u9634\u6781\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("nm", 4);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").setIndex("Vi0", -1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Eeq", "Ecorr");
    model.component("comp1").physics("tcd").feature("es1").feature("er2")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("i0", "icorr*cO2/ c_O2_sat");
    model.component("comp1").physics("tcd").feature("es1").feature("er2").set("Ac", "bc");
    model.component("comp1").physics("tcd").feature("es1").create("er3", "ElectrodeReaction", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er3")
         .label("\u7535\u6781\u53cd\u5e94\uff1a\u6790\u6c22\u9634\u6781\u53cd\u5e94");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Eeq_mat", "userdef");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Eeq", "E_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er3")
         .set("ElectrodeKinetics", "CathodicTafelEquation");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("i0", "i0_H2");
    model.component("comp1").physics("tcd").feature("es1").feature("er3").set("Ac", "b_H2");
    model.component("comp1").physics("tcd").feature("es1").create("dlc1", "DoubleLayerCapacitance", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("dlc1").set("Cdl", "C");
    model.component("comp1").physics("ge").feature("ge1").setIndex("name", "E", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("equation", "E_app-tcd.itotavg_es1*Rs-E", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("initialValueU", "E0", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").setIndex("description", "\u7535\u4f4d", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomDependentVariableUnit", "V", 0, 0);
    model.component("comp1").physics("ge").feature("ge1").set("CustomSourceTermUnit", "1");
    model.component("comp1").physics("ge").feature("ge1").set("SourceTermQuantity", "none");
    model.component("comp1").physics("ge").feature("ge1").setIndex("CustomSourceTermUnit", "V", 0, 0);

    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u7814\u7a76\uff1a\u4ea4\u6d41\u5f71\u54cd");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Ecorr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "Ecorr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V", 0);
    model.study("std1").feature("param").setIndex("pname", "E_DC", 0);
    model.study("std1").feature("param").setIndex("plistarr", "-0.74[V] -0.67[V] -0.6[V] -0.4[V]", 0);
    model.study("std1").feature("param").setIndex("pname", "Ecorr", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("pname", "Ecorr", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "V", 1);
    model.study("std1").feature("param").setIndex("pname", "C", 1);
    model.study("std1").feature("param").setIndex("plistarr", "35[F/m^2] 2.61[F/m^2] 2.57[F/m^2] 3.09[F/m^2]", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();
    model.sol("sol1").copySolution("sol2");
    model.sol("sol2").label("\u89e3 1 - \u65e0\u4ea4\u6d41");

    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").set("tlist", "range(0,tf/10,tf)");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std1");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u4ea4\u6d41\u7684\u5f71\u54cd");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6 vs. \u5916\u52a0\u76f4\u6d41\u7535\u4f4d");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("data", "dset2");
    model.result("pg1").feature("glob1").setIndex("expr", "ia", 0);
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "E_DC");
    model.result("pg1").feature("glob1").set("legendmethod", "manual");
    model.result("pg1").feature("glob1").setIndex("legends", "\u65e0\u4ea4\u6d41", 0);
    model.result("pg1").run();
    model.result("pg1").create("glob2", "Global");
    model.result("pg1").feature("glob2").set("markerpos", "datapoints");
    model.result("pg1").feature("glob2").set("linewidth", "preference");
    model.result("pg1").feature("glob2").set("data", "dset4");
    model.result("pg1").feature("glob2").setIndex("looplevelinput", "last", 0);
    model.result("pg1").feature("glob2").setIndex("expr", "timeint(tf-1/f,tf,ia)/(1/f)", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "\u65f6\u5747\u9633\u6781\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg1").feature("glob2").set("xdatasolnumtype", "outer");
    model.result("pg1").feature("glob2").set("xdata", "expr");
    model.result("pg1").feature("glob2").set("xdataexpr", "E_DC");
    model.result("pg1").feature("glob2").set("legendmethod", "manual");
    model.result("pg1").feature("glob2").setIndex("legends", "AC", 0);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/tcd", true);
    model.study("std2").feature("stat").setSolveFor("/physics/ge", true);
    model.study("std2").label("\u7814\u7a76\uff1a\u9891\u7387\u5f71\u54cd");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("time", "Transient");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Ecorr", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "V", 0);
    model.study("std2").feature("param").setIndex("pname", "Ecorr", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "V", 0);
    model.study("std2").feature("param").setIndex("pname", "f", 0);
    model.study("std2").feature("param").setIndex("plistarr", "60[Hz] 0.01[Hz]", 0);
    model.study("std2").feature("time").set("tlist", "range(0,tf/10,tf)");
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol11");
    model.sol("sol11").study("std2");
    model.sol("sol11").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol11");
    model.batch("p2").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u4ea4\u6d41\u9891\u7387\u7684\u5f71\u54cd");
    model.result("pg2").set("data", "dset7");
    model.result("pg2").setIndex("looplevelinput", "manual", 1);
    model.result("pg2").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u7535\u6d41\u5bc6\u5ea6 vs. \u65f6\u95f4");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u6d41\u5bc6\u5ea6 (A/m<sup>2</sup>)");
    model.result("pg2").create("ptgr1", "PointGraph");
    model.result("pg2").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg2").feature("ptgr1").set("linewidth", "preference");
    model.result("pg2").feature("ptgr1").label("\u70b9\u7ed3\u679c\u56fe\uff1a\u9633\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr1").selection().set(1);
    model.result("pg2").feature("ptgr1").set("expr", "tcd.iloc_er1");
    model.result("pg2").feature("ptgr1").set("descr", "\u5c40\u90e8\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr1").set("legend", true);
    model.result("pg2").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg2").feature("ptgr1").setIndex("legends", "\u9633\u6781\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg2").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr2").label("\u70b9\u7ed3\u679c\u56fe\uff1a\u9634\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr2").set("expr", "tcd.iloc_er2+tcd.iloc_er3");
    model.result("pg2").feature("ptgr2").setIndex("legends", "\u9634\u6781\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg2").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr3").set("expr", "tcd.idl");
    model.result("pg2").feature("ptgr3")
         .label("\u70b9\u7ed3\u679c\u56fe\uff1a\u53cc\u7535\u5c42\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr3").setIndex("legends", "\u53cc\u7535\u5c42\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg2").feature().duplicate("ptgr4", "ptgr3");
    model.result("pg2").run();
    model.result("pg2").feature("ptgr4").set("expr", "tcd.itot");
    model.result("pg2").feature("ptgr4").set("descr", "\u754c\u9762\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr4").label("\u70b9\u7ed3\u679c\u56fe\uff1a\u603b\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg2").feature("ptgr4").setIndex("legends", "\u603b\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", new int[]{2}, 1);
    model.result("pg2").run();

    model.title("\u4ea4\u6d41\u7535\u5f15\u8d77\u7684\u8150\u8680");

    model
         .description("\u672c\u6a21\u578b\u9996\u5148\u4f7f\u7528\u7a33\u6001\u5206\u6790\u8bc4\u4f30\u76f4\u6d41 (DC) \u5916\u52a0\u7535\u52bf\u5bf9\u8150\u8680\u7684\u5f71\u54cd\uff0c\u7136\u540e\u4f7f\u7528\u77ac\u6001\u5206\u6790\u8bc4\u4f30\u4ea4\u6d41\u5bf9\u8150\u8680\u7684\u5f71\u54cd\u3002\u968f\u540e\u5bf9\u6a21\u578b\u8fdb\u884c\u6269\u5c55\uff0c\u4ee5\u7814\u7a76\u9891\u7387\u5bf9\u4ea4\u6d41\u8150\u8680\u901f\u7387\u7684\u5f71\u54cd\uff0c\u5c24\u5176\u662f\u5728\u8f83\u9ad8\u9891\u7387\u4e0b\uff0c\u63d0\u51fa\u4e86\u53cc\u7535\u5c42\u7535\u5bb9\u7684\u8d21\u732e\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();

    model.label("ac_corrosion.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
