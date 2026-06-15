/*
 * geared_rotors.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:25 by COMSOL 6.3.0.290. */
public class geared_rotors {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Tutorials,_Transmission");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("rotsld", "SolidRotor", "geom1");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").setSolveFor("/physics/rotsld", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n1", "20", "\u9f7f\u6570\uff0c\u9f7f\u8f6e 1");
    model.param().set("d1", "100[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u9f7f\u8f6e 1");
    model.param().set("n2", "30", "\u9f7f\u6570\uff0c\u9f7f\u8f6e 2");
    model.param().set("d2", "150[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u9f7f\u8f6e 2");
    model.param().set("alpha", "25[deg]", "\u538b\u529b\u89d2");
    model.param().set("beta", "30[deg]", "\u87ba\u65cb\u89d2");
    model.param().set("gr", "n2/n1", "\u9f7f\u8f6e\u6bd4");
    model.param().set("kg", "2e6[N/m]", "\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6");
    model.param().set("kb", "1e7[N/m]", "\u8f74\u627f\u521a\u5ea6");
    model.param().set("omega", "1500[rpm]", "\u9a71\u52a8\u8f74\u7684\u89d2\u901f\u5ea6");
    model.param().set("Text", "1e2[N*m]", "\u4ece\u52a8\u8f74\u4e0a\u7684\u963b\u529b\u77e9");
    model.param().set("t_end", "1.5/omega", "\u603b\u4eff\u771f\u65f6\u95f4");

    model.component("comp1").func().create("step1", "Step");
    model.component("comp1").func("step1").set("location", "t_end/12");
    model.component("comp1").func("step1").set("smooth", "t_end/6");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "geared_rotors.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
    model.component("comp1").geom("geom1").feature("fin").set("createpairs", false);
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

    model.component("comp1").physics("rotsld").prop("RotorProperties").set("freqr", "omega");
    model.component("comp1").physics("rotsld").feature("raxi1").feature("fsup1").selection().set(97, 98);
    model.component("comp1").physics("rotsld").feature("raxi1").feature("ssup1").selection().set(119, 120);
    model.component("comp1").physics("rotsld").create("raxi2", "RotorAxis", 3);
    model.component("comp1").physics("rotsld").feature("raxi2").selection().set(5, 6, 7);
    model.component("comp1").physics("rotsld").feature("raxi2").feature("fsup1").selection().set(744, 756);
    model.component("comp1").physics("rotsld").feature("raxi2").feature("ssup1").selection().set(749, 761);
    model.component("comp1").physics("rotsld").create("raxi3", "RotorAxis", 3);
    model.component("comp1").physics("rotsld").feature("raxi3").selection().set(3, 4);
    model.component("comp1").physics("rotsld").feature("raxi3").feature("fsup1").selection().set(355, 363);
    model.component("comp1").physics("rotsld").feature("raxi3").feature("ssup1").selection().set(358, 366);
    model.component("comp1").physics("rotsld").create("rsp1", "RotorSpeed", 3);
    model.component("comp1").physics("rotsld").feature("rsp1").selection().set(5, 6, 7);
    model.component("comp1").physics("rotsld").feature("rsp1").set("freqr", "-omega/gr");
    model.component("comp1").physics("rotsld").create("rsp2", "RotorSpeed", 3);
    model.component("comp1").physics("rotsld").feature("rsp2").selection().set(3, 4);
    model.component("comp1").physics("rotsld").feature("rsp2").set("freqr", "omega/gr^2");
    model.component("comp1").physics("rotsld").feature("far1").selection().set(57);
    model.component("comp1").physics("rotsld").feature("far1").set("useWeakConstraints", true);
    model.component("comp1").physics("rotsld").create("hlg1", "HelicalGear", 3);
    model.component("comp1").physics("rotsld").feature("hlg1").selection().set(1);
    model.component("comp1").physics("rotsld").feature("hlg1").set("nt", "n1");
    model.component("comp1").physics("rotsld").feature("hlg1").set("dp", "d1");
    model.component("comp1").physics("rotsld").feature("hlg1").set("alpha", "alpha");
    model.component("comp1").physics("rotsld").feature("hlg1").set("beta", "beta");
    model.component("comp1").physics("rotsld").feature("hlg1")
         .set("CenterOfRotationType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("rotsld").feature("hlg1").set("EntityLevel", "Point");
    model.component("comp1").physics("rotsld").feature("hlg1").feature("crp1").selection().set(101, 115);
    model.component("comp1").physics("rotsld").feature().duplicate("hlg2", "hlg1");
    model.component("comp1").physics("rotsld").feature("hlg2").selection().set(5);
    model.component("comp1").physics("rotsld").feature("hlg2").set("nt", "n2");
    model.component("comp1").physics("rotsld").feature("hlg2").set("dp", "d2");
    model.component("comp1").physics("rotsld").feature("hlg2").set("beta", "-beta");
    model.component("comp1").physics("rotsld").feature("hlg2").feature("crp1").selection().set(747, 760);
    model.component("comp1").physics("rotsld").feature().duplicate("hlg3", "hlg1");
    model.component("comp1").physics("rotsld").feature("hlg3").selection().set(6);
    model.component("comp1").physics("rotsld").feature("hlg3").feature("crp1").selection().set(745, 758);
    model.component("comp1").physics("rotsld").feature().duplicate("hlg4", "hlg2");
    model.component("comp1").physics("rotsld").feature("hlg4").selection().set(3);
    model.component("comp1").physics("rotsld").feature("hlg4").feature("crp1").selection().set(357, 364);
    model.component("comp1").physics("rotsld").create("grp1", "GearPair", -1);
    model.component("comp1").physics("rotsld").feature("grp1").set("Wheel", "hlg1");
    model.component("comp1").physics("rotsld").feature("grp1").set("Pinion", "hlg2");
    model.component("comp1").physics("rotsld").feature("grp1").set("IncludeGearElasticity", true);
    model.component("comp1").physics("rotsld").feature("grp1").feature("gel1").set("Specify", "GearPair");
    model.component("comp1").physics("rotsld").feature("grp1").feature("gel1").set("km_dr", "kg");
    model.component("comp1").physics("rotsld").create("grp2", "GearPair", -1);
    model.component("comp1").physics("rotsld").feature("grp2").set("Wheel", "hlg3");
    model.component("comp1").physics("rotsld").feature("grp2").set("Pinion", "hlg4");
    model.component("comp1").physics("rotsld").feature("grp2").set("IncludeGearElasticity", true);
    model.component("comp1").physics("rotsld").feature("grp2").feature("gel1").set("Specify", "GearPair");
    model.component("comp1").physics("rotsld").feature("grp2").feature("gel1").set("km_dr", "kg");
    model.component("comp1").physics("rotsld").create("jrb1", "JournalBearing", 2);
    model.component("comp1").physics("rotsld").feature("jrb1").selection().set(52);
    model.component("comp1").physics("rotsld").feature("jrb1").set("e_aux", new int[]{1, 0, 0});
    model.component("comp1").physics("rotsld").feature("jrb1").set("BearingModel", "kTot");
    model.component("comp1").physics("rotsld").feature("jrb1").set("k_u", new String[]{"kb", "0", "0", "kb"});
    model.component("comp1").physics("rotsld").feature().duplicate("jrb2", "jrb1");
    model.component("comp1").physics("rotsld").feature("jrb2").selection().set(57);
    model.component("comp1").physics("rotsld").feature("jrb2").set("constrainAxialMotion", false);
    model.component("comp1").physics("rotsld").feature().duplicate("jrb3", "jrb2");
    model.component("comp1").physics("rotsld").feature("jrb3").selection().set(397);
    model.component("comp1").physics("rotsld").feature("jrb3").set("constrainAxialMotion", true);
    model.component("comp1").physics("rotsld").feature().duplicate("jrb4", "jrb3");
    model.component("comp1").physics("rotsld").feature("jrb4").selection().set(406);
    model.component("comp1").physics("rotsld").feature("jrb4").set("constrainAxialMotion", false);
    model.component("comp1").physics("rotsld").feature().duplicate("jrb5", "jrb4");
    model.component("comp1").physics("rotsld").feature("jrb5").selection().set(191);
    model.component("comp1").physics("rotsld").feature().duplicate("jrb6", "jrb5");
    model.component("comp1").physics("rotsld").feature("jrb6").selection().set(196);
    model.component("comp1").physics("rotsld").feature("jrb6").set("constrainAxialMotion", true);
    model.component("comp1").physics("rotsld").create("atq1", "AppliedTorque", 2);
    model.component("comp1").physics("rotsld").feature("atq1").selection().set(191);
    model.component("comp1").physics("rotsld").feature("atq1").set("Tax", "Text*step1(t)");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").label("\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387");
    model.study("std1").create("param", "Parametric");
    model.study("std1").feature("param").setIndex("pname", "n1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "n1", 0);
    model.study("std1").feature("param").setIndex("plistarr", "", 0);
    model.study("std1").feature("param").setIndex("punit", "", 0);
    model.study("std1").feature("param").setIndex("pname", "omega", 0);
    model.study("std1").feature("param").setIndex("plistarr", "range(0,500,9000)", 0);
    model.study("std1").feature("param").setIndex("punit", "rpm", 0);
    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);
    model.study("std1").createAutoSequences("all");

    model.sol().create("sol2");
    model.sol("sol2").study("std1");
    model.sol("sol2").label("\u53c2\u6570\u5316\u89e3 1");

    model.batch("p1").feature("so1").set("psol", "sol2");
    model.batch("p1").run("compute");

    model.result().dataset().create("cln1", "CutLine3D");
    model.result().dataset("cln1").label("\u8f74");
    model.result().dataset("cln1").set("data", "none");
    model.result().dataset("cln1")
         .set("genpoints", new String[][]{{"comp1.rotsld.raxi1.r1x", "comp1.rotsld.raxi1.r1y", "comp1.rotsld.raxi1.r1z"}, {"comp1.rotsld.raxi1.r2x", "comp1.rotsld.raxi1.r2y", "comp1.rotsld.raxi1.r2z"}});
    model.result().dataset("cln1").set("bounded", false);
    model.result().dataset("cln1").set("data", "dset2");
    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u632f\u578b (rotsld)");
    model.result("pg1").set("data", "dset2");
    model.result("pg1").setIndex("looplevel", 1, 0);
    model.result("pg1").setIndex("looplevel", 19, 1);
    model.result("pg1").set("showlegends", "off");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").set("smooth", "internal");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u6da1\u52a8 (rotsld)");
    model.result("pg2").set("data", "cln1");
    model.result("pg2").setIndex("looplevel", 1, 0);
    model.result("pg2").setIndex("looplevel", 19, 1);
    model.result("pg2").set("showlegends", "off");
    model.result("pg2").feature().create("wp1", "Whirl");
    model.result("pg2").feature("wp1").set("nrings", 10);
    model.result("pg2").feature("wp1").set("colortable", "TrafficLight");
    model.result("pg2").feature("wp1").set("smooth", "internal");
    model.result("pg2").feature("wp1").set("data", "parent");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("data", "dset2");
    model.result().evaluationGroup("std1EvgFrq")
         .label("\u7279\u5f81\u9891\u7387 (\u7814\u7a76 1 - \u7279\u5f81\u9891\u7387)");
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
    model.result("pg1").run();
    model.result("pg1").set("looplevel", new int[]{2, 19});
    model.result("pg1").run();
    model.result().create("pg3", "PlotGroup1D");
    model.result("pg3").set("data", "dset2");
    model.result("pg3").create("lnsg1", "LineSegments");
    model.result("pg3").feature("lnsg1").set("data", "dset2");
    model.result("pg3").feature("lnsg1").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg3").feature("lnsg1").set("xexpr", new String[]{"0[Hz]", "rotsld.freqr"});
    model.result("pg3").feature("lnsg1").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg1").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg1").set("yexpr", new String[]{"0[Hz]", "0[Hz]"});
    model.result("pg3").feature("lnsg1").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg1").set("ydescr", new String[]{"\u539f\u70b9", "0 \u00d7 \u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg1").set("linestyle", "cycle");
    model.result("pg3").feature("lnsg1").set("linecolor", "black");
    model.result("pg3").feature("lnsg1").set("linewidth", 1);
    model.result("pg3").feature("lnsg1").set("legend", true);
    model.result("pg3").feature("lnsg1").set("legendmethod", "manual");
    model.result("pg3").feature("lnsg1").set("legends", "0\\times\\Omega");
    model.result("pg3").feature("lnsg1").label("\u8f85\u52a9\u7ebf (0\u00d7\u03a9)");
    model.result("pg3").create("lnsg2", "LineSegments");
    model.result("pg3").feature("lnsg2").set("data", "dset2");
    model.result("pg3").feature("lnsg2").set("looplevelinput", new String[][]{{"last", "last"}});
    model.result("pg3").feature("lnsg2").set("xexpr", new String[]{"0[Hz]", "min(real(freq)/2,rotsld.freqr)"});
    model.result("pg3").feature("lnsg2").set("xunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg2").set("xdescr", new String[]{"\u539f\u70b9", "\u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg2").set("yexpr", new String[]{"0[Hz]", "min(real(freq),2*rotsld.freqr)"});
    model.result("pg3").feature("lnsg2").set("yunit", new String[]{"Hz", "Hz"});
    model.result("pg3").feature("lnsg2").set("ydescr", new String[]{"\u539f\u70b9", "2 \u00d7 \u89d2\u901f\u5ea6"});
    model.result("pg3").feature("lnsg2").set("linestyle", "cycle");
    model.result("pg3").feature("lnsg2").set("linecolor", "black");
    model.result("pg3").feature("lnsg2").set("linewidth", 1);
    model.result("pg3").feature("lnsg2").set("legend", true);
    model.result("pg3").feature("lnsg2").set("legendmethod", "manual");
    model.result("pg3").feature("lnsg2").set("legends", "2\\times\\Omega");
    model.result("pg3").feature("lnsg2").label("\u8f85\u52a9\u7ebf (2\u00d7\u03a9)");
    model.result("pg3").create("glob1", "Global");
    model.result("pg3").feature("glob1").set("expr", "real(freq)");
    model.result("pg3").feature("glob1").set("descr", "\u963b\u5c3c\u56fa\u6709\u9891\u7387");
    model.result("pg3").feature("glob1").set("xdata", "expr");
    model.result("pg3").feature("glob1").set("xdataexpr", "rotsld.freqr");
    model.result("pg3").feature("glob1").set("xdataunit", "Hz");
    model.result("pg3").feature("glob1").set("xdatadescr", "\u65cb\u8f6c\u901f\u5ea6");
    model.result("pg3").feature("glob1").set("linestyle", "solid");
    model.result("pg3").feature("glob1").set("linecolor", "cycle");
    model.result("pg3").feature("glob1").set("linewidth", 2);
    model.result("pg3").feature("glob1").set("legend", false);
    model.result("pg3").feature("glob1").set("xdatasolnumtype", "outer");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg3").feature("glob1").label("\u56fa\u6709\u9891\u7387");
    model.result("pg3").feature("glob1").create("col1", "Color");
    model.result("pg3").feature("glob1").feature("col1").set("expr", "rotsld.i_sd");
    model.result("pg3").feature("glob1").feature("col1").set("colorlegend", false);
    model.result("pg3").feature("glob1").feature("col1").label("\u65b9\u5411\u6027");
    model.result("pg3").feature("glob1").feature("col1").set("colortable", "Spectrum");
    model.result("pg3").feature("glob1").feature("col1").set("rangecoloractive", true);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormin", -1.3);
    model.result("pg3").feature("glob1").feature("col1").set("rangecolormax", 1.3);
    model.result("pg3").feature("glob1").feature("col1").set("colorscalemode", "linearsymmetric");
    model.result("pg3").feature("glob1").create("gmrk1", "GraphMarker");
    model.result("pg3").feature("glob1").feature("gmrk1").set("displaymode", "intersection");
    model.result("pg3").feature("glob1").feature("gmrk1").set("intersectionline", "general");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffc", 0);
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffa", " -0 -2");
    model.result("pg3").feature("glob1").feature("gmrk1").set("generalcoeffb", 1);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includexcoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("includeycoord", false);
    model.result("pg3").feature("glob1").feature("gmrk1").set("precision", 3);
    model.result("pg3").feature("glob1").feature("gmrk1").set("pointradius", 2.5);
    model.result("pg3").feature("glob1").feature("gmrk1").set("anchorpoint", "lowermiddle");
    model.result("pg3").feature("glob1").feature("gmrk1").label("\u4ea4\u96c6 ([0,2]\u00d7\u03a9)");
    model.result("pg3").set("titletype", "manual");
    model.result("pg3").set("title", "\u574e\u8d1d\u5c14\u56fe");
    model.result("pg3").label("\u574e\u8d1d\u5c14\u56fe (rotsld)");
    model.result("pg3").label("\u574e\u8d1d\u5c14\u56fe (rotsld)");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").feature("glob1").feature().remove("col1");
    model.result("pg3").run();
    model.result("pg3").run();
    model.result("pg3").run();

    model.study().create("std2");
    model.study("std2").create("freq", "Frequency");
    model.study("std2").feature("freq").setSolveFor("/physics/rotsld", true);
    model.study("std2").label("\u7814\u7a76 2 - \u9891\u57df");
    model.study("std2").setGenPlots(false);
    model.study("std2").create("param", "Parametric");
    model.study("std2").feature("param").setIndex("pname", "n1", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "n1", 0);
    model.study("std2").feature("param").setIndex("plistarr", "", 0);
    model.study("std2").feature("param").setIndex("punit", "", 0);
    model.study("std2").feature("param").setIndex("pname", "omega", 0);
    model.study("std2").feature("param")
         .setIndex("plistarr", "range(1000,1000,2000) range(2500,50,6500) range(7000,1000,9000)", 0);
    model.study("std2").feature("param").setIndex("punit", "rpm", 0);
    model.study("std2").feature("freq").set("plist", "2*omega");
    model.study("std2").feature("freq").set("useadvanceddisable", true);
    model.study("std2").feature("freq").set("disabledphysics", new String[]{"rotsld/atq1"});
    model.study("std2").createAutoSequences("all");

    model.sol().create("sol23");
    model.sol("sol23").study("std2");
    model.sol("sol23").label("\u53c2\u6570\u5316\u89e3 2");

    model.batch("p2").feature("so1").set("psol", "sol23");
    model.batch("p2").run("compute");

    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").set("data", "dset4");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").setIndex("expr", "abs(rotsld.hlg1.u)", 0);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(rotsld.hlg2.u)", 1);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(rotsld.hlg3.u)", 2);
    model.result("pg4").feature("glob1").setIndex("expr", "abs(rotsld.hlg4.u)", 3);
    model.result("pg4").feature("glob1").set("xdatasolnumtype", "outer");
    model.result("pg4").feature("glob1").set("xdata", "expr");
    model.result("pg4").feature("glob1").set("xdataexpr", "2*pi[rad]*omega");
    model.result("pg4").feature("glob1").set("xdatadescractive", true);
    model.result("pg4").feature("glob1").set("xdatadescr", "omega");
    model.result("pg4").feature("glob1").set("linestyle", "cycle");
    model.result("pg4").feature("glob1").set("linewidth", 3);
    model.result("pg4").feature("glob1").set("legendmethod", "manual");
    model.result("pg4").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 1", 0);
    model.result("pg4").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 2", 1);
    model.result("pg4").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 3", 2);
    model.result("pg4").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 4", 3);
    model.result("pg4").run();
    model.result("pg4").label("\u9891\u7387\u54cd\u5e94\uff1ax \u65b9\u5411\u4f4d\u79fb");
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "x \u65b9\u5411\u4f4d\u79fb (m)");
    model.result("pg4").set("ylog", true);
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").run();
    model.result("pg5").feature("glob1").setIndex("expr", "abs(rotsld.hlg1.thz)", 0);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(rotsld.hlg2.thz)", 1);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(rotsld.hlg3.thz)", 2);
    model.result("pg5").feature("glob1").setIndex("expr", "abs(rotsld.hlg4.thz)", 3);
    model.result("pg5").run();
    model.result("pg5").label("\u9891\u7387\u54cd\u5e94\uff1az \u65b9\u5411\u65cb\u8f6c");
    model.result("pg5").set("ylabel", "z \u65b9\u5411\u65cb\u8f6c (rad)");
    model.result("pg5").run();

    model.study().create("std3");
    model.study("std3").create("time", "Transient");
    model.study("std3").feature("time").setSolveFor("/physics/rotsld", true);
    model.study("std3").label("\u7814\u7a76 3\uff1a\u77ac\u6001");
    model.study("std3").feature("time").set("tlist", "range(0,t_end/150,t_end)");
    model.study("std3").showAutoSequences("all");

    model.sol("sol110").feature("t1").feature("fc1").set("jtech", "onevery");

    model.study("std3").createAutoSequences("all");

    model.sol("sol110").runAll();

    model.result().create("pg6", "PlotGroup3D");
    model.result("pg6").label("\u5e94\u529b (rotsld)");
    model.result("pg6").set("data", "dset5");
    model.result("pg6").setIndex("looplevel", 151, 0);
    model.result("pg6").feature().create("surf1", "Surface");
    model.result("pg6").feature("surf1").label("\u8868\u9762");
    model.result("pg6").feature("surf1").set("expr", "rotsld.mises");
    model.result("pg6").feature("surf1").set("colortable", "Prism");
    model.result("pg6").feature("surf1").set("smooth", "internal");
    model.result("pg6").feature("surf1").set("threshold", "manual");
    model.result("pg6").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg6").feature("surf1").set("data", "parent");
    model.result("pg6").feature("surf1").feature().create("def1", "Deform");
    model.result("pg6").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg6").run();
    model.result().dataset().duplicate("dset6", "dset5");
    model.result().dataset("dset6").selection().geom("geom1", 3);
    model.result().dataset("dset6").selection().geom("geom1", 3);
    model.result().dataset("dset6").selection().set(1, 3, 5, 6);
    model.result("pg6").run();
    model.result("pg6").run();
    model.result("pg6").feature("surf1").feature("def1")
         .set("expr", new String[]{"rotsld.us_fix", "rotsld.vs_fix", "rotsld.ws_fix"});
    model.result("pg6").feature("surf1").feature("def1")
         .set("descr", "\u4f4d\u79fb\u573a\uff0c\u56fa\u5b9a\u5750\u6807\u7cfb");
    model.result("pg6").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg6").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg6").run();
    model.result("pg6").feature().duplicate("surf2", "surf1");
    model.result("pg6").run();
    model.result("pg6").feature("surf2").set("data", "dset6");
    model.result("pg6").feature("surf2").set("expr", "1");
    model.result("pg6").feature("surf2").set("coloring", "uniform");
    model.result("pg6").feature("surf2").set("color", "gray");
    model.result("pg6").run();
    model.result("pg6").feature("surf1").set("rangecoloractive", true);
    model.result("pg6").feature("surf1").set("rangecolormax", "1e7");
    model.result("pg6").run();
    model.result().dataset().create("cpt1", "CutPoint3D");
    model.result().dataset("cpt1").set("data", "dset5");
    model.result().dataset("cpt1")
         .set("pointx", "rotsld.hlg1.xcx, rotsld.hlg2.xcx, rotsld.hlg3.xcx, rotsld.hlg4.xcx");
    model.result().dataset("cpt1")
         .set("pointy", "rotsld.hlg1.xcy, rotsld.hlg2.xcy, rotsld.hlg3.xcy, rotsld.hlg4.xcy");
    model.result().dataset("cpt1")
         .set("pointz", "rotsld.hlg1.xcz, rotsld.hlg2.xcz, rotsld.hlg3.xcz, rotsld.hlg4.xcz");
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").set("data", "cpt1");
    model.result("pg7").create("ptgr1", "PointGraph");
    model.result("pg7").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg7").feature("ptgr1").set("linewidth", "preference");
    model.result("pg7").feature("ptgr1").set("expr", "w");
    model.result("pg7").feature("ptgr1").set("xdata", "expr");
    model.result("pg7").feature("ptgr1").set("xdataexpr", "u");
    model.result("pg7").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg7").feature("ptgr1").set("linewidth", 3);
    model.result("pg7").feature("ptgr1").set("legend", true);
    model.result("pg7").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u9f7f\u8f6e 1", 0);
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u9f7f\u8f6e 2", 1);
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u9f7f\u8f6e 3", 2);
    model.result("pg7").feature("ptgr1").setIndex("legends", "\u9f7f\u8f6e 4", 3);
    model.result("pg7").run();
    model.result("pg7").label("\u9f7f\u8f6e\u8f68\u9053\uff0c\u65cb\u8f6c\u5750\u6807\u7cfb");
    model.result("pg7").set("titletype", "label");
    model.result("pg7").set("preserveaspect", true);
    model.result("pg7").set("legendpos", "lowerleft");
    model.result("pg7").run();
    model.result().duplicate("pg8", "pg7");
    model.result("pg8").run();
    model.result("pg8").run();
    model.result("pg8").feature("ptgr1").set("expr", "rotsld.ws_fix");
    model.result("pg8").feature("ptgr1").set("xdataexpr", "rotsld.us_fix");
    model.result("pg8").run();
    model.result("pg8").label("\u9f7f\u8f6e\u8f68\u9053\uff0c\u56fa\u5b9a\u5750\u6807\u7cfb");
    model.result("pg8").run();
    model.result().create("pg9", "PlotGroup1D");
    model.result("pg9").run();
    model.result("pg9").set("data", "dset5");
    model.result("pg9").create("glob1", "Global");
    model.result("pg9").feature("glob1").set("markerpos", "datapoints");
    model.result("pg9").feature("glob1").set("linewidth", "preference");
    model.result("pg9").feature("glob1").setIndex("expr", "rotsld.grp1.th_el", 0);
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 0);
    model.result("pg9").feature("glob1").setIndex("expr", "rotsld.grp2.th_el", 1);
    model.result("pg9").feature("glob1").setIndex("unit", "deg", 1);
    model.result("pg9").feature("glob1").set("linestyle", "cycle");
    model.result("pg9").feature("glob1").set("linewidth", 3);
    model.result("pg9").feature("glob1").set("legendmethod", "manual");
    model.result("pg9").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 1", 0);
    model.result("pg9").feature("glob1").setIndex("legends", "\u9f7f\u8f6e 2", 1);
    model.result("pg9").run();
    model.result("pg9").label("\u9f7f\u8f6e DTE");
    model.result("pg9").set("titletype", "manual");
    model.result("pg9").set("title", "\u52a8\u6001\u4f20\u52a8\u8bef\u5dee");
    model.result("pg9").set("legendpos", "lowerleft");
    model.result("pg9").run();
    model.result().dataset().create("cpt2", "CutPoint3D");
    model.result().dataset("cpt2").set("data", "dset5");
    model.result().dataset("cpt2").set("pointx", 0);
    model.result().dataset("cpt2").set("pointy", "1.25*d1");
    model.result().dataset("cpt2").set("pointz", 0);
    model.result().dataset("cpt2").set("snapping", "boundary");
    model.result().create("pg10", "PlotGroup1D");
    model.result("pg10").run();
    model.result("pg10").set("data", "cpt2");
    model.result("pg10").create("ptgr1", "PointGraph");
    model.result("pg10").feature("ptgr1").set("markerpos", "datapoints");
    model.result("pg10").feature("ptgr1").set("linewidth", "preference");
    model.result("pg10").feature("ptgr1").set("expr", "rotsld.jrb2.f2");
    model.result("pg10").feature("ptgr1").set("linestyle", "cycle");
    model.result("pg10").feature("ptgr1").set("linewidth", 3);
    model.result("pg10").feature("ptgr1").set("legendmethod", "manual");
    model.result("pg10").feature("ptgr1").set("legend", true);
    model.result("pg10").feature("ptgr1").setIndex("legends", "x \u5206\u91cf", 0);
    model.result("pg10").feature().duplicate("ptgr2", "ptgr1");
    model.result("pg10").run();
    model.result("pg10").feature("ptgr2").set("expr", "rotsld.jrb2.f3");
    model.result("pg10").feature("ptgr2").setIndex("legends", "z \u5206\u91cf", 0);
    model.result("pg10").run();
    model.result("pg10").label("\u8f74\u627f\u529b");
    model.result("pg10").set("titletype", "label");
    model.result("pg10").set("ylabelactive", true);
    model.result("pg10").set("ylabel", "\u529b (N)");
    model.result("pg10").set("legendpos", "lowerleft");
    model.result("pg10").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");
    model.result().export("anim1").set("gltfincludelines", "on");
    model.result().export("anim1").set("title1d", "on");
    model.result().export("anim1").set("legend1d", "on");
    model.result().export("anim1").set("logo1d", "on");
    model.result().export("anim1").set("options1d", "on");
    model.result().export("anim1").set("title2d", "on");
    model.result().export("anim1").set("legend2d", "on");
    model.result().export("anim1").set("logo2d", "on");
    model.result().export("anim1").set("options2d", "off");
    model.result().export("anim1").set("title3d", "on");
    model.result().export("anim1").set("legend3d", "on");
    model.result().export("anim1").set("logo3d", "on");
    model.result().export("anim1").set("options3d", "off");
    model.result().export("anim1").set("axisorientation", "on");
    model.result().export("anim1").set("grid", "on");
    model.result().export("anim1").set("axes1d", "on");
    model.result().export("anim1").set("axes2d", "on");
    model.result().export("anim1").set("showgrid", "on");
    model.result().export("anim1").showFrame();
    model.result().export("anim1").set("plotgroup", "pg6");
    model.result().export("anim1").set("maxframes", 50);
    model.result("pg6").run();

    model.title("\u901a\u8fc7\u659c\u9f7f\u8f6e\u8fde\u63a5\u7684\u8f6c\u5b50");

    model
         .description("\u5728\u672c\u6559\u5b66\u6a21\u578b\u4e2d\uff0c\u60a8\u5c06\u5b66\u4e60\u5982\u4f55\u4f7f\u7528\u201c\u8f6c\u5b50\u52a8\u529b\u5b66\u6a21\u5757\u201d\uff08\u201c\u7ed3\u6784\u529b\u5b66\u6a21\u5757\u201d\u548c COMSOL Multiphysics\u00ae \u7684\u9644\u52a0\u4ea7\u54c1\uff09\u5bf9\u901a\u8fc7\u659c\u9f7f\u8f6e\u8fde\u63a5\u7684\u591a\u4e2a\u8f6c\u5b50\u8fdb\u884c\u5efa\u6a21\u3002\u901a\u5e38\u60c5\u51b5\u4e0b\uff0c\u7cfb\u7edf\u4e2d\u5b58\u5728\u7684\u9f7f\u8f6e\u4f1a\u4f7f\u8f6c\u5b50\u53d1\u751f\u6a2a\u5411\u548c\u626d\u8f6c\u632f\u52a8\uff0c\u5efa\u6a21\u4eff\u771f\u8fc7\u7a0b\u4e2d\u53ef\u4ee5\u5047\u8bbe\u9f7f\u8f6e\u4e4b\u95f4\u4e3a\u5f39\u6027\u556e\u5408\uff0c\u5e76\u5177\u6709\u6052\u5b9a\u7684\u521a\u5ea6\u503c\u3002\n\n\u6211\u4eec\u6f14\u793a\u4e86\u4e00\u79cd\u7279\u5f81\u9891\u7387\u5206\u6790\uff0c\u7528\u4e8e\u8ba1\u7b97\u9a71\u52a8\u8f6c\u5b50\u5728\u4e0d\u540c\u8f6c\u901f\u65f6\u7cfb\u7edf\u7684\u7279\u5f81\u9891\u7387\u3002\u6b64\u5916\uff0c\u8fd8\u5bf9\u9a71\u52a8\u8f6c\u5b50\u7684\u7ed9\u5b9a\u8f6c\u901f\u548c\u4ece\u52a8\u8f6c\u5b50\u4e0a\u7684\u8d1f\u8f7d\u626d\u77e9\u6267\u884c\u77ac\u6001\u5206\u6790\u3002\u901a\u8fc7\u8fd9\u4e9b\u5206\u6790\uff0c\u6211\u4eec\u53ef\u4ee5\u8ba1\u7b97\u9f7f\u8f6e\u4e2d\u5fc3\u7684\u8f68\u9053\u548c\u8f74\u627f\u4e0a\u7684\u529b\uff0c\u5176\u4e2d\u5bf9\u521a\u6027\u548c\u5f39\u6027\u9f7f\u8f6e\u556e\u5408\u6267\u884c\u7684\u77ac\u6001\u5206\u6790\uff0c\u53ef\u4ee5\u7814\u7a76\u9f7f\u8f6e\u556e\u5408\u521a\u5ea6\u5bf9\u8f6c\u5b50\u632f\u52a8\u7684\u5f71\u54cd\u3002\n\n\u672c\u6559\u5b66\u6a21\u578b\u7684\u4eff\u771f\u7ed3\u679c\u5305\u62ec\uff1a\u4e00\u4e2a\u663e\u793a\u7279\u5f81\u9891\u7387\u968f\u8f6c\u901f\u53d8\u5316\u7684\u574e\u8d1d\u5c14\u56fe\u3001\u8f6c\u5b50\u7684\u4e34\u754c\u8f6c\u901f\u3001\u9f7f\u8f6e\u4f4d\u79fb\u548c\u65cb\u8f6c\u7684\u9891\u7387\u54cd\u5e94\u66f2\u7ebf\u3001\u8f74\u4e0a\u7684 von\u00a0Mises \u5e94\u529b\u5206\u5e03\u3001\u65cb\u8f6c\u548c\u56fa\u5b9a\u53c2\u8003\u5750\u6807\u7cfb\u4e2d\u9f7f\u8f6e\u4e2d\u5fc3\u7684\u8f68\u9053\uff0c\u4ee5\u53ca\u5c06\u65cb\u8f6c\u4ece\u4e00\u4e2a\u8f74\u4f20\u9012\u5230\u53e6\u4e00\u4e2a\u8f74\u65f6\u7684\u52a8\u6001\u4f20\u52a8\u8bef\u5dee\u3002");

    model.mesh().clearMeshes();

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
    model.sol("sol43").clearSolutionData();
    model.sol("sol44").clearSolutionData();
    model.sol("sol45").clearSolutionData();
    model.sol("sol46").clearSolutionData();
    model.sol("sol47").clearSolutionData();
    model.sol("sol48").clearSolutionData();
    model.sol("sol49").clearSolutionData();
    model.sol("sol50").clearSolutionData();
    model.sol("sol51").clearSolutionData();
    model.sol("sol52").clearSolutionData();
    model.sol("sol53").clearSolutionData();
    model.sol("sol54").clearSolutionData();
    model.sol("sol55").clearSolutionData();
    model.sol("sol56").clearSolutionData();
    model.sol("sol57").clearSolutionData();
    model.sol("sol58").clearSolutionData();
    model.sol("sol59").clearSolutionData();
    model.sol("sol60").clearSolutionData();
    model.sol("sol61").clearSolutionData();
    model.sol("sol62").clearSolutionData();
    model.sol("sol63").clearSolutionData();
    model.sol("sol64").clearSolutionData();
    model.sol("sol65").clearSolutionData();
    model.sol("sol66").clearSolutionData();
    model.sol("sol67").clearSolutionData();
    model.sol("sol68").clearSolutionData();
    model.sol("sol69").clearSolutionData();
    model.sol("sol70").clearSolutionData();
    model.sol("sol71").clearSolutionData();
    model.sol("sol72").clearSolutionData();
    model.sol("sol73").clearSolutionData();
    model.sol("sol74").clearSolutionData();
    model.sol("sol75").clearSolutionData();
    model.sol("sol76").clearSolutionData();
    model.sol("sol77").clearSolutionData();
    model.sol("sol78").clearSolutionData();
    model.sol("sol79").clearSolutionData();
    model.sol("sol80").clearSolutionData();
    model.sol("sol81").clearSolutionData();
    model.sol("sol82").clearSolutionData();
    model.sol("sol83").clearSolutionData();
    model.sol("sol84").clearSolutionData();
    model.sol("sol85").clearSolutionData();
    model.sol("sol86").clearSolutionData();
    model.sol("sol87").clearSolutionData();
    model.sol("sol88").clearSolutionData();
    model.sol("sol89").clearSolutionData();
    model.sol("sol90").clearSolutionData();
    model.sol("sol91").clearSolutionData();
    model.sol("sol92").clearSolutionData();
    model.sol("sol93").clearSolutionData();
    model.sol("sol94").clearSolutionData();
    model.sol("sol95").clearSolutionData();
    model.sol("sol96").clearSolutionData();
    model.sol("sol97").clearSolutionData();
    model.sol("sol98").clearSolutionData();
    model.sol("sol99").clearSolutionData();
    model.sol("sol100").clearSolutionData();
    model.sol("sol101").clearSolutionData();
    model.sol("sol102").clearSolutionData();
    model.sol("sol103").clearSolutionData();
    model.sol("sol104").clearSolutionData();
    model.sol("sol105").clearSolutionData();
    model.sol("sol106").clearSolutionData();
    model.sol("sol107").clearSolutionData();
    model.sol("sol108").clearSolutionData();
    model.sol("sol109").clearSolutionData();
    model.sol("sol110").clearSolutionData();

    model.label("geared_rotors.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
