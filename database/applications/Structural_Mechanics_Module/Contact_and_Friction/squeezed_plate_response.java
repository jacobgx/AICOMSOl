/*
 * squeezed_plate_response.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:03 by COMSOL 6.3.0.290. */
public class squeezed_plate_response {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Contact_and_Friction");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.param().set("F", "1[kN/m^2]");
    model.param().descr("F", "\u5916\u52a0\u7b80\u8c10\u8377\u8f7d");
    model.param().set("ro", "1[m]");
    model.param().descr("ro", "\u677f\u534a\u5f84");
    model.param().set("ri", "0.2[m]");
    model.param().descr("ri", "\u677f\u5939\u6301\u5668\u534a\u5f84");
    model.param().set("th", "0.02[m]");
    model.param().descr("th", "\u677f\u539a");

    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new String[]{"ro", "th"});
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", "ri", 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new String[]{"ri", "2*th"});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new String[]{"0", "th"});
    model.component("comp1").geom("geom1").feature().duplicate("r3", "r2");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new String[]{"0", "-2*th"});
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

    model.component("comp1").physics("solid").create("icnt1", "InteriorContact", 1);
    model.component("comp1").physics("solid").feature("icnt1").selection().set(4, 6);
    model.component("comp1").physics("solid").feature("icnt1").set("ContactMethodCtrl", "AugmentedLagrange");
    model.component("comp1").physics("solid").feature("icnt1").set("tunedFor", "Speed");
    model.component("comp1").physics("solid").create("roll1", "Roller", 1);
    model.component("comp1").physics("solid").feature("roll1").selection().set(2);
    model.component("comp1").physics("solid").create("disp1", "Displacement1", 1);
    model.component("comp1").physics("solid").feature("disp1").selection().set(7);
    model.component("comp1").physics("solid").feature("disp1").setIndex("Direction", "prescribed", 2);
    model.component("comp1").physics("solid").feature("disp1").setIndex("U0", "-1[um]", 2);
    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 2, 3);

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(3);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 4);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(2, 4, 6, 7);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 24);
    model.component("comp1").mesh("mesh1").feature("size").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").setGenPlots(false);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 1);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("modenumber", "solid.mk");
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().dataset("dset1solidrev").set("defaultPlotIDs", new String[]{"modeShape3D|solid"});
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("data", "dset1solidrev");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b, 3D (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg1").feature("surf1").feature("def").set("descractive", true);
    model.result("pg1").label("\u632f\u578b, 3D (solid)");
    model.result("pg1").run();
    model.result("pg1").label("\u632f\u578b\uff0c\u56fa\u5b9a");

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("geometricNonlinearity", true);
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("stat").set("useadvanceddisable", true);
    model.study("std2").feature("stat").set("disabledphysics", new String[]{"solid/fix1"});
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 1);
    model.study("std2").feature("eig").set("useadvanceddisable", true);
    model.study("std2").feature("eig").set("disabledphysics", new String[]{"solid/fix1"});
    model.study("std2").setGenPlots(false);
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("modenumber", "solid.mk");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().dataset("dset2solidrev").set("defaultPlotIDs", new String[]{"modeShape3D|solid"});
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset2solidrev");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").label("\u632f\u578b, 3D (solid)");
    model.result("pg2").set("showlegends", false);
    model.result("pg2").create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg2").feature("surf1").set("threshold", "manual");
    model.result("pg2").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg2").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg2").feature("surf1").set("colortabletrans", "none");
    model.result("pg2").feature("surf1").set("colorscalemode", "linear");
    model.result("pg2").feature("surf1").create("def", "Deform");
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").label("\u632f\u578b, 3D (solid)");
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b\uff0c\u9884\u5e94\u529b");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 1);
    model.component("comp1").physics("solid").feature("bndl1").selection().set(13);
    model.component("comp1").physics("solid").feature("bndl1")
         .set("forceReferenceArea", new String[]{"0", "0", "-F"});
    model.component("comp1").physics("solid").feature("bndl1").set("harmonicPerturbation", true);

    model.param().set("freq_ref", "24.3[Hz]");
    model.param().descr("freq_ref", "\u53c2\u8003\u9891\u7387");

    model.study().create("std3");
    model.study("std3").create("stat", "Stationary");
    model.study("std3").feature("stat").set("plotgroup", "Default");
    model.study("std3").feature("stat").set("geometricNonlinearity", true);
    model.study("std3").feature("stat").set("outputmap", new String[]{});
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").set("ngenAUX", "1");
    model.study("std3").feature("stat").set("goalngenAUX", "1");
    model.study("std3").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std3").create("frlin", "Frequencylinearized");
    model.study("std3").feature("frlin").set("plotgroup", "Default");
    model.study("std3").feature("frlin").set("geometricNonlinearity", true);
    model.study("std3").feature("frlin").set("outputmap", new String[]{});
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").set("ngenAUX", "1");
    model.study("std3").feature("frlin").set("goalngenAUX", "1");
    model.study("std3").feature("frlin").setSolveFor("/physics/solid", true);
    model.study("std3").feature("stat").set("useadvanceddisable", true);
    model.study("std3").feature("stat").set("disabledphysics", new String[]{"solid/fix1"});
    model.study("std3").feature("frlin").set("plist", "freq_ref*range(0.95,5e-3,1.05)");
    model.study("std3").feature("frlin").set("useadvanceddisable", true);
    model.study("std3").feature("frlin").set("disabledphysics", new String[]{"solid/fix1"});
    model.study("std3").setGenPlots(false);
    model.study("std3").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().dataset().create("dset4solidrev", "Revolve2D");
    model.result().dataset("dset4solidrev").set("data", "dset4");
    model.result().dataset("dset4solidrev").set("revangle", 225);
    model.result().dataset("dset4solidrev").set("startangle", -90);
    model.result().dataset("dset4solidrev").set("modenumber", "solid.mk");
    model.result().dataset("dset4solidrev").set("hasspacevars", true);
    model.result().dataset("dset4solidrev")
         .set("defaultPlotIDs", new String[]{"stress3D|solid", "boundaryLoads|solid"});
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset4solidrev");
    model.result("pg3").setIndex("looplevel", 21, 0);
    model.result("pg3").label("\u5e94\u529b, 3D (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("differential", true);
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("differential", true);
    model.result("pg3").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").feature("surf1").feature("def").set("descractive", true);
    model.result("pg3").label("\u5e94\u529b, 3D (solid)");
    model.result("pg3").run();
    model.result("pg3").label("\u4f4d\u79fb\uff0c\u9891\u7387\u54cd\u5e94");
    model.result("pg3").setIndex("looplevel", 11, 0);
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "solid.disp");
    model.result("pg3").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").set("showlegends", false);
    model.result("pg3").run();
    model.result("pg3").set("showlegends", true);
    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 1);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection().set(1, 2, 3, 4);
    model.result("pg4").create("arwl1", "ArrowLine");
    model.result("pg4").feature("arwl1").set("arrowbase", "head");
    model.result("pg4").feature("arwl1").set("expr", new String[]{"solid.icnt1.Tnr", "solid.icnt1.Tnz"});
    model.result("pg4").feature("arwl1").set("placement", "gausspoints");
    model.result("pg4").feature("arwl1").set("gporder", 4);
    model.result("pg4").feature("arwl1").label("\u5185\u90e8\u63a5\u89e6 1, \u538b\u529b");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg4").feature("arwl1").set("inheritplot", "none");
    model.result("pg4").feature("arwl1").set("color", "green");
    model.result("pg4").feature("arwl1").create("col", "Color");
    model.result("pg4").feature("arwl1").feature("col").set("colortable", "Rainbow");
    model.result("pg4").feature("arwl1").feature("col").set("colortabletrans", "none");
    model.result("pg4").feature("arwl1").feature("col").set("colorscalemode", "linear");
    model.result("pg4").feature("arwl1").feature("col").set("colordata", "arrowlength");
    model.result("pg4").feature("arwl1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("arwl1").feature("col").set("topcolor", "green");
    model.result("pg4").feature("arwl1").feature("col").set("bottomcolor", "custom");
    model.result("pg4").feature("arwl1").feature("col")
         .set("custombottomcolor", new double[]{0.509804, 0.54902, 0.509804});
    model.result("pg4").feature("arwl1").create("def", "Deform");
    model.result("pg4").feature("arwl1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg4").feature("arwl1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("arwl1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("arwl1").feature("def").set("scale", 1);
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").label("\u63a5\u89e6\u529b (solid)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").label("\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg5").set("data", "dset4");
    model.result("pg5").set("xlabelactive", true);
    model.result("pg5").set("xlabel", "\u9891\u7387 (Hz)");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").set("ylabel", "\u4f4d\u79fb\u5927\u5c0f (mm)");
    model.result("pg5").create("ptgr1", "PointGraph");
    model.result("pg5").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg5").feature("ptgr1").set("linewidth", "preference");
    model.result("pg5").feature("ptgr1").selection().set(10);
    model.result("pg5").feature("ptgr1").set("expr", "solid.uAmpZ");
    model.result("pg5").feature("ptgr1").set("descr", "\u4f4d\u79fb\u5927\u5c0f\uff0cZ \u5206\u91cf");
    model.result("pg5").feature("ptgr1").set("unit", "mm");
    model.result("pg5").feature("ptgr1").create("gmrk1", "GraphMarker");
    model.result("pg5").feature("ptgr1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").feature("gmrk1").set("display", "max");
    model.result("pg5").feature("ptgr1").feature("gmrk1").set("inclxcoord", true);
    model.result("pg5").feature("ptgr1").feature("gmrk1").set("includeunit", true);
    model.result("pg5").feature("ptgr1").feature("gmrk1").set("precision", 3);
    model.result("pg5").run();

    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "F", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "F", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std1").feature("param").setIndex("pname", "ri", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0.2,0.2,0.8)", 0);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol6");
    model.sol("sol6").study("std1");
    model.sol("sol6").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol6");
    model.batch("p1").run("compute");

    model.result("pg1").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u7279\u5f81\u9891\u7387\uff0c\u56fa\u5b9a");
    model.result().evaluationGroup("eg1").set("data", "dset6");
    model.result().evaluationGroup("eg1").set("includeparameters", false);
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "ri", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "real(freq)", 1);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "\u9891\u7387", 1);
    model.result().evaluationGroup("eg1").run();

    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "F", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "F", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std2").feature("param").setIndex("pname", "ri", 0);
    model.study("std2").feature("param").setIndex("plistarr", "range(0.2,0.2,0.8)", 0);
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol11");
    model.sol("sol11").study("std2");
    model.sol("sol11").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol11");
    model.batch("p2").run("compute");

    model.result("pg2").run();
    model.result().evaluationGroup().duplicate("eg2", "eg1");
    model.result().evaluationGroup("eg2").label("\u7279\u5f81\u9891\u7387\uff0c\u9884\u5e94\u529b");
    model.result().evaluationGroup("eg2").set("data", "dset7");
    model.result().evaluationGroup("eg2").run();

    model.func().create("int1", "Interpolation");
    model.func("int1").set("source", "resultTable");
    model.func("int1").set("resultTable", "evalGroup:eg2");
    model.func("int1").setIndex("argunit", "m", 0);
    model.func("int1").setEntry("funcnames", "col2", "freq_ref");
    model.func("int1").setIndex("fununit", "Hz", 0);
    model.func("int1").set("interp", "neighbor");

    model.param().set("freq_ref", "freq_ref(ri)");

    model.study("std3").create("param", "Parametric");
    model.study("std3").feature("param").setIndex("pname", "F", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std3").feature("param").setIndex("pname", "F", 0);
    model.study("std3").feature("param").setIndex("plistarr", "", 0);
    model.study("std3").feature("param").setIndex("punit", "N/m^2", 0);
    model.study("std3").feature("param").setIndex("pname", "ri", 0);
    model.study("std3").feature("param").setIndex("plistarr", "range(0.2,0.2,0.8)", 0);
    model.study("std3").createAutoSequences("all");

    model.sol().create("sol16");
    model.sol("sol16").study("std3");
    model.sol("sol16").label("\u53c2\u6570\u5316\u89e3 3");

    model.batch("p3").feature("so1").set("psol", "sol16");
    model.batch("p3").run("compute");

    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").set("data", "dset8");
    model.result("pg5").run();
    model.result("pg5").feature("ptgr1").set("legend", true);
    model.result("pg5").feature("ptgr1").set("autopoint", false);
    model.result("pg5").run();
    model.result().evaluationGroup().create("eg3", "EvaluationGroup");
    model.result().evaluationGroup("eg3").label("\u7279\u5f81\u9891\u7387\uff0c\u9891\u7387\u54cd\u5e94");
    model.result().evaluationGroup("eg3").set("data", "dset8");
    model.result().evaluationGroup("eg3").set("includeparameters", false);
    model.result().evaluationGroup("eg3").set("concatenation", "vertical");
    model.result().evaluationGroup("eg3").create("pev1", "EvalPoint");
    model.result().evaluationGroup("eg3").feature("pev1").set("data", "dset8");
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("looplevelinput", "manual", 1);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("looplevel", new int[]{1}, 1);
    model.result().evaluationGroup("eg3").feature("pev1").selection().set(10);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("expr", "solid.uAmpZ", 0);
    model.result().evaluationGroup("eg3").feature("pev1").setIndex("unit", "mm", 0);
    model.result().evaluationGroup("eg3").feature("pev1").set("dataseries", "maximum");
    model.result().evaluationGroup("eg3").feature("pev1").set("includeparam", true);
    model.result().evaluationGroup("eg3").run();
    model.result().evaluationGroup("eg3").feature().duplicate("pev2", "pev1");
    model.result().evaluationGroup("eg3").feature("pev2").setIndex("looplevel", new int[]{2}, 1);
    model.result().evaluationGroup("eg3").feature().duplicate("pev3", "pev2");
    model.result().evaluationGroup("eg3").feature("pev3").setIndex("looplevel", new int[]{3}, 1);
    model.result().evaluationGroup("eg3").feature().duplicate("pev4", "pev3");
    model.result().evaluationGroup("eg3").feature("pev4").setIndex("looplevel", new int[]{4}, 1);
    model.result().evaluationGroup("eg3").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u56fa\u6709\u9891\u7387");
    model.result("pg6").set("data", "none");
    model.result("pg6").set("legendpos", "upperleft");
    model.result("pg6").create("tblp1", "Table");
    model.result("pg6").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg6").feature("tblp1").set("linewidth", "preference");
    model.result("pg6").feature("tblp1").set("source", "evaluationgroup");
    model.result("pg6").feature("tblp1").label("\u7279\u5f81\u9891\u7387\uff0c\u56fa\u5b9a");
    model.result("pg6").feature("tblp1").set("legend", true);
    model.result("pg6").feature("tblp1").set("legendmethod", "manual");
    model.result("pg6").feature("tblp1").setIndex("legends", "\u56fa\u5b9a", 0);
    model.result("pg6").feature().duplicate("tblp2", "tblp1");
    model.result("pg6").run();
    model.result("pg6").feature("tblp2").label("\u7279\u5f81\u9891\u7387\uff0c\u9884\u5e94\u529b");
    model.result("pg6").feature("tblp2").set("evaluationgroup", "eg2");
    model.result("pg6").feature("tblp2").setIndex("legends", "\u9884\u5e94\u529b\uff0c\u7279\u5f81\u9891\u7387", 0);
    model.result("pg6").feature().duplicate("tblp3", "tblp2");
    model.result("pg6").run();
    model.result("pg6").feature("tblp3").label("\u7279\u5f81\u9891\u7387\uff0c\u9891\u7387\u54cd\u5e94");
    model.result("pg6").feature("tblp3").set("xaxisdata", 1);
    model.result("pg6").feature("tblp3").set("plotcolumninput", "manual");
    model.result("pg6").feature("tblp3").set("plotcolumns", new int[]{2});
    model.result("pg6").feature("tblp3").set("linestyle", "none");
    model.result("pg6").feature("tblp3").set("linemarker", "point");
    model.result("pg6").feature("tblp3").setIndex("legends", "\u9884\u5e94\u529b\uff0c\u9891\u7387\u54cd\u5e94", 0);
    model.result("pg6").run();
    model.result().dataset("dset1solidrev").set("data", "dset6");
    model.result().dataset("dset2solidrev").set("data", "dset7");
    model.result().dataset("dset4solidrev").set("data", "dset8");
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{1, 1});
    model.result("pg1").run();
    model.result("pg2").run();
    model.result("pg2").set("looplevel", new int[]{1, 1});
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").set("looplevel", new int[]{11, 1});
    model.result("pg3").run();

    model.title("\u6324\u538b\u677f\u7684\u632f\u52a8");

    model
         .description("\u672c\u6a21\u578b\u9610\u8ff0\u5728\u6c42\u89e3\u63a5\u89e6\u95ee\u9898\u65f6\uff0c\u5982\u4f55\u6267\u884c\u7279\u5f81\u9891\u7387\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\u3002\u4e00\u5757\u5706\u5f62\u8584\u677f\u5728\u4e24\u5757\u8f83\u5c0f\u7684\u5706\u5f62\u677f\u4e4b\u95f4\u53d7\u5230\u6324\u538b\u3002\u672c\u4f8b\u5c06\u9891\u7387\u54cd\u5e94\u5206\u6790\u7684\u5e45\u5ea6\u5cf0\u503c\u4e0e\u5728\u63a5\u89e6\u72b6\u6001\u4e0b\u8ba1\u7b97\u7684\u56fa\u6709\u9891\u7387\u8fdb\u884c\u6bd4\u8f83\uff0c\u5e76\u4e0e\u7406\u60f3\u60c5\u51b5\uff08\u5373\uff0c\u5c06\u4e00\u5757\u73af\u5f62\u677f\u5939\u5728\u5185\u534a\u5f84\u5904\uff09\u4e0b\u7684\u56fa\u6709\u9891\u7387\u8fdb\u884c\u6bd4\u8f83\u3002\n\n\u5176\u4e2d\u6c42\u89e3\u4e09\u4e2a\u95ee\u9898\u5e76\u6bd4\u8f83\u5206\u6790\u7ed3\u679c\uff0c\u901a\u8fc7\u6a21\u578b\u65b9\u6cd5\u5b9e\u73b0\u4e86\u8fd9\u4e00\u5e8f\u5217\u7684\u81ea\u52a8\u5316\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol4").clearSolutionData();
    model.sol("sol6").clearSolutionData();
    model.sol("sol7").clearSolutionData();
    model.sol("sol8").clearSolutionData();
    model.sol("sol9").clearSolutionData();
    model.sol("sol10").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol11").clearSolutionData();
    model.sol("sol12").clearSolutionData();
    model.sol("sol13").clearSolutionData();
    model.sol("sol14").clearSolutionData();
    model.sol("sol15").clearSolutionData();
    model.sol("sol5").clearSolutionData();
    model.sol("sol16").clearSolutionData();
    model.sol("sol17").clearSolutionData();
    model.sol("sol18").clearSolutionData();
    model.sol("sol19").clearSolutionData();
    model.sol("sol20").clearSolutionData();

    model.label("squeezed_plate_response.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
