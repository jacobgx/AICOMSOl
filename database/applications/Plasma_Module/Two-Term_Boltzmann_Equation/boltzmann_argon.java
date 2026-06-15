/*
 * boltzmann_argon.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:07 by COMSOL 6.3.0.290. */
public class boltzmann_argon {

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

    model.param().set("Beta", "0");
    model.param().descr("Beta", "\u7535\u79bb\u5ea6");
    model.param().set("xArs", "0");
    model.param().descr("xArs", "Ars \u6469\u5c14\u5206\u6570");
    model.param().set("wN", "0");
    model.param().descr("wN", "\u7ea6\u5316\u9891\u7387");

    model.component("comp1").physics("be").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec1").set("Filepath", "Ar_xsecs.txt");
    model.component("comp1").physics("be").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("be").feature("bmdl1").set("ne", "1E18[1/m^3]");
    model.component("comp1").physics("be").feature("bmdl1").set("beta", "Beta");
    model.component("comp1").physics("be").feature("bmdl1").set("RateCoefPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("TransportPropPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("ebar", false);
    model.component("comp1").physics("be").feature("init1").set("ebarinit", "5[V]");
    model.component("comp1").physics("be").feature("init1").set("Td0", "10[Td]");

    model.study("std1").label("eedf e-e");
    model.study("std1").feature("ref").set("plist", "10[Td]");
    model.study("std1").feature("ref").set("useparam", true);
    model.study("std1").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std1").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std1").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std1").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std1").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std1").feature("ref").setIndex("plistarr_aux", "0 1e-6 1e-5 1e-4 1e-3 1e-2", 0);
    model.study("std1").feature("ref").set("pcontinuationmode", "last");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset1");
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
    model.result("pg1").run();
    model.result("pg1").label("EEDF e-e");
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 35);
    model.result("pg1").set("ymin", "1e-9");
    model.result("pg1").set("ymax", 1);
    model.result("pg1").run();
    model.result("pg1").feature("lngr1").set("legendmethod", "evaluated");
    model.result("pg1").feature("lngr1").set("legendpattern", "Beta = eval(Beta)");
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("ebar", "MeanEnergies");
    model.study("std2").feature("ebar").set("plotgroup", "Default");
    model.study("std2").feature("ebar").set("ftplistmethod", "manual");
    model.study("std2").feature("ebar").set("solnum", "auto");
    model.study("std2").feature("ebar").set("notsolnum", "auto");
    model.study("std2").feature("ebar").set("outputmap", new String[]{});
    model.study("std2").feature("ebar").setSolveFor("/physics/be", true);
    model.study("std2").feature("ebar").set("plist", "range(2,0.2,10)");
    model.study("std2").label("Rion e-e");
    model.study("std2").setGenPlots(false);
    model.study("std2").setGenConv(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "Beta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "Beta", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0 1e-6 1e-5 1e-4 1e-3 1e-2", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol3");
    model.sol("sol3").study("std2");
    model.sol("sol3").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol3");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u7535\u79bb vs. \u7535\u5b50\u5e73\u5747\u80fd\u91cf e-e");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf (eV)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u7535\u79bb\u7387\u7cfb\u6570 (m<sup>3</sup>/s)");
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 2);
    model.result("pg2").set("xmax", 10);
    model.result("pg2").set("ymin", "1e-21");
    model.result("pg2").set("ymax", "1e-14");
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "be.k_4", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "\u901f\u7387\u7cfb\u6570", 0);
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();

    model.component("comp1").physics("be").prop("EEDFSettings").set("eecolls", false);
    model.component("comp1").physics("be").feature("bmdl1").set("MoleConstrainedSpecies", "Ar");
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", "xArs", 1, 0);

    model.study().create("std3");
    model.study("std3").create("ref", "ReducedElectricFields");
    model.study("std3").feature("ref").set("plotgroup", "Default");
    model.study("std3").feature("ref").set("ftplistmethod", "manual");
    model.study("std3").feature("ref").set("solnum", "auto");
    model.study("std3").feature("ref").set("notsolnum", "auto");
    model.study("std3").feature("ref").set("outputmap", new String[]{});
    model.study("std3").feature("ref").setSolveFor("/physics/be", true);
    model.study("std3").feature("ref").set("plist", "10[Td]");
    model.study("std3").feature("ref").set("preusesol", "no");
    model.study("std3").feature("ref").set("useparam", true);
    model.study("std3").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std3").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std3").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std3").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std3").feature("ref").setIndex("pname_aux", "xArs", 0);
    model.study("std3").feature("ref").setIndex("plistarr_aux", "0 1e-5 1e-4 1e-3", 0);
    model.study("std3").setGenConv(false);
    model.study("std3").setGenPlots(false);
    model.study("std3").label("eedf xArs");
    model.study("std3").createAutoSequences("all");

    model.sol("sol10").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("EEDF xArs");
    model.result("pg3").set("data", "dset4");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("legendpattern", "xArs= eval(xArs)");
    model.result("pg3").run();

    model.study().create("std4");
    model.study("std4").create("ebar", "MeanEnergies");
    model.study("std4").feature("ebar").set("plotgroup", "Default");
    model.study("std4").feature("ebar").set("ftplistmethod", "manual");
    model.study("std4").feature("ebar").set("solnum", "auto");
    model.study("std4").feature("ebar").set("notsolnum", "auto");
    model.study("std4").feature("ebar").set("outputmap", new String[]{});
    model.study("std4").feature("ebar").setSolveFor("/physics/be", true);
    model.study("std4").feature("ebar").set("plist", "range(2,0.2,10)");
    model.study("std4").label("Rion xArs");
    model.study("std4").setGenPlots(false);
    model.study("std4").setGenConv(false);
    model.study("std4").create("param", "Parametric");
    model.study("std4").feature("param").setIndex("pname", "Beta", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("pname", "Beta", 0);
    model.study("std4").feature("param").setIndex("plistarr", "", 0);
    model.study("std4").feature("param").setIndex("punit", "", 0);
    model.study("std4").feature("param").setIndex("pname", "xArs", 0);
    model.study("std4").feature("param").setIndex("plistarr", "0 1e-5 1e-4 1e-3", 0);
    model.study("std4").createAutoSequences("all");

    model.sol().create("sol12");
    model.sol("sol12").study("std4");
    model.sol("sol12").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol12");
    model.batch("p2").run("compute");

    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u79bb vs. \u7535\u5b50\u5e73\u5747\u80fd\u91cf xArs");
    model.result("pg4").set("data", "dset6");
    model.result("pg4").run();
    model.result("pg4").run();

    model.component("comp1").physics("be").prop("EEDFSettings").set("oscillating", true);
    model.component("comp1").physics("be").prop("EEDFSettings").set("omegaN", "wN");

    model.study().create("std5");
    model.study("std5").create("ref", "ReducedElectricFields");
    model.study("std5").feature("ref").set("plotgroup", "Default");
    model.study("std5").feature("ref").set("ftplistmethod", "manual");
    model.study("std5").feature("ref").set("solnum", "auto");
    model.study("std5").feature("ref").set("notsolnum", "auto");
    model.study("std5").feature("ref").set("outputmap", new String[]{});
    model.study("std5").feature("ref").setSolveFor("/physics/be", true);
    model.study("std5").feature("ref").set("plist", "0.76[Td]");
    model.study("std5").label("0.76Td w/N=0");
    model.study("std5").setGenPlots(false);
    model.study("std5").setGenConv(false);
    model.study("std5").createAutoSequences("all");

    model.sol("sol17").runAll();

    model.study().create("std6");
    model.study("std6").create("ref", "ReducedElectricFields");
    model.study("std6").feature("ref").set("plotgroup", "Default");
    model.study("std6").feature("ref").set("ftplistmethod", "manual");
    model.study("std6").feature("ref").set("solnum", "auto");
    model.study("std6").feature("ref").set("notsolnum", "auto");
    model.study("std6").feature("ref").set("outputmap", new String[]{});
    model.study("std6").feature("ref").setSolveFor("/physics/be", true);
    model.study("std6").feature("ref").set("plist", "3.83[Td]");
    model.study("std6").feature("ref").set("useparam", true);
    model.study("std6").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std6").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std6").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std6").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std6").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std6").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std6").feature("ref").setIndex("pname_aux", "wN", 0);
    model.study("std6").feature("ref").setIndex("plistarr_aux", "2e-13", 0);
    model.study("std6").label("3.83Td w/N=2e-13");
    model.study("std6").setGenPlots(false);
    model.study("std6").setGenConv(false);
    model.study("std6").createAutoSequences("all");

    model.sol("sol18").runAll();

    model.study().create("std7");
    model.study("std7").create("ref", "ReducedElectricFields");
    model.study("std7").feature("ref").set("plotgroup", "Default");
    model.study("std7").feature("ref").set("ftplistmethod", "manual");
    model.study("std7").feature("ref").set("solnum", "auto");
    model.study("std7").feature("ref").set("notsolnum", "auto");
    model.study("std7").feature("ref").set("outputmap", new String[]{});
    model.study("std7").feature("ref").setSolveFor("/physics/be", true);
    model.study("std7").feature("ref").set("plist", "69.8[Td]");
    model.study("std7").feature("ref").set("useparam", true);
    model.study("std7").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std7").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std7").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std7").feature("ref").setIndex("pname_aux", "Beta", 0);
    model.study("std7").feature("ref").setIndex("plistarr_aux", "", 0);
    model.study("std7").feature("ref").setIndex("punit_aux", "", 0);
    model.study("std7").feature("ref").setIndex("pname_aux", "wN", 0);
    model.study("std7").feature("ref").setIndex("plistarr_aux", "1e-12", 0);
    model.study("std7").label("69.8Td w/N=1e-12");
    model.study("std7").setGenPlots(false);
    model.study("std7").setGenConv(false);
    model.study("std7").createAutoSequences("all");

    model.sol("sol19").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset7");
    model.result("pg5").set("xmax", 20);
    model.result("pg5").set("ymin", "1e-8");
    model.result("pg5").set("ymax", 10);
    model.result("pg5").label("EEDF w/N");
    model.result("pg5").run();
    model.result("pg5").feature("lngr1").set("legendpattern", "E/N=eval(be.EN,Td,3) Td, w/N=eval(wN)");
    model.result("pg5").run();
    model.result("pg5").feature().duplicate("lngr2", "lngr1");
    model.result("pg5").run();
    model.result("pg5").feature("lngr2").set("data", "dset8");
    model.result("pg5").feature().duplicate("lngr3", "lngr2");
    model.result("pg5").run();
    model.result("pg5").feature("lngr3").set("data", "dset9");
    model.result("pg5").run();

    model.study().create("std8");
    model.study("std8").create("ref", "ReducedElectricFields");
    model.study("std8").feature("ref").set("plotgroup", "Default");
    model.study("std8").feature("ref").set("ftplistmethod", "manual");
    model.study("std8").feature("ref").set("solnum", "auto");
    model.study("std8").feature("ref").set("notsolnum", "auto");
    model.study("std8").feature("ref").set("outputmap", new String[]{});
    model.study("std8").feature("ref").setSolveFor("/physics/be", true);
    model.study("std8").feature("ref").set("plist", "range(10,2,2500)[Td]");
    model.study("std8").feature("ref").set("preusesol", "yes");
    model.study("std8").label("Rion w/N");
    model.study("std8").setGenPlots(false);
    model.study("std8").setGenConv(false);
    model.study("std8").create("param", "Parametric");
    model.study("std8").feature("param").setIndex("pname", "Beta", 0);
    model.study("std8").feature("param").setIndex("plistarr", "", 0);
    model.study("std8").feature("param").setIndex("punit", "", 0);
    model.study("std8").feature("param").setIndex("pname", "Beta", 0);
    model.study("std8").feature("param").setIndex("plistarr", "", 0);
    model.study("std8").feature("param").setIndex("punit", "", 0);
    model.study("std8").feature("param").setIndex("pname", "wN", 0);
    model.study("std8").feature("param").setIndex("plistarr", "0 1e-13 2e-13 1e-12", 0);
    model.study("std8").createAutoSequences("all");

    model.sol().create("sol21");
    model.sol("sol21").study("std8");
    model.sol("sol21").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol21");
    model.batch("p3").run("compute");

    model.result("pg4").run();
    model.result().duplicate("pg6", "pg4");
    model.result("pg6").run();
    model.result("pg6").label("\u7535\u79bb vs. \u7535\u5b50\u5e73\u5747\u80fd\u91cf w/N");
    model.result("pg6").set("data", "dset11");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("xdata", "expr");
    model.result("pg6").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg6").run();

    model.title("\u6c29\u6c14\u73bb\u5c14\u5179\u66fc\u5206\u6790");

    model
         .description("\u672c\u6559\u7a0b\u7814\u7a76\u6c29\u6c14\u653e\u7535\u65f6\uff0c\u5bf9\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u8bbe\u7f6e\u4e0d\u540c\u7684\u53c2\u6570\u53ca\u901f\u7387\u7cfb\u6570\u4ea7\u751f\u7684\u6548\u679c\u3002");

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

    model.label("boltzmann_argon.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
