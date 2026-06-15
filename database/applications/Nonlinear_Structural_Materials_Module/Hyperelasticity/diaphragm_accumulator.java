/*
 * diaphragm_accumulator.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:30 by COMSOL 6.3.0.290. */
public class diaphragm_accumulator {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Nonlinear_Structural_Materials_Module\\Hyperelasticity");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);
    model.component("comp1").geom("geom1").axisymmetric(true);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.study().create("std1");
    model.study("std1").create("stat", "Stationary");
    model.study("std1").feature("stat").setSolveFor("/physics/solid", true);

    model.component("comp1").geom("geom1").insertFile("diaphragm_accumulator_geom_sequence.mph", "geom1");

    model.param().create("par2");
    model.param("par2").set("para", "1");
    model.param("par2").descr("para", "Stepping parameter");
    model.param("par2").set("p0", "0.9*p1");
    model.param("par2").descr("p0", "Precharge pressure");
    model.param("par2").set("p1", "30[bar]");
    model.param("par2").descr("p1", "Minimum operating pressure");
    model.param("par2").set("p2", "3*p1");
    model.param("par2").descr("p2", "Maximum operating pressure");
    model.param("par2").set("t_end", "30[s]");
    model.param("par2").descr("t_end", "Time");

    model.func().create("int1", "Interpolation");
    model.func("int1").set("funcname", "pressure");
    model.func("int1").setIndex("table", 0, 0, 0);
    model.func("int1").setIndex("table", 0, 0, 1);
    model.func("int1").setIndex("table", "t_end*0.5", 1, 0);
    model.func("int1").setIndex("table", "p2", 1, 1);
    model.func("int1").setIndex("table", "t_end*0.9", 2, 0);
    model.func("int1").setIndex("table", "p1", 2, 1);
    model.func("int1").setIndex("table", "t_end", 3, 0);
    model.func("int1").setIndex("table", "p1", 3, 1);
    model.func("int1").set("interp", "piecewisecubic");
    model.func("int1").setIndex("fununit", "Pa", 0);
    model.func("int1").setIndex("argunit", "s", 0);

    model.component("comp1").pair().create("p1", "Contact");

    model.component("comp1").geom("geom1").run();

    model.component("comp1").pair("p1").source().set(13, 60, 65, 73);
    model.component("comp1").pair("p1").destination().set(2, 25, 40, 61, 63);

