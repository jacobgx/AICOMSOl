/*
 * differential_gear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:19 by COMSOL 6.3.0.290. */
public class differential_gear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 3);
    model.component("comp1").geom("geom1").geomRep("comsol");

    model.component("comp1").mesh().create("mesh1");
    model.component("comp1").mesh("mesh1").contribute("geom/detail", true);

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);

//    To import content from file, use:
//    model.param().loadFile("FILENAME");
    model.param().set("n_pn", "15", "\u9f7f\u6570\uff0c\u5c0f\u9f7f\u8f6e");
    model.param().set("dp_pn", "25[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u5c0f\u9f7f\u8f6e");
    model.param().set("gamma_pn", "20[deg]", "\u9525\u89d2\uff0c\u5c0f\u9f7f\u8f6e");
    model.param().set("n_rg", "60", "\u9f7f\u6570\uff0c\u9f7f\u5708");
    model.param().set("dp_rg", "100[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u9f7f\u5708");
    model.param().set("gamma_rg", "70[deg]", "\u9525\u89d2\uff0c\u9f7f\u5708");
    model.param().set("n_sp", "20", "\u9f7f\u6570\uff0c\u661f\u5f62\u9f7f\u8f6e");
    model.param().set("dp_sp", "25[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u661f\u5f62\u9f7f\u8f6e");
    model.param().set("gamma_sp", "30[deg]", "\u9525\u89d2\uff0c\u661f\u5f62\u9f7f\u8f6e");
    model.param().set("n_sd", "30", "\u9f7f\u6570\uff0c\u534a\u8f74\u9f7f\u8f6e");
    model.param().set("dp_sd", "37.5[mm]", "\u8282\u5706\u76f4\u5f84\uff0c\u534a\u8f74\u9f7f\u8f6e");
    model.param().set("gamma_sd", "60[deg]", "\u9525\u89d2\uff0c\u534a\u8f74\u9f7f\u8f6e");
    model.param().set("alpha", "25[deg]", "\u538b\u529b\u89d2");
    model.param().set("d_sp", "15[mm]", "\u661f\u5f62\u9f7f\u8f6e\u4e2d\u5fc3\u504f\u79fb\u91cf");
    model.param().set("omega", "200[rad/s]", "\u4f20\u52a8\u8f74\u89d2\u901f\u5ea6");
    model.param().set("cf", "15[N*m*s/rad]", "\u6469\u64e6\u963b\u5c3c\u7cfb\u6570");

    model.component("comp1").geom("geom1").create("imp1", "Import");
    model.component("comp1").geom("geom1").feature("imp1").set("filename", "differential_gear.mphbin");
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

    model.component("comp1").physics("mbd").create("bvg1", "BevelGear", 3);
    model.component("comp1").physics("mbd").feature("bvg1").label("\u9525\u9f7f\u8f6e\uff1a\u5c0f\u9f7f\u8f6e");
    model.component("comp1").physics("mbd").feature("bvg1").selection().set(6);
    model.component("comp1").physics("mbd").feature("bvg1").set("nt", "n_pn");
    model.component("comp1").physics("mbd").feature("bvg1").set("dp", "dp_pn");
    model.component("comp1").physics("mbd").feature("bvg1").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("bvg1").set("gamma", "gamma_pn");
    model.component("comp1").physics("mbd").feature("bvg1").set("eg", new int[]{-1, 0, 0});
    model.component("comp1").physics("mbd").feature("bvg1").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature("bvg1").set("xc", new String[]{"dp_rg/2", "-dp_pn/2", "0"});
    model.component("comp1").physics("mbd").create("bvg2", "BevelGear", 3);
    model.component("comp1").physics("mbd").feature("bvg2")
         .label("\u9525\u9f7f\u8f6e\uff1a\u73af\u5f62\u9f7f\u8f6e");
    model.component("comp1").physics("mbd").feature("bvg2").selection().set(1);
    model.component("comp1").physics("mbd").feature("bvg2").set("nt", "n_rg");
    model.component("comp1").physics("mbd").feature("bvg2").set("dp", "dp_rg");
    model.component("comp1").physics("mbd").feature("bvg2").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("bvg2").set("gamma", "gamma_rg");
    model.component("comp1").physics("mbd").feature("bvg2").set("eg", new int[]{0, -1, 0});
    model.component("comp1").physics("mbd").feature("bvg2").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").create("bvg3", "BevelGear", 3);
    model.component("comp1").physics("mbd").feature("bvg3")
         .label("\u9525\u9f7f\u8f6e\uff1a\u661f\u5f62\u9f7f\u8f6e 1");
    model.component("comp1").physics("mbd").feature("bvg3").selection().set(5);
    model.component("comp1").physics("mbd").feature("bvg3").set("nt", "n_sp");
    model.component("comp1").physics("mbd").feature("bvg3").set("dp", "dp_sp");
    model.component("comp1").physics("mbd").feature("bvg3").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("bvg3").set("gamma", "gamma_sp");
    model.component("comp1").physics("mbd").feature("bvg3").set("eg", new int[]{0, 0, -1});
    model.component("comp1").physics("mbd").feature("bvg3").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature("bvg3").set("xc", new String[]{"0", "-d_sp", "dp_sd/2"});
    model.component("comp1").physics("mbd").feature().duplicate("bvg4", "bvg3");
    model.component("comp1").physics("mbd").feature("bvg4")
         .label("\u9525\u9f7f\u8f6e\uff1a\u661f\u5f62\u9f7f\u8f6e 2");
    model.component("comp1").physics("mbd").feature("bvg4").selection().set(4);
    model.component("comp1").physics("mbd").feature("bvg4").set("eg", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature("bvg4").set("xc", new String[]{"0", "-d_sp", "-dp_sd/2"});
    model.component("comp1").physics("mbd").create("bvg5", "BevelGear", 3);
    model.component("comp1").physics("mbd").feature("bvg5").label("\u9525\u9f7f\u8f6e\uff1a\u4fa7\u9f7f\u8f6e 1");
    model.component("comp1").physics("mbd").feature("bvg5").selection().set(3);
    model.component("comp1").physics("mbd").feature("bvg5").set("nt", "n_sd");
    model.component("comp1").physics("mbd").feature("bvg5").set("dp", "dp_sd");
    model.component("comp1").physics("mbd").feature("bvg5").set("alpha", "alpha");
    model.component("comp1").physics("mbd").feature("bvg5").set("gamma", "gamma_sd");
    model.component("comp1").physics("mbd").feature("bvg5").set("eg", new int[]{0, -1, 0});
    model.component("comp1").physics("mbd").feature("bvg5").set("CenterOfRotationType", "UserDefined");
    model.component("comp1").physics("mbd").feature("bvg5").set("xc", new String[]{"0", "-d_sp+dp_sp/2", "0"});
    model.component("comp1").physics("mbd").feature().duplicate("bvg6", "bvg5");
    model.component("comp1").physics("mbd").feature("bvg6").label("\u9525\u9f7f\u8f6e\uff1a\u4fa7\u9f7f\u8f6e 2");
    model.component("comp1").physics("mbd").feature("bvg6").selection().set(2);
    model.component("comp1").physics("mbd").feature("bvg6").set("eg", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature("bvg6").set("xc", new String[]{"0", "-d_sp-dp_sp/2", "0"});
    model.component("comp1").physics("mbd").feature("bvg1").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1")
         .set("RotationType", "PrescribedRotationGroup");
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1").set("Omega", new int[]{-1, 0, 0});
    model.component("comp1").physics("mbd").feature("bvg1").feature("pdr1").set("phi0", "omega*t");
    model.component("comp1").physics("mbd").feature("bvg2").create("pdr1", "PrescribedDispRot", -1);
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1").setIndex("Direction", true, 0);
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1").setIndex("Direction", true, 1);
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1").setIndex("Direction", true, 2);
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1")
         .set("RotationType", "ConstrainedRotationGroup");
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1").setIndex("ConstrainedRotation", true, 0);
    model.component("comp1").physics("mbd").feature("bvg2").feature("pdr1").setIndex("ConstrainedRotation", true, 2);

    model.nodeGroup().create("grp1", "Physics", "mbd");
    model.nodeGroup("grp1").placeAfter("init1");
    model.nodeGroup("grp1").add("bvg1");
    model.nodeGroup("grp1").add("bvg2");
    model.nodeGroup("grp1").add("bvg3");
    model.nodeGroup("grp1").add("bvg4");
    model.nodeGroup("grp1").add("bvg5");
    model.nodeGroup("grp1").add("bvg6");
    model.nodeGroup("grp1").label("\u9525\u9f7f\u8f6e");

    model.component("comp1").physics("mbd").create("grp1", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp1").set("Wheel", "bvg1");
    model.component("comp1").physics("mbd").feature("grp1").set("Pinion", "bvg2");
    model.component("comp1").physics("mbd").create("grp2", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp2").set("Wheel", "bvg3");
    model.component("comp1").physics("mbd").feature("grp2").set("Pinion", "bvg5");
    model.component("comp1").physics("mbd").create("grp3", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp3").set("Wheel", "bvg3");
    model.component("comp1").physics("mbd").feature("grp3").set("Pinion", "bvg6");
    model.component("comp1").physics("mbd").create("grp4", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp4").set("Wheel", "bvg4");
    model.component("comp1").physics("mbd").feature("grp4").set("Pinion", "bvg5");
    model.component("comp1").physics("mbd").create("grp5", "GearPair", -1);
    model.component("comp1").physics("mbd").feature("grp5").set("Wheel", "bvg4");
    model.component("comp1").physics("mbd").feature("grp5").set("Pinion", "bvg6");

    model.nodeGroup().create("grp2", "Physics", "mbd");
    model.nodeGroup("grp2").placeAfter("init1");
    model.nodeGroup("grp2").add("grp1");
    model.nodeGroup("grp2").add("grp2");
    model.nodeGroup("grp2").add("grp3");
    model.nodeGroup("grp2").add("grp4");
    model.nodeGroup("grp2").add("grp5");
    model.nodeGroup("grp2").label("\u9f7f\u8f6e\u526f");

    model.component("comp1").physics("mbd").create("hgj1", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj1").set("Source", "bvg2");
    model.component("comp1").physics("mbd").feature("hgj1").set("Destination", "bvg3");
    model.component("comp1").physics("mbd").feature("hgj1").set("CenterOfJointType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("hgj1").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj1").feature("cjp1").selection().set(1528, 1531);
    model.component("comp1").physics("mbd").feature("hgj1").set("e", new int[]{0, 0, 1});
    model.component("comp1").physics("mbd").feature().duplicate("hgj2", "hgj1");
    model.component("comp1").physics("mbd").feature("hgj2").set("Destination", "bvg4");
    model.component("comp1").physics("mbd").feature("hgj2").feature("cjp1").selection().set(1282, 1341);
    model.component("comp1").physics("mbd").create("hgj3", "HingeJoint", -1);
    model.component("comp1").physics("mbd").feature("hgj3").set("Source", "fixed");
    model.component("comp1").physics("mbd").feature("hgj3").set("Destination", "bvg5");
    model.component("comp1").physics("mbd").feature("hgj3").set("CenterOfJointType", "CentroidOfSelectedEntities");
    model.component("comp1").physics("mbd").feature("hgj3").set("EntityLevel", "Point");
    model.component("comp1").physics("mbd").feature("hgj3").feature("cjp1").selection().set(1060, 1061);
    model.component("comp1").physics("mbd").feature("hgj3").set("e", new int[]{0, 1, 0});
    model.component("comp1").physics("mbd").feature().duplicate("hgj4", "hgj3");
    model.component("comp1").physics("mbd").feature("hgj4").set("Destination", "bvg6");
    model.component("comp1").physics("mbd").feature("hgj4").feature("cjp1").selection().set(748, 749);
    model.component("comp1").physics("mbd").feature("hgj3").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj3").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj3").feature("afm1").set("Ms", "cf*omega_d");
    model.component("comp1").physics("mbd").feature("hgj4").create("afm1", "AppliedForceAndMoment", -1);
    model.component("comp1").physics("mbd").feature("hgj4").feature("afm1")
         .set("AppliedOnSelectedAttachment", "Joint");
    model.component("comp1").physics("mbd").feature("hgj4").feature("afm1").set("Ms", "-cf*omega_d");

    model.nodeGroup().create("grp3", "Physics", "mbd");
    model.nodeGroup("grp3").placeAfter("init1");
    model.nodeGroup("grp3").add("hgj1");
    model.nodeGroup("grp3").add("hgj2");
    model.nodeGroup("grp3").add("hgj3");
    model.nodeGroup("grp3").add("hgj4");
    model.nodeGroup("grp3").label("\u94f0\u94fe\u5173\u8282");

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("omega_i", "d(mbd.hgj3.th,t)");
    model.component("comp1").variable("var1").descr("omega_i", "\u5185\u8f6e\u89d2\u901f\u5ea6");
    model.component("comp1").variable("var1").set("omega_o", "d(mbd.hgj4.th,t)");
    model.component("comp1").variable("var1").descr("omega_o", "\u5916\u8f6e\u89d2\u901f\u5ea6");
    model.component("comp1").variable("var1").set("omega_r", "1+5*rect1(omega*t)");
    model.component("comp1").variable("var1").descr("omega_r", "\u5185\u5916\u8f6e\u901f\u6bd4");
    model.component("comp1").variable("var1").set("omega_d", "omega_o-omega_r*omega_i");
    model.component("comp1").variable("var1").descr("omega_d", "\u5185\u5916\u8f6e\u901f\u5ea6\u5dee");

    model.component("comp1").func().create("rect1", "Rectangle");
    model.component("comp1").func("rect1").set("lower", "2*pi");
    model.component("comp1").func("rect1").set("upper", "6*pi");
    model.component("comp1").func("rect1").set("smooth", "pi/4");

    model.component("comp1").mesh("mesh1").autoMeshSize(6);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,5e-4,0.125)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup3D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup3D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("vol1", "Volume");
    model.result("pg2").feature("vol1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("descractive", true);
    model.result("pg2").feature("vol1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("vol1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("vol1").set("rangecoloractive", "on");
    model.result("pg2").feature("vol1").set("rangecolormin", -0.5);
    model.result("pg2").feature("vol1").set("rangecolormax", 9.5);
    model.result("pg2").feature("vol1").set("colortable", "Cyclic");
    model.result("pg2").feature("vol1").set("colorlegend", false);
    model.result("pg2").feature("vol1").set("colortabletype", "discrete");
    model.result("pg2").feature("vol1").set("smooth", "none");
    model.result("pg2").feature("vol1").set("data", "parent");
    model.result("pg2").feature("vol1").feature().create("def1", "Deform");
    model.result("pg2").feature("vol1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("vol1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY", "mbd.u_tZ"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");

    return model;
  }

  public static Model run2(Model model) {
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result("pg1").run();

    model.component("comp1").view("view1").set("showgrid", false);

    model.result().duplicate("pg3", "pg1");
    model.result("pg3").run();
    model.result("pg3").label("\u901f\u5ea6\uff1a\u5927\u5c0f");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("expr", "mbd.vel");
    model.result("pg3").run();
    model.result().dataset().duplicate("dset2", "dset1");
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().geom("geom1", 3);
    model.result().dataset("dset2").selection().set(1, 2, 3, 4, 5);
    model.result("pg3").run();
    model.result().duplicate("pg4", "pg3");
    model.result("pg4").run();
    model.result("pg4").label("\u901f\u5ea6\uff1aY \u5206\u91cf");
    model.result("pg4").set("data", "dset2");
    model.result("pg4").setIndex("looplevel", 161, 0);
    model.result("pg4").set("view", "new");
    model.result("pg4").run();
    model.result("pg4").run();
    model.result("pg4").feature("surf1").set("expr", "mbd.u_tY");
    model.result("pg4").feature("surf1").set("colortable", "AuroraAustralis");

    model.view("view2").set("showgrid", false);

    model.result("pg4").run();

    model.component("comp1").physics("mbd").prop("Results").set("ReferenceFrame", "bvg2");

    model.sol("sol1").updateSolution();

    model.result("pg1").run();
    model.result().dataset().duplicate("dset3", "dset2");
    model.result().dataset("dset3").selection().geom("geom1", 3);
    model.result().dataset("dset3").selection().set(1, 4, 5);
    model.result("pg1").run();
    model.result().duplicate("pg5", "pg1");
    model.result("pg5").run();
    model.result("pg5").label("\u901f\u5ea6\uff1a\u73af\u5f62\u9f7f\u8f6e\u53c2\u8003");
    model.result("pg5").set("data", "dset3");
    model.result("pg5").setIndex("looplevel", 161, 0);
    model.result("pg5").set("edges", false);
    model.result("pg5").run();
    model.result("pg5").feature("surf1").set("expr", "mbd.vel_ref");
    model.result("pg5").run();
    model.result("pg5").feature("surf1").feature("def1").set("expr", new String[]{"u_ref", "v_ref", "w_ref"});
    model.result("pg5").run();
    model.result("pg5").set("view", "new");
    model.result("pg5").run();

    model.view("view3").set("showgrid", false);

    model.result().create("pg6", "PlotGroup1D");
    model.result("pg6").run();
    model.result("pg6").label("\u89d2\u901f\u5ea6\uff1a\u8f66\u8f6e");
    model.result("pg6").create("glob1", "Global");
    model.result("pg6").feature("glob1").set("markerpos", "datapoints");
    model.result("pg6").feature("glob1").set("linewidth", "preference");
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj3.th_t"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.hgj3.th_t", "mbd.hgj4.th_t"});
    model.result("pg6").feature("glob1")
         .set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6", "\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg6").feature("glob1").set("linewidth", 2);
    model.result("pg6").feature("glob1").set("legendmethod", "manual");
    model.result("pg6").feature("glob1").setIndex("legends", "\u5185\u8f6e", 0);
    model.result("pg6").feature("glob1").setIndex("legends", "\u5916\u8f6e", 1);
    model.result("pg6").run();
    model.result("pg6").set("titletype", "none");
    model.result("pg6").set("ylabelactive", true);
    model.result("pg6").set("ylabel", "\u89d2\u901f\u5ea6 (rad/s)");
    model.result("pg6").run();
    model.result().duplicate("pg7", "pg6");
    model.result("pg7").run();
    model.result("pg7").label("\u89d2\u901f\u5ea6\uff1a\u661f\u5f62\u9f7f\u8f6e");
    model.result("pg7").set("titletype", "manual");
    model.result("pg7").set("title", "\u661f\u5f62\u9f7f\u8f6e");
    model.result("pg7").run();
    model.result("pg7").feature("glob1").set("expr", new String[]{"mbd.hgj1.th_t"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u89d2\u901f\u5ea6"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"rad/s"});
    model.result("pg7").feature("glob1").set("legend", false);
    model.result("pg7").run();
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
    model.result().export("anim1").set("plotgroup", "pg3");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export().duplicate("anim2", "anim1");
    model.result().export("anim2").showFrame();
    model.result().export("anim2").set("plotgroup", "pg4");
    model.result().export().duplicate("anim3", "anim2");
    model.result().export("anim3").showFrame();
    model.result().export("anim3").set("plotgroup", "pg5");
    model.result("pg3").run();

    model.title("\u5dee\u52a8\u8f6e\u7cfb\u673a\u6784");

    model
         .description("\u672c\u4f8b\u9610\u660e\u4e86\u6c7d\u8f66\u4e2d\u5e38\u7528\u7684\u5dee\u52a8\u8f6e\u7cfb\u673a\u6784\u7684\u5efa\u6a21\u8fc7\u7a0b\u3002\u4f7f\u7528\u4e86\u4e94\u7ec4\u76f4\u9f7f\u9525\u9f7f\u8f6e\u5bf9\u5dee\u52a8\u9f7f\u8f6e\u5efa\u6a21\u3002\u6539\u53d8\u5dee\u52a8\u9f7f\u8f6e\u4e24\u4e2a\u8f93\u51fa\u8f74\u7684\u89d2\u901f\u5ea6\u6bd4\u7387\uff0c\u6a21\u62df\u8f66\u8f86\u5728\u5e73\u76f4\u548c\u5f2f\u66f2\u9053\u8def\u4e0a\u7684\u8fd0\u52a8\u3002\u6267\u884c\u77ac\u6001\u7814\u7a76\u4ee5\u8ba1\u7b97\u6240\u6709\u9f7f\u8f6e\u7684\u89d2\u901f\u5ea6\u4ee5\u53ca\u661f\u5f62\u9f7f\u8f6e\u7ed5\u5176\u81ea\u8eab\u8f74\u7684\u65cb\u8f6c\u60c5\u51b5\u3002");

    model.mesh().clearMeshes();

    model.sol("sol1").clearSolutionData();

    model.label("differential_gear.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
