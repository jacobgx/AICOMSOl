/*
 * nonlinear_harmonic.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:21 by COMSOL 6.3.0.290. */
public class nonlinear_harmonic {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbrn", "StructuralMembrane", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").set("geometricNonlinearity", true);
    model.study("std1").feature("stat").set("outputmap", new String[]{});
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").set("ngenAUX", "1");
    model.study("std1").feature("stat").set("goalngenAUX", "1");
    model.study("std1").feature("stat").setSolveFor("/physics/mbrn", true);
    model.study("std1").create("frlin", "Frequencylinearized");
    model.study("std1").feature("frlin").set("geometricNonlinearity", true);
    model.study("std1").feature("frlin").set("outputmap", new String[]{});
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").set("ngenAUX", "1");
    model.study("std1").feature("frlin").set("goalngenAUX", "1");
    model.study("std1").feature("frlin").setSolveFor("/physics/mbrn", true);

    model.param().set("R", "250[mm]");
    model.param().descr("R", "\u534a\u5f84");
    model.param().set("th", "0.2[mm]");
    model.param().descr("th", "\u539a\u5ea6");
    model.param().set("T0", "100[MPa]*th");
    model.param().descr("T0", "\u9884\u7d27\u529b");
    model.param().set("F0", "10[kPa]");
    model.param().descr("F0", "\u6fc0\u52b1\u8f7d\u8377\u5927\u5c0f");
    model.param().set("freq0", "50[Hz]");
    model.param().descr("freq0", "\u6fc0\u52b1\u9891\u7387");

    model.component("comp1").coordSystem().create("sys2", "Cylindrical");

    model.component("comp1").geom("geom1").run();
    model.component("comp1").geom("geom1").create("wp1", "WorkPlane");
    model.component("comp1").geom("geom1").feature("wp1").set("unite", true);
    model.component("comp1").geom("geom1").feature("wp1").geom().create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("wp1").geom().feature("c1").set("r", "R");
    model.component("comp1").geom("geom1").feature("wp1").geom().run("c1");
    model.component("comp1").geom("geom1").feature("wp1").geom().create("pt1", "Point");
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

    model.component("comp1").physics("mbrn").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("dmp1")
         .set("InputParameters", "DampingRatios");
    model.component("comp1").physics("mbrn").feature("lemm1").feature("dmp1").set("f1", 170);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("dmp1").set("zeta1", "2e-2");
    model.component("comp1").physics("mbrn").feature("lemm1").feature("dmp1").set("f2", 400);
    model.component("comp1").physics("mbrn").feature("lemm1").feature("dmp1").set("zeta2", "4e-2");
    model.component("comp1").physics("mbrn").feature("to1").set("d", "th");
    model.component("comp1").physics("mbrn").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("mbrn").feature("disp1").selection().all();
    model.component("comp1").physics("mbrn").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("mbrn").create("disp2", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp2").selection().set(3);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 0);
    model.component("comp1").physics("mbrn").feature("disp2").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("mbrn").create("disp3", "Displacement0", 0);
    model.component("comp1").physics("mbrn").feature("disp3").selection().set(1);
    model.component("comp1").physics("mbrn").feature("disp3").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mbrn").feature("disp3").setIndex("Direction", "prescribed", 1);
    model.component("comp1").physics("mbrn").create("el1", "EdgeLoad", 1);
    model.component("comp1").physics("mbrn").feature("el1").selection().all();
    model.component("comp1").physics("mbrn").feature("el1").set("coordinateSystem", "sys2");
    model.component("comp1").physics("mbrn").feature("el1")
         .set("forceReferenceLength", new String[]{"T0", "0", "0"});
    model.component("comp1").physics("mbrn").create("spf1", "SpringFoundation2", 2);
    model.component("comp1").physics("mbrn").feature("spf1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("spf1").set("kPerArea", new int[]{0, 0, 0, 0, 0, 0, 0, 0, 10});
    model.component("comp1").physics("mbrn").create("fl1", "FaceLoad", 2);
    model.component("comp1").physics("mbrn").feature("fl1").selection().set(1);
    model.component("comp1").physics("mbrn").feature("fl1").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl1").set("pressure", "F0");
    model.component("comp1").physics("mbrn").feature("fl1").set("harmonicPerturbation", true);
    model.component("comp1").physics("mbrn").feature("fl1").create("ph1", "Phase", 2);
    model.component("comp1").physics("mbrn").feature("fl1").feature("ph1").set("FPhScalar", "-pi/2");

    model.component("comp1").mesh("mesh1").autoMeshSize(7);

    model.study("std1").feature("frlin").set("plist", "freq0");
    model.study("std1").feature("frlin").set("useadvanceddisable", true);
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"mbrn/spf1"});
    model.study("std1").setGenPlots(false);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").label("\u4f4d\u79fb (mbrn)");
    model.result("pg1").set("showlegends", true);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"mbrn.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("differential", true);
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").label("\u4f4d\u79fb (mbrn)");
    model.result("pg1").run();
    model.result("pg1").set("phasetype", "manual");
    model.result("pg1").set("phase", -90);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "w");
    model.result("pg1").feature("surf1").set("unit", "mm");
    model.result("pg1").run();
    model.result().numerical().create("pev1", "EvalPoint");
    model.result().numerical("pev1").selection().set(3);
    model.result().numerical("pev1").setIndex("expr", "w", 0);
    model.result().numerical("pev1").setIndex("unit", "mm", 0);
    model.result().numerical("pev1").set("evalmethod", "lintotalpeak");
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u70b9\u8ba1\u7b97 1");
    model.result().numerical("pev1").set("table", "tbl1");
    model.result().numerical("pev1").setResult();

    model.component("comp1").physics("mbrn").create("init2", "init", 2);
    model.component("comp1").physics("mbrn").feature("init2").selection().set(1);
    model.component("comp1").physics("mbrn").feature("init2")
         .set("u", new String[]{"withsol('sol1',real(u))", "withsol('sol1',real(v))", "withsol('sol1',real(w))"});
    model.component("comp1").physics("mbrn").feature("init2")
         .set("du/dt", new String[]{"withsol('sol1',real(mbrn.u_tX))", "withsol('sol1',real(mbrn.u_tY))", "withsol('sol1',real(mbrn.u_tZ))"});
    model.component("comp1").physics("mbrn").create("fl2", "FaceLoad", 2);
    model.component("comp1").physics("mbrn").feature("fl2").selection().set(1);
    model.component("comp1").physics("mbrn").feature("fl2").set("forceType", "FollowerPressure");
    model.component("comp1").physics("mbrn").feature("fl2").set("pressure", "sin(2*pi*t*freq0)*F0");

    model.component("comp1").probe().create("point1", "Point");
    model.component("comp1").probe("point1").selection().set(3);
    model.component("comp1").probe("point1").set("expr", "w");
    model.component("comp1").probe("point1").set("unit", "mm");

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/mbrn", true);
    model.study("std2").feature("time").set("tlist", "0 range(3,1/20,4)/freq0");
    model.study("std2").feature("time").set("geometricNonlinearity", true);
    model.study("std2").feature("time").set("useadvanceddisable", true);
    model.study("std2").feature("time").set("disabledphysics", new String[]{"mbrn/spf1", "mbrn/fl1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.component("comp1").probe("point1").genResult("none");

    model.sol("sol3").runAll();

    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").setIndex("looplevel", 22, 0);
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").label("\u4f4d\u79fb (mbrn) 1");
    model.result("pg3").set("showlegends", true);
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"mbrn.disp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").label("\u4f4d\u79fb (mbrn) 1");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb - \u65f6\u57df");
    model.result("pg3").setIndex("looplevel", 17, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "w");
    model.result("pg3").feature("surf1").set("unit", "mm");
    model.result("pg3").run();
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").label("\u4f4d\u79fb");
    model.result("pg2").set("titletype", "manual");
    model.result("pg2").set("title", "\u5706\u76d8\u4e2d\u5fc3\u7684\u5782\u76f4\u4f4d\u79fb");
    model.result("pg2").set("ylabelactive", true);
    model.result("pg2").set("ylabel", "\u4f4d\u79fb (mm)");
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result("pg2").feature("tblp1").set("linewidth", 2);
    model.result("pg2").feature("tblp1").set("legend", false);
    model.result("pg2").set("window", "window1");
    model.result("pg2").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("titletype", "none");
    model.result("pg4").label("\u7ebf\u6027\u8c10\u6ce2 vs. \u77ac\u6001");
    model.result("pg4").set("xlabelactive", true);
    model.result("pg4").set("xlabel", "\u76f8\u4f4d (rad)");
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("ptgr1", "PointGraph");
    model.result("pg4").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg4").feature("ptgr1").set("linewidth", "preference");
    model.result("pg4").feature("ptgr1").selection().set(3);
    model.result("pg4").feature("ptgr1").set("expr", "w");
    model.result("pg4").feature("ptgr1").set("unit", "mm");
    model.result("pg4").feature("ptgr1").set("xdata", "phase");
    model.result("pg4").feature("ptgr1").set("xdataphaserange", "range(0,0.1,2*pi)");
    model.result("pg4").feature("ptgr1").set("linewidth", 2);
    model.result("pg4").feature("ptgr1").set("legend", true);
    model.result("pg4").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg4").feature("ptgr1").setIndex("legends", "\u7ebf\u6027\u8c10\u54cd\u5e94", 0);
    model.result("pg4").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg4").run();
    model.result("pg4").feature("ptgr2").set("data", "dset3");
    model.result("pg4").feature("ptgr2").setIndex("looplevelinput", "interp", 0);
    model.result("pg4").feature("ptgr2").setIndex("interp", "range(6e-2,1e-3,8e-2)", 0);
    model.result("pg4").feature("ptgr2").set("xdata", "expr");
    model.result("pg4").feature("ptgr2").set("xdataexpr", "(t-0.06)*2*pi*freq0");
    model.result("pg4").feature("ptgr2").setIndex("legends", "\u77ac\u6001", 0);
    model.result("pg4").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"mbrn/fl1", "mbrn/init2", "mbrn/fl2"});
    model.study("std1").feature("frlin").set("disabledphysics", new String[]{"mbrn/spf1", "mbrn/init2", "mbrn/fl2"});

    model.result("pg4").run();

    model.title("\u975e\u7ebf\u6027\u8c10\u6ce2\u54cd\u5e94");

    model
         .description("\u672c\u6559\u7a0b\u6f14\u793a\u5982\u4f55\u8bc4\u4f30\u5177\u6709\u9002\u5ea6\u975e\u7ebf\u6027\u7279\u6027\u7684\u7ed3\u6784\u7684\u8c10\u6ce2\u54cd\u5e94\u3002\u975e\u7ebf\u6027\u95ee\u9898\u53ea\u80fd\u901a\u8fc7\u65f6\u57df\u5206\u6790\u8fdb\u884c\u6c42\u89e3\u3002\u52a0\u901f\u8ba1\u7b97\u7684\u65b9\u6cd5\u662f\u4f7f\u7528\u7ebf\u6027\u5316\u9891\u7387\u54cd\u5e94\u5206\u6790\u6765\u63d0\u4f9b\u826f\u597d\u7684\u521d\u59cb\u6761\u4ef6\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("nonlinear_harmonic.mph");

    return model;
  }

  public static void main(String[] args) {
    run();
  }

}
