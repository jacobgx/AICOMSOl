/*
 * tower_sensitivity.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:15 by COMSOL 6.3.0.290. */
public class tower_sensitivity {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Sensitivity_and_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("truss", "Truss", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/truss", true);

    model.component("comp1").geom("geom1").insertFile("tower_sensitivity_geom_sequence.mph", "geom1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat1").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat1").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat1").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");
    model.component("comp1").coordSystem("sys2").setIndex("origin", "Lx/2", 0);
    model.component("comp1").coordSystem("sys2").setIndex("origin", "Ly/2", 1);

    model.component("comp1").common().create("cvf1", "ControlVariableField");
    model.component("comp1").common("cvf1").set("name", "Abar");
    model.component("comp1").common("cvf1").selection().geom("geom1", 1);
    model.component("comp1").common("cvf1").selection().all();
    model.component("comp1").common("cvf1").set("shapeFunctionType", "shdisc");
    model.component("comp1").common("cvf1").set("order", "0");
    model.component("comp1").common("cvf1").set("initialValue", "1");

    model.param().set("d1", "1[cm]");
    model.param().descr("d1", "\u5782\u76f4\u6746\u76f4\u5f84");
    model.param().set("d2", "5[mm]");
    model.param().descr("d2", "\u5bf9\u89d2\u548c\u6c34\u5e73\u6746\u76f4\u5f84");

    model.component("comp1").physics("truss").selection().named("geom1_boxsel5");
    model.component("comp1").physics("truss").feature("csd1").set("area", "pi/4*d1^2*Abar");
    model.component("comp1").physics("truss").create("csd2", "CrossSectionTruss", 1);
    model.component("comp1").physics("truss").feature("csd2").selection().named("geom1_unisel1");
    model.component("comp1").physics("truss").feature("csd2").set("area", "pi/4*d2^2*Abar");
    model.component("comp1").physics("truss").create("pin1", "Pinned", 0);
    model.component("comp1").physics("truss").feature("pin1").selection().named("geom1_boxsel1");
    model.component("comp1").physics("truss").create("pl1", "PointLoad", 0);
    model.component("comp1").physics("truss").feature("pl1").selection().named("geom1_boxsel2");
    model.component("comp1").physics("truss").feature("pl1").set("forcePoint", new String[]{"1[kN]", "0", "0"});

    model.group().create("lg1", "LoadGroup");

    model.component("comp1").physics("truss").feature("pl1").set("loadGroup", "lg1");
    model.component("comp1").physics("truss").create("pl2", "PointLoad", 0);
    model.component("comp1").physics("truss").feature("pl2").selection().named("geom1_boxsel2");
    model.component("comp1").physics("truss").feature("pl2").set("coordinateSystem", "sys2");
    model.component("comp1").physics("truss").feature("pl2").set("forcePoint", new String[]{"0", "1[kN]", "0"});

    model.group().create("lg2", "LoadGroup");

    model.component("comp1").physics("truss").feature("pl2").set("loadGroup", "lg2");
    model.component("comp1").physics("truss").create("avgr1", "AverageRotation", -1);
    model.component("comp1").physics("truss").feature("avgr1").selection("PointSelection").named("geom1_boxsel2");

    model.group("lg1").label("\u8f7d\u8377\u7ec4\uff1a\u5f2f\u66f2");
    model.group("lg1").paramName("lgB");
    model.group("lg2").label("\u8f7d\u8377\u7ec4\uff1a\u626d\u8f6c");
    model.group("lg2").paramName("lgT");

