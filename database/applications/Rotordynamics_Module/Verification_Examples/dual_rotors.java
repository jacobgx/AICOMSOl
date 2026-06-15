/*
 * dual_rotors.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:49 by COMSOL 6.3.0.290. */
public class dual_rotors {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Rotordynamics_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotbm", "BeamRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/rotbm", true);

    model.param().label("\u53c2\u6570\uff1a\u51e0\u4f55\u5f62\u72b6");
    model.param().set("x1", "0[cm]");
    model.param().descr("x1", "\u5de5\u4f4d 1 \u7684\u4f4d\u7f6e");
    model.param().set("x2", "7.62[cm]");
    model.param().descr("x2", "\u5de5\u4f4d 2 \u7684\u4f4d\u7f6e");
    model.param().set("x3", "25.4[cm]");
    model.param().descr("x3", "\u5de5\u4f4d 3 \u7684\u4f4d\u7f6e");
    model.param().set("x4", "40.64[cm]");
    model.param().descr("x4", "\u5de5\u4f4d 4 \u7684\u4f4d\u7f6e");
    model.param().set("x5", "45.72[cm]");
    model.param().descr("x5", "\u5de5\u4f4d 5 \u7684\u4f4d\u7f6e");
    model.param().set("x6", "50.8[cm]");
    model.param().descr("x6", "\u5de5\u4f4d 6 \u7684\u4f4d\u7f6e");
    model.param().set("x7", "15.24[cm]");
    model.param().descr("x7", "\u5de5\u4f4d 7 \u7684\u4f4d\u7f6e");
    model.param().set("x8", "20.32[cm]");
    model.param().descr("x8", "\u5de5\u4f4d 8 \u7684\u4f4d\u7f6e");
    model.param().set("x9", "35.56[cm]");
    model.param().descr("x9", "\u5de5\u4f4d 9 \u7684\u4f4d\u7f6e");
    model.param().set("x10", "40.64[cm]");
    model.param().descr("x10", "\u5de5\u4f4d 10 \u7684\u4f4d\u7f6e");
    model.param().set("r1", "1.52[cm]");
    model.param().descr("r1", "\u5185\u90e8\u8f6c\u5b50\u7684\u534a\u5f84");
    model.param().set("r2i", "1.905[cm]");
    model.param().descr("r2i", "\u5916\u90e8\u8f6c\u5b50\u7684\u5185\u534a\u5f84");
    model.param().set("r2o", "2.54[cm]");
    model.param().descr("r2o", "\u5916\u90e8\u8f6c\u5b50\u7684\u5916\u534a\u5f84");
    model.param().create("par2");
    model.param("par2").label("\u53c2\u6570\uff1a\u8f74\u627f");
    model.param("par2").set("k1", "27.95e6[N/m]");
    model.param("par2").descr("k1", "\u521a\u5ea6\uff0c\u5de5\u4f4d 1 \u7684\u8f74\u627f");
    model.param("par2").set("k4", "8.7598e6[N/m]");
    model.param("par2").descr("k4", "\u521a\u5ea6\uff0c\u5de5\u4f4d 4 \u4e0e 10 \u4e4b\u95f4\u7684\u8f74\u627f");
    model.param("par2").set("k6", "17.519e6[N/m]");
    model.param("par2").descr("k6", "\u521a\u5ea6\uff0c\u5de5\u4f4d 6 \u7684\u8f74\u627f");
    model.param("par2").set("k7", "17.519e6[N/m]");
    model.param("par2").descr("k7", "\u521a\u5ea6\uff0c\u5de5\u4f4d 7 \u7684\u8f74\u627f");
    model.param().create("par3");
    model.param("par3").label("\u53c2\u6570\uff1a\u6750\u6599");
    model.param("par3").set("Es", "206.9[GPa]");
    model.param("par3").descr("Es", "\u8f6c\u5b50\u7684\u6768\u6c0f\u6a21\u91cf");
    model.param("par3").set("rhos", "8304[kg/m^3]");
    model.param("par3").descr("rhos", "\u8f6c\u5b50\u7684\u5bc6\u5ea6");
    model.param("par3").set("nus", "0.3");
    model.param("par3").descr("nus", "\u8f6c\u5b50\u7684\u6cca\u677e\u6bd4");
    model.param("par3").set("m2", "4.904[kg]");
    model.param("par3").descr("m2", "\u5de5\u4f4d 2 \u7684\u8d28\u91cf");
    model.param("par3").set("m5", "4.203[kg]");
    model.param("par3").descr("m5", "\u5de5\u4f4d 5 \u7684\u8d28\u91cf");
    model.param("par3").set("m8", "3.327[kg]");
    model.param("par3").descr("m8", "\u5de5\u4f4d 8 \u7684\u8d28\u91cf");
    model.param("par3").set("m9", "2.227[kg]");
    model.param("par3").descr("m9", "\u5de5\u4f4d 9 \u7684\u8d28\u91cf");
    model.param("par3").set("Ip2", "0.02712[kg*m^2]");
    model.param("par3").descr("Ip2", "\u5de5\u4f4d 2 \u7684\u6781\u60ef\u6027\u77e9");
    model.param("par3").set("Ip5", "0.02034[kg*m^2]");
    model.param("par3").descr("Ip5", "\u5de5\u4f4d 5 \u7684\u6781\u60ef\u6027\u77e9");
    model.param("par3").set("Ip8", "0.01469[kg*m^2]");
    model.param("par3").descr("Ip8", "\u5de5\u4f4d 8 \u7684\u6781\u60ef\u6027\u77e9");
    model.param("par3").set("Ip9", "0.00972[kg*m^2]");
    model.param("par3").descr("Ip9", "\u5de5\u4f4d 9 \u7684\u6781\u60ef\u6027\u77e9");
    model.param("par3").set("Id2", "Ip2/2");
    model.param("par3").descr("Id2", "\u5de5\u4f4d 2 \u7684\u5f84\u5411\u60ef\u6027\u77e9");
    model.param("par3").set("Id5", "Ip5/2");
    model.param("par3").descr("Id5", "\u5de5\u4f4d 5 \u7684\u5f84\u5411\u60ef\u6027\u77e9");
    model.param("par3").set("Id8", "Ip8/2");
    model.param("par3").descr("Id8", "\u5de5\u4f4d 8 \u7684\u5f84\u5411\u60ef\u6027\u77e9");
    model.param("par3").set("Id9", "Ip9/2");
    model.param("par3").descr("Id9", "\u5de5\u4f4d 9 \u7684\u5f84\u5411\u60ef\u6027\u77e9");
    model.param().create("par4");
    model.param("par4").label("\u53c2\u6570\uff1a\u89d2\u901f\u5ea6");
    model.param("par4").set("fr", "1000[rpm]");
    model.param("par4").descr("fr", "\u5185\u90e8\u8f6c\u5b50\u7684\u89d2\u901f\u5ea6");
    model.param("par4").set("fr2", "1.5*fr");
    model.param("par4").descr("fr2", "\u5916\u90e8\u8f6c\u5b50\u7684\u89d2\u901f\u5ea6");

    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x1", 0, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 0, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x2", 1, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 1, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x3", 2, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 2, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x4", 3, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 3, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x5", 4, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 4, 2);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", "x6", 5, 0);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 5, 1);
    model.component("comp1").geom("geom1").feature("pol1").setIndex("table", 0, 5, 2);
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5185\u90e8\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("pol1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "x7", 0, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 0, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "6*r2o", 0, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "x8", 1, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 1, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "6*r2o", 1, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "x9", 2, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 2, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "6*r2o", 2, 2);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "x10", 3, 0);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", 0, 3, 1);
    model.component("comp1").geom("geom1").feature("pol2").setIndex("table", "6*r2o", 3, 2);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u5916\u90e8\u8f6c\u5b50");
    model.component("comp1").geom("geom1").feature("pol2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"Es"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nus"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rhos"});

    model.component("comp1").physics("rotbm").prop("RotorProperties").set("freqr", "fr");
    model.component("comp1").physics("rotbm").feature("rcs1").set("do_circ", "2*r1");
    model.component("comp1").physics("rotbm").create("rcs2", "RotorCrossSection", 1);
    model.component("comp1").physics("rotbm").feature("rcs2").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("rotbm").feature("rcs2").set("SectionType", "PipeSection");
    model.component("comp1").physics("rotbm").feature("rcs2").set("do_pipe", "2*r2o");
    model.component("comp1").physics("rotbm").feature("rcs2").set("di_pipe", "2*r2i");
    model.component("comp1").physics("rotbm").create("rsp1", "RotorSpeed", 1);
    model.component("comp1").physics("rotbm").feature("rsp1").selection().named("geom1_csel2_edg");
    model.component("comp1").physics("rotbm").feature("rsp1").set("freqr", "fr2");
    model.component("comp1").physics("rotbm").create("disk1", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk1").selection().set(2);
    model.component("comp1").physics("rotbm").feature("disk1").set("mass", "m2");
    model.component("comp1").physics("rotbm").feature("disk1").set("Ip", "Ip2");
    model.component("comp1").physics("rotbm").feature("disk1").set("Id", "Id2");
    model.component("comp1").physics("rotbm").create("disk2", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk2").selection().set(9);
    model.component("comp1").physics("rotbm").feature("disk2").set("mass", "m5");
    model.component("comp1").physics("rotbm").feature("disk2").set("Ip", "Ip5");
    model.component("comp1").physics("rotbm").feature("disk2").set("Id", "Id5");
    model.component("comp1").physics("rotbm").create("disk3", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk3").selection().set(4);
    model.component("comp1").physics("rotbm").feature("disk3").set("mass", "m8");
    model.component("comp1").physics("rotbm").feature("disk3").set("Ip", "Ip8");
    model.component("comp1").physics("rotbm").feature("disk3").set("Id", "Id8");
    model.component("comp1").physics("rotbm").create("disk4", "Disk", 0);
    model.component("comp1").physics("rotbm").feature("disk4").selection().set(6);
    model.component("comp1").physics("rotbm").feature("disk4").set("mass", "m9");
    model.component("comp1").physics("rotbm").feature("disk4").set("Ip", "Ip9");
    model.component("comp1").physics("rotbm").feature("disk4").set("Id", "Id9");
    model.component("comp1").physics("rotbm").create("jrb1", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb1").selection().set(1);
    model.component("comp1").physics("rotbm").feature("jrb1").set("BearingModel", "kTot");
    model.component("comp1").physics("rotbm").feature("jrb1").set("k_u", new String[]{"k1", "0", "0", "k1"});
    model.component("comp1").physics("rotbm").create("msb1", "MultiSpoolBearing", 0);
    model.component("comp1").physics("rotbm").feature("msb1").selection().set(7);
    model.component("comp1").physics("rotbm").feature("msb1").selection("Destination").set(8);
    model.component("comp1").physics("rotbm").feature("msb1").set("DispCon", "Flexible");
    model.component("comp1").physics("rotbm").feature("msb1").set("ku", new String[]{"k4", "0", "0", "k4"});
    model.component("comp1").physics("rotbm").create("jrb2", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb2").selection().set(10);
    model.component("comp1").physics("rotbm").feature("jrb2").set("BearingModel", "kTot");
    model.component("comp1").physics("rotbm").feature("jrb2").set("k_u", new String[]{"k6", "0", "0", "k6"});
    model.component("comp1").physics("rotbm").create("jrb3", "JournalBearing", 0);
    model.component("comp1").physics("rotbm").feature("jrb3").selection().set(3);
    model.component("comp1").physics("rotbm").feature("jrb3").set("BearingModel", "kTot");
    model.component("comp1").physics("rotbm").feature("jrb3").set("k_u", new String[]{"k7", "0", "0", "k7"});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Es", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "Es", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "Pa", 0);
    model.study("std1").feature("param").setIndex("pname", "fr", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,1000,20000) range(20500,500,25000)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 8);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 31, 1);
    model.result("pg1").label("\u6da1\u52a8 (rotbm)");
    model.result("pg1").create("wp1", "Whirl");
    model.result("pg1").feature("wp1").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("wp1").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("wp1").set("nplanes", "1");
    model.result("pg1").feature("wp1").set("nrings", "10");
    model.result("pg1").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg1").feature("wp1").set("ringcolor", "blue");
    model.result("pg1").feature("wp1").selection().geom("geom1", 1);
    model.result("pg1").feature("wp1").selection().set(1, 2, 3, 4, 5, 6, 7, 8);
    model.result("pg1").feature("wp1").selection().inherit(false);
    model.result("pg1").feature("wp1").selection().embedded(false);
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", new String[]{"1"});
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("radiusexpr", new String[]{"rotbm.re "});
    model.result("pg1").feature("line1").set("tuberadiusscaleactive", true);
    model.result("pg1").feature("line1").set("tuberadiusscale", 1);
    model.result("pg1").feature("line1").set("tubeendcaps", false);
    model.result("pg1").feature("line1").set("coloring", "uniform");
    model.result("pg1").feature("line1").set("color", "custom");
    model.result("pg1").feature("line1")
         .set("customcolor", new double[]{0.9803921580314636, 0.7843137383460999, 0.7058823704719543});
    model.result("pg1").feature("line1").set("threshold", "manual");
    model.result("pg1").feature("line1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("line1").set("titletype", "none");
    model.result("pg1").feature("line1").label("\u8f6c\u5b50");
    model.result("pg1").feature("line1").create("def", "Deform");
    model.result("pg1").feature("line1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("line1").feature("def").set("scale", "1");
    model.result("pg1").feature("line1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj1", "PointTrajectories");
    model.result("pg1").feature("pttraj1").set("plotdata", "points");
    model.result("pg1").feature("pttraj1").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj1").selection().set(2);
    model.result("pg1").feature("pttraj1").selection().inherit(false);
    model.result("pg1").feature("pttraj1").selection().embedded(false);
    model.result("pg1").feature("pttraj1").set("linetype", "none");
    model.result("pg1").feature("pttraj1").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj1").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj1").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj1")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj1")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e20x ", "0.5*rotbm.disk1.de*rotbm.e20y ", "0.5*rotbm.disk1.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj1")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk1.de*rotbm.e30x ", "0.5*rotbm.disk1.de*rotbm.e30y ", "0.5*rotbm.disk1.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj1").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj1").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj1").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj1").set("titletype", "none");
    model.result("pg1").feature("pttraj1").label("\u5706\u76d8 1");
    model.result("pg1").feature("pttraj1").create("def", "Deform");
    model.result("pg1").feature("pttraj1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj1").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj1").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj2", "PointTrajectories");
    model.result("pg1").feature("pttraj2").set("plotdata", "points");
    model.result("pg1").feature("pttraj2").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj2").selection().set(9);
    model.result("pg1").feature("pttraj2").selection().inherit(false);
    model.result("pg1").feature("pttraj2").selection().embedded(false);
    model.result("pg1").feature("pttraj2").set("linetype", "none");
    model.result("pg1").feature("pttraj2").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj2").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj2").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj2")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj2")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e20x ", "0.5*rotbm.disk2.de*rotbm.e20y ", "0.5*rotbm.disk2.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj2")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk2.de*rotbm.e30x ", "0.5*rotbm.disk2.de*rotbm.e30y ", "0.5*rotbm.disk2.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj2").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj2").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj2").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj2").set("titletype", "none");
    model.result("pg1").feature("pttraj2").label("\u5706\u76d8 2");
    model.result("pg1").feature("pttraj2").create("def", "Deform");
    model.result("pg1").feature("pttraj2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj2").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj2").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj3", "PointTrajectories");
    model.result("pg1").feature("pttraj3").set("plotdata", "points");
    model.result("pg1").feature("pttraj3").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj3").selection().set(4);
    model.result("pg1").feature("pttraj3").selection().inherit(false);
    model.result("pg1").feature("pttraj3").selection().embedded(false);
    model.result("pg1").feature("pttraj3").set("linetype", "none");
    model.result("pg1").feature("pttraj3").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj3").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj3").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj3")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj3")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e20x ", "0.5*rotbm.disk3.de*rotbm.e20y ", "0.5*rotbm.disk3.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj3")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk3.de*rotbm.e30x ", "0.5*rotbm.disk3.de*rotbm.e30y ", "0.5*rotbm.disk3.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj3").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj3").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj3").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj3").set("titletype", "none");
    model.result("pg1").feature("pttraj3").label("\u5706\u76d8 3");
    model.result("pg1").feature("pttraj3").create("def", "Deform");
    model.result("pg1").feature("pttraj3").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj3").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj3").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj4", "PointTrajectories");
    model.result("pg1").feature("pttraj4").set("plotdata", "points");
    model.result("pg1").feature("pttraj4").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj4").selection().set(6);
    model.result("pg1").feature("pttraj4").selection().inherit(false);
    model.result("pg1").feature("pttraj4").selection().embedded(false);
    model.result("pg1").feature("pttraj4").set("linetype", "none");
    model.result("pg1").feature("pttraj4").set("expr", new String[]{"X", "Y", "Z"});
    model.result("pg1").feature("pttraj4").set("pointtype", "ellipse");
    model.result("pg1").feature("pttraj4").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj4")
         .set("custompointcolor", new double[]{0.8039215803146362, 0.5215686559677124, 0.24705882370471954});
    model.result("pg1").feature("pttraj4")
         .set("semimajorexpr", new String[]{"0.5*rotbm.disk4.de*rotbm.e20x ", "0.5*rotbm.disk4.de*rotbm.e20y ", "0.5*rotbm.disk4.de*rotbm.e20z "});
    model.result("pg1").feature("pttraj4")
         .set("semiminorexpr", new String[]{"0.5*rotbm.disk4.de*rotbm.e30x ", "0.5*rotbm.disk4.de*rotbm.e30y ", "0.5*rotbm.disk4.de*rotbm.e30z "});
    model.result("pg1").feature("pttraj4").set("ellipsecount", 1);
    model.result("pg1").feature("pttraj4").set("ellipsearrowscaleactive", true);
    model.result("pg1").feature("pttraj4").set("ellipsearrowtype", "none");
    model.result("pg1").feature("pttraj4").set("titletype", "none");
    model.result("pg1").feature("pttraj4").label("\u5706\u76d8 4");
    model.result("pg1").feature("pttraj4").create("def", "Deform");
    model.result("pg1").feature("pttraj4").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj4").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj4").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj5", "PointTrajectories");
    model.result("pg1").feature("pttraj5").set("plotdata", "points");
    model.result("pg1").feature("pttraj5").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj5").selection().set(1);
    model.result("pg1").feature("pttraj5").selection().inherit(false);
    model.result("pg1").feature("pttraj5").selection().embedded(false);
    model.result("pg1").feature("pttraj5").set("linetype", "none");
    model.result("pg1").feature("pttraj5").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj5")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb1.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb1.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj5")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb1.e3gx ", "rotbm.re*rotbm.jrb1.e3gy ", "rotbm.re*rotbm.jrb1.e3gz "});
    model.result("pg1").feature("pttraj5").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj5").set("arrowbase", "head");
    model.result("pg1").feature("pttraj5").set("arrowscale", "10");
    model.result("pg1").feature("pttraj5").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj5").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj5")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj5").set("titletype", "none");
    model.result("pg1").feature("pttraj5").label("\u8f74\u9888\u8f74\u627f 1");
    model.result("pg1").feature("pttraj5").create("def", "Deform");
    model.result("pg1").feature("pttraj5").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj5").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj5").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj6", "PointTrajectories");
    model.result("pg1").feature("pttraj6").set("plotdata", "points");
    model.result("pg1").feature("pttraj6").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj6").selection().set(10);
    model.result("pg1").feature("pttraj6").selection().inherit(false);
    model.result("pg1").feature("pttraj6").selection().embedded(false);
    model.result("pg1").feature("pttraj6").set("linetype", "none");
    model.result("pg1").feature("pttraj6").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj6")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb2.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb2.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj6")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb2.e3gx ", "rotbm.re*rotbm.jrb2.e3gy ", "rotbm.re*rotbm.jrb2.e3gz "});
    model.result("pg1").feature("pttraj6").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj6").set("arrowbase", "head");
    model.result("pg1").feature("pttraj6").set("arrowscale", "10");
    model.result("pg1").feature("pttraj6").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj6").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj6")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});
    model.result("pg1").feature("pttraj6").set("titletype", "none");
    model.result("pg1").feature("pttraj6").label("\u8f74\u9888\u8f74\u627f 2");
    model.result("pg1").feature("pttraj6").create("def", "Deform");
    model.result("pg1").feature("pttraj6").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj6").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj6").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").create("pttraj7", "PointTrajectories");
    model.result("pg1").feature("pttraj7").set("plotdata", "points");
    model.result("pg1").feature("pttraj7").selection().geom("geom1", 0);
    model.result("pg1").feature("pttraj7").selection().set(3);
    model.result("pg1").feature("pttraj7").selection().inherit(false);
    model.result("pg1").feature("pttraj7").selection().embedded(false);
    model.result("pg1").feature("pttraj7").set("linetype", "none");
    model.result("pg1").feature("pttraj7").set("pointtype", "arrow");
    model.result("pg1").feature("pttraj7")
         .set("expr", new String[]{"X-1.0*rotbm.re*rotbm.jrb3.e3gx ", "Y-1.0*rotbm.re*rotbm.jrb3.e3gy ", "Z-1.0*rotbm.re*rotbm.jrb3.e3gz "});
    model.result("pg1").feature("pttraj7")
         .set("arrowexpr", new String[]{"rotbm.re*rotbm.jrb3.e3gx ", "rotbm.re*rotbm.jrb3.e3gy ", "rotbm.re*rotbm.jrb3.e3gz "});
    model.result("pg1").feature("pttraj7").set("arrowtype", "arrowhead");
    model.result("pg1").feature("pttraj7").set("arrowbase", "head");
    model.result("pg1").feature("pttraj7").set("arrowscale", "10");
    model.result("pg1").feature("pttraj7").set("arrowscaleactive", true);
    model.result("pg1").feature("pttraj7").set("pointcolor", "custom");
    model.result("pg1").feature("pttraj7")
         .set("custompointcolor", new double[]{0.5882353186607361, 0.8627451062202454, 0.5882353186607361});

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").feature("pttraj7").set("titletype", "none");
    model.result("pg1").feature("pttraj7").label("\u8f74\u9888\u8f74\u627f 3");
    model.result("pg1").feature("pttraj7").create("def", "Deform");
    model.result("pg1").feature("pttraj7").feature("def").set("scaleactive", true);
    model.result("pg1").feature("pttraj7").feature("def").set("scale", "1");
    model.result("pg1").feature("pttraj7").feature("def")
         .set("expr", new String[]{"-rotbm.De_max*rotbm.e30x ", "-rotbm.De_max*rotbm.e30y ", "-rotbm.De_max*rotbm.e30z "});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").feature("def").set("scale", 1.5);
    model.result("pg1").run();
    model.result("pg1").feature("pttraj1").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj2").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj3").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj4").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj5").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj6").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("pttraj7").set("inheritplot", "line1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").active(false);
    model.result("pg1").feature("pttraj1").active(false);
    model.result("pg1").feature("pttraj2").active(false);
    model.result("pg1").feature("pttraj3").active(false);
    model.result("pg1").feature("pttraj4").active(false);
    model.result("pg1").feature("pttraj5").active(false);
    model.result("pg1").feature("pttraj6").active(false);
    model.result("pg1").feature("pttraj7").active(false);
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2, 31});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{4, 31});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{6, 31});
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{8, 31});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("line1").active(true);
    model.result("pg1").feature("pttraj1").active(true);
    model.result("pg1").feature("pttraj2").active(true);
    model.result("pg1").feature("pttraj3").active(true);
    model.result("pg1").feature("pttraj4").active(true);
    model.result("pg1").feature("pttraj5").active(true);
    model.result("pg1").feature("pttraj6").active(true);
    model.result("pg1").feature("pttraj7").active(true);
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("lnsg1", "LineSegments");
    model.result("pg2").feature("lnsg1").set("data", "dset2");
    model.result("pg2").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg2").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "min(real(freq),rotbm.freqr)"});
    model.result("pg2").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg2").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg2").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg2").feature("lnsg1").set("linecolor", "black");
    model.result("pg2").feature("lnsg1").set("linewidth", 1);
    model.result("pg2").feature("lnsg1").set("legend", true);
    model.result("pg2").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg2").feature("lnsg1").set("legends", "1\\times\\Omega");
    model.result("pg2").feature("lnsg1").label("\u8f85\u52a9\u7ebf (1\u00d7\u03a9)");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("expr", "real(freq)");
    model.result("pg2").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "rotbm.freqr");
    model.result("pg2").feature("glob1").set("xdataunit", "Hz");
    model.result("pg2").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg2").feature("glob1").set("linestyle", "solid");
    model.result("pg2").feature("glob1").set("linecolor", "cycle");
    model.result("pg2").feature("glob1").set("linewidth", 2);
    model.result("pg2").feature("glob1").set("legend", false);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg2").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").feature("glob1").feature("col1").set("expr", "rotbm.i_sd");
    model.result("pg2").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg2").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg2").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg2").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg2").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg2").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg2").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg2").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffa", " -1");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg2").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg2").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg2").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg2").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 (1\u00d7\u03a9)");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").label("\u574e\u8d1d\u5c14\u56fe (rotbm)");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result("pg2").feature("lnsg1").setIndex("xexpr", 0, 0);
    model.result("pg2").feature("lnsg1").setIndex("xexpr", "rotbm.omegar", 1);
    model.result("pg2").feature("lnsg1").setIndex("xunit", "rad/s", 1);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", 0, 0);
    model.result("pg2").feature("lnsg1").setIndex("yexpr", "rotbm.omegar", 1);
    model.result("pg2").feature("lnsg1").setIndex("yunit", "rad/s", 1);
    model.result("pg2").feature("lnsg1").setIndex("legends", "1\\times\\Omega<sub>1</sub>", 0);
    model.result("pg2").feature().duplicate("lnsg2", "lnsg1");
    model.result("pg2").run();
    model.result("pg2").feature("lnsg2").label("\u8f85\u52a9\u7ebf (1.5\u00d7\u03a9)");
    model.result("pg2").feature("lnsg2").setIndex("yexpr", "1.5*rotbm.omegar", 1);
    model.result("pg2").feature("lnsg2").setIndex("legends", "1\\times\\Omega<sub>2</sub>", 0);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").setIndex("expr", "abs(freq)*2*pi", 0);
    model.result("pg2").feature("glob1").setIndex("unit", "rad/s", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Natural frequency", 0);
    model.result("pg2").feature("glob1").set("xdataexpr", "rotbm.omegar");
    model.result("pg2").feature("glob1").set("xdataunit", "rad/s");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 ([1,1.5]\u00d7\u03a9)");
    model.result("pg2").feature("glob1").feature("gmrk1").set("generalcoeffa", "-1 -1.5");
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymax", 3100);
    model.result("pg2").run();
    model.result("pg1").run();

    model.title("\u53cc\u8f6c\u5b50\u7cfb\u7edf\u7684\u4e34\u754c\u8f6c\u901f");

    model
         .description("\u7531\u4e8e\u5bf9\u66f4\u5927\u529f\u7387\u548c\u66f4\u5c0f\u5c3a\u5bf8\u7684\u9700\u6c42\uff0c\u5e26\u6709\u8f74\u95f4\u8f74\u627f\u7684\u53cc\u8f74\u7cfb\u7edf\u6b63\u6210\u4e3a\u71c3\u6c14\u8f6e\u673a\u53d1\u52a8\u673a\u7684\u6807\u51c6\u914d\u7f6e\u3002\u8fd9\u79cd\u7cfb\u7edf\u5305\u62ec\u4e24\u4e2a\u4ee5\u4e0d\u540c\u901f\u5ea6\u8fd0\u884c\u7684\u540c\u8f74\u8f6c\u5b50\uff0c\u4e24\u4e2a\u8f6c\u5b50\u901a\u8fc7\u4e00\u4e2a\u591a\u8f74\u8f74\u627f\u76f8\u4e92\u8fde\u63a5\u3002\u5728\u8fd9\u4e2a\u793a\u4f8b\u4e2d\uff0c\u4e3a\u4e86\u786e\u5b9a\u4e34\u754c\u8f6c\u901f\uff0c\u5bf9\u8fd9\u79cd\u53cc\u8f6c\u5b50\u7cfb\u7edf\u8fdb\u884c\u4e86\u7279\u5f81\u9891\u7387\u5206\u6790\u3002\u591a\u8f74\u8f74\u627f\u7684\u4ea4\u53c9\u6fc0\u52b1\u632f\u52a8\u4f7f\u5f97\u4e00\u4e2a\u8f74\u7684\u52a8\u529b\u7279\u6027\u53d6\u51b3\u4e8e\u53e6\u4e00\u4e2a\u8f74\u7684\u52a8\u529b\u7279\u6027\u3002");

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
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();
    model.sol("sol21").clearSolutionData();
    model.sol("sol22").clearSolutionData();
    model.sol("sol23").clearSolutionData();
    model.sol("sol24").clearSolutionData();
    model.sol("sol25").clearSolutionData();
    model.sol("sol26").clearSolutionData();
    model.sol("sol27").clearSolutionData();
    model.sol("sol28").clearSolutionData();
    model.sol("sol29").clearSolutionData();
    model.sol("sol30").clearSolutionData();
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();

    model.label("dual_rotors.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
