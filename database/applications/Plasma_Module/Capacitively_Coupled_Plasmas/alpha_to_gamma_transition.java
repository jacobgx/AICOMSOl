/*
 * alpha_to_gamma_transition.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:00 by COMSOL 6.3.0.290. */
public class alpha_to_gamma_transition {

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

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", 2, 1);

    model.param().set("f0", "13.56[MHz]");
    model.param().set("Prf", "1[W]");

    model.component("comp1").cpl().create("aveop1", "Average");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().all();

    model.component("comp1").physics("ptp").prop("CrossSectionArea").set("A", "80[cm^2]");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Period_xd", "1/f0");
    model.component("comp1").physics("ptp").prop("ExtraDimensionSettings").set("Nelemptp", 30);
    model.component("comp1").physics("ptp").feature("pes1").set("T", "300[K]");
    model.component("comp1").physics("ptp").feature("init1").set("neinit", "1E15[1/m^3]");
    model.component("comp1").physics("ptp").feature("init1").set("ebarinit", "2[V]");
    model.component("comp1").physics("ptp").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("ptp").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("ptp").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("ptp").create("eir6", "ElectronImpactReaction", 1);
    model.component("comp1").physics("ptp").feature("eir6").set("formula", "e+Ar2+=>Ars+Ar");
    model.component("comp1").physics("ptp").feature("eir6").set("type", "Excitation");
    model.component("comp1").physics("ptp").feature("eir6").set("de", "-2.5[V]");
    model.component("comp1").physics("ptp").feature("eir6")
         .set("kf", "7e-13[m^3/s]*N_A_const*(300/(ptp.Te*11600))^0.5");
    model.component("comp1").physics("ptp").create("rxn1", "Reaction", 1);
    model.component("comp1").physics("ptp").feature("rxn1").set("formula", "Ars+Ars=>e+Ar+Ar+");
    model.component("comp1").physics("ptp").feature("rxn1").set("kf", "1.2e-15[m^3/s]*N_A_const");
    model.component("comp1").physics("ptp").create("rxn2", "Reaction", 1);
    model.component("comp1").physics("ptp").feature("rxn2").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("rxn2").set("kf", "5e5");
    model.component("comp1").physics("ptp").create("rxn3", "Reaction", 1);
    model.component("comp1").physics("ptp").feature("rxn3").set("formula", "Ar+Ar+Ar+=>Ar2++Ar");
    model.component("comp1").physics("ptp").feature("rxn3").set("kf", "2.5e-43[m^6/s]*N_A_const^2");
    model.component("comp1").physics("ptp").feature("Ar").set("FromMassConstraint", true);
    model.component("comp1").physics("ptp").feature("Ar").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ars").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("PresetSpeciesData", "Ar");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("Ar_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("Ar_1p").set("MobilitySpecification", "ArIoninAr");
    model.component("comp1").physics("ptp").feature("Ar2_1p").set("InitIon", true);
    model.component("comp1").physics("ptp").feature("Ar2_1p").set("M", "0.08[kg/mol]");
    model.component("comp1").physics("ptp").feature("Ar2_1p")
         .set("MobilityDiffusivitySpecification", "SpecifyMobilityComputeDiffusivity");
    model.component("comp1").physics("ptp").feature("Ar2_1p")
         .set("IonTemperatureSpecification", "LocalFieldApproximation");
    model.component("comp1").physics("ptp").feature("Ar2_1p").set("MobilitySpecification", "ArIoninAr");
    model.component("comp1").physics("ptp").create("sr1", "SurfaceReaction", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("formula", "Ar+=>Ar");
    model.component("comp1").physics("ptp").feature("sr1").selection().all();
    model.component("comp1").physics("ptp").feature("sr1").set("gammaf", 0);
    model.component("comp1").physics("ptp").feature("sr1").set("gammai", 0.1);
    model.component("comp1").physics("ptp").feature().duplicate("sr2", "sr1");
    model.component("comp1").physics("ptp").feature("sr2").set("formula", "Ar2+=>2Ar");
    model.component("comp1").physics("ptp").feature().duplicate("sr3", "sr2");
    model.component("comp1").physics("ptp").feature("sr3").set("formula", "Ars=>Ar");
    model.component("comp1").physics("ptp").feature("sr3").set("gammai", 0);
    model.component("comp1").physics("ptp").feature("sr3").set("ebari", 0);
    model.component("comp1").physics("ptp").feature("sr3").set("gammaf", 1);
    model.component("comp1").physics("ptp").create("wall1", "WallDriftDiffusion", 0);
    model.component("comp1").physics("ptp").feature("wall1").selection().all();
    model.component("comp1").physics("ptp").create("gnd1", "Ground", 0);
    model.component("comp1").physics("ptp").feature("gnd1").selection().set(2);
    model.component("comp1").physics("ptp").create("mct1", "MetalContact", 0);
    model.component("comp1").physics("ptp").feature("mct1").selection().set(1);
    model.component("comp1").physics("ptp").feature("mct1").set("Source", "RF");
    model.component("comp1").physics("ptp").feature("mct1").set("Prf", "Prf");
    model.component("comp1").physics("ptp").feature("mct1").set("fp", "f0");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("type", "predefined");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemcount", 150);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("elemratio", 10);
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("symmetric", true);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").setGenConv(false);
    model.study("std1").feature("tper").set("useparam", true);
    model.study("std1").feature("tper").setIndex("pname", "f0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "Hz", 0);
    model.study("std1").feature("tper").setIndex("pname", "f0", 0);
    model.study("std1").feature("tper").setIndex("plistarr", "", 0);
    model.study("std1").feature("tper").setIndex("punit", "Hz", 0);
    model.study("std1").feature("tper").setIndex("pname", "Prf", 0);
    model.study("std1").feature("tper")
         .setIndex("plistarr", "10^{range(1.6989700043360187,0.04775533481992659,2.845098040014257)}", 0);
    model.study("std1").createAutoSequences("sol");
    model.study("std1").createAutoSequences("jobs");

    model.sol("sol1").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u529f\u7387\u548c ne vs. \u5916\u52a0\u7535\u538b");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").setIndex("expr", "ptp.mct1.PowerT", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "\u529f\u7387\u7ec8\u7aef", 0);
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "ptp.mct1.Va_per");
    model.result("pg1").feature().duplicate("glob2", "glob1");
    model.result("pg1").run();
    model.result("pg1").feature("glob2").setIndex("expr", "aveop1(ptp.neav)", 0);
    model.result("pg1").feature("glob2").setIndex("descr", "ne \u5e73\u5747\u503c", 0);
    model.result("pg1").run();
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("twoyaxes", true);
    model.result("pg1").setIndex("plotonsecyaxis", true, 1, 1);
    model.result("pg1").set("legendpos", "upperleft");

