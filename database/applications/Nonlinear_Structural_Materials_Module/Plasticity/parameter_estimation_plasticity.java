/*
 * parameter_estimation_plasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:34 by COMSOL 6.3.0.290. */
public class parameter_estimation_plasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Plasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.baseSystem("mpa");

    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").label("\u5faa\u73af\u526a\u5207\u6570\u636e");
    model.result().table("tbl1").importData("parameter_estimation_plasticity_shear_data.txt");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").set("data", "none");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("source", "table");
    model.result("pg1").feature("tblp1").set("table", "tbl1");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").run();
    model.result("pg1").feature("tblp1").set("xaxisdata", 2);
    model.result("pg1").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg1").feature("tblp1").set("plotcolumns", new int[]{3});
    model.result("pg1").feature("tblp1").set("linemarker", "point");
    model.result("pg1").run();
    model.result("pg1").label("\u5faa\u73af\u526a\u5207\u6570\u636e");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5de5\u7a0b\u526a\u5207\u5e94\u53d8 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u526a\u5207\u5e94\u529b (MPa)");
    model.result("pg1").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").label("\u526a\u5207\u5e94\u53d8");
    model.func("int1").set("source", "resultTable");
    model.func("int1").setIndex("argunit", "s", 0);
    model.func("int1").setEntry("funcnames", "col2", "shear_strain");
    model.func("int1").setIndex("fununit", "1", 0);

    model.param().set("L", "1[mm]");
    model.param().descr("L", "\u5355\u4f4d\u957f\u5ea6");
    model.param().set("rho0", "8000[kg/m^3]");
    model.param().descr("rho0", "\u5bc6\u5ea6");
    model.param().set("E0", "200[GPa]");
    model.param().descr("E0", "\u6768\u6c0f\u6a21\u91cf");
    model.param().set("nu0", "0.3");
    model.param().descr("nu0", "\u6cca\u677e\u6bd4");
    model.param().set("sig_y0", "350[MPa]");
    model.param().descr("sig_y0", "\u521d\u59cb\u5c48\u670d\u5e94\u529b");
    model.param().set("sig_sat", "100[MPa]");
    model.param().descr("sig_sat", "\u9971\u548c\u5e94\u529b");
    model.param().set("beta", "5");
    model.param().descr("beta", "\u9971\u548c\u6307\u6570");
    model.param().set("C_k", "10[GPa]");
    model.param().descr("C_k", "\u8fd0\u52a8\u786c\u5316\u6a21\u91cf");
    model.param().set("E_k", "1/(1/E0+1/C_k)");
    model.param().descr("E_k", "\u8fd0\u52a8\u5b66\u5207\u7ebf\u6a21\u91cf");
    model.param().set("gamma_k", "100");
    model.param().descr("gamma_k", "\u8fd0\u52a8\u786c\u5316\u53c2\u6570");
    model.param().set("t", "0[s]");
    model.param().descr("t", "\u65f6\u95f4\u53c2\u6570");

    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new String[]{"L", "L", "L"});
    model.component("comp1").geom("geom1").run();

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E0"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu0"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho0"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic_material_model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmags", new String[]{"sig_y0"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", new String[]{"E_k"});
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce[Material_model]");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", new String[]{"sig_sat"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", new String[]{"beta"});
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", new String[]{"C_k"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick")
         .set("gammak", new String[]{"gamma_k"});

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").feature("lemm1").set("geometricNonlinearity", "linear");
    model.component("comp1").physics("solid").feature("lemm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").feature("lemm1").create("plsty1", "Plasticity", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .label("\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("IsotropicHardeningModel", "Voce");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty1")
         .set("KinematicHardeningModel", "LinearKinematicHardening");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(3);
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2, 5);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(4);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "shear_strain(t)*L", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().set(1);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("tau", "aveop1(solid.sxz)");
    model.component("comp1").variable("var1").descr("tau", "\u526a\u5207\u5e94\u529b");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").label("\u6b63\u6f14\u95ee\u9898");
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").feature("stat").setIndex("pname", "L", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "mm", 0);
    model.study("std1").feature("stat").setIndex("pname", "t", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(0,1,100)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u521d\u59cb\u6a21\u578b\u9884\u6d4b");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").set("legendpos", "lowerright");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("legend", true);
    model.result("pg2").feature("tblp1").set("legendmethod", "manual");
    model.result("pg2").feature("tblp1").setIndex("legends", "\u526a\u5207\u6570\u636e", 0);
    model.result("pg2").run();
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").setIndex("expr", "tau", 0);
    model.result("pg2").feature("glob1").set("xdata", "expr");
    model.result("pg2").feature("glob1").set("xdataexpr", "shear_strain(t)");
    model.result("pg2").feature("glob1").set("autosolution", false);
    model.result("pg2").run();
    model.result("pg2").feature("glob1").create("comp1", "Comparison");
    model.result("pg2").run();
    model.result("pg2").feature("glob1").feature("comp1").set("metric", "rms");
    model.result("pg2").run();

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso1").setEntry("parameterName", "col1", "t");
    model.component("comp1").common("lso1").setEntry("parameterUnit", "col1", "s");
    model.component("comp1").common("lso1").setEntry("columnType", "col2", "none");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col3", "comp1.tau");
    model.component("comp1").common("lso1").setEntry("variableName", "col3", "shear_stress");
    model.component("comp1").common("lso1").setEntry("unit", "col3", "MPa");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u53c2\u6570\u4f30\u8ba1\uff1a\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").setIndex("pname", "L", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "1[mm]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "L", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "1[mm]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "rho0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "8000[kg/m^3]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "rho0", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "8000[kg/m^3]", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "E0", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "200[GPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "E0", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "200[GPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "sig_sat", 0);
    model.study("std2").feature("lsqo").setIndex("scale", "100[MPa]", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "beta", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 5, 1);
    model.study("std2").feature("lsqo").setIndex("pname", "C_k", 2);
    model.study("std2").feature("lsqo").setIndex("scale", "10[GPa]", 2);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("stat").set("lsqcont", true);
    model.study("std2").feature("stat").set("lsqpcontinuation", "t");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result("pg2").run();
    model.result().duplicate("pg3", "pg2");
    model.result("pg3").run();
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1\uff1a\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.result("pg3").set("data", "dset2");

    model.study("std2").feature("lsqo").set("plot", true);
    model.study("std2").feature("lsqo").set("plotgroup", "pg3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.component("comp1").physics("solid").feature("lemm1").feature().duplicate("plsty2", "plsty1");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .label("Armstrong-Frederick \u8fd0\u52a8\u786c\u5316");
    model.component("comp1").physics("solid").feature("lemm1").feature("plsty2")
         .set("KinematicHardeningModel", "ArmstrongFrederick");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").label("\u53c2\u6570\u4f30\u8ba1\uff1aArmstrong-Frederick \u8fd0\u52a8\u786c\u5316");
    model.study("std3").setGenPlots(false);
    model.study("std3").create("lsqo", "LSQOptimization");
    model.study("std3").feature("lsqo").setIndex("pname", "L", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "1[mm]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "L", 0);
    model.study("std3").feature("lsqo").setIndex("initval", "1[mm]", 0);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "rho0", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "8000[kg/m^3]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "rho0", 1);
    model.study("std3").feature("lsqo").setIndex("initval", "8000[kg/m^3]", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std3").feature("lsqo").setIndex("pname", "E0", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "200[GPa]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "E0", 2);
    model.study("std3").feature("lsqo").setIndex("initval", "200[GPa]", 2);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "nu0", 3);
    model.study("std3").feature("lsqo").setIndex("initval", 0.3, 3);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std3").feature("lsqo").setIndex("pname", "nu0", 3);
    model.study("std3").feature("lsqo").setIndex("initval", 0.3, 3);
    model.study("std3").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std3").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std3").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std3").feature("lsqo").setIndex("pname", "sig_sat", 0);
    model.study("std3").feature("lsqo").setIndex("scale", "100[MPa]", 0);
    model.study("std3").feature("lsqo").setIndex("pname", "beta", 1);
    model.study("std3").feature("lsqo").setIndex("scale", 10, 1);
    model.study("std3").feature("lsqo").setIndex("pname", "C_k", 2);
    model.study("std3").feature("lsqo").setIndex("scale", "10[GPa]", 2);
    model.study("std3").feature("lsqo").setIndex("pname", "gamma_k", 3);
    model.study("std3").feature("lsqo").setIndex("scale", 100, 3);
    model.study("std3").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std3").feature("stat").set("lsqcont", true);
    model.study("std3").feature("stat").set("lsqpcontinuation", "t");
    model.study("std3").createAutoSequences("sol");
    model.study("std3").createAutoSequences("jobs");

    model.sol("sol3").runFromTo("st1", "v1");

    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u53c2\u6570\u4f30\u8ba1\uff1aArmstrong-Frederick \u8fd0\u52a8\u786c\u5316");
    model.result("pg4").set("data", "dset3");

    model.study("std3").feature("lsqo").set("plot", true);
    model.study("std3").feature("lsqo").set("plotgroup", "pg4");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg4").run();

    model.study("std3").feature("lsqo").set("probewindow", "");

    model.result().evaluationGroup().create("std2lsqoparam1", "EvaluationGroup");
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").set("data", "dset2");
    model.result().evaluationGroup("std2lsqoparam1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").set("transpose", true);
    model.result().evaluationGroup("std2lsqoparam1").set("includeparameters", false);
    model.result().evaluationGroup("std2lsqoparam1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "sig_sat", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "beta", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "C_k", 2);
    model.result().evaluationGroup("std2lsqoparam1").run();
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1")
         .label("\u4f30\u8ba1\u53c2\u6570\uff1a\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.result().evaluationGroup().create("std3lsqoparam1", "EvaluationGroup");
    model.result().evaluationGroup("std3lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std3) 1");
    model.result().evaluationGroup("std3lsqoparam1").set("data", "dset3");
    model.result().evaluationGroup("std3lsqoparam1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std3lsqoparam1").set("transpose", true);
    model.result().evaluationGroup("std3lsqoparam1").set("includeparameters", false);
    model.result().evaluationGroup("std3lsqoparam1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std3lsqoparam1").feature("gev1").setIndex("expr", "sig_sat", 0);
    model.result().evaluationGroup("std3lsqoparam1").feature("gev1").setIndex("expr", "beta", 1);
    model.result().evaluationGroup("std3lsqoparam1").feature("gev1").setIndex("expr", "C_k", 2);
    model.result().evaluationGroup("std3lsqoparam1").feature("gev1").setIndex("expr", "gamma_k", 3);
    model.result().evaluationGroup("std3lsqoparam1").run();
    model.result().evaluationGroup("std3lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std3) 1");
    model.result().evaluationGroup("std3lsqoparam1")
         .label("\u4f30\u8ba1\u53c2\u6570\uff1aArmstrong-Frederick \u8fd0\u52a8\u786c\u5316");

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/plsty2"});
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/lemm1/plsty2"});

    model.result().table("comp1").label("RMS \u6bd4\u8f83\uff1a\u521d\u59cb\u6a21\u578b\u9884\u6d4b");
    model.result().table("comp2").label("RMS \u6bd4\u8f83\uff1a\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.result().table("comp3").label("RMS \u6bd4\u8f83\uff1aArmstrong-Frederick \u8fd0\u52a8\u786c\u5316");
    model.result().table("tbl2").label("\u76ee\u6807\u63a2\u9488\u8868\uff1a\u7ebf\u6027\u8fd0\u52a8\u786c\u5316");
    model.result().table("tbl3")
         .label("\u76ee\u6807\u63a2\u9488\u8868\uff1aArmstrong-Frederick \u8fd0\u52a8\u786c\u5316");
    model.result("pg4").run();

    model.title("\u5f39\u5851\u6027\u6750\u6599\u7684\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5728\u7ed9\u5b9a\u5faa\u73af\u526a\u5207\u6570\u636e\u7684\u60c5\u51b5\u4e0b\uff0c\u8ba1\u7b97\u7ec4\u5408\u786c\u5316\u5f39\u5851\u6027\u6750\u6599\u6a21\u578b\u7684\u6750\u6599\u53c2\u6570\u3002");

    model.label("parameter_estimation_plasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