    model.study("std1").label("\u503e\u659c\u7075\u654f\u5ea6");
    model.study("std1").setGenPlots(false);
    model.study("std1").create("sens", "Sensitivity");
    model.study("std1").feature("sens").setIndex("optobj", "comp1.truss.avgr1.thY", 0);
    model.study("std1").feature("sens").setIndex("descr", "\u503e\u89d2", 0);
    model.study("std1").feature("stat").set("useloadcase", true);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std1").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std1").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std1").feature("stat").setIndex("loadcase", "\u5f2f\u66f2", 0);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std1").feature("stat").setIndex("loadcase", "\u626d\u8f6c", 1);
    model.study("std1").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "truss.avgr1.thY", 0);
    model.result().numerical("gev1").setIndex("unit", "deg", 0);
    model.result().numerical("gev1").setIndex("descr", "\u503e\u89d2", 0);
    model.result().numerical("gev1").setIndex("expr", "truss.avgr1.thZ", 1);
    model.result().numerical("gev1").setIndex("unit", "deg", 1);
    model.result().numerical("gev1").setIndex("descr", "\u59ff\u6001\u89d2", 1);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").run();
    model.result("pg1").label("\u503e\u659c\u7075\u654f\u5ea6");
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("edges", false);
    model.result("pg1").create("line1", "Line");
    model.result("pg1").feature("line1").set("expr", "fsens(Abar)");
    model.result("pg1").feature("line1").set("linetype", "tube");
    model.result("pg1").feature("line1").set("colorscalemode", "linearsymmetric");
    model.result("pg1").feature("line1").set("smooth", "internal");
    model.result("pg1").feature("line1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("line2", "line1");
    model.result("pg1").run();
    model.result("pg1").feature("line2").set("data", "dset1");
    model.result("pg1").feature("line2").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("line2").set("colortable", "Cividis");
    model.result("pg1").feature("line2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("line2").feature("trn1").set("move", new int[]{-4, 0, 0});
    model.result("pg1").run();
    model.result("pg1").create("arpt1", "ArrowPoint");
    model.result("pg1").feature("arpt1").set("expr", new String[]{"truss.fx", "truss.fy", "truss.fz"});
    model.result("pg1").feature("arpt1").set("descr", "\u8f7d\u8377");
    model.result("pg1").feature("arpt1").create("def1", "Deform");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("arpt1").set("inheritplot", "line1");
    model.result("pg1").feature("arpt1").set("inheritarrowscale", false);
    model.result("pg1").feature("arpt1").set("inheritcolor", false);
    model.result("pg1").feature("arpt1").set("inheritrange", false);
    model.result("pg1").feature().duplicate("arpt2", "arpt1");
    model.result("pg1").run();
    model.result("pg1").feature("arpt2").set("data", "dset1");
    model.result("pg1").feature("arpt2").setIndex("looplevel", 1, 0);
    model.result("pg1").feature("arpt2").set("inheritplot", "line2");
    model.result("pg1").feature("arpt2").create("trn1", "Transformation");
    model.result("pg1").run();
    model.result("pg1").feature("arpt2").feature("trn1").set("move", new int[]{-4, 0, 0});
    model.result("pg1").run();
    model.result("pg1").set("legendpos", "alternating");

    model.component("comp1").view("view1").set("showaxisorientation", false);

    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/truss", true);
    model.study("std2").feature("stat").set("useloadcase", true);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 1", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 0, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 0, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u8f7d\u8377\u5de5\u51b5 2", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 0);
    model.study("std2").feature("stat").setIndex("loadgroup", false, 1, 1);
    model.study("std2").feature("stat").setIndex("loadgroupweight", "1.0", 1, 1);
    model.study("std2").feature("stat").setIndex("loadcase", "\u5f2f\u66f2", 0);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 0, 0);
    model.study("std2").feature("stat").setIndex("loadcase", "\u626d\u8f6c", 1);
    model.study("std2").feature("stat").setIndex("loadgroup", true, 1, 1);
    model.study("std2").label("\u59ff\u6001\u7075\u654f\u5ea6");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("sens", "Sensitivity");
    model.study("std2").feature("sens").setIndex("optobj", "comp1.truss.avgr1.thZ", 0);
    model.study("std2").feature("sens").setIndex("descr", "\u59ff\u6001\u89d2", 0);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").set("data", "dset2");
    model.result("pg2").label("\u59ff\u6001\u7075\u654f\u5ea6");
    model.result("pg2").run();
    model.result("pg2").feature("arpt2").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").feature("line2").set("data", "dset2");
    model.result("pg2").run();
    model.result("pg2").run();

    model.title("\u6841\u67b6\u5854\u7684\u7075\u654f\u5ea6\u5206\u6790");

    model
         .description("\u5728\u8ba1\u7b97\u76ee\u6807\u51fd\u6570\u76f8\u5bf9\u4e8e\u591a\u4e2a\u63a7\u5236\u53d8\u91cf\u7684\u68af\u5ea6\u65f6\uff0c\u7075\u654f\u5ea6\u5206\u6790\u662f\u4e00\u79cd\u975e\u5e38\u6709\u6548\u7684\u65b9\u6cd5\u3002\u672c\u4f8b\u4f7f\u7528\u6841\u67b6\u5854\u9876\u90e8\u7684\u4fef\u4ef0\u548c\u504f\u8f6c\u4f5c\u4e3a\u76ee\u6807\u51fd\u6570\uff0c\u8ba1\u7b97\u8fd9\u4e9b\u89d2\u5ea6\u76f8\u5bf9\u4e8e\u5404\u6746\u4ef6\u76f4\u5f84\u53d8\u5316\u7684\u7075\u654f\u5ea6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("tower_sensitivity.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
