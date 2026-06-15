/*
 * localized_heat_source.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:37 by COMSOL 6.3.0.290. */
public class localized_heat_source {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Heat_Transfer_Module\\Verification_Examples");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/ht", true);

    model.param().set("R_disk", "0.1[m]");
    model.param().descr("R_disk", "\u5706\u76d8\u534a\u5f84");
    model.param().set("R_source", "0.01[m]");
    model.param().descr("R_source", "\u70ed\u6e90\u534a\u5f84");
    model.param().set("T0", "300[K]");
    model.param().descr("T0", "\u5706\u76d8\u7684\u8fb9\u754c\u6e29\u5ea6");
    model.param().set("k_cork", "0.042[W/(m*K)]");
    model.param().descr("k_cork", "\u70ed\u5bfc\u7387\uff0c\u8f6f\u6728");
    model.param().set("cp_cork", "1.88[kJ/(kg*K)]");
    model.param().descr("cp_cork", "\u6052\u538b\u70ed\u5bb9\uff0c\u8f6f\u6728");
    model.param().set("rho_cork", "150[kg/m^3]");
    model.param().descr("rho_cork", "\u5bc6\u5ea6\uff0c\u8f6f\u6728");
    model.param().set("mh", "0.01[m]");
    model.param().descr("mh", "\u7f51\u683c\u5927\u5c0f\u53c2\u6570");

    model.func().create("an1", "Analytic");
    model.func("an1").set("expr", "-1/(2*pi*k_cork)*log(sqrt(x^2+y^2)/R_disk) + T0");
    model.func("an1").set("args", "x,y");
    model.func("an1").setIndex("argunit", "m", 0);
    model.func("an1").set("fununit", "K");
    model.func("an1").setIndex("plotargs", "-R_disk", 0, 1);
    model.func("an1").setIndex("plotargs", "R_disk", 0, 2);
    model.func("an1").setIndex("plotargs", "-R_disk", 1, 1);
    model.func("an1").setIndex("plotargs", "R_disk", 1, 2);
    model.func().create("an2", "Analytic");
    model.func("an2")
         .set("expr", "if(sqrt(x^2+y^2)>R_source, (-1/(2*pi*k_cork)*log(sqrt(x^2+y^2)/R_disk) + T0), (1/(2*pi*k_cork)*(-(x^2+y^2)/(2*R_source^2)+0.5-log(R_source/R_disk)) + T0))");
    model.func("an2").set("args", "x,y");
    model.func("an2").setIndex("plotargs", "-R_disk", 0, 1);
    model.func("an2").setIndex("plotargs", "R_disk", 0, 2);
    model.func("an2").setIndex("plotargs", "-R_disk", 1, 1);
    model.func("an2").setIndex("plotargs", "R_disk", 1, 2);
    model.func("an2").setIndex("argunit", "m", 0);
    model.func("an2").set("fununit", "K");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", "R_disk");
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pt1", "Point");
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u8f6f\u6728");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_cork"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_cork"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", new String[]{"cp_cork"});

    model.component("comp1").physics("ht").create("temp1", "TemperatureBoundary", 1);
    model.component("comp1").physics("ht").feature("temp1").selection().all();
    model.component("comp1").physics("ht").feature("temp1").set("T0", "T0");
    model.component("comp1").physics("ht").create("lihs1", "LineHeatSource", 0);
    model.component("comp1").physics("ht").feature("lihs1").selection().set(3);
    model.component("comp1").physics("ht").feature("lihs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("lihs1").set("Pl", 1);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 0);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hmax", "mh");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();
    model.component("comp1").cpl().create("maxop1", "Maximum");
    model.component("comp1").cpl("maxop1").selection().all();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u70b9\u6e90");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "mh", 0);
    model.study("std1").feature("param").setIndex("plistarr", "10^{range(-6,0.5,-2)}", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u6e29\u5ea6 (ht)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 9, 0);
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("solutionparams", "parent");
    model.result("pg1").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").label("\u6e29\u5ea6 - \u7814\u7a76 1");
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "T-T0");
    model.result("pg1").feature("surf1").create("hght1", "Height");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("\u4e0e\u89e3\u6790\u89e3\u4e4b\u95f4\u7684 L2 \u8bef\u5dee -\u7814\u7a76 1");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").selection().all();
    model.result("pg2").feature("lngr1").set("expr", "sqrt(intop1((T-an1(x,y))^2))/sqrt(intop1((T-T0)^2))");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "1/mh");
    model.result("pg2").feature("lngr1").set("linecolor", "blue");
    model.result("pg2").feature("lngr1").set("linewidth", 5);
    model.result("pg2").run();
    model.result("pg2").set("xlog", true);
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u6700\u9ad8\u6e29\u5ea6 - \u7814\u7a76 1");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("lngr1", "LineGraph");
    model.result("pg3").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg3").feature("lngr1").set("linewidth", "preference");
    model.result("pg3").feature("lngr1").selection().all();
    model.result("pg3").feature("lngr1").set("expr", "maxop1(T)");
    model.result("pg3").feature("lngr1").set("xdata", "expr");
    model.result("pg3").feature("lngr1").set("xdataexpr", "1/mh");
    model.result("pg3").feature("lngr1").set("linecolor", "blue");
    model.result("pg3").feature("lngr1").set("linewidth", 5);
    model.result("pg3").run();
    model.result("pg3").set("xlog", true);

    model.component("comp1").physics("ht").create("lihs2", "LineHeatSource", 0);
    model.component("comp1").physics("ht").feature("lihs2").selection().set(3);
    model.component("comp1").physics("ht").feature("lihs2").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("lihs2").set("Pl", 1);
    model.component("comp1").physics("ht").feature("lihs2").set("specifyHeatSourceRadius", true);
    model.component("comp1").physics("ht").feature("lihs2").set("radius", "R_source");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"ht/lihs2"});
    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"ht/lihs1"});
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5e26\u534a\u5f84\u7684\u70b9\u6e90");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "mh", 0);
    model.study("std2").feature("param").setIndex("plistarr", "10^{range(-6,0.5,-2)}", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol13");
    model.sol("sol13").study("std2");
    model.sol("sol13").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol13");
    model.batch("p2").run("compute");

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u6e29\u5ea6 (ht)");
    model.result("pg4").set("data", "dset4");
    model.result("pg4").setIndex("looplevel", 9, 0);
    model.result("pg4").feature().create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("solutionparams", "parent");
    model.result("pg4").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg4").feature("surf1").set("showsolutionparams", "on");
    model.result("pg4").feature("surf1").set("data", "parent");
    model.result("pg4").run();
    model.result("pg4").label("\u6e29\u5ea6 - \u7814\u7a76 2");
    model.result("pg4").setIndex("looplevel", 7, 0);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "T-T0");
    model.result("pg4").feature("surf1").create("hght1", "Height");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().dataset().create("cln1", "CutLine2D");
    model.result().dataset("cln1").set("data", "dset4");
    model.result().dataset("cln1").setIndex("genpoints", "R_disk", 1, 0);
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u89e3\u6790\u89e3\uff0c\u6709/\u65e0\u534a\u5f84\u7684\u70b9\u6e90");
    model.result("pg5").set("data", "cln1");
    model.result("pg5").setIndex("looplevelinput", "first", 0);
    model.result("pg5").set("titletype", "manual");
    model.result("pg5").set("title", "\u4e0d\u540c\u70b9\u6e90\u7684\u6e29\u5ea6\u6bd4\u8f83");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u6e29\u5ea6 (K)");
    model.result("pg5").create("lngr1", "LineGraph");
    model.result("pg5").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr1").set("linewidth", "preference");
    model.result("pg5").feature("lngr1").set("expr", "an1(x,y)");
    model.result("pg5").feature("lngr1").set("linewidth", 3);
    model.result("pg5").feature("lngr1").set("legend", true);
    model.result("pg5").feature("lngr1").set("legendmethod", "manual");
    model.result("pg5").feature("lngr1").setIndex("legends", "\u65e0\u6307\u5b9a\u534a\u5f84", 0);
    model.result("pg5").run();
    model.result("pg5").create("lngr2", "LineGraph");
    model.result("pg5").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg5").feature("lngr2").set("linewidth", "preference");
    model.result("pg5").feature("lngr2").set("expr", "an2(x,y)");
    model.result("pg5").feature("lngr2").set("linewidth", 3);
    model.result("pg5").feature("lngr2").set("titletype", "none");
    model.result("pg5").feature("lngr2").set("legend", true);
    model.result("pg5").feature("lngr2").set("legendmethod", "manual");
    model.result("pg5").feature("lngr2").setIndex("legends", "\u6709\u6307\u5b9a\u534a\u5f84", 0);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u6e29\u5ea6 vs. \u534a\u5f84 - \u7814\u7a76 2");
    model.result("pg6").set("data", "cln1");
    model.result("pg6").setIndex("looplevelinput", "last", 0);
    model.result("pg6").create("lngr1", "LineGraph");
    model.result("pg6").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr1").set("linewidth", "preference");
    model.result("pg6").feature("lngr1").set("linestyle", "none");
    model.result("pg6").feature("lngr1").set("linemarker", "circle");
    model.result("pg6").feature("lngr1").set("markerpos", "interp");
    model.result("pg6").feature("lngr1").set("markers", 25);
    model.result("pg6").feature("lngr1").set("legend", true);
    model.result("pg6").feature("lngr1").set("legendmethod", "manual");
    model.result("pg6").feature("lngr1").setIndex("legends", "\u6570\u503c\u89e3\uff0cmh = 0.01", 0);
    model.result("pg6").run();
    model.result("pg6").create("lngr2", "LineGraph");
    model.result("pg6").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg6").feature("lngr2").set("linewidth", "preference");
    model.result("pg6").feature("lngr2").set("expr", "an2(x,y)");
    model.result("pg6").feature("lngr2").set("linewidth", 3);
    model.result("pg6").feature("lngr2").set("legend", true);
    model.result("pg6").feature("lngr2").set("legendmethod", "manual");
    model.result("pg6").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u4e0e\u89e3\u6790\u89e3\u4e4b\u95f4\u7684 L2 \u8bef\u5dee - \u7814\u7a76 2");
    model.result("pg7").set("data", "dset4");
    model.result("pg7").create("lngr1", "LineGraph");
    model.result("pg7").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg7").feature("lngr1").set("linewidth", "preference");
    model.result("pg7").feature("lngr1").selection().all();
    model.result("pg7").feature("lngr1").set("expr", "sqrt(intop1((T-an2(x,y))^2))/sqrt(intop1((T-T0)^2))");
    model.result("pg7").feature("lngr1").set("xdata", "expr");
    model.result("pg7").feature("lngr1").set("xdataexpr", "1/mh");
    model.result("pg7").feature("lngr1").set("linecolor", "blue");
    model.result("pg7").feature("lngr1").set("linewidth", 5);
    model.result("pg7").run();
    model.result("pg7").set("xlog", true);
    model.result().dataset().create("cln2", "CutLine2D");
    model.result().dataset("cln2").set("data", "dset2");
    model.result().dataset("cln2").setIndex("genpoints", "R_disk", 1, 0);
    model.result().create("pg8", "PlotGroup1D");
    model.result("pg8").run();
    model.result("pg8")
         .label("\u4e0e\u89e3\u6790\u89e3\u4e4b\u95f4\u7684 L1 \u8bef\u5dee - \u7814\u7a76 1 \u548c\u7814\u7a76 2");
    model.result("pg8").set("data", "cln2");
    model.result("pg8").setIndex("looplevelinput", "last", 0);
    model.result("pg8").create("lngr1", "LineGraph");
    model.result("pg8").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr1").set("linewidth", "preference");
    model.result("pg8").feature("lngr1").set("expr", "abs(T-an2(x,y))");
    model.result("pg8").feature("lngr1").set("linewidth", 3);
    model.result("pg8").feature("lngr1").set("legend", true);
    model.result("pg8").feature("lngr1").set("legendmethod", "manual");
    model.result("pg8").feature("lngr1").setIndex("legends", "\u65e0\u6307\u5b9a\u534a\u5f84\u7684\u70b9\u6e90", 0);
    model.result("pg8").run();
    model.result("pg8").create("lngr2", "LineGraph");
    model.result("pg8").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg8").feature("lngr2").set("linewidth", "preference");
    model.result("pg8").feature("lngr2").set("data", "cln1");
    model.result("pg8").feature("lngr2").setIndex("looplevelinput", "last", 0);
    model.result("pg8").feature("lngr2").set("expr", "abs(T-an2(x,y))");
    model.result("pg8").feature("lngr2").set("titletype", "none");
    model.result("pg8").feature("lngr2").set("linewidth", 3);
    model.result("pg8").feature("lngr2").set("legend", true);
    model.result("pg8").feature("lngr2").set("legendmethod", "manual");
    model.result("pg8").feature("lngr2").setIndex("legends", "\u6709\u6307\u5b9a\u534a\u5f84\u7684\u70b9\u6e90", 0);
    model.result("pg8").run();

    model.component("comp1").mesh().create("mesh2");
    model.component("comp1").mesh("mesh2").automatic(false);
    model.component("comp1").mesh("mesh2").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh2").feature("size").set("hmax", "mh");
    model.component("comp1").mesh("mesh2").feature("size").set("hmin", "mh");
    model.component("comp1").mesh("mesh2").run();

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/ht", true);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"ht/lihs1"});
    model.study("std3")
         .label("\u7814\u7a76 3\uff1a\u6709\u534a\u5f84\u7684\u70b9\u6e90\uff0c\u7c97\u5316\u7f51\u683c");
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "R_disk", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "mh", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0.01,0.012,0.06)", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol24");
    model.sol("sol24").study("std3");
    model.sol("sol24").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol24");
    model.batch("p3").run("compute");

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").label("\u6e29\u5ea6 (ht)");
    model.result("pg9").set("data", "dset6");
    model.result("pg9").setIndex("looplevel", 5, 0);
    model.result("pg9").feature().create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("solutionparams", "parent");
    model.result("pg9").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg9").feature("surf1").set("showsolutionparams", "on");
    model.result("pg9").feature("surf1").set("data", "parent");
    model.result("pg9").run();
    model.result("pg9").label("\u7f51\u683c\u5206\u8fa8\u7387 - \u7814\u7a76 3");
    model.result("pg9").run();
    model.result("pg9").feature("surf1").set("coloring", "uniform");
    model.result("pg9").feature("surf1").set("wireframe", true);
    model.result("pg9").run();
    model.result("pg9").create("con1", "Contour");
    model.result("pg9").feature("con1").set("expr", "sqrt(x^2+y^2)");
    model.result("pg9").feature("con1").set("levelmethod", "levels");
    model.result("pg9").feature("con1").set("levels", "R_source");
    model.result("pg9").feature("con1").set("contourtype", "tubes");
    model.result("pg9").feature("con1").set("coloring", "uniform");
    model.result("pg9").feature("con1").set("color", "blue");
    model.result("pg9").feature("con1").set("colorlegend", false);
    model.result("pg9").run();
    model.result("pg9").run();
    model.result("pg9").setIndex("looplevel", 1, 0);
    model.result("pg9").run();
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").label("\u4e0e\u89e3\u6790\u89e3\u4e4b\u95f4\u7684 L2 \u8bef\u5dee -\u7814\u7a76 3");
    model.result("pg10").set("data", "dset6");
    model.result("pg10").create("lngr1", "LineGraph");
    model.result("pg10").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg10").feature("lngr1").set("linewidth", "preference");
    model.result("pg10").feature("lngr1").selection().all();
    model.result("pg10").feature("lngr1").set("expr", "sqrt(intop1((T-an2(x,y))^2))/sqrt(intop1((T-T0)^2))");
    model.result("pg10").feature("lngr1").set("xdata", "expr");
    model.result("pg10").feature("lngr1").set("xdataexpr", "1/mh");
    model.result("pg10").feature("lngr1").set("linecolor", "blue");
    model.result("pg10").feature("lngr1").set("linewidth", 5);
    model.result("pg10").run();

    model.component().create("comp2", true);

    model.component("comp2").geom().create("geom2", 2);

    model.component("comp2").mesh().create("mesh3");

    model.component("comp2").geom("geom2").create("imp1", "Import");
    model.component("comp2").geom("geom2").feature("imp1").set("type", "sequence");
    model.component("comp2").geom("geom2").feature("imp1").set("sequence", "geom1");
    model.component("comp2").geom("geom2").feature("imp1").importData();
    model.component("comp2").geom("geom2").create("c1", "Circle");
    model.component("comp2").geom("geom2").feature("c1").set("r", "R_source");
    model.component("comp2").geom("geom2").runPre("fin");

    model.component("comp2").physics().create("ht2", "HeatTransfer", "geom2");

    model.study("std1").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std2").feature("stat").setSolveFor("/physics/ht2", false);
    model.study("std3").feature("stat").setSolveFor("/physics/ht2", false);

    model.component("comp2").geom("geom2").run();

    model.component("comp2").physics("ht2").create("temp1", "TemperatureBoundary", 1);
    model.component("comp2").physics("ht2").feature("temp1").selection().set(1, 2, 5, 8);
    model.component("comp2").physics("ht2").feature("temp1").set("T0", "T0");
    model.component("comp2").physics("ht2").create("hs1", "HeatSource", 2);
    model.component("comp2").physics("ht2").feature("hs1").selection().set(2);
    model.component("comp2").physics("ht2").feature("hs1").set("heatSourceType", "HeatRate");
    model.component("comp2").physics("ht2").feature("hs1").set("P0", 1);

    model.component("comp2").material().create("mat2", "Common");
    model.component("comp2").material("mat2").label("\u8f6f\u6728");
    model.component("comp2").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"k_cork"});
    model.component("comp2").material("mat2").propertyGroup("def").set("density", new String[]{"rho_cork"});
    model.component("comp2").material("mat2").propertyGroup("def").set("heatcapacity", new String[]{"cp_cork"});

    model.component("comp2").mesh("mesh3").run();

    model.study().create("std4");
    model.study("std4").create("stat", "Stationary");
    model.study("std4").feature("stat").setSolveFor("/physics/ht", false);
    model.study("std4").feature("stat").setSolveFor("/physics/ht2", true);
    model.study("std4").label("\u7814\u7a76 4\uff1a\u9762\u70ed\u6e90");
    model.study("std4").createAutoSequences("all");

    model.sol("sol30").runAll();

    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u6e29\u5ea6 (ht2)");
    model.result("pg11").set("data", "dset8");
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").run();
    model.result("pg11").label("\u6e29\u5ea6 - \u7814\u7a76 4");
    model.result().dataset().create("cln3", "CutLine2D");
    model.result().dataset("cln3").set("data", "dset8");
    model.result().dataset("cln3").setIndex("genpoints", "R_disk", 1, 0);
    model.result().create("pg12", "PlotGroup1D");
    model.result("pg12").run();
    model.result("pg12").label("\u6e29\u5ea6 vs. \u534a\u5f84 - \u7814\u7a76 4");
    model.result("pg12").set("data", "cln3");
    model.result("pg12").create("lngr1", "LineGraph");
    model.result("pg12").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr1").set("linewidth", "preference");
    model.result("pg12").feature("lngr1").set("linestyle", "none");
    model.result("pg12").feature("lngr1").set("linemarker", "cycle");
    model.result("pg12").feature("lngr1").set("markerpos", "interp");
    model.result("pg12").feature("lngr1").set("markers", 25);
    model.result("pg12").feature("lngr1").set("legend", true);
    model.result("pg12").feature("lngr1").set("legendmethod", "manual");
    model.result("pg12").feature("lngr1").setIndex("legends", "\u6570\u503c\u89e3", 0);
    model.result("pg12").run();
    model.result("pg12").create("lngr2", "LineGraph");
    model.result("pg12").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg12").feature("lngr2").set("linewidth", "preference");
    model.result("pg12").feature("lngr2").set("expr", "an2(x,y)");
    model.result("pg12").feature("lngr2").set("linewidth", 3);
    model.result("pg12").feature("lngr2").set("legend", true);
    model.result("pg12").feature("lngr2").set("legendmethod", "manual");
    model.result("pg12").feature("lngr2").setIndex("legends", "\u89e3\u6790\u89e3", 0);
    model.result("pg12").run();

    model.title("\u76d8\u4e0a\u5c40\u90e8\u70ed\u6e90\u7684\u70ed\u4f20\u5bfc");

    model
         .description("\u8fd9\u4e2a\u70ed\u4f20\u5bfc\u793a\u4f8b\u6f14\u793a\u5982\u4f55\u4f7f\u7528\u201c\u4f20\u70ed\u201d\u63a5\u53e3\u5728\u4e00\u4e2a\u5c0f\u7684\u6c42\u89e3\u57df\u4e2d\u5b9a\u4e49\u5c40\u90e8\u70ed\u6e90\uff0c\u6bd4\u8f83\u4e86\u91c7\u7528\u4e0d\u540c\u7279\u5f81\u8fdb\u884c\u70ed\u6e90\u5efa\u6a21\u7684\u7cbe\u5ea6\u548c\u6570\u503c\u8ba1\u7b97\u6210\u672c\u3002\u4eff\u771f\u7ed3\u679c\u4e3a\u5982\u4f55\u6839\u636e\u6e90\u4e0e\u5468\u56f4\u5178\u578b\u51e0\u4f55\u5c3a\u5bf8\u4e4b\u6bd4\u6765\u9009\u62e9\u5408\u9002\u7684\u9009\u9879\u63d0\u4f9b\u4e86\u6307\u5bfc\u3002");

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

    model.label("localized_heat_source.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
