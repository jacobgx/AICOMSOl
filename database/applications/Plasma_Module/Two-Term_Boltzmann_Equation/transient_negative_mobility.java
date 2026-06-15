/*
 * transient_negative_mobility.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:08 by COMSOL 6.3.0.290. */
public class transient_negative_mobility {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Plasma_Module\\Two-Term_Boltzmann_Equation");

    model.component().create("comp1", true);

    model.component("comp1").physics().create("be", "BoltzmannEquation");

    model.study().create("std1");
    model.study("std1").create("ref", "ReducedElectricFields");
    model.study("std1").feature("ref").set("ftplistmethod", "manual");
    model.study("std1").feature("ref").set("solnum", "auto");
    model.study("std1").feature("ref").set("notsolnum", "auto");
    model.study("std1").feature("ref").set("outputmap", new String[]{});
    model.study("std1").feature("ref").setSolveFor("/physics/be", true);

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedf", "BoltzmannTwoTerm");
    model.component("comp1").physics("be").prop("EEDFSettings").set("eecolls", true);
    model.component("comp1").physics("be").prop("EEDFSettings").set("NelemEEDF", 300);
    model.component("comp1").physics("be").prop("EEDFSettings").set("RelemEEDF", 30);
    model.component("comp1").physics("be").prop("EEDFSettings").set("emax", 30);
    model.component("comp1").physics("be").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec1").set("Filepath", "Xe_xsecs.txt");
    model.component("comp1").physics("be").feature("xsec1").runCommand("importData");

    model.param().set("Tgas", "300[K]");
    model.param().descr("Tgas", "\u6c14\u4f53\u6e29\u5ea6");
    model.param().set("P0", "1[atm]");
    model.param().descr("P0", "\u6c14\u538b");
    model.param().set("Ngas", "P0/(k_B_const*Tgas)");
    model.param().descr("Ngas", "\u6c14\u4f53\u5bc6\u5ea6");
    model.param().set("Beta", "1e-7");
    model.param().descr("Beta", "\u7535\u79bb\u5ea6");
    model.param().set("ne", "Beta*Ngas+eps");
    model.param().descr("ne", "\u7535\u5b50\u5bc6\u5ea6");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", -5);
    model.component("comp1").func("rect1").set("upper", 5);
    model.component("comp1").func("rect1").set("smooth", 3);

