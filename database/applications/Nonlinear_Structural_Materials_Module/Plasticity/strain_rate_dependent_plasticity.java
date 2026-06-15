/*
 * strain_rate_dependent_plasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:35 by COMSOL 6.3.0.290. */
public class strain_rate_dependent_plasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.component("comp1").multiphysics().create("te1", "ThermalExpansion", 2);
    model.component("comp1").multiphysics("te1").set("Heat_physics", "ht");
    model.component("comp1").multiphysics("te1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("te1").selection().all();

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/solid", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);
    model.study("std1").feature("time").setSolveFor("/multiphysics/te1", true);

    model.param().set("totL", "100[mm]");
    model.param().descr("totL", "\u8bd5\u6837\u603b\u957f");
    model.param().set("avgStrain", "0.1");
    model.param().descr("avgStrain", "\u5e73\u5747\u5e94\u53d8");
    model.param().set("wMax", "avgStrain*totL/2");
    model.param().descr("wMax", "\u5bf9\u79f0\u534a\u8f74\u7684\u6700\u5927\u4f4d\u79fb");
    model.param().set("strainRate", "0.1[1/s]");
    model.param().descr("strainRate", "\u5e94\u53d8\u7387");
    model.param().set("tFinal", "avgStrain/strainRate");
    model.param().descr("tFinal", "\u7ed3\u675f\u65f6\u95f4");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "table");
    model.component("comp1").geom("geom1").feature("pol1").set("type", "open");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1").set("x", "5 5 0  0 10 10 6.01");
    model.component("comp1").geom("geom1").feature("pol1").set("y", "20 0 0 50 50 30 30");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("qb1", "QuadraticBezier");
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 6.01, 0, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 30, 1, 0);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 5.25, 0, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 25, 1, 1);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 5, 0, 2);
    model.component("comp1").geom("geom1").feature("qb1").setIndex("p", 20, 1, 2);
    model.component("comp1").geom("geom1").run("qb1");
    model.component("comp1").geom("geom1").create("csol1", "ConvertToSolid");
    model.component("comp1").geom("geom1").feature("csol1").selection("input").set("pol1", "qb1");
    model.component("comp1").geom("geom1").run("csol1");
    model.component("comp1").geom("geom1").create("fil1", "Fillet");
    model.component("comp1").geom("geom1").feature("fil1").selection("point").set("csol1", 5);
    model.component("comp1").geom("geom1").feature("fil1").set("radius", 4);
    model.component("comp1").geom("geom1").run("fil1");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(3);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "wMax*t/tFinal", 2);
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "JohnsonCook");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").label("\u7ed3\u6784\u94a2");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"200[GPa]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"0.3"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"7850"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"400[MPa]"});
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", new String[]{"200[MPa]"});
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", new String[]{"0.5"});
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", new String[]{"0.12"});
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", new String[]{"0.6"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("heatcapacity", new String[]{"475[J/(kg*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]"});

    model.component("comp1").physics("ht").create("hf1", "HeatFluxBoundary", 1);
    model.component("comp1").physics("ht").feature("hf1").set("HeatFluxType", "ConvectiveHeatFlux");
    model.component("comp1").physics("ht").feature("hf1").set("h", 15);
    model.component("comp1").physics("ht").feature("hf1").selection().set(4, 5, 6, 7, 8);

    model.component("comp1").multiphysics("te1").set("IncludeTed", false);
    model.component("comp1").multiphysics("te1").set("IncludeMechanicalLosses", true);

    model.component("comp1").physics("solid").feature("lemm1").set("CalculateDissipatedEnergy", true);

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1")
         .set("sizeexpr", "if(z<30[mm],1[mm],3[mm])");
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u6709\u70ed\u8f6f\u5316");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "totL", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "totL", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "strainRate", 0);
    model.study("std1").feature("param").setIndex("plistarr", "0.01 0.1 1 10", 0);
    model.study("std1").feature("time")
         .set("tlist", "range(0,0.01*tFinal,0.1*tFinal) range(0.12*tFinal,0.02*tFinal,tFinal)");
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("t1").set("tstepsbdf", "strict");

    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 56, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2solidrev");
    model.result("pg2").setIndex("looplevel", 56, 0);
    model.result("pg2").setIndex("looplevel", 4, 1);
    model.result("pg2").label("\u5e94\u529b, 3D (solid)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "Rainbow");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").set("colortable", "Prism");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 56, 0);
    model.result("pg3").setIndex("looplevel", 4, 1);
    model.result("pg3").set("dataisaxisym", "off");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result().dataset().create("mir1", "Mirror3D");
    model.result().dataset("mir1").set("quickplane", "xy");
    model.result("pg2").run();
    model.result("pg2").set("data", "mir1");
    model.result("pg2").run();
    model.result("pg2").feature("surf1").set("unit", "MPa");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u5e94\u529b vs. \u5e94\u53d8");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(1);
    model.result("pg4").feature("ptgr1").set("expr", "solid.sz");
    model.result("pg4").feature("ptgr1").set("unit", "MPa");
    model.result("pg4").feature("ptgr1").set("xdatasolnumtype", "inner");
    model.result("pg4").feature("ptgr1").set("xdata", "expr");
    model.result("pg4").feature("ptgr1").set("xdataexpr", "solid.eZZ");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("autopoint", false);
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u8f74\u5411\u5e94\u53d8");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u8f74\u5411\u5e94\u529b (MPa)");
    model.result("pg4").set("legendpos", "lowerright");
    model.result("pg4").run();

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().geom("geom1", 1);
    model.component("comp1").cpl("intop1").selection().set(3);
    model.component("comp1").cpl("intop1").set("method", "summation");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Fz", "intop1(solid.RFz)");
    model.component("comp1").variable("var1").descr("Fz", "\u8f74\u5411\u529b");

    model.sol("sol1").updateSolution();
    model.sol("sol2").updateSolution();

    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u529b vs. \u4f4d\u79fb");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("legendpos", "lowerright");
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "Fz", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "kN", 0);
    model.result("pg5").feature("glob1").set("xdatasolnumtype", "inner");
    model.result("pg5").feature("glob1").set("xdata", "expr");
    model.result("pg5").feature("glob1").set("xdataexpr", "wMax*t/tFinal");
    model.result("pg5").feature("glob1").set("linewidth", 2);
    model.result("pg5").feature("glob1").set("xdatadescractive", true);
    model.result("pg5").feature("glob1").set("xdatadescr", "\u6269\u5c55");
    model.result("pg5").feature("glob1").set("autodescr", false);
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").set("titletype", "none");
    model.result("pg5").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").setSolveFor("/physics/ht", true);
    model.study("std2").feature("time").setSolveFor("/multiphysics/te1", true);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u65e0\u70ed\u8f6f\u5316");
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "totL", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "totL", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "m", 0);
    model.study("std2").feature("param").setIndex("pname", "strainRate", 0);
    model.study("std2").feature("param").setIndex("plistarr", "0.01 0.1 1 10", 0);
    model.study("std2").feature("time")
         .set("tlist", "range(0,0.01*tFinal,0.1*tFinal) range(0.12*tFinal,0.02*tFinal,tFinal)");

    model.component("comp1").physics("solid").feature("lemm1").feature().duplicate("plsty2", "plsty1");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("ThermalSoftening", "NoThermalSoftening");

    model.study("std1").feature("time").set("useadvanceddisable", true);
    model.study("std1").feature("time").set("disabledphysics", new String[]{"solid/lemm1/plsty2"});
    model.study("std2").showAutoSequences("all");

    model.sol("sol7").feature("t1").set("tstepsbdf", "strict");

    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol8");
    model.sol("sol8").study("std2");
    model.sol("sol8").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol8");
    model.batch("p2").run("compute");

    model.result("pg4").run();
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("data", "dset4");
    model.result("pg4").feature("ptgr2").set("linecolor", "cyclereset");
    model.result("pg4").feature("ptgr2").set("linemarker", "cycle");
    model.result("pg4").feature("ptgr2").set("markerpos", "interp");
    model.result("pg4").feature("ptgr2").set("legendsuffix", "\uff0c\u65e0\u6e29\u5ea6\u8f6f\u5316");
    model.result("pg4").run();
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 56, 0);
    model.result("pg6").setIndex("looplevel", 4, 1);
    model.result("pg6").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg6").create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("expr", new String[]{"solid.epeGp"});
    model.result("pg6").feature("surf1").set("inheritplot", "none");
    model.result("pg6").feature("surf1").set("resolution", "normal");
    model.result("pg6").feature("surf1").set("colortabletype", "discrete");
    model.result("pg6").feature("surf1").set("bandcount", 11);
    model.result("pg6").feature("surf1").set("colortable", "AuroraAustralisDark");
    model.result("pg6").feature("surf1").set("descractive", true);
    model.result("pg6").feature("surf1").set("descr", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg6").label("\u7b49\u6548\u5851\u6027\u5e94\u53d8 (solid)");
    model.result("pg6").run();
    model.result("pg6").set("looplevel", new int[]{56, 1});
    model.result("pg6").set("titletype", "manual");
    model.result("pg6").set("title", "\u7b49\u6548\u5851\u6027\u5e94\u53d8");
    model.result("pg6").set("paramindicator", "");
    model.result("pg6").set("plotarrayenable", true);
    model.result("pg6").set("relpadding", 1);
    model.result("pg6").create("mms1", "MaxMinSurface");
    model.result("pg6").feature("mms1").set("arraydim", "1");
    model.result("pg6").feature("mms1").set("expr", "solid.epeGp");
    model.result("pg6").feature("mms1").set("display", "max");
    model.result("pg6").feature("mms1").set("precision", 3);
    model.result("pg6").feature("mms1").set("manualindexing", true);
    model.result("pg6").run();
    model.result("pg6").create("ann1", "Annotation");
    model.result("pg6").feature("ann1").set("arraydim", "1");
    model.result("pg6").feature("ann1").set("posyexpr", "totL/2");
    model.result("pg6").feature("ann1").set("level", "global");
    model.result("pg6").feature("ann1")
         .set("text", "$\\dot \\varepsilon = eval(strainRate,1/s,1) \\;  \\mathrm s^{-1}$");
    model.result("pg6").feature("ann1").set("latexmarkup", true);
    model.result("pg6").feature("ann1").set("showpoint", false);
    model.result("pg6").feature("ann1").set("exprprecision", 2);
    model.result("pg6").feature("ann1").set("anchorpoint", "lowerleft");
    model.result("pg6").feature("ann1").set("manualindexing", true);
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").feature().duplicate("mms2", "mms1");
    model.result("pg6").feature().duplicate("ann2", "ann1");
    model.result("pg6").feature("surf2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "dset2");
    model.result("pg6").feature("surf2").set("looplevel", new String[]{"last", "2"});
    model.result("pg6").feature("surf2").set("inheritplot", "surf1");
    model.result("pg6").feature("mms2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("mms2").set("data", "dset2");
    model.result("pg6").feature("mms2").set("looplevel", new String[]{"last", "2"});
    model.result("pg6").feature("mms2").set("arrayindex", 1);
    model.result("pg6").feature("ann2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("ann2").set("data", "dset2");
    model.result("pg6").feature("ann2").set("looplevel", new String[]{"last", "2"});
    model.result("pg6").feature("ann2").set("arrayindex", 1);
    model.result("pg6").feature("surf2").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf3", "surf2");
    model.result("pg6").feature().duplicate("mms3", "mms2");
    model.result("pg6").feature().duplicate("ann3", "ann2");
    model.result("pg6").feature("surf3").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf3").set("looplevel", new String[]{"last", "3"});
    model.result("pg6").feature("mms3").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("mms3").set("looplevel", new String[]{"last", "3"});
    model.result("pg6").feature("mms3").set("anchorpoint", "upperright");
    model.result("pg6").feature("mms3").set("arrayindex", 2);
    model.result("pg6").feature("ann3").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("ann3").set("looplevel", new String[]{"last", "3"});
    model.result("pg6").feature("ann3").set("arrayindex", 2);
    model.result("pg6").feature("surf3").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf4", "surf3");
    model.result("pg6").feature().duplicate("mms4", "mms3");
    model.result("pg6").feature().duplicate("ann4", "ann3");
    model.result("pg6").feature("surf4").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("surf4").set("looplevel", new String[]{"last", "4"});
    model.result("pg6").feature("mms4").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("mms4").set("looplevel", new String[]{"last", "4"});
    model.result("pg6").feature("mms4").set("arrayindex", 3);
    model.result("pg6").feature("ann4").set("arraydim", "1");
    model.result("pg6").run();
    model.result("pg6").feature("ann4").set("looplevel", new String[]{"last", "4"});
    model.result("pg6").feature("ann4").set("arrayindex", 3);
    model.result("pg6").feature("ann4").set("text", "$\\dot \\varepsilon = eval(strainRate) \\;  \\mathrm s^{-1}$");
    model.result("pg6").run();
    model.result("pg6").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u6e29\u5ea6\u4e0a\u5347");
    model.result("pg7").set("title", "\u6e29\u5ea6\u4e0a\u5347");
    model.result("pg7").feature("surf1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf1").set("expr", "T-293.15");
    model.result("pg7").feature("surf1").set("colortable", "Thermal");
    model.result("pg7").feature("surf2").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf2").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("surf3").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf3").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("surf4").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("surf4").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("mms1").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("mms1").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("mms2").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("mms2").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("mms3").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("mms3").set("expr", "T-minput.Tempref");
    model.result("pg7").feature("mms4").set("arraydim", "1");
    model.result("pg7").run();
    model.result("pg7").feature("mms4").set("expr", "T-minput.Tempref");
    model.result("pg7").run();

    model.title("\u5e94\u53d8\u7387\u76f8\u5173\u5851\u6027\u62c9\u4f38\u8bd5\u9a8c");

    model
         .description("\u672c\u4f8b\u91c7\u7528\u56db\u79cd\u4e0d\u540c\u7684\u5e94\u53d8\u7387\u6765\u6a21\u62df\u62c9\u4f38\u8bd5\u9a8c\u3002\u5176\u4e2d\u4f7f\u7528 Johnson-Cook \u786c\u5316\u5b9a\u5f8b\u6a21\u62df\u5851\u6027\u786c\u5316\u7684\u5e94\u53d8\u7387\u4f9d\u8d56\u6027\u3002\n\n\u6b64\u5916\uff0c\u8fd8\u8ba1\u7b97\u4e86\u5851\u6027\u53d8\u5f62\u4ea7\u751f\u7684\u70ed\u91cf\u5f15\u8d77\u7684\u6e29\u5ea6\u5206\u5e03\u548c\u70ed\u81a8\u80c0\u3002\u5728\u53e6\u4e00\u9879\u7814\u7a76\u4e2d\uff0c\u7814\u7a76\u4e86\u5728 Johnson-Cook \u6a21\u578b\u4e2d\u5305\u542b\u6e29\u5ea6\u4f9d\u5b58\u6027\u7684\u5f71\u54cd\u3002");

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

    model.label("strain_rate_dependent_plasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
