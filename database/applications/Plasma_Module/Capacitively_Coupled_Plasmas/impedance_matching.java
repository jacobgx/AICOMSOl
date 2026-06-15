/*
 * impedance_matching.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class impedance_matching {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Capacitively_Coupled_Plasmas");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ptp", "ColdPlasmaTimePeriodic", "geom1");

    model.study().create("std1");
    model.study("std1").create("tper", "TimePeriodic");
    model.study("std1").feature("tper").set("ftplistmethod", "manual");
    model.study("std1").feature("tper").set("solnum", "auto");
    model.study("std1").feature("tper").set("notsolnum", "auto");
    model.study("std1").feature("tper").set("outputmap", new String[]{});
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").set("ngenAUX", "1");
    model.study("std1").feature("tper").set("ngen", "2");
    model.study("std1").feature("tper").set("goalngenAUX", "1");
    model.study("std1").feature("tper").setSolveFor("/physics/ptp", true);

    model.param().set("L", "0.025[m]");
    model.param().descr("L", "\u653e\u7535\u95f4\u9699");
    model.param().set("de", "0.3[m]");
    model.param().descr("de", "\u7535\u6781\u76f4\u5f84");
    model.param().set("As", "0.25*pi*de^2");
    model.param().descr("As", "\u7535\u6781\u9762\u79ef");
    model.param().set("P0", "10[W]");
    model.param().descr("P0", "\u8f93\u5165\u529f\u7387");
    model.param().set("f0", "13.56E6[Hz]");
    model.param().descr("f0", "\u9891\u7387");
    model.param().set("p0", "1[torr]");
    model.param().descr("p0", "\u538b\u529b");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u6e29\u5ea6");
    model.param().set("Rp", "42.696[ohm]");
    model.param().descr("Rp", "\u7b49\u79bb\u5b50\u4f53\u963b\u6297\uff0c\u5b9e\u90e8");
    model.param().set("Xp", "-156.62[ohm]");
    model.param().descr("Xp", "\u7b49\u79bb\u5b50\u4f53\u963b\u6297\uff0c\u865a\u90e8");
    model.param().set("Rs", "50[ohm]");
    model.param().descr("Rs", "\u53d1\u7535\u673a\u963b\u6297");
    model.param().set("fmatch", "13.56E6[Hz]");
    model.param().descr("fmatch", "\u5b8c\u5168\u5339\u914d\u7684\u9891\u7387");
    model.param().set("Xmd", "sqrt(Rp*Rs-Rp^2)-Xp");
    model.param().descr("Xmd", "\u5339\u914d\u52a9\u53d8\u91cf");
    model.param().set("Bm", "((1/(Rp*Rs))-(1/Rs^2))^0.5");
    model.param().descr("Bm", "\u5339\u914d\u52a9\u53d8\u91cf");
    model.param().set("Lm", "Xmd/(2*pi*fmatch)");
    model.param().descr("Lm", "\u7535\u611f");
    model.param().set("Cp", "Bm/(2*pi*fmatch)");
    model.param().descr("Cp", "\u7535\u5bb9");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "As");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "1/f0");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 30);
    model.component("comp1").physics("ptp").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "He_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").feature("He").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("He").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("Hes").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("He_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("He_1p").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("He_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("He_1p").set("MobilitySpecification", "HeIoninHe");
    model.component("comp1").physics("ptp").feature("pes1").set("T", "T0");
    model.component("comp1").physics("ptp").feature("pes1").set("pA", "p0");
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr1").selection().all();
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "He+=>He");
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("gammai", 0.1);
    model.component("comp1").physics("ptp").feature("sr1").set("ebari", 5.8);
    model.component("comp1").physics("ptp").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Hes=>He");
    model.component("comp1").physics("ptp").feature("sr2").set("gammaf", 1);
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 0);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(2);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(1);
    model.component("comp1").physics("ptp").feature("mct1").set("TerminalType", "Circuit");
    model.component("comp1").physics("ptp").feature("mct1").set("CircuitType", "LNetwork");
    model.component("comp1").physics("ptp").feature("mct1").set("SourceType", "PowerSource");
    model.component("comp1").physics("ptp").feature("mct1").set("Ps", "P0");
    model.component("comp1").physics("ptp").feature("mct1").set("Rs", "Rs");
    model.component("comp1").physics("ptp").feature("mct1").set("Cp", "Cp");
    model.component("comp1").physics("ptp").feature("mct1").set("Lm", "Lm");
    model.component("comp1").physics("ptp").feature("mct1").set("fpc", "f0");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 125);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u529f\u7387\u626b\u63cf");
    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("tper").set("useparam", true);
    model.study("std1").feature("tper").setIndex("pname", "L", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "m", 0);
    model.study("std1").feature("tper").setIndex("pname", "L", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "m", 0);
    model.study("std1").feature("tper").setIndex("pname", "P0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "range(1,0.5918367346938775,30)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"ptp.mct1.alphaP"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6700\u5927\u529f\u7387\u4f20\u9012\u7cfb\u6570"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg1").feature("glob1").set("expr", new String[]{"ptp.mct1.alphaP", "ptp.mct1.etaP"});
    model.result("pg1").feature("glob1")
         .set("descr", new String[]{"\u6700\u5927\u529f\u7387\u4f20\u9012\u7cfb\u6570", "\u6548\u7387"});
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6700\u5927\u529f\u7387\u4f20\u9012\u6548\u7387 (1) \u548c\u6548\u7387 (1)");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg1").label("\u529f\u7387\u626b\u63cf");

    model.study().create("std2");
    model.study("std2").create("tper", "TimePeriodic");
    model.study("std2").feature("tper").set("plotgroup", "Default");
    model.study("std2").feature("tper").set("ftplistmethod", "manual");
    model.study("std2").feature("tper").set("solnum", "auto");
    model.study("std2").feature("tper").set("notsolnum", "auto");
    model.study("std2").feature("tper").set("outputmap", new String[]{});
    model.study("std2").feature("tper").set("ngenAUX", "1");
    model.study("std2").feature("tper").set("ngen", "2");
    model.study("std2").feature("tper").set("goalngenAUX", "1");
    model.study("std2").feature("tper").set("ngenAUX", "1");
    model.study("std2").feature("tper").set("ngen", "2");
    model.study("std2").feature("tper").set("goalngenAUX", "1");
    model.study("std2").feature("tper").setSolveFor("/physics/ptp", true);
    model.study("std2").feature("tper").set("useparam", true);
    model.study("std2").feature("tper").setIndex("pname", "L", 0);
    model.study("std2").feature("tper").setIndex("plistarr", "", 0);
    model.study("std2").feature("tper").setIndex("punit", "m", 0);
    model.study("std2").feature("tper").setIndex("pname", "L", 0);
    model.study("std2").feature("tper").setIndex("plistarr", "", 0);
    model.study("std2").feature("tper").setIndex("punit", "m", 0);
    model.study("std2").feature("tper").setIndex("pname", "f0", 0);
    model.study("std2").feature("tper").setIndex("plistarr", "range(11,0.1,16)", 0);
    model.study("std2").feature("tper").setIndex("punit", "MHz", 0);
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").label("\u9891\u7387\u626b\u63cf");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u9891\u7387\u626b\u63cf");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").run();

    model.study().create("std3");
    model.study("std3").create("tper", "TimePeriodic");
    model.study("std3").feature("tper").set("plotgroup", "Default");
    model.study("std3").feature("tper").set("ftplistmethod", "manual");
    model.study("std3").feature("tper").set("solnum", "auto");
    model.study("std3").feature("tper").set("notsolnum", "auto");
    model.study("std3").feature("tper").set("outputmap", new String[]{});
    model.study("std3").feature("tper").set("ngenAUX", "1");
    model.study("std3").feature("tper").set("ngen", "2");
    model.study("std3").feature("tper").set("goalngenAUX", "1");
    model.study("std3").feature("tper").set("ngenAUX", "1");
    model.study("std3").feature("tper").set("ngen", "2");
    model.study("std3").feature("tper").set("goalngenAUX", "1");
    model.study("std3").feature("tper").setSolveFor("/physics/ptp", true);
    model.study("std3").feature("tper").set("useparam", true);
    model.study("std3").feature("tper").setIndex("pname", "L", 0);
    model.study("std3").feature("tper").setIndex("plistarr", "", 0);
    model.study("std3").feature("tper").setIndex("punit", "m", 0);
    model.study("std3").feature("tper").setIndex("pname", "L", 0);
    model.study("std3").feature("tper").setIndex("plistarr", "", 0);
    model.study("std3").feature("tper").setIndex("punit", "m", 0);
    model.study("std3").feature("tper").setIndex("pname", "p0", 0);
    model.study("std3").feature("tper").setIndex("plistarr", "range(2,-0.055172413793103454,0.4)", 0);
    model.study("std3").feature("tper").setIndex("punit", "torr", 0);
    model.study("std3").label("\u538b\u529b\u626b\u63cf");
    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u538b\u529b\u626b\u63cf");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").set("xdataparamunit", "Torr");
    model.result("pg3").run();

    model.param().set("Rp", "29.175[ohm]");
    model.param().set("Xp", "-126.47[ohm]");

    model.study().create("std4");
    model.study("std4").create("tper", "TimePeriodic");
    model.study("std4").feature("tper").set("plotgroup", "Default");
    model.study("std4").feature("tper").set("ftplistmethod", "manual");
    model.study("std4").feature("tper").set("solnum", "auto");
    model.study("std4").feature("tper").set("notsolnum", "auto");
    model.study("std4").feature("tper").set("outputmap", new String[]{});
    model.study("std4").feature("tper").set("ngenAUX", "1");
    model.study("std4").feature("tper").set("ngen", "2");
    model.study("std4").feature("tper").set("goalngenAUX", "1");
    model.study("std4").feature("tper").set("ngenAUX", "1");
    model.study("std4").feature("tper").set("ngen", "2");
    model.study("std4").feature("tper").set("goalngenAUX", "1");
    model.study("std4").feature("tper").setSolveFor("/physics/ptp", true);
    model.study("std4").feature("tper").set("useparam", true);
    model.study("std4").feature("tper").setIndex("pname", "L", 0);
    model.study("std4").feature("tper").setIndex("plistarr", "", 0);
    model.study("std4").feature("tper").setIndex("punit", "m", 0);
    model.study("std4").feature("tper").setIndex("pname", "L", 0);
    model.study("std4").feature("tper").setIndex("plistarr", "", 0);
    model.study("std4").feature("tper").setIndex("punit", "m", 0);
    model.study("std4").feature("tper").setIndex("pname", "P0", 0);
    model.study("std4").feature("tper").setIndex("plistarr", "range(10,1.4,80)", 0);
    model.study("std4").setGenConv(false);
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u5927\u529f\u7387\u626b\u63cf");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"ptp.mct1.alphaP"});
    model.result("pg4").feature("glob1")
         .set("descr", new String[]{"\u6700\u5927\u529f\u7387\u4f20\u9012\u7cfb\u6570"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").create("glob2", "Global");
    model.result("pg4").feature("glob2").set("markerpos", "datapoints");
    model.result("pg4").feature("glob2").set("linewidth", "preference");
    model.result("pg4").feature("glob2").set("expr", new String[]{"ptp.mct1.etaP"});
    model.result("pg4").feature("glob2").set("descr", new String[]{"\u6548\u7387"});
    model.result("pg4").feature("glob2").set("unit", new String[]{"1"});
    model.result("pg4").run();
    model.result("pg4").label("\u5927\u529f\u7387\u626b\u63cf");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();

    model.title("\u963b\u6297\u5339\u914d");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5728\u9ad8\u4f4e\u529f\u7387\u4e0b\u4f7f\u7528 L \u578b\u5339\u914d\u7f51\u7edc\u9a71\u52a8\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u3002\u5728\u4f4e\u529f\u7387\u4e0b\uff0c\u7535\u6d41\u4e2d\u7684\u8c10\u6ce2\u6210\u5206\u8f83\u5c0f\uff0c\u901a\u8fc7\u9009\u5b9a\u7684\u529f\u7387\u503c\u53ef\u4ee5\u5b9e\u73b0\u5b8c\u7f8e\u5339\u914d\u3002\u672c\u4f8b\u5bf9\u529f\u7387\u3001\u9891\u7387\u548c\u538b\u529b\u6267\u884c\u626b\u63cf\uff0c\u4ece\u800c\u68c0\u67e5\u5b83\u4eec\u5bf9\u5339\u914d\u529f\u7387\u4f20\u8f93\u6bd4\u548c\u6548\u7387\u7684\u5f71\u54cd\u3002\u6700\u540e\uff0c\u5bf9\u8f83\u9ad8\u529f\u7387\u8303\u56f4\u6267\u884c\u626b\u63cf\uff0c\u7531\u4e8e\u7535\u6d41\u4e2d\u7684\u8c10\u6ce2\u5206\u91cf\u76f8\u5f53\u5927\uff0c\u5bfc\u81f4\u963b\u6297\u5931\u914d\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("impedance_matching.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