    model.component("comp1").physics("be").feature("bmdl1").set("Tg", "Tgas");
    model.component("comp1").physics("be").feature("bmdl1").set("ne", "ne");
    model.component("comp1").physics("be").feature("bmdl1").set("beta", "Beta");
    model.component("comp1").physics("be").feature("bmdl1").set("ebar", false);
    model.component("comp1").physics("be").feature("bmdl1").set("TransportPropPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("RateCoefPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("ebar", true);
    model.component("comp1").physics("be").feature("bmdl1").set("PlotAsAFunctionOf", "EN");

    model.study("std1").label("\u7a33\u6001");
    model.study("std1").feature("ref").set("plist", "10^{range(log10(0.1),1/7,log10(10))}[Td]");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "Tgas", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "Tgas", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "K", 0);
    model.study("std1").feature("param").setIndex("pname", "Beta", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0 1e-7 1e-6 1e-5", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Energy (eV)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg1").label("EEDF (be)");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"be.ebar"});
    model.result("pg2").feature("glob1").set("descr", new String[]{"\u7535\u5b50\u5e73\u5747\u80fd\u91cf"});
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "be.EN");
    model.result("pg2").feature("glob1").set("xdataunit", "Td");
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", "on");
    model.result("pg2").set("xlabel", "Reduced electric field (Td)");
    model.result("pg2").set("ylabelactive", "on");
    model.result("pg2").set("ylabel", "Mean electron energy (eV)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf (be)");
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevelinput", "manual", 1);
    model.result("pg1").setIndex("looplevel", new int[]{1}, 1);
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 10);
    model.result("pg1").set("ymin", "1e-4");
    model.result("pg1").set("ymax", 20);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg1").feature("lngr1").set("legendpattern", "eval(be.EN, Td, 2) Td");
    model.result("pg1").run();
    model.result("pg1").label("EEDF\uff0cBeta=0");
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("EEDF\uff0cBeta=1e-6");
    model.result("pg3").setIndex("looplevel", new int[]{3}, 1);
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("ymin", 0.5);
    model.result("pg2").set("ymax", 4);
    model.result("pg2").set("showlegends", true);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();
    model.result("pg2").set("legendpos", "upperleft");
    model.result("pg2").set("xlog", true);
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").label("\u7a33\u6001");

    model.result().create("pg4", "PlotGroup1D");

    model.nodeGroup("grp1").add("plotgroup", "pg4");

    model.result("pg4").run();
    model.result("pg4").label("\u6f02\u79fb\u901f\u5ea6");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "be.w", 0);
    model.result("pg4").run();
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "be.EN");
    model.result("pg4").feature("glob1").set("xdataunit", "Td");
    model.result("pg4").feature("glob1").set("autodescr", false);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("xlog", true);
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("ymin", 800);
    model.result("pg4").set("ymax", 10000);
    model.result("pg4").set("legendpos", "upperleft");

    model.study().create("std2");
    model.study("std2").create("ref", "ReducedElectricFields");
    model.study("std2").feature("ref").set("plotgroup", "Default");
    model.study("std2").feature("ref").set("ftplistmethod", "manual");
    model.study("std2").feature("ref").set("solnum", "auto");
    model.study("std2").feature("ref").set("notsolnum", "auto");
    model.study("std2").feature("ref").set("outputmap", new String[]{});
    model.study("std2").feature("ref").setSolveFor("/physics/be", true);
    model.study().create("std3");
    model.study("std3").create("ref", "ReducedElectricFields");
    model.study("std3").feature("ref").set("plotgroup", "Default");
    model.study("std3").feature("ref").set("ftplistmethod", "manual");
    model.study("std3").feature("ref").set("solnum", "auto");
    model.study("std3").feature("ref").set("notsolnum", "auto");
    model.study("std3").feature("ref").set("outputmap", new String[]{});
    model.study("std3").feature("ref").setSolveFor("/physics/be", true);
    model.study().create("std4");
    model.study("std4").create("time", "Transient");
    model.study("std4").feature("time").setSolveFor("/physics/be", true);
    model.study().create("std5");
    model.study("std5").create("time", "Transient");
    model.study("std5").feature("time").setSolveFor("/physics/be", true);
    model.study("std2").label("\u77ac\u6001\u7684\u521d\u59cb\u6761\u4ef6\uff0c2.2[Td]\uff0cBeta=0");
    model.study("std2").feature("ref").set("plist", "2.2[Td]");

    model.param().set("P0", "10[atm]");
    model.param().set("Beta", "0");

    model.component("comp1").physics("be").feature("bmdl1").set("ebar", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol7").runAll();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg5").feature("lngr1").selection().all();
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg5").set("titletype", "none");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "Energy (eV)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg5").label("EEDF (be)");
    model.result("pg5").run();
    model.result("pg5").label("EEDF\uff0cBeta=0\uff0c2.2 Td");
    model.result("pg5").run();

    model.study("std3").label("\u77ac\u6001\u7684\u521d\u59cb\u6761\u4ef6\uff0c2.2[Td]\uff0cBeta=1e-7");
    model.study("std3").feature("ref").set("plist", "2.2[Td]");

    model.param().set("Beta", "1e-7");

    model.study("std3").createAutoSequences("all");

    model.sol("sol8").runAll();

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset4");
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg6").feature("lngr1").selection().all();
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylog", true);
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "Energy (eV)");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg6").label("EEDF (be)");
    model.result("pg6").run();
    model.result("pg5").run();
    model.result("pg5").label("EEDF\uff0cBeta=1e-7\uff0c2.2[Td]");
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").label("\u521d\u59cb\u6761\u4ef6");

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedfTemporalBehavior", "TimeDependentEEDF");
    model.component("comp1").physics("be").feature("bmdl1").set("pA", "P0");
    model.component("comp1").physics("be").feature("bmdl1").set("Erd", "rect1(t/1[ns])*(2.2-0.01)[Td]+0.01[Td]");

    model.study("std4").label("\u77ac\u6001\uff0cBeta=0");
    model.study("std4").feature("time").set("tunit", "ns");
    model.study("std4").feature("time").set("tlist", "0  range(1,1,40) range(45,5,200)");
    model.study("std4").feature("time").set("useinitsol", true);
    model.study("std4").feature("time").set("initmethod", "sol");
    model.study("std4").feature("time").set("initstudy", "std2");

    model.param().set("Beta", "0");

    model.study("std4").createAutoSequences("all");

    model.sol("sol9").runAll();

    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").set("data", "dset5");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("legend", true);
    model.result("pg7").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg7").set("titletype", "none");
    model.result("pg7").set("ylog", true);
    model.result("pg7").set("xlabelactive", true);
    model.result("pg7").set("xlabel", "Energy (eV)");
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg7").label("EEDF (be) 1");
    model.result("pg7").run();
    model.result("pg7").label("EEDF \u77ac\u6001\uff0cBeta=0");
    model.result("pg7").setIndex("looplevelinput", "manual", 0);
    model.result("pg7").setIndex("looplevel", new int[]{6, 7, 9, 21, 44}, 0);
    model.result("pg7").run();
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("xmin", 0);
    model.result("pg7").set("xmax", 7);
    model.result("pg7").set("ymin", "3e-4");
    model.result("pg7").set("ymax", 10);
    model.result("pg7").run();

    model.param().set("Beta", "1e-7");

    model.study("std5").feature("time").set("tunit", "ns");
    model.study("std5").feature("time").set("tlist", "0  range(1,1,40) range(45,5,200)");
    model.study("std5").feature("time").set("useinitsol", true);
    model.study("std5").feature("time").set("initstudy", "std3");
    model.study("std5").feature("time").set("initmethod", "sol");
    model.study("std5").label("\u77ac\u6001\uff0cBeta=1e-7");
    model.study("std5").createAutoSequences("all");

    model.sol("sol10").runAll();

    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").set("data", "dset6");
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("xdata", "expr");
    model.result("pg8").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg8").feature("lngr1").selection().all();
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("expr", new String[]{"be.f"});
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("ylog", true);
    model.result("pg8").set("xlabelactive", true);
    model.result("pg8").set("xlabel", "Energy (eV)");
    model.result("pg8").set("ylabelactive", true);
    model.result("pg8").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg8").label("EEDF (be) 1");
    model.result("pg8").run();
    model.result("pg8").label("EEDF \u77ac\u6001\uff0cBeta=1e-7");
    model.result("pg8").setIndex("looplevelinput", "manual", 0);
    model.result("pg8").setIndex("looplevel", new int[]{6, 7, 9, 21, 36}, 0);
    model.result("pg8").set("axislimits", true);
    model.result("pg8").set("xmin", 0);
    model.result("pg8").set("xmax", 7);
    model.result("pg8").set("ymin", "3e-4");
    model.result("pg8").set("ymax", 10);
    model.result("pg8").run();
    model.result("pg7").run();

    model.nodeGroup().create("grp3", "Results");
    model.nodeGroup("grp3").set("type", "plotgroup");
    model.nodeGroup().move("grp3", 2);
    model.nodeGroup("grp3").add("plotgroup", "pg7");
    model.nodeGroup("grp3").add("plotgroup", "pg8");
    model.nodeGroup("grp3").label("\u77ac\u6001");

    model.result().create("pg9", "PlotGroup1D");

    model.nodeGroup("grp3").add("plotgroup", "pg9");

    model.result("pg9").run();
    model.result("pg9").label("\u77ac\u6001\u6f02\u79fb\u901f\u5ea6");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").set("data", "dset5");
    model.result("pg9").feature("glob1").setIndex("expr", "be.w", 0);
    model.result("pg9").feature("glob1").set("legendmethod", "evaluated");
    model.result("pg9").feature("glob1").set("legendpattern", "Beta=eval(Beta)");
    model.result("pg9").run();
    model.result("pg9").feature().duplicate("glob2", "glob1");
    model.result("pg9").run();
    model.result("pg9").feature("glob2").set("data", "dset6");
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").set("titletype", "none");
    model.result("pg9").set("axislimits", true);
    model.result("pg9").set("ymin", -500);
    model.result("pg9").set("ymax", 2000);
    model.result("pg9").run();
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg2").run();
    model.result("pg4").run();
    model.result("pg7").run();
    model.result("pg8").run();
    model.result("pg9").run();

    model
         .title("\u6c19\u7684\u77ac\u6001\u8d1f\u8fc1\u79fb\u7387\u548c\u8d1f\u5fae\u5206\u7535\u5bfc\u7387\u6548\u5e94");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u63d0\u51fa\u7684\u7814\u7a76\u663e\u793a\u6c19\u4e2d\u7684\u77ac\u6001\u8d1f\u8fc1\u79fb\u7387\u548c\u8d1f\u5fae\u5206\u7535\u5bfc\u7387\u6548\u5e94\uff0c\u5176\u4e2d\u4f7f\u7528\u4e24\u9879\u8fd1\u4f3c\u7684\u7a33\u6001\u548c\u77ac\u6001\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\u6765\u8ba1\u7b97\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u3002\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u3001\u7535\u5b50\u5e73\u5747\u80fd\u91cf\u548c\u6f02\u79fb\u901f\u5ea6\u4e0e Z. Donko \u548c N. Dyatko \u53d1\u8868\u7684\u4eff\u771f\u7ed3\u679c\u975e\u5e38\u543b\u5408\u3002");

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

    model.label("transient_negative_mobility.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
