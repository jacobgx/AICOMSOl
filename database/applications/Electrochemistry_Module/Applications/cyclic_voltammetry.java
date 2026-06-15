/*
 * cyclic_voltammetry.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 09:52 by COMSOL 6.3.0.290. */
public class cyclic_voltammetry {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Electrochemistry_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");

    model.study().create("std1");
    model.study("std1").create("cyclv", "CyclicVoltammetry");
    model.study("std1").feature("cyclv").set("ftplistmethod", "manual");
    model.study("std1").feature("cyclv").set("initialtime", "0");
    model.study("std1").feature("cyclv").set("solnum", "auto");
    model.study("std1").feature("cyclv").set("notsolnum", "auto");
    model.study("std1").feature("cyclv").set("outputmap", new String[]{});
    model.study("std1").feature("cyclv").setSolveFor("/physics/tcd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("v", "0.1[V/s]", "Voltammetric scan rate");
    model.param().set("c_bulk", "1.0[mmol/L]", "Reactant bulk concentration");
    model.param().set("DA", "1.0e-9[m^2/s]", "Reactant diffusion coefficient");
    model.param().set("DB", "1.0e-9[m^2/s]", "Product diffusion coefficient");
    model.param().set("k0", "i0/F_const", "Reaction rate");
    model.param().set("Cdl", "0.2[F/m^2]", "Double layer capacity");
    model.param().set("T", "298.15[K]", "Temperature");
    model.param().set("E_vertex1", "-0.5[V]", "Start potential");
    model.param().set("E_vertex2", "0.5[V]", "Switching potential");
    model.param().set("L", "6*sqrt(DA*2*abs(E_vertex1-E_vertex2)/v)", "Outer bound on diffusion layer");
    model.param().set("c_bulk_p", "0[mmol/L]", "Product bulk concentration");
    model.param().set("n_scp", "3", "Number of scans before measurement");
    model.param().set("n_sc", "1", "Number of scans, measurement");
    model.param().set("i0", "10[A/m^2]");
    model.param().set("beta_a", "0.5[1]", "Anodic transfer coefficient");
    model.param().set("beta_c", "0.5[1]", "Cathodic transfer coefficient");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("tcd").field("concentration").component(1, "cA");
    model.component("comp1").physics("tcd").field("concentration").component(2, "cB");
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cB", new String[]{"DB", "0", "0", "0", "DB", "0", "0", "0", "DB"});
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk_p", 1);
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk_p", 1);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "CyclicVoltammetry");
    model.component("comp1").physics("tcd").feature("es1").set("sweeprate", "v");
    model.component("comp1").physics("tcd").feature("es1").set("ncycle", "n_scp");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex1", "E_vertex1");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex2", "E_vertex2");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "1[mol/m^3]", 0, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("cref", "1[mol/m^3]", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("alphaa", "beta_a");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").create("dlc1", "DoubleLayerCapacitance", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("dlc1").set("Cdl", "Cdl");
    model.component("comp1").physics("tcd").feature().duplicate("es2", "es1");
    model.component("comp1").physics("tcd").feature("es2").set("ncycle", "n_sc");

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").feature("cyclv").set("useadvanceddisable", true);
    model.study("std1").feature("cyclv").set("disabledphysics", new String[]{"tcd/es2"});
    model.study("std1").feature("cyclv").set("usertol", true);
    model.study("std1").feature("cyclv").set("rtol", "1e-7");
    model.study("std1").create("cyclv2", "CyclicVoltammetry");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t1").setEntry("atolmethod", "comp1_cA", "scaled");
    model.sol("sol1").feature("t1").setEntry("atolvaluemethod", "comp1_cA", "manual");
    model.sol("sol1").feature("t1").setEntry("atol", "comp1_cA", "1e-4");

    model.study("std1").feature("cyclv2").set("usertol", true);
    model.study("std1").feature("cyclv2").set("rtol", "1e-7");
    model.study("std1").feature("cyclv2").set("useadvanceddisable", true);
    model.study("std1").feature("cyclv2").set("disabledphysics", new String[]{"tcd/es1"});
    model.study("std1").feature("cyclv2").set("usesol", true);

    model.sol("sol1").feature("t2").set("atolglobalvaluemethod", "manual");
    model.sol("sol1").feature("t2").setEntry("atolmethod", "comp1_cA", "scaled");
    model.sol("sol1").feature("t2").setEntry("atolvaluemethod", "comp1_cA", "manual");
    model.sol("sol1").feature("t2").setEntry("atol", "comp1_cA", "1e-4");

    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("Cyclic Voltammograms");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("markerpos", "datapoints");
    model.result("pg1").feature("glob1").set("linewidth", "preference");
    model.result("pg1").feature("glob1").set("expr", new String[]{"tcd.Itot_es2"});
    model.result("pg1").feature("glob1").set("descr", new String[]{"Total current"});
    model.result("pg1").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg1").feature("glob1").setIndex("expr", "tcd.itotavg_es2", 0);
    model.result("pg1").feature("glob1").setIndex("descr", "Current density", 0);
    model.result("pg1").feature("glob1").set("xdataexpr", "tcd.phis_es2");
    model.result("pg1").feature("glob1").set("xdatadescr", "Electric potential");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Cyclic Voltammograms, Sample Preparations");
    model.result("pg2").set("titletype", "label");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").set("data", "dset2");
    model.result("pg2").feature("glob1").setIndex("expr", "tcd.itotavg_es1", 0);
    model.result("pg2").feature("glob1").setIndex("descr", "Current density", 0);
    model.result("pg2").feature("glob1").set("xdataexpr", "tcd.phis_es1");
    model.result("pg2").feature("glob1").set("xdatadescr", "Electric potential");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").create("col1", "Color");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("col1").set("expr", "tcd.cycle_es1");
    model.result("pg2").feature("glob1").feature("col1").set("descr", "Cycle number");
    model.result("pg2").feature("glob1").feature("col1").set("expr", "t");
    model.result("pg2").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(1);
    model.result().numerical("pev1").setIndex("looplevelinput", "last", 0);
    model.result().numerical("pev1").setIndex("expr", "timemax(0,(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA)", 0);
    model.result().numerical("pev1").setIndex("descr", "Maximum current density", 0);
    model.result().numerical().duplicate("pev2", "pev1");
    model.result().numerical("pev2")
         .setIndex("expr", "attimemax(0,(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA)", 0);
    model.result().numerical("pev2").setIndex("unit", "s", 0);
    model.result().numerical("pev2").setIndex("descr", "Time at maximum current density", 0);
    model.result().numerical().duplicate("pev3", "pev2");
    model.result().numerical("pev3")
         .setIndex("expr", "timemin(0,1.9*(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA )", 0);
    model.result().numerical("pev3").setIndex("descr", "Minimum current density", 0);
    model.result().numerical().duplicate("pev4", "pev3");
    model.result().numerical("pev4")
         .setIndex("expr", "attimemin(0,1.9*(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA,t )", 0);
    model.result().numerical("pev4").setIndex("descr", "Time at minimum current density", 0);
    model.result().numerical().duplicate("pev5", "pev4");
    model.result().numerical("pev5")
         .setIndex("expr", "attimemax(0,(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA,tcd.Ect)", 0);
    model.result().numerical("pev5").setIndex("descr", "Electrode potential at maximum current", 0);
    model.result().numerical().duplicate("pev6", "pev5");
    model.result().numerical("pev6")
         .setIndex("expr", "attimemin(0,1.9*(E_vertex2-E_vertex1)/v,F_const*tcd.ndflux_cA,tcd.Ect )", 0);
    model.result().numerical("pev6").setIndex("descr", "Electrode potential at minimum current", 0);

    model.title(null);

    model.description("");

    model.label("cyclic_voltammetry_embedded.mph");

    model.setExpectedComputationTime("7 \u79d2");

    model.result().report().create("rpt1", "Report");
    model.result().report("rpt1").set("format", "docx");
    model.result().report("rpt1").set("filename", "user:///cyclicvoltammetry1d.docx");
    model.result().report("rpt1").set("imagesize", "large");
    model.result().report("rpt1").feature().create("tp1", "TitlePage");
    model.result().report("rpt1").feature("tp1")
         .set("title", "\u5b8f\u7535\u6781\u5faa\u73af\u4f0f\u5b89\u6cd5\u5206\u6790 - \u4e00\u7ef4");
    model.result().report("rpt1").feature("tp1").set("titleimage", "none");
    model.result().report("rpt1").feature("tp1").set("includeauthor", false);
    model.result().report("rpt1").feature("tp1")
         .set("summary", "\u672c\u4f8b\u6a21\u62df\u5728\u6beb\u7c73\u7ea7\u7535\u6781\u4e0a\u8fdb\u884c\u7684\u5faa\u73af\u4f0f\u5b89\u6cd5\u8bd5\u9a8c\u3002\u5728\u8fd9\u79cd\u5e38\u89c1\u7684\u7535\u5316\u5b66\u5206\u6790\u6280\u672f\u4e2d\uff0c\u5bf9\u5de5\u4f5c\u7535\u6781\u7684\u7535\u4f4d\u6765\u56de\u626b\u63cf\uff0c\u540c\u65f6\u8bb0\u5f55\u7535\u6d41\u7684\u53d8\u5316\u3002\u901a\u8fc7\u7535\u6d41-\u7535\u538b\u6ce2\u5f62\uff08\u4f0f\u5b89\u56fe\uff09\uff0c\u53ef\u4ee5\u83b7\u53d6\u5173\u4e8e\u5206\u6790\u7269\u7684\u53cd\u5e94\u6027\u548c\u8d28\u91cf\u4f20\u9012\u5c5e\u6027\u7684\u4fe1\u606f\u3002");
    model.result().report("rpt1").feature("tp1").set("includeacknowledgment", false);
    model.result().report("rpt1").feature().create("toc1", "TableOfContents");
    model.result().report("rpt1").feature("toc1").label("\u76ee\u5f55");
    model.result().report("rpt1").feature().create("sec1", "Section");
    model.result().report("rpt1").feature("sec1").label("\u8f6f\u4ef6\u4fe1\u606f");
    model.result().report("rpt1").feature("sec1").feature().create("root1", "Model");
    model.result().report("rpt1").feature("sec1").feature("root1").label("\u5173\u4e8e\u8f6f\u4ef6");
    model.result().report("rpt1").feature("sec1").feature("root1").set("includeunitsystem", true);
    model.result().report("rpt1").feature().create("sec2", "Section");
    model.result().report("rpt1").feature("sec2").label("\u6a21\u578b\u53c2\u6570");
    model.result().report("rpt1").feature("sec2").feature().create("param1", "Parameter");
    model.result().report("rpt1").feature().create("sec3", "Section");
    model.result().report("rpt1").feature("sec3").label("\u7ed3\u679c");
    model.result().report("rpt1").feature("sec3").feature().create("pg1", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg1").label("\u5faa\u73af\u4f0f\u5b89\u56fe");
    model.result().report("rpt1").feature("sec3").feature().create("pg2", "PlotGroup");
    model.result().report("rpt1").feature("sec3").feature("pg2").set("noderef", "pg2");
    model.result().report("rpt1").feature("sec3").feature("pg2")
         .label("\u5faa\u73af\u4f0f\u5b89\u56fe\uff0c\u6837\u54c1\u51c6\u5907");
    model.result().report("rpt1").feature("sec3").feature().create("field1", "DoubleDataField");

    model.title("\u5faa\u73af\u4f0f\u5b89\u6cd5");

    model
         .description("\u672c App \u7684\u76ee\u7684\u662f\u6f14\u793a\u548c\u6a21\u62df\u5faa\u73af\u4f0f\u5b89\u6cd5\u7684\u4f7f\u7528\u3002\u60a8\u53ef\u4ee5\u66f4\u6539\u4e24\u79cd\u7269\u8d28\u7684\u672c\u4f53\u6d53\u5ea6\u3001\u4f20\u9012\u5c5e\u6027\u3001\u52a8\u529b\u5b66\u53c2\u6570\u4ee5\u53ca\u5faa\u73af\u7535\u538b\u7a97\u53e3\u548c\u626b\u63cf\u901f\u7387\u3002\n\n\u5faa\u73af\u4f0f\u5b89\u6cd5\u662f\u7814\u7a76\u7535\u5316\u5b66\u7cfb\u7edf\u7684\u4e00\u79cd\u5e38\u7528\u5206\u6790\u6280\u672f\u3002\u4ece\u65f6\u95f4\u4e0a\u770b\uff0c\u8be5\u65b9\u6cd5\u5728\u8d77\u59cb\u7535\u4f4d\u4e0e\u9876\u7535\u4f4d\u4e4b\u95f4\u6765\u56de\u5bf9\u5de5\u4f5c\u7535\u6781\u548c\u53c2\u6bd4\u7535\u6781\u4e4b\u95f4\u7684\u7535\u4f4d\u5dee\u8fdb\u884c\u7ebf\u6027\u626b\u63cf\u3002\u7535\u6d41-\u7535\u538b\u6ce2\u5f62\u79f0\u4e3a\u4f0f\u5b89\u56fe\uff0c\u53ef\u4ee5\u63d0\u4f9b\u6709\u5173\u7535\u89e3\u8d28\u7684\u53cd\u5e94\u6027\u548c\u8d28\u91cf\u4f20\u9012\u5c5e\u6027\u7684\u4fe1\u606f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("cyclic_voltammetry.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
