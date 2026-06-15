/*
 * ac_fuel_cell.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:07 by COMSOL 6.3.0.290. */
public class ac_fuel_cell {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\Fuel_Cells");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("fc", "HydrogenFuelCell", "geom1");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings")
         .set("ChargeCarryingIon", "Generic");
    model.component("comp1").physics("fc").prop("DefaultElectrodeReactionSettings").set("OperationMode", "FuelCell");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("ftplistmethod", "manual");
    model.study("std1").feature("stat").set("solnum", "auto");
    model.study("std1").feature("stat").set("notsolnum", "auto");
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/fc", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("ftplistmethod", "manual");
    model.study("std1").feature("frlin").set("solnum", "auto");
    model.study("std1").feature("frlin").set("notsolnum", "auto");
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/fc", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("k", "10[S/m]", "\u591a\u5b54\u7535\u6781\u7684\u7535\u5bfc\u7387");
    model.param().set("sigmal", "5[S/m]", "\u7535\u89e3\u8d28\u7535\u5bfc\u7387");
    model.param().set("fl", "0.2", "\u6709\u6548\u7535\u89e3\u8d28\u7535\u5bfc\u7387\u6821\u6b63");
    model.param().set("T", "353[K]", "\u6e29\u5ea6");
    model.param().set("Eeq", "1[V]", "\u5e73\u8861\u7535\u4f4d");
    model.param().set("i0", "1[A/m^2]", "\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("av", "1e6[1/m]", "\u6d3b\u6027\u6bd4\u8868\u9762\u79ef");
    model.param().set("Cdl", "0.5[F/m^2]", "\u7535\u53cc\u5c42\u7535\u5bb9");
    model.param().set("avdl", "1e6[1/m]", "\u53cc\u7535\u5c42\u9762\u79ef");
    model.param().set("iavg", "-0.1[A/cm^2]", "\u5916\u52a0\u5e73\u5747\u7535\u6781\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("delta_iavg", "5[mA/cm^2]", "\u6270\u52a8\u5927\u5c0f");
    model.param()
         .set("L", "100[um]", "\u7535\u89e3\u8d28\u57df\u548c\u591a\u5b54\u7535\u6781\u57df\u7684\u5bbd\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").set("specify", "len");
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L", 0);
    model.component("comp1").geom("geom1").feature("i1").setIndex("len", "L", 1);
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("fc").create("mem1", "Membrane", 1);
    model.component("comp1").physics("fc").feature("mem1").selection().set(1);
    model.component("comp1").physics("fc").create("o2gde1", "O2GasDiffusionElectrode", 1);
    model.component("comp1").physics("fc").feature("o2gde1").selection().set(2);
    model.component("comp1").physics("fc").feature("icph1").set("sigmal_mat", "userdef");
    model.component("comp1").physics("fc").feature("icph1")
         .set("sigmal", new String[]{"sigmal", "0", "0", "0", "sigmal", "0", "0", "0", "sigmal"});
    model.component("comp1").physics("fc").feature("o2gde1")
         .set("sigmas", new String[]{"k", "0", "0", "0", "k", "0", "0", "0", "k"});
    model.component("comp1").physics("fc").feature("o2gde1").set("IonicCorrModel", "userdef");
    model.component("comp1").physics("fc").feature("o2gde1").set("fl", "fl");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Eeq_mat", "userdef");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Eeq", "Eeq");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("i0", "i0");
    model.component("comp1").physics("fc").feature("o2gde1").feature("o2gder1").set("Av", "av");
    model.component("comp1").physics("fc").feature("o2gde1").create("pdl1", "PorousMatrixDoubleLayerCapacitance", 1);
    model.component("comp1").physics("fc").feature("o2gde1").feature("pdl1").set("Cdl", "Cdl");
    model.component("comp1").physics("fc").feature("o2gde1").feature("pdl1").set("av_dl", "avdl");
    model.component("comp1").physics("fc").feature("icph1").create("eip1", "ElectrolytePotential", 0);
    model.component("comp1").physics("fc").feature("icph1").feature("eip1").selection().set(1);
    model.component("comp1").physics("fc").feature("ecph1").create("ec1", "ElectrodeCurrent", 0);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").selection().set(3);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1")
         .set("ElectronicCurrentType", "AverageCurrentDensity");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("Ias", "iavg");
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("IncludeHarmonicPerturbation", true);
    model.component("comp1").physics("fc").feature("ecph1").feature("ec1").set("deltaIas", "delta_iavg");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("pname", "k", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "S/m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10 1000", 0);
    model.study("std1").feature("frlin").set("plist", "10^range(0,0.05,4.95) 10^range(5,0.5,7)");
    model.study("std1").feature("stat").set("usestol", true);
    model.study("std1").feature("stat").set("stol", "1e-5");
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std1");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset3");
    model.result("pg1").create("nyq1", "Nyquist");
    model.result("pg1").feature("nyq1").set("unit", new String[]{""});
    model.result("pg1").feature("nyq1").set("expr", new String[]{"conj(fc.Zvsgrnd_ec1) "});
    model.result("pg1").feature("nyq1").set("descr", new String[]{""});
    model.result("pg1").label("\u5bf9\u5730\u963b\u6297\uff0c\u5948\u594e\u65af\u7279 (fc)");
    model.result("pg1").feature("nyq1").setIndex("descr", "\u5bf9\u5730\u963b\u6297", 0);
    model.result("pg1").feature("nyq1").set("differential", "off");
    model.result("pg1").feature("nyq1").set("autodescr", "off");
    model.result("pg1").set("preserveaspect", "on");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"real(conj(fc.Zvsgrnd_ec1)) "});
    model.result("pg2").feature("glob1").set("descr", new String[]{""});
    model.result("pg2").label("\u5bf9\u5730\u963b\u6297\uff0c\u5b9e\u90e8 (fc)");
    model.result("pg2").feature("glob1").setIndex("descr", "Impedance_vs_ground_real_part", 0);
    model.result("pg2").feature("glob1").set("differential", "off");
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "freq");
    model.result("pg2").feature("glob1").set("autodescr", "off");
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg2").set("xlog", "on");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "real(Z) (\\Omega m<sup>2</sup>)");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"imag(conj(fc.Zvsgrnd_ec1)) "});
    model.result("pg3").feature("glob1").set("descr", new String[]{""});
    model.result("pg3").label("\u5bf9\u5730\u963b\u6297\uff0c\u865a\u90e8 (fc)");
    model.result("pg3").feature("glob1").setIndex("descr", "Impedance_vs_ground_imaginary_part", 0);
    model.result("pg3").feature("glob1").set("differential", "off");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "freq");
    model.result("pg3").feature("glob1").set("autodescr", "off");
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg3").set("xlog", "on");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "-imag(Z) (\\Omega m<sup>2</sup>)");
    model.result("pg1").run();
    model.result("pg1").run();

    model.title("\u71c3\u6599\u7535\u6c60\u4e2d\u7684\u7535\u5316\u5b66\u963b\u6297\u8c31");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6a21\u62df\u71c3\u6599\u7535\u6c60\u7684\u7535\u5316\u5b66\u4ea4\u6d41\u963b\u6297\u3002");

    model.label("ac_fuel_cell.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
