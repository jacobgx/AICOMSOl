/*
 * bracket_eigenfrequency_shape_optimization.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 13:18 by COMSOL 6.3.0.290. */
public class bracket_eigenfrequency_shape_optimization {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Structural_Mechanics_Module\\Tutorials");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("solid", "SolidMechanics", "geom1");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("type", "native");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "bracket.mphbin");
    model.component("comp1").geom("geom1").feature("imp1").importData();
    model.component("comp1").geom("geom1").run("imp1");
    model.component("comp1").geom("geom1").create("blk1", "Block");
    model.component("comp1").geom("geom1").selection().create("csel1", "CumulativeSelection");
    model.component("comp1").geom("geom1").selection("csel1").label("\u5206\u5272\u5757");
    model.component("comp1").geom("geom1").feature("blk1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").feature("blk1").set("size", new double[]{0.025, 0.13, 0.04});
    model.component("comp1").geom("geom1").feature("blk1").set("pos", new double[]{-0.11, -0.12, 0.025});
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").run("blk1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir1").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("mir2", "Mirror");
    model.component("comp1").geom("geom1").feature("mir2").selection("input").named("csel1");
    model.component("comp1").geom("geom1").feature("mir2").set("keep", true);
    model.component("comp1").geom("geom1").feature("mir2").set("axis", new int[]{1, 0, 0});
    model.component("comp1").geom("geom1").feature("mir2").set("contributeto", "csel1");
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").run("mir2");
    model.component("comp1").geom("geom1").create("par1", "Partition");
    model.component("comp1").geom("geom1").feature("par1").selection("input").set("imp1");
    model.component("comp1").geom("geom1").feature("par1").selection("tool").named("csel1");
    model.component("comp1").geom("geom1").run("par1");
    model.component("comp1").geom("geom1").run("fin");

    model.component("comp1").selection().create("sel1", "Explicit");
    model.component("comp1").selection("sel1").label("\u87ba\u6813\u5b54");
    model.component("comp1").selection("sel1").geom(2);
    model.component("comp1").selection("sel1").set(21);
    model.component("comp1").selection("sel1").set("groupcontang", true);
    model.component("comp1").selection("sel1")
         .remove(16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 31, 33, 35, 36, 38, 39, 44, 45, 46, 47, 48, 49, 51, 52, 53, 59, 61, 63, 64, 66, 67, 68, 69, 70, 71, 72, 79, 82, 85);
    model.component("comp1").selection("sel1").add(40, 41, 42, 43, 54, 55, 56, 57);
    model.component("comp1").selection("sel1").set("color", "8");
    model.component("comp1").selection().create("sel2", "Explicit");
    model.component("comp1").selection("sel2").label("\u53f3\u9500\u5b54");
    model.component("comp1").selection("sel2").geom(2);
    model.component("comp1").selection("sel2").set(4);
    model.component("comp1").selection("sel2").set("groupcontang", true);
    model.component("comp1").selection("sel2").set("color", "9");
    model.component("comp1").selection().create("sel3", "Explicit");
    model.component("comp1").selection("sel3").label("\u5de6\u9500\u5b54");
    model.component("comp1").selection("sel3").geom(2);
    model.component("comp1").selection("sel3").set(75);
    model.component("comp1").selection("sel3").set("groupcontang", true);
    model.component("comp1").selection("sel3").set("color", "12");
    model.component("comp1").selection().create("uni1", "Union");
    model.component("comp1").selection("uni1").label("\u9500\u5b54");
    model.component("comp1").selection("uni1").set("entitydim", 2);
    model.component("comp1").selection("uni1").set("input", new String[]{"sel2", "sel3"});
    model.component("comp1").selection().create("sel4", "Explicit");
    model.component("comp1").selection("sel4").label("\u87ba\u6813\u5b54\u8fb9");
    model.component("comp1").selection("sel4").geom(1);
    model.component("comp1").selection("sel4").set("groupcontang", true);
    model.component("comp1").selection("sel4").add(82, 83, 84, 85, 87, 88, 89, 90, 118, 119, 120, 121, 125, 126);

