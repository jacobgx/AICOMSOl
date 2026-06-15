/*
 * parameter_estimation_hyperelasticity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:31 by COMSOL 6.3.0.290. */
public class parameter_estimation_hyperelasticity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

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
    model.result().table("tbl1").label("\u5355\u8f74\u6570\u636e");
    model.result().table("tbl1").importData("parameter_estimation_hyperelasticity_uniaxial.txt");
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").label("\u7eaf\u526a\u5207\u6570\u636e");
    model.result().table("tbl2").importData("parameter_estimation_hyperelasticity_pure_shear.txt");
    model.result().table().create("tbl3", "Table");
    model.result().table("tbl3").label("\u7b49\u53cc\u8f74\u6570\u636e");
    model.result().table("tbl3").importData("parameter_estimation_hyperelasticity_equibiaxial.txt");
    model.result().create("pg1", "PlotGroup1D");
    model.result("pg1").run();
    model.result("pg1").label("\u5b9e\u9a8c\u6570\u636e");
    model.result("pg1").set("titletype", "label");
    model.result("pg1").set("xlabelactive", true);
    model.result("pg1").set("xlabel", "\u5916\u52a0\u62c9\u4f38 (1)");
    model.result("pg1").set("ylabelactive", true);
    model.result("pg1").set("ylabel", "\u6807\u79f0\u5e94\u529b (MPa)");
    model.result("pg1").set("legendpos", "upperleft");
    model.result("pg1").create("tblp1", "Table");
    model.result("pg1").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg1").feature("tblp1").set("linewidth", "preference");
    model.result("pg1").feature("tblp1").label("\u5355\u8f74\u6570\u636e");
    model.result("pg1").feature("tblp1").set("linestyle", "none");
    model.result("pg1").feature("tblp1").set("linecolor", "cyclereset");
    model.result("pg1").feature("tblp1").set("linemarker", "point");
    model.result("pg1").feature("tblp1").set("legend", true);
    model.result("pg1").feature("tblp1").set("legendmethod", "manual");
    model.result("pg1").feature("tblp1").setIndex("legends", "\u5355\u8f74\u6570\u636e", 0);
    model.result("pg1").feature().duplicate("tblp2", "tblp1");
    model.result("pg1").run();
    model.result("pg1").feature("tblp2").label("\u7eaf\u526a\u5207\u6570\u636e");
    model.result("pg1").feature("tblp2").set("table", "tbl2");
    model.result("pg1").feature("tblp2").set("linecolor", "cycle");
    model.result("pg1").feature("tblp2").setIndex("legends", "\u7eaf\u526a\u5207\u6570\u636e", 0);
    model.result("pg1").feature().duplicate("tblp3", "tblp2");
    model.result("pg1").run();
    model.result("pg1").feature("tblp3").label("\u7b49\u53cc\u8f74\u6570\u636e");
    model.result("pg1").feature("tblp3").set("table", "tbl3");
    model.result("pg1").feature("tblp3").setIndex("legends", "\u7b49\u53cc\u8f74\u6570\u636e", 0);
    model.result("pg1").run();

    model.param().set("mu1", "200[kPa]");
    model.param().descr("mu1", "Ogden \u6a21\u91cf\uff0c\u5206\u652f 1");
    model.param().set("alpha1", "2.0");
    model.param().descr("alpha1", "Ogden \u6307\u6570\uff0c\u5206\u652f 1");
    model.param().set("mu2", "-1.0[kPa]");
    model.param().descr("mu2", "Ogden \u6a21\u91cf\uff0c\u5206\u652f 2");
    model.param().set("alpha2", "-2.0");
    model.param().descr("alpha2", "Ogden \u6307\u6570\uff0c\u5206\u652f 2");
    model.param().set("stretch", "1");
    model.param().descr("stretch", "\u5916\u52a0\u62c9\u4f38");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("arr1", "Array");
    model.component("comp1").geom("geom1").feature("arr1").selection("input").set("blk1");
    model.component("comp1").geom("geom1").feature("arr1").set("fullsize", new int[]{1, 3, 1});
    model.component("comp1").geom("geom1").feature("arr1").set("displ", new int[]{0, 3, 0});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").physics("solid").prop("StructuralTransientBehavior")
         .set("StructuralTransientBehavior", "Quasistatic");
    model.component("comp1").physics("solid").prop("ShapeProperty").set("order_displacement", 1);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 3);
    model.component("comp1").physics("solid").feature("hmm1").selection().set(1, 2, 3);
    model.component("comp1").physics("solid").feature("hmm1").set("MaterialModel", "Ogden");
    model.component("comp1").physics("solid").feature("hmm1").set("Compressibility_Ogden", "Incompressible");
    model.component("comp1").physics("solid").feature("hmm1").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("mup", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("alphap", 0, 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("mup", "mu1", 0, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("alphap", "alpha1", 0, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("mup", "mu2", 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").setIndex("alphap", "alpha2", 1, 0);
    model.component("comp1").physics("solid").feature("hmm1").set("reducedIntegration", true);
    model.component("comp1").physics("solid").create("roll1", "Roller", 2);
    model.component("comp1").physics("solid").feature("roll1").selection().set(1, 2, 3, 6, 7, 8, 11, 12, 13);
    model.component("comp1").physics("solid").create("disp1", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp1").selection().set(16, 17, 18);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "stretch-1", 0);
    model.component("comp1").physics("solid").create("disp2", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp2").selection().set(10);
    model.component("comp1").physics("solid").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").create("disp3", "Displacement2", 2);
    model.component("comp1").physics("solid").feature("disp3").selection().set(15);
    model.component("comp1").physics("solid").feature("disp3").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("solid").feature("disp3").setIndex("U0", "stretch-1", 1);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").selection().set(1, 6, 11);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().all();
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().set(1);
    model.component("comp1").cpl("aveop1").set("frame", "material");
    model.component("comp1").cpl().duplicate("aveop2", "aveop1");
    model.component("comp1").cpl("aveop2").selection().set(2);
    model.component("comp1").cpl().duplicate("aveop3", "aveop2");
    model.component("comp1").cpl("aveop3").selection().set(3);

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("P_ua", "aveop1(solid.PxX)");
    model.component("comp1").variable("var1").descr("P_ua", "\u5355\u8f74");
    model.component("comp1").variable("var1").set("P_ps", "aveop2(solid.PxX)");
    model.component("comp1").variable("var1").descr("P_ps", "\u7eaf\u526a\u5207");
    model.component("comp1").variable("var1").set("P_eb", "aveop3(solid.PxX)");
    model.component("comp1").variable("var1").descr("P_eb", "\u7b49\u53cc\u8f74");

    model.study("std1").label("\u6b63\u6f14\u95ee\u9898");
    model.study("std1").setGenPlots(false);
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "mu1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "MPa", 0);
    model.study("std1").feature("stat").setIndex("pname", "mu1", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "MPa", 0);
    model.study("std1").feature("stat").setIndex("pname", "stretch", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "range(1.0, 0.05, 2.5)", 0);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pw").set("scalemethod", "manual");
    model.sol("sol1").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", "10[MPa]");
    model.sol("sol1").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol1").feature("s1").feature("fc1").set("dtech", "const");

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u521d\u59cb\u6a21\u578b\u9884\u6d4b");
    model.result("pg2").set("data", "dset1");
    model.result("pg2").create("glob1", "Global");
    model.result("pg2").feature("glob1").set("markerpos", "datapoints");
    model.result("pg2").feature("glob1").set("linewidth", "preference");
    model.result("pg2").feature("glob1").label("\u521d\u59cb\u6a21\u578b\u9884\u6d4b");
    model.result("pg2").feature("glob1").setIndex("expr", "P_ua", 0);
    model.result("pg2").feature("glob1").setIndex("expr", "P_ps", 1);
    model.result("pg2").feature("glob1").setIndex("expr", "P_eb", 2);
    model.result("pg2").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg2").feature("glob1").set("legendmethod", "manual");
    model.result("pg2").feature("glob1").setIndex("legends", "\u5355\u8f74\u6a21\u578b", 0);
    model.result("pg2").feature("glob1").setIndex("legends", "\u7eaf\u526a\u5207\u6a21\u578b", 1);
    model.result("pg2").feature("glob1").setIndex("legends", "\u7b49\u53cc\u8f74\u6a21\u578b", 2);
    model.result("pg2").run();

    model.component("comp1").common().create("lso1", "LeastSquaresObjective");
    model.component("comp1").common("lso1").label("\u5355\u8f74\u62c9\u4f38\u8bd5\u9a8c");
    model.component("comp1").common("lso1").set("source", "resultTable");
    model.component("comp1").common("lso1").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso1").setEntry("parameterName", "col1", "stretch");
    model.component("comp1").common("lso1").setEntry("parameterUnit", "col1", "1");
    model.component("comp1").common("lso1").setEntry("modelExpression", "col2", "comp1.P_ua");
    model.component("comp1").common("lso1").setEntry("variableName", "col2", "UA");
    model.component("comp1").common("lso1").setEntry("unit", "col2", "MPa");
    model.component("comp1").common().create("lso2", "LeastSquaresObjective");
    model.component("comp1").common("lso2").label("\u7eaf\u526a\u5207\u8bd5\u9a8c");
    model.component("comp1").common("lso2").set("source", "resultTable");
    model.component("comp1").common("lso2").set("resultTable", "tbl2");
    model.component("comp1").common("lso2").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso2").setEntry("parameterName", "col1", "stretch");
    model.component("comp1").common("lso2").setEntry("parameterUnit", "col1", "1");
    model.component("comp1").common("lso2").setEntry("modelExpression", "col2", "comp1.P_ps");
    model.component("comp1").common("lso2").setEntry("variableName", "col2", "PS");
    model.component("comp1").common("lso2").setEntry("unit", "col2", "MPa");
    model.component("comp1").common().create("lso3", "LeastSquaresObjective");
    model.component("comp1").common("lso3").label("\u7b49\u53cc\u8f74\u62c9\u4f38\u8bd5\u9a8c");
    model.component("comp1").common("lso3").set("source", "resultTable");
    model.component("comp1").common("lso3").set("resultTable", "tbl3");
    model.component("comp1").common("lso3").setEntry("columnType", "col1", "param");
    model.component("comp1").common("lso3").setEntry("parameterName", "col1", "stretch");
    model.component("comp1").common("lso3").setEntry("parameterUnit", "col1", "1");
    model.component("comp1").common("lso3").setEntry("modelExpression", "col2", "comp1.P_eb");
    model.component("comp1").common("lso3").setEntry("variableName", "col2", "EB");
    model.component("comp1").common("lso3").setEntry("unit", "col2", "MPa");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").label("\u53c2\u6570\u4f30\u8ba1");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("lsqo", "LSQOptimization");
    model.study("std2").feature("lsqo").setIndex("pname", "mu1", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "200[kPa]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "mu1", 0);
    model.study("std2").feature("lsqo").setIndex("initval", "200[kPa]", 0);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 0);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 0);
    model.study("std2").feature("lsqo").setIndex("pname", "alpha1", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "2.0", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "alpha1", 1);
    model.study("std2").feature("lsqo").setIndex("initval", "2.0", 1);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 1);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 1);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 1);
    model.study("std2").feature("lsqo").setIndex("pname", "mu2", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "-1.0[kPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "mu2", 2);
    model.study("std2").feature("lsqo").setIndex("initval", "-1.0[kPa]", 2);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 2);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 2);
    model.study("std2").feature("lsqo").setIndex("pname", "alpha2", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "-2.0", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("pname", "alpha2", 3);
    model.study("std2").feature("lsqo").setIndex("initval", "-2.0", 3);
    model.study("std2").feature("lsqo").setIndex("scale", 1, 3);
    model.study("std2").feature("lsqo").setIndex("lbound", "", 3);
    model.study("std2").feature("lsqo").setIndex("ubound", "", 3);
    model.study("std2").feature("lsqo").setIndex("scale", "200[kPa]", 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 0);
    model.study("std2").feature("lsqo").setIndex("lbound", 0, 1);
    model.study("std2").feature("lsqo").setIndex("scale", "1[kPa]", 2);
    model.study("std2").feature("lsqo").setIndex("ubound", 0, 2);
    model.study("std2").feature("lsqo").setIndex("ubound", 0, 3);
    model.study("std2").feature("lsqo").set("lsqdatamethod", "lsq");
    model.study("std2").feature("stat").set("lsqcont", true);
    model.study("std2").feature("stat").set("lsqpcontinuation", "stretch");
    model.study("std2").feature("stat").set("initvallsq", "1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").feature("comp1_solid_hmm1_pw").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_solid_hmm1_pw").set("scaleval", "10[MPa]");
    model.sol("sol2").feature("v1").feature("comp1_u").set("scalemethod", "manual");
    model.sol("sol2").feature("o1").feature("s1").feature("fc1").set("dtech", "const");

    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").label("\u53c2\u6570\u4f30\u8ba1\uff1aOgden \u8d85\u5f39\u6027");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u5916\u52a0\u62c9\u4f38 (1)");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u6807\u79f0\u5e94\u529b (MPa)");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("yseclabelactive", true);
    model.result("pg3").set("yseclabel", "\u6807\u79f0\u5e94\u529b (MPa)");
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("tblp1", "pg2/tblp1");
    model.result("pg3").feature().copy("tblp2", "pg2/tblp2");
    model.result("pg3").feature().copy("tblp3", "pg2/tblp3");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("tblp3").set("plotonsecyaxis", true);
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").label("\u5355\u8f74\u6a21\u578b");
    model.result("pg3").feature("glob1").setIndex("expr", "lso1.UA.model", 0);
    model.result("pg3").feature("glob1").set("linecolor", "cyclereset");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "\u5355\u8f74\u6a21\u578b", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").label("\u7eaf\u526a\u5207\u6a21\u578b");
    model.result("pg3").feature("glob2").setIndex("expr", "lso2.PS.model", 0);
    model.result("pg3").feature("glob2").set("linecolor", "cycle");
    model.result("pg3").feature("glob2").setIndex("legends", "\u7eaf\u526a\u5207\u6a21\u578b", 0);
    model.result("pg3").feature().duplicate("glob3", "glob2");
    model.result("pg3").run();
    model.result("pg3").feature("glob3").label("\u7b49\u53cc\u8f74\u6a21\u578b");
    model.result("pg3").feature("glob3").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob3").setIndex("expr", "lso3.EB.model", 0);
    model.result("pg3").feature("glob3").setIndex("legends", "\u7b49\u53cc\u8f74\u6a21\u578b", 0);
    model.result("pg3").run();
    model.result("pg3").feature("glob1").create("filt1", "GlobalFilter");
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature("filt1").set("expr", "stretch<=2");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").create("filt1", "GlobalFilter");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").feature("filt1").set("expr", "stretch<=1.75");

    model.study("std2").feature("lsqo").set("plot", true);
    model.study("std2").feature("lsqo").set("plotgroup", "pg3");
    model.study("std2").feature("lsqo").set("showindobj", true);
    model.study("std2").feature("lsqo").set("plotobj", true);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg3").run();

    model.study("std2").feature("lsqo").set("probewindow", "");

    model.result().evaluationGroup().create("std2lsqoparam1", "EvaluationGroup");
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").set("data", "dset2");
    model.result().evaluationGroup("std2lsqoparam1").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std2lsqoparam1").set("transpose", true);
    model.result().evaluationGroup("std2lsqoparam1").set("includeparameters", false);
    model.result().evaluationGroup("std2lsqoparam1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "mu1", 0);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "alpha1", 1);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "mu2", 2);
    model.result().evaluationGroup("std2lsqoparam1").feature("gev1").setIndex("expr", "alpha2", 3);
    model.result().evaluationGroup("std2lsqoparam1").run();
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570 (std2) 1");
    model.result().evaluationGroup("std2lsqoparam1").label("\u4f30\u8ba1\u53c2\u6570");
    model.result("pg4").set("window", "window1");
    model.result("pg4").run();
    model.result("pg4").label("\u76ee\u6807\u51fd\u6570");
    model.result("pg4").set("ylog", true);
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg3").run();

    model.title("\u8d85\u5f39\u6027\u6750\u6599\u7684\u53c2\u6570\u4f30\u8ba1");

    model
         .description("\u672c\u6559\u5b66\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5efa\u7acb\u53c2\u6570\u4f30\u8ba1\u7814\u7a76\uff0c\u4ee5\u5c06 Ogden \u8d85\u5f39\u6027\u6a21\u578b\u7684\u6750\u6599\u53c2\u6570\u4e0e\u5b9e\u9a8c\u6570\u636e\u8fdb\u884c\u62df\u5408\u3002\u8be5\u8fc7\u7a0b\u8003\u8651\u4e86\u5927\u53d8\u5f62\u6761\u4ef6\u4e0b\u7684\u591a\u4e2a\u8f7d\u8377\u5de5\u51b5\uff0c\u8fd9\u5bf9\u4e8e\u83b7\u5f97\u5177\u6709\u826f\u597d\u9884\u6d4b\u80fd\u529b\u7684\u672c\u6784\u6a21\u578b\u6765\u8bf4\u901a\u5e38\u662f\u5fc5\u8981\u7684\u3002");

    model.label("parameter_estimation_hyperelasticity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
