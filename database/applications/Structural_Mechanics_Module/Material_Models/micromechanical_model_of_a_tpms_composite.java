/*
 * micromechanical_model_of_a_tpms_composite.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:13 by COMSOL 6.3.0.290. */
public class micromechanical_model_of_a_tpms_composite {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Material_Models");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("th", "4[mm]", "Wall thickness");
    model.param().set("L", "100[mm]", "Unit cell length");
    model.param().set("E_m", "10[GPa]", "Young's modulus, matrix");
    model.param().set("E_f", "20*E_m", "Young's modulus, TPMS");
    model.param().set("nu_m", "0.3", "Poisson's ratio, matrix");
    model.param().set("nu_f", "0.3", "Poisson's ratio, TPMS");
    model.param().set("G_m", "E_m/(2*(1+nu_m))", "Shear modulus, matrix");
    model.param().set("alpha_m", "44E-6[1/K]", "Coefficient of thermal expansion, matrix");
    model.param().set("alpha_f", "0.8E-6[1/K]", "Coefficient of thermal expansion, TPMS");
    model.param().set("rho_m", "3000[kg/m^3]", "Density, matrix");
    model.param().set("rho_f", "7000[kg/m^3]", "Density, TPMS");

    model.geom()
         .load(new String[]{"part1"}, "COMSOL_Multiphysics\\Unit_Cells_and_RVEs\\Miscellaneous\\gyroid.mph", new String[]{"part1"});
    model.component("comp1").geom("geom1").create("pi1", "PartInstance");
    model.component("comp1").geom("geom1").feature("pi1").set("selkeepnoncontr", false);
    model.component("comp1").geom("geom1").feature("pi1").set("part", "part1");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "lm", "L");
    model.component("comp1").geom("geom1").feature("pi1").setEntry("inputexpr", "th", "th");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").view("view1").set("showgrid", false);
    model.component("comp1").view("view1").set("transparency", false);

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().named("geom1_pi1_thi1_dom");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("v_f", "intop1(1)/L^3");
    model.component("comp1").variable("var1").descr("v_f", "Volume fraction of tpms");
    model.component("comp1").variable("var1").set("E_h", "1/solid.cp1.Dinv11");
    model.component("comp1").variable("var1").descr("E_h", "Homogenized Young's modulus");
    model.component("comp1").variable("var1").set("nu_h", "-E_h*solid.cp1.Dinv12");
    model.component("comp1").variable("var1").descr("nu_h", "Homogenized Poisson's ratio");
    model.component("comp1").variable("var1").set("G_h", "1/solid.cp1.Dinv55");
    model.component("comp1").variable("var1").descr("G_h", "Homogenized shear modulus");
    model.component("comp1").variable("var1").set("alpha_h", "solid.cp2.alphaXX");
    model.component("comp1").variable("var1").descr("alpha_h", "Homogenized coefficient of thermal expansion");

    model.component("comp1").physics("solid").feature("lemm1").create("te1", "ThermalExpansion", 3);
    model.component("comp1").physics("solid").feature("lemm1").feature("te1")
         .set("minput_temperature_src", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("te1").set("minput_temperature", "294.15[K]");
    model.component("comp1").physics("solid").create("cp1", "CellPeriodicity", 3);
    model.component("comp1").physics("solid").feature("cp1").set("BoundaryExpansion", "PrescribedStrain");
    model.component("comp1").physics("solid").feature("cp1").set("EffectivePropertiese", "ElasticityMatrixStandard");
    model.component("comp1").physics("solid").feature("cp1").create("bp1", "BoundaryPair", 2);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection().named("geom1_pi1_unisel1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").set("manualDestinationSelection", true);
    model.component("comp1").physics("solid").feature("cp1").feature("bp1").selection("destinationDomains")
         .named("geom1_pi1_boxsel3");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp2", "bp1");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection().named("geom1_pi1_unisel2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp2").selection("destinationDomains")
         .named("geom1_pi1_boxsel5");
    model.component("comp1").physics("solid").feature("cp1").feature().duplicate("bp3", "bp2");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection().named("geom1_pi1_unisel3");
    model.component("comp1").physics("solid").feature("cp1").feature("bp3").selection("destinationDomains")
         .named("geom1_pi1_boxsel7");
    model.component("comp1").physics("solid").feature("cp1").set("parametricStudy", "yes");
    model.component("comp1").physics("solid").feature("cp1").set("parametricSweep", "filled");
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "para", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(0,0.1,1)", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterUnit", 1, 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(0,0.1,1)", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterUnit", 1, 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "para", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(0,0.1,1)", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterUnit", 1, 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "th", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "range(4,2,12)", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterUnit", "mm", 0, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterName", "nu_f", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").setIndex("parameterRange", "0.3 -0.3 -0.5 -0.75", 1, 0);
    model.component("comp1").physics("solid").feature("cp1").runCommand("createLoadGroupsandStudy");
    model.component("comp1").physics("solid").feature().duplicate("cp2", "cp1");
    model.component("comp1").physics("solid").feature("cp2").set("BoundaryExpansion", "FreeExpansion");
    model.component("comp1").physics("solid").feature("cp2").set("EffectiveProperties", "ThermalExpansion");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").selection().named("geom1_pi1_difsel1");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", new String[]{"E_m"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", new String[]{"nu_m"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", new String[]{"rho_m"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_m"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("geom1_pi1_thi1_dom");
    model.component("comp1").material("mat2").propertyGroup().create("Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", new String[]{"E_f"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", new String[]{"nu_f"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"rho_f"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"alpha_f"});

    model.component("comp1").mesh("mesh1").automatic(false);
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 4);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(12, 22, 28, 45, 48, 50, 63, 72, 78, 97, 100, 103, 106, 114, 116, 118, 126, 129, 154, 160, 179, 182, 198, 216, 218, 224, 225);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 2);
    model.component("comp1").mesh("mesh1").run();

    model.study("solidcp1std").feature("solidcp1stat").set("useadvanceddisable", true);
    model.study("solidcp1std").feature("solidcp1stat")
         .set("disabledphysics", new String[]{"solid/lemm1/te1", "solid/cp2"});

    model.sol("solidcp1sol").feature("s1").feature("i1").active(true);

    model.batch("solidcp1p").run("compute");

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 6, 0);
    model.result("pg1").setIndex("looplevel", 4, 1);
    model.result("pg1").setIndex("looplevel", 5, 2);
    model.result("pg1").set("defaultPlotID", "stress");
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg1").feature("vol1").set("threshold", "manual");
    model.result("pg1").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("vol1").set("resolution", "custom");
    model.result("pg1").feature("vol1").set("refine", 2);
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("def", "Deform");
    model.result("pg1").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("solidcp1stdEg", "EvaluationGroup");
    model.result().evaluationGroup("solidcp1stdEg").set("defaultPlotID", "homogenizedMaterialTablecp1");
    model.result().evaluationGroup("solidcp1stdEg").set("data", "dset2");
    model.result().evaluationGroup("solidcp1stdEg").set("includeparameters", "off");
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("solidcp1stdEg").setIndex("looplevelinput", "last", 2);
    model.result().evaluationGroup("solidcp1stdEg")
         .label("\u6750\u6599\u5c5e\u6027 (\u5355\u5143\u5468\u671f\u6027\u7814\u7a76)");
    model.result().evaluationGroup("solidcp1stdEg").create("gmevcp1", "EvalGlobalMatrix");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevcp1").set("expr", "root.comp1.solid.cp1.D");
    model.result().evaluationGroup("solidcp1stdEg").feature("gmevcp1").set("descr", "\u5f39\u6027\u77e9\u9635");
    model.result().evaluationGroup("solidcp1stdEg").run();
    model.result("pg1").run();

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").set("sweeptype", "filled");
    model.study("std1").feature("param").setIndex("pname", "th", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("pname", "th", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "m", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(4,2,12)", 0);
    model.study("std1").feature("param").setIndex("punit", "mm", 0);
    model.study("std1").feature("param").setIndex("pname", "L", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "L", 1);
    model.study("std1").feature("param").setIndex("plistarr", "", 1);
    model.study("std1").feature("param").setIndex("punit", "m", 1);
    model.study("std1").feature("param").setIndex("pname", "nu_f", 1);
    model.study("std1").feature("param").setIndex("plistarr", "0.3 -0.3 -0.5 -0.75", 1);
    model.study("std1").feature("param").setIndex("punit", 1, 1);
    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/cp1"});

    model.sol().create("sol21");
    model.sol("sol21").study("std1");
    model.sol("sol21").create("st1", "StudyStep");
    model.sol("sol21").feature("st1").set("study", "std1");
    model.sol("sol21").feature("st1").set("studystep", "stat");
    model.sol("sol21").create("v1", "Variables");
    model.sol("sol21").feature("v1").set("control", "stat");
    model.sol("sol21").create("s1", "Stationary");
    model.sol("sol21").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol21").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol21").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol21").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol21").feature("s1").create("d1", "Direct");
    model.sol("sol21").feature("s1").feature("d1").set("linsolver", "mumps");
    model.sol("sol21").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol21").feature("s1").create("i1", "Iterative");
    model.sol("sol21").feature("s1").feature("i1").set("linsolver", "fgmres");
    model.sol("sol21").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol21").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol21").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol21").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol21").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol21").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol21").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol21").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol21").feature("s1").feature().remove("fcDef");
    model.sol("sol21").attach("std1");

    model.batch().create("p1", "Parametric");
    model.batch("p1").study("std1");
    model.batch("p1").create("so1", "Solutionseq");
    model.batch("p1").feature("so1").set("seq", "sol21");
    model.batch("p1").feature("so1").set("store", "on");
    model.batch("p1").feature("so1").set("clear", "on");
    model.batch("p1").feature("so1").set("psol", "none");
    model.batch("p1").set("pname", new String[]{"th", "nu_f"});
    model.batch("p1").set("plistarr", new String[]{"range(4,2,12)", "0.3 -0.3 -0.5 -0.75"});
    model.batch("p1").set("sweeptype", "filled");
    model.batch("p1").set("probesel", "all");
    model.batch("p1").set("probes", new String[]{});
    model.batch("p1").set("plot", "off");
    model.batch("p1").set("err", "on");
    model.batch("p1").attach("std1");
    model.batch("p1").set("control", "param");

    model.sol().create("sol22");
    model.sol("sol22").study("std1");
    model.sol("sol22").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol22");
    model.batch("p1").run("compute");

    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset4");
    model.result("pg2").setIndex("looplevel", 4, 0);
    model.result("pg2").setIndex("looplevel", 5, 1);
    model.result("pg2").set("defaultPlotID", "stress");
    model.result("pg2").label("\u5e94\u529b (solid) 1");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").create("vol1", "Volume");
    model.result("pg2").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg2").feature("vol1").set("threshold", "manual");
    model.result("pg2").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("vol1").set("resolution", "custom");
    model.result("pg2").feature("vol1").set("refine", 2);
    model.result("pg2").feature("vol1").set("colortable", "Prism");
    model.result("pg2").feature("vol1").create("def", "Deform");
    model.result("pg2").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1Eg", "EvaluationGroup");
    model.result().evaluationGroup("std1Eg").set("defaultPlotID", "homogenizedMaterialTablecp2");
    model.result().evaluationGroup("std1Eg").set("data", "dset4");
    model.result().evaluationGroup("std1Eg").set("includeparameters", "off");
    model.result().evaluationGroup("std1Eg").setIndex("looplevelinput", "last", 0);
    model.result().evaluationGroup("std1Eg").setIndex("looplevelinput", "last", 1);
    model.result().evaluationGroup("std1Eg").label("\u6750\u6599\u5c5e\u6027 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1Eg").create("gmevcp2", "EvalGlobalMatrix");
    model.result().evaluationGroup("std1Eg").feature("gmevcp2").set("expr", "root.comp1.solid.cp2.alpha");
    model.result().evaluationGroup("std1Eg").feature("gmevcp2").set("descr", "\u70ed\u81a8\u80c0\u7cfb\u6570");
    model.result().evaluationGroup("std1Eg").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevelinput", "first", 0);
    model.result("pg3").set("titletype", "label");
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("legendpos", "upperleft");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").setIndex("expr", "E_h/E_m", 0);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "level3");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "v_f");
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("markerpos", "interp");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").set("legendpos", "lowerleft");
    model.result("pg4").run();
    model.result("pg4").feature("glob1").setIndex("expr", "nu_h", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "", 0);
    model.result("pg4").run();
    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "G_h/G_m", 0);
    model.result("pg5").run();
    model.result("pg3").run();
    model.result().duplicate("pg6", "pg3");
    model.result("pg6").run();
    model.result("pg6").set("data", "none");
    model.result("pg6").set("legendpos", "upperright");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").setIndex("expr", "alpha_h/alpha_m", 0);
    model.result("pg6").run();
    model.result("pg6").set("data", "dset4");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("xdatasolnumtype", "level2");
    model.result("pg6").run();
    model.result("pg1").run();
    model.result("pg1").setIndex("looplevel", 1, 2);
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 1, 1);
    model.result("pg1").run();
    model.result("pg1").feature("vol1").set("rangedataactive", true);
    model.result("pg1").feature("vol1").set("rangecoloractive", true);
    model.result("pg1").feature("vol1").set("rangecolormax", "2E11");
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").setIndex("looplevel", 1, 1);
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").run();
    model.result("pg2").feature("vol1").set("rangecoloractive", true);
    model.result("pg2").feature("vol1").set("rangecolormax", "2E6");
    model.result("pg2").run();
    model.result("pg2").run();

    model
         .title("\u57fa\u4e8e\u4e09\u91cd\u5468\u671f\u6700\u5c0f\u66f2\u9762\u7684\u590d\u5408\u6750\u6599\u7ec6\u89c2\u529b\u5b66\u6a21\u578b");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u57fa\u4e8e\u4e09\u91cd\u5468\u671f\u6700\u5c0f\u66f2\u9762 (TPMS) \u7684\u590d\u5408\u6750\u6599\u7684\u5747\u5300\u5f39\u6027\u548c\u70ed\u5c5e\u6027\u3002\n\n\u5176\u4e2d\u91c7\u7528\u5468\u671f\u6027\u8fb9\u754c\u6761\u4ef6\u6765\u5904\u7406\u57fa\u4e8e\u87ba\u65cb TPMS \u7684\u57fa\u672c\u5355\u5143\uff0c\u4ee5\u83b7\u5f97\u5747\u8d28\u6750\u6599\u5c5e\u6027\u3002\u672c\u4f8b\u5206\u6790\u4e86\u8d1f\u6cca\u677e\u6bd4\u548c\u4e0d\u540c\u4f53\u79ef\u5206\u6570\u5bf9\u5747\u8d28\u5c5e\u6027\u7684\u5f71\u54cd\u3002");

    model.mesh().clearMeshes();

    model.sol("solidcp1sol").clearSolutionData();
    model.sol("solidcp1solp").clearSolutionData();
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
    model.sol("sol31").clearSolutionData();
    model.sol("sol32").clearSolutionData();
    model.sol("sol33").clearSolutionData();
    model.sol("sol34").clearSolutionData();
    model.sol("sol35").clearSolutionData();
    model.sol("sol36").clearSolutionData();
    model.sol("sol37").clearSolutionData();
    model.sol("sol38").clearSolutionData();
    model.sol("sol39").clearSolutionData();
    model.sol("sol40").clearSolutionData();
    model.sol("sol41").clearSolutionData();
    model.sol("sol42").clearSolutionData();

    model.label("micromechanical_model_of_a_tpms_composite.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
