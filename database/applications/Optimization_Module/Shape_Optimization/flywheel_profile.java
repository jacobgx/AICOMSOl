/*
 * flywheel_profile.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:43 by COMSOL 6.3.0.290. */
public class flywheel_profile {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Optimization_Module\\Shape_Optimization");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.param().set("r0", "1[cm]");
    model.param().descr("r0", "\u98de\u8f6e\u5185\u534a\u5f84");
    model.param().set("r1", "60[cm]");
    model.param().descr("r1", "\u98de\u8f6e\u5916\u534a\u5f84");
    model.param().set("H0", "3[cm]");
    model.param().descr("H0", "\u98de\u8f6e\u521d\u59cb\u539a\u5ea6");
    model.param().set("omega", "2*pi*50[rad/s]");
    model.param().descr("omega", "\u89d2\u901f\u5ea6");
    model.param().set("pExp", "10");
    model.param().descr("pExp", "P \u6a21\u6307\u6570");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"r1-r0", "H0"});
    model.component("comp1").geom("geom1").feature("r1").set("pos", new String[]{"r0", "0"});
    model.component("comp1").geom("geom1").run();

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

    model.component("comp1").physics("solid").create("rotf1", "RotatingFrame", 2);
    model.component("comp1").physics("solid").feature("rotf1").set("Ovm", "omega");
    model.component("comp1").physics("solid").create("symp1", "SymmetryPlane", 1);
    model.component("comp1").physics("solid").feature("symp1").selection().set(2);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);

    model.component("comp1").massProp().create("mass1", "MassProperties");
    model.component("comp1").massProp("mass1").set("densitySource", "fromPhysics");

    model.component("comp1").cpl().create("aveop1", "Average");
    model.component("comp1").cpl("aveop1").set("axisym", true);
    model.component("comp1").cpl("aveop1").selection().all();

    model.study("std1").label("\u7814\u7a76 1\uff1a\u521d\u59cb\u8bbe\u8ba1");
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u521d\u59cb\u7ea6\u675f");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "aveop1(solid.mises^pExp)^(1/pExp)", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("unit", "N/m^2", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u5e73\u5747\u503c 1", 0);
    model.result().evaluationGroup("eg1").run();

    model.param().set("sigma0", "96.2[MPa]");
    model.param().descr("sigma0", "\u521d\u59cb p \u6a21\u5e94\u529b");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common().create("fsr1", "FreeShapeSymmetry");
    model.component("comp1").common("fsr1").selection().set(1, 4);
    model.component("comp1").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp1").common("fsb1").selection().set(3);
    model.component("comp1").common("fsb1").set("maximumDisplacement", "2*H0/3");
    model.component("comp1").common("fsb1").set("filterRadiusType", "Small");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("stress_Pnorm", "aveop1((solid.mises/sigma0)^pExp)^(1/pExp)");
    model.component("comp1").variable("var1")
         .descr("stress_Pnorm", "von Mises \u5e94\u529b\u7684\u6807\u5ea6 P \u6a21");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std1").feature("stat").setSolveFor("/frame/material1", false);
    model.study("std2").label("\u7814\u7a76 2\uff1a\u5f62\u72b6\u4f18\u5316");
    model.study("std2").createAutoSequences("sol");
    model.study("std2").createAutoSequences("jobs");

    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset2");
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
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1")
         .label("\u7814\u7a76 2\uff1a\u5f62\u72b6\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg3").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").set("frametype", "geometry");
    model.result("pg3").set("edgecolor", "gray");
    model.result("pg3").set("titletype", "none");
    model.result("pg3").create("line1", "Line");
    model.result("pg3").feature("line1").set("expr", "1");
    model.result("pg3").feature("line1").set("coloring", "uniform");
    model.result("pg3").feature("line1").set("color", "fromtheme");
    model.result("pg3").create("arwl1", "ArrowLine");
    model.result("pg3").feature("arwl1").set("data", "dset2g1");
    model.result("pg3").feature("arwl1").set("expr", new String[]{"fsd1.dRg", "fsd1.dZg"});
    model.result("pg3").feature("arwl1").set("scaleactive", true);
    model.result("pg3").feature("arwl1").set("scale", "1");
    model.result("pg3").feature("arwl1").set("placement", "uniform");
    model.result("pg3").feature("arwl1").set("arrowcount", 200);
    model.result("pg3").feature("arwl1").create("col1", "Color");
    model.result("pg3").feature("arwl1").feature("col1").set("expr", "fsd1.rel_disp");
    model.result("pg3").feature("arwl1").feature("col1").set("rangecoloractive", "on");
    model.result("pg3").feature("arwl1").feature("col1").set("rangecolormin", 0);
    model.result("pg3").feature("arwl1").feature("col1").set("rangecolormax", 1);
    model.result("pg3").feature("arwl1").create("sel1", "Selection");
    model.result("pg3").feature("arwl1").feature("sel1").selection().named("dsel_fsd1");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("arwl1").set("placement", "elements");

    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("optsolver", "ipopt");
    model.study("std2").feature("sho").set("optobj", new String[]{"comp1.mass1.Ip1"});
    model.study("std2").feature("sho").set("descr", new String[]{"\u4e00\u6b21\u60ef\u6027\u77e9\u4e3b\u503c"});
    model.study("std2").feature("sho")
         .set("constraintExpression", new String[]{"comp1.fsd1.relVolume", "comp1.stress_Pnorm"});
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").setIndex("constraintUbound", 1, 0);
    model.study("std2").feature("sho").setIndex("constraintLbound", "", 0);
    model.study("std2").feature("sho").setIndex("constraintUbound", 1, 1);
    model.study("std2").feature("sho").setIndex("constraintLbound", "", 1);
    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg3");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg1").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result().dataset().create("mir1", "Mirror2D");
    model.result().dataset("mir1").setIndex("genpoints", 1, 1, 0);
    model.result().dataset("mir1").setIndex("genpoints", 0, 1, 1);
    model.result().dataset("mir1").set("hasvar", true);
    model.result().dataset().duplicate("mir2", "mir1");
    model.result().dataset("mir2").set("data", "dset2");
    model.result("pg1").run();
    model.result("pg1").set("data", "mir2");
    model.result("pg1").set("titletype", "manual");
    model.result("pg1")
         .set("title", "\u8868\u9762\uff1avon Mises \u5e94\u529b\uff0c\u521d\u59cb\u8bbe\u8ba1\uff08\u5e95\u90e8\uff09\u548c\u4f18\u5316\u8bbe\u8ba1\uff08\u9876\u90e8\uff09");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf1").feature().remove("def");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "mir1");
    model.result("pg1").feature("surf2").set("inheritplot", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").feature("def").set("expr", new String[]{"0", "if(mir1side,1,-1)*-3*H0"});
    model.result("pg1").feature("surf2").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf2").feature("def").set("scale", 1);
    model.result("pg1").run();

    model.title("\u98de\u8f6e\u539a\u5ea6\u5206\u5e03\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u901a\u8fc7\u6c42\u89e3\u95ee\u9898\u6765\u786e\u5b9a\u98de\u8f6e\u7684\u6700\u4f73\u539a\u5ea6\u5206\u5e03\uff0c\u4ece\u800c\u5728\u4e0d\u589e\u52a0\u98de\u8f6e\u8d28\u91cf\u6216\u6700\u5927\u5e94\u529b\u7684\u60c5\u51b5\u4e0b\u5b9e\u73b0\u60ef\u6027\u77e9\u6700\u5927\u5316\u3002");

    model.label("flywheel_profile.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
