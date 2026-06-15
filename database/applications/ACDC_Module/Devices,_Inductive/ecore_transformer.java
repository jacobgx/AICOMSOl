/*
 * ecore_transformer.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 29 2026, 17:14 by COMSOL 6.3.0.290. */
public class ecore_transformer {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\ACDC_Module\\Devices,_Inductive");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mf", "InductionCurrents", "geom1");

    model.study().create("std1");
    model.study("std1").create("ccc", "CoilCurrentCalculation");
    model.study("std1").feature("ccc").set("CoilName", "1");
    model.study("std1").feature("ccc").set("outputmap", new String[]{});
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").set("ngenAUX", "1");
    model.study("std1").feature("ccc").set("goalngenAUX", "1");
    model.study("std1").feature("ccc").setSolveFor("/physics/mf", true);

    model.param().set("Rp", "100[ohm]");
    model.param().descr("Rp", "\u521d\u7ea7\u7ed5\u7ec4\u7535\u963b");
    model.param().set("Ns", "300");
    model.param().descr("Ns", "\u6b21\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u531d\u6570");
    model.param().set("nu", "50[Hz]");
    model.param().descr("nu", "\u7535\u6e90\u7535\u538b\u7684\u9891\u7387");

    model.component("comp1").variable().create("var1");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").variable("var1").label("\u53d8\u91cf\uff0c\u7b97\u4f8b 1");
    model.component("comp1").variable("var1").set("Rs", "10[kohm]");
    model.component("comp1").variable("var1").descr("Rs", "\u6b21\u7ea7\u7ed5\u7ec4\u7535\u963b");
    model.component("comp1").variable("var1").set("Np", "300");
    model.component("comp1").variable("var1").descr("Np", "\u521d\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u531d\u6570");
    model.component("comp1").variable("var1").set("Vac", "25[V]");
    model.component("comp1").variable("var1").descr("Vac", "\u7535\u6e90\u7535\u538b");
    model.component("comp1").variable().duplicate("var2", "var1");
    model.component("comp1").variable("var2").label("\u53d8\u91cf\uff0c\u7b97\u4f8b 2");
    model.component("comp1").variable("var2").set("Rs", "100[ohm]");
    model.component("comp1").variable("var2").set("Np", "3e5");
    model.component("comp1").variable("var2").set("Vac", "25[kV]");

    model.component("comp1").geom("geom1").insertFile("ecore_transformer_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u78c1\u82af");
    model.component("comp1").geom("geom1").feature("ext1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("fin");
    model.component("comp1").geom("geom1").create("sel1", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel1").label("\u521d\u7ea7\u7ed5\u7ec4");
    model.component("comp1").geom("geom1").feature("sel1").selection("selection").set("fin", 5, 6, 8, 9);
    model.component("comp1").geom("geom1").selection().create("csel2", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel2").label("\u7ed5\u7ec4");
    model.component("comp1").geom("geom1").feature("sel1").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").run("sel1");
    model.component("comp1").geom("geom1").create("sel2", "ExplicitSelection");
    model.component("comp1").geom("geom1").feature("sel2").selection("selection").set("fin", 3, 4, 7, 10);
    model.component("comp1").geom("geom1").feature("sel2").set("contributeto", "csel2");
    model.component("comp1").geom("geom1").feature("sel2").label("\u6b21\u7ea7\u7ed5\u7ec4");
    model.component("comp1").geom("geom1").run("sel2");

    model.component("comp1").view("view1").set("renderwireframe", true);
    model.component("comp1").view("view1").hideEntities().create("hide1");
    model.component("comp1").view("view1").hideEntities("hide1").geom("geom1", 2);
    model.component("comp1").view("view1").hideEntities("hide1").set(1, 2, 3, 4, 5, 56);

    model.component("comp1").physics("mf").prop("ShapeProperty").set("order_magneticvectorpotential", 1);
    model.component("comp1").physics("mf").create("lc1", "LaminatedCore", 3);
    model.component("comp1").physics("mf").feature("lc1").selection().named("geom1_csel1_dom");
    model.component("comp1").physics("mf").feature("lc1").set("LaminationModel", "DirectionBased");
    model.component("comp1").physics("mf").feature("lc1").set("Direction", new int[]{0, 1, 0});
    model.component("comp1").physics("mf").feature("lc1").set("kh_steinmetz", 1000);
    model.component("comp1").physics("mf").feature("lc1").set("alpha", 1);
    model.component("comp1").physics("mf").feature("lc1").set("beta_steinmetz", 1.5);
    model.component("comp1").physics("mf").create("coil1", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil1").selection().named("geom1_sel1");
    model.component("comp1").physics("mf").feature("coil1").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil1").set("N", "Np");
    model.component("comp1").physics("mf").feature("coil1").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf").feature("coil1").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil1").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil1").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("mf").feature("coil1").feature("ccc1").feature("ct1").selection().set(35);
    model.component("comp1").physics("mf").create("coil2", "Coil", 3);
    model.component("comp1").physics("mf").feature("coil2").selection().named("geom1_sel2");
    model.component("comp1").physics("mf").feature("coil2").set("ConductorModel", "Multi");
    model.component("comp1").physics("mf").feature("coil2").set("N", "Ns");
    model.component("comp1").physics("mf").feature("coil2").set("CoilExcitation", "CircuitCurrent");
    model.component("comp1").physics("mf").feature("coil2").set("AreaFrom", "UserDefined");
    model.component("comp1").physics("mf").feature("coil2").set("HarmonicLoss", false);
    model.component("comp1").physics("mf").feature("coil2").create("loss1", "LossCalculation", 3);
    model.component("comp1").physics("mf").feature("coil2").feature("ccc1").feature("ct1").selection().set(31);
    model.component("comp1").physics("mf").create("gfa1", "GaugeFixingA", 3);
    model.component("comp1").physics("mf").feature("gfa1").set("equationForm", "Stationary");
    model.component("comp1").physics("mf").feature("coil1").set("toBeReferencedElectricalCircuitTag", "cir");
    model.component("comp1").physics("mf").feature("coil1").set("toBeCreatedElement", "ModelDeviceIV");
    model.component("comp1").physics("mf").feature("coil1").runCommand("addElectricalCircuitConnection");
    model.component("comp1").physics("cir").create("V1", "VoltageSource", -1);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 1, 0, 0);
    model.component("comp1").physics("cir").feature("V1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("V1").set("sourceType", "SineSource");
    model.component("comp1").physics("cir").feature("V1").set("value", "Vac");
    model.component("comp1").physics("cir").feature("V1").set("freq", "nu");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 2, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "Rp");
    model.component("comp1").physics("mf").feature("coil2").set("toBeReferencedElectricalCircuitTag", "cir");
    model.component("comp1").physics("mf").feature("coil2").set("toBeCreatedElement", "ModelDeviceIV");
    model.component("comp1").physics("mf").feature("coil2").runCommand("addElectricalCircuitConnection");
    model.component("comp1").physics("cir").feature("IvsU2").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").create("R2", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 0, 0, 0);
    model.component("comp1").physics("cir").feature("R2").setIndex("Connections", 3, 1, 0);
    model.component("comp1").physics("cir").feature("R2").set("R", "Rs");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup().create("linzRes", "linzRes", "Linearized resistivity");
    model.component("comp1").material("mat1").label("Copper");
    model.component("comp1").material("mat1").set("family", "copper");
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]", "0", "0", "0", "5.998e7[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"17e-6[1/K]", "0", "0", "0", "17e-6[1/K]", "0", "0", "0", "17e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "385[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "8960[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]", "0", "0", "0", "400[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "110[GPa]");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material("mat1").propertyGroup("linzRes").label("Linearized resistivity");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("rho0", "1.72e-8[ohm*m]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("alpha", "0.0039[1/K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").set("Tref", "298[K]");
    model.component("comp1").material("mat1").propertyGroup("linzRes").addInput("temperature");
    model.component("comp1").material("mat1").selection().named("geom1_csel2_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup().create("BHCurve", "BHCurve", "B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func().create("BH", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("EffectiveBHCurve", "EffectiveBHCurve", "Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func()
         .create("BHeff", "Interpolation");
    model.component("comp1").material("mat2").label("Soft Iron (Without Losses)");
    model.component("comp1").material("mat2").set("family", "iron");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"0[S/m]", "0", "0", "0", "0[S/m]", "0", "0", "0", "0[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").label("B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
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
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normB", "BH(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("normH", "BH_inv(normBin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").set("Wpm", "BH_prim(normHin)");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").descr("normHin", "\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve")
         .descr("normBin", "\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").label("Effective B-H Curve");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("table", new String[][]{{"0", "0"}, 
         {"663.146", "1.000000051691021"}, 
         {"1067.5", "1.4936495124126294"}, 
         {"1705.23", "1.9415328461315795"}, 
         {"2463.11", "2.257765669366018"}, 
         {"3841.67", "2.609980642431287"}, 
         {"5425.74", "2.8664452090837504"}, 
         {"7957.75", "3.1441438097176118"}, 
         {"12298.3", "3.448538051654125"}, 
         {"20462.8", "3.7816711973679054"}, 
         {"32169.6", "4.058345590113038"}, 
         {"61213.4", "4.420646552950275"}, 
         {"111408", "4.721274089545955"}, 
         {"188487.757", "4.972148140718701"}, 
         {"267930.364", "5.145510860855953"}, 
         {"347507.836", "5.245510861426532"}});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("fununit", new String[]{"T"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("argunit", new String[]{"A/m"});
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff").set("defineinv", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").func("BHeff")
         .set("defineprimfun", true);
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").set("normBeff", "BHeff(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("normHeff", "BHeff_inv(normBeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .set("Wpmeff", "BHeff_prim(normHeffin)");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normHeffin", "\u6709\u6548\u78c1\u573a\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve")
         .descr("normBeffin", "\u6709\u6548\u78c1\u901a\u5bc6\u5ea6\u6a21");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfield");
    model.component("comp1").material("mat2").propertyGroup("EffectiveBHCurve").addInput("magneticfluxdensity");
    model.component("comp1").material("mat2").selection().named("geom1_csel1_dom");
    model.component("comp1").material("mat2").propertyGroup("BHCurve").func("BH").set("extrap", "linear");

    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").selection().geom("geom1");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").selection()
         .set(2, 3, 4, 5, 6, 7, 8, 9, 10);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hmax", 6);
    model.component("comp1").mesh("mesh1").run();

    model.component("comp1").view("view1").set("hidestatus", "hide");

    model.study("std1").feature("ccc").set("useadvanceddisable", true);
    model.study("std1").feature("ccc").set("disabledvariables", new String[]{"var2"});
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mf", true);
    model.study("std2").feature("time").setSolveFor("/physics/cir", true);
    model.study("std2").label("\u7814\u7a762\uff0c\u7b97\u4f8b 1");
    model.study("std2").setGenPlots(false);
    model.study("std2").feature("time").set("tlist", "range(0,5e-4,0.05)");
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledvariables", new String[]{"var2"});
    model.study("std2").feature("time").set("usesol", true);
    model.study("std2").feature("time").set("notsolmethod", "sol");
    model.study("std2").feature("time").set("notstudy", "std1");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("v1").set("scalemethod", "none");
    model.sol("sol2").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol2").feature("t1").feature("fc1").set("maxiter", 25);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(2, 3, 4, 5, 6, 7, 8, 9, 10);
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u78c1\u901a\u5bc6\u5ea6\u548c\u7535\u6d41");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").create("vol1", "Volume");
    model.result("pg1").feature("vol1").set("colortable", "Prism");
    model.result("pg1").feature("vol1").create("sel1", "Selection");
    model.result("pg1").feature("vol1").feature("sel1").selection().set(2, 4, 6, 7, 8, 10);
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").create("arwv1", "ArrowVolume");
    model.result("pg1").feature("arwv1").set("expr", new String[]{"mf.Jx", "mf.Jy", "mf.Jz"});
    model.result("pg1").feature("arwv1").set("descr", "\u7535\u6d41\u5bc6\u5ea6");
    model.result("pg1").feature("arwv1").set("xnumber", 10);
    model.result("pg1").feature("arwv1").set("ynumber", 10);
    model.result("pg1").feature("arwv1").set("znumber", 5);
    model.result("pg1").feature("arwv1").set("color", "blue");
    model.result("pg1").feature("arwv1").create("sel1", "Selection");
    model.result("pg1").feature("arwv1").feature("sel1").selection().named("geom1_sel1");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("arwv2", "arwv1");
    model.result("pg1").run();
    model.result("pg1").feature("arwv2").set("color", "black");
    model.result("pg1").run();
    model.result("pg1").feature("arwv2").feature("sel1").selection().named("geom1_sel2");
    model.result("pg1").run();
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").run();
    model.result("pg2").label("\u78c1\u82af\u4e2d\u7684\u78c1\u901a\u5bc6\u5ea6");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").selection().geom("geom1", 3);
    model.result("pg2").selection().named("geom1_csel1_dom");
    model.result("pg2").create("slc1", "Slice");
    model.result("pg2").feature("slc1").set("quickplane", "zx");
    model.result("pg2").feature("slc1").set("quickynumber", 1);
    model.result("pg2").run();
    model.result("pg2").create("arwv1", "ArrowVolume");
    model.result("pg2").feature("arwv1").set("xnumber", 10);
    model.result("pg2").feature("arwv1").set("ynumber", 1);
    model.result("pg2").feature("arwv1").set("znumber", 10);
    model.result("pg2").feature("arwv1").set("arrowtype", "cone");
    model.result("pg2").feature("arwv1").set("scaleactive", true);
    model.result("pg2").feature("arwv1").set("scale", 30);
    model.result("pg2").feature("arwv1").set("color", "black");
    model.result("pg2").run();
    model.result("pg2").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").run();
    model.result("pg3").set("data", "dset2");
    model.result("pg3").label("\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b\uff0c\u7b97\u4f8b 1");
    model.result("pg3").set("twoyaxes", true);
    model.result("pg3").set("xlabelactive", true);
    model.result("pg3").set("xlabel", "\u65f6\u95f4\uff08\u79d2\uff09");
    model.result("pg3").set("ylabelactive", true);
    model.result("pg3").set("ylabel", "\u521d\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b (V)");
    model.result("pg3").set("yseclabelactive", true);
    model.result("pg3").set("yseclabel", "\u6b21\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b (V)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("markerpos", "datapoints");
    model.result("pg3").feature("glob1").set("linewidth", "preference");
    model.result("pg3").feature("glob1").set("expr", new String[]{"mf.VCoil_1"});
    model.result("pg3").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u538b"});
    model.result("pg3").feature("glob1").set("unit", new String[]{"V"});
    model.result("pg3").feature("glob1")
         .setIndex("descr", "\u521d\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b", 0);
    model.result("pg3").feature("glob1").set("linemarker", "cycle");
    model.result("pg3").feature("glob1").set("markerpos", "interp");
    model.result("pg3").feature("glob1").set("legendmethod", "manual");
    model.result("pg3").feature("glob1").setIndex("legends", "VCoil_1", 0);
    model.result("pg3").feature().duplicate("glob2", "glob1");
    model.result("pg3").run();
    model.result("pg3").feature("glob2").setIndex("expr", "mf.VCoil_2", 0);
    model.result("pg3").feature("glob2")
         .setIndex("descr", "\u6b21\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b", 0);
    model.result("pg3").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg3").feature("glob2").setIndex("legends", "VCoil_2", 0);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset2");
    model.result("pg4").label("\u7ed5\u7ec4\u4e2d\u7684\u7535\u6d41\uff0c\u7b97\u4f8b 1");
    model.result("pg4").set("twoyaxes", true);
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u65f6\u95f4\uff08\u79d2\uff09");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u521d\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u7535\u6d41(A)");
    model.result("pg4").set("yseclabelactive", true);
    model.result("pg4").set("yseclabel", "\u6b21\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u6d41(A)");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mf.ICoil_1"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u7ebf\u5708\u7535\u6d41"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"A"});
    model.result("pg4").feature("glob1").setIndex("descr", "\u521d\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u7535\u6d41", 0);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").feature("glob1").set("markerpos", "interp");
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "ICoil_1", 0);
    model.result("pg4").feature().duplicate("glob2", "glob1");
    model.result("pg4").run();
    model.result("pg4").feature("glob2").setIndex("expr", "mf.ICoil_2", 0);
    model.result("pg4").feature("glob2").setIndex("descr", "\u6b21\u7ea7\u7ed5\u7ec4\u4e2d\u7684\u7535\u6d41", 0);
    model.result("pg4").feature("glob2").set("plotonsecyaxis", true);
    model.result("pg4").feature("glob2").setIndex("legends", "ICoil_2", 0);
    model.result("pg4").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/mf", true);
    model.study("std3").feature("time").setSolveFor("/physics/cir", true);
    model.study("std3").label("\u7814\u7a76 3\uff0c\u7b97\u4f8b 2");
    model.study("std3").setGenPlots(false);
    model.study("std3").feature("time").set("tlist", "range(0,5e-4,0.05)");
    model.study("std3").feature("time").set("useadvanceddisable", true);
    model.study("std3").feature("time").set("disabledvariables", new String[]{"var1"});
    model.study("std3").feature("time").set("usesol", true);
    model.study("std3").feature("time").set("notsolmethod", "sol");
    model.study("std3").feature("time").set("notstudy", "std1");
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("v1").set("scalemethod", "none");
    model.sol("sol3").feature("t1").set("maxstepconstraintbdf", "const");
    model.sol("sol3").feature("t1").set("maxstepbdf", "2.5e-4");
    model.sol("sol3").feature("t1").feature("fc1").set("jtech", "onevery");
    model.sol("sol3").feature("t1").feature("fc1").set("maxiter", 25);
    model.sol("sol3").feature("t1").feature("dDef").set("linsolver", "pardiso");

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg3").run();
    model.result().duplicate("pg5", "pg3");
    model.result("pg5").run();
    model.result("pg5").label("\u7ed5\u7ec4\u4e2d\u7684\u611f\u5e94\u7535\u538b\uff0c\u7b97\u4f8b 2");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").run();

    model.study().create("std4");
    model.study("std4").create("emloss", "TimeToFrequencyLosses");
    model.study("std4").feature("emloss").set("fftinputstudy", "current");
    model.study("std4").feature("emloss").set("lossstarttime", "0");
    model.study("std4").feature("emloss").set("notsolnum", "auto");
    model.study("std4").feature("emloss").set("outputmap", new String[]{});
    model.study("std4").feature("emloss").setSolveFor("/physics/mf", true);
    model.study("std4").feature("emloss").setSolveFor("/physics/cir", true);
    model.study().create("std5");
    model.study("std5").create("emloss", "TimeToFrequencyLosses");
    model.study("std5").feature("emloss").set("fftinputstudy", "current");
    model.study("std5").feature("emloss").set("lossstarttime", "0");
    model.study("std5").feature("emloss").set("notsolnum", "auto");
    model.study("std5").feature("emloss").set("outputmap", new String[]{});
    model.study("std5").feature("emloss").setSolveFor("/physics/mf", true);
    model.study("std5").feature("emloss").setSolveFor("/physics/cir", true);
    model.study("std4").setGenPlots(false);
    model.study("std4").label("\u635f\u8017\u8ba1\u7b97\uff0c\u7b97\u4f8b 1");
    model.study("std5").setGenPlots(false);
    model.study("std5").label("\u635f\u8017\u8ba1\u7b97\uff0c\u7b97\u4f8b 2");
    model.study("std4").feature("emloss").set("fftinputstudy", "std2");
    model.study("std4").feature("emloss").set("useadvanceddisable", true);
    model.study("std4").feature("emloss").set("disabledvariables", new String[]{"var2"});
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.study("std5").feature("emloss").set("fftinputstudy", "std3");
    model.study("std5").feature("emloss").set("useadvanceddisable", true);
    model.study("std5").feature("emloss").set("disabledvariables", new String[]{"var1"});
    model.study("std5").createAutoSequences("all");

    model.sol("sol6").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").run();
    model.result("pg6").label("\u78c1\u82af\u7684\u5468\u671f\u5e73\u5747\u635f\u8017");
    model.result("pg6").set("edges", false);
    model.result("pg6").set("titletype", "manual");
    model.result("pg6")
         .set("title", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u7535\u78c1 (W/m<sup>3</sup>)\uff1a\u7b97\u4f8b  1\uff08\u5de6\uff09\uff0c\u7b97\u4f8b 2\uff08\u53f3\uff09");
    model.result("pg6").create("vol1", "Volume");
    model.result("pg6").feature("vol1").set("data", "dset4");
    model.result("pg6").feature("vol1").set("expr", "mf.Qh");
    model.result("pg6").feature("vol1").create("sel1", "Selection");
    model.result("pg6").feature("vol1").feature("sel1").selection().named("geom1_csel1_dom");
    model.result("pg6").run();
    model.result("pg6").feature("vol1").set("colortable", "Plasma");
    model.result("pg6").feature().duplicate("vol2", "vol1");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").set("data", "dset6");
    model.result("pg6").feature("vol2").set("colorlegend", false);
    model.result("pg6").feature("vol2").set("inheritplot", "vol1");
    model.result("pg6").feature("vol2").create("trn1", "Transformation");
    model.result("pg6").run();
    model.result("pg6").feature("vol2").feature("trn1").set("move", new int[]{100, -50, 0});
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().numerical().create("int1", "IntVolume");
    model.result().numerical("int1").set("data", "dset4");
    model.result().numerical("int1").selection().named("geom1_csel1_dom");
    model.result().numerical("int1").setIndex("expr", "mf.Qh", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u4f53\u79ef\u5206 1");

    return model;
  }

  public static Model run2(Model model) {
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").setResult();
    model.result().numerical("int1").set("data", "dset6");
    model.result().numerical("int1").set("table", "tbl1");
    model.result().numerical("int1").appendResult();
    model.result().numerical().duplicate("int2", "int1");
    model.result().numerical("int2").set("data", "dset7");
    model.result().numerical("int2").setIndex("expr", "mf.Qloss", 0);
    model.result().numerical("int2")
         .setIndex("descr", "\u4f53\u79ef\u635f\u8017\u5bc6\u5ea6\uff0c\u5468\u671f\u5e73\u5747", 0);
    model.result().table().create("tbl2", "Table");
    model.result().table("tbl2").comments("\u4f53\u79ef\u5206 2");
    model.result().numerical("int2").set("table", "tbl2");
    model.result().numerical("int2").setResult();
    model.result("pg1").run();

    model.title("E \u78c1\u82af\u53d8\u538b\u5668");

    model
         .description("\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u6267\u884c\u77ac\u6001\u4eff\u771f\uff0c\u6a21\u62df\u4e00\u4e2a\u5355\u76f8 E \u78c1\u82af\u53d8\u538b\u5668\u3002\u901a\u8fc7\u5728\u8f6f\u94a2\u78c1\u82af\u4e0a\u5f15\u5165\u975e\u7ebf\u6027 B-H \u66f2\u7ebf\u6548\u679c\uff0c\u53ef\u4ee5\u8ba1\u7b97\u78c1\u573a\u548c\u7535\u573a\u7684\u7a7a\u95f4\u5206\u5e03\u3001\u78c1\u9971\u548c\u6548\u5e94\u3001\u77ac\u6001\u54cd\u5e94\u4ee5\u53ca\u78c1\u901a\u91cf\u6cc4\u6f0f\u7b49\u3002\u5176\u4e2d\u6a21\u62df\u4e24\u79cd\u4e0d\u540c\u7248\u672c\u7684\u53d8\u538b\u5668\uff1a\u7b2c\u4e00\u79cd\u91c7\u7528\u5355\u531d\u6570\u6bd4\uff0c\u7b2c\u4e8c\u79cd\u91c7\u7528\u531d\u6570\u6bd4 1000\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();

    model.label("ecore_transformer.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
