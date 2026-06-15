/*
 * cyclic_voltammetry_1d.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:11 by COMSOL 6.3.0.290. */
public class cyclic_voltammetry_1d {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Fuel_Cell_and_Electrolyzer_Module\\General_Electrochemistry");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("tcd", "TertiaryElectroanalysis", "geom1");
    model.component("comp1").physics("tcd").field("concentration").field("cA");
    model.component("comp1").physics("tcd").field("concentration").component(new String[]{"cA", "cB"});

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
    model.param().set("v", "1[V/s]", "\u4f0f\u5b89\u626b\u63cf\u901f\u7387");
    model.param().set("c_bulk", "1[mmol/L]", "\u53cd\u5e94\u7269\u672c\u4f53\u6d53\u5ea6");
    model.param().set("DA", "1e-9[m^2/s]", "\u53cd\u5e94\u7269\u6269\u6563\u7cfb\u6570");
    model.param().set("DB", "1e-9[m^2/s]", "\u4ea7\u7269\u6269\u6563\u7cfb\u6570");
    model.param().set("K0", "1e10", "\u53cd\u5e94\u901f\u7387\uff08\u65e0\u91cf\u7eb2\uff09");
    model.param().set("re", "10[mm]", "\u7535\u6781\u534a\u5f84");
    model.param().set("i0ref", "9.6485e10[A/m^2]", "\u53c2\u8003\u4ea4\u6362\u7535\u6d41\u5bc6\u5ea6");
    model.param().set("Cdl", "0.2[F/m^2]", "\u53cc\u7535\u5c42\u7535\u5bb9");
    model.param().set("T", "298.15[K]", "\u6e29\u5ea6");
    model.param().set("E_vertex1", "-0.4[V]", "\u8d77\u59cb\u7535\u4f4d");
    model.param().set("E_vertex2", "0.4[V]", "\u5207\u6362\u7535\u4f4d");
    model.param()
         .set("L", "6*sqrt(DA*2*abs(E_vertex1-E_vertex2)/v)", "\u6269\u6563\u5c42\u4e0a\u7684\u5916\u8fb9\u754c");
    model.param()
         .set("cB0", "c_bulk/(1+exp(-E_vertex1*F_const/(R_const*T)))", "\u7535\u6781\u7684\u521d\u59cb\u4ea7\u7269\u6d53\u5ea6");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "L", 1);
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cA", new String[]{"DA", "0", "0", "0", "DA", "0", "0", "0", "DA"});
    model.component("comp1").physics("tcd").feature("ice1")
         .set("D_cB", new String[]{"DB", "0", "0", "0", "DB", "0", "0", "0", "DB"});
    model.component("comp1").physics("tcd").create("conc1", "Concentration", 0);
    model.component("comp1").physics("tcd").feature("conc1").selection().set(2);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("c0", "c_bulk", 0);
    model.component("comp1").physics("tcd").feature("conc1").setIndex("species", true, 1);
    model.component("comp1").physics("tcd").create("es1", "ElectrodeSurface", 0);
    model.component("comp1").physics("tcd").feature("es1").selection().set(1);
    model.component("comp1").physics("tcd").feature("es1").set("BoundaryCondition", "CyclicVoltammetry");
    model.component("comp1").physics("tcd").feature("es1").set("sweeprate", "v");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex1", "E_vertex1");
    model.component("comp1").physics("tcd").feature("es1").set("Evertex2", "E_vertex2");
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", 1, 0);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").setIndex("Vi0", -1, 1);
    model.component("comp1").physics("tcd").feature("es1").feature("er1").set("i0_ref", "i0ref");
    model.component("comp1").physics("tcd").feature("es1").create("dlc1", "DoubleLayerCapacitance", 0);
    model.component("comp1").physics("tcd").feature("es1").feature("dlc1").set("Cdl", "Cdl");
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "c_bulk-cB0*(1-x/L)", 0);
    model.component("comp1").physics("tcd").feature("init1").setIndex("initc", "cB0*(1-x/L)", 1);

    model.common("cminpt").set("modified", new String[][]{{"temperature", "T"}});

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "v", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V/s", 0);
    model.study("std1").feature("param").setIndex("pname", "v", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "V/s", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^range(-3,1,0)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("glob1", "Global");
    model.result("pg1").feature("glob1").set("unit", new String[]{""});
    model.result("pg1").feature("glob1").set("expr", new String[]{"tcd.Itot_es1 "});
    model.result("pg1").feature("glob1").set("descr", new String[]{""});
    model.result("pg1").label("\u5faa\u73af\u4f0f\u5b89\u56fe (tcd)");
    model.result("pg1").feature("glob1").set("titletype", "none");
    model.result("pg1").feature("glob1").set("xdata", "expr");
    model.result("pg1").feature("glob1").set("xdataexpr", "root.comp1.tcd.phis_es1 ");
    model.result("pg1").feature("glob1").set("xdatadescr", "\u7535\u52bf");
    model.result("pg1").feature("glob1").setIndex("descr", "\u603b\u7535\u6d41", 0);
    model.result("pg1").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("unit", new String[]{""});
    model.result("pg2").feature("glob1").set("expr", new String[]{"tcd.phis_es1 "});
    model.result("pg2").feature("glob1").set("descr", new String[]{""});
    model.result("pg2").label("\u7535\u6781\u7535\u4f4d (tcd)");
    model.result("pg2").feature("glob1").setIndex("descr", "\u7535\u52bf", 0);
    model.result("pg2").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("unit", new String[]{""});
    model.result("pg3").feature("glob1").set("expr", new String[]{"tcd.itotavg_es1 "});
    model.result("pg3").feature("glob1").set("descr", new String[]{""});
    model.result("pg3").label("\u5e73\u5747\u7535\u6d41\u5bc6\u5ea6 (tcd)");
    model.result("pg3").feature("glob1").setIndex("descr", "\u7535\u6d41\u5bc6\u5ea6", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level1");
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28");
    model.result("pg4").label("\u6d53\u5ea6\uff0c\u6240\u6709\u7269\u8d28 (tcd)");
    model.result("pg4").create("lngr1", "LineGraph");
    model.result("pg4").feature("lngr1").set("xdata", "expr");
    model.result("pg4").feature("lngr1").set("xdataexpr", "x");
    model.result("pg4").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr1").selection().set(1);
    model.result("pg4").feature("lngr1").set("expr", new String[]{"cA"});
    model.result("pg4").feature("lngr1").label("\u7269\u8d28 A");
    model.result("pg4").feature("lngr1").set("legend", true);
    model.result("pg4").feature("lngr1").set("autosolution", false);
    model.result("pg4").feature("lngr1").set("autoexpr", false);
    model.result("pg4").feature("lngr1").set("autodescr", false);
    model.result("pg4").feature("lngr1").set("legendprefix", "A ");
    model.result("pg4").feature("lngr1").set("descractive", true);
    model.result("pg4").feature("lngr1").set("descr", "\u6d53\u5ea6");
    model.result("pg4").create("lngr2", "LineGraph");
    model.result("pg4").feature("lngr2").set("xdata", "expr");
    model.result("pg4").feature("lngr2").set("xdataexpr", "x");
    model.result("pg4").feature("lngr2").selection().geom("geom1", 1);
    model.result("pg4").feature("lngr2").selection().set(1);
    model.result("pg4").feature("lngr2").set("expr", new String[]{"cB"});
    model.result("pg4").feature("lngr2").label("\u7269\u8d28 B");
    model.result("pg4").feature("lngr2").set("legend", true);
    model.result("pg4").feature("lngr2").set("autosolution", false);
    model.result("pg4").feature("lngr2").set("autoexpr", false);
    model.result("pg4").feature("lngr2").set("autodescr", false);
    model.result("pg4").feature("lngr2").set("legendprefix", "B ");
    model.result("pg4").feature("lngr2").set("descractive", true);
    model.result("pg4").feature("lngr2").set("descr", "\u6d53\u5ea6");
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").label("\u6d53\u5ea6, A (tcd)");
    model.result("pg5").set("titletype", "custom");
    model.result("pg5").set("prefixintitle", "");
    model.result("pg5").set("expressionintitle", false);
    model.result("pg5").set("typeintitle", false);
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("xdata", "expr");
    model.result("pg5").feature("lngr1").set("xdataexpr", "x");
    model.result("pg5").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg5").feature("lngr1").selection().set(1);
    model.result("pg5").feature("lngr1").set("expr", new String[]{"cA"});
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").label("\u6d53\u5ea6, B (tcd)");
    model.result("pg6").set("titletype", "custom");
    model.result("pg6").set("prefixintitle", "");
    model.result("pg6").set("expressionintitle", false);
    model.result("pg6").set("typeintitle", false);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("xdata", "expr");
    model.result("pg6").feature("lngr1").set("xdataexpr", "x");
    model.result("pg6").feature("lngr1").selection().geom("geom1", 1);
    model.result("pg6").feature("lngr1").selection().set(1);
    model.result("pg6").feature("lngr1").set("expr", new String[]{"cB"});
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").run();
    model.result("pg1").feature("glob1").set("autodescr", false);
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevelinput", "first", 1);
    model.result("pg2").set("titletype", "none");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").set("autodescr", false);
    model.result("pg2").run();

    model.title("\u5927\u7535\u6781\u4e0a\u7684\u5faa\u73af\u4f0f\u5b89\u6cd5 - \u4e00\u7ef4");

    model
         .description("\u672c\u4f8b\u6a21\u62df\u6beb\u7c73\u5c3a\u5ea6\u7535\u6781\u7684\u5faa\u73af\u4f0f\u5b89\u6cd5\u8fc7\u7a0b\u3002\u5728\u8fd9\u79cd\u5e38\u89c1\u7684\u7535\u5316\u5b66\u5206\u6790\u6280\u672f\u4e2d\uff0c\u5bf9\u5de5\u4f5c\u7535\u6781\u7684\u7535\u4f4d\u6765\u56de\u626b\u63cf\uff0c\u5e76\u8bb0\u5f55\u76f8\u5e94\u7684\u7535\u6d41\u53d8\u5316\u3002\u7535\u6d41-\u7535\u538b\u6ce2\u5f62\uff08\u4f0f\u5b89\u56fe\uff09\u63d0\u4f9b\u4e86\u6709\u5173\u5206\u6790\u7269\u7684\u53cd\u5e94\u6027\u548c\u8d28\u91cf\u4f20\u9012\u5c5e\u6027\u7684\u4fe1\u606f\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();

    model.label("cyclic_voltammetry_1d.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