    model.sol("sol1").feature("s1").feature("fc1").set("plot", true);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("tptd", "TimePeriodicToTimeDependent");
    model.study("std2").feature("tptd").set("plotgroup", "Default");
    model.study("std2").feature("tptd").set("ftplistmethod", "manual");
    model.study("std2").feature("tptd").set("initialtime", "0");
    model.study("std2").feature("tptd").set("solnum", "auto");
    model.study("std2").feature("tptd").set("notsolnum", "auto");
    model.study("std2").feature("tptd").set("outputmap", new String[]{});
    model.study("std2").feature("tptd").setSolveFor("/physics/ptp", true);
    model.study().create("std3");
    model.study("std3").create("tptd", "TimePeriodicToTimeDependent");
    model.study("std3").feature("tptd").set("plotgroup", "Default");
    model.study("std3").feature("tptd").set("ftplistmethod", "manual");
    model.study("std3").feature("tptd").set("initialtime", "0");
    model.study("std3").feature("tptd").set("solnum", "auto");
    model.study("std3").feature("tptd").set("notsolnum", "auto");
    model.study("std3").feature("tptd").set("outputmap", new String[]{});
    model.study("std3").feature("tptd").setSolveFor("/physics/ptp", true);
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").feature("tptd").set("tlist", "range(0,(1/f0)/101,1/f0)");
    model.study("std2").feature("tptd").set("usesol", true);
    model.study("std2").feature("tptd").set("notsolmethod", "sol");
    model.study("std2").feature("tptd").set("notstudy", "std1");
    model.study("std2").feature("tptd").set("notsolnum", "from_list");
    model.study("std2").feature("tptd").set("notlistsolnum", new int[]{8});
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study("std3").setGenPlots(false);
    model.study("std3").setGenConv(false);
    model.study("std3").feature("tptd").set("tlist", "range(0,(1/f0)/101,1/f0)");
    model.study("std3").feature("tptd").set("usesol", true);
    model.study("std3").feature("tptd").set("notsolmethod", "sol");
    model.study("std3").feature("tptd").set("notstudy", "std1");
    model.study("std3").feature("tptd").set("notsolnum", "from_list");
    model.study("std3").feature("tptd").set("notlistsolnum", new int[]{24});
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().dataset().create("par1", "Parametric1D");
    model.result().dataset("par1").set("data", "dset2");
    model.result().dataset("par1").set("levelscale", "f0");
    model.result().dataset().duplicate("par2", "par1");
    model.result().dataset("par2").set("data", "dset3");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u5b50\u5bc6\u5ea6\uff0calpha \u6001");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u5468\u671f\u5206\u6570");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", "ptp.ne");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u5b50\u6e29\u5ea6\uff0calpha \u6001");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "ptp.Te");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u5b50\u5438\u6536\u529f\u7387\uff0calpha \u6001");
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "ptp.Pcap");
    model.result("pg4").run();
    model.result("pg4").create("con1", "Contour");
    model.result("pg4").feature("con1").set("expr", "(ptp.n_wAr_1p+ptp.n_wAr2_1p-ptp.ne)*e_const");
    model.result("pg4").feature("con1").set("levelmethod", "levels");
    model.result("pg4").feature("con1").set("levels", "1e-2");
    model.result("pg4").feature("con1").set("contourtype", "tubes");
    model.result("pg4").feature("con1").set("coloring", "uniform");
    model.result("pg4").feature("con1").set("color", "white");
    model.result("pg4").feature("con1").set("colorlegend", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u5b50\u5438\u6536\u529f\u7387\uff0cgamma \u6001");
    model.result("pg5").set("data", "par2");
    model.result("pg5").run();
    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u5b50\u6e90\uff0calpha \u6001");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("expr", "ptp.Re");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u7535\u5b50\u6e90\uff0cgamma \u6001");
    model.result("pg7").set("data", "par2");
    model.result("pg7").run();

    model.title("\u03b1 \u5230 \u03b3 \u8f6c\u53d8");

    model
         .description("\u7535\u5bb9\u8026\u5408\u5c04\u9891\u653e\u7535\u53ef\u4ee5\u6839\u636e\u653e\u7535\u529f\u7387\u5728\u4e24\u79cd\u5b8c\u5168\u4e0d\u540c\u7684\u6001\u4e0b\u5de5\u4f5c\u3002\u5728\u79f0\u4e3a \u03b1 \u6001\u7684\u4f4e\u529f\u7387\u6001\u4e0b\uff0c\u7535\u573a\u632f\u8361\u5bfc\u81f4\u52a0\u70ed\u5e76\u4ea7\u751f\u7535\u5b50\uff1b\u5728\u79f0\u4e3a \u03b3 \u6001\u7684\u9ad8\u529f\u7387\u6001\u4e0b\uff0c\u653e\u7535\u4e3b\u8981\u901a\u8fc7\u5957\u5185\u7531\u79bb\u5b50\u8f70\u51fb\u7535\u6781\u6240\u53d1\u5c04\u7684\u4e8c\u6b21\u7535\u5b50\u5f15\u53d1\u7684\u7535\u5b50\u96ea\u5d29\u6765\u7ef4\u6301\u3002\u6b64\u6a21\u578b\u4f7f\u7528\u201c\u7b49\u79bb\u5b50\u4f53\uff0c\u65f6\u95f4\u5468\u671f\u201d\u63a5\u53e3\u7814\u7a76\u5927\u6c14\u538b\u4e0b\uff0c\u7535\u5bb9\u8026\u5408\u5c04\u9891\u653e\u7535\u7684 \u03b1 \u6001\u548c \u03b3 \u6001\u4ee5\u53ca\u5b83\u4eec\u4e4b\u95f4\u7684\u8f6c\u53d8\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("alpha_to_gamma_transition.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