    model.component("comp1").physics("solid").feature("lemm1").create("iss1", "InitialStressandStrain", 2);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1").selection().set(8);
    model.component("comp1").physics("solid").feature("lemm1").feature("iss1")
         .set("eil", new String[]{"0", "0", "0", "0", "0.01[mm]/R", "0", "0", "0", "0"});
    model.component("comp1").physics("solid").feature("dcnt1").set("penaltyCtrlPenalty", "ManualTuning");
    model.component("comp1").physics("solid").feature("dcnt1").set("fp_penalty", 5);
    model.component("comp1").physics("solid").create("hmm1", "HyperelasticModel", 2);
    model.component("comp1").physics("solid").feature("hmm1").selection().named("geom1_sel1");
    model.component("comp1").physics("solid").feature("hmm1").set("Compressibility_NeoHookean", "Incompressible");
    model.component("comp1").physics("solid").create("fix1", "Fixed", 1);
    model.component("comp1").physics("solid").feature("fix1").selection().set(31);
    model.component("comp1").physics("solid").create("enc1", "EnclosedCavity", 1);
    model.component("comp1").physics("solid").feature("enc1").selection()
         .set(5, 9, 10, 16, 34, 35, 36, 40, 41, 42, 47, 52, 55, 59, 66, 67, 69, 70, 73, 75);
    model.component("comp1").physics("solid").feature("enc1").set("volumeType", "openSurface");
    model.component("comp1").physics("solid").feature("enc1").selection("referencePoint").set(13);
    model.component("comp1").physics("solid").feature("enc1").create("pp1", "PrescribedPressure", 1);
    model.component("comp1").physics("solid").feature("enc1").feature("pp1").set("p", "p0*para");
    model.component("comp1").physics("solid").feature("enc1").feature("fl1")
         .set("referencePressure", "fromPrescribedPressure");
    model.component("comp1").physics("solid").feature("enc1").feature("fl1").set("referenceVolume", "userDefined");
    model.component("comp1").physics("solid").create("enc2", "EnclosedCavity", 1);
    model.component("comp1").physics("solid").feature("enc2").set("volumeType", "openSurface");
    model.component("comp1").physics("solid").feature("enc2").selection("referencePoint").set(15);
    model.component("comp1").physics("solid").feature("enc2").selection().set(2, 11, 13, 25, 60, 61, 63, 65);
    model.component("comp1").physics("solid").feature("enc2").feature().remove("fl1");
    model.component("comp1").physics("solid").feature("enc2").create("pp1", "PrescribedPressure", 1);
    model.component("comp1").physics("solid").feature("enc2").feature("pp1").set("p", "pressure(t)");

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
    model.component("comp1").material("mat1").selection().named("geom1_uni4_dom");
    model.component("comp1").material().create("mat2", "Common");
    model.component("comp1").material("mat2").selection().named("geom1_sel1");
    model.component("comp1").material("mat2").propertyGroup().create("Lame", "Lame", "Lame_parameters");
    model.component("comp1").material("mat2").propertyGroup("Lame").set("muLame", new String[]{"0.7[MPa]"});
    model.component("comp1").material("mat2").propertyGroup("def").set("density", new String[]{"1100"});
    model.component("comp1").material().create("mat3", "Common");
    model.component("comp1").material("mat3").selection().named("geom1_sel2");
    model.component("comp1").material("mat3").propertyGroup()
         .create("Enu", "Enu", "Young's_modulus_and_Poisson's_ratio");
    model.component("comp1").material("mat3").propertyGroup("Enu").set("E", new String[]{"1.1[GPa]"});
    model.component("comp1").material("mat3").propertyGroup("Enu").set("nu", new String[]{"0.35"});
    model.component("comp1").material("mat3").propertyGroup("def").set("density", new String[]{"1300"});

    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").selection().set(5, 6);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 9);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size2", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").selection().set(3, 4, 7, 9);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmax", "shell.th/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hminactive", true);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size2").set("hmin", "shell.th/2");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("se1", "SizeExpression");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1").selection().geom("geom1", 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1").selection().set(1, 2);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("se1")
         .set("sizeexpr", "if(R>10[mm], 0.22, 0.5)*dia.th");
    model.component("comp1").mesh("mesh1").feature("ftri1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").selection().set(50, 51, 53, 54, 58);
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("dis1").set("numelem", 3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("stat").set("useadvanceddisable", true);
    model.study("std1").feature("stat").set("disabledphysics", new String[]{"solid/enc1/fl1", "solid/enc2/pp1"});
    model.study("std1").feature("stat").set("useparam", true);
    model.study("std1").feature("stat").setIndex("pname", "dia.alpha", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "dia.alpha", 0);
    model.study("std1").feature("stat").setIndex("plistarr", "", 0);
    model.study("std1").feature("stat").setIndex("punit", "rad", 0);
    model.study("std1").feature("stat").setIndex("pname", "para", 0);
    model.study("std1").feature("stat")
         .setIndex("plistarr", "range(0, 5e-4, 8e-3), range(0.01, 0.01, 0.1), 0.5, 0.7, 1", 0);
    model.study("std1").feature("stat").setIndex("punit", "", 0);
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").set("data", "dset1");
    model.result("pg1").setIndex("looplevel", 30, 0);
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
    model.result("pg1").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg1").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset1solidrev", "Revolve2D");
    model.result().dataset("dset1solidrev").set("data", "dset1");
    model.result().dataset("dset1solidrev").set("revangle", 225);
    model.result().dataset("dset1solidrev").set("startangle", -90);
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").set("data", "dset1solidrev");
    model.result("pg2").setIndex("looplevel", 30, 0);
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
    model.result().dataset("dset1solidrev").set("hasspacevars", true);
    model.result("pg2").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg2").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg2").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg2").feature("surf1").feature("def").set("descractive", true);
    model.result("pg2").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg2").feature("surf1").feature("def").set("scale", "1");
    model.result("pg1").run();
    model.result().configuration().create("prfu1", "PreferredUnits");
    model.result().configuration("prfu1")
         .setIndex("quantityunits", new String[]{"stress", "\u5e94\u529b\u5f20\u91cf", "N/m^2", "N/m^2"}, 0);
    model.result().configuration("prfu1").setIndex("quantityunits", "MPa", 0, 3);
    model.result().configuration("prfu1").apply();
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("titletype", "none");
    model.result("pg1").feature("surf2").set("colortable", "Twilight");
    model.result("pg1").feature("surf2").create("sel1", "Selection");
    model.result("pg1").feature("surf2").feature("sel1").selection().named("geom1_sel1");
    model.result("pg1").run();
    model.result("pg2").run();

    model.study().create("std2");
    model.study("std2").create("time", "Transient");
    model.study("std2").feature("time").setSolveFor("/physics/solid", true);
    model.study("std2").feature("time").set("tlist", "range(0,t_end/100,t_end)");
    model.study("std2").feature("time").set("useinitsol", true);
    model.study("std2").feature("time").set("initmethod", "sol");
    model.study("std2").feature("time").set("initstudy", "std1");
    model.study("std2").feature("time").set("solnum", "last");
    model.study("std2").showAutoSequences("all");

    model.sol("sol2").feature("t1").set("timemethod", "bdf");
    model.sol("sol2").feature("t1").set("tstepsbdf", "strict");
    model.sol("sol2").runAll();

    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").setIndex("looplevel", 101, 0);
    model.result("pg3").label("\u5e94\u529b (solid) 1");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("surf1").set("threshold", "manual");
    model.result("pg3").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("surf1").set("colortable", "Rainbow");
    model.result("pg3").feature("surf1").set("colortabletrans", "none");
    model.result("pg3").feature("surf1").set("colorscalemode", "linear");
    model.result("pg3").feature("surf1").set("resolution", "normal");
    model.result("pg3").feature("surf1").set("colortable", "Prism");
    model.result("pg3").feature("surf1").create("def", "Deform");
    model.result("pg3").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").feature("surf1").feature("def").set("expr", new String[]{"u", "w"});
    model.result("pg3").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().dataset().create("dset2solidrev", "Revolve2D");
    model.result().dataset("dset2solidrev").set("data", "dset2");
    model.result().dataset("dset2solidrev").set("revangle", 225);
    model.result().dataset("dset2solidrev").set("startangle", -90);
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset2solidrev");
    model.result("pg4").setIndex("looplevel", 101, 0);
    model.result("pg4").label("\u5e94\u529b, 3D (solid) 1");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg4").feature("surf1").set("threshold", "manual");
    model.result("pg4").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg4").feature("surf1").set("colortable", "Rainbow");
    model.result("pg4").feature("surf1").set("colortabletrans", "none");
    model.result("pg4").feature("surf1").set("colorscalemode", "linear");
    model.result("pg4").feature("surf1").set("colortable", "Prism");
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result().dataset("dset2solidrev").set("hasspacevars", true);
    model.result("pg4").feature("surf1").feature("def").set("revcoordsys", "cylindrical");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("descractive", true);
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", "1");
    model.result("pg3").run();
    model.result("pg3").setIndex("looplevel", 51, 0);
    model.result("pg3").run();
    model.result("pg3").feature().duplicate("surf2", "surf1");
    model.result("pg3").run();
    model.result("pg3").feature("surf2").set("titletype", "none");
    model.result("pg3").feature("surf2").set("colortable", "Twilight");
    model.result("pg3").feature("surf2").create("sel1", "Selection");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("surf2").feature("sel1").selection().named("geom1_sel1");
    model.result("pg3").run();
    model.result("pg4").run();
    model.result().create("pg5", "PlotGroup1D");
    model.result("pg5").run();
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("titletype", "label");
    model.result("pg5").set("ylabelactive", true);
    model.result("pg5").create("glob1", "Global");
    model.result("pg5").feature("glob1").set("markerpos", "datapoints");
    model.result("pg5").feature("glob1").set("linewidth", "preference");
    model.result("pg5").feature("glob1").setIndex("expr", "solid.enc1.p_rel", 0);
    model.result("pg5").feature("glob1").setIndex("unit", "bar", 0);
    model.result("pg5").feature("glob1").setIndex("descr", "Nitrogen", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "solid.enc2.p_rel", 1);
    model.result("pg5").feature("glob1").setIndex("unit", "bar", 1);
    model.result("pg5").feature("glob1").setIndex("descr", "Hydraulic oil", 1);
    model.result("pg5").run();
    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("titletype", "label");
    model.result("pg6").set("showlegends", false);
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").setIndex("expr", "solid.enc2.DeltaV-at(0[s],solid.enc2.DeltaV)", 0);
    model.result("pg6").feature("glob1").setIndex("unit", "ml", 0);
    model.result("pg6").feature("glob1").setIndex("descr", "Volume Change", 0);
    model.result("pg6").run();
    model.result("pg6").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg6").feature("glob1").feature("gmrk1").set("linewidth", "preference");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").feature("gmrk1").set("display", "max");
    model.result("pg6").feature("glob1").feature("gmrk1").set("includeunit", true);
    model.result("pg6").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg6").feature("glob1").feature("gmrk1").set("anchorpoint", "uppermiddle");
    model.result("pg6").run();
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{});
    model.result("pg7").feature("glob1").set("descr", new String[]{});
    model.result("pg7").feature("glob1").setIndex("expr", "solid.enc1.fl1.T", 0);
    model.result("pg7").feature("glob1").setIndex("unit", "\u00b0C", 0);
    model.result("pg7").run();
    model.result().create("pg8", "PlotGroup3D");
    model.result("pg8").run();
    model.result("pg8").set("data", "dset2solidrev");
    model.result("pg8").setIndex("looplevel", 51, 0);
    model.result("pg8").set("titletype", "none");
    model.result("pg8").set("frametype", "spatial");
    model.result("pg8").create("surf1", "Surface");
    model.result("pg8").feature("surf1").set("expr", "1");
    model.result("pg8").feature("surf1").create("mtrl1", "MaterialAppearance");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("mtrl1").set("appearance", "custom");
    model.result("pg8").feature("surf1").feature("mtrl1").set("family", "steel");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("def1", "Deform");
    model.result("pg8").feature("surf1").feature("def1").set("revcoordsys", "cylindrical");
    model.result("pg8").run();
    model.result("pg8").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg8").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg8").run();
    model.result("pg8").feature("surf1").create("sel1", "Selection");
    model.result("pg8").feature("surf1").feature("sel1").selection().set(3, 4, 5, 6, 7, 8, 9);
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf2", "surf1");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("mtrl1").set("family", "rubber");
    model.result("pg8").feature("surf2").feature("mtrl1").set("color", "black");
    model.result("pg8").run();
    model.result("pg8").feature("surf2").feature("sel1").selection().named("geom1_sel1");
    model.result("pg8").run();
    model.result("pg8").feature().duplicate("surf3", "surf2");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("mtrl1").set("family", "plastic");
    model.result("pg8").feature("surf3").feature("mtrl1").set("color", "gray");
    model.result("pg8").run();
    model.result("pg8").feature("surf3").feature("sel1").selection().named("geom1_sel2");
    model.result("pg8").run();

    model.view("view3").set("showgrid", false);
    model.view("view3").set("ssao", true);
    model.view("view3").set("environmentmap", "envmap_machineshop2");
    model.view("view3").set("displayoutput", true);
    model.view("view3").set("showaxisorientation", false);

    model.result("pg8").run();

    model.title("\u9694\u819c\u5f0f\u84c4\u80fd\u5668");

    model
         .description("\u9694\u819c\u5f0f\u84c4\u80fd\u5668\u662f\u6db2\u538b\u7cfb\u7edf\u4e2d\u4f7f\u7528\u7684\u4e00\u79cd\u538b\u529b\u5bb9\u5668\uff0c\u5185\u90e8\u91c7\u7528\u6a61\u80f6\u9694\u819c\u6765\u9694\u79bb\u6db2\u538b\u6cb9\u4e0e\u60f0\u6027\u3001\u53ef\u538b\u7f29\u6c14\u4f53\uff08\u901a\u5e38\u662f\u6c2e\u6c14\uff09\u3002\u8fd9\u79cd\u84c4\u80fd\u5668\u5177\u6709\u591a\u79cd\u7528\u9014\uff0c\u4f8b\u5982\uff0c\u53ef\u4ee5\u4e3a\u6db2\u538b\u6cb9\u63d0\u4f9b\u4e34\u65f6\u7684\u80fd\u91cf\u50a8\u5b58\uff0c\u5e76\u8865\u507f\u538b\u529b\u548c\u4f53\u79ef\u7684\u6ce2\u52a8\uff0c\u4ece\u800c\u51cf\u7f13\u8109\u52a8\u548c\u51b2\u51fb\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("diaphragm_accumulator.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
