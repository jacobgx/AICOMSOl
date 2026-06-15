/*
 * landing_gear.java
 */

import com.comsol.model.*;
import com.comsol.model.util.*;

/** Model exported on Apr 30 2026, 11:20 by COMSOL 6.3.0.290. */
public class landing_gear {

  public static Model run() {
    Model model = ModelUtil.create("Model");

    model
         .modelPath("D:\\Program Files\\COMSOL\\COMSOL63\\Multiphysics\\applications\\Multibody_Dynamics_Module\\Automotive_and_Aerospace");

    model.component().create("comp1", true);

    model.component("comp1").geom().create("geom1", 2);

    model.component("comp1").mesh().create("mesh1");

    model.component("comp1").physics().create("mbd", "MultibodyDynamics", "geom1");
    model.component("comp1").physics().create("ht", "HeatTransfer", "geom1");

    model.study().create("std1");
    model.study("std1").create("time", "Transient");
    model.study("std1").feature("time").setSolveFor("/physics/mbd", true);
    model.study("std1").feature("time").setSolveFor("/physics/ht", true);

    model.param().set("m", "5000[kg]");
    model.param().descr("m", "\u98de\u673a\u7684\u8d28\u91cf");
    model.param().set("vi", "10[m/s]");
    model.param().descr("vi", "\u98de\u673a\u4e0b\u964d\u901f\u5ea6");
    model.param().set("k_sa", "2.5e6[N/m]");
    model.param().descr("k_sa", "\u51cf\u632f\u5668\u5f39\u7c27\u5e38\u6570");
    model.param().set("c_sa", "5e4[N*s/m]");
    model.param().descr("c_sa", "\u51cf\u632f\u5668\u963b\u5c3c\u7cfb\u6570");
    model.param().set("k_t", "2.5e7[N/m]");
    model.param().descr("k_t", "\u8f6e\u80ce\u521a\u5ea6");
    model.param().set("c_t", "2.5e6[N*s/m]");
    model.param().descr("c_t", "\u8f6e\u80ce\u963b\u5c3c\u7cfb\u6570");
    model.param().set("d", "0.1[m]");
    model.param().descr("d", "\u9762\u5916\u5c3a\u5bf8");

    model.component("comp1").geom("geom1").create("c1", "Circle");
    model.component("comp1").geom("geom1").feature("c1").set("r", 0.15);
    model.component("comp1").geom("geom1").feature("c1").set("pos", new double[]{-0.3, 0});
    model.component("comp1").geom("geom1").run("c1");
    model.component("comp1").geom("geom1").create("pol1", "Polygon");
    model.component("comp1").geom("geom1").feature("pol1").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("x", "-0.35 -0.35 -0.35 -0.15 -0.15 -0.05 -0.05 -0.05 -0.05 0 0 0 0 -0.15+0.1*(sqrt(5)-2) -0.15+0.1*(sqrt(5)-2) -0.25 -0.25 -0.25");
    model.component("comp1").geom("geom1").feature("pol1")
         .set("y", "0 0.3 0.3 0.4 0.4 0.4 0.4 1 1 1 1 0.3 0.3 0.3 0.3 0.3-0.05*(sqrt(5)-1) 0.3-0.05*(sqrt(5)-1) 0");
    model.component("comp1").geom("geom1").run("pol1");
    model.component("comp1").geom("geom1").create("pol2", "Polygon");
    model.component("comp1").geom("geom1").feature("pol2").set("source", "vectors");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("x", "-0.15 -0.15 -0.15 -0.05 -0.05 -0.05 -0.05 0 0 0 0 -0.05 -0.05 -0.05");
    model.component("comp1").geom("geom1").feature("pol2")
         .set("y", "0.8 1.3+0.1*sqrt(2) 1.3+0.1*sqrt(2) 1.4+0.1*sqrt(2) 1.4+0.1*sqrt(2) 1.8 1.8 1.8 1.8 1.4 1.4 1.4 1.4 0.8");
    model.component("comp1").geom("geom1").run("pol2");
    model.component("comp1").geom("geom1").create("copy1", "Copy");
    model.component("comp1").geom("geom1").feature("copy1").selection("input").set("pol1");
    model.component("comp1").geom("geom1").run("copy1");
    model.component("comp1").geom("geom1").create("dif1", "Difference");
    model.component("comp1").geom("geom1").feature("dif1").selection("input").set("c1");
    model.component("comp1").geom("geom1").feature("dif1").selection("input2").set("copy1");
    model.component("comp1").geom("geom1").run("dif1");
    model.component("comp1").geom("geom1").create("mir1", "Mirror");
    model.component("comp1").geom("geom1").feature("mir1").selection("input").set("dif1", "pol1", "pol2");
    model.component("comp1").geom("geom1").feature("mir1").set("keep", true);
    model.component("comp1").geom("geom1").run("mir1");
    model.component("comp1").geom("geom1").create("uni1", "Union");
    model.component("comp1").geom("geom1").feature("uni1").selection("input").set("mir1(2)", "pol1");
    model.component("comp1").geom("geom1").feature("uni1").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni1");
    model.component("comp1").geom("geom1").create("uni2", "Union");
    model.component("comp1").geom("geom1").feature("uni2").selection("input").set("mir1(3)", "pol2");
    model.component("comp1").geom("geom1").feature("uni2").set("intbnd", false);
    model.component("comp1").geom("geom1").run("uni2");
    model.component("comp1").geom("geom1").create("uni3", "Union");
    model.component("comp1").geom("geom1").feature("uni3").selection("input").set("dif1", "mir1(1)", "uni1");
    model.component("comp1").geom("geom1").feature("fin").set("action", "assembly");
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

    model.component("comp1").physics("mbd").prop("d").set("d", "d");
    model.component("comp1").physics("mbd").feature("init1").set("InitialValueType", "locallyDefined");
    model.component("comp1").physics("mbd").feature("init1").set("ut", new String[]{"0", "-vi", "0"});
    model.component("comp1").physics("mbd").create("att1", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att1").selection().set(34, 40);
    model.component("comp1").physics("mbd").feature("att1").set("ConnectionType", "FlexibleType");
    model.component("comp1").physics("mbd").create("att2", "Attachment", 1);
    model.component("comp1").physics("mbd").feature("att2").selection().set(10, 14);
    model.component("comp1").physics("mbd").feature("att2").set("ConnectionType", "FlexibleType");
    model.component("comp1").physics("mbd").create("prj1", "PrismaticJoint", -1);
    model.component("comp1").physics("mbd").feature("prj1").set("Source", "att1");
    model.component("comp1").physics("mbd").feature("prj1").set("Destination", "att2");
    model.component("comp1").physics("mbd").feature("prj1").set("e", new int[]{0, -1, 0});
    model.component("comp1").physics("mbd").feature("prj1").create("sd1", "SpringAndDamper", -1);
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("k_u", "k_sa");
    model.component("comp1").physics("mbd").feature("prj1").feature("sd1").set("c_u", "c_sa");
    model.component("comp1").physics("mbd").create("adm1", "AddedMass1", 1);
    model.component("comp1").physics("mbd").feature("adm1").selection().set(37, 39);
    model.component("comp1").physics("mbd").feature("adm1").set("MassType", "mTot");
    model.component("comp1").physics("mbd").feature("adm1")
         .set("mTot", new String[]{"0", "0", "0", "0", "m", "0", "0", "0", "0"});
    model.component("comp1").physics("mbd").create("gacc1", "GravityAcceleration", -1);
    model.component("comp1").physics("mbd").create("spf1", "SpringFoundation1", 1);
    model.component("comp1").physics("mbd").feature("spf1").selection().set(2, 19);
    model.component("comp1").physics("mbd").feature("spf1").set("SpringType", "kTot");
    model.component("comp1").physics("mbd").feature("spf1")
         .set("kTot", new String[]{"0", "0", "0", "0", "k_t", "0", "0", "0", "0"});
    model.component("comp1").physics("mbd").feature("spf1").set("ViscousType", "DampTot");
    model.component("comp1").physics("mbd").feature("spf1")
         .set("DampTot", new String[]{"0", "0", "0", "0", "c_t", "0", "0", "0", "0"});

    model.component("comp1").cpl().create("intop1", "Integration");
    model.component("comp1").cpl("intop1").set("axisym", true);
    model.component("comp1").cpl("intop1").selection().all();

    model.component("comp1").variable().create("var1");
    model.component("comp1").variable("var1").set("Q", "mbd.prj1.Qdamper");
    model.component("comp1").variable("var1").descr("Q", "\u6bcf\u79d2\u4ea7\u751f\u7684\u70ed\u91cf");
    model.component("comp1").variable("var1").set("Wp", "intop1(mbd.rho*g_const*v*mbd.d)");
    model.component("comp1").variable("var1").descr("Wp", "\u52bf\u80fd");
    model.component("comp1").variable("var1").set("h_sa", "timeint(0,t,Q)");
    model.component("comp1").variable("var1")
         .descr("h_sa", "\u51cf\u632f\u5668\u4e2d\u7684\u80fd\u91cf\u635f\u5931");
    model.component("comp1").variable("var1").set("Ws_sa", "mbd.prj1.Wspring");
    model.component("comp1").variable("var1")
         .descr("Ws_sa", "\u51cf\u9707\u5668\u4e2d\u5b58\u50a8\u7684\u80fd\u91cf");

    model.component("comp1").physics("ht").prop("PhysicalModelProperty").set("dz", "d");
    model.component("comp1").physics("ht").create("pbhs1", "PairBoundaryHeatSource", 1);
    model.component("comp1").physics("ht").feature("pbhs1").set("pairs", new String[]{"ap1"});
    model.component("comp1").physics("ht").feature("pbhs1").set("heatSourceType", "HeatRate");
    model.component("comp1").physics("ht").feature("pbhs1").set("Pb", "Q");

    model.component("comp1").mesh("mesh1").autoMeshSize(3);
    model.component("comp1").mesh("mesh1").run();

    model.study("std1").feature("time").set("tlist", "range(0,0.01,0.7)");
    model.study("std1").createAutoSequences("all");

    model.sol("sol1").runAll();

    model.result().create("pg1", "PlotGroup2D");
    model.result("pg1").label("\u4f4d\u79fb (mbd)");
    model.result("pg1").set("frametype", "spatial");
    model.result("pg1").feature().create("surf1", "Surface");
    model.result("pg1").feature("surf1").label("\u8868\u9762");
    model.result("pg1").feature("surf1").set("colortable", "SpectrumLight");
    model.result("pg1").feature("surf1").set("data", "parent");
    model.result("pg1").feature("surf1").feature().create("def1", "Deform");
    model.result("pg1").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg1").feature("surf1").feature("def1").set("scaleactive", true);
    model.result().create("pg2", "PlotGroup2D");
    model.result("pg2").label("\u901f\u5ea6 (mbd)");
    model.result("pg2").set("frametype", "spatial");
    model.result("pg2").feature().create("surf1", "Surface");
    model.result("pg2").feature("surf1").label("\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("descractive", true);
    model.result("pg2").feature("surf1").set("expr", "mod(dom,10)");
    model.result("pg2").feature("surf1").set("descr", "\u57df\u7f16\u53f7");
    model.result("pg2").feature("surf1").set("rangecoloractive", "on");
    model.result("pg2").feature("surf1").set("rangecolormin", -0.5);
    model.result("pg2").feature("surf1").set("rangecolormax", 9.5);
    model.result("pg2").feature("surf1").set("colortable", "Cyclic");
    model.result("pg2").feature("surf1").set("colorlegend", false);
    model.result("pg2").feature("surf1").set("colortabletype", "discrete");
    model.result("pg2").feature("surf1").set("smooth", "none");
    model.result("pg2").feature("surf1").set("data", "parent");
    model.result("pg2").feature("surf1").feature().create("def1", "Deform");
    model.result("pg2").feature("surf1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg2").feature().create("arwl1", "ArrowLine");
    model.result("pg2").feature("arwl1").label("\u7ebf\u4e0a\u7bad\u5934");
    model.result("pg2").feature("arwl1").set("expr", new String[]{"mbd.u_tX", "mbd.u_tY"});
    model.result("pg2").feature("arwl1").set("placement", "elements");
    model.result("pg2").feature("arwl1").set("data", "parent");
    model.result("pg2").feature("arwl1").feature().create("def1", "Deform");
    model.result("pg2").feature("arwl1").feature("def1").label("\u53d8\u5f62");
    model.result("pg2").feature("arwl1").feature("def1").set("scaleactive", true);
    model.result().create("pg3", "PlotGroup2D");
    model.result("pg3").label("\u6e29\u5ea6 (ht)");
    model.result("pg3").feature().create("surf1", "Surface");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("solutionparams", "parent");
    model.result("pg3").feature("surf1").set("expr", "T");
    model.result("pg3").feature("surf1").set("colortable", "HeatCameraLight");
    model.result("pg3").feature("surf1").set("showsolutionparams", "on");
    model.result("pg3").feature("surf1").set("data", "parent");
    model.result("pg1").run();
    model.result("pg1").run();
    model.result("pg1").label("\u5e94\u529b");
    model.result("pg1").set("frametype", "material");
    model.result("pg1").setIndex("looplevel", 7, 0);
    model.result("pg1").run();
    model.result("pg1").feature("surf1").set("expr", "mbd.misesGp");
    model.result("pg1").feature("surf1").set("descr", "von Mises \u5e94\u529b");
    model.result("pg1").feature("surf1").set("resolution", "norefine");
    model.result("pg1").run();
    model.result("pg3").run();
    model.result("pg3").feature("surf1").set("resolution", "norefine");
    model.result("pg3").feature("surf1").create("def1", "Deform");
    model.result("pg3").run();
    model.result("pg3").feature("surf1").feature("def1").set("scaleactive", true);
    model.result("pg3").feature("surf1").feature("def1").set("scale", 1);
    model.result("pg3").run();
    model.result().create("pg4", "PlotGroup1D");
    model.result("pg4").run();
    model.result("pg4").label("\u76f8\u5bf9\u4f4d\u79fb");
    model.result("pg4").set("titletype", "none");
    model.result("pg4").create("glob1", "Global");
    model.result("pg4").feature("glob1").set("markerpos", "datapoints");
    model.result("pg4").feature("glob1").set("linewidth", "preference");
    model.result("pg4").feature("glob1").set("expr", new String[]{"mbd.prj1.u"});
    model.result("pg4").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u4f4d\u79fb"});
    model.result("pg4").feature("glob1").set("unit", new String[]{"m"});
    model.result("pg4").feature("glob1").set("legend", false);
    model.result("pg4").feature("glob1").set("linewidth", 2);
    model.result("pg4").run();
    model.result("pg4").run();
    model.result().duplicate("pg5", "pg4");
    model.result("pg5").run();
    model.result("pg5").label("\u76f8\u5bf9\u901f\u5ea6");
    model.result("pg5").run();
    model.result("pg5").feature("glob1").set("expr", new String[]{"mbd.prj1.u_t"});
    model.result("pg5").feature("glob1").set("descr", new String[]{"\u76f8\u5bf9\u901f\u5ea6"});
    model.result("pg5").feature("glob1").set("unit", new String[]{"m/s"});
    model.result("pg5").run();
    model.result("pg5").run();
    model.result().duplicate("pg6", "pg5");
    model.result("pg6").run();
    model.result("pg6").label("\u5173\u8282\u529b");
    model.result("pg6").run();
    model.result("pg6").feature("glob1").set("expr", new String[]{"mbd.prj1.Fy"});
    model.result("pg6").feature("glob1").set("descr", new String[]{"\u5173\u8282\u529b\uff0cy \u5206\u91cf"});
    model.result("pg6").feature("glob1").set("unit", new String[]{"N"});
    model.result("pg6").run();
    model.result().create("pg7", "PlotGroup1D");
    model.result("pg7").run();
    model.result("pg7").label("\u80fd\u91cf");
    model.result("pg7").set("titletype", "none");
    model.result("pg7").create("glob1", "Global");
    model.result("pg7").feature("glob1").set("markerpos", "datapoints");
    model.result("pg7").feature("glob1").set("linewidth", "preference");
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u52bf\u80fd"});
    model.result("pg7").feature("glob1").set("unit", new String[]{"J"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot"});
    model.result("pg7").feature("glob1").set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot", "mbd.Ws_tot"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd", "\u603b\u5f39\u6027\u5e94\u53d8\u80fd"});
    model.result("pg7").feature("glob1").set("expr", new String[]{"Wp", "mbd.Wk_tot", "mbd.Ws_tot", "h_sa"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd", "\u603b\u5f39\u6027\u5e94\u53d8\u80fd", "\u51cf\u632f\u5668\u4e2d\u7684\u80fd\u91cf\u635f\u5931"});
    model.result("pg7").feature("glob1")
         .set("expr", new String[]{"Wp", "mbd.Wk_tot", "mbd.Ws_tot", "h_sa", "Ws_sa"});
    model.result("pg7").feature("glob1")
         .set("descr", new String[]{"\u52bf\u80fd", "\u603b\u52a8\u80fd", "\u603b\u5f39\u6027\u5e94\u53d8\u80fd", "\u51cf\u632f\u5668\u4e2d\u7684\u80fd\u91cf\u635f\u5931", "\u51cf\u9707\u5668\u4e2d\u5b58\u50a8\u7684\u80fd\u91cf"});
    model.result("pg7").feature("glob1").set("linewidth", 2);
    model.result("pg7").feature("glob1").set("linemarker", "cycle");
    model.result("pg7").feature("glob1").set("markerpos", "interp");
    model.result("pg7").run();
    model.result("pg7").run();
    model.result("pg7").set("ylabelactive", true);
    model.result("pg7").set("ylabel", "\u80fd\u91cf (J)");
    model.result("pg7").set("axislimits", true);
    model.result("pg7").set("ymax", "3.1e5");
    model.result("pg7").run();
    model.result().export().create("anim1", "Animation");
    model.result().export("anim1").set("fontsize", "9");
    model.result().export("anim1").set("colortheme", "globaltheme");
    model.result().export("anim1").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim1").set("background", "color");

    return model;
  }

  public static Model run2(Model model) {
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
    model.result().export("anim1").set("target", "player");
    model.result().export("anim1").set("maxframes", 100);
    model.result().export().create("anim2", "Animation");
    model.result().export("anim2").set("fontsize", "9");
    model.result().export("anim2").set("colortheme", "globaltheme");
    model.result().export("anim2").set("customcolor", new double[]{1, 1, 1});
    model.result().export("anim2").set("background", "color");
    model.result().export("anim2").set("gltfincludelines", "on");
    model.result().export("anim2").set("title1d", "on");
    model.result().export("anim2").set("legend1d", "on");
    model.result().export("anim2").set("logo1d", "on");
    model.result().export("anim2").set("options1d", "on");
    model.result().export("anim2").set("title2d", "on");
    model.result().export("anim2").set("legend2d", "on");
    model.result().export("anim2").set("logo2d", "on");
    model.result().export("anim2").set("options2d", "off");
    model.result().export("anim2").set("title3d", "on");
    model.result().export("anim2").set("legend3d", "on");
    model.result().export("anim2").set("logo3d", "on");
    model.result().export("anim2").set("options3d", "off");
    model.result().export("anim2").set("axisorientation", "on");
    model.result().export("anim2").set("grid", "on");
    model.result().export("anim2").set("axes1d", "on");
    model.result().export("anim2").set("axes2d", "on");
    model.result().export("anim2").set("showgrid", "on");
    model.result().export("anim2").set("target", "player");
    model.result().export("anim2").set("plotgroup", "pg3");
    model.result().export("anim2").set("maxframes", 100);
    model.result("pg3").run();

    model.title("\u8d77\u843d\u67b6\u7684\u5e94\u529b\u548c\u53d1\u70ed");

    model
         .description("\u672c\u4f8b\u9610\u660e\u8d77\u843d\u67b6\u7684\u591a\u4f53\u52a8\u529b\u5b66\u4eff\u771f\u3002\u4f7f\u7528\u68f1\u67f1\u5173\u8282\u53ca\u5176\u76f8\u8fde\u7684\u5f39\u7c27\u963b\u5c3c\u5668\u5bf9\u51cf\u9707\u652f\u67f1\u8fdb\u884c\u5efa\u6a21\u3002\u7814\u7a76\u4e86\u7531\u4e8e\u51cf\u9707\u652f\u67f1\u80fd\u8017\u5f15\u8d77\u7684\u53d1\u70ed\u548c\u6e29\u5347\u3002");

    model.label("landing_gear.mph");

    return model;
  }

  public static void main(String[] args) {
    Model model = run();
    run2(model);
  }

}
