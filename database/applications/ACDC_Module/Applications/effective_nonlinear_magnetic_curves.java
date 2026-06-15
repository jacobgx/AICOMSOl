/*
 * effective_nonlinear_magnetic_curves.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class effective_nonlinear_magnetic_curves {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model.modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Applications");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 1);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("c", "CoefficientFormPDE", "geom1");
    model.component("comp1").physics("c").prop("EquationForm").set("form", "Automatic");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/c", true);

    model.param().set("Bmax", "2.4");
    model.param().descr("Bmax", "Maximum value of the B field in the curve");
    model.param().set("Hmax", "347507.836");
    model.param().descr("Hmax", "Maximum value of the H field in the curve");
    model.param().set("f0", "1[Hz]");
    model.param().descr("f0", "Dummy frequency used for averaging");
    model.param().set("T", "1/f0");
    model.param().descr("T", "Dummy period used for averaging");
    model.param().set("tol", "1e-5");
    model.param().descr("tol", "Integration tolerance");

    model.func().create("int1", "Interpolation");
    model.func("int1")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1"}, 
         {"1067.5", "1.1"}, 
         {"1705.23", "1.2"}, 
         {"2463.11", "1.3"}, 
         {"3841.67", "1.4"}, 
         {"5425.74", "1.5"}, 
         {"7957.75", "1.6"}, 
         {"12298.3", "1.7"}, 
         {"20462.8", "1.8"}, 
         {"32169.6", "1.9"}, 
         {"61213.4", "2"}, 
         {"111408", "2.1"}, 
         {"188487.757", "2.2"}, 
         {"267930.364", "2.3"}, 
         {"347507.836", "2.4"}});
    model.func("int1").label("Imported BH Curve");
    model.func("int1").set("funcname", "BH");
    model.func("int1").setIndex("fununit", "T", 0);
    model.func("int1").setIndex("argunit", "A/m", 0);
    model.func().create("an1", "Analytic");
    model.func("an1").label("Time-Harmonic H Field");
    model.func("an1").set("funcname", "H");
    model.func("an1").set("expr", "Amp*sin(2*pi*f0*t)");
    model.func("an1").set("args", "Amp, t");
    model.func("an1").setIndex("argunit", "A/m", 0);
    model.func("an1").setIndex("argunit", "s", 1);
    model.func("an1").set("fununit", "A/m");

    model.component("comp1").label("B Integration Geometry");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").set("Hamp", "1[A/m^2]*x");
    model.component("comp1").variable("var1")
         .descr("Hamp", "Interpretation of the spatial dimension as amplitude of the time-harmonic H field.");
    model.component("comp1").variable("var1").set("B_eff_SE", "if(Hamp==0,0,comp1.intBdH*2/(Hamp))");
    model.component("comp1").variable("var1")
         .descr("B_eff_SE", "Effective B curve computed using the Simple Energy method");
    model.component("comp1").variable("var1")
         .set("B_eff_AE", "if(Hamp==0,0,16/(Hamp*T)*integrate(integrate(BH(hf),hf,H(Hamp,0),H(Hamp,t),tol),t,0,T/4,tol))");
    model.component("comp1").variable("var1")
         .descr("B_eff_AE", "Effective B curve computed using the Average Energy method");

    model.component("comp1").geom("geom1").create("i1", "Interval");
    model.component("comp1").geom("geom1").feature("i1").setIndex("coord", "Hmax", 1);
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("c").prop("Units").set("CustomDependentVariableUnit", "1");
    model.component("comp1").physics("c").prop("Units").set("DependentVariableQuantity", "none");
    model.component("comp1").physics("c").prop("Units").setIndex("CustomDependentVariableUnit", "T*A/m", 0, 0);
    model.component("comp1").physics("c").prop("Units").setIndex("CustomSourceTermUnit", "T", 0, 0);
    model.component("comp1").physics("c").field("dimensionless").field("intBdH");
    model.component("comp1").physics("c").field("dimensionless").component(1, "intBdH");
    model.component("comp1").physics("c").feature("cfeq1").setIndex("c", 0, 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("f", "BH(Hamp)", 0);
    model.component("comp1").physics("c").feature("cfeq1").setIndex("be", 1, 0);
    model.component("comp1").physics("c").create("dir1", "DirichletBoundary", 0);
    model.component("comp1").physics("c").feature("dir1").selection().set(1);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature().move("dis1", 1);
    model.component("comp1").mesh("mesh1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("dis1").set("type", "explicit");
    model.component("comp1").mesh("mesh1").feature("dis1")
         .set("explicit", "0, 663.146, 1067.5, 1705.23, 2463.11, 3841.67, 5425.74, 7957.75, 12298.3, 20462.8, 32169.6, 61213.4, 111408, 188487.757, 267930.364, 347507.836");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").showAutoSequences("all");

    model.result().dataset().create("cpt1", "CutPoint1D");
    model.result().dataset("cpt1").label("Cut Point H Samples");
    model.result().dataset("cpt1")
         .set("pointx", "0, 663.146, 1067.5, 1705.23, 2463.11, 3841.67, 5425.74, 7957.75, 12298.3, 20462.8, 32169.6, 61213.4, 111408, 188487.757, 267930.364, 347507.836");
    model.result().dataset().create("grid1", "Grid1D");
    model.result().dataset("grid1").set("source", "data");
    model.result().dataset("grid1").label("BH Function Grid");
    model.result().dataset("grid1").set("source", "function");
    model.result().dataset("grid1").set("function", "all");
    model.result().dataset("grid1").set("parmax1", "Hmax");
    model.result().dataset().create("cpt2", "CutPoint1D");
    model.result().dataset("cpt2").label("Cut Point BH Function Grid");
    model.result().dataset("cpt2").set("data", "grid1");
    model.result().dataset("cpt2")
         .set("pointx", "0, 663.146, 1067.5, 1705.23, 2463.11, 3841.67, 5425.74, 7957.75, 12298.3, 20462.8, 32169.6, 61213.4, 111408, 188487.757, 267930.364, 347507.836");
    model.result().dataset().create("cpt3", "CutPoint1D");
    model.result().dataset("cpt3").label("Sampling Point for Time Representation");
    model.result().dataset("cpt3").set("pointx", 347507.836);
    model.result().dataset().create("cpt4", "CutPoint1D");
    model.result().dataset("cpt4").label("Cut Point for Indicator Evaluation");
    model.result().dataset("cpt4").set("pointx", 0);
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").label("BH Curve, SE");
    model.result().numerical("pev1").set("data", "cpt1");
    model.result().numerical("pev1").setIndex("expr", "B_eff_SE", 0);
    model.result().numerical().duplicate("pev2", "pev1");
    model.result().numerical("pev2").label("BH Curve, AE");
    model.result().numerical("pev2").setIndex("expr", "B_eff_AE", 0);
    model.result().numerical().create("pev3", "EvalPoint");
    model.result().numerical("pev3").label("Sampling for Time Representation");
    model.result().numerical("pev3").set("data", "cpt3");
    model.result().numerical("pev3").setIndex("expr", "B_eff_SE", 0);
    model.result().numerical("pev3").setIndex("expr", "B_eff_AE", 1);
    model.result().numerical().create("pev4", "EvalPoint");
    model.result().numerical("pev4").label("Sampling for Indicator (BH Plot)");
    model.result().numerical("pev4").set("data", "cpt4");
    model.result().numerical("pev4").setIndex("expr", "BH(x)", 0);
    model.result().numerical("pev4").setIndex("descr", "", 0);
    model.result().numerical("pev4").setIndex("expr", "B_eff_SE", 1);
    model.result().numerical("pev4").setIndex("expr", "B_eff_AE", 2);
    model.result().numerical().create("pev5", "EvalPoint");
    model.result().numerical("pev5").label("Sampling for Indicator (mur Plot)");
    model.result().numerical("pev5").set("data", "cpt4");
    model.result().numerical("pev5").setIndex("expr", "BH(x)/x/mu0_const", 0);
    model.result().numerical("pev5").setIndex("expr", "B_eff_SE/x/mu0_const", 1);
    model.result().numerical("pev5").setIndex("expr", "B_eff_AE/x/mu0_const", 2);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("Indicator Evaluation (BH Plot)");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("Indicator Evaluation (mur Plot)");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").label("Magnetic Flux Density vs. Magnetic Field (BH)");
    model.result("pg1").set("data", "none");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1").set("title", "Magnetic flux density vs. Magnetic field norm; <b>B</b>(<b>H</b>) curve");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "Magnetic field norm <b>H</b> (kA/m)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "Magnetic flux density <b>B</b> (T)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("lngr1", "LineGraph");
    model.result("pg1").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr1").set("linewidth", "preference");
    model.result("pg1").feature("lngr1").label("Original BH Curve");
    model.result("pg1").feature("lngr1").set("data", "grid1");
    model.result("pg1").feature("lngr1").set("expr", "BH(x)");
    model.result("pg1").feature("lngr1").set("xdata", "expr");
    model.result("pg1").feature("lngr1").set("xdataexpr", "x/1000");
    model.result("pg1").feature("lngr1").set("linecolor", "custom");
    model.result("pg1").feature("lngr1")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").run();
    model.result("pg1").create("ptgr1", "PointGraph");
    model.result("pg1").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr1").set("linewidth", "preference");
    model.result("pg1").feature("ptgr1").label("Original BH, point");
    model.result("pg1").feature("ptgr1").set("data", "cpt2");
    model.result("pg1").feature("ptgr1").set("expr", "BH(x)");
    model.result("pg1").feature("ptgr1").set("xdata", "expr");
    model.result("pg1").feature("ptgr1").set("xdataexpr", "x/1000");
    model.result("pg1").feature("ptgr1").set("linemarker", "point");
    model.result("pg1").feature("ptgr1").set("linecolor", "custom");
    model.result("pg1").feature("ptgr1")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg1").run();
    model.result("pg1").create("lngr2", "LineGraph");
    model.result("pg1").feature("lngr2").set("markerpos", "datapoints");
    model.result("pg1").feature("lngr2").set("linewidth", "preference");
    model.result("pg1").feature("lngr2").set("data", "dset1");
    model.result("pg1").feature("lngr2").selection().all();
    model.result("pg1").feature("lngr2").set("expr", "B_eff_SE");
    model.result("pg1").feature("lngr2").set("xdata", "expr");
    model.result("pg1").feature("lngr2").set("xdataexpr", "x/1000");
    model.result("pg1").feature("lngr2").set("linecolor", "green");
    model.result("pg1").feature("lngr2").label("BH, Simple Energy");
    model.result("pg1").run();
    model.result("pg1").create("ptgr2", "PointGraph");
    model.result("pg1").feature("ptgr2").set("markerpos", "datapoints");
    model.result("pg1").feature("ptgr2").set("linewidth", "preference");
    model.result("pg1").feature("ptgr2").label("BH, Simple Energy, Point");
    model.result("pg1").feature("ptgr2").set("data", "cpt1");
    model.result("pg1").feature("ptgr2").set("expr", "B_eff_SE");
    model.result("pg1").feature("ptgr2").set("xdata", "expr");
    model.result("pg1").feature("ptgr2").set("xdataexpr", "x/1000");
    model.result("pg1").feature("ptgr2").set("linemarker", "point");
    model.result("pg1").feature("ptgr2").set("linecolor", "green");
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").active(false);
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("lngr3", "lngr2");
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").label("BH, Average Energy");
    model.result("pg1").feature("lngr3").set("expr", "B_eff_AE");
    model.result("pg1").feature("lngr3").set("linecolor", "red");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("ptgr3", "ptgr2");
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").label("BH, Average Energy, Point");
    model.result("pg1").feature("ptgr3").set("linecolor", "red");
    model.result("pg1").feature("ptgr3").set("expr", "B_eff_AE");
    model.result("pg1").run();
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").label("Indicator");
    model.result("pg1").feature("tblp1").set("linemarker", "cycle");
    model.result("pg1").feature("tblp1").set("linecolor", "blue");
    model.result("pg1").feature("tblp1").active(false);
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("data", "grid1");
    model.result("pg1").feature("ann1").set("latexmarkup", true);
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("color", "blue");
    model.result("pg1").feature("ann1").active(false);
    model.result().create("pg2", "PlotGroup1D");
    model.result("pg2").run();
    model.result("pg2").label("Time Representation");
    model.result("pg2").set("data", "grid1");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "Half Cycle Time Response");
    model.result("pg2").set("xlabelactive", true);
    model.result("pg2").set("xlabel", "Time");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("xlabel", "Time (Cycles)");
    model.result("pg2").set("ylabel", "Magnetic flux density <b>B</b> (T)");
    model.result("pg2").create("lngr1", "LineGraph");
    model.result("pg2").feature("lngr1").set("markerpos", "datapoints");
    model.result("pg2").feature("lngr1").set("linewidth", "preference");
    model.result("pg2").feature("lngr1").label("Exact");
    model.result("pg2").feature("lngr1").set("expr", "BH(347507.836*sin(pi*x/Hmax))");
    model.result("pg2").feature("lngr1").set("xdata", "expr");
    model.result("pg2").feature("lngr1").set("xdataexpr", "x/Hmax/2");
    model.result("pg2").feature("lngr1").set("linecolor", "custom");
    model.result("pg2").feature("lngr1")
         .set("customlinecolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg2").feature("lngr1").set("linemarker", "point");
    model.result("pg2").feature("lngr1").set("markerpos", "interp");
    model.result("pg2").feature("lngr1").set("markers", 19);
    model.result("pg2").feature("lngr1").set("legend", true);
    model.result("pg2").feature("lngr1").set("autoplotlabel", true);
    model.result("pg2").feature("lngr1").set("autosolution", false);
    model.result("pg2").feature().duplicate("lngr2", "lngr1");
    model.result("pg2").run();
    model.result("pg2").feature("lngr2").label("Simple Energy");
    model.result("pg2").feature("lngr2").set("expr", "4.260*sin(pi*x/Hmax)");
    model.result("pg2").feature("lngr2").set("linecolor", "green");
    model.result("pg2").feature("lngr2").active(false);
    model.result("pg2").feature().duplicate("lngr3", "lngr2");
    model.result("pg2").run();
    model.result("pg2").feature("lngr3").label("Average Energy");
    model.result("pg2").feature("lngr3").set("expr", "5.253*sin(pi*x/Hmax)");
    model.result("pg2").feature("lngr3").set("linecolor", "red");
    model.result("pg1").run();
    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("Relative Permeability vs. Magnetic Field");
    model.result("pg3")
         .set("title", "Relative permeability vs. Magnetic field norm; \u03bc<sub>r</sub>(<b>H</b>) curve");
    model.result("pg3").set("ylabel", "Relative permeability \u03bc<sub>r</sub>");
    model.result("pg3").run();
    model.result("pg3").feature("lngr1").set("expr", "BH(x)/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr1").set("expr", "BH(x)/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("lngr2").set("expr", "B_eff_SE/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr2").set("expr", "B_eff_SE/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("lngr3").set("expr", "B_eff_AE/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("ptgr3").set("expr", "B_eff_AE/x/mu0_const");
    model.result("pg3").run();
    model.result("pg3").feature("tblp1").set("table", "tbl2");
    model.result("pg3").run();
    model.result("pg3").feature("ann1").set("showpoint", true);
    model.result("pg3").feature("ann1").set("color", "custom");
    model.result("pg3").feature("ann1")
         .set("customcolor", new double[]{0.21176470816135406, 0.5490196347236633, 0.7960784435272217});
    model.result("pg3").run();
    model.result("pg3").create("ann2", "Annotation");
    model.result("pg3").feature("ann2").set("data", "grid1");
    model.result("pg3").feature("ann2").set("showpoint", false);
    model.result("pg3").feature("ann2").set("latexmarkup", true);
    model.result("pg3").feature("ann2").set("color", "blue");
    model.result("pg3").feature("ann2").active(false);
    model.result("pg2").run();
    model.result().duplicate("pg4", "pg2");
    model.result("pg4").run();
    model.result("pg4").label("Time Representation (mur)");

    model.title(null);

    model.description("");

    model.label("effective_nonlinear_magnetic_curves_embedded.mph");

    model.result("pg4").run();
    model.result("pg4").feature("lngr2").active(true);
    model.result("pg4").feature("lngr3").active(true);
    model.result("pg4").feature("lngr2").active(false);
    model.result("pg4").feature("lngr3").active(false);

    model.setExpectedComputationTime("1 \u79d2");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result("pg1").feature("lngr2").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").active(true);
    model.result("pg1").run();
    model.result("pg1").feature("lngr2").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr2").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("lngr3").active(false);
    model.result("pg1").run();
    model.result("pg1").feature("ptgr3").active(false);

    model.title("\u6709\u6548\u975e\u7ebf\u6027\u78c1\u66f2\u7ebf\u8ba1\u7b97\u5668");

    model
         .description("\u672c App \u6f14\u793a\u4ee5\u4e0b\u5185\u5bb9\uff1a\n\n\u2022 \u4ece\u6587\u672c\u6587\u4ef6\u5bfc\u5165\u6d4b\u91cf\u6570\u636e\n\u2022 \u4f7f\u7528\u65b9\u6cd5\u5904\u7406\u6d4b\u91cf\u7684\u6570\u636e\n\u2022 \u5c06\u7ed3\u679c\u5bfc\u51fa\u81f3\u6587\u672c\u6587\u4ef6\n\u2022 \u5c06\u7ed3\u679c\u5bfc\u51fa\u4e3a COMSOL\u201c\u6750\u6599\u5e93\u201d\u6587\u4ef6\u3002\n\n\u8be5 App \u4e0e\u201c\u6709\u6548\u975e\u7ebf\u6027\u672c\u6784\u5173\u7cfb\u201d\u529f\u80fd\u76f8\u8f85\u76f8\u6210\u3002\u201cAC/DC \u6a21\u5757\u201d\u4e2d\u57fa\u4e8e\u78c1\u573a\u7684\u63a5\u53e3\u652f\u6301\u201c\u6709\u6548 BH \u66f2\u7ebf\u201d\u6750\u6599\u6a21\u578b\uff0c\u53ef\u7528\u4e8e\u5728\u9891\u57df\u4eff\u771f\u4e2d\u8fd1\u4f3c\u6a21\u62df\u975e\u7ebf\u6027\u78c1\u6027\u6750\u6599\u7684\u7279\u6027\uff0c\u4ece\u800c\u907f\u514d\u5168\u77ac\u6001\u4eff\u771f\u5e26\u6765\u7684\u989d\u5916\u8ba1\u7b97\u6210\u672c\u3002\n\n\u201c\u6709\u6548 BH \u66f2\u7ebf\u201d\u6750\u6599\u6a21\u578b\u9700\u8981\u5c06\u6709\u6548 Beff(H) \u5173\u7cfb\u5b9a\u4e49\u4e3a\u63d2\u503c\u51fd\u6570\u3002\u8fd9\u4e2a\u5b9e\u7528\u7a0b\u5e8f App \u53ef\u7528\u4e8e\u8ba1\u7b97\u4ece\u6750\u6599\u7684 B(H) \u5173\u7cfb\u5f00\u59cb\u7684\u63d2\u503c\u6570\u636e\u3002\n\n\u53ef\u4ee5\u4ece\u6587\u672c\u6587\u4ef6\u5bfc\u5165 B(H) \u5173\u7cfb\u7684\u63d2\u503c\u6570\u636e\uff0c\u4e5f\u53ef\u4ee5\u5728\u8868\u683c\u4e2d\u8fdb\u884c\u8f93\u5165\u3002\u7136\u540e\uff0cApp \u4f1a\u4f7f\u7528\u4e24\u79cd\u4e0d\u540c\u7684\u80fd\u91cf\u65b9\u6cd5\u6765\u8ba1\u7b97 Beff(H) \u5173\u7cfb\u7684\u63d2\u503c\u6570\u636e\u3002\u5f97\u5230\u7684\u6709\u6548\u6750\u6599\u5c5e\u6027\u53ef\u4ee5\u5bfc\u51fa\u4e3a COMSOL\u201c\u6750\u6599\u5e93\u201d\u6587\u4ef6\uff0c\u8fdb\u4e00\u6b65\u7528\u4e8e\u5177\u6709\u201c\u78c1\u573a\u201d\u63a5\u53e3\u7684\u6a21\u578b\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("effective_nonlinear_magnetic_curves.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