    model.component("comp1").material().create("mat1", "Common");
    model.component("comp1").material("mat1").propertyGroup().create("Enu", "Young's modulus and Poisson's ratio");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("an1", "Analytic");
    model.component("comp1").material("mat1").propertyGroup("Enu").func().create("an2", "Analytic");
    model.component("comp1").material("mat1").propertyGroup().create("Murnaghan", "Murnaghan");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ElastoplasticModel", "Elastoplastic material model");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Ludwik", "Ludwik");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("JohnsonCook", "Johnson-Cook");
    model.component("comp1").material("mat1").propertyGroup().create("Swift", "Swift");
    model.component("comp1").material("mat1").propertyGroup().create("Voce", "Voce");
    model.component("comp1").material("mat1").propertyGroup("Voce").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("HockettSherby", "Hockett-Sherby");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func().create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("ArmstrongFrederick", "Armstrong-Frederick");
    model.component("comp1").material("mat1").propertyGroup("ArmstrongFrederick").func()
         .create("int1", "Interpolation");
    model.component("comp1").material("mat1").propertyGroup().create("Norton", "Norton");
    model.component("comp1").material("mat1").propertyGroup().create("Garofalo", "Garofalo (hyperbolic sine)");
    model.component("comp1").material("mat1").propertyGroup()
         .create("ChabocheViscoplasticity", "Chaboche viscoplasticity");
    model.component("comp1").material("mat1").label("Structural steel");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("noise", true);
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
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
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").label("Analytic 1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("funcname", "E");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1")
         .set("expr", "200[GPa]*(1 - 3.34e-4[1/K]*(T - 293.15[K]))");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("fununit", "Pa");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an1")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").label("Analytic 2");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("funcname", "nu");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2")
         .set("expr", "0.3*(1 + 1e-4[1/K]*(T - 293.15[K]))");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("args", new String[]{"T"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("fununit", "1");
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Enu").func("an2")
         .set("plotargs", new String[][]{{"T", "0", "1500"}});
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
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").func("int1")
         .set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("sigmags", "350[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Et", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").set("Ek", "1.045[GPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel")
         .set("sigmagh", "1.050[GPa]*epe*a(T)");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("ElastoplasticModel").addInput("effectiveplasticstrain");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("Ludwik").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
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
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("fununit", new String[]{"1"});
    model.component("comp1").material("mat1").propertyGroup("Voce").func("int1").set("argunit", new String[]{"K"});
    model.component("comp1").material("mat1").propertyGroup("Voce").set("sigma_voc", "249[MPa]*a(T)");
    model.component("comp1").material("mat1").propertyGroup("Voce").set("beta_voc", "9.3");
    model.component("comp1").material("mat1").propertyGroup("Voce").addInput("temperature");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").label("Interpolation 1");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1").set("funcname", "a");
    model.component("comp1").material("mat1").propertyGroup("HockettSherby").func("int1")
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
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
         .set("table", new String[][]{{"600[K]", "1"}, {"1100[K]", "0.1"}, {"1643[K]", "0"}});
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
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("A_cha", "1");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("sigRef_cha", "490[MPa]");
    model.component("comp1").material("mat1").propertyGroup("ChabocheViscoplasticity").set("n_cha", "9");
    model.component("comp1").material("mat1").set("family", "custom");
    model.component("comp1").material("mat1").set("lighting", "cooktorrance");
    model.component("comp1").material("mat1").set("fresnel", 0.9);
    model.component("comp1").material("mat1").set("roughness", 0.3);
    model.component("comp1").material("mat1").set("anisotropy", 0);
    model.component("comp1").material("mat1").set("flipanisotropy", false);
    model.component("comp1").material("mat1").set("metallic", 0);
    model.component("comp1").material("mat1").set("pearl", 0);
    model.component("comp1").material("mat1").set("diffusewrap", 0);
    model.component("comp1").material("mat1").set("clearcoat", 0);
    model.component("comp1").material("mat1").set("reflectance", 0);
    model.component("comp1").material("mat1").set("ambient", "custom");
    model.component("comp1").material("mat1")
         .set("customambient", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("diffuse", "custom");
    model.component("comp1").material("mat1")
         .set("customdiffuse", new double[]{0.6666666666666666, 0.6666666666666666, 0.6666666666666666});
    model.component("comp1").material("mat1").set("specular", "custom");
    model.component("comp1").material("mat1")
         .set("customspecular", new double[]{0.7843137254901961, 0.7843137254901961, 0.7843137254901961});
    model.component("comp1").material("mat1").set("noisecolor", "custom");
    model.component("comp1").material("mat1").set("customnoisecolor", new double[]{0, 0, 0});
    model.component("comp1").material("mat1").set("noisescale", 0);
    model.component("comp1").material("mat1").set("noise", "off");
    model.component("comp1").material("mat1").set("noisefreq", 1);
    model.component("comp1").material("mat1").set("normalnoisebrush", "0");
    model.component("comp1").material("mat1").set("normalnoisetype", "0");
    model.component("comp1").material("mat1").set("alpha", 1);
    model.component("comp1").material("mat1").set("anisotropyaxis", new double[]{0, 0, 1});

    model.component("comp1").physics("solid").create("fix1", "Fixed", 2);
    model.component("comp1").physics("solid").feature("fix1").selection().named("sel1");

    model.component("comp1").mesh("mesh1").create("edg1", "Edge");
    model.component("comp1").mesh("mesh1").feature("edg1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("edg1").create("dis1", "Distribution");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").selection().named("sel4");
    model.component("comp1").mesh("mesh1").feature("edg1").feature("dis1").set("numelem", 8);
    model.component("comp1").mesh("mesh1").create("swe1", "Sweep");
    model.component("comp1").mesh("mesh1").feature("swe1").selection().geom("geom1", 3);
    model.component("comp1").mesh("mesh1").feature("swe1").selection().set(1, 4, 5, 6, 9);
    model.component("comp1").mesh("mesh1").feature("swe1").selection("sourceface").set(1, 33, 37, 50, 72);
    model.component("comp1").mesh("mesh1").feature("swe1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("custom", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmaxactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hmax", "8[mm]");
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hgradactive", true);
    model.component("comp1").mesh("mesh1").feature("swe1").feature("size1").set("hgrad", 1.2);
    model.component("comp1").mesh("mesh1").create("ftet1", "FreeTet");
    model.component("comp1").mesh("mesh1").feature("ftet1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftet1").feature("size1").set("hauto", 3);

    model.title("\u652f\u67b6\u51e0\u4f55");

    model
         .description("\u672c\u4f8b\u662f\u6240\u6709\u652f\u67b6\u6559\u7a0b\u7684\u57fa\u7840\uff0c\u5176\u4e2d\u5305\u542b\u652f\u67b6\u51e0\u4f55\u7ed3\u6784\u3002");

    model.label("bracket_basic.mph");

    model.study().create("std1");
    model.study("std1").create("eig", "Eigenfrequency");
    model.study("std1").feature("eig").set("chkeigregion", true);
    model.study("std1").feature("eig").set("conrad", "1");
    model.study("std1").feature("eig").set("conradynhm", "1");
    model.study("std1").feature("eig").set("storefact", false);
    model.study("std1").feature("eig").set("solnum", "auto");
    model.study("std1").feature("eig").set("notsolnum", "auto");
    model.study("std1").feature("eig").set("outputmap", new String[]{});
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").set("ngenAUX", "1");
    model.study("std1").feature("eig").set("goalngenAUX", "1");
    model.study("std1").feature("eig").setSolveFor("/physics/solid", true);

    model.component("comp1").common().create("mpf1", "ParticipationFactors");

    model.study("std1").feature("eig").set("neigsactive", true);
    model.study("std1").feature("eig").set("neigs", 10);

    model.sol().create("sol1");
    model.sol("sol1").study("std1");
    model.sol("sol1").create("st1", "StudyStep");
    model.sol("sol1").feature("st1").set("study", "std1");
    model.sol("sol1").feature("st1").set("studystep", "eig");
    model.sol("sol1").create("v1", "Variables");
    model.sol("sol1").feature("v1").set("control", "eig");
    model.sol("sol1").create("e1", "Eigenvalue");
    model.sol("sol1").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol1").feature("e1").set("eigvfunscaleparam", "4.2299999999999996E-7");
    model.sol("sol1").feature("e1").set("control", "eig");
    model.sol("sol1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol1").attach("std1");
    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").set("defaultPlotID", "modeShape");
    model.result("pg1").label("\u632f\u578b (solid)");
    model.result("pg1").set("showlegends", false);
    model.result("pg1").create("surf1", "Surface");
    model.result("pg1").feature("surf1").set("expr", new String[]{"solid.disp"});
    model.result("pg1").feature("surf1").set("threshold", "manual");
    model.result("pg1").feature("surf1").set("thresholdvalue", 0.2);
    model.result("pg1").feature("surf1").set("colortable", "AuroraBorealis");
    model.result("pg1").feature("surf1").create("def", "Deform");
    model.result("pg1").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg1").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().evaluationGroup().create("std1EvgFrq", "EvaluationGroup");
    model.result().evaluationGroup("std1EvgFrq").set("defaultPlotID", "eigenfrequenciesTable_solid");
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
    model.result("pg1").label("\u632f\u578b\uff0c\u539f\u59cb");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg1").set("titletype", "none");
    model.result("pg1").set("plotarrayenable", true);
    model.result("pg1").set("arrayshape", "square");
    model.result("pg1").set("relrowpadding", 0.4);
    model.result("pg1").set("relcolumnpadding", 0.4);
    model.result("pg1").feature("surf1").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature().duplicate("surf2", "surf1");
    model.result("pg1").feature("surf2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf2").set("data", "dset1");
    model.result("pg1").feature("surf2").set("looplevel", new int[]{2});
    model.result("pg1").feature().duplicate("surf3", "surf2");
    model.result("pg1").feature("surf3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf3").set("looplevel", new int[]{3});
    model.result("pg1").feature().duplicate("surf4", "surf3");
    model.result("pg1").feature("surf4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf4").set("looplevel", new int[]{4});
    model.result("pg1").feature().duplicate("surf5", "surf4");
    model.result("pg1").feature("surf5").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf5").set("looplevel", new int[]{5});
    model.result("pg1").feature().duplicate("surf6", "surf5");
    model.result("pg1").feature("surf6").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("surf6").set("looplevel", new int[]{6});

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().param().set("dx", "-0.05");
    model.result().param().descr("dx", "\u6807\u6ce8\u7684\u504f\u79fb\u4f4d\u7f6e\uff0cX");
    model.result().param().set("dy", "-0.3");
    model.result().param().descr("dy", "\u6807\u6ce8\u7684\u504f\u79fb\u4f4d\u7f6e\uff0cY");
    model.result("pg1").run();
    model.result("pg1").create("ann1", "Annotation");
    model.result("pg1").feature("ann1").set("arraydim", "2");
    model.result("pg1").feature("ann1").set("text", "\u6a21\u6001 1");
    model.result("pg1").feature("ann1").set("posxexpr", "dx");
    model.result("pg1").feature("ann1").set("posyexpr", "dy");
    model.result("pg1").feature("ann1").set("showpoint", false);
    model.result("pg1").feature("ann1").set("manualindexing", true);
    model.result("pg1").feature().duplicate("ann2", "ann1");
    model.result("pg1").feature("ann2").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("ann2").set("text", "\u6a21\u6001 2");
    model.result("pg1").feature("ann2").set("colindex", 1);
    model.result("pg1").feature().duplicate("ann3", "ann2");
    model.result("pg1").feature("ann3").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("ann3").set("text", "\u6a21\u6001 3");
    model.result("pg1").feature("ann3").set("colindex", 2);
    model.result("pg1").feature().duplicate("ann4", "ann3");
    model.result("pg1").feature("ann4").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("ann4").set("text", "\u6a21\u6001 4");
    model.result("pg1").feature("ann4").set("rowindex", 1);
    model.result("pg1").feature("ann4").set("colindex", 0);
    model.result("pg1").feature().duplicate("ann5", "ann4");
    model.result("pg1").feature("ann5").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("ann5").set("text", "\u6a21\u6001 5");
    model.result("pg1").feature("ann5").set("colindex", 1);
    model.result("pg1").feature().duplicate("ann6", "ann5");
    model.result("pg1").feature("ann6").set("arraydim", "2");
    model.result("pg1").run();
    model.result("pg1").feature("ann6").set("text", "\u6a21\u6001 6");
    model.result("pg1").feature("ann6").set("colindex", 2);
    model.result("pg1").run();
    model.result().numerical().create("gev1", "EvalGlobal");
    model.result().numerical("gev1").setIndex("expr", "freq", 0);
    model.result().table().create("tbl1", "Table");
    model.result().table("tbl1").comments("\u5168\u5c40\u8ba1\u7b97 1");
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").setResult();

    model.param().set("P0", "30[MPa]");
    model.param().descr("P0", "\u5cf0\u503c\u8f7d\u8377\u5f3a\u5ea6");
    model.param().set("YC", "-300[mm]");
    model.param().descr("YC", "\u5b54\u4e2d\u5fc3\u7684 Y \u5750\u6807");

    model.component("comp1").func().create("an1", "Analytic");
    model.component("comp1").func("an1").set("funcname", "load");
    model.component("comp1").func("an1").set("expr", "F*cos(atan2(py,abs(px)))");
    model.component("comp1").func("an1").set("args", "F, py, px");
    model.component("comp1").func("an1").setIndex("argunit", "Pa", 0);
    model.component("comp1").func("an1").setIndex("argunit", "m", 1);
    model.component("comp1").func("an1").setIndex("argunit", "m", 2);
    model.component("comp1").func("an1").set("fununit", "Pa");

    model.component("comp1").coordSystem("sys1").set("frametype", "material");

    model.component("comp1").physics("solid").create("bndl1", "BoundaryLoad", 2);
    model.component("comp1").physics("solid").feature("bndl1").selection().named("uni1");
    model.component("comp1").physics("solid").feature("bndl1").set("coordinateSystem", "sys1");
    model.component("comp1").physics("solid").feature("bndl1")
         .set("FperArea", new String[]{"0", "0", "load(-P0,Z,Y-YC)*(sign(X)*(Y-YC)<0)"});

    model.study().create("std2");
    model.study("std2").create("stat", "Stationary");
    model.study("std2").feature("stat").set("plotgroup", "Default");
    model.study("std2").feature("stat").set("outputmap", new String[]{});
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").set("ngenAUX", "1");
    model.study("std2").feature("stat").set("goalngenAUX", "1");
    model.study("std2").feature("stat").setSolveFor("/physics/solid", true);
    model.study("std2").create("eig", "Eigenfrequency");
    model.study("std2").feature("eig").set("plotgroup", "Default");
    model.study("std2").feature("eig").set("chkeigregion", true);
    model.study("std2").feature("eig").set("conrad", "1");
    model.study("std2").feature("eig").set("conradynhm", "1");
    model.study("std2").feature("eig").set("storefact", false);
    model.study("std2").feature("eig").set("geometricNonlinearity", true);
    model.study("std2").feature("eig").set("outputmap", new String[]{});
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").set("ngenAUX", "1");
    model.study("std2").feature("eig").set("goalngenAUX", "1");
    model.study("std2").feature("eig").setSolveFor("/physics/solid", true);
    model.study("std2").feature("eig").set("neigsactive", true);
    model.study("std2").feature("eig").set("neigs", 10);
    model.study("std2").feature("eig").set("shift", "100");
    model.study("std2").setGenPlots(false);

    model.sol().create("sol2");
    model.sol("sol2").study("std2");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stat");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").set("control", "stat");
    model.sol("sol2").create("s1", "Stationary");
    model.sol("sol2").feature("s1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("s1").create("fc1", "FullyCoupled");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("d1").set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("d1").set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("d1").label("\u5efa\u8bae\u7684\u76f4\u63a5\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").create("i1", "Iterative");
    model.sol("sol2").feature("s1").feature("i1").set("linsolver", "gmres");
    model.sol("sol2").feature("s1").feature("i1").set("rhob", 400);
    model.sol("sol2").feature("s1").feature("i1").set("nlinnormuse", true);
    model.sol("sol2").feature("s1").feature("i1").label("\u5efa\u8bae\u7684\u8fed\u4ee3\u6c42\u89e3\u5668 (solid)");
    model.sol("sol2").feature("s1").feature("i1").create("mg1", "Multigrid");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").set("prefun", "gmg");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("pr").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").create("so1", "SOR");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("po").feature("so1").set("relax", 0.8);
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").create("d1", "Direct");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("linsolver", "pardiso");
    model.sol("sol2").feature("s1").feature("i1").feature("mg1").feature("cs").feature("d1")
         .set("pivotperturb", 1.0E-9);
    model.sol("sol2").feature("s1").feature("fc1").set("linsolver", "d1");
    model.sol("sol2").feature("s1").feature("fc1").set("termonres", "auto");
    model.sol("sol2").feature("s1").feature("fc1").set("reserrfact", 1000);
    model.sol("sol2").feature("s1").feature().remove("fcDef");
    model.sol("sol2").create("su1", "StoreSolution");
    model.sol("sol2").create("st2", "StudyStep");
    model.sol("sol2").feature("st2").set("study", "std2");
    model.sol("sol2").feature("st2").set("studystep", "eig");
    model.sol("sol2").create("v2", "Variables");
    model.sol("sol2").feature("v2").set("initmethod", "sol");
    model.sol("sol2").feature("v2").set("initsol", "sol2");
    model.sol("sol2").feature("v2").set("initsoluse", "sol3");
    model.sol("sol2").feature("v2").set("notsolmethod", "sol");
    model.sol("sol2").feature("v2").set("notsol", "sol2");
    model.sol("sol2").feature("v2").set("control", "eig");
    model.sol("sol2").create("e1", "Eigenvalue");
    model.sol("sol2").feature("e1").set("eigvfunscale", "maximum");
    model.sol("sol2").feature("e1").set("eigvfunscaleparam", "4.2299999999999996E-7");
    model.sol("sol2").feature("e1").set("storelinpoint", true);
    model.sol("sol2").feature("e1").set("control", "eig");
    model.sol("sol2").feature("e1").set("linpmethod", "sol");
    model.sol("sol2").feature("e1").set("linpsol", "sol2");
    model.sol("sol2").feature("e1").set("linpsoluse", "sol3");
    model.sol("sol2").feature("e1").set("control", "eig");
    model.sol("sol2").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").feature("v2").set("notsolnum", "auto");
    model.sol("sol2").feature("v2").set("notsolvertype", "solnum");
    model.sol("sol2").attach("std2");
    model.sol("sol2").runAll();

    model.result("pg1").run();
    model.result().duplicate("pg2", "pg1");
    model.result("pg2").run();
    model.result("pg2").label("\u632f\u578b\uff0c\u9884\u5e94\u529b");
    model.result("pg2").set("data", "dset2");
    model.result("pg2").feature("surf2").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf2").set("data", "dset2");
    model.result("pg2").feature("surf3").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf3").set("data", "dset2");
    model.result("pg2").feature("surf4").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf4").set("data", "dset2");
    model.result("pg2").feature("surf5").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf5").set("data", "dset2");
    model.result("pg2").feature("surf6").set("arraydim", "2");
    model.result("pg2").run();
    model.result("pg2").feature("surf6").set("data", "dset2");
    model.result("pg2").run();
    model.result().numerical("gev1").set("data", "dset2");
    model.result().numerical("gev1").setIndex("descr", "\u9891\u7387\uff0c\u9884\u5e94\u529b", 0);
    model.result().numerical("gev1").set("table", "tbl1");
    model.result().numerical("gev1").appendResult();
    model.result().create("pg3", "PlotGroup3D");
    model.result("pg3").set("data", "dset3");
    model.result("pg3").set("defaultPlotID", "stress");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result("pg3").set("frametype", "spatial");
    model.result("pg3").create("vol1", "Volume");
    model.result("pg3").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg3").feature("vol1").set("threshold", "manual");
    model.result("pg3").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg3").feature("vol1").set("resolution", "custom");
    model.result("pg3").feature("vol1").set("refine", 2);
    model.result("pg3").feature("vol1").set("colortable", "Prism");
    model.result("pg3").feature("vol1").create("def", "Deform");
    model.result("pg3").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg3").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg3").label("\u5e94\u529b (solid)");
    model.result().create("pg4", "PlotGroup3D");
    model.result("pg4").set("data", "dset3");
    model.result("pg4").set("defaultPlotID", "boundaryLoads");
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg4").set("showlegends", true);
    model.result("pg4").set("titletype", "label");
    model.result("pg4").set("frametype", "spatial");
    model.result("pg4").set("showlegendsunit", true);
    model.result("pg4").create("surf1", "Surface");
    model.result("pg4").feature("surf1").set("expr", new String[]{"1"});
    model.result("pg4").feature("surf1").label("\u7070\u8272\u8868\u9762");
    model.result("pg4").feature("surf1").set("coloring", "uniform");
    model.result("pg4").feature("surf1").set("color", "gray");
    model.result("pg4").feature("surf1").set("inheritcolor", false);
    model.result("pg4").feature("surf1").set("inheritrange", false);
    model.result("pg4").feature("surf1").set("inherittransparency", false);
    model.result("pg4").feature("surf1").create("def", "Deform");
    model.result("pg4").feature("surf1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("surf1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("surf1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("surf1").feature("def").set("scale", 0);
    model.result("pg4").feature("surf1").create("sel1", "Selection");
    model.result("pg4").feature("surf1").feature("sel1").selection().geom("geom1", 2);
    model.result("pg4").feature("surf1").feature("sel1").selection()
         .set(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88);
    model.result("pg4").feature("surf1").create("tran1", "Transparency");
    model.result("pg4").feature("surf1").feature("tran1").set("transparency", 0.8);
    model.result("pg4").create("arws1", "ArrowSurface");
    model.result("pg4").feature("arws1")
         .set("expr", new String[]{"solid.bndl1.F_Ax", "solid.bndl1.F_Ay", "solid.bndl1.F_Az"});
    model.result("pg4").feature("arws1").set("placement", "gausspoints");
    model.result("pg4").feature("arws1").set("arrowbase", "tail");
    model.result("pg4").feature("arws1").label("\u8fb9\u754c\u8f7d\u8377 1");
    model.result("pg4").feature("arws1").set("inheritplot", "none");
    model.result("pg4").feature("arws1").create("col", "Color");
    model.result("pg4").feature("arws1").feature("col").set("colordata", "arrowlength");
    model.result("pg4").feature("arws1").feature("col").set("coloring", "gradient");
    model.result("pg4").feature("arws1").feature("col").set("topcolor", "red");
    model.result("pg4").feature("arws1").feature("col").set("bottomcolor", "custom");
    model.result("pg4").feature("arws1").feature("col")
         .set("custombottomcolor", new double[]{0.5882353186607361, 0.5137255191802979, 0.5176470875740051});
    model.result("pg4").feature("arws1").set("color", "red");
    model.result("pg4").feature("arws1").create("def", "Deform");
    model.result("pg4").feature("arws1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg4").feature("arws1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result("pg4").feature("arws1").feature("def").set("scaleactive", true);
    model.result("pg4").feature("arws1").feature("def").set("scale", 0);
    model.result("pg4").feature().move("surf1", 1);
    model.result("pg4").label("\u8fb9\u754c\u8f7d\u8377 (solid)");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg3").run();
    model.result("pg3").feature().copy("arws1", "pg4/arws1");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").set("inheritplot", "vol1");
    model.result("pg3").feature("arws1").set("inheritcolor", false);
    model.result("pg3").feature("arws1").set("inheritrange", false);
    model.result("pg3").feature("arws1").set("arrowbase", "head");
    model.result("pg3").run();
    model.result("pg3").feature("arws1").feature("col").set("colorlegend", false);
    model.result("pg4").run();
    model.result().remove("pg4");
    model.result("pg3").run();
    model.result("pg3").feature("vol1").set("colortabletrans", "nonlinear");
    model.result("pg3").feature("vol1").set("colorcalibration", -1.4);
    model.result("pg3").run();
    model.result("pg3").label("\u6765\u81ea\u9759\u8f7d\u8377\u7684\u5e94\u529b");
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u7279\u5f81\u9891\u7387\u6bd4\u8f83");
    model.result("pg4").set("ylabelactive", true);
    model.result("pg4").set("ylabel", "\u9891\u7387 [Hz]");
    model.result("pg4").set("manualgrid", true);
    model.result("pg4").set("legendpos", "upperleft");
    model.result("pg4").create("tblp1", "Table");
    model.result("pg4").feature("tblp1").set("markerpos", "datapoints");
    model.result("pg4").feature("tblp1").set("linewidth", "preference");
    model.result("pg4").feature("tblp1").set("xaxisdata", "rowindex");
    model.result("pg4").feature("tblp1").set("plotcolumninput", "manual");
    model.result("pg4").feature("tblp1").set("plotcolumns", new int[]{2, 3});
    model.result("pg4").feature("tblp1").set("legend", true);
    model.result("pg4").feature("tblp1").set("linestyle", "none");
    model.result("pg4").feature("tblp1").set("linemarker", "cycle");
    model.result("pg4").run();
    model.result("pg4").set("ylog", true);
    model.result("pg1").run();

    model.title("\u652f\u67b6 - \u7279\u5f81\u9891\u7387\u5206\u6790");

    model
         .description("\u672c\u4f8b\u8ba1\u7b97\u4e09\u7ef4\u652f\u67b6\u7684\u56fa\u6709\u9891\u7387\uff0c\u5176\u4e2d\u7b2c\u4e8c\u4e2a\u7814\u7a76\u5bf9\u652f\u67b6\u627f\u53d7\u9759\u8f7d\u8377\u7684\u60c5\u51b5\u6267\u884c\u9884\u5e94\u529b\u7279\u5f81\u9891\u7387\u5206\u6790\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();
    model.sol("sol3").clearSolutionData();

    model.label("bracket_eigenfrequency.mph");

    model.component("comp1").common().create("fsd1", "FreeShapeDomain");
    model.component("comp1").common("fsd1").selection().all();
    model.component("comp1").common("fsd1").selection().set(1);
    model.component("comp1").common().create("fsb1", "FreeShapeBoundary");
    model.component("comp1").common("fsb1").selection().set(19);
    model.component("comp1").common("fsb1").set("maximumDisplacementType", "Custom");
    model.component("comp1").common("fsb1").setIndex("move_lBound", "-2[cm]", 0);
    model.component("comp1").common("fsb1").setIndex("move_uBound", "2[cm]", 0);
    model.component("comp1").common("fsb1").setIndex("move_lock", true, 1);
    model.component("comp1").common("fsb1").setIndex("move_lock", true, 2);
    model.component("comp1").common("fsb1").set("filterRadiusType", "Custom");
    model.component("comp1").common("fsb1").set("L_min", "1[cm]");
    model.component("comp1").common().create("mss1", "MirrorShape");
    model.component("comp1").common("mss1").selection().set(9);
    model.component("comp1").common("mss1").set("pointType", "Custom");
    model.component("comp1").common("mss1").set("normalType", "Custom");

    model.component("comp1").cpl().create("genext1", "GeneralExtrusion");
    model.component("comp1").cpl("genext1").selection().geom("geom1", 2);
    model.component("comp1").cpl("genext1").selection().set(19);
    model.component("comp1").cpl("genext1").set("dstmap", new String[]{"10[cm]*sign(Xg)", "y", "z"});
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Yg", 1);
    model.component("comp1").cpl("genext1").setIndex("dstmap", "Zg", 2);
    model.component("comp1").cpl("genext1").set("srcframe", "geometry");

    model.component("comp1").common().create("pres1", "PrescribedDeformationDeformedGeometry");
    model.component("comp1").common("pres1").selection().all();
    model.component("comp1").common("pres1").selection().geom("geom1", 2);
    model.component("comp1").common("pres1").selection().set(1);
    model.component("comp1").common("pres1")
         .set("prescribedDeformation", new String[]{"genext1(material.dX)", "genext1(material.dY)", "genext1(material.dZ)"});

    model.component("comp1").mesh("mesh1").run("edg1");
    model.component("comp1").mesh("mesh1").create("ftri1", "FreeTri");
    model.component("comp1").mesh("mesh1").feature("ftri1").selection().set(1, 72);
    model.component("comp1").mesh("mesh1").feature("ftri1").create("size1", "Size");
    model.component("comp1").mesh("mesh1").feature("ftri1").feature("size1").set("hauto", 1);
    model.component("comp1").mesh("mesh1").run();

    model.study("std2").feature().remove("stat");
    model.study("std2").feature().remove("eig");

    model.sol().remove("sol2");

    model.study("std2").create("stef", "StationaryThenEigenfrequency");
    model.study("std2").create("sho", "ShapeOptimization");
    model.study("std2").feature("sho").set("mmamaxiter", 50);
    model.study("std2").feature("sho").setIndex("optobj", "freq", 0);
    model.study("std2").feature("sho").set("objectivetype", "maximization");
    model.study("std2").feature("sho").set("objectivesolution", "min");
    model.study("std2").feature("sho").set("objectivescaling", "init");
    model.study("std1").label("\u521d\u59cb\u8bbe\u8ba1");
    model.study("std2").label("\u5f62\u72b6\u4f18\u5316");
    model.study("std2").setGenPlots(true);

    model.sol().create("sol2");

    model.component("comp1").mesh("mesh1").stat().selection().geom(3);
    model.component("comp1").mesh("mesh1").stat().selection().set(1);

    model.sol("sol2").study("std2");
    model.sol("sol2").create("st1", "StudyStep");
    model.sol("sol2").feature("st1").set("study", "std2");
    model.sol("sol2").feature("st1").set("studystep", "stef");
    model.sol("sol2").create("v1", "Variables");
    model.sol("sol2").feature("v1").feature("comp1_fsb1_d").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_material_disp").set("scalemethod", "manual");
    model.sol("sol2").feature("v1").feature("comp1_fsb1_d").set("scaleval", "-(-2[cm])");
    model.sol("sol2").feature("v1").feature("comp1_material_disp").set("scaleval", "0.0057939115880823585");
    model.sol("sol2").feature("v1").set("control", "stef");
    model.sol("sol2").create("o1", "Optimization");
    model.sol("sol2").feature("o1").set("control", "sho");
    model.sol("sol2").feature("o1").create("s1", "StationaryAttrib");
    model.sol("sol2").feature("o1").feature("s1")
         .set("solveforvars", new String[]{"comp1_material_disp", "comp1_fsb1_d", "comp1_fsb1_c"});
    model.sol("sol2").feature("o1").create("e1", "EigenvalueAttrib");
    model.sol("sol2").feature("o1").feature("e1").set("solveforvars", new String[]{"comp1_fsb1_c", "comp1_u"});
    model.sol("sol2").feature("o1").feature("e1").set("control", "stef");
    model.sol("sol2").feature("o1").feature("e1").feature("aDef").set("cachepattern", true);
    model.sol("sol2").attach("std2");
    model.sol("sol2").runFromTo("st1", "v1");

    model.result().create("pg5", "PlotGroup3D");
    model.result("pg5").set("data", "dset2");
    model.result("pg5").set("defaultPlotID", "stress");
    model.result("pg5").label("\u5e94\u529b (solid)");
    model.result("pg5").set("frametype", "spatial");
    model.result("pg5").create("vol1", "Volume");
    model.result("pg5").feature("vol1").set("expr", new String[]{"solid.misesGp"});
    model.result("pg5").feature("vol1").set("threshold", "manual");
    model.result("pg5").feature("vol1").set("thresholdvalue", 0.2);
    model.result("pg5").feature("vol1").set("resolution", "custom");
    model.result("pg5").feature("vol1").set("refine", 2);
    model.result("pg5").feature("vol1").set("colortable", "Prism");
    model.result("pg5").feature("vol1").create("def", "Deform");
    model.result("pg5").feature("vol1").feature("def").set("expr", new String[]{"u", "v", "w"});
    model.result("pg5").feature("vol1").feature("def").set("descr", "\u4f4d\u79fb\u573a");
    model.result().create("pg6", "PlotGroup3D");
    model.result().dataset().duplicate("dset2g1", "dset2");
    model.result().dataset("dset2g1").label("\u5f62\u72b6\u4f18\u5316/\u89e3 2 (2) - \u51e0\u4f55");
    model.result().dataset("dset2g1").set("frametype", "geometry");
    model.result("pg6").label("\u5f62\u72b6\u4f18\u5316");
    model.result("pg6").set("data", "dset2");
    model.result("pg6").set("frametype", "geometry");
    model.result("pg6").set("edgecolor", "gray");
    model.result("pg6").set("titletype", "none");
    model.result("pg6").create("line1", "Line");
    model.result("pg6").feature("line1").set("expr", "1");
    model.result("pg6").feature("line1").set("coloring", "uniform");
    model.result("pg6").feature("line1").set("color", "fromtheme");
    model.result("pg6").create("con1", "Surface");
    model.result("pg6").feature("con1").set("expr", "fsd1.rel_disp");
    model.result("pg6").feature("con1").set("colortabletype", "discrete");
    model.result("pg6").feature("con1").set("bandcount", 20);
    model.result("pg6").feature("con1").set("rangecoloractive", true);
    model.result("pg6").feature("con1").set("rangecolormin", 0);
    model.result("pg6").feature("con1").set("rangecolormax", 1);
    model.result("pg6").feature("con1").set("colortable", "RainbowLight");
    model.result("pg6").feature("con1").set("smooth", "none");
    model.result("pg6").create("arws1", "ArrowSurface");
    model.result("pg6").feature("arws1").set("data", "dset2g1");
    model.result("pg6").feature("arws1").set("expr", new String[]{"fsd1.dXg", "fsd1.dYg", "fsd1.dZg"});
    model.result("pg6").feature("arws1").set("scaleactive", true);
    model.result("pg6").feature("arws1").set("scale", "1");
    model.result("pg6").feature("arws1").set("placement", "uniform");
    model.result("pg6").feature("arws1").set("arrowcount", 200);
    model.result("pg6").feature("arws1").create("sel1", "Selection");
    model.result("pg6").feature("arws1").feature("sel1").selection().named("dsel_fsd1");
    model.result().create("pg7", "PlotGroup3D");
    model.result("pg7").set("data", "dset2");
    model.result("pg7").label("\u53d8\u5f62\u51e0\u4f55");
    model.result("pg7").create("mesh1", "Mesh");
    model.result("pg7").feature("mesh1").set("meshdomain", "volume");
    model.result("pg7").feature("mesh1").set("colortable", "TrafficFlow");
    model.result("pg7").feature("mesh1").set("colortabletrans", "nonlinear");
    model.result("pg7").feature("mesh1").set("nonlinearcolortablerev", true);
    model.result("pg7").feature("mesh1").create("sel1", "MeshSelection");
    model.result("pg7").feature("mesh1").feature("sel1").selection().set(1, 9);
    model.result("pg7").feature("mesh1").set("qualmeasure", "custom");
    model.result("pg7").feature("mesh1").set("qualexpr", "comp1.material.relVol");
    model.result("pg7").feature("mesh1").set("colorrangeunitinterval", false);
    model.result("pg5").run();

    model.sol("sol2").feature("o1").set("gcmma", false);

    model.study("std2").feature("sho").set("plot", true);
    model.study("std2").feature("sho").set("plotgroup", "pg6");

    model.sol("sol2").runAll();

    model.result("pg5").run();

    model.study("std2").feature("sho").set("probewindow", "");

    model.result("pg7").run();

    model.title("\u652f\u67b6 - \u7279\u5f81\u9891\u7387\u5f62\u72b6\u4f18\u5316");

    model
         .description("\u672c\u4f8b\u4f7f\u7528\u5f62\u72b6\u4f18\u5316\u6765\u6700\u5927\u5316\u4e09\u7ef4\u652f\u67b6\u7684\u6700\u4f4e\u56fa\u6709\u9891\u7387\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();
    model.sol("sol2").clearSolutionData();

    model.label("bracket_eigenfrequency_shape_optimization.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
