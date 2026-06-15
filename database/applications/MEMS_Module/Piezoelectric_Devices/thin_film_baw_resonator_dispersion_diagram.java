/*
 * thin_film_baw_resonator_dispersion_diagram.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:02 by COMSOL 6.3.0.290. */
public class thin_film_baw_resonator_dispersion_diagram {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\MEMS_Module\\Piezoelectric_Devices");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");
    model.component("comp1").physics("solid").create("pzm1", "PiezoelectricMaterialModel");
    model.component("comp1").physics("solid").feature("pzm1").selection().all();
    model.component("comp1").physics().create("es", "Electrostatics", "geom1");
    model.component("comp1").physics("es").create("ccnp1", "ChargeConservationPiezo");
    model.component("comp1").physics("es").feature("ccnp1").selection().all();

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 2);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("shift", "1[Hz]");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("linpsolnum", "auto");
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("filtereigdescription", new String[]{"Damped natural frequency"});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std1").feature("eig").setSolveFor("/physics/es", true);
    model.study("std1").feature("eig").setSolveFor("/multiphysics/pze1", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.component("comp1").geom("geom1").lengthUnit("\u00b5m");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{1000, 16.7});
    model.component("comp1").geom("geom1").feature("r1").set("base", "center");
    model.component("comp1").geom("geom1").feature("r1").setIndex("layer", 50, 0);
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", true);
    model.component("comp1").geom("geom1").feature("r1").set("layerbottom", false);
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").feature().duplicate("r2", "r1");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{1000, 0.2});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new double[]{0, -1.25});
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{500, 0.2});
    model.component("comp1").geom("geom1").feature("r3").set("base", "center");
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{0, 8.45});
    model.component("comp1").geom("geom1").runPre("fin");

    model.component("comp1").view("view1").axis().set("viewscaletype", "automatic");

    model.component("comp1").coordSystem().create("pml1", "PML");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").coordSystem("pml1").selection().set(1, 2, 3, 8, 9, 10);
    model.component("comp1").coordSystem("pml1").set("PMLfactor", "5");
    model.component("comp1").coordSystem("pml1").set("PMLgamma", "2");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Anisotropic", "Anisotropic", "Anisotropic");
    model.component("comp1").material("mat1").label("Si - Silicon (single-crystal, anisotropic)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.7058823529411765});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.7);
    model.component("comp1").material("mat1").set("roughness", 0.5);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "2330[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("Anisotropic").label("Anisotropic");
    model.component("comp1").material("mat1").propertyGroup("Anisotropic")
         .set("D", new String[]{"166[GPa]", "64[GPa]", "64[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "64[GPa]", "166[GPa]", "64[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "64[GPa]", "64[GPa]", "166[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", 
         "0[GPa]", "80[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]", "0[GPa]", 
         "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "0[GPa]", "80[GPa]"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").label("Al - Aluminum");
    model.component("comp1").material("mat2").set("family", "aluminum");
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]", "0", "0", "0", "35.5e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]", "0", "0", "0", "23.1e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "904[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "2700[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]", "0", "0", "0", "237[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "70.0e9[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "0.35");
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat3").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat3").label("Zinc Oxide");
    model.component("comp1").material("mat3").set("family", "custom");
    model.component("comp1").material("mat3").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat3").set("diffuse", "custom");
    model.component("comp1").material("mat3")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("ambient", "custom");
    model.component("comp1").material("mat3")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat3").set("noise", true);
    model.component("comp1").material("mat3").set("fresnel", 0.9);
    model.component("comp1").material("mat3").set("roughness", 0.1);
    model.component("comp1").material("mat3").set("diffusewrap", 0);
    model.component("comp1").material("mat3").set("reflectance", 0);
    model.component("comp1").material("mat3").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat3").propertyGroup("def")
         .set("relpermittivity", new String[]{"8.5446", "0", "0", "0", "8.5446", "0", "0", "0", "10.204"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", "5680[kg/m^3]");
    model.component("comp1").material("mat3").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("sE", new String[]{"7.86e-012[1/Pa]", "-3.43e-012[1/Pa]", "-2.21e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-3.43e-012[1/Pa]", "7.86e-012[1/Pa]", "-2.21e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-2.21e-012[1/Pa]", "-2.21e-012[1/Pa]", "6.94e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "2.36e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.36e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "2.26e-011[1/Pa]"});
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-5.43e-012[C/N]", "0[C/N]", "0[C/N]", "-5.43e-012[C/N]", "0[C/N]", "0[C/N]", "1.167e-011[C/N]", "0[C/N]", 
         "-1.134e-011[C/N]", "0[C/N]", "-1.134e-011[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat3").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"9.16", "0", "0", "0", "9.16", "0", "0", "0", "12.64"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("cE", new String[]{"2.09714e+011[Pa]", "1.2114e+011[Pa]", "1.05359e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "1.2114e+011[Pa]", "2.09714e+011[Pa]", "1.05359e+011[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "1.05359e+011[Pa]", "1.05359e+011[Pa]", "2.11194e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "4.23729e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.23729e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "4.42478e+010[Pa]"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-0.567005[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-0.567005[C/m^2]", "0[C/m^2]", "0[C/m^2]", "1.32044[C/m^2]", "0[C/m^2]", 
         "-0.480508[C/m^2]", "0[C/m^2]", "-0.480508[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat3").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"8.5446", "0", "0", "0", "8.5446", "0", "0", "0", "10.204"});
    model.component("comp1").material("mat2").selection().set(2, 5, 7, 9);
    model.component("comp1").material("mat3").selection().set(3, 6, 10);

    model.component("comp1").physics("solid").prop("d").set("d", "1.7[mm]");
    model.component("comp1").physics("solid").prop("cref").set("cref", "9000[m/s]");
    model.component("comp1").physics("solid").feature("pzm1").selection().set(3, 6, 10);
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 2);
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s", 0.001);
    model.component("comp1").physics("solid").feature("pzm1").create("dels1", "DielectricLoss", 2);
    model.component("comp1").physics("solid").feature("pzm1").feature("dels1").set("eta_epsilonS_mat", "userdef");
    model.component("comp1").physics("solid").feature("pzm1").feature("dels1")
         .set("eta_epsilonS", new double[]{0.01, 0, 0, 0, 0.01, 0, 0, 0, 0.01});
    model.component("comp1").physics("solid").create("lemm2", "LinearElasticModel", 2);
    model.component("comp1").physics("solid").feature("lemm2").selection().set(1, 4, 8);
    model.component("comp1").physics("solid").feature("lemm2").set("SolidModel", "Anisotropic");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(1, 3, 5, 27, 28, 29);
    model.component("comp1").physics("es").selection().set(3, 6, 10);
    model.component("comp1").physics("es").prop("d").set("d", "1.7[mm]");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(6, 13, 25);
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(16);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Voltage");

    model.component("comp1").mesh("mesh1").create("map1", "Map");
    model.component("comp1").mesh("mesh1").feature("map1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").selection().set(2, 21);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 10);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis2", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").selection().set(9);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis2").set("numelem", 100);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis3", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").selection().set(8);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis3").set("numelem", 2);
    model.component("comp1").mesh("mesh1").feature("map1").create("dis4", "Distribution");
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").selection().set(12);
    model.component("comp1").mesh("mesh1").feature("map1").feature("dis4").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("eig").set("eigmethod", "region");
    model.study("std1").feature("eig").set("appnreigs", 6);
    model.study("std1").feature("eig").set("eigunit", "MHz");
    model.study("std1").feature("eig").set("eigsr", 220);
    model.study("std1").feature("eig").set("eiglr", 230);
    model.study("std1").feature("eig").set("eigli", 0.1);
    model.study("std1").showAutoSequences("all");

    model.sol("sol1").feature("e1").set("eigref", "2e8");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset1");
    model.result().evaluationGroup("std1EvgFrq").label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1EvgFrq").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "2*pi*freq", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "rad/s", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u89d2\u9891\u7387", 0);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "imag(freq)/abs(freq)", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u963b\u5c3c\u6bd4", 1);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("expr", "abs(freq)/imag(freq)/2", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1EvgFrq").feature("gev1").setIndex("descr", "\u54c1\u8d28\u56e0\u5b50", 2);
    model.result().evaluationGroup("std1EvgFrq").run();
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u7535\u52bf (es)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").set("showlegendsmaxmin", true);
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("solutionparams", "parent");
    model.result("pg2").feature("surf1").set("expr", "V");
    model.result("pg2").feature("surf1").set("colortable", "Dipole");
    model.result("pg2").feature("surf1").set("showsolutionparams", "on");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature().create("str1", "Streamline");
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("solutionparams", "parent");
    model.result("pg2").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg2").feature("str1").set("titletype", "none");
    model.result("pg2").feature("str1").set("posmethod", "uniform");
    model.result("pg2").feature("str1").set("udist", 0.02);
    model.result("pg2").feature("str1").set("maxlen", 0.4);
    model.result("pg2").feature("str1").set("maxsteps", 5000);
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("inheritcolor", false);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("showsolutionparams", "on");
    model.result("pg2").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg2").feature("str1").set("data", "parent");
    model.result("pg2").feature("str1").selection().geom("geom1", 1);
    model.result("pg2").feature("str1").selection().set(5, 6, 7, 12, 13, 14, 16, 19, 24, 25, 26, 29);
    model.result("pg2").feature("str1").set("inheritplot", "surf1");
    model.result("pg2").feature("str1").feature().create("col1", "Color");
    model.result("pg2").feature("str1").feature("col1").set("expr", "V");
    model.result("pg2").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg2").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg2").feature("str1").feature().create("filt1", "Filter");
    model.result("pg2").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u7535\u573a (es)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").set("showlegendsmaxmin", true);
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "es.normE");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg3").feature().create("str1", "Streamline");
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("solutionparams", "parent");
    model.result("pg3").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg3").feature("str1").set("titletype", "none");
    model.result("pg3").feature("str1").set("posmethod", "uniform");
    model.result("pg3").feature("str1").set("udist", 0.02);
    model.result("pg3").feature("str1").set("maxlen", 0.4);
    model.result("pg3").feature("str1").set("maxsteps", 5000);
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("inheritcolor", false);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("showsolutionparams", "on");
    model.result("pg3").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg3").feature("str1").set("data", "parent");
    model.result("pg3").feature("str1").selection().geom("geom1", 1);
    model.result("pg3").feature("str1").selection().set(5, 6, 7, 12, 13, 14, 16, 19, 24, 25, 26, 29);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().evaluationGroup().create("std1mpf1", "EvaluationGroup");
    model.result().evaluationGroup("std1mpf1").set("data", "dset1");
    model.result().evaluationGroup("std1mpf1").label("\u53c2\u4e0e\u56e0\u5b50 (\u7814\u7a76 1)");
    model.result().evaluationGroup("std1mpf1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormX", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u5e73\u79fb", 0);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormY", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u5e73\u79fb", 1);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfLnormZ", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u5e73\u79fb", 2);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormX", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cX \u65cb\u8f6c", 3);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormY", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cY \u65cb\u8f6c", 4);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.pfRnormZ", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "1", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u53c2\u4e0e\u56e0\u5b50\uff0c\u5f52\u4e00\u5316\uff0cZ \u65cb\u8f6c", 5);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLX", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u5e73\u79fb", 6);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLY", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u5e73\u79fb", 7);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffLZ", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u5e73\u79fb", 8);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRX", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cX \u65cb\u8f6c", 9);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRY", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cY \u65cb\u8f6c", 10);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("expr", "mpf1.mEffRZ", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1").setIndex("unit", "kg*m^2", 11);
    model.result().evaluationGroup("std1mpf1").feature("gev1")
         .setIndex("descr", "\u6709\u6548\u6a21\u6001\u8d28\u91cf\uff0cZ \u65cb\u8f6c", 11);
    model.result().evaluationGroup("std1mpf1").run();
    model.result("pg1").run();

    model.component("comp1").view("view1").axis().set("viewscaletype", "none");

    model.result("pg1").run();
    model.result("pg1").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("freq").set("plist", "range(215,0.1,235)[MHz]");
    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result().create("pg4", "PlotGroup2D");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 201, 0);
    model.result("pg4").label("\u5e94\u529b (solid)");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("resolution", "normal");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg5", "PlotGroup2D");
    model.result("pg5").label("\u7535\u52bf (es) 1");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").setIndex("looplevel", 201, 0);
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").set("showlegendsmaxmin", true);
    model.result("pg5").feature().create("surf1", "Surface");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("solutionparams", "parent");
    model.result("pg5").feature("surf1").set("expr", "V");
    model.result("pg5").feature("surf1").set("colortable", "Dipole");
    model.result("pg5").feature("surf1").set("showsolutionparams", "on");
    model.result("pg5").feature("surf1").set("data", "parent");
    model.result("pg5").feature().create("str1", "Streamline");
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("solutionparams", "parent");
    model.result("pg5").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg5").feature("str1").set("titletype", "none");
    model.result("pg5").feature("str1").set("posmethod", "uniform");
    model.result("pg5").feature("str1").set("udist", 0.02);

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg5").feature("str1").set("maxlen", 0.4);
    model.result("pg5").feature("str1").set("maxsteps", 5000);
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("inheritcolor", false);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("showsolutionparams", "on");
    model.result("pg5").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg5").feature("str1").set("data", "parent");
    model.result("pg5").feature("str1").selection().geom("geom1", 1);
    model.result("pg5").feature("str1").selection().set(5, 6, 7, 12, 13, 14, 16, 19, 24, 25, 26, 29);
    model.result("pg5").feature("str1").set("inheritplot", "surf1");
    model.result("pg5").feature("str1").feature().create("col1", "Color");
    model.result("pg5").feature("str1").feature("col1").set("expr", "V");
    model.result("pg5").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg5").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg5").feature("str1").feature().create("filt1", "Filter");
    model.result("pg5").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg6", "PlotGroup2D");
    model.result("pg6").label("\u7535\u573a (es) 1");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").setIndex("looplevel", 201, 0);
    model.result("pg6").set("frametype", "spatial");
    model.result("pg6").set("showlegendsmaxmin", true);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("solutionparams", "parent");
    model.result("pg6").feature("surf1").set("expr", "es.normE");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg6").feature("surf1").set("showsolutionparams", "on");
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature().create("str1", "Streamline");
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("solutionparams", "parent");
    model.result("pg6").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg6").feature("str1").set("titletype", "none");
    model.result("pg6").feature("str1").set("posmethod", "uniform");
    model.result("pg6").feature("str1").set("udist", 0.02);
    model.result("pg6").feature("str1").set("maxlen", 0.4);
    model.result("pg6").feature("str1").set("maxsteps", 5000);
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("inheritcolor", false);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("showsolutionparams", "on");
    model.result("pg6").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg6").feature("str1").set("data", "parent");
    model.result("pg6").feature("str1").selection().geom("geom1", 1);
    model.result("pg6").feature("str1").selection().set(5, 6, 7, 12, 13, 14, 16, 19, 24, 25, 26, 29);
    model.result("pg6").feature("str1").set("inheritplot", "surf1");
    model.result("pg6").feature("str1").feature().create("col1", "Color");
    model.result("pg6").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg6").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg6").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg6").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg6").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg6").feature("str1").feature().create("filt1", "Filter");
    model.result("pg6").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg4").run();
    model.result("pg4").label("\u4f4d\u79fb (solid)");
    model.result("pg4").setIndex("looplevel", 66, 0);
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "solid.disp");
    model.result("pg4").feature("surf1").set("descr", "\u4f4d\u79fb\u5927\u5c0f");
    model.result("pg4").run();
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u5bfc\u7eb3");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").setIndex("expr", "abs(es.Y11)", 0);
    model.result("pg7").feature("glob1").setIndex("descr", "\u5bfc\u7eb3\u7684\u7edd\u5bf9\u503c", 0);
    model.result("pg7").feature("glob1").set("xdataparamunit", "MHz");
    model.result("pg7").run();
    model.result("pg7").set("ylog", true);
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").label("\u54c1\u8d28\u56e0\u5b50");
    model.result("pg8").run();
    model.result("pg8").feature("glob1").set("expr", new String[]{"solid.Q_freq"});
    model.result("pg8").feature("glob1").set("descr", new String[]{"\u9891\u7387\u54c1\u8d28\u56e0\u5b50"});
    model.result("pg8").feature("glob1").set("unit", new String[]{"1"});
    model.result("pg8").set("ylog", false);
    model.result("pg8").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").label("Q \u56e0\u5b50");
    model.result().numerical("gev1").setIndex("looplevelinput", "manual", 0);
    model.result().numerical("gev1").setIndex("looplevel", new int[]{1}, 0);
    model.result().numerical("gev1").set("expr", new String[]{"solid.Q_eig"});
    model.result().numerical("gev1").set("descr", new String[]{"\u7279\u5f81\u503c\u54c1\u8d28\u56e0\u5b50"});
    model.result().numerical("gev1").set("unit", new String[]{"1"});
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("Q \u56e0\u5b50");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();
    model.result().numerical().duplicate("gev2", "gev1");
    model.result().numerical("gev2").set("expr", new String[]{"solid.decay"});
    model.result().numerical("gev2").set("descr", new String[]{"\u6307\u6570\u8870\u51cf\u56e0\u5b50"});
    model.result().numerical("gev2").set("unit", new String[]{"1"});
    model.result().numerical("gev2").label("\u8870\u51cf\u56e0\u5b50");
    model.result().numerical("gev2").set("table", "tbl1");
    model.result().numerical("gev2").appendResult();
    model.result("pg2").run();
    model.result("pg2").feature("str1").set("udist", 0.005);
    model.result("pg2").run();
    model.result("pg3").run();
    model.result("pg3").feature("str1").set("udist", 0.005);
    model.result("pg3").run();
    model.result("pg5").run();
    model.result("pg5").feature("str1").set("udist", 0.005);
    model.result("pg5").run();
    model.result("pg6").run();
    model.result("pg6").feature("str1").set("udist", 0.005);
    model.result("pg6").run();

    model.title("\u590d\u5408\u8584\u819c\u4f53\u58f0\u6ce2\u8c10\u632f\u5668");

    model
         .description("\u4f53\u58f0\u6ce2 (BAW) \u8c10\u632f\u5668\u662f\u8bb8\u591a\u5c04\u9891\u5e94\u7528\u4e2d\u53ef\u7528\u4f5c\u7a84\u5e26\u6ee4\u6ce2\u5668\u7684\u975e\u5e38\u6709\u7528\u7684\u5143\u4ef6\u3002\u672c\u4f8b\u6f14\u793a\u5982\u4f55\u5bf9\u590d\u5408\u8584\u819c\u4f53\u58f0\u6ce2\u8c10\u632f\u5668\u6267\u884c\u7279\u5f81\u9891\u7387\u548c\u9891\u7387\u54cd\u5e94\u5206\u6790\u3002");

    model.label("thin_film_baw_resonator.mph");

    model.result("pg6").run();

    model.component("comp1").geom("geom1").feature("r1").set("size", new double[]{2, 16.7});
    model.component("comp1").geom("geom1").feature("r1").set("layerleft", false);
    model.component("comp1").geom("geom1").feature("r1").set("layerright", false);
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{2, 0.2});
    model.component("comp1").geom("geom1").feature("r2").set("layerleft", false);
    model.component("comp1").geom("geom1").feature("r2").set("layerright", false);
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{2, 0.2});
    model.component("comp1").geom("geom1").runPre("fin");
    model.component("comp1").geom("geom1").run();

    model.component("comp1").physics("solid").feature().remove("fix1");
    model.component("comp1").physics("solid").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("solid").feature("pc1").selection().set(1, 3, 5, 7, 10, 11, 12, 13);
    model.component("comp1").physics("solid").feature("pc1").set("PeriodicType", "Floquet");

    model.param().set("k", "0[1/m]");
    model.param().descr("k", "\u6ce2\u6570");

    model.component("comp1").physics("solid").feature("pc1").set("kFloquet", new String[]{"k", "0", "0"});
    model.component("comp1").physics("solid").feature("pc1")
         .label("\u5468\u671f\u6027\u6761\u4ef6 1\uff1a\u5b9e\u90e8 k");
    model.component("comp1").physics("solid").feature().duplicate("pc2", "pc1");
    model.component("comp1").physics("solid").feature("pc2")
         .label("\u5468\u671f\u6027\u6761\u4ef6 2\uff1a\u865a\u90e8 k");
    model.component("comp1").physics("solid").feature("pc2").set("kFloquet", new String[]{"i*k", "0", "0"});
    model.component("comp1").physics("es").create("pc1", "PeriodicCondition", 1);
    model.component("comp1").physics("es").feature("pc1")
         .label("\u5468\u671f\u6027\u6761\u4ef6 1\uff1a\u5b9e\u90e8 k");
    model.component("comp1").physics("es").feature("pc1").selection().set(5, 12);
    model.component("comp1").physics("es").feature("pc1").set("PeriodicType", "Floquet");
    model.component("comp1").physics("es").feature("pc1").set("kFloquet", new String[]{"k", "0", "0"});
    model.component("comp1").physics("es").feature().duplicate("pc2", "pc1");
    model.component("comp1").physics("es").feature("pc2")
         .label("\u5468\u671f\u6027\u6761\u4ef6 2\uff1a\u865a\u90e8 k");
    model.component("comp1").physics("es").feature("pc2").set("kFloquet", new String[]{"i*k", "0", "0"});
    model.component("comp1").physics("es").feature("term1").label("\u7ec8\u7aef 1\uff1a\u5b9e\u90e8 k");
    model.component("comp1").physics("es").feature("term1").set("V0", "1[V]*exp(-i*k*x)");
    model.component("comp1").physics("es").feature().duplicate("term2", "term1");
    model.component("comp1").physics("es").feature("term2").label("\u7ec8\u7aef 2\uff1a\u865a\u90e8 k");
    model.component("comp1").physics("es").feature("term2").set("V0", "1[V]*exp(-i*i*k*x)");

    model.component("comp1").mesh("mesh1").feature("map1").feature("dis1").set("numelem", 1);
    model.component("comp1").mesh("mesh1").feature("map1").feature().remove("dis2");
    model.component("comp1").mesh("mesh1").feature("map1").feature().remove("dis3");
    model.component("comp1").mesh("mesh1").feature("map1").feature().remove("dis4");

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/es", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std3").feature("freq").set("plist", "range(200,0.5,260)[MHz]");
    model.study("std3").feature("freq").set("useadvanceddisable", true);
    model.study("std3").feature("freq").set("disabledphysics", new String[]{"solid/pc2", "es/pc2", "es/term2"});
    model.study("std3").feature("freq").set("useparam", true);
    model.study("std3").feature("freq").setIndex("pname_aux", "k", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "1/m", 0);
    model.study("std3").feature("freq").setIndex("pname_aux", "k", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "1/m", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "range(0,0.025,1)*1.5e5", 0);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u9891\u57df\uff0c\u5b9e\u90e8 k");
    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result().create("pg9", "PlotGroup2D");
    model.result("pg9").set("data", "dset3");
    model.result("pg9").setIndex("looplevel", 41, 0);
    model.result("pg9").setIndex("looplevel", 121, 1);
    model.result("pg9").label("\u5e94\u529b (solid)");
    model.result("pg9").set("frametype", "spatial");
    model.result("pg9").create("surf1", "Surface");
    model.result("pg9").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg9").feature("surf1").set("threshold", "manual");
    model.result("pg9").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg9").feature("surf1").set("colortable", "Rainbow");
    model.result("pg9").feature("surf1").set("colortabletrans", "none");
    model.result("pg9").feature("surf1").set("colorscalemode", "linear");
    model.result("pg9").feature("surf1").set("resolution", "normal");
    model.result("pg9").feature("surf1").set("colortable", "Prism");
    model.result("pg9").feature("surf1").create("def", "Deform");
    model.result("pg9").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg9").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg10", "PlotGroup2D");
    model.result("pg10").label("\u7535\u52bf (es) 2");
    model.result("pg10").set("data", "dset3");
    model.result("pg10").setIndex("looplevel", 41, 0);
    model.result("pg10").setIndex("looplevel", 121, 1);
    model.result("pg10").set("frametype", "spatial");
    model.result("pg10").set("showlegendsmaxmin", true);
    model.result("pg10").feature().create("surf1", "Surface");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("solutionparams", "parent");
    model.result("pg10").feature("surf1").set("expr", "V");
    model.result("pg10").feature("surf1").set("colortable", "Dipole");
    model.result("pg10").feature("surf1").set("showsolutionparams", "on");
    model.result("pg10").feature("surf1").set("data", "parent");
    model.result("pg10").feature().create("str1", "Streamline");
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("solutionparams", "parent");
    model.result("pg10").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg10").feature("str1").set("titletype", "none");
    model.result("pg10").feature("str1").set("posmethod", "uniform");
    model.result("pg10").feature("str1").set("udist", 0.02);
    model.result("pg10").feature("str1").set("maxlen", 0.4);
    model.result("pg10").feature("str1").set("maxsteps", 5000);
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("inheritcolor", false);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("showsolutionparams", "on");
    model.result("pg10").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg10").feature("str1").set("data", "parent");
    model.result("pg10").feature("str1").selection().geom("geom1", 1);
    model.result("pg10").feature("str1").selection().set(5, 6, 8, 12);
    model.result("pg10").feature("str1").set("inheritplot", "surf1");
    model.result("pg10").feature("str1").feature().create("col1", "Color");
    model.result("pg10").feature("str1").feature("col1").set("expr", "V");
    model.result("pg10").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg10").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg10").feature("str1").feature().create("filt1", "Filter");
    model.result("pg10").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg11", "PlotGroup2D");
    model.result("pg11").label("\u7535\u573a (es) 2");
    model.result("pg11").set("data", "dset3");
    model.result("pg11").setIndex("looplevel", 41, 0);
    model.result("pg11").setIndex("looplevel", 121, 1);
    model.result("pg11").set("frametype", "spatial");
    model.result("pg11").set("showlegendsmaxmin", true);
    model.result("pg11").feature().create("surf1", "Surface");
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("solutionparams", "parent");
    model.result("pg11").feature("surf1").set("expr", "es.normE");
    model.result("pg11").feature("surf1").set("colortable", "Prism");
    model.result("pg11").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg11").feature("surf1").set("showsolutionparams", "on");
    model.result("pg11").feature("surf1").set("data", "parent");
    model.result("pg11").feature().create("str1", "Streamline");
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("solutionparams", "parent");
    model.result("pg11").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg11").feature("str1").set("titletype", "none");
    model.result("pg11").feature("str1").set("posmethod", "uniform");
    model.result("pg11").feature("str1").set("udist", 0.02);
    model.result("pg11").feature("str1").set("maxlen", 0.4);
    model.result("pg11").feature("str1").set("maxsteps", 5000);
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("inheritcolor", false);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("showsolutionparams", "on");
    model.result("pg11").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg11").feature("str1").set("data", "parent");
    model.result("pg11").feature("str1").selection().geom("geom1", 1);
    model.result("pg11").feature("str1").selection().set(5, 6, 8, 12);
    model.result("pg11").feature("str1").set("inheritplot", "surf1");
    model.result("pg11").feature("str1").feature().create("col1", "Color");
    model.result("pg11").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg11").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg11").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg11").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg11").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg11").feature("str1").feature().create("filt1", "Filter");
    model.result("pg11").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg9").run();

    model.study().create("std4");
    model.study("std4").create("freq", "Frequency");
    model.study("std4").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std4").feature("freq").setSolveFor("/physics/es", true);
    model.study("std4").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std4").feature("freq").set("plist", "range(200,0.5,260)[MHz]");
    model.study("std4").feature("freq").set("useadvanceddisable", true);
    model.study("std4").feature("freq").set("disabledphysics", new String[]{"solid/pc1", "es/term1", "es/pc1"});
    model.study("std4").feature("freq").set("useparam", true);
    model.study("std4").feature("freq").setIndex("pname_aux", "k", 0);
    model.study("std4").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std4").feature("freq").setIndex("punit_aux", "1/m", 0);
    model.study("std4").feature("freq").setIndex("pname_aux", "k", 0);
    model.study("std4").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std4").feature("freq").setIndex("punit_aux", "1/m", 0);
    model.study("std4").feature("freq").setIndex("plistarr_aux", "-range(0,0.025,1)*2.8e4", 0);
    model.study("std4").label("\u7814\u7a76 4\uff1a\u9891\u57df\uff0c\u865a\u90e8 k");
    model.study("std4").createAutoSequences("all");

    model.sol("sol4").runAll();

    model.result().create("pg12", "PlotGroup2D");
    model.result("pg12").set("data", "dset4");
    model.result("pg12").setIndex("looplevel", 41, 0);
    model.result("pg12").setIndex("looplevel", 121, 1);
    model.result("pg12").label("\u5e94\u529b (solid) 1");
    model.result("pg12").set("frametype", "spatial");
    model.result("pg12").create("surf1", "Surface");
    model.result("pg12").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg12").feature("surf1").set("threshold", "manual");
    model.result("pg12").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg12").feature("surf1").set("colortable", "Rainbow");
    model.result("pg12").feature("surf1").set("colortabletrans", "none");
    model.result("pg12").feature("surf1").set("colorscalemode", "linear");
    model.result("pg12").feature("surf1").set("resolution", "normal");
    model.result("pg12").feature("surf1").set("colortable", "Prism");
    model.result("pg12").feature("surf1").create("def", "Deform");
    model.result("pg12").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg12").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg13", "PlotGroup2D");
    model.result("pg13").label("\u7535\u52bf (es) 3");
    model.result("pg13").set("data", "dset4");
    model.result("pg13").setIndex("looplevel", 41, 0);
    model.result("pg13").setIndex("looplevel", 121, 1);
    model.result("pg13").set("frametype", "spatial");
    model.result("pg13").set("showlegendsmaxmin", true);
    model.result("pg13").feature().create("surf1", "Surface");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("solutionparams", "parent");
    model.result("pg13").feature("surf1").set("expr", "V");
    model.result("pg13").feature("surf1").set("colortable", "Dipole");
    model.result("pg13").feature("surf1").set("showsolutionparams", "on");
    model.result("pg13").feature("surf1").set("data", "parent");
    model.result("pg13").feature().create("str1", "Streamline");
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("solutionparams", "parent");
    model.result("pg13").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg13").feature("str1").set("titletype", "none");
    model.result("pg13").feature("str1").set("posmethod", "uniform");
    model.result("pg13").feature("str1").set("udist", 0.02);
    model.result("pg13").feature("str1").set("maxlen", 0.4);
    model.result("pg13").feature("str1").set("maxsteps", 5000);
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("inheritcolor", false);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("showsolutionparams", "on");
    model.result("pg13").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg13").feature("str1").set("data", "parent");
    model.result("pg13").feature("str1").selection().geom("geom1", 1);
    model.result("pg13").feature("str1").selection().set(5, 6, 8, 12);
    model.result("pg13").feature("str1").set("inheritplot", "surf1");
    model.result("pg13").feature("str1").feature().create("col1", "Color");
    model.result("pg13").feature("str1").feature("col1").set("expr", "V");
    model.result("pg13").feature("str1").feature("col1").set("colortable", "DipoleDark");
    model.result("pg13").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg13").feature("str1").feature().create("filt1", "Filter");
    model.result("pg13").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result().create("pg14", "PlotGroup2D");
    model.result("pg14").label("\u7535\u573a (es) 3");
    model.result("pg14").set("data", "dset4");
    model.result("pg14").setIndex("looplevel", 41, 0);
    model.result("pg14").setIndex("looplevel", 121, 1);
    model.result("pg14").set("frametype", "spatial");
    model.result("pg14").set("showlegendsmaxmin", true);
    model.result("pg14").feature().create("surf1", "Surface");
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("solutionparams", "parent");
    model.result("pg14").feature("surf1").set("expr", "es.normE");
    model.result("pg14").feature("surf1").set("colortable", "Prism");
    model.result("pg14").feature("surf1").set("colortabletrans", "nonlinear");
    model.result("pg14").feature("surf1").set("colorcalibration", -0.8);
    model.result("pg14").feature("surf1").set("showsolutionparams", "on");
    model.result("pg14").feature("surf1").set("data", "parent");
    model.result("pg14").feature().create("str1", "Streamline");
    model.result("pg14").feature("str1").set("showsolutionparams", "on");
    model.result("pg14").feature("str1").set("solutionparams", "parent");
    model.result("pg14").feature("str1").set("expr", new String[]{"es.Ex", "es.Ey"});
    model.result("pg14").feature("str1").set("titletype", "none");
    model.result("pg14").feature("str1").set("posmethod", "uniform");
    model.result("pg14").feature("str1").set("udist", 0.02);
    model.result("pg14").feature("str1").set("maxlen", 0.4);
    model.result("pg14").feature("str1").set("maxsteps", 5000);
    model.result("pg14").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg14").feature("str1").set("inheritcolor", false);
    model.result("pg14").feature("str1").set("showsolutionparams", "on");
    model.result("pg14").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg14").feature("str1").set("showsolutionparams", "on");
    model.result("pg14").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg14").feature("str1").set("showsolutionparams", "on");
    model.result("pg14").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg14").feature("str1").set("showsolutionparams", "on");
    model.result("pg14").feature("str1").set("maxtime", Double.POSITIVE_INFINITY);
    model.result("pg14").feature("str1").set("data", "parent");
    model.result("pg14").feature("str1").selection().geom("geom1", 1);
    model.result("pg14").feature("str1").selection().set(5, 6, 8, 12);
    model.result("pg14").feature("str1").set("inheritplot", "surf1");
    model.result("pg14").feature("str1").feature().create("col1", "Color");
    model.result("pg14").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg14").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg14").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg14").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg14").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg14").feature("str1").feature().create("filt1", "Filter");
    model.result("pg14").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg12").run();
    model.result().evaluationGroup().create("eg1", "EvaluationGroup");
    model.result().evaluationGroup("eg1").label("\u8ba1\u7b97\u7ec4 1\uff1a\u8272\u6563\u56fe");
    model.result().evaluationGroup("eg1").set("data", "none");
    model.result().evaluationGroup("eg1").create("gev1", "EvalGlobal");
    model.result().evaluationGroup("eg1").feature("gev1").label("\u5168\u5c40\u8ba1\u7b97 1\uff1a\u5b9e\u90e8 k");
    model.result().evaluationGroup("eg1").feature("gev1").set("data", "dset3");
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("expr", "log10(abs(es.Y11)/1[S])", 0);
    model.result().evaluationGroup("eg1").feature("gev1").setIndex("descr", "log10(abs(Y11))", 0);
    model.result().evaluationGroup("eg1").feature().duplicate("gev2", "gev1");
    model.result().evaluationGroup("eg1").feature("gev2").label("\u5168\u5c40\u8ba1\u7b97 2\uff1a\u865a\u90e8 k");
    model.result().evaluationGroup("eg1").feature("gev2").set("data", "dset4");
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("expr", "log10(abs(es.Y22)/1[S])", 0);
    model.result().evaluationGroup("eg1").feature("gev2").setIndex("descr", "log10(abs(Y22))", 0);
    model.result().evaluationGroup("eg1").run();
    model.result().create("pg15", "PlotGroup2D");

    return model;
  }

  public static Model run3(Model model) {
    model.result("pg15").set("data", "none");
    model.result("pg15").create("tbls1", "TableSurface");
    model.result("pg15").feature("tbls1").set("source", "evaluationgroup");
    model.result("pg15").feature("tbls1").set("evaluationgroup", "eg1");
    model.result("pg15").run();
    model.result("pg15").feature("tbls1").set("colx", 2);
    model.result("pg15").feature("tbls1").set("coly", 1);
    model.result("pg15").feature("tbls1").set("rangecoloractive", true);
    model.result("pg15").feature("tbls1").set("rangecolormin", -4.5);
    model.result("pg15").feature("tbls1").set("rangecolormax", -1.5);
    model.result("pg15").feature("tbls1").label("\u8868\u683c\u9762\u56fe 1\uff1a\u5b9e\u90e8 k");
    model.result("pg15").feature().duplicate("tbls2", "tbls1");
    model.result("pg15").run();
    model.result("pg15").feature("tbls2").label("\u8868\u683c\u9762\u56fe 2\uff1a\u865a\u90e8 k");
    model.result("pg15").feature("tbls2").set("colx", 5);
    model.result("pg15").feature("tbls2").set("coly", 4);
    model.result("pg15").feature("tbls2").set("coldata", 6);
    model.result("pg15").feature("tbls2").set("inheritplot", "tbls1");
    model.result("pg15").run();
    model.result("pg15").label("\u8272\u6563\u56fe");
    model.result("pg15").run();
    model.result().dataset().create("arr1", "Array2D");
    model.result().dataset("arr1").label("\u4e8c\u7ef4\u9635\u5217 1\uff1a\u5b9e\u90e8 k");
    model.result().dataset("arr1").set("data", "dset3");
    model.result().dataset("arr1").set("fullsize", new int[]{40, 1});
    model.result().dataset("arr1").set("floquetper", true);
    model.result().dataset("arr1").set("wavevector", new String[]{"k", "0"});
    model.result().create("pg16", "PlotGroup2D");
    model.result("pg16").run();
    model.result("pg16").label("TS2 \u6a21\u5f0f");
    model.result("pg16").set("data", "arr1");
    model.result("pg16").setIndex("looplevel", 4, 1);
    model.result("pg16").setIndex("looplevel", 23, 0);
    model.result("pg16").set("edges", false);
    model.result("pg16").create("surf1", "Surface");
    model.result("pg16").feature("surf1").create("def1", "Deform");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").create("str1", "Streamline");
    model.result("pg16").feature("str1").set("posmethod", "uniform");
    model.result("pg16").feature("str1").set("udist", 0.025);
    model.result("pg16").feature("str1").set("pointtype", "arrow");
    model.result("pg16").feature("str1").set("inheritplot", "surf1");
    model.result("pg16").feature("str1").set("inheritcolor", false);
    model.result("pg16").feature("str1").create("def1", "Deform");
    model.result("pg16").run();
    model.result("pg16").run();
    model.result("pg16").run();
    model.result().duplicate("pg17", "pg16");
    model.result("pg17").run();
    model.result("pg17").label("TE1 \u6a21\u5f0f");
    model.result("pg17").setIndex("looplevel", 89, 1);
    model.result("pg17").run();
    model.result().dataset().duplicate("arr2", "arr1");
    model.result().dataset("arr2").label("\u4e8c\u7ef4\u9635\u5217 1\uff1a\u865a\u90e8 k");
    model.result().dataset("arr2").set("data", "dset4");
    model.result().dataset("arr2").set("wavevector", new String[]{"i*k", "0"});
    model.result("pg17").run();
    model.result().duplicate("pg18", "pg17");
    model.result("pg18").run();
    model.result("pg18").label("\u6d88\u5931\u6a21");
    model.result("pg18").set("data", "arr2");
    model.result("pg18").setIndex("looplevel", 29, 1);
    model.result("pg18").setIndex("looplevel", 37, 0);
    model.result("pg18").run();

    model.title("\u8584\u819c\u4f53\u58f0\u6ce2\u7ed3\u6784\u7684\u8272\u6563\u56fe");

    model
         .description("\u672c\u6a21\u578b\u5bf9\u201c\u590d\u5408\u8584\u819c\u4f53\u58f0\u6ce2\u8c10\u632f\u5668\u201d\u6559\u7a0b\u8fdb\u884c\u6269\u5c55\uff0c\u663e\u793a\u5982\u4f55\u6839\u636e\u4eff\u771f\u7ed3\u679c\u521b\u5efa\u8272\u6563\u56fe\u3002\u6211\u4eec\u53ef\u4ee5\u6839\u636e\u6ce2\u6570\u7684\u5b9e\u503c\u548c\u865a\u503c\u7ed8\u5236\u6563\u5c04\u66f2\u7ebf\uff0c\u5b83\u4eec\u5206\u522b\u5bf9\u5e94\u4e8e\u4f20\u64ad\u6a21\u548c\u6d88\u5931\u6a21\u3002\u901a\u8fc7\u7ed8\u5236\u6bcf\u79cd\u6a21\u5f0f\u7684\u4f4d\u79fb\u573a\uff0c\u8fd8\u53ef\u4ee5\u751f\u6210\u5176\u53ef\u89c6\u5316\u6548\u679c\u3002\u5728\u4e3a\u4f53\u58f0\u6ce2\u8c10\u632f\u5668\u7684\u5c42\u7ed3\u6784\u9009\u62e9\u5408\u9002\u7684\u8bbe\u8ba1\u53c2\u6570\u7684\u8fc7\u7a0b\u4e2d\uff0c\u8003\u8651\u8fd9\u79cd\u4f20\u64ad\u6a21\u548c\u6d88\u5931\u6a21\u975e\u5e38\u91cd\u8981\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();
    model.sol("sol4").clearSolutionData();

    model.result("pg15").feature("tbls2").set("tablechanged", true);
    model.result("pg15").feature("tbls2").set("showparam", false);
    model.result("pg15").feature("tbls1").set("tablechanged", true);
    model.result("pg15").feature("tbls1").set("showparam", false);

    model.label("thin_film_baw_resonator_dispersion_diagram.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    model = run2(model);
    run3(model);
  }

}
