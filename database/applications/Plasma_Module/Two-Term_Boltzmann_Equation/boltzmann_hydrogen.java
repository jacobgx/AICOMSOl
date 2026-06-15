/*
 * boltzmann_hydrogen.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 12:07 by COMSOL 6.3.0.290. */
public class boltzmann_hydrogen {

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

    model.param().set("Tgas", "300[K]");
    model.param().descr("Tgas", "\u6c14\u4f53\u6e29\u5ea6");
    model.param().set("p0", "25000[Pa]");
    model.param().descr("p0", "\u6c14\u538b");
    model.param().set("Ngas", "p0/(k_B_const*Tgas)");
    model.param().descr("Ngas", "\u6c14\u4f53\u5bc6\u5ea6");
    model.param().set("f0", "2.45[GHz]");
    model.param().descr("f0", "\u6fc0\u52b1\u9891\u7387");
    model.param().set("w0", "2*pi*f0");
    model.param().descr("w0", "\u89d2\u9891\u7387");
    model.param().set("wsN", "w0/Ngas");
    model.param().descr("wsN", "\u7ea6\u5316\u89d2\u9891\u7387");
    model.param().set("EN", "50[Td]");
    model.param().descr("EN", "\u7ea6\u5316\u7535\u573a");

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedf", "BoltzmannTwoTerm");
    model.component("comp1").physics("be").prop("EEDFSettings").set("oscillating", true);
    model.component("comp1").physics("be").prop("EEDFSettings").set("omegaN", "wsN");
    model.component("comp1").physics("be").prop("EEDFSettings").set("emax", "200[V]");
    model.component("comp1").physics("be").create("xsec1", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec1").set("Filepath", "H2_marques_xsecs.txt");
    model.component("comp1").physics("be").feature("xsec1").runCommand("importData");
    model.component("comp1").physics("be").create("xsec2", "CrossSectionImport", -1);
    model.component("comp1").physics("be").feature("xsec2").set("Filepath", "H_marques_xsecs.txt");
    model.component("comp1").physics("be").feature("xsec2").runCommand("importData");
    model.component("comp1").physics("be").feature("bmdl1").set("Tg", "Tgas");
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", 0.5, 0, 0);
    model.component("comp1").physics("be").feature("bmdl1").setIndex("x", 0.5, 1, 0);
    model.component("comp1").physics("be").feature("bmdl1").set("RateCoefPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("TransportPropPG", false);
    model.component("comp1").physics("be").feature("bmdl1").set("ebar", false);
    model.component("comp1").physics("be").feature("init1").set("ebarinit", "3[V]");
    model.component("comp1").physics("be").feature("init1").set("Td0", "35[Td]");

    model.study("std1").label("\u9ad8\u9891\u6781\u9650\u7a33\u6001");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("ref").set("plist", "35.355[Td] 50[Td]");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("EEDF \u9ad8\u9891\u6781\u9650\u503c vs. EEDF \u65f6\u5747\u503c");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").set("data", "dset1");
    model.result("pg1").feature("lngr1").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").feature("lngr1").setIndex("looplevel", new int[]{1}, 0);
    model.result("pg1").feature("lngr1").selection().all();
    model.result("pg1").feature("lngr1").set("expr", "be.f");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg1").feature("lngr1").set("legend", true);
    model.result("pg1").feature("lngr1").set("legendmethod", "manual");
    model.result("pg1").feature("lngr1").setIndex("legends", "\u9ad8\u9891\u6781\u9650", 0);
    model.result("pg1").feature("lngr1").set("linewidth", 3);
    model.result("pg1").run();
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg1").set("ylog", true);
    model.result("pg1").set("axislimits", true);
    model.result("pg1").set("xmin", 0);
    model.result("pg1").set("xmax", 20);
    model.result("pg1").set("ymin", "1e-7");
    model.result("pg1").set("ymax", 1);
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/be", true);
    model.study("std2").feature("time").set("tlist", "range(0,(5/f0-0)/4,5/f0) range(5/f0,(6/f0-(5/f0))/24,6/f0)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("solnum", "from_list");
    model.study("std2").feature("time").set("listsolnum", new int[]{2});
    model.study("std2").setGenPlots(false);

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedfTemporalBehavior", "TimeDependentEEDF");
    model.component("comp1").physics("be").feature("bmdl1").set("pA", "p0");
    model.component("comp1").physics("be").feature("bmdl1").set("Erd", "EN*cos(2*pi*f0*t)");

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.study("std2").label("\u77ac\u6001");

    model.result("pg1").run();
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2").set("data", "dset2");
    model.result("pg1").feature("lngr2").setIndex("looplevelinput", "manual", 0);
    model.result("pg1").feature("lngr2").setIndex("looplevel", new int[]{7, 13}, 0);
    model.result("pg1").feature("lngr2").selection().all();
    model.result("pg1").feature("lngr2").set("expr", "be.f");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg1").feature("lngr2").set("linestyle", "dashed");
    model.result("pg1").feature("lngr2").set("linecolor", "black");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("lngr3", "LineGraph");
    model.result("pg1").feature("lngr3").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr3").set("linewidth", "preference");
    model.result("pg1").feature("lngr3").set("data", "dset2");
    model.result("pg1").feature("lngr3").setIndex("looplevelinput", "last", 0);
    model.result("pg1").feature("lngr3").selection().all();
    model.result("pg1").feature("lngr3").set("expr", "timeavg(5/f0, 6/f0, be.f)");
    model.result("pg1").feature("lngr3").set("xdata", "expr");
    model.result("pg1").feature("lngr3").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg1").feature("lngr3").set("linewidth", 3);
    model.result("pg1").feature("lngr3").set("legend", true);
    model.result("pg1").feature("lngr3").set("legendmethod", "manual");
    model.result("pg1").feature("lngr3").setIndex("legends", "\u65f6\u5747", 0);
    model.result("pg1").run();

    model.component("comp1").physics("be").prop("EEDFSettings").set("eedfTemporalBehavior", "StationaryEEDF");

    model.study().create("std3");
    model.study("std3").create("ref", "ReducedElectricFields");
    model.study("std3").feature("ref").set("plotgroup", "Default");
    model.study("std3").feature("ref").set("ftplistmethod", "manual");
    model.study("std3").feature("ref").set("solnum", "auto");
    model.study("std3").feature("ref").set("notsolnum", "auto");
    model.study("std3").feature("ref").set("outputmap", new String[]{});
    model.study("std3").feature("ref").setSolveFor("/physics/be", true);
    model.study("std3").feature("ref").set("plist", "range(10,10,500)[Td]");
    model.study("std3").setGenPlots(false);
    model.study("std3").label("\u9ad8\u9891\u6781\u9650\u53c2\u6570\u5316");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("EEDF");
    model.result("pg2").set("data", "dset3");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1").set("expr", "be.f");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "xe_be*root.comp1.be.emax");
    model.result("pg2").run();
    model.result("pg2").set("titletype", "none");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "\u80fd\u91cf (eV)");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "EEDF (eV<sup>-3/2</sup>)");
    model.result("pg2").set("ylog", true);
    model.result("pg2").set("axislimits", true);
    model.result("pg2").set("xmin", 0);
    model.result("pg2").set("xmax", 200);
    model.result("pg2").set("ymin", "1e-7");
    model.result("pg2").set("ymax", 2);
    model.result("pg2").set("showlegends", false);
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u7535\u5b50\u5e73\u5747\u80fd\u91cf vs. E/N");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "be.ebar", 0);
    model.result("pg3").feature("glob1").set("xdataparamunit", "Td");
    model.result("pg3").run();
    model.result("pg3").set("titletype", "none");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "E/N (Td)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf (eV)");
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7535\u5b50\u8fc1\u79fb\u7387 vs. \u7535\u5b50\u5e73\u5747\u80fd\u91cf");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "be.muN", 0);
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg4").run();
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf (eV)");
    model.result("pg4").set("axislimits", true);
    model.result("pg4").set("xmin", 0);
    model.result("pg4").set("xmax", 19);
    model.result("pg4").set("ymin", 0);
    model.result("pg4").set("ymax", "2e24");
    model.result("pg4").set("showlegends", false);
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u7535\u79bb\u7387\u5e38\u6570 vs. \u7535\u5b50\u5e73\u5747\u80fd\u91cf");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").set("titletype", "none");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_21", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_22", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "be.k_29", 2);
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "be.ebar");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u7535\u5b50\u5e73\u5747\u80fd\u91cf (eV)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u901f\u7387\u5e38\u6570 (m<sup>3</sup>/s)");
    model.result("pg5").set("ylog", true);
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg4").run();
    model.result("pg5").run();

    model.title("\u6c22\u73bb\u5c14\u5179\u66fc\u5206\u6790");

    model
         .description("\u672c\u6a21\u578b\u5728\u5206\u5b50\u548c\u539f\u5b50\u6c22\u7684\u80cc\u666f\u4e0b\u901a\u8fc7\u4e24\u79cd\u8fd1\u4f3c\u65b9\u6cd5\u6c42\u89e3\u73bb\u5c14\u5179\u66fc\u65b9\u7a0b\uff0c\u5e76\u901a\u8fc7\u5728\u7535\u5b50\u78b0\u649e\u622a\u9762\u4e0a\u5bf9\u7535\u5b50\u80fd\u91cf\u5206\u5e03\u51fd\u6570\u8fdb\u884c\u9002\u5f53\u79ef\u5206\u6765\u8ba1\u7b97\u7535\u5b50\u8fc1\u79fb\u7387\u548c\u6e90\u9879\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("boltzmann_hydrogen.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
