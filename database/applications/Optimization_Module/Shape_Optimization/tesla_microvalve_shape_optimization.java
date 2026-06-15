/*
 * tesla_microvalve_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:44 by COMSOL 6.3.0.290. */
public class tesla_microvalve_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("spf", "LaminarFlow", "geom1");
    model.component("comp1").physics().create("spf2", "LaminarFlow", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std1").feature("stat").setSolveFor("/physics/spf2", true);

    model.component("comp1").geom("geom1")
         .insertFile("tesla_microvalve_shape_optimization_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.param().label("\u51e0\u4f55\u53c2\u6570");
    model.param().create("par2");
    model.param("par2").set("Re", "100");
    model.param("par2").descr("Re", "\u96f7\u8bfa\u6570");
    model.param("par2").set("mu0", "1e-3[Pa*s]");
    model.param("par2").descr("mu0", "\u52a8\u529b\u9ecf\u5ea6");
    model.param("par2").set("rho0", "1e3[kg/m^3]");
    model.param("par2").descr("rho0", "\u5bc6\u5ea6");
    model.param("par2").set("Uin", "Re*mu0/(rho0*D)");
    model.param("par2").descr("Uin", "\u5e73\u5747\u5165\u53e3\u901f\u5ea6");
    model.param("par2").set("meshsz", "0.01[mm]");
    model.param("par2").descr("meshsz", "\u7f51\u683c\u5927\u5c0f");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("dynamicviscosity", new String[]{"mu0"});

    model.component("comp1").physics("spf").create("iwbc1", "InteriorWallBC", 1);
    model.component("comp1").physics("spf").feature("iwbc1").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf").feature("sym1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf").feature("inl1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("spf").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf").feature("out1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf2").create("iwbc1", "InteriorWallBC", 1);
    model.component("comp1").physics("spf2").feature("iwbc1").selection().named("geom1_unisel1");
    model.component("comp1").physics("spf2").create("sym1", "Symmetry", 1);
    model.component("comp1").physics("spf2").feature("sym1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("spf2").create("inl1", "InletBoundary", 1);
    model.component("comp1").physics("spf2").feature("inl1").selection().named("geom1_boxsel3");
    model.component("comp1").physics("spf2").feature("inl1").set("BoundaryCondition", "FullyDevelopedFlow");
    model.component("comp1").physics("spf2").feature("inl1").set("Uavfdf", "Uin");
    model.component("comp1").physics("spf2").create("out1", "OutletBoundary", 1);
    model.component("comp1").physics("spf2").feature("out1").selection().named("geom1_boxsel2");

    model.component("comp1").mesh("mesh1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size1").selection().geom("geom1", 1);
    model.component("comp1").mesh("mesh1").feature("size1").selection().named("geom1_unisel1");
    model.component("comp1").mesh("mesh1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("size1").set("hmax", "meshsz/2");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", "meshsz");
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", "meshsz/2");
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop1").selection().named("geom1_boxsel2");
    model.component("comp1").cpl().create("aveop2", "Average");
    model.component("comp1").cpl("aveop2").set("axisym", true);
    model.component("comp1").cpl("aveop2").selection().geom("geom1", 1);
    model.component("comp1").cpl("aveop2").selection().named("geom1_boxsel3");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Di", "(aveop2(p2)-aveop1(p2))/(aveop1(p)-aveop2(p))");
    model.component("comp1").variable("var1").descr("Di", "\u538b\u5dee\u6bd4");

    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u901f\u5ea6 (spf)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("showsolutionparams", "on");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u538b\u529b (spf)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("con1", "Contour");
    model.result("pg2").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("expr", "p");
    model.result("pg2").feature("con1").set("number", 40);
    model.result("pg2").feature("con1").set("levelrounding", false);
    model.result("pg2").feature("con1").set("smooth", "internal");
    model.result("pg2").feature("con1").set("showsolutionparams", "on");
    model.result("pg2").feature("con1").set("data", "parent");
    model.result().dataset("dset1").set("geom", "geom1");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u901f\u5ea6 (spf2)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").label("\u8868\u9762");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("expr", "spf2.U");
    model.result("pg3").feature("surf1").set("smooth", "internal");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").label("\u538b\u529b (spf2)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").feature().create("con1", "Contour");
    model.result("pg4").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("expr", "p2");
    model.result("pg4").feature("con1").set("number", 40);
    model.result("pg4").feature("con1").set("levelrounding", false);
    model.result("pg4").feature("con1").set("smooth", "internal");
    model.result("pg4").feature("con1").set("showsolutionparams", "on");
    model.result("pg4").feature("con1").set("data", "parent");
    model.result("pg1").run();

    model.nodeGroup().create("grp1", "Results");
    model.nodeGroup("grp1").set("type", "plotgroup");
    model.nodeGroup("grp1").add("plotgroup", "pg1");
    model.nodeGroup("grp1").add("plotgroup", "pg2");
    model.nodeGroup("grp1").add("plotgroup", "pg3");
    model.nodeGroup("grp1").add("plotgroup", "pg4");
    model.nodeGroup("grp1").label("\u521d\u59cb\u8bbe\u8ba1");

    model.result("pg3").run();
    model.result("pg3").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "Di", 0);
    model.result().numerical("gev1").setIndex("descr", "", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common().create("poly1", "PolynomialBoundary");
    model.component("comp1").common("poly1").selection().named("geom1_unisel1");
    model.component("comp1").common("poly1").set("maximumDisplacement", "L3/2");
    model.component("comp1").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp1").common("fsr1").selection().named("geom1_unisel2");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std2").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std2").label("\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u901f\u5ea6 (spf) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").label("\u8868\u9762");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("smooth", "internal");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u538b\u529b (spf) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").feature().create("con1", "Contour");
    model.result("pg6").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("expr", "p");
    model.result("pg6").feature("con1").set("number", 40);
    model.result("pg6").feature("con1").set("levelrounding", false);
    model.result("pg6").feature("con1").set("smooth", "internal");
    model.result("pg6").feature("con1").set("showsolutionparams", "on");
    model.result("pg6").feature("con1").set("data", "parent");
    model.result().dataset("dset2").set("geom", "geom1");
    model.result().create("pg7", "PlotGroup2D");
    model.result("pg7").label("\u901f\u5ea6 (spf2) 1");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").set("frametype", "spatial");
    model.result("pg7").feature().create("surf1", "Surface");
    model.result("pg7").feature("surf1").label("\u8868\u9762");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("expr", "spf2.U");
    model.result("pg7").feature("surf1").set("smooth", "internal");
    model.result("pg7").feature("surf1").set("showsolutionparams", "on");
    model.result("pg7").feature("surf1").set("data", "parent");
    model.result().create("pg8", "PlotGroup2D");
    model.result("pg8").label("\u538b\u529b (spf2) 1");
    model.result("pg8").set("data", "dset2");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").feature().create("con1", "Contour");
    model.result("pg8").feature("con1").label("\u7b49\u503c\u7ebf");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("expr", "p2");
    model.result("pg8").feature("con1").set("number", 40);
    model.result("pg8").feature("con1").set("levelrounding", false);
    model.result("pg8").feature("con1").set("smooth", "internal");
    model.result("pg8").feature("con1").set("showsolutionparams", "on");
    model.result("pg8").feature("con1").set("data", "parent");
    model.result().create("pg9", "PlotGroup2D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg9").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg9").set("data", "dset2");
    model.result("pg9").set("frametype", "geometry");
    model.result("pg9").set("edgecolor", "gray");
    model.result("pg9").set("titletype", "none");
    model.result("pg9").create("line1", "Line");
    model.result("pg9").feature("line1").set("expr", "1");
    model.result("pg9").feature("line1").set("coloring", "uniform");
    model.result("pg9").feature("line1").set("color", "fromtheme");
    model.result("pg9").create("arwl1", "ArrowLine");
    model.result("pg9").feature("arwl1").set("data", "dset2g1");
    model.result("pg9").feature("arwl1").set("expr", new String[]{"fsd1.dXg", "fsd1.dYg"});
    model.result("pg9").feature("arwl1").set("scaleactive", true);
    model.result("pg9").feature("arwl1").set("scale", "1");
    model.result("pg9").feature("arwl1").set("placement", "uniform");
    model.result("pg9").feature("arwl1").set("arrowcount", 200);
    model.result("pg9").feature("arwl1").create("col1", "Color");
    model.result("pg9").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg9").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg9").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg9").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg9").feature("arwl1").create("sel1", "Selection");
    model.result("pg9").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg5").run();

    model.nodeGroup().create("grp2", "Results");
    model.nodeGroup("grp2").set("type", "plotgroup");
    model.nodeGroup().move("grp2", 1);
    model.nodeGroup("grp2").add("plotgroup", "pg5");
    model.nodeGroup("grp2").add("plotgroup", "pg6");
    model.nodeGroup("grp2").add("plotgroup", "pg7");
    model.nodeGroup("grp2").add("plotgroup", "pg8");
    model.nodeGroup("grp2").label("\u5df2\u4f18\u5316");

    model.result("pg7").run();
    model.result("pg7").create("line1", "Line");
    model.result("pg7").feature("line1").set("coloring", "uniform");
    model.result("pg7").feature("line1").set("color", "green");
    model.result("pg7").feature("line1").create("sel1", "Selection");
    model.result("pg7").feature("line1").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg7").run();
    model.result("pg7").create("line2", "Line");
    model.result("pg7").feature("line2").set("data", "dset1");
    model.result("pg7").feature("line2").set("coloring", "uniform");
    model.result("pg7").feature("line2").create("sel1", "Selection");
    model.result("pg7").feature("line2").feature("sel1").selection().named("geom1_unisel1");
    model.result("pg7").run();
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").set("elemcolor", "none");
    model.result("pg7").feature("mesh1").create("def1", "Deform");
    model.result("pg7").run();
    model.result("pg7").feature("mesh1").feature("def1").set("expr", new String[]{"0", "-2*y"});
    model.result("pg7").feature("mesh1").feature("def1").set("scaleactive", true);
    model.result("pg7").feature("mesh1").feature("def1").set("scale", 1);

    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 20);
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.Di"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u538b\u5dee\u6bd4"});
    model.study("std2").feature("sho").setIndex("optobj", "if(Re==100,comp1.Di,0)", 0);
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg7");
    model.study("std2").feature("stat").set("useparam", true);
    model.study("std2").feature("stat").setIndex("pname", "D", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "D", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "", 0);
    model.study("std2").feature("stat").setIndex("punit", "m", 0);
    model.study("std2").feature("stat").setIndex("pname", "Re", 0);
    model.study("std2").feature("stat").setIndex("plistarr", "50 100", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg5").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg7").run();
    model.result("pg7").run();
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 0);
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl3");
    model.result().numerical("gev1").setResult();
    model.result().dataset("dset2").createDeformedConfig("deform1", "mesh2");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/spf", true);
    model.study("std3").feature("stat").setSolveFor("/physics/spf2", true);
    model.study("std3").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std3").feature("stat").set("usesol", true);
    model.study("std3").feature("stat").set("notsolmethod", "sol");
    model.study("std3").feature("stat").set("notstudy", "std2");
    model.study("std3").feature("stat").set("useparam", true);
    model.study("std3").feature("stat").setIndex("pname", "D", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "D", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "", 0);
    model.study("std3").feature("stat").setIndex("punit", "m", 0);
    model.study("std3").feature("stat").setIndex("pname", "Re", 0);
    model.study("std3").feature("stat").setIndex("plistarr", "range(50,25,200)", 0);
    model.study("std3").label("\u9a8c\u8bc1");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "D", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "D", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "m", 0);
    model.study("std3").feature("param").setIndex("pname", "meshsz", 0);
    model.study("std3").feature("param").setIndex("plistarr", "0.02 0.014 0.01", 0);
    model.study("std3").feature("param").setIndex("punit", "mm", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol4");
    model.sol("sol4").study("std3");
    model.sol("sol4").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol4");
    model.batch("p1").run("compute");

    model.result("pg7").run();
    model.result().duplicate("pg10", "pg7");

    model.nodeGroup("grp2").add("plotgroup", "pg10");

    model.result("pg10").run();
    model.result("pg10").label("\u9a8c\u8bc1");
    model.result("pg10").set("data", "dset4");
    model.result("pg10").setIndex("looplevel", 3, 0);
    model.result("pg10").run();

    model.nodeGroup("grp2").remove("plotgroup", "pg10", false);

    model.result("pg10").run();
    model.result().numerical("gev1").set("data", "dset4");
    model.result().numerical("gev1").setIndex("looplevelinput", "last", 1);
    model.result().numerical("gev1").setIndex("looplevelinput", "manualindices", 0);
    model.result().numerical("gev1").setIndex("looplevelindices", 3, 0);
    model.result().table().create("tbl4", "Table");
    model.result().table("tbl4").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl4");
    model.result().numerical("gev1").setResult();
    model.result().create("pg11", "PlotGroup1D");
    model.result("pg11").run();
    model.result("pg11").label("\u538b\u5dee\u6bd4 vs. \u96f7\u8bfa\u6570");
    model.result("pg11").set("ylabelactive", true);
    model.result("pg11").set("ylabel", "\u538b\u5dee\u6bd4");
    model.result("pg11").set("legendpos", "upperleft");
    model.result("pg11").create("glob1", "Global");
    model.result("pg11").feature("glob1").set("markerpos", "datapoints");
    model.result("pg11").feature("glob1").set("linewidth", "preference");
    model.result("pg11").feature("glob1").set("data", "dset4");
    model.result("pg11").feature("glob1").setIndex("expr", "Di", 0);
    model.result("pg11").feature("glob1").setIndex("descr", "", 0);
    model.result("pg11").feature("glob1").set("xdatasolnumtype", "level1");
    model.result("pg11").run();
    model.result("pg11").feature("glob1").set("linewidth", 2);
    model.result("pg11").feature("glob1").set("linemarker", "square");
    model.result("pg11").run();
    model.result("pg9").run();
    model.result("pg9").feature("arwl1").set("arrowcount", 50);
    model.result("pg9").run();

    model.title("Tesla \u5fae\u9600\u7684\u5f62\u72b6\u4f18\u5316");

    model
         .description("Tesla \u5fae\u9600\u7684\u62d3\u6251\u4f18\u5316\u662f\u6211\u4eec\u5efa\u7acb\u521d\u59cb\u51e0\u4f55\u6a21\u578b\u7684\u7075\u611f\u6765\u6e90\u3002\u672c\u4f8b\u4f7f\u7528\u4e8c\u9636\u4f2f\u6069\u65af\u5766\u591a\u9879\u5f0f\u6765\u63cf\u8ff0\u51e0\u4f55\u5f62\u72b6\u7684\u6270\u52a8\uff0c\u5728\u53d8\u5f62\u6784\u578b\u4e2d\u5bf9\u6700\u7ec8\u8bbe\u8ba1\u91cd\u65b0\u5212\u5206\u7f51\u683c\uff0c\u5e76\u5728\u4e00\u5b9a\u7684\u96f7\u8bfa\u6570\u8303\u56f4\u5185\u7814\u7a76\u5176\u6027\u80fd\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("tesla_microvalve_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
