/*
 * piezoelectric_energy_harvester.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 10:59 by COMSOL 6.3.0.290. */
public class piezoelectric_energy_harvester {

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
    model.component("comp1").physics().create("cir", "Circuit", "geom1");

    model.component("comp1").multiphysics().create("pze1", "PiezoelectricEffect", 2);
    model.component("comp1").multiphysics("pze1").set("Solid_physics", "solid");
    model.component("comp1").multiphysics("pze1").set("Electrostatics_physics", "es");

    model.study().create("std1");
    model.study("std1").create("freq", "Frequency");
    model.study("std1").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std1").feature("freq").setSolveFor("/physics/es", true);
    model.study("std1").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std1").feature("freq").setSolveFor("/multiphysics/pze1", true);

    model.param().set("acc", "1");
    model.param().descr("acc", "\u52a0\u901f\u5ea6 (g)");
    model.param().set("R_load", "12[kohm]");
    model.param().descr("R_load", "\u8d1f\u8f7d\u7535\u963b");
    model.param().set("w_plate", "14[mm]");
    model.param().descr("w_plate", "\u9762\u5916\u5c3a\u5bf8");

    model.component("comp1").geom("geom1").lengthUnit("mm");
    model.component("comp1").geom("geom1").create("r1", "Rectangle");
    model.component("comp1").geom("geom1").feature("r1").set("pos", new int[]{-1, -1});
    model.component("comp1").geom("geom1").run("r1");
    model.component("comp1").geom("geom1").create("r2", "Rectangle");
    model.component("comp1").geom("geom1").feature("r2").set("size", new double[]{21, 0.16});
    model.component("comp1").geom("geom1").feature("r2").set("pos", new int[]{-1, 0});
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 0.06, 0);
    model.component("comp1").geom("geom1").feature("r2").setIndex("layer", 0.04, 1);
    model.component("comp1").geom("geom1").run("r2");
    model.component("comp1").geom("geom1").create("r3", "Rectangle");
    model.component("comp1").geom("geom1").feature("r3").set("size", new double[]{4, 1.7});
    model.component("comp1").geom("geom1").feature("r3").set("pos", new double[]{16, 0.16});
    model.component("comp1").geom("geom1").run("r3");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("r1", "r2", "r3");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("cha1", "Chamfer");
    model.component("comp1").geom("geom1").feature("cha1").selection("point").set("uni1", 7, 8);
    model.component("comp1").geom("geom1").feature("cha1").set("dist", 0.25);
    model.component("comp1").geom("geom1").feature().duplicate("r4", "r2");
    model.component("comp1").geom("geom1").run("r4");
    model.component("comp1").geom("geom1").create("ls1", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls1").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls1").set("coord1", new double[]{0.25, 0});
    model.component("comp1").geom("geom1").feature("ls1").set("coord2", new double[]{0.25, 0.16});
    model.component("comp1").geom("geom1").run("ls1");
    model.component("comp1").geom("geom1").create("ls2", "LineSegment");
    model.component("comp1").geom("geom1").feature("ls2").set("specify1", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("specify2", "coord");
    model.component("comp1").geom("geom1").feature("ls2").set("coord1", new double[]{15.75, 0.16});
    model.component("comp1").geom("geom1").feature("ls2").set("coord2", new double[]{15.75, 0});
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StrainCharge", "StrainCharge", "Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup()
         .create("StressCharge", "StressCharge", "Stress-charge form");
    model.component("comp1").material("mat1").label("Lead Zirconate Titanate (PZT-5A)");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("customspecular", new double[]{0.7843137254901961, 1, 1});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.1);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat1").propertyGroup("def")
         .set("relpermittivity", new String[]{"919.1", "0", "0", "0", "919.1", "0", "0", "0", "826.6"});
    model.component("comp1").material("mat1").propertyGroup("def").set("density", "7750[kg/m^3]");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge").label("Strain-charge form");
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("sE", new String[]{"1.64e-011[1/Pa]", "-5.74e-012[1/Pa]", "-7.22e-012[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "-5.74e-012[1/Pa]", "1.64e-011[1/Pa]", "-7.22e-012[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "-7.22e-012[1/Pa]", "-7.22e-012[1/Pa]", "1.88e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "4.75e-011[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.75e-011[1/Pa]", "0[1/Pa]", 
         "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "0[1/Pa]", "4.43e-011[1/Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("dET", new String[]{"0[C/N]", "0[C/N]", "-1.71e-010[C/N]", "0[C/N]", "0[C/N]", "-1.71e-010[C/N]", "0[C/N]", "0[C/N]", "3.74e-010[C/N]", "0[C/N]", 
         "5.84e-010[C/N]", "0[C/N]", "5.84e-010[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]", "0[C/N]"});
    model.component("comp1").material("mat1").propertyGroup("StrainCharge")
         .set("epsilonrT", new String[]{"1730", "0", "0", "0", "1730", "0", "0", "0", "1700"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge").label("Stress-charge form");
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("cE", new String[]{"1.20346e+011[Pa]", "7.51791e+010[Pa]", "7.50901e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "7.51791e+010[Pa]", "1.20346e+011[Pa]", "7.50901e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "7.50901e+010[Pa]", "7.50901e+010[Pa]", "1.10867e+011[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", 
         "0[Pa]", "2.10526e+010[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.10526e+010[Pa]", "0[Pa]", 
         "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "0[Pa]", "2.25734e+010[Pa]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("eES", new String[]{"0[C/m^2]", "0[C/m^2]", "-5.35116[C/m^2]", "0[C/m^2]", "0[C/m^2]", "-5.35116[C/m^2]", "0[C/m^2]", "0[C/m^2]", "15.7835[C/m^2]", "0[C/m^2]", 
         "12.2947[C/m^2]", "0[C/m^2]", "12.2947[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]", "0[C/m^2]"});
    model.component("comp1").material("mat1").propertyGroup("StressCharge")
         .set("epsilonrS", new String[]{"919.1", "0", "0", "0", "919.1", "0", "0", "0", "826.6"});
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Enu", "Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup("Enu").func().create("int2", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Murnaghan", "Murnaghan", "Murnaghan");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ElastoplasticModel", "ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Ludwik", "Ludwik", "Ludwik");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("JohnsonCook", "JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat2").propertyGroup().create("Swift", "Swift", "Swift");
    model.component("comp1").material("mat2").propertyGroup().create("Voce", "Voce", "Voce");
    model.component("comp1").material("mat2").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("HockettSherby", "HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ArmstrongFrederick", "ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat2").propertyGroup().create("Norton", "Norton", "Norton");
    model.component("comp1").material("mat2").propertyGroup()
         .create("Garofalo", "Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup()
         .create("ChabocheViscoplasticity", "ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat2").label("Structural steel");
    model.component("comp1").material("mat2").set("family", "custom");
    model.component("comp1").material("mat2")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat2").set("diffuse", "custom");
    model.component("comp1").material("mat2")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("ambient", "custom");
    model.component("comp1").material("mat2")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat2").set("noise", true);
    model.component("comp1").material("mat2").set("fresnel", 0.9);
    model.component("comp1").material("mat2").set("roughness", 0.3);
    model.component("comp1").material("mat2").set("diffusewrap", 0);
    model.component("comp1").material("mat2").set("reflectance", 0);
    model.component("comp1").material("mat2").propertyGroup("def").label("Basic");
    model.component("comp1").material("mat2").propertyGroup("def").set("lossfactor", "0.02");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermeability", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def").set("heatcapacity", "475[J/(kg*K)]");
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalconductivity", new String[]{"44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]", "0", "0", "0", "44.5[W/(m*K)]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("electricconductivity", new String[]{"4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]", "0", "0", "0", "4.032e6[S/m]"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("relpermittivity", new String[]{"1", "0", "0", "0", "1", "0", "0", "0", "1"});
    model.component("comp1").material("mat2").propertyGroup("def")
         .set("thermalexpansioncoefficient", new String[]{"12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]", "0", "0", "0", "12.3e-6[1/K]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", "7850[kg/m^3]");
    model.component("comp1").material("mat2").propertyGroup("Enu").label("Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("funcname", "E");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1")
         .set("table", new String[][]{{"293.15", "200e9"}, {"793.15", "166.6e9"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("fununit", new String[]{"Pa"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").label("Interpolation 2");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("funcnametable", new String[][]{{"int1", "1"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("funcname", "nu");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2")
         .set("table", new String[][]{{"293.15", "0.30"}, {"793.15", "0.315"}});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("extrap", "linear");
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Enu").func("int2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Enu").set("E", "E(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").set("nu", "nu(T)");
    model.component("comp1").material("mat2").propertyGroup("Enu").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("l", "-3.0e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("m", "-6.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("Murnaghan").set("n", "-7.2e11[Pa]");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .label("Elastoplastic material model");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("Hillcoefficients", new String[]{"0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]", "0[m^2*s^4/kg^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel")
         .set("ys", new String[]{"0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]", "0[N/m^2]"});
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("k_lud", "560[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").set("n_lud", "0.61");
    model.component("comp1").material("mat2").propertyGroup("Ludwik").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("k_jcook", "560[MPa]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("n_jcook", "0.61");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("C_jcook", "0.12");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("epet0_jcook", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("JohnsonCook").set("m_jcook", "0.6");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("e0_swi", "0.021");
    model.component("comp1").material("mat2").propertyGroup("Swift").set("n_swi", "0.2");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat2").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("sigma_hoc", "684[MPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("m_hoc", "3.9");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").set("n_hoc", "0.85");
    model.component("comp1").material("mat2").propertyGroup("HockettSherby").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .label("Interpolation 1");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1").set("funcname", "a");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("table", new String[][]{{"600", "1"}, {"1100", "0.1"}, {"1643", "0"}});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("Ck", "2.070[GPa]*a(T)");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").set("gammak", "8.0");
    model.component("comp1").material("mat2").propertyGroup("ArmstrongFrederick").addInput("temperature");
    model.component("comp1").material("mat2").propertyGroup("Norton").label("Norton");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("A_nor", "1.2e-15[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("sigRef_nor", "1[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Norton").set("n_nor", "4.5");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").label("Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("A_gar", "1e-6[1/s]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("sigRef_gar", "100[MPa]");
    model.component("comp1").material("mat2").propertyGroup("Garofalo").set("n_gar", "4.6");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity")
         .label("Chaboche viscoplasticity");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1[1/s]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat2").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat2").selection().set(1, 3, 6, 9, 11);

    model.component("comp1").physics("solid").feature("lemm1").create("dmp1", "Damping", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("lemm1").feature("dmp1").set("eta_s", 0.001);
    model.component("comp1").physics("solid").feature("pzm1").selection().set(2, 4, 5, 7, 8, 10);
    model.component("comp1").physics("solid").feature("pzm1").create("mdmp1", "MechanicalDamping", 2);
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1")
         .set("DampingType", "IsotropicLossFactor");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s_mat", "userdef");
    model.component("comp1").physics("solid").feature("pzm1").feature("mdmp1").set("eta_s", 0.001);
    model.component("comp1").physics("solid").create("bl1", "BodyLoad", 2);
    model.component("comp1").physics("solid").feature("bl1").selection().all();
    model.component("comp1").physics("solid").feature("bl1")
         .set("forceReferenceVolume", new String[]{"0", "-solid.rho*g_const*acc", "0"});
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(2);
    model.component("comp1").physics("es").selection().set(2, 4, 5, 7, 8, 10);
    model.component("comp1").physics("es").prop("d").set("d", "w_plate");
    model.component("comp1").physics("es").create("gnd1", "Ground", 1);
    model.component("comp1").physics("es").feature("gnd1").selection().set(4, 9, 13, 18, 20, 26);
    model.component("comp1").physics("es").create("term1", "Terminal", 1);
    model.component("comp1").physics("es").feature("term1").selection().set(6, 8, 15, 17, 22, 24);
    model.component("comp1").physics("es").feature("term1").set("TerminalType", "Circuit");
    model.component("comp1").physics("cir").create("R1", "Resistor", -1);
    model.component("comp1").physics("cir").feature("R1").setIndex("Connections", 0, 1, 0);
    model.component("comp1").physics("cir").feature("R1").set("R", "R_load");
    model.component("comp1").physics("cir").create("termI1", "ModelTerminalIV", -1);
    model.component("comp1").physics("cir").feature("termI1").set("Connections", 1);
    model.component("comp1").physics("cir").feature("termI1").set("V_src", "root.comp1.es.V0_1");

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("size").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("size").set("hmax", 0.02);
    model.component("comp1").mesh("mesh1").feature("size").set("hmin", 0.002);

    model.study("std1").label("\u9891\u7387\u54cd\u5e94");
    model.study("std1").feature("freq").set("plist", "range(62,1,80)");
    model.study("std1").showAutoSequences("all");

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.sol("sol1").feature("s1").feature("dDef").set("errorchk", false);

    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 19, 0);
    model.result("pg1").label("\u5e94\u529b (solid)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.misesGp_peak"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "Rainbow");
    model.result("pg1").feature("surf1").set("colortabletrans", "none");
    model.result("pg1").feature("surf1").set("colorscalemode", "linear");
    model.result("pg1").feature("surf1").set("resolution", "normal");
    model.result("pg1").feature("surf1").set("colortable", "Prism");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
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
    model.result("pg2").feature("str1").selection()
         .set(3, 4, 6, 7, 8, 9, 12, 13, 15, 16, 17, 18, 19, 20, 22, 23, 24, 26, 29, 31);
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

    return model;
  }

  public static Model run2(Model model) {
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
    model.result("pg3").feature("str1").selection()
         .set(3, 4, 6, 7, 8, 9, 12, 13, 15, 16, 17, 18, 19, 20, 22, 23, 24, 26, 29, 31);
    model.result("pg3").feature("str1").set("inheritplot", "surf1");
    model.result("pg3").feature("str1").feature().create("col1", "Color");
    model.result("pg3").feature("str1").feature("col1").set("expr", "es.normE");
    model.result("pg3").feature("str1").feature("col1").set("colortable", "PrismDark");
    model.result("pg3").feature("str1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("str1").feature("col1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("str1").feature("col1").set("colorcalibration", -0.8);
    model.result("pg3").feature("str1").feature().create("filt1", "Filter");
    model.result("pg3").feature("str1").feature("filt1").set("expr", "!isScalingSystemDomain");
    model.result("pg1").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u9891\u7387\u54cd\u5e94\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg4").set("titletype", "manual");
    model.result("pg4").set("title", "\u9891\u7387\u54cd\u5e94\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(cir.R1.v)", 0);
    model.result("pg4").feature("glob1").setIndex("descr", "\u7535\u538b (V)", 0);
    model.result("pg4").feature("glob1")
         .setIndex("expr", "0.5*intop1(realdot(-solid.rho*g_const*acc,solid.u_tY))*w_plate", 1);
    model.result("pg4").feature("glob1").setIndex("unit", "mW", 1);
    model.result("pg4").feature("glob1").setIndex("descr", "\u8f93\u5165\u673a\u68b0\u529f\u7387 (mW)", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "0.5*realdot(cir.R1.i,cir.R1.v)", 2);
    model.result("pg4").feature("glob1").setIndex("unit", "mW", 2);
    model.result("pg4").feature("glob1").setIndex("descr", "\u8f93\u51fa\u7535\u529f\u7387 (mW)", 2);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").feature("glob1").set("linemarker", "cycle");
    model.result("pg4").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std2").feature("freq").setSolveFor("/physics/es", true);
    model.study("std2").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std2").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std2").feature("freq").set("plist", 70.5);
    model.study("std2").feature("freq").set("useparam", true);
    model.study("std2").feature("freq").setIndex("pname_aux", "acc", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "acc", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std2").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std2").feature("freq").setIndex("pname_aux", "R_load", 0);
    model.study("std2").feature("freq").setIndex("plistarr_aux", "10^range(2,0.25,5)", 0);
    model.study("std2").label("\u8d1f\u8f7d\u76f8\u5173\u6027");
    model.study("std2").setGenPlots(false);
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("s1").feature("dDef").set("errorchk", false);

    model.study("std2").createAutoSequences("all");

    model.sol("sol2").runAll();

    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u8d1f\u8f7d\u76f8\u5173\u6027\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("title", "\u8d1f\u8f7d\u76f8\u5173\u6027\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg5").set("legendpos", "upperleft");
    model.result("pg5").set("xlog", true);
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("freq", "Frequency");
    model.study("std3").feature("freq").setSolveFor("/physics/solid", true);
    model.study("std3").feature("freq").setSolveFor("/physics/es", true);
    model.study("std3").feature("freq").setSolveFor("/physics/cir", true);
    model.study("std3").feature("freq").setSolveFor("/multiphysics/pze1", true);
    model.study("std3").feature("freq").set("plist", 70.5);
    model.study("std3").feature("freq").set("useparam", true);
    model.study("std3").feature("freq").setIndex("pname_aux", "acc", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std3").feature("freq").setIndex("pname_aux", "acc", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "", 0);
    model.study("std3").feature("freq").setIndex("punit_aux", "", 0);
    model.study("std3").feature("freq").setIndex("plistarr_aux", "range(0.25,0.25,2)", 0);
    model.study("std3").label("\u52a0\u901f\u5ea6\u76f8\u5173\u6027");
    model.study("std3").setGenPlots(false);
    model.study("std3").showAutoSequences("all");

    model.sol("sol3").feature("s1").feature("dDef").set("errorchk", false);

    model.study("std3").createAutoSequences("all");

    model.sol("sol3").runAll();

    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u52a0\u901f\u5ea6\u76f8\u5173\u6027\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg6").set("data", "dset3");
    model.result("pg6").set("xlabelactive", true);
    model.result("pg6").set("xlabel", "\u52a0\u901f\u5ea6 (g)");
    model.result("pg6").set("xlog", false);
    model.result("pg6").set("title", "\u52a0\u901f\u5ea6\u76f8\u5173\u6027\uff1a\u7535\u538b\u548c\u529f\u7387");
    model.result("pg6").run();

    model.title("\u538b\u7535\u80fd\u91cf\u91c7\u96c6\u5668");

    model
         .description("\u672c\u6a21\u578b\u6f14\u793a\u5982\u4f55\u5206\u6790\u4e00\u4e2a\u57fa\u4e8e\u60ac\u81c2\u6881\u7684\u7b80\u5355\u538b\u7535\u80fd\u91cf\u91c7\u96c6\u5668\u3002\u901a\u8fc7\u5411\u8be5\u80fd\u91cf\u91c7\u96c6\u5668\u65bd\u52a0\u4e00\u4e2a\u6b63\u5f26\u52a0\u901f\u5ea6\uff0c\u6765\u8ba1\u7b97\u968f\u9891\u7387\u3001\u8d1f\u8f7d\u963b\u6297\u53ca\u52a0\u901f\u5ea6\u5927\u5c0f\u800c\u53d8\u5316\u7684\u8f93\u51fa\u529f\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("piezoelectric_energy_harvester.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
