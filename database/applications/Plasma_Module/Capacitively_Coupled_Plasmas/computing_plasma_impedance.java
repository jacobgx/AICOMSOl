/*
 * computing_plasma_impedance.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:01 by COMSOL 6.3.0.290. */
public class computing_plasma_impedance {

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

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "As");
    model.component("comp1").physics("ptp").prop("ElectronProperties").set("ReducedProps", true);
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "1/f0");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 30);
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "He_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").feature("He").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("He").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("Hes").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("He_1p").set("PresetSpeciesData", "He");
    model.component("comp1").physics("ptp").feature("He_1p").set("InitIon", true);
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
    model.component("comp1").physics("ptp").feature("mct1").set("Source", "RF");
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "P0");
    model.component("comp1").physics("ptp").feature("mct1").set("fp", "f0");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 125);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u65f6\u95f4\u5468\u671f");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x");
    model.result("pg1").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg1").feature("lngr1").selection().set(1);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"ptp.neav"});
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x");
    model.result("pg2").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg2").feature("lngr1").selection().set(1);
    model.result("pg2").feature("lngr1").set("expr", new String[]{"ptp.Teav"});
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset1");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "x");
    model.result("pg3").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg3").feature("lngr1").selection().set(1);
    model.result("pg3").feature("lngr1").set("expr", new String[]{"ptp.Vav"});
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset1");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr1").selection().all();
    model.result("pg4").feature("lngr1").set("expr", new String[]{"ptp.Pcap_av"});
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("legendmethod", "manual");
    model.result("pg4").feature("lngr1").setIndex("legends", "Electrons", 0);
    model.result("pg4").feature("lngr1").set("resolution", "norefine");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr2").selection().all();
    model.result("pg4").feature("lngr2").set("expr", new String[]{"ptp.Pcap_ions_av"});
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("legendmethod", "manual");
    model.result("pg4").feature("lngr2").setIndex("legends", "Ions", 0);
    model.result("pg4").feature("lngr2").set("resolution", "norefine");
    model.result("pg4").create("lngr3", "LineGraph");
    model.result("pg4").feature("lngr3").set("xdata", "expr");
    model.result("pg4").feature("lngr3").set("xdataexpr", new String[]{"x"});
    model.result("pg4").feature("lngr3").selection().all();
    model.result("pg4").feature("lngr3").set("expr", new String[]{"ptp.Pcap_ele_ions_av"});
    model.result("pg4").feature("lngr3").set("legend", true);
    model.result("pg4").feature("lngr3").set("legendmethod", "manual");
    model.result("pg4").feature("lngr3").setIndex("legends", "Electrons and ions", 0);
    model.result("pg4").feature("lngr3").set("resolution", "norefine");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "Power Deposition (W/m<sup>3</sup>)");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").label("\u7535\u5bb9\u529f\u7387\u6c89\u79ef\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset1");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "Neutral Species Number Density, Period Averaged (1/m<sup>3</sup>)");
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset1");
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "Charged Species Number Density, Period Averaged (1/m<sup>3</sup>)");
    model.result("pg6").set("ylabel", "Number density (1/m<sup>3</sup>)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("expr", new String[]{"ptp.n_wHe_av"});
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "wHe", 0);
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("xdata", "expr");
    model.result("pg5").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg5").feature("lngr2").selection().all();
    model.result("pg5").feature("lngr2").set("expr", new String[]{"ptp.n_wHes_av"});
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "wHes", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", new String[]{"x"});
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("expr", new String[]{"ptp.n_wHe_1p_av"});
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "wHe_1p", 0);
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("xdata", "expr");
    model.result("pg6").feature("lngr2").set("xdataexpr", new String[]{"x"});
    model.result("pg6").feature("lngr2").selection().all();
    model.result("pg6").feature("lngr2").set("expr", new String[]{"ptp.neav"});
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "ne", 0);
    model.result("pg5").label("\u4e2d\u6027\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg6").label("\u5e26\u7535\u7269\u8d28\u6570\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg1").label("\u7535\u5b50\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg2").label("\u7535\u5b50\u6e29\u5ea6\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result("pg3").label("\u7535\u52bf\uff0c\u5468\u671f\u5e73\u5747 (ptp)");
    model.result().dataset().create("dset2", "Solution");
    model.result().dataset("dset2").set("solution", "sol1");
    model.result().dataset("dset2").set("geom", "ptp_xdim");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", new String[]{"ptp.mct1.V"});
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("legendmethod", "manual");
    model.result("pg7").feature("lngr1").setIndex("legends", "V", 0);
    model.result("pg7").create("lngr2", "LineGraph");
    model.result("pg7").feature("lngr2").set("xdata", "expr");
    model.result("pg7").feature("lngr2").set("xdataexpr", new String[]{"x1_ptp"});
    model.result("pg7").feature("lngr2").selection().all();
    model.result("pg7").feature("lngr2").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg7").feature("lngr2").set("resolution", "norefine");
    model.result("pg7").feature("lngr2").set("legend", true);
    model.result("pg7").feature("lngr2").set("legendmethod", "manual");
    model.result("pg7").feature("lngr2").setIndex("legends", "I", 0);
    model.result("pg7").set("twoyaxes", true);
    model.result("pg7").setIndex("plotonsecyaxis", true, 0, 1);
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "Period fraction");
    model.result("pg7").label("\u7535\u6d41\u548c\u7535\u538b, \u91d1\u5c5e\u63a5\u89e6 1 (ptp)");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg7").run();

    model.study().create("std2");
    model.study("std2").create("tptd", "TimePeriodicToTimeDependent");
    model.study("std2").feature("tptd").set("plotgroup", "Default");
    model.study("std2").feature("tptd").set("ftplistmethod", "manual");
    model.study("std2").feature("tptd").set("initialtime", "0");
    model.study("std2").feature("tptd").set("solnum", "auto");
    model.study("std2").feature("tptd").set("notsolnum", "auto");
    model.study("std2").feature("tptd").set("outputmap", new String[]{});
    model.study("std2").feature("tptd").setSolveFor("/physics/ptp", true);
    model.study("std2").feature("tptd").set("tlist", "range(0,(1/f0-0)/100,1/f0)");
    model.study("std2").feature("tptd").set("usesol", true);
    model.study("std2").feature("tptd").set("notsolmethod", "sol");
    model.study("std2").feature("tptd").set("notstudy", "std1");
    model.study("std2").label("\u65f6\u95f4\u5468\u671f\u5230\u77ac\u6001");
    model.study("std2").setGenConv(false);
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8").label("\u7535\u6d41\u7684\u8c10\u6ce2\u542b\u91cf");
    model.result("pg8").set("data", "dset3");
    model.result("pg8").create("glob1", "Global");
    model.result("pg8").feature("glob1").set("markerpos", "datapoints");
    model.result("pg8").feature("glob1").set("linewidth", "preference");
    model.result("pg8").feature("glob1").set("expr", new String[]{"ptp.mct1.I"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u7535\u6d41"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("xdata", "fourier");
    model.result("pg8").feature("glob1").set("fouriershow", "spectrum");
    model.result("pg8").run();

    model.study().create("std3");
    model.study("std3").label("\u7528\u4e8e\u8ba1\u7b97\u7b49\u79bb\u5b50\u4f53\u963b\u6297\u7684 FFT \u7814\u7a76");
    model.study("std3").create("tffft", "TimeToFreqFFT");
    model.study("std3").feature("tffft").set("fftendtime", "1/f0");
    model.study("std3").feature("tffft").set("fftmaxfreq", "f0");
    model.study("std3").feature("tffft").set("fftinputstudy", "std2");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").set("expr", new String[]{"ptp.mct1.Z"});
    model.result().numerical("gev1").label("\u963b\u6297 (ptp, dset4)");
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").set("descr", "\u963b\u6297");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u963b\u6297");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.study().create("std4");
    model.study("std4").label("\u8fd0\u884c\u6240\u6709\u7814\u7a76");
    model.study("std4").create("ref", "StudyReference");
    model.study("std4").feature("ref").set("studyref", "std1");
    model.study("std4").create("ref2", "StudyReference");
    model.study("std4").feature("ref2").set("studyref", "std2");
    model.study("std4").create("ref3", "StudyReference");
    model.study("std4").feature("ref3").set("studyref", "std3");

    model.param().set("P0", "50[W]");

    model.study("std4").createAutoSequences("all");

    model.batch("s1").run("compute");

    model.result("pg8").run();
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();

    model.title("\u8ba1\u7b97\u7b49\u79bb\u5b50\u4f53\u963b\u6297");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u8ba1\u7b97\u7535\u5bb9\u8026\u5408\u7b49\u79bb\u5b50\u4f53\u7684\u963b\u6297\u3002\u5148\u901a\u8fc7\u201c\u65f6\u95f4\u5468\u671f\u201d\u7814\u7a76\u8ba1\u7b97\u7b49\u79bb\u5b50\u4f53\u7684\u65f6\u95f4\u5468\u671f\u89e3\uff0c\u7136\u540e\uff0c\u5c06\u8be5\u89e3\u8f6c\u6362\u4e3a\u65f6\u57df\u89e3\uff0c\u518d\u8c03\u7528\u5feb\u901f\u5085\u91cc\u53f6\u53d8\u6362 (FFT) \u6c42\u89e3\u5668\u3002\u8fd9\u6837\u53ef\u4ee5\u6839\u636e\u7ed9\u5b9a\u7684\u4e00\u7ec4\u8f93\u5165\u53c2\u6570\u6765\u8ba1\u7b97\u7b49\u79bb\u5b50\u4f53\u963b\u6297\u3002\u8bbe\u8ba1\u5339\u914d\u7f51\u7edc\u65f6\uff0c\u8be5\u963b\u6297\u503c\u975e\u5e38\u6709\u7528\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.label("computing_plasma_impedance.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
